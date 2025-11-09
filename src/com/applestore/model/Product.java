package com.applestore.model;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom; // used in old vision

/**
 * 产品抽象基类 | Abstract Product Base Class
 * 定义所有苹果产品的通用属性 | Defines common attributes for all Apple products
 */
public abstract class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String model;
    private double price;
    private int stock; // 库存数量 | Stock quantity
    private String color;
    private int storage; // 存储容量（GB） | Storage capacity (GB)

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
     * 生成唯一ID | Generate unique ID
     */
    private String generateId() {

        return UUID.randomUUID().toString().substring(0, 8);
    }
    //This is the third time I tried to fix the bug that all the products have the same id.(At least it's output like this)
    //Oh!I forget to delete the previous ID.


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
    }//Maybe at most time we don't need to change the name.

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
            throw new IllegalArgumentException("价格不能为负数 | Price cannot be negative");
        }
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("库存不能为负数 | Stock cannot be negative");
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
     * 获取产品详细信息 | Get product details
     */
    public abstract String getDetails();

    @Override
    public String toString() {
        return String.format("%s - %s | 颜色: %s | 存储: %dGB   | 价格: ¥%.2f | 库存: %d | Color: %s | Storage: %dGB | Price: ¥%.2f | Stock: %d",
                name, model, color, storage, price, stock, color, storage, price, stock);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;//类型声明 变量名 = 强制类型转换 转换Object类型的obj对象
                                       //Maybe change the second "product" into "otherProduct" is better?
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }//I have searched that the hashCode() method is used to calculate the hash value of an object.
    // It is used to improve the performance of hash-based data structures such as HashMap and HashSet.
   // And form my point of view, hashcode is an identifier used for quick lookups.

}
