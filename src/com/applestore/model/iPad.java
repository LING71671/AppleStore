package com.applestore.model;

/**
 * iPad产品类 | iPad Product Class
 */
public class iPad extends Product {
    private String screenSize; // 屏幕尺寸 | Screen size
    private boolean cellular; // 是否支持蜂窝网络 | Whether cellular network is supported

    public iPad(String model, double price, int stock, String color, int storage, String screenSize, boolean cellular) {
        super("iPad", model, price, stock, color, storage);
        this.screenSize = screenSize;
        this.cellular = cellular;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public boolean isCellular() {
        return cellular;
    }

    @Override
    public String getDetails() {
        return String.format("iPad %s，配备 %s 英寸显示屏，存储容量 %dGB，" +
               "%s蜂窝网络支持，轻便便携，性能出色。 | iPad %s with %s inch display, %dGB storage, " +
               "%s cellular network support, lightweight and portable, excellent performance.",
               getModel(), screenSize, getStorage(), cellular ? "支持" : "不支持",
               getModel(), screenSize, getStorage(), cellular ? "supports" : "does not support");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | 屏幕: %s | %s | Screen: %s | %s",
               screenSize, cellular ? "蜂窝网络" : "Wi-Fi",
               screenSize, cellular ? "Cellular" : "Wi-Fi");
    }
}
