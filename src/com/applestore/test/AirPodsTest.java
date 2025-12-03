package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirPodsTest {
    @Test
    void testNoiseCancellationInDetails() {
        AirPods airpods = new AirPods("AirPods Pro 2", 1899.0, 40, "白色", 256, "主动降噪", 30);
        String details = airpods.getDetails();
        assertTrue(details.contains("主动降噪"));
        assertTrue(details.contains("30 小时"));
    }
}