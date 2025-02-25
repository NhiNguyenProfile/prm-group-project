package com.example.prm392_project.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(), DatabaseHelper.class, "prm392_project")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
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
                    UserDAO userDAO = database.userDao();
                    if (userDAO.getAllUserAsync().isEmpty()) {
                        Users defaultUser = new Users("Alex", "alex@gmail.com", "12345", "09081827374", "123 Le loi", "USER");
                        userDAO.insertUser(defaultUser);
                    }
                }
            });
        }
    };

}
