package com.kezhevatov.math.service.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Sequence generator
 */
@Component
public class CosSequenceGenerator {

    private final CosSequenceElementGenerator cosSequenceElementGenerator;

    @Autowired
    public CosSequenceGenerator(CosSequenceElementGenerator cosSequenceElementGenerator) {
        this.cosSequenceElementGenerator = cosSequenceElementGenerator;
    }

    /**
     * Generate sequence by index
     * @param elementIndex
     * @return sequence. Example: ((cos(1)+3)cos(1-cos(2))+2)cos(1-cos(2+cos(3)))+1
     */
    @Cacheable("sequence")
    public String getSequenceByIndex(int elementIndex) {

        StringBuilder result = new StringBuilder(elementIndex);

        for (int i = 1; i < elementIndex; i++) {
            result.append('(');
        }

        for (int i = 1; i <= elementIndex; i++) {
            result.append(cosSequenceElementGenerator.getElementByIndex(i))
                    .append('+')
                    .append(elementIndex - i + 1)
                    .append(i != elementIndex ? ')' : "");
        }

        return result.toString();
    }
}
