package com.arudanovsky.counter.common;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/6/17.
 * Класс для работы с числами
 */

public class NumberUtils {
    /**
     * Метод, инкрементирующий входящее значение на определенный шаг
     * @param value значение которое нужно увеличить
     * @param step шаг, на который нужно увеличить
     * @return {@code value} после инкрементации
     */
    public static BigDecimal increment(BigDecimal value, BigDecimal step) {
        return value.add(step);
    }

    /**
     * Метод аналогичный {@link NumberUtils#increment(BigDecimal, BigDecimal)},
     * но имеющий дополнительный параметр максимального значения {@code maxValue}.
     * Если значение {@code value} после инкрементации больше максимального {@code maxValue},
     * то оно приравнивается 0.
     * @param value значение которое нужно увеличить
     * @param step шаг, на который нужно увеличить
     * @param maxValue максимальное значение
     * @return {@code value} после инкрементации
     */
    public static BigDecimal increment(BigDecimal value, BigDecimal step, BigDecimal maxValue) {
        BigDecimal total = value.add(step);
        if (total.compareTo(maxValue) > 0)
            total = BigDecimal.ZERO;
        return total;
    }
}
