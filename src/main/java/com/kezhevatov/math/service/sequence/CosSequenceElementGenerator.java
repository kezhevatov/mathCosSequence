package com.kezhevatov.math.service.sequence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *  Sequence element generator
 *
 */
@Component
public class CosSequenceElementGenerator {
    private final List<String> elementsArray;
    private final ReadWriteLock lock;

    public CosSequenceElementGenerator() {
        elementsArray = new ArrayList<>();
        elementsArray.add("cos(1)");

        lock = new ReentrantReadWriteLock(true);
    }

    /**
     * Generate element by index
     *
     * @param elementIndex index of element
     * @return element. Example: cos(1-cos(2+cos(3)))
     */
    public String getElementByIndex(int elementIndex) {
        lock.readLock().lock();
        if (elementsArray.size() < elementIndex) {
            lock.readLock().unlock();
            lock.writeLock().lock();
            try {
                fillArray(elementIndex);
                lock.readLock().lock();
            } finally {
                lock.writeLock().unlock();
            }
        }

        try {
            return elementsArray.get(elementIndex - 1);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void fillArray(int elementIndex) {
        StringBuilder parenthesis = createStartedParenthesis();
        StringBuilder calculatedElement = new StringBuilder();
        calculatedElement.append(getLastElementValueWithoutRightParenthesis());

        for (int i = elementsArray.size() + 1; i <= elementIndex; i++) {
            calculatedElement.append(i % 2 != 0 ? "+" : "-").append("cos(").append(i);
            parenthesis.append(')');

            elementsArray.add(calculatedElement.toString() + parenthesis.toString());
        }
    }

    private String getLastElementValueWithoutRightParenthesis() {
        int arraySize = elementsArray.size();
        int lastElementLength = elementsArray.get(arraySize - 1).length();

        return elementsArray.get(arraySize - 1).substring(0, lastElementLength - arraySize);
    }

    private StringBuilder createStartedParenthesis() {
        StringBuilder parenthesis = new StringBuilder();
        for (int i = 0; i < elementsArray.size(); i++) {
            parenthesis.append(')');
        }

        return parenthesis;
    }
}
