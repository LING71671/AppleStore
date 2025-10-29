package com.applestore;

import com.applestore.model.*;
import com.applestore.service.ProductManager;
import com.applestore.service.Store;
import com.applestore.ui.ConsoleUI;
import com.applestore.ui.Menu;
import com.applestore.util.ColorPrinter;
import com.applestore.util.FileManager;

import java.util.List;
import java.util.Scanner;

/**
 * AppleStore 主程序
 * 
 * @author AppleStore Team
 * @version 1.0
 */
public class App {
    private static ProductManager productManager;
    private static Store store;
    private static ConsoleUI consoleUI;
    private static Menu menu;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // 初始化组件
        initializeComponents();
        
        // 显示欢迎界面
        ColorPrinter.printWelcome();
        
        // 加载数据
        loadData();
        
        // 如果数据为空，初始化示例数据
        if (productManager.getProductCount() == 0) {
            ColorPrinter.printInfo("第一次运行，正在初始化示例数据...");
            store.initializeSampleData();
            FileManager.saveProducts(productManager); // 自动保存
        }
        
        // 运行主循环
        runMainLoop();
        
        // 退出前保存数据
        FileManager.saveProducts(productManager);
        
        // 显示退出信息
        ColorPrinter.printLine(60);
        ColorPrinter.printRainbow("  ════════ 感谢使用 AppleStore ════════");
        ColorPrinter.printLine(60);
        ColorPrinter.println("  👋 再见！期待您的下次光临！", ColorPrinter.BOLD_GREEN);
        ColorPrinter.printLine(60);
    }
    
    /**
     * 初始化组件
     */
    private static void initializeComponents() {
        productManager = new ProductManager();
        store = new Store(productManager);
        menu = new Menu();
        consoleUI = new ConsoleUI(productManager, menu);
        scanner = new Scanner(System.in);
    }
    
    /**
     * 加载数据
     */
    private static void loadData() {
        List<Product> products = FileManager.loadProducts();
        if (!products.isEmpty()) {
            productManager.setProducts(products);
        }
    }
    
    /**
     * 运行主循环
     */
    private static void runMainLoop() {
        while (true) {
            // 清屏（打印空行模拟）
            System.out.println("\n\n");
            
            // 显示主菜单
            menu.showMainMenu();
            
            // 获取用户选择
            int choice = menu.getUserChoice();
            
            // 处理用户选择
            if (!handleUserChoice(choice)) {
                break; // 用户选择退出
            }
            
            // 等待用户按键
            if (choice != 0) {
                menu.waitForEnter();
            }
        }
    }
    
    /**
     * 处理用户选择
     */
    private static boolean handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                consoleUI.displayAllProducts();
                break;
                
            case 2:
                addProduct();
                break;
                
            case 3:
                updateProduct();
                break;
                
            case 4:
                deleteProduct();
                break;
                
            case 5:
                searchProduct();
                break;
                
            case 6:
                consoleUI.showFilterMenu();
                break;
                
            case 7:
                consoleUI.showSortMenu();
                break;
                
            case 8:
                store.printStatistics();
                break;
                
            case 9:
                consoleUI.showDataManagementMenu();
                break;
                
            case 0:
                return false; // 退出程序
                
            default:
                ColorPrinter.printError("无效的选择，请输入 0-9 之间的数字");
        }
        return true;
    }
    
    /**
     * 添加产品
     */
    private static void addProduct() {
        int type = menu.chooseProductType();
        if (type == -1) return;
        
        Product product = null;
        
        try {
            // 根据类型选择不同的配置选项
            switch (type) {
                case 1: // Apple Vision Pro
                    product = createAppleVisionPro();
                    break;
                    
                case 2: // MacBook
                    product = createMacBook();
                    break;
                    
                case 3: // iPad
                    product = createiPad();
                    break;
                    
                case 4: // iPhone
                    product = createiPhone();
                    break;
                    
                case 5: // Apple Watch
                    product = createAppleWatch();
                    break;
                    
                case 6: // AirPods
                    product = createAirPods();
                    break;
            }
            
            if (product != null) {
                productManager.addProduct(product);
                FileManager.saveProducts(productManager); // 自动保存
            }
            
        } catch (Exception e) {
            ColorPrinter.printError("添加产品失败: " + e.getMessage());
        }
    }
    
    /**
     * 创建Apple Vision Pro
     */
    private static Product createAppleVisionPro() {
        int storage = menu.chooseStorage(new int[]{256, 512});
        String color = menu.chooseColor(new String[]{"深空灰", "深空黑色"});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new AppleVisionPro(storage + "GB", price, stock, color, storage);
    }
    
    /**
     * 创建MacBook
     */
    private static Product createMacBook() {
        ColorPrinter.print("请输入型号 (例如: MacBook Pro M3 14英寸): ", ColorPrinter.BOLD_YELLOW);
        String model = menu.inputSearchKeyword();
        String screenSize = menu.chooseScreenSize(new String[]{"13.6英寸", "15.3英寸", "14.2英寸", "16.2英寸"});
        ColorPrinter.print("请输入芯片 (例如: M3, M3 Max): ", ColorPrinter.BOLD_YELLOW);
        String chip = menu.inputSearchKeyword();
        String color = menu.chooseColor(new String[]{"午夜色", "星光色", "深空灰色", "深空黑色", "银色"});
        int storage = menu.chooseStorage(new int[]{256, 512, 1024});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new MacBook(model, price, stock, color, storage, screenSize, chip);
    }
    
    /**
     * 创建iPad
     */
    private static Product createiPad() {
        ColorPrinter.print("请输入型号 (例如: iPad Air 第5代): ", ColorPrinter.BOLD_YELLOW);
        String model = menu.inputSearchKeyword();
        String screenSize = menu.chooseScreenSize(new String[]{"10.9英寸", "11英寸", "12.9英寸"});
        boolean cellular = menu.chooseCellular();
        String color = menu.chooseColor(new String[]{"深空灰", "银色", "蓝色", "粉色", "紫色"});
        int storage = menu.chooseStorage(new int[]{64, 128, 256, 512});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new iPad(model, price, stock, color, storage, screenSize, cellular);
    }
    
    /**
     * 创建iPhone
     */
    private static Product createiPhone() {
        ColorPrinter.print("请输入型号 (例如: iPhone 15 Pro): ", ColorPrinter.BOLD_YELLOW);
        String model = menu.inputSearchKeyword();
        String screenSize = menu.chooseScreenSize(new String[]{"6.1英寸", "6.7英寸"});
        ColorPrinter.print("请输入摄像头配置: ", ColorPrinter.BOLD_YELLOW);
        String camera = menu.inputSearchKeyword();
        String color = menu.chooseColor(new String[]{"深空灰", "银色", "金色", "原色钛金属", "蓝色钛金属", 
                "粉色", "黄色", "绿色", "蓝色", "午夜色"});
        int storage = menu.chooseStorage(new int[]{128, 256, 512, 1024});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new iPhone(model, price, stock, color, storage, screenSize, camera);
    }
    
    /**
     * 创建Apple Watch
     */
    private static Product createAppleWatch() {
        ColorPrinter.print("请输入型号 (例如: Apple Watch Series 9): ", ColorPrinter.BOLD_YELLOW);
        String model = menu.inputSearchKeyword();
        String caseSize = menu.chooseScreenSize(new String[]{"41mm", "44mm", "45mm", "49mm"});
        String caseMaterial = menu.chooseColor(new String[]{"铝金属", "铝合金", "不锈钢", "钛金属"});
        boolean cellular = menu.chooseCellular();
        String color = menu.chooseColor(new String[]{"午夜色", "星光色", "粉砂色", "午夜色铝金属", 
                "星光色铝金属", "深空灰色不锈钢", "银色不锈钢", "钛金属原色"});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new AppleWatch(model, price, stock, color, 64, caseSize, caseMaterial, cellular);
    }
    
    /**
     * 创建AirPods
     */
    private static Product createAirPods() {
        ColorPrinter.print("请输入型号 (例如: AirPods Pro 第2代): ", ColorPrinter.BOLD_YELLOW);
        String model = menu.inputSearchKeyword();
        String noiseCancellation = menu.chooseColor(new String[]{"主动降噪", "自适应均衡", "无降噪"});
        
        int batteryLife = 30;
        while (true) {
            ColorPrinter.print("请输入续航时间(小时): ", ColorPrinter.BOLD_YELLOW);
            try {
                String batteryInput = scanner.nextLine().trim();
                if (batteryInput.isEmpty()) {
                    batteryLife = 30; // 默认值
                    break;
                }
                batteryLife = Integer.parseInt(batteryInput);
                if (batteryLife > 0) {
                    break;
                } else {
                    ColorPrinter.printError("续航时间必须大于0");
                }
            } catch (NumberFormatException e) {
                ColorPrinter.printError("请输入有效的数字");
            }
        }
        
        String color = menu.chooseColor(new String[]{"白色", "银色", "深空灰色", "粉色", "黄色", "绿色", "紫色"});
        double price = menu.inputPrice();
        int stock = menu.inputStock();
        
        return new AirPods(model, price, stock, color, 256, noiseCancellation, batteryLife);
    }
    
    /**
     * 更新产品
     */
    private static void updateProduct() {
        consoleUI.displayAllProducts();
        String id = menu.selectProduct(productManager);
        if (id == null) return;
        
        Product product = productManager.findById(id);
        ColorPrinter.printInfo("正在更新产品...");
        
        try {
            // 更新价格
            ColorPrinter.print("请输入新价格 (¥, 保持不变请按Enter): ", ColorPrinter.BOLD_YELLOW);
            String priceInput = scanner.nextLine().trim();
            if (!priceInput.isEmpty()) {
                try {
                    double newPrice = Double.parseDouble(priceInput);
                    if (newPrice > 0) {
                        product.setPrice(newPrice);
                    } else {
                        ColorPrinter.printError("价格必须大于0");
                        return;
                    }
                } catch (NumberFormatException e) {
                    ColorPrinter.printError("请输入有效的数字");
                    return;
                }
            }
            
            // 更新库存
            ColorPrinter.print("请输入新库存 (保持不变请按Enter): ", ColorPrinter.BOLD_YELLOW);
            String stockInput = scanner.nextLine().trim();
            if (!stockInput.isEmpty()) {
                try {
                    int newStock = Integer.parseInt(stockInput);
                    if (newStock >= 0) {
                        product.setStock(newStock);
                    } else {
                        ColorPrinter.printError("库存不能为负数");
                        return;
                    }
                } catch (NumberFormatException e) {
                    ColorPrinter.printError("请输入有效的数字");
                    return;
                }
            }
            
            productManager.updateProduct(id, product);
            FileManager.saveProducts(productManager); // 自动保存
            
        } catch (Exception e) {
            ColorPrinter.printError("更新失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除产品
     */
    private static void deleteProduct() {
        consoleUI.displayAllProducts();
        String id = menu.selectProduct(productManager);
        if (id == null) return;
        
        Product product = productManager.findById(id);
        consoleUI.displayProductDetails(product);
        
        if (menu.confirmDelete()) {
            productManager.deleteProduct(id);
            FileManager.saveProducts(productManager); // 自动保存
        } else {
            ColorPrinter.printInfo("已取消删除操作");
        }
    }
    
    /**
     * 搜索产品
     */
    private static void searchProduct() {
        String keyword = menu.inputSearchKeyword();
        if (keyword.isEmpty()) {
            ColorPrinter.printError("搜索关键词不能为空");
            return;
        }
        
        List<Product> results = productManager.search(keyword);
        consoleUI.displaySearchResults(results, keyword);
    }
}
