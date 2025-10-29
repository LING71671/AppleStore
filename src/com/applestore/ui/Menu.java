package com.applestore.ui;

import com.applestore.model.*;
import com.applestore.service.ProductManager;
import com.applestore.util.ColorPrinter;

import java.util.Scanner;

/**
 * 菜单类
 * 处理用户交互和选择菜单
 */
public class Menu {
    private Scanner scanner;
    
    public Menu() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 显示主菜单
     */
    public void showMainMenu() {
        ColorPrinter.printLine(60);
        ColorPrinter.println("                  📋 主菜单", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printLine(60);
        ColorPrinter.println("  1️⃣  查看所有产品", ColorPrinter.GREEN);
        ColorPrinter.println("  2️⃣  添加新产品", ColorPrinter.GREEN);
        ColorPrinter.println("  3️⃣  更新产品信息", ColorPrinter.GREEN);
        ColorPrinter.println("  4️⃣  删除产品", ColorPrinter.RED);
        ColorPrinter.println("  5️⃣  搜索产品", ColorPrinter.YELLOW);
        ColorPrinter.println("  6️⃣  筛选产品", ColorPrinter.YELLOW);
        ColorPrinter.println("  7️⃣  排序产品", ColorPrinter.YELLOW);
        ColorPrinter.println("  8️⃣  查看统计信息", ColorPrinter.CYAN);
        ColorPrinter.println("  9️⃣  保存/加载数据", ColorPrinter.BLUE);
        ColorPrinter.println("  0️⃣  退出程序", ColorPrinter.MAGENTA);
        ColorPrinter.printLine(60);
    }
    
    /**
     * 获取用户选择
     */
    public int getUserChoice() {
        ColorPrinter.print("请输入选项 (0-9): ", ColorPrinter.BOLD_YELLOW);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * 选择产品类型
     */
    public int chooseProductType() {
        ColorPrinter.printLine(50);
        ColorPrinter.println("  请选择产品类型:", ColorPrinter.BOLD_CYAN);
        ColorPrinter.println("  1. Apple Vision Pro 🥽", ColorPrinter.RED);
        ColorPrinter.println("  2. MacBook 💻", ColorPrinter.BLUE);
        ColorPrinter.println("  3. iPad 📱", ColorPrinter.YELLOW);
        ColorPrinter.println("  4. iPhone 📞", ColorPrinter.GREEN);
        ColorPrinter.println("  5. Apple Watch ⌚", ColorPrinter.MAGENTA);
        ColorPrinter.println("  6. AirPods 🎧", ColorPrinter.CYAN);
        ColorPrinter.printLine(50);
        
        int choice = getUserChoice();
        if (choice < 1 || choice > 6) {
            ColorPrinter.printError("无效的选择，返回主菜单");
            return -1;
        }
        return choice;
    }
    
    /**
     * 选择存储容量
     */
    public int chooseStorage(int[] options) {
        ColorPrinter.println("\n请选择存储容量 (GB):", ColorPrinter.CYAN);
        for (int i = 0; i < options.length; i++) {
            ColorPrinter.println("  " + (i + 1) + ". " + options[i] + "GB", ColorPrinter.WHITE);
        }
        
        int choice = getUserChoice();
        if (choice >= 1 && choice <= options.length) {
            return options[choice - 1];
        }
        return options[0]; // 默认返回第一个选项
    }
    
    /**
     * 选择颜色
     */
    public String chooseColor(String[] colors) {
        ColorPrinter.println("\n请选择颜色:", ColorPrinter.CYAN);
        for (int i = 0; i < colors.length; i++) {
            ColorPrinter.println("  " + (i + 1) + ". " + colors[i], ColorPrinter.WHITE);
        }
        
        int choice = getUserChoice();
        if (choice >= 1 && choice <= colors.length) {
            return colors[choice - 1];
        }
        return colors[0]; // 默认返回第一个选项
    }
    
    /**
     * 选择屏幕尺寸
     */
    public String chooseScreenSize(String[] sizes) {
        ColorPrinter.println("\n请选择屏幕尺寸:", ColorPrinter.CYAN);
        for (int i = 0; i < sizes.length; i++) {
            ColorPrinter.println("  " + (i + 1) + ". " + sizes[i], ColorPrinter.WHITE);
        }
        
        int choice = getUserChoice();
        if (choice >= 1 && choice <= sizes.length) {
            return sizes[choice - 1];
        }
        return sizes[0];
    }
    
    /**
     * 选择是否支持蜂窝网络
     */
    public boolean chooseCellular() {
        ColorPrinter.println("\n是否支持蜂窝网络?", ColorPrinter.CYAN);
        ColorPrinter.println("  1. 是", ColorPrinter.GREEN);
        ColorPrinter.println("  2. 否", ColorPrinter.WHITE);
        
        int choice = getUserChoice();
        return choice == 1;
    }
    
    /**
     * 输入价格
     */
    public double inputPrice() {
        while (true) {
            ColorPrinter.print("请输入价格 (¥): ", ColorPrinter.BOLD_YELLOW);
            try {
                double price = Double.parseDouble(scanner.nextLine().trim());
                if (price > 0) {
                    return price;
                } else {
                    ColorPrinter.printError("价格必须大于0");
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printError("请输入有效的数字");
            }
        }
    }
    
    /**
     * 输入库存
     */
    public int inputStock() {
        while (true) {
            ColorPrinter.print("请输入库存数量: ", ColorPrinter.BOLD_YELLOW);
            try {
                int stock = Integer.parseInt(scanner.nextLine().trim());
                if (stock >= 0) {
                    return stock;
                } else {
                    ColorPrinter.printError("库存不能为负数");
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printError("请输入有效的数字");
            }
        }
    }
    
    /**
     * 输入搜索关键词
     */
    public String inputSearchKeyword() {
        ColorPrinter.print("请输入搜索关键词: ", ColorPrinter.BOLD_YELLOW);
        return scanner.nextLine().trim();
    }
    
    /**
     * 选择产品
     */
    public String selectProduct(ProductManager productManager) {
        ColorPrinter.print("请输入产品ID: ", ColorPrinter.BOLD_YELLOW);
        String id = scanner.nextLine().trim();
        
        if (productManager.findById(id) == null) {
            ColorPrinter.printError("未找到该产品");
            return null;
        }
        
        return id;
    }
    
    /**
     * 确认删除
     */
    public boolean confirmDelete() {
        ColorPrinter.printWarning("确定要删除该产品吗? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        return confirm.equals("y") || confirm.equals("yes");
    }
    
    /**
     * 等待用户按键
     */
    public void waitForEnter() {
        ColorPrinter.print("\n按 Enter 键继续...", ColorPrinter.CYAN);
        scanner.nextLine();
    }
}

