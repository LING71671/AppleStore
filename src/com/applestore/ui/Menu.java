package com.applestore.ui;

import com.applestore.service.ProductManager;
import com.applestore.util.ColorPrinter;

import java.util.Scanner;

/**
 * 菜单类 | Menu Class
 * 处理用户交互和选择菜单 | Handle user interaction and menu selection
 */
public class Menu {
    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * 显示主菜单 | Display main menu
     */
    public void showMainMenu() {
        ColorPrinter.printLine(60);
        ColorPrinter.println("                  📋 主菜单 | Main Menu", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printLine(60);
        ColorPrinter.println("  1️⃣  查看所有产品 | View all products", ColorPrinter.GREEN);
        ColorPrinter.println("  2️⃣  添加新产品 | Add new product", ColorPrinter.GREEN);
        ColorPrinter.println("  3️⃣  更新产品信息 | Update product information", ColorPrinter.GREEN);
        ColorPrinter.println("  4️⃣  删除产品 | Delete product", ColorPrinter.RED);
        ColorPrinter.println("  5️⃣  搜索产品 | Search product", ColorPrinter.YELLOW);
        ColorPrinter.println("  6️⃣  筛选产品 | Filter products", ColorPrinter.YELLOW);
        ColorPrinter.println("  7️⃣  排序产品 | Sort products", ColorPrinter.YELLOW);
        ColorPrinter.println("  8️⃣  查看统计信息 | View statistics", ColorPrinter.CYAN);
        ColorPrinter.println("  9️⃣  保存/加载数据 | Save/Load data", ColorPrinter.BLUE);
        ColorPrinter.println("  0️⃣  退出程序 | Exit program", ColorPrinter.MAGENTA);
        ColorPrinter.printLine(60);
    }

    /**
     * 获取用户选择 | Get user choice
     */
    public int getUserChoice() {
        ColorPrinter.print("请输入屏幕上的选项| Please enter the option on the screen : ", ColorPrinter.BOLD_YELLOW);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 选择产品类型 | Choose product type
     */
    public int chooseProductType() {
        ColorPrinter.printLine(50);
        ColorPrinter.println("  请选择产品类型: | Please select product type:", ColorPrinter.BOLD_CYAN);
        ColorPrinter.println("  1. Apple Vision Pro 🥽", ColorPrinter.RED);
        ColorPrinter.println("  2. MacBook 💻", ColorPrinter.BLUE);
        ColorPrinter.println("  3. iPad 📱", ColorPrinter.YELLOW);
        ColorPrinter.println("  4. iPhone 📞", ColorPrinter.GREEN);
        ColorPrinter.println("  5. Apple Watch ⌚", ColorPrinter.MAGENTA);
        ColorPrinter.println("  6. AirPods 🎧", ColorPrinter.CYAN);
        ColorPrinter.printLine(50);

        int choice = getUserChoice();
        if (choice < 1 || choice > 6) {
            ColorPrinter.printError("无效的选择，返回主菜单 | Invalid choice, returning to main menu");
            return -1;
        }
        return choice;
    }

    /**
     * 选择存储容量 | Choose storage capacity
     */
    public int chooseStorage(int[] options) {
        ColorPrinter.println("\n请选择存储容量 (GB): | Please select storage capacity (GB):", ColorPrinter.CYAN);
        for (int i = 0; i < options.length; i++) {
            ColorPrinter.println("  " + (i + 1) + ". " + options[i] + "GB", ColorPrinter.WHITE);
        }

        int choice = getUserChoice();
        if (choice >= 1 && choice <= options.length) {
            return options[choice - 1];
        }
        return options[0]; // 默认返回第一个选项 | Default return first option
    }

    /**
     * 选择颜色 | Choose color
     */
    public String chooseColor(String[] colors) {
        ColorPrinter.println("\n请选择颜色: | Please select color:", ColorPrinter.CYAN);
        for (int i = 0; i < colors.length; i++) {
            ColorPrinter.println("  " + (i + 1) + ". " + colors[i], ColorPrinter.WHITE);
        }

        int choice = getUserChoice();
        if (choice >= 1 && choice <= colors.length) {
            return colors[choice - 1];
        }
        return colors[0]; // 默认返回第一个选项 | Default return first option
    }

    /**
     * 选择屏幕尺寸 | Choose screen size
     */
    public String chooseScreenSize(String[] sizes) {
        ColorPrinter.println("\n请选择屏幕尺寸: | Please select screen size:", ColorPrinter.CYAN);
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
     * 选择是否支持蜂窝网络 | Choose whether cellular network is supported
     */
    public boolean chooseCellular() {
        ColorPrinter.println("\n是否支持蜂窝网络? | Does it support cellular network?", ColorPrinter.CYAN);
        ColorPrinter.println("  1. 是 | Yes", ColorPrinter.GREEN);
        ColorPrinter.println("  2. 否 | No", ColorPrinter.WHITE);

        int choice = getUserChoice();
        return choice == 1;
    }

    /**
     * 输入价格 | Input price
     */
    public double inputPrice() {
        while (true) {
            ColorPrinter.print("请输入价格 (¥): | Please enter price (¥): ", ColorPrinter.BOLD_YELLOW);
            try {
                double price = Double.parseDouble(scanner.nextLine().trim());
                if (price > 0) {
                    return price;
                } else {
                    ColorPrinter.printError("价格必须大于0 | Price must be greater than 0");
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printError("请输入有效的数字 | Please enter a valid number");
            }
        }
    }

    /**
     * 输入库存 | Input stock
     */
    public int inputStock() {
        while (true) {
            ColorPrinter.print("请输入库存数量: | Please enter stock quantity: ", ColorPrinter.BOLD_YELLOW);
            try {
                int stock = Integer.parseInt(scanner.nextLine().trim());
                if (stock >= 0) {
                    return stock;
                } else {
                    ColorPrinter.printError("库存不能为负数 | Stock cannot be negative");
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printError("请输入有效的数字 | Please enter a valid number");
            }
        }
    }

    /**
     * 输入搜索关键词 | Input search keyword
     */
    public String inputSearchKeyword() {
        ColorPrinter.print("请输入搜索关键词: | Please enter search keyword: ", ColorPrinter.BOLD_YELLOW);
        return scanner.nextLine().trim();
    }

    /**
     * 选择产品 | Select product
     */
    public String selectProduct(ProductManager productManager) {
        ColorPrinter.print("请输入产品ID: | Please enter product ID: ", ColorPrinter.BOLD_YELLOW);
        String id = scanner.nextLine().trim();

        if (productManager.findById(id) == null) {
            ColorPrinter.printError("未找到该产品 | Product not found");
            return null;
        }

        return id;
    }

    /**
     * 确认删除 | Confirm deletion
     */
    public boolean confirmDelete() {
        ColorPrinter.printWarning("确定要删除该产品吗? (y/n): | Are you sure you want to delete this product? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        return confirm.equals("y") || confirm.equals("yes");
    }

    /**
     * 等待用户按键 | Wait for user key press
     */
    public void waitForEnter() {
        ColorPrinter.print("\n按 Enter 键继续... | Press Enter to continue...", ColorPrinter.CYAN);
        scanner.nextLine();
    }
}
