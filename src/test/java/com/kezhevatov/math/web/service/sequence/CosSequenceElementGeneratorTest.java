package com.kezhevatov.math.web.service.sequence;

import com.kezhevatov.math.service.sequence.CosSequenceElementGenerator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andrey Kezhevatov
 * Date: 03.12.2017
 */
public class CosSequenceElementGeneratorTest {

    @Test
    public void testSequenceElementGenerateValues() {
        CosSequenceElementGenerator cosSequenceElementGenerator = new CosSequenceElementGenerator();

        String result5 = cosSequenceElementGenerator.getElementByIndex(5);
        String result4 = cosSequenceElementGenerator.getElementByIndex(4);
        String result3 = cosSequenceElementGenerator.getElementByIndex(3);
        String result2 = cosSequenceElementGenerator.getElementByIndex(2);
        String result1 = cosSequenceElementGenerator.getElementByIndex(1);

        Assert.assertEquals("cos(1-cos(2+cos(3-cos(4+cos(5)))))", result5);
        Assert.assertEquals("cos(1-cos(2+cos(3-cos(4))))", result4);
        Assert.assertEquals("cos(1-cos(2+cos(3)))", result3);
        Assert.assertEquals("cos(1-cos(2))", result2);
        Assert.assertEquals("cos(1)", result1);
    }
}
