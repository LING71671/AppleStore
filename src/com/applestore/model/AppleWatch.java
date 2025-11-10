package com.applestore.model;

/**
 * Apple Watch产品类 | Apple Watch Product Class
 */
public class AppleWatch extends Product {
    private String caseSize; // 表壳尺寸 | Case size
    private String caseMaterial; // 表壳材质 | Case material
    private boolean cellular; // 是否支持蜂窝网络 | Whether cellular network is supported

    public AppleWatch(String model, double price, int stock, String color, int storage,
                     String caseSize, String caseMaterial, boolean cellular) {
        super("Apple Watch", model, price, stock, color, storage);
        this.caseSize = caseSize;
        this.caseMaterial = caseMaterial;
        this.cellular = cellular;
    }

    public String getCaseSize() {
        return caseSize;
    }

    public String getCaseMaterial() {
        return caseMaterial;
    }

    public boolean isCellular() {
        return cellular;
    }

    @Override
    public String getDetails() {
        return String.format("Apple Watch %s，%s mm 表壳，采用 %s 材质，" +
               "%s蜂窝网络，全天候健康监测，功能强大。 | Apple Watch %s with %s mm case, made of %s material, " +
               "%s cellular network, all-day health monitoring, powerful functions.",
               getModel(), caseSize, caseMaterial, cellular ? "支持" : "不支持",
               getModel(), caseSize, caseMaterial, cellular ? "supports" : "does not support");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | 表壳: %s %s | %s | Case: %s %s | %s",
               caseSize, caseMaterial, cellular ? "蜂窝网络" : "GPS",
               caseSize, caseMaterial, cellular ? "Cellular" : "GPS");
    }
}
