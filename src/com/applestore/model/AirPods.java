package com.applestore.model;

/**
 * AirPods产品类 | AirPods Product Class
 */
public class AirPods extends Product {
    private String noiseCancellation; // 降噪类型 | Noise cancellation type
    private int batteryLife; // 续航时间（小时） | Battery life (hours)

    public AirPods(String model, double price, int stock, String color, int storage,
                   String noiseCancellation, int batteryLife) {
        super("AirPods", model, price, stock, color, storage);
        this.noiseCancellation = noiseCancellation;
        this.batteryLife = batteryLife;
    }

    public String getNoiseCancellation() {
        return noiseCancellation;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    @Override
    public String getDetails() {
        return String.format("AirPods %s，%s 技术，" +
               "续航时间最长可达 %d 小时，音质出色，使用便捷。 | AirPods %s with %s technology, " +
               "up to %d hours of battery life, excellent sound quality and easy to use.",
               getModel(), noiseCancellation, batteryLife, getModel(), noiseCancellation, batteryLife);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | 降噪: %s | 续航: %d小时 | Noise Cancellation: %s | Battery Life: %d hours",
               noiseCancellation, batteryLife, noiseCancellation, batteryLife);
    }
}
