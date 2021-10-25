package com.example.mobilewallet;

import com.google.gson.annotations.SerializedName;

public class Statement {

    @SerializedName("TransactionID")
    private String superName;


    public void TransactionID(String Transaction) {
        this.superName = Transaction;
    }

    public String TransactionID() {
        return superName;
    }
}
