package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MacBookTest {
    @Test
    void testGetDetails_ContainsChip() {
        MacBook macBook = new MacBook("MacBook Pro M3 Max", 24999.0, 10, "深空黑色", 1024, "16.2英寸", "M3 Max");
        String details = macBook.getDetails();
        assertTrue(details.contains("M3 Max"));
        assertTrue(details.contains("16.2英寸"));
    }
}