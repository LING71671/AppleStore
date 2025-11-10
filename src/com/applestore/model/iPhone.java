package com.applestore.model;

/**
 * iPhone产品类 | iPhone Product Class
 */
public class iPhone extends Product {
    private String screenSize; // 屏幕尺寸 | Screen size
    private String camera; // 摄像头系统 | Camera system

    public iPhone(String model, double price, int stock, String color, int storage, String screenSize, String camera) {
        super("iPhone", model, price, stock, color, storage);
        this.screenSize = screenSize;
        this.camera = camera;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getCamera() {
        return camera;
    }

    @Override
    public String getDetails() {
        return String.format("iPhone %s，配备 %s 英寸超级视网膜XDR显示屏，" +
               "%s 摄像头系统，性能卓越，拍照出色。 | iPhone %s with %s inch Super Retina XDR display, " +
               "%s camera system, excellent performance and outstanding photography.",
               getModel(), screenSize, camera, getModel(), screenSize, camera);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | 屏幕: %s | 摄像头: %s | Screen: %s | Camera: %s", screenSize, camera, screenSize, camera);
    }
}
