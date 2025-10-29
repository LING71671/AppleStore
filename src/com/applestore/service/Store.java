package com.applestore.service;

import com.applestore.model.*;
import com.applestore.util.ColorPrinter;

/**
 * 商店类
 * 管理产品库存和初始化示例数据
 */
public class Store {
    private ProductManager productManager;
    
    public Store(ProductManager productManager) {
        this.productManager = productManager;
    }
    
    /**
     * 初始化商店示例数据
     */
    public void initializeSampleData() {
        ColorPrinter.printInfo("正在初始化商店数据...");
        
        // Apple Vision Pro
        productManager.addProduct(new AppleVisionPro("256GB", 25999.00, 15, "深空灰", 256));
        productManager.addProduct(new AppleVisionPro("512GB", 27999.00, 8, "深空灰", 512));
        
        // MacBook
        productManager.addProduct(new MacBook("MacBook Air M3 13英寸", 8999.00, 20, 
                "午夜色", 256, "13.6英寸", "M3"));
        productManager.addProduct(new MacBook("MacBook Air M3 15英寸", 10499.00, 12, 
                "银色", 512, "15.3英寸", "M3"));
        productManager.addProduct(new MacBook("MacBook Pro M3 14英寸", 15999.00, 18, 
                "深空灰色", 512, "14.2英寸", "M3"));
        productManager.addProduct(new MacBook("MacBook Pro M3 Max 16英寸", 24999.00, 10, 
                "深空黑色", 1024, "16.2英寸", "M3 Max"));
        
        // iPad
        productManager.addProduct(new iPad("iPad (第10代) 10.9英寸", 3599.00, 25, 
                "蓝色", 64, "10.9英寸", false));
        productManager.addProduct(new iPad("iPad Air (第5代) 11英寸", 4399.00, 22, 
                "紫色", 256, "10.9英寸", false));
        productManager.addProduct(new iPad("iPad Pro 12.9英寸", 9299.00, 15, 
                "深空灰", 512, "12.9英寸", true));
        
        // iPhone
        productManager.addProduct(new iPhone("iPhone 15 128GB", 5999.00, 30, 
                "粉色", 128, "6.1英寸", "双摄像头"));
        productManager.addProduct(new iPhone("iPhone 15 Plus 256GB", 7999.00, 20, 
                "蓝色", 256, "6.7英寸", "双摄像头"));
        productManager.addProduct(new iPhone("iPhone 15 Pro 256GB", 8999.00, 25, 
                "原色钛金属", 256, "6.1英寸", "Pro三摄"));
        productManager.addProduct(new iPhone("iPhone 15 Pro Max 512GB", 11799.00, 18, 
                "蓝色钛金属", 512, "6.7英寸", "Pro三摄"));
        
        // Apple Watch
        productManager.addProduct(new AppleWatch("Apple Watch SE (第2代)", 1999.00, 35, 
                "午夜色", 32, "44mm", "铝合金", false));
        productManager.addProduct(new AppleWatch("Apple Watch Series 9", 2999.00, 28, 
                "粉砂色", 64, "45mm", "铝金属", true));
        productManager.addProduct(new AppleWatch("Apple Watch Ultra 2", 6499.00, 12, 
                "钛金属原色", 64, "49mm", "钛金属", true));
        
        // AirPods
        productManager.addProduct(new AirPods("AirPods (第3代)", 1399.00, 50, 
                "白色", 256, "自适应均衡", 30));
        productManager.addProduct(new AirPods("AirPods Pro (第2代)", 1899.00, 40, 
                "白色", 256, "主动降噪", 30));
        productManager.addProduct(new AirPods("AirPods Max", 4399.00, 20, 
                "银色", 512, "主动降噪", 20));
        
        ColorPrinter.printSuccess("商店数据初始化完成，共加载 " + productManager.getProductCount() + " 个产品");
    }
    
    /**
     * 获取产品统计信息
     */
    public void printStatistics() {
        ColorPrinter.printTitleBox("商店统计信息");
        
        ColorPrinter.printInfo("产品总数: " + productManager.getProductCount());
        ColorPrinter.printInfo("总库存: " + productManager.getTotalStock());
        ColorPrinter.printInfo("平均价格: ¥" + String.format("%.2f", productManager.getAveragePrice()));
        
        // 按类型统计
        ColorPrinter.println("\n按类型统计:", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printInfo("Apple Vision Pro: " + productManager.getProductsByType(AppleVisionPro.class).size());
        ColorPrinter.printInfo("MacBook: " + productManager.getProductsByType(MacBook.class).size());
        ColorPrinter.printInfo("iPad: " + productManager.getProductsByType(iPad.class).size());
        ColorPrinter.printInfo("iPhone: " + productManager.getProductsByType(iPhone.class).size());
        ColorPrinter.printInfo("Apple Watch: " + productManager.getProductsByType(AppleWatch.class).size());
        ColorPrinter.printInfo("AirPods: " + productManager.getProductsByType(AirPods.class).size());
        
        System.out.println();
    }
}

