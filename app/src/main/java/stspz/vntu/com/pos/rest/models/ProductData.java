package stspz.vntu.com.pos.rest.models;

import com.google.gson.annotations.Expose;

/**
 * Created by Alexander on 11.03.2016.
 */

public class ProductData {

    @Expose
    private int id;
    @Expose
    private String categoryId;
    @Expose
    private String created;
    @Expose
    private String expires;
    @Expose
    private String name;
    @Expose
    private int count;
    @Expose
    private int code;
    @Expose
    private int status;
    @Expose
    private float purchasedAmount;
    @Expose
    private float saleAmount;
    @Expose
    private float salePercent;
    @Expose
    private String categoryName;
    @Expose
    private boolean forSale;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(float purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public float getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
    }

    public float getSalePercent() {
        return salePercent;
    }

    public void setSalePercent(float salePercent) {
        this.salePercent = salePercent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
}
