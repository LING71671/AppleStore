package com.applestore.service;

import com.applestore.model.*;
import com.applestore.util.ColorPrinter;

import java.util.*;
import java.util.stream.Collectors;
//We search and know that CRUD is crucial for constant manage.

/**
 * 产品管理器 | Product Manager
 * 负责产品的CRUD操作和数据管理 | Responsible for product CRUD operations and data management
 */
public class ProductManager {
    private List<Product> products;

    public ProductManager() {
        this.products = new ArrayList<>();
    }

    /**
     * 添加产品 | Add product
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            ColorPrinter.printError("产品不能为空 | Product cannot be null");
            return false;
        }

        // 检查ID是否已存在 | Check if ID already exists
        if (findById(product.getId()) != null) {
            ColorPrinter.printWarning("产品ID已存在: " + product.getId() + " | Product ID already exists: " + product.getId());
            return false;
        }

        products.add(product);
        ColorPrinter.printSuccess("产品添加成功: " + product.getName() + " - " + product.getModel() + " | Product added successfully: " + product.getName() + " - " + product.getModel());
        return true;
    }

    /**
     * 根据ID查找产品 | Find product by ID
     */
    public Product findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * 获取所有产品 | Get all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * 根据类型查找产品 | Find products by type
     */
    public <T extends Product> List<T> getProductsByType(Class<T> type) {
        return products.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    /**
     * 更新产品 | Update product
     */
    public boolean updateProduct(String id, Product updatedProduct) {
        Product product = findById(id);
        if (product == null) {
            ColorPrinter.printError("未找到ID为 " + id + " 的产品 | Product with ID " + id + " not found");
            return false;
        }

        if (updatedProduct == null) {
            ColorPrinter.printError("更新数据不能为空 | Update data cannot be null");
            return false;
        }

        // 保留原有ID | Preserve original ID
        updatedProduct.setId(id);

        int index = products.indexOf(product);
        products.set(index, updatedProduct);

        ColorPrinter.printSuccess("产品更新成功: " + updatedProduct.getName() + " - " + updatedProduct.getModel() + " | Product updated successfully: " + updatedProduct.getName() + " - " + updatedProduct.getModel());
        return true;
    }

    /**
     * 删除产品 | Delete product
     */
    public boolean deleteProduct(String id) {
        Product product = findById(id);
        if (product == null) {
            ColorPrinter.printError("未找到ID为 " + id + " 的产品 | Product with ID " + id + " not found");
            return false;
        }

        products.remove(product);
        ColorPrinter.printSuccess("产品删除成功: " + product.getName() + " - " + product.getModel() + " | Product deleted successfully: " + product.getName() + " - " + product.getModel());
        return true;
    }

    /**
     * 根据名称搜索产品 | Search products by name
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
     * 根据型号搜索产品 | Search products by model
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
     * 搜索产品（综合搜索，包括名称、型号、颜色） | Search products (comprehensive search including name, model, color)
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
     * 按价格范围过滤产品 | Filter products by price range
     */
    public List<Product> filterByPrice(double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    /**
     * 按颜色过滤产品 | Filter products by color
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
     * 统计产品数量 | Count products
     */
    public int getProductCount() {
        return products.size();
    }

    /**
     * 获取产品库存总量 | Get total product stock
     */
    public int getTotalStock() {
        return products.stream()
                .mapToInt(Product::getStock)
                .sum();
    }

    /**
     * 获取平均价格 | Get average price
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
     * 按价格排序 | Sort by price
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
     * 按名称排序 | Sort by name
     */
    public List<Product> sortByName() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparing(Product::getName));
        return sorted;
    }

    /**
     * 清空所有产品 | Clear all products
     */
    public void clearAll() {
        products.clear();
        ColorPrinter.printWarning("所有产品已清空 | All products cleared");
    }

    /**
     * 设置产品列表（用于从文件加载） | Set product list (for loading from file)
     */
    public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }
}
