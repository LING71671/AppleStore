package com.applestore.model;

/**
 * iPhone产品类
 */
public class iPhone extends Product {
    private String screenSize;
    private String camera; // 摄像头系统
    
    public iPhone(String model, double price, int stock, String color, int storage, String screenSize, String camera) {
        super("iPhone", model, price, stock, color, storage);
        this.screenSize = screenSize;
        this.camera = camera;
    }
    
    public String getScreenSize() {
        return screenSize;
    }
    
    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
    
    public String getCamera() {
        return camera;
    }
    
    public void setCamera(String camera) {
        this.camera = camera;
    }
    
    @Override
    public String getDetails() {
        return String.format("iPhone %s，配备 %s 英寸超级视网膜XDR显示屏，" +
               "%s 摄像头系统，性能卓越，拍照出色。",
               getModel(), screenSize, camera);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | 屏幕: %s | 摄像头: %s", screenSize, camera);
    }
}

