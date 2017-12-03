package com.kezhevatov.math.service;

import com.kezhevatov.math.exception.CustomException;
import com.kezhevatov.math.exception.ErrorCode;
import com.kezhevatov.math.service.sequence.CosSequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Andrey Kezhevatov.
 * Date: 03.12.2017
 */
@Service
public class MathService {

    private final CosSequenceGenerator cosSequenceGenerator;

    @Value("${cosSequenceGenerator.maxValue}")
    private int maxValue;

    @Autowired
    public MathService(CosSequenceGenerator cosSequenceGenerator) {
        this.cosSequenceGenerator = cosSequenceGenerator;
    }

    public String getSequenceByIndex(int elementIndex) {
        checkElementIndex(elementIndex);
        return cosSequenceGenerator.getSequenceByIndex(elementIndex);
    }

    private void checkElementIndex(int elementIndex) {
        if (elementIndex < 1) {
            throw new CustomException(ErrorCode.ARGUMENT_NOT_VALID, "Min value = 1");
        }

        if (elementIndex > maxValue) {
            throw new CustomException(ErrorCode.ARGUMENT_NOT_VALID, "Max value = " + maxValue);
        }
    }
}
