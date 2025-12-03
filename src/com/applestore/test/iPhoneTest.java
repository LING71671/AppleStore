package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class iPhoneTest {
    @Test
    void testGetDetails_ContainsScreenAndCamera() {
        iPhone iphone = new iPhone("iPhone 15 Pro", 8999.0, 25, "原色钛金属", 256, "6.1英寸", "Pro三摄");
        String details = iphone.getDetails();
        assertTrue(details.contains("6.1英寸"));
        assertTrue(details.contains("Pro三摄"));
    }
}