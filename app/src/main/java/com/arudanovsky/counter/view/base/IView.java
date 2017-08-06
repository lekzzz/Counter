package com.arudanovsky.counter.view.base;

/**
 * Created by arudanovskiy on 8/6/17.
 * Общий интерфейс для фрагментов
 */

public interface IView {
    /**
     * Метод для отображения ошибки, случившейся при каких-либо обстоятельствах,
     * которую можно показать пользователю.
     * Это может быть ошибка валидации, или ошибка при подгрузке данных.
     * @param message текст ошибки, которую увидит пользователь
     */
    void showError(String message);
}
