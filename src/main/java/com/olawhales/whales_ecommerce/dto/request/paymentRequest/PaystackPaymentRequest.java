package com.olawhales.whales_ecommerce.dto.request.paymentRequest;

import lombok.Data;

@Data
public class PaystackPaymentRequest {
    private String email;
    private int amount;
    private String currency = "NGN";
    private String callback_url;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }
}
