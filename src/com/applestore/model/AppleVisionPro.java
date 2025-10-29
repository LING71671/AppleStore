package com.applestore.model;

/**
 * Apple Vision Pro产品类
 */
public class AppleVisionPro extends Product {
    
    public AppleVisionPro(String model, double price, int stock, String color, int storage) {
        super("Apple Vision Pro", model, price, stock, color, storage);
    }
    
    @Override
    public String getDetails() {
        return "Apple Vision Pro 是一款革命性的空间计算设备，配备超高清显示屏，" +
               "支持手势和眼球追踪，为您带来沉浸式的虚拟体验。";
    }
}

