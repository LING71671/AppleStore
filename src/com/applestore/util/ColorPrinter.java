package com.applestore.util;

import java.util.Random;

/**
 * 控制台彩色打印工具类 | Console Color Printing Utility Class
 * 提供彩虹色输出和装饰图案 | Provides rainbow color output and decorative patterns
 */
public class ColorPrinter {
    // ANSI颜色代码 | ANSI color codes
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String MAGENTA = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static final String BOLD_GREEN = "\033[1;32m";
    public static final String BOLD_YELLOW = "\033[1;33m";
    public static final String BOLD_CYAN = "\033[1;36m";

    /**
     * 打印单个彩色文本 | Print single colored text
     */
    public static void print(String text, String color) {
        System.out.print(color + text + RESET);
    }

    /**
     * 打印彩色文本并换行 | Print colored text with newline
     */
    public static void println(String text, String color) {
        System.out.println(color + text + RESET);
    }

    /**
     * 打印彩虹色文本（每个字符不同颜色） | Print rainbow colored text (each character with different color)
     */
    public static void printRainbow(String text) {
        String[] colors = {RED, YELLOW, GREEN, CYAN, BLUE, MAGENTA};
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(colors[i % colors.length] + chars[i] + RESET);
        }
        System.out.println();
    }

    /**
     * 打印随机彩色文本 | Print random colored text
     */
    public static void printRandomColor(String text) {
        String[] colors = {RED, YELLOW, GREEN, CYAN, BLUE, MAGENTA};
        Random random = new Random();
        String color = colors[random.nextInt(colors.length)];
        println(text, color);
    }

    /**
     * 打印装饰分隔线 | Print decorative separator line
     */
    public static void printLine(int length) {
        StringBuilder line = new StringBuilder();
        String[] symbols = {"═", "━", "─", "▬", "▭"};
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            line.append(symbols[random.nextInt(symbols.length)]);
        }
        printRainbow(line.toString());
    }

    /**
     * 打印标题框 | Print title box
     */
    public static void printTitleBox(String title) {
        String[] colors = {CYAN, BLUE, MAGENTA};
        Random random = new Random();
        String color = colors[random.nextInt(colors.length)];

        int len = title.length() + 6;
        println("╔" + "═".repeat(len) + "╗", color);
        println("║  " + title + " ║", color);           //We are thinking about how to align perfectly...
        println("╚" + "═".repeat(len) + "╝", color);
    }

    /**
     * 打印欢迎消息 | Print welcome message
     */
    public static void printWelcome() {
        println("\n", RESET);
        printLine(50);
        printRainbow("     ╔════════════════════════╗");
        printRainbow("     ║       Welcome to       ║");
        printRainbow("     ║       AppleStore       ║");
        printRainbow("     ╚════════════════════════╝");
        printLine(50);
        println("", RESET);
    }

    /**
     * 打印提示信息 | Print info message
     */
    public static void printInfo(String message) {
        println("ℹ️  " + message, CYAN);
    }

    /**
     * 打印成功信息 | Print success message
     */
    public static void printSuccess(String message) {
        println("✓ " + message, GREEN);
    }

    /**
     * 打印错误信息 | Print error message
     */
    public static void printError(String message) {
        println("✗ " + message, RED);
    }

    /**
     * 打印警告信息 | Print warning message
     */
    public static void printWarning(String message) {
        println("⚠ " + message, YELLOW);
    }

    /**
     * 打印表格分隔线 | Print table separator
     */
    public static void printTableSeparator() {
        println("┌──────────────────────────────────────────────────────────────────────────────────────┐", CYAN);
    }

    /**
     * 打印表格头 | Print table header
     */
    public static void printTableHeader() {
        printTableSeparator();
        println("│" + BOLD_CYAN + String.format("%-10s", "ID") + RESET +
               "│" + BOLD_CYAN + String.format("%-48s", "产品信息") + RESET +
               "│" + BOLD_CYAN + String.format("%-10s", "价格") + RESET +
               "│" + BOLD_CYAN + String.format("%-6s", "库存") + RESET + "    │", WHITE);
        printTableSeparator();
    }

    /**
     * 打印表格底部 | Print table bottom
     */
    public static void printTableBottom() {
        println("└──────────────────────────────────────────────────────────────────────────────────────┘\n", CYAN);
    }
}
