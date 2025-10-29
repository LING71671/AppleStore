package com.applestore.service;

import com.applestore.model.*;
import com.applestore.util.ColorPrinter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 产品管理器
 * 负责产品的CRUD操作和数据管理
 */
public class ProductManager {
    private List<Product> products;
    
    public ProductManager() {
        this.products = new ArrayList<>();
    }
    
    /**
     * 添加产品
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            ColorPrinter.printError("产品不能为空");
            return false;
        }
        
        // 检查ID是否已存在
        if (findById(product.getId()) != null) {
            ColorPrinter.printWarning("产品ID已存在: " + product.getId());
            return false;
        }
        
        products.add(product);
        ColorPrinter.printSuccess("产品添加成功: " + product.getName() + " - " + product.getModel());
        return true;
    }
    
    /**
     * 根据ID查找产品
     */
    public Product findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * 获取所有产品
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
    
    /**
     * 根据类型查找产品
     */
    public <T extends Product> List<T> getProductsByType(Class<T> type) {
        return products.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
    
    /**
     * 更新产品
     */
    public boolean updateProduct(String id, Product updatedProduct) {
        Product product = findById(id);
        if (product == null) {
            ColorPrinter.printError("未找到ID为 " + id + " 的产品");
            return false;
        }
        
        if (updatedProduct == null) {
            ColorPrinter.printError("更新数据不能为空");
            return false;
        }
        
        // 保留原有ID
        updatedProduct.setId(id);
        
        int index = products.indexOf(product);
        products.set(index, updatedProduct);
        
        ColorPrinter.printSuccess("产品更新成功: " + updatedProduct.getName() + " - " + updatedProduct.getModel());
        return true;
    }
    
    /**
     * 删除产品
     */
    public boolean deleteProduct(String id) {
        Product product = findById(id);
        if (product == null) {
            ColorPrinter.printError("未找到ID为 " + id + " 的产品");
            return false;
        }
        
        products.remove(product);
        ColorPrinter.printSuccess("产品删除成功: " + product.getName() + " - " + product.getModel());
        return true;
    }
    
    /**
     * 根据名称搜索产品
     */
    public List<Product> searchByName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }
        
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    /**
     * 根据型号搜索产品
     */
    public List<Product> searchByModel(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }
        
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getModel().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    /**
     * 搜索产品（综合搜索，包括名称、型号、颜色）
     */
    public List<Product> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllProducts();
        }
        
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword) ||
                           p.getModel().toLowerCase().contains(lowerKeyword) ||
                           p.getColor().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    /**
     * 按价格范围过滤产品
     */
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
    
    /**
     * 按颜色过滤产品
     */
    public List<Product> filterByColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            return getAllProducts();
        }
        
        String lowerColor = color.toLowerCase();
        return products.stream()
                .filter(p -> p.getColor().toLowerCase().contains(lowerColor))
                .collect(Collectors.toList());
    }
    
    /**
     * 统计产品数量
     */
    public int getProductCount() {
        return products.size();
    }
    
    /**
     * 获取产品库存总量
     */
    public int getTotalStock() {
        return products.stream()
                .mapToInt(Product::getStock)
                .sum();
    }
    
    /**
     * 获取平均价格
     */
    public double getAveragePrice() {
        if (products.isEmpty()) {
            return 0;
        }
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }
    
    /**
     * 按价格排序
     */
    public List<Product> sortByPrice(boolean ascending) {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort((p1, p2) -> {
            if (ascending) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            } else {
                return Double.compare(p2.getPrice(), p1.getPrice());
            }
        });
        return sorted;
    }
    
    /**
     * 按名称排序
     */
    public List<Product> sortByName() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparing(Product::getName));
        return sorted;
    }
    
    /**
     * 清空所有产品
     */
    public void clearAll() {
        products.clear();
        ColorPrinter.printWarning("所有产品已清空");
    }
    
    /**
     * 设置产品列表（用于从文件加载）
     */
    public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
}

