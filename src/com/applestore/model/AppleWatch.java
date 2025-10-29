package com.applestore.model;

/**
 * Apple Watch产品类
 */
public class AppleWatch extends Product {
    private String caseSize;
    private String caseMaterial;
    private boolean cellular; // 是否支持蜂窝网络
    
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
    
    public void setCaseSize(String caseSize) {
        this.caseSize = caseSize;
    }
    
    public String getCaseMaterial() {
        return caseMaterial;
    }
    
    public void setCaseMaterial(String caseMaterial) {
        this.caseMaterial = caseMaterial;
    }
    
    public boolean isCellular() {
        return cellular;
    }
    
    public void setCellular(boolean cellular) {
        this.cellular = cellular;
    }
    
    @Override
    public String getDetails() {
        return String.format("Apple Watch %s，%s mm 表壳，采用 %s 材质，" +
               "%s蜂窝网络，全天候健康监测，功能强大。",
               getModel(), caseSize, caseMaterial, cellular ? "支持" : "不支持");
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format(" | 表壳: %s %s | %s", 
               caseSize, caseMaterial, cellular ? "蜂窝网络" : "GPS");
    }
}

