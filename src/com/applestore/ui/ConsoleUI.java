package com.applestore.ui;

import com.applestore.model.*;
import com.applestore.service.ProductManager;
import com.applestore.util.ColorPrinter;
import com.applestore.util.FileManager;

import java.util.List;

/**
 * 控制台UI类 | Console UI Class
 * 负责产品展示和用户交互 | Responsible for product display and user interaction
 */
public class ConsoleUI {
    private Menu menu;
    private ProductManager productManager;

    public ConsoleUI(ProductManager productManager, Menu menu) {
        this.productManager = productManager;
        this.menu = menu;
    }

    /**
     * 显示所有产品 | Display all products
     */
    public void displayAllProducts() {
        List<Product> products = productManager.getAllProducts();

        if (products.isEmpty()) {
            ColorPrinter.printError("暂无产品 | No products available");
            return;
        }

        ColorPrinter.printTitleBox("产品列表 | Product List");
        ColorPrinter.printTableHeader();

        for (Product product : products) {
            String row = String.format(("│%-10s│%-50s│%-13s│%-10s│" ),
                    truncate(product.getId(), 10),
                    truncate(product.getName() + " " + product.getModel(), 50),
                    product.getPrice(),
                    product.getStock());
            ColorPrinter.println(row, ColorPrinter.WHITE);
        }

        ColorPrinter.printTableBottom();
        ColorPrinter.printInfo("共 " + products.size() + " 个产品 | Total " + products.size() + " products");
    }

    /**
     * 显示单个产品详情 | Display single product details
     */
    public void displayProductDetails(Product product) {
        if (product == null) return;

        ColorPrinter.printLine(60);
        ColorPrinter.printRainbow("  ════════ 产品详情 ════════ | Product Details");
        ColorPrinter.printLine(60);

        ColorPrinter.println("  ID: " + product.getId(), ColorPrinter.CYAN);
        ColorPrinter.println("  名称: " + product.getName() + " | Name: " + product.getName(), ColorPrinter.WHITE);
        ColorPrinter.println("  型号: " + product.getModel() + " | Model: " + product.getModel(), ColorPrinter.WHITE);
        ColorPrinter.println("  颜色: " + product.getColor() + " | Color: " + product.getColor(), ColorPrinter.WHITE);
        ColorPrinter.println("  存储: " + product.getStorage() + "GB | Storage: " + product.getStorage() + "GB", ColorPrinter.WHITE);
        ColorPrinter.println("  价格: ¥" + String.format("%.2f", product.getPrice()) + " | Price: ¥" + String.format("%.2f", product.getPrice()), ColorPrinter.GREEN);
        ColorPrinter.println("  库存: " + product.getStock() + " | Stock: " + product.getStock(), ColorPrinter.YELLOW);

        // 根据产品类型显示额外信息 | Display additional information based on product type
        if (product instanceof MacBook) {
            MacBook mb = (MacBook) product;
            ColorPrinter.println("  屏幕: " + mb.getScreenSize() + " | Screen: " + mb.getScreenSize(), ColorPrinter.WHITE);
            ColorPrinter.println("  芯片: " + mb.getChip() + " | Chip: " + mb.getChip(), ColorPrinter.WHITE);
        } else if (product instanceof iPad) {
            iPad ipad = (iPad) product;
            ColorPrinter.println("  屏幕: " + ipad.getScreenSize() + " | Screen: " + ipad.getScreenSize(), ColorPrinter.WHITE);
            ColorPrinter.println("  网络: " + (ipad.isCellular() ? "蜂窝网络 + Wi-Fi" : "Wi-Fi") + " | Network: " + (ipad.isCellular() ? "Cellular + Wi-Fi" : "Wi-Fi"), ColorPrinter.WHITE);
        } else if (product instanceof iPhone) {
            iPhone iphone = (iPhone) product;
            ColorPrinter.println("  屏幕: " + iphone.getScreenSize() + " | Screen: " + iphone.getScreenSize(), ColorPrinter.WHITE);
            ColorPrinter.println("  摄像头: " + iphone.getCamera() + " | Camera: " + iphone.getCamera(), ColorPrinter.WHITE);
        } else if (product instanceof AppleWatch) {
            AppleWatch watch = (AppleWatch) product;
            ColorPrinter.println("  表壳: " + watch.getCaseSize() + " " + watch.getCaseMaterial() + " | Case: " + watch.getCaseSize() + " " + watch.getCaseMaterial(), ColorPrinter.WHITE);
            ColorPrinter.println("  网络: " + (watch.isCellular() ? "蜂窝网络 + GPS" : "GPS") + " | Network: " + (watch.isCellular() ? "Cellular + GPS" : "GPS"), ColorPrinter.WHITE);
        } else if (product instanceof AirPods) {
            AirPods airpods = (AirPods) product;
            ColorPrinter.println("  降噪: " + airpods.getNoiseCancellation() + " | Noise Cancellation: " + airpods.getNoiseCancellation(), ColorPrinter.WHITE);
            ColorPrinter.println("  续航: " + airpods.getBatteryLife() + "小时 | Battery Life: " + airpods.getBatteryLife() + " hours", ColorPrinter.WHITE);
        }

        ColorPrinter.println("  " + product.getDetails(), ColorPrinter.CYAN);
        ColorPrinter.printLine(60);
    }

