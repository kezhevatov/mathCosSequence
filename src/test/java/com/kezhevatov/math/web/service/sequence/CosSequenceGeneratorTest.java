package com.kezhevatov.math.web.service.sequence;

import com.kezhevatov.math.service.sequence.CosSequenceGenerator;
import com.kezhevatov.math.service.sequence.CosSequenceElementGenerator;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Andrey Kezhevatov
 * Date: 03.12.2017
 */
public class CosSequenceGeneratorTest {

    @Test
    public void testSequenceGenerateValues() {
        CosSequenceElementGenerator cosSequenceElementGenerator = mock(CosSequenceElementGenerator.class);

        when(cosSequenceElementGenerator.getElementByIndex(1)).thenReturn("cos(1)");
        when(cosSequenceElementGenerator.getElementByIndex(2)).thenReturn("cos(1-cos(2))");
        when(cosSequenceElementGenerator.getElementByIndex(3)).thenReturn("cos(1-cos(2+cos(3)))");

        CosSequenceGenerator cosSequenceGenerator = new CosSequenceGenerator(cosSequenceElementGenerator);
        String result = cosSequenceGenerator.getSequenceByIndex(3);

        Assert.assertEquals("((cos(1)+3)cos(1-cos(2))+2)cos(1-cos(2+cos(3)))+1", result);
    }
}
