package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppleWatchTest {
    @Test
    void testCellularDisplay() {
        AppleWatch watch = new AppleWatch("Ultra 2", 6499.0, 12, "钛金属", 64, "49mm", "钛金属", true);
        String details = watch.getDetails();
        assertTrue(details.contains("支持蜂窝网络") || details.contains("supports"));
    }
}