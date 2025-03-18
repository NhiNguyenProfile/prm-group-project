package com.example.prm392_project.data.model;

import com.example.prm392_project.payment.Constant.AppInfo;
import com.example.prm392_project.payment.Helper.Helpers;

import java.util.Date;

public class CreateOrderData {
    public String AppId;
    public String AppUser;
    public String AppTime;
    public String Amount;
    public String AppTransId;
    public String EmbedData;
    public String Items;
    public String BankCode;
    public String Description;
    public String Mac;

    public CreateOrderData(String amount) throws Exception {
        long appTime = new Date().getTime();
        AppId = String.valueOf(AppInfo.APP_ID);
        AppUser = "Android_Demo";
        AppTime = String.valueOf(appTime);
        Amount = amount;
        AppTransId = Helpers.getAppTransId();
        EmbedData = "{}";
        Items = "[]";
        BankCode = "zalopayapp";
        Description = "Merchant pay for order #" + Helpers.getAppTransId();
        String inputHMac = String.format("%s|%s|%s|%s|%s|%s|%s",
                this.AppId,
                this.AppTransId,
                this.AppUser,
                this.Amount,
                this.AppTime,
                this.EmbedData,
                this.Items);

        Mac = Helpers.getMac(AppInfo.MAC_KEY, inputHMac);
    }
}