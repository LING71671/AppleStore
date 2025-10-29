package com.applestore.model;

import java.io.Serializable;

/**
 * 产品抽象基类
 * 定义所有苹果产品的通用属性
 */
public abstract class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private String model;
    private double price;
    private int stock; // 库存数量
    private String color;
    private int storage; // 存储容量（GB）
    
    public Product(String name, String model, double price, int stock, String color, int storage) {
        this.id = generateId();
        this.name = name;
        this.model = model;
        this.price = price;
        this.stock = stock;
        this.color = color;
        this.storage = storage;
    }
    
    /**
     * 生成唯一ID
     */
    private String generateId() {
        return System.currentTimeMillis() + "-" + (int)(Math.random() * 1000);
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("价格不能为负数");
        }
        this.price = price;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("库存不能为负数");
        }
        this.stock = stock;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public int getStorage() {
        return storage;
    }
    
    public void setStorage(int storage) {
        this.storage = storage;
    }
    
    /**
     * 获取产品详细信息
     */
    public abstract String getDetails();
    
    @Override
    public String toString() {
        return String.format("%s - %s | 颜色: %s | 存储: %dGB | 价格: ¥%.2f | 库存: %d",
                name, model, color, storage, price, stock);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id.equals(product.id);
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

