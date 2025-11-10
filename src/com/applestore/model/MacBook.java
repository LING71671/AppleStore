package com.applestore.model;

/**
 * MacBook产品类 | MacBook Product Class
 */
public class MacBook extends Product {
    private String screenSize; // 屏幕尺寸 | Screen size
    private String chip; // 芯片 | Chip

    public MacBook(String model, double price, int stock, String color, int storage, String screenSize, String chip) {
        super("MacBook", model, price, stock, color, storage);
        this.screenSize = screenSize;
        this.chip = chip;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public String getChip() {
        return chip;
    }

    @Override
    public String getDetails() {
        return String.format("MacBook %s，配备 %s 英寸显示屏，搭载 Apple %s 芯片，" +
               "性能强劲，续航持久，是专业人士的理想选择。 | MacBook %s with %s inch display, powered by Apple %s chip, " +
               "powerful performance, long battery life, ideal choice for professionals.",
               getModel(), screenSize, chip, getModel(), screenSize, chip);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | 屏幕: %s | 芯片: %s | Screen: %s | Chip: %s", screenSize, chip, screenSize, chip);
    }
}