    /**
     * 显示搜索结果 | Display search results
     */
    public void displaySearchResults(List<Product> products, String keyword) {
        if (products.isEmpty()) {
            ColorPrinter.printWarning("没有找到包含 \"" + keyword + "\" 的产品 | No products found containing \"" + keyword + "\"");
            return;
        }

        ColorPrinter.printTableHeader();

        for (Product product : products) {
            String row = String.format("│%-10s│%-50s│%-13s│%-10s│",
                    truncate(product.getId(), 10),
                    truncate(product.getName() + " " + product.getModel(), 25),
                    product.getPrice(),
                    product.getStock());
            ColorPrinter.println(row, ColorPrinter.WHITE);
        }

        ColorPrinter.printTableBottom();
        ColorPrinter.printInfo("找到 " + products.size() + " 个产品 | Found " + products.size() + " products");
    }

    /**
     * 显示筛选结果 | Display filter results
     */
    public void displayFilterResults(List<Product> products) {
        displayAllProducts(); // 复用显示方法 | Reuse display method
    }

    /**
     * 显示数据管理菜单 | Display data management menu
     */
    public void showDataManagementMenu() {
        ColorPrinter.printLine(50);
        ColorPrinter.println("  📊 数据管理 | Data Management", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printLine(50);
        ColorPrinter.println("  1. 保存数据到文件 | Save data to file", ColorPrinter.GREEN);
        ColorPrinter.println("  2. 从文件加载数据 | Load data from file", ColorPrinter.BLUE);
        ColorPrinter.println("  3. 导出为CSV | Export to CSV", ColorPrinter.YELLOW);
        ColorPrinter.println("  4. 从CSV导入 | Import from CSV", ColorPrinter.MAGENTA);
        ColorPrinter.println("  5. 返回主菜单 | Return to main menu", ColorPrinter.WHITE);
        ColorPrinter.printLine(50);

        int choice = menu.getUserChoice();

        switch (choice) {
            case 1:
                FileManager.saveProducts(productManager);
                break;
            case 2:
                List<Product> products = FileManager.loadProducts();
                productManager.setProducts(products);
                break;
            case 3:
                ColorPrinter.print("请输入文件名 (不含扩展名): | Please enter filename (without extension): ", ColorPrinter.BOLD_YELLOW);
                String exportFile = menu.inputSearchKeyword() + ".csv";
                FileManager.exportToCSV(productManager, exportFile);
                break;
            case 4:
                ColorPrinter.print("请输入CSV文件名: | Please enter CSV filename: ", ColorPrinter.BOLD_YELLOW);
                String importFile = menu.inputSearchKeyword();
                FileManager.importFromCSV(importFile, productManager);
                break;
            case 5:
                return;
            default:
                ColorPrinter.printError("无效的选择 | Invalid choice");
        }

        menu.waitForEnter();
    }

    /**
     * 显示筛选菜单 | Display filter menu
     */
    public void showFilterMenu() {
        ColorPrinter.printLine(50);
        ColorPrinter.println("  🔍 筛选产品 | Filter Products", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printLine(50);
        ColorPrinter.println("  1. 按颜色筛选 | Filter by color", ColorPrinter.MAGENTA);
        ColorPrinter.println("  2. 按价格范围筛选 | Filter by price range", ColorPrinter.GREEN);
        ColorPrinter.println("  3. 返回主菜单 | Return to main menu", ColorPrinter.WHITE);
        ColorPrinter.printLine(50);

        int choice = menu.getUserChoice();

        switch (choice) {
            case 1:
                String keyword = menu.inputSearchKeyword();
                List<Product> products = productManager.filterByColor(keyword);
                displaySearchResults(products, "颜色: " + keyword + " | Color: " + keyword);
                break;
            case 2:
                ColorPrinter.print("请输入最低价格: | Please enter minimum price: ", ColorPrinter.BOLD_YELLOW);
                double min = Double.parseDouble(menu.inputSearchKeyword());
                ColorPrinter.print("请输入最高价格: | Please enter maximum price: ", ColorPrinter.BOLD_YELLOW);
                double max = Double.parseDouble(menu.inputSearchKeyword());
                products = productManager.filterByPrice(min, max);
                displaySearchResults(products, String.format("价格: ¥%.2f - ¥%.2f | Price: ¥%.2f - ¥%.2f", min, max, min, max));
                break;
            case 3:
                return;
            default:
                ColorPrinter.printError("无效的选择 | Invalid choice");
        }

        menu.waitForEnter();
    }

    /**
     * 显示排序菜单 | Display sort menu
     */
    public void showSortMenu() {
        ColorPrinter.printLine(50);
        ColorPrinter.println("  📈 排序产品 | Sort Products", ColorPrinter.BOLD_CYAN);
        ColorPrinter.printLine(50);
        ColorPrinter.println("  1. 按价格升序 | Sort by price ascending", ColorPrinter.GREEN);
        ColorPrinter.println("  2. 按价格降序 | Sort by price descending", ColorPrinter.YELLOW);
        ColorPrinter.println("  3. 按名称排序 | Sort by name", ColorPrinter.BLUE);
        ColorPrinter.println("  4. 返回主菜单 | Return to main menu", ColorPrinter.WHITE);
        ColorPrinter.printLine(50);

        int choice = menu.getUserChoice();

        List<Product> sorted;
        switch (choice) {
            case 1:
                sorted = productManager.sortByPrice(true);
                displaySearchResults(sorted, "按价格升序 | Sort by price ascending");
                break;
            case 2:
                sorted = productManager.sortByPrice(false);
                displaySearchResults(sorted, "按价格降序 | Sort by price descending");
                break;
            case 3:
                sorted = productManager.sortByName();
                displaySearchResults(sorted, "按名称排序 | Sort by name");
                break;
            case 4:
                return;
            default:
                ColorPrinter.printError("无效的选择 | Invalid choice");
                return;
        }

        menu.waitForEnter();
    }

    /**
     * 截断字符串 | Truncate string
     */
    private String truncate(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
        //We enlarge the maximum length to ensure there is enough space to ensure the completly display of the string
    }
}




