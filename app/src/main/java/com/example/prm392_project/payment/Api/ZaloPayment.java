package com.example.prm392_project.payment.Api;

import com.example.prm392_project.data.model.CreateOrderData;
import com.example.prm392_project.payment.Constant.AppInfo;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ZaloPayment {
    public JSONObject createOrder(String amount) throws Exception {
        CreateOrderData input = new CreateOrderData(amount);

        RequestBody formBody = new FormBody.Builder()
                .add("app_id", input.AppId)
                .add("app_user", input.AppUser)
                .add("app_time", input.AppTime)
                .add("amount", input.Amount)
                .add("app_trans_id", input.AppTransId)
                .add("embed_data", input.EmbedData)
                .add("item", input.Items)
                .add("bank_code", input.BankCode)
                .add("description", input.Description)
                .add("mac", input.Mac)
                .build();

        JSONObject data = HttpProvider.sendPost(AppInfo.URL_CREATE_ORDER, formBody);
        return data;
    }
}
