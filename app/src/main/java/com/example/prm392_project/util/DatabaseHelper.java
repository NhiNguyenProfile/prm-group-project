package com.example.prm392_project.util;

import android.content.Context;

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
                    new Categories("Loafers", "Giày chạy bộ êm ái, hỗ trợ tối đa khi vận động.", R.raw.cat2),
                    new Categories("Slip-ons", "Giày bóng rổ chuyên dụng, giúp tăng độ nảy và bảo vệ cổ chân.", R.raw.cat3),
                    new Categories("Espadrilles", "Giày đá bóng chuyên dụng, thiết kế đinh bám sân.", R.raw.cat4),
                    new Categories("Oxford", "Giày chơi tennis với độ bám cao và hỗ trợ di chuyển ngang.", R.raw.cat5),
                    new Categories("Derby", "Giày chơi golf chống nước, giúp tăng độ bám trên sân cỏ.", R.raw.cat6)
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
                    new Products("Adidas Ultraboost", 2200f, "Giày thể thao êm ái, hỗ trợ tối đa khi vận động.", "Adidas", R.raw.shoes2, categoriesDAO.getCategoryByName("Sneaker")),
                    new Products("Vans Old Skool", 1500f, "Giày sneaker phong cách streetwear, dễ phối đồ.", "Vans", R.raw.shoes3, categoriesDAO.getCategoryByName("Sneaker")),
                    new Products("Puma RS-X", 1900f, "Giày sneaker thiết kế độc đáo, phù hợp đi chơi.", "Puma", R.raw.shoes4, categoriesDAO.getCategoryByName("Sneaker")),

                    new Products("Jordan 1 Retro High", 2500f, "Giày bóng rổ chuyên dụng, giúp tăng độ nảy và bảo vệ cổ chân.", "Nike", R.raw.shoes1, categoriesDAO.getCategoryByName("Slip-ons")),
                    new Products("Converse Chuck Taylor", 1300f, "Giày Slip-ons cổ điển, dễ phối đồ và thoải mái.", "Converse", R.raw.shoes2, categoriesDAO.getCategoryByName("Slip-ons")),
                    new Products("TOMS Classic", 1200f, "Giày slip-ons vải thoáng khí, phù hợp đi dạo.", "TOMS", R.raw.shoes3, categoriesDAO.getCategoryByName("Slip-ons")),

                    new Products("Puma Future Z", 1800f, "Giày đá bóng chuyên dụng, thiết kế đinh bám sân.", "Puma", R.raw.shoes4, categoriesDAO.getCategoryByName("Espadrilles")),
                    new Products("Nike Mercurial Vapor", 2600f, "Giày đá bóng tốc độ, hỗ trợ di chuyển nhanh.", "Nike", R.raw.shoes1, categoriesDAO.getCategoryByName("Espadrilles")),
                    new Products("Adidas Predator Edge", 2300f, "Giày đá bóng giúp kiểm soát bóng tốt hơn.", "Adidas", R.raw.shoes2, categoriesDAO.getCategoryByName("Espadrilles")),

                    new Products("NikeCourt Air Zoom Vapor", 2100f, "Giày tennis cao cấp, hỗ trợ di chuyển nhanh chóng.", "Nike", R.raw.shoes3, categoriesDAO.getCategoryByName("Oxford")),
                    new Products("Adidas Barricade", 2300f, "Giày chơi tennis với độ bám cao và hỗ trợ di chuyển ngang.", "Adidas", R.raw.shoes4, categoriesDAO.getCategoryByName("Oxford")),

                    new Products("FootJoy Pro SL", 3000f, "Giày golf cao cấp, chống nước và độ bám tuyệt vời.", "FootJoy", R.raw.shoes1, categoriesDAO.getCategoryByName("Derby")),
                    new Products("Nike Air Max 90 G", 2800f, "Giày golf thời trang, thiết kế thoải mái và bền bỉ.", "Nike", R.raw.shoes2, categoriesDAO.getCategoryByName("Derby")),

                    new Products("Gucci Horsebit Loafer", 5000f, "Giày lười cao cấp, sang trọng và lịch lãm.", "Gucci", R.raw.shoes3, categoriesDAO.getCategoryByName("Loafers")),
                    new Products("Timberland 6-inch Boots", 2800f, "Giày lười phong cách cổ điển, phù hợp mọi dịp.", "Timberland", R.raw.shoes4, categoriesDAO.getCategoryByName("Loafers")),
                    new Products("Cole Haan Grand Penny", 2000f, "Giày loafer thoải mái, thích hợp công sở.", "Cole Haan", R.raw.shoes1, categoriesDAO.getCategoryByName("Loafers")),

                    new Products("Soludos Original Dali", 1400f, "Giày Espadrilles vải đế cói nhẹ nhàng.", "Soludos", R.raw.shoes2, categoriesDAO.getCategoryByName("Espadrilles")),
                    new Products("Minnetonka Classic Moc", 1600f, "Giày moccasins da lộn mềm mại và thoải mái.", "Minnetonka", R.raw.shoes3, categoriesDAO.getCategoryByName("Espadrilles"))
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
