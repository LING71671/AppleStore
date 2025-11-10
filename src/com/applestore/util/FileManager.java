package com.applestore.util;

import com.applestore.model.*;
import com.applestore.service.ProductManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 文件管理工具类 | File Management Utility Class
 * 负责数据的持久化存储 | Responsible for data persistence storage
 */
public class FileManager {
    private static final String DATA_DIR = "data";
    private static final String PRODUCTS_FILE = DATA_DIR + "/products.txt";

    /**
     * 确保数据目录存在 | Ensure data directory exists
     */
    public static void ensureDataDirectory() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    /**
     * 保存产品数据到文件 | Save product data to file
     */
    public static boolean saveProducts(ProductManager productManager) {
        ensureDataDirectory();

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(PRODUCTS_FILE))) {

            oos.writeObject(productManager.getAllProducts());
            ColorPrinter.printSuccess("产品数据已成功保存到文件 | Product data successfully saved to file");
            return true;

        } catch (IOException e) {
            ColorPrinter.printError("保存产品数据失败: " + e.getMessage() + " | Failed to save product data: " + e.getMessage());
            return false;
        }
    }

    /**
     * 从文件加载产品数据 | Load product data from file
     */
    @SuppressWarnings("unchecked")
    public static List<Product> loadProducts() {
        ensureDataDirectory();

        File file = new File(PRODUCTS_FILE);
        if (!file.exists()) {
            ColorPrinter.printInfo("数据文件不存在，将创建新文件 | Data file does not exist, will create new file");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(PRODUCTS_FILE))) {

            List<Product> products = (List<Product>) ois.readObject();
            ColorPrinter.printSuccess("成功从文件加载 " + products.size() + " 个产品 | Successfully loaded " + products.size() + " products from file");
            return products;

        } catch (IOException | ClassNotFoundException e) {
            ColorPrinter.printError("加载产品数据失败: " + e.getMessage() + " | Failed to load product data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 导出产品数据为CSV格式 | Export product data to CSV format
     */
    public static boolean exportToCSV(ProductManager productManager, String filename) {
        ensureDataDirectory();

        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_DIR + "/" + filename))) {

            writer.println("ID,产品名称,型号,颜色,存储(GB),价格,库存 | ID,Product Name,Model,Color,Storage(GB),Price,Stock");

            for (Product product : productManager.getAllProducts()) {
                writer.printf("%s,%s,%s,%s,%d,%.2f,%d%n",
                        product.getId(),
                        product.getName(),
                        product.getModel(),
                        product.getColor(),
                        product.getStorage(),
                        product.getPrice(),
                        product.getStock());
            }

            ColorPrinter.printSuccess("数据已导出到 " + filename + " | Data exported to " + filename);
            return true;

        } catch (IOException e) {
            ColorPrinter.printError("导出CSV失败: " + e.getMessage() + " | CSV export failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * 导入CSV数据 | Import CSV data
     */
    public static boolean importFromCSV(String filename, ProductManager productManager) {
        ensureDataDirectory();

        File file = new File(DATA_DIR + "/" + filename);
        if (!file.exists()) {
            ColorPrinter.printError("文件不存在: " + filename + " | File does not exist: " + filename);
            return false;
        }

        try (Scanner scanner = new Scanner(file)) {

            if (scanner.hasNextLine()) {
                scanner.nextLine(); // 跳过标题行 | Skip header line
            }

            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 7) {
                    try {
                        String name = data[1];
                        String model = data[2];
                        String color = data[3];
                        int storage = Integer.parseInt(data[4]);
                        double price = Double.parseDouble(data[5]);
                        int stock = Integer.parseInt(data[6]);

                        Product product = createProductByName(name, model, price, stock, color, storage);
                        if (product != null) {
                            productManager.addProduct(product);
                            count++;
                        }
                    } catch (Exception e) {
                        ColorPrinter.printWarning("跳过无效数据行: " + line + " | Skipping invalid data row: " + line);
                    }
                }
            }

            ColorPrinter.printSuccess("成功导入 " + count + " 个产品 | Successfully imported " + count + " products");
            return true;

        } catch (IOException e) {
            ColorPrinter.printError("导入CSV失败: " + e.getMessage() + " | CSV import failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * 根据产品名称创建产品对象 | Create product object by product name
     */
    private static Product createProductByName(String name, String model, double price,
                                               int stock, String color, int storage) {
        switch (name) {
            case "Apple Vision Pro":
                return new AppleVisionPro(model, price, stock, color, storage);
            case "MacBook":
                // MacBook需要额外参数 | MacBook requires additional parameters
                return new MacBook(model, price, stock, color, storage, "16英寸", "M3");
            case "iPad":
                return new iPad(model, price, stock, color, storage, "11英寸", false);
            case "iPhone":
                return new iPhone(model, price, stock, color, storage, "6.7英寸", "Pro三摄");
            case "Apple Watch":
                return new AppleWatch(model, price, stock, color, storage, "45mm", "铝合金", false);
            case "AirPods":
                return new AirPods(model, price, stock, color, storage, "主动降噪", 30);
            default:
                return null;
        }
    }

}
