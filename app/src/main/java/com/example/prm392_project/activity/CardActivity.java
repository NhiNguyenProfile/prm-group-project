package com.example.prm392_project.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.prm392_project.data.adapter.CartAdapter;
import com.example.prm392_project.data.model.CartItem;
import com.example.prm392_project.data.model.OrderItems;
import com.example.prm392_project.data.model.Orders;
import com.example.prm392_project.data.repositories.callback.OrderCallBack;
import com.example.prm392_project.data.repositories.callback.ProductVariantCallBack;
import com.example.prm392_project.data.view_model.OrderItemsViewModel;
import com.example.prm392_project.data.view_model.OrdersViewModel;
import com.example.prm392_project.data.view_model.ProductVariantViewModel;
import com.example.prm392_project.data.view_model.ProductViewModel;
import com.example.prm392_project.databinding.ActivityCardBinding;
import com.example.prm392_project.databinding.CustomShowMessageDialogBinding;
import com.example.prm392_project.payment.Api.ZaloPayment;
import com.example.prm392_project.util.ChangeNumberItemsListener;
import com.example.prm392_project.util.ManagementCart;
import com.example.prm392_project.util.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class CardActivity extends AppCompatActivity {
    private ActivityCardBinding binding;
    private ManagementCart managementCart;
    private ProductViewModel productViewModel;
    private ProductVariantViewModel productVariantViewModel;
    private OrdersViewModel ordersViewModel;
    private OrderItemsViewModel orderItemsViewModel;
    private double tax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {
        managementCart = new ManagementCart(this);
        productVariantViewModel = new ProductVariantViewModel(getApplication());
        ordersViewModel = new OrdersViewModel(getApplication());
        orderItemsViewModel = new OrderItemsViewModel(getApplication());
        productViewModel = new ProductViewModel(getApplication());
        handleAllFunction();
    }

    private void handleAllFunction() {
        initCartList();
        calculateCart();
        goBack();
        setUpZaloPay();
        onPayment();
    }

    private void setUpZaloPay() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ZaloPaySDK.init(2553, Environment.SANDBOX);
    }

    private JSONObject getJsonData(String totalPrice) {
        ZaloPayment zaloApi = new ZaloPayment();
        try {
            return zaloApi.createOrder(totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getToken(JSONObject data) {
        try {
            return data.getString("zp_trans_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String returnCode(JSONObject data) {
        try {
            return data.getString("return_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String returnMessage(JSONObject data) {
        try {
            return data.getString("return_message");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void openDialog() {
        CustomShowMessageDialogBinding customShowMessageDialogBinding = CustomShowMessageDialogBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customShowMessageDialogBinding.getRoot()).setCancelable(false);
        AlertDialog dialog = builder.create();
        customShowMessageDialogBinding.dialogConfirmBTN.setText("Login");
        customShowMessageDialogBinding.dialogMessage.setText("Please login before buy product");
        customShowMessageDialogBinding.dialogCancelBTN.setOnClickListener(v1 -> dialog.dismiss());
        customShowMessageDialogBinding.dialogConfirmBTN.setOnClickListener(v2 -> {
            startActivity(new Intent(CardActivity.this, LoginActivity.class));
            dialog.dismiss();
        });
        dialog.show();
    }

    private void onPayment() {
        binding.checkoutBTN.setOnClickListener(v -> {
            if (!SessionManager.getInstance().isLoggedIn()) {
                openDialog();
                return;
            }

            String totalPrice = binding.totalTxt.getText().toString().replace("$", "");
            @SuppressLint("DefaultLocale") JSONObject data = getJsonData(String.format("%.0f", Double.parseDouble(totalPrice)));
            if (data == null) {
                Toast.makeText(this, "Data null", Toast.LENGTH_SHORT).show();
                return;
            }
            String returnCode = returnCode(data);
            String returnMessage = returnMessage(data);
            if (returnCode == null) {
                Toast.makeText(this, returnMessage, Toast.LENGTH_SHORT).show();
                return;
            }
            if (returnCode.equals("1")) {
                String token = getToken(data);
                ZaloPaySDK.getInstance().payOrder(CardActivity.this, token, "demozpdk://app", new PayOrderListener() {
                    @Override
                    public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                        paymentSuccess();
                        managementCart.clearCart();
                        initCartList();
                        startActivity(new Intent(CardActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onPaymentCanceled(String zpTransToken, String appTransID) {
                    }

                    @Override
                    public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                    }
                });
            }
        });
    }

    private void paymentSuccess() {
        List<CartItem> cartItems = managementCart.getListCart();
        // Insert Order
        float totalPrice = Float.parseFloat(binding.totalTxt.getText().toString().replace("$", ""));
        String userId = SessionManager.getInstance().getUserId();
        String status = "Pending";

        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setTotalAmount(totalPrice);
        orders.setStatus(status);

        ordersViewModel.insertOrder(orders, new OrderCallBack() {
            @Override
            public void onInsertAsync(String orderId) {
                super.onInsertAsync(orderId);

                // Insert OrderItems
                for (CartItem item : cartItems) {
                    productVariantViewModel.getProductVariantBySizeAndProductIdAsync(item.getProductId(), item.getSize(), new ProductVariantCallBack() {
                        @Override
                        public void onGetProductVariantsById(String variantId) {
                            super.onGetProductVariantsById(variantId);
                            OrderItems orderItems = new OrderItems();
                            orderItems.setOrderId(orderId);
                            orderItems.setVariantId(variantId);
                            orderItems.setQuantity(item.getQuantity());
                            orderItems.setPrice(item.getPrice());
                            orderItemsViewModel.insertOrderItems(orderItems);
                        }
                    });
                }

            }
        });

    }

    private void initCartList() {
        binding.viewCart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.viewCart.setAdapter(new CartAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void onChanged() {
                calculateCart();
            }
        }));

        if (managementCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollView2.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollView2.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10.0;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }

    private void goBack() {
        binding.topAppBar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}