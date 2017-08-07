package com.arudanovsky.counter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/7/17.
 * Настройки приложения.
 * Тут хранятся необходимые значения.
 */

public class AppPreferences {
    private static final String COUNTER_MAX_VALUE = BigDecimal.TEN.toString();
    private static final String COUNTER_INCREMENTATION_STEP = BigDecimal.ONE.toString();
    private static final String COUNTER_VALUE = BigDecimal.ZERO.toString();

    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName();

    private SharedPreferences mSharedPrefs;

    /**
     * Инициализация класса настроек
     * @param context контекст
     */
    public AppPreferences(Context context) {
        this.mSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
    }

    /**
     * Метод для получения максимального значения счетчика
     * @return максимальное значение счетчика
     */
    public BigDecimal getCounterMaxValue() {
        return new BigDecimal(mSharedPrefs.getString(COUNTER_MAX_VALUE, BigDecimal.TEN.toString()));
    }

    /**
     * Устанавливает максимальное значение для счетчика
     * @param maxValue максимальное значение для счетчика
     */
    public void setCounterMaxValue(BigDecimal maxValue) {
        mSharedPrefs.edit().putString(COUNTER_MAX_VALUE, maxValue.toString()).apply();
    }

    /**
     * Метод для получения шага инремента
     * @return размер шага инкрементации
     */
    public BigDecimal getCounterIncrementationStep() {
        return new BigDecimal(mSharedPrefs.getString(COUNTER_INCREMENTATION_STEP, BigDecimal.ONE.toString()));
    }

    /**
     * Устанавливает шаг инкрементации
     * @param step шаг инкрементации
     */
    public void setCounterIncrementationStep(BigDecimal step) {
        mSharedPrefs.edit().putString(COUNTER_INCREMENTATION_STEP, step.toString()).apply();
    }

    /**
     * Метод для получения сохраненное значения счетчика
     * @return сохраненное значение счетчика
     */
    public BigDecimal getCounterValue() {
        return new BigDecimal(mSharedPrefs.getString(COUNTER_VALUE, BigDecimal.ZERO.toString()));
    }

    /**
     * Сохраняет текущее значение для счетчика
     * @param value текущее значение для счетчика
     */
    public void setCounterValue(BigDecimal value) {
        mSharedPrefs.edit().putString(COUNTER_VALUE, value.toString()).apply();
    }
}
