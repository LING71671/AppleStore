package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class iPadTest {
    @Test
    void testCellularSupport_DisplayCorrectly() {
        iPad ipad = new iPad("iPad Pro", 9299.0, 15, "深空灰", 512, "12.9英寸", true);
        String details = ipad.getDetails();
        assertTrue(details.contains("支持蜂窝网络") || details.contains("supports"));
    }
}