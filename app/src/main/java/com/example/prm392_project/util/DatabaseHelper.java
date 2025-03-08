package com.example.prm392_project.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.prm392_project.R;
import com.example.prm392_project.data.dao.CategoriesDAO;
import com.example.prm392_project.data.dao.OrderItemsDAO;
import com.example.prm392_project.data.dao.OrdersDAO;
import com.example.prm392_project.data.dao.ProductsDAO;
import com.example.prm392_project.data.dao.ProductsVariantsDAO;
import com.example.prm392_project.data.dao.SizesDAO;
import com.example.prm392_project.data.dao.UserDAO;
import com.example.prm392_project.data.model.Categories;
import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.model.ProductVariants;
import com.example.prm392_project.data.model.Products;
import com.example.prm392_project.data.model.Sizes;
import com.example.prm392_project.data.model.Users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Database(entities = {Users.class, Sizes.class, ProductVariants.class, Products.class, Orders.class, OrderItems.class, Categories.class}, version = 1, exportSchema = false)
@TypeConverters(ConvertHelper.class)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract UserDAO userDao();

    public abstract SizesDAO sizesDAO();

    public abstract ProductsVariantsDAO productVariantsDAO();

    public abstract ProductsDAO productsDAO();

    public abstract OrdersDAO ordersDAO();

    public abstract OrderItemsDAO orderItemsDAO();

    public abstract CategoriesDAO categoriesDAO();

    private static volatile DatabaseHelper INSTANCE;

    public static DatabaseHelper getInstance(Context ctx) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(), DatabaseHelper.class, "prm392_project").fallbackToDestructiveMigration().addCallback(roomCallBack).build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback roomCallBack = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            AppExecutors.getDatabaseExecutor().execute(() -> {
                DatabaseHelper database = INSTANCE;
                if (database != null) {
                    insertDefaultUsers(database);
                    insertDefaultCategory(database);
                    insertDefaultSize(database);
                    insertDefaultProduct(database);
                    insertDefaultProductImage(database);
                    insertDefaultProductVariants(database);
                }
            });
        }
    };

    private static void insertDefaultCategory(DatabaseHelper database) {
        CategoriesDAO categoriesDAO = database.categoriesDAO();
        if (categoriesDAO.getAllCategoryAsync().isEmpty()) {
            List<Categories> categories = Arrays.asList(
                    new Categories("Sneaker", "Giày thể thao phong cách, phù hợp đi chơi và vận động nhẹ.", R.raw.cat1),
                    new Categories("Running Shoes", "Giày chạy bộ êm ái, hỗ trợ tối đa khi vận động.", R.raw.cat2),
                    new Categories("Basketball Shoes", "Giày bóng rổ chuyên dụng, giúp tăng độ nảy và bảo vệ cổ chân.", R.raw.cat3),
                    new Categories("Football Shoes", "Giày đá bóng chuyên dụng, thiết kế đinh bám sân.", R.raw.cat4),
                    new Categories("Tennis Shoes", "Giày chơi tennis với độ bám cao và hỗ trợ di chuyển ngang.", R.raw.cat5),
                    new Categories("Golf Shoes", "Giày chơi golf chống nước, giúp tăng độ bám trên sân cỏ.", R.raw.cat6),
                    new Categories("Hiking Shoes", "Giày leo núi chắc chắn, chống trượt và chịu được thời tiết khắc nghiệt.", R.raw.cat1),
                    new Categories("Skate Shoes", "Giày trượt ván với đế phẳng, độ bám tốt và thiết kế thời trang.", R.raw.cat2),
                    new Categories("Casual Shoes", "Giày thời trang hàng ngày, phù hợp đi làm và đi chơi.", R.raw.cat3),
                    new Categories("Loafers", "Giày lười không dây, dễ đi, thoải mái và thanh lịch.", R.raw.cat4),
                    new Categories("Sandals", "Dép sandal thoáng khí, phù hợp cho mùa hè.", R.raw.cat5),
                    new Categories("Slip-on", "Giày không dây tiện lợi, phù hợp di chuyển nhanh.", R.raw.cat6),
                    new Categories("Formal Shoes", "Giày da sang trọng, thích hợp cho sự kiện và công việc.", R.raw.cat1),
                    new Categories("Boots", "Giày bốt cổ cao, giữ ấm và bảo vệ chân tốt.", R.raw.cat2),
                    new Categories("Chelsea Boots", "Giày bốt cổ thấp, thiết kế thời trang và dễ mang.", R.raw.cat3),
                    new Categories("Hiking Boots", "Giày bốt leo núi, chống nước và bảo vệ chân.", R.raw.cat4),
                    new Categories("Work Boots", "Giày bốt bảo hộ, chống trượt và bảo vệ chân.", R.raw.cat5),
                    new Categories("Winter Boots", "Giày bốt mùa đông, giữ ấm và chống nước.", R.raw.cat6),
                    new Categories("Heels", "Giày cao gót dành cho nữ, phong cách và sang trọng.", R.raw.cat1),
                    new Categories("Espadrilles", "Giày vải đế cói, nhẹ nhàng và phù hợp mùa hè.", R.raw.cat2),
                    new Categories("Moccasins", "Giày da mềm, không dây, thoải mái và phong cách.", R.raw.cat3),
                    new Categories("Oxford Shoes", "Giày Oxford cổ điển, phù hợp với trang phục lịch sự.", R.raw.cat4),
                    new Categories("Derby Shoes", "Giày Derby nam, dễ phối đồ và thanh lịch.", R.raw.cat5)
            );
            categoriesDAO.insertCategories(categories);
        }
    }

    private static void insertDefaultSize(DatabaseHelper database) {
        SizesDAO sizeDAO = database.sizesDAO();
        if (sizeDAO.getAllSizesAsync().isEmpty()) {
            List<Sizes> sizes = Arrays.asList(new Sizes("36"), new Sizes("37"), new Sizes("38"), new Sizes("39"), new Sizes("40"), new Sizes("41"), new Sizes("42"), new Sizes("43"));
            sizeDAO.insertSizes(sizes);
        }
    }

    private static void insertDefaultProduct(DatabaseHelper database) {
        ProductsDAO productsDAO = database.productsDAO();
        CategoriesDAO categoriesDAO = database.categoriesDAO();
        if (productsDAO.getAllProductAsync().isEmpty()) {
            List<Products> products = Arrays.asList(
                    new Products("Nike Air Force 1", 2000f, "Giày sneaker phong cách cổ điển, phù hợp cho mọi dịp.", "Nike", R.raw.shoes1, categoriesDAO.getCategoryByName("Sneaker")),
                    new Products("Adidas Ultraboost", 2200f, "Giày chạy bộ với công nghệ Boost, êm ái và đàn hồi tốt.", "Adidas", R.raw.shoes2, categoriesDAO.getCategoryByName("Running Shoes")),
                    new Products("Jordan 1 Retro High", 2500f, "Giày bóng rổ huyền thoại, biểu tượng của thời trang.", "Nike", R.raw.shoes3, categoriesDAO.getCategoryByName("Basketball Shoes")),
                    new Products("Puma Future Z", 1800f, "Giày đá bóng chuyên nghiệp, thiết kế nhẹ và bám sân tốt.", "Puma", R.raw.shoes4, categoriesDAO.getCategoryByName("Football Shoes")),
                    new Products("NikeCourt Air Zoom Vapor", 2100f, "Giày tennis cao cấp, hỗ trợ di chuyển nhanh chóng.", "Nike", R.raw.shoes1, categoriesDAO.getCategoryByName("Tennis Shoes")),
                    new Products("FootJoy Pro SL", 3000f, "Giày golf cao cấp, chống nước và độ bám tuyệt vời.", "FootJoy", R.raw.shoes2, categoriesDAO.getCategoryByName("Golf Shoes")),
                    new Products("Columbia Newton Ridge", 1700f, "Giày leo núi chống nước, chắc chắn và bền bỉ.", "Columbia", R.raw.shoes3, categoriesDAO.getCategoryByName("Hiking Shoes")),
                    new Products("Vans Old Skool", 1500f, "Giày trượt ván cổ điển, phong cách streetwear.", "Vans", R.raw.shoes4, categoriesDAO.getCategoryByName("Skate Shoes")),
                    new Products("Converse Chuck Taylor", 1300f, "Giày casual huyền thoại, dễ phối đồ và thoải mái.", "Converse", R.raw.shoes1, categoriesDAO.getCategoryByName("Casual Shoes")),
                    new Products("Gucci Horsebit Loafer", 5000f, "Giày lười cao cấp, sang trọng và lịch lãm.", "Gucci", R.raw.shoes2, categoriesDAO.getCategoryByName("Loafers")),
                    new Products("Timberland 6-inch Boots", 2800f, "Giày bốt cao cổ, chống nước và siêu bền.", "Timberland", R.raw.shoes3, categoriesDAO.getCategoryByName("Boots")),
                    new Products("Dr. Martens 1460", 2600f, "Giày Chelsea Boots cổ điển, chất lượng cao.", "Dr. Martens", R.raw.shoes4, categoriesDAO.getCategoryByName("Chelsea Boots")),
                    new Products("Merrell Moab 2 Mid", 2400f, "Giày bốt leo núi, hỗ trợ cổ chân và chống nước.", "Merrell", R.raw.shoes1, categoriesDAO.getCategoryByName("Hiking Boots")),
                    new Products("Red Wing Iron Ranger", 3200f, "Giày bốt bảo hộ chắc chắn, phong cách vintage.", "Red Wing", R.raw.shoes2, categoriesDAO.getCategoryByName("Work Boots")),
                    new Products("The North Face ThermoBall", 3500f, "Giày bốt mùa đông giữ ấm cực tốt.", "The North Face", R.raw.shoes3, categoriesDAO.getCategoryByName("Winter Boots")),
                    new Products("Christian Louboutin So Kate", 7000f, "Giày cao gót sang trọng, tôn dáng hoàn hảo.", "Christian Louboutin", R.raw.shoes4, categoriesDAO.getCategoryByName("Heels")),
                    new Products("Soludos Original Dali", 1400f, "Giày Espadrilles vải đế cói nhẹ nhàng.", "Soludos", R.raw.shoes1, categoriesDAO.getCategoryByName("Espadrilles")),
                    new Products("Minnetonka Classic Moc", 1600f, "Giày moccasins da lộn mềm mại và thoải mái.", "Minnetonka", R.raw.shoes2, categoriesDAO.getCategoryByName("Moccasins")),
                    new Products("Allen Edmonds Park Avenue", 4500f, "Giày Oxford nam, thiết kế sang trọng.", "Allen Edmonds", R.raw.shoes3, categoriesDAO.getCategoryByName("Oxford Shoes")),
                    new Products("Dr. Martens 1461", 2400f, "Giày Derby bền bỉ, phong cách cổ điển.", "Dr. Martens", R.raw.shoes4, categoriesDAO.getCategoryByName("Derby Shoes"))
            );
            productsDAO.insertProducts(products);
        }
    }

    private static void insertDefaultProductVariants(DatabaseHelper database) {
        ProductsVariantsDAO productVariantsDAO = database.productVariantsDAO();
        ProductsDAO productsDAO = database.productsDAO();
        SizesDAO sizesDAO = database.sizesDAO();

        if (true) {
            List<Products> products = productsDAO.getAllProductAsync();
            List<Sizes> sizes = sizesDAO.getAllSizesAsync();
            List<ProductVariants> productVariants = new ArrayList<>();

            Random random = new Random();
            for (Products product : products) {
                for (Sizes size : sizes) {
                    int stockQuantity = random.nextInt(50) + 1;
                    productVariants.add(new ProductVariants(product.getId(), size.getSize(), stockQuantity));
                }
            }
            productVariantsDAO.insertProductVariants(productVariants);
        }
    }

    private static void insertDefaultProductImage(DatabaseHelper database) {

    }

    private static void insertDefaultUsers(DatabaseHelper database) {
        UserDAO userDAO = database.userDao();
        if (userDAO.getAllUserAsync().isEmpty()) {
            Users defaultUser = new Users("Alex", "alex@gmail.com", "12345", "09081827374", "123 Le loi", "USER");
            userDAO.insertUser(defaultUser);
        }

    }
}
