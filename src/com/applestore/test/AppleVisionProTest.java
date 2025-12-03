package com.applestore.test;

import com.applestore.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppleVisionProTest {
    @Test
    void testDetailsContainsSpatialComputing() {
        AppleVisionPro vision = new AppleVisionPro("512GB", 27999.0, 8, "深空灰", 512);
        String details = vision.getDetails();
        assertTrue(details.contains("空间计算") || details.contains("spatial computing"));
        assertTrue(details.contains("眼球追踪") || details.contains("eye tracking"));
    }
}