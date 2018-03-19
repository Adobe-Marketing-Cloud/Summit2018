package com.adobe.summit.l731.product;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ProductCategory {

    private String title;
    private String image;
    private String path;
    private String productPath;
    private boolean isProduct;
    private List<ProductCategory> categories;

    public ProductCategory(String title, String image, String path, Boolean isProduct, List<ProductCategory> children){
        setTitle(title);
        setImage(image);
        setPath(path);
        setProduct(isProduct);
        setCategories(children);
    }

    public ProductCategory(String title, String image, String path, Boolean isProduct, String productPath, List<ProductCategory> children){
        setTitle(title);
        setImage(image);
        setPath(path);
        setProduct(isProduct);
        setProductPath(productPath);
        setCategories(children);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isProduct() {
        return isProduct;
    }

    public void setProduct(boolean product) {
        isProduct = product;
    }

    public String getProductPath() {
        return productPath;
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }

    public List<ProductCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ProductCategory> categories) {
        this.categories = categories;
    }

}