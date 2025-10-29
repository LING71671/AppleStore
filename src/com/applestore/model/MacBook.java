package com.applestore.model;

/**
 * MacBook产品类
 */
public class MacBook extends Product {
    private String screenSize;
    private String chip;
    
    public MacBook(String model, double price, int stock, String color, int storage, String screenSize, String chip) {
        super("MacBook", model, price, stock, color, storage);
        this.screenSize = screenSize;
        this.chip = chip;
    }
    
    public String getScreenSize() {
        return screenSize;
    }
    
    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
    
    public String getChip() {
        return chip;
    }
    
    public void setChip(String chip) {
        this.chip = chip;
    }
    
    @Override
    public String getDetails() {
        return String.format("MacBook %s，配备 %s 英寸显示屏，搭载 Apple %s 芯片，" +
               "性能强劲，续航持久，是专业人士的理想选择。",
               getModel(), screenSize, chip);
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | 屏幕: %s | 芯片: %s", screenSize, chip);
    }
}

