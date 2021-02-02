package com.example.demo.myUtile;

public class PurchaseRequest
{
    private String customer_full_name;
    private Double purchase_price;
    private int version ;

    public PurchaseRequest()
    {

    }
    public PurchaseRequest(String customer_full_name , Double purchase_price , int version)
    {
        this.customer_full_name = customer_full_name;
        this.purchase_price = purchase_price;
        this.version = version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setPurchase_price(Double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Double getPurchase_price() {
        return purchase_price;
    }

    public void setCustomer_full_name(String customer_full_name) {
        this.customer_full_name = customer_full_name;
    }

    public String getCustomer_full_name() {
        return customer_full_name;
    }
}
