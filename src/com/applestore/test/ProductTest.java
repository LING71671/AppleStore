package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testGenerateUniqueId() {
        Product p1 = new TestProduct("Test", "M1", 1000.0, 10, "Red", 128);
        Product p2 = new TestProduct("Test", "M1", 1000.0, 10, "Red", 128);

        assertNotNull(p1.getId());
        assertNotNull(p2.getId());
        assertNotEquals(p1.getId(), p2.getId(), "每个产品应生成唯一ID");
        assertEquals(8, p1.getId().length(), "ID应为8位");
    }

    @Test
    void testSetPrice_NegativePrice_ThrowsException() {
        Product product = new TestProduct("iPhone", "15", 5999.0, 10, "Black", 128);
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-100.0));
    }

    @Test
    void testSetStock_NegativeStock_ThrowsException() {
        Product product = new TestProduct("iPad", "Pro", 8000.0, 5, "Silver", 256);
        assertThrows(IllegalArgumentException.class, () -> product.setStock(-5));
    }

    @Test
    void testEqualsAndHashCode_SameId_ReturnsTrue() {
        String id = "abc12345";
        Product p1 = new TestProduct("Mac", "Pro", 20000.0, 3, "Space Gray", 512);
        Product p2 = new TestProduct("Mac", "Air", 9000.0, 8, "Silver", 256);

        p1.setId(id);
        p2.setId(id);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testToString_ContainsAllFields() {
        Product product = new TestProduct("Apple Watch", "Series 9", 2999.0, 20, "Pink", 64);
        String str = product.toString();
        assertTrue(str.contains("Apple Watch"));
        assertTrue(str.contains("Series 9"));
        assertTrue(str.contains("Pink"));
        assertTrue(str.contains("2999.00"));
    }

    // 内部类解决 Product 是 abstract 的问题
    private static class TestProduct extends Product {
        public TestProduct(String name, String model, double price, int stock, String color, int storage) {
            super(name, model, price, stock, color, storage);
        }
        @Override public String getDetails() { return "test"; }
    }
}