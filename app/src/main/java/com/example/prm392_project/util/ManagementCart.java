package com.example.prm392_project.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.prm392_project.data.model.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManagementCart {
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ManagementCart(Context context) {
        sharedPreferences = context.getSharedPreferences("CartPreferences", Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void insertItem(CartItem item, Context context) {
        ArrayList<CartItem> list = getListCart();
        boolean existAlready = false;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getProductName().equals(item.getProductName()) && list.get(i).getSize().equals(item.getSize())) {
                existAlready = true;
                index = i;
                break;
            }
        }

        if (existAlready) {
            list.get(index).setQuantity(item.getQuantity());
        } else {
            list.add(item);
        }
        saveListCart(list);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<CartItem> getListCart() {
        String json = sharedPreferences.getString("CartList", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    public void minusItem(ArrayList<CartItem> list, int position, ChangeNumberItemsListener listener) {
        if (list.get(position).getQuantity() == 1) {
            list.remove(position);
        } else {
            list.get(position).setQuantity(list.get(position).getQuantity() - 1);
        }
        saveListCart(list);
        listener.onChanged();
    }

    public void plusItem(ArrayList<CartItem> list, int position, ChangeNumberItemsListener listener) {
        list.get(position).setQuantity(list.get(position).getQuantity() + 1);
        saveListCart(list);
        listener.onChanged();
    }

    public double getTotalFee() {
        ArrayList<CartItem> list = getListCart();
        double fee = 0.0;
        for (CartItem item : list) {
            fee += item.getPrice() * item.getQuantity();
        }
        return fee;
    }

    private void saveListCart(ArrayList<CartItem> list) {
        String json = gson.toJson(list);
        sharedPreferences.edit().putString("CartList", json).apply();
    }
}
