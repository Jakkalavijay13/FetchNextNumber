package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.YourEntity;
import com.example.repositories.YourRepository;

@Service
public class FetchNextNumberService {
    @Autowired
    private YourRepository yourRepository;

    @Transactional
    public String fetchNextNumber(String categoryCode) {
        YourEntity entity = yourRepository.findByCategoryCode(categoryCode);

        int fetchedValue = (entity != null) ? entity.getValue() : 0;
        int nextNumber = calculateNextNumber(fetchedValue);

        // Simulate other processing by introducing a delay of 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (entity != null) {
            entity.setValue(nextNumber);
        } else {
            entity = new YourEntity();
            entity.setCategoryCode(categoryCode);
            entity.setValue(nextNumber);
        }

        yourRepository.save(entity);

        String result = "{\"OldValue\": " + fetchedValue + ", \"NewValue\": " + nextNumber + "}";
        return result;
    }

    private int calculateNextNumber(int currentValue) {
        int nextNumber = currentValue + 1;
        while (sumDigits(nextNumber) != 1) {
            nextNumber += 1;
        }
        return nextNumber;
    }

    private int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
