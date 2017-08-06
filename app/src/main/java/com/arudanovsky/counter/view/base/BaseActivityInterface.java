package com.arudanovsky.counter.view.base;

/**
 * Created by arudanovskiy on 8/6/17.
 * Базовый интерфейс для активити.
 * Предназначен для работы с фрагментами наследующимеся от {@link BaseFragment}
 */

public interface BaseActivityInterface {
    /**
     * Метод предназначенный для смены титула у активити, которая имплементирует данный интерфейс.
     * @param title нужное название экрана
     */
    void changeTitle(String title);
}
