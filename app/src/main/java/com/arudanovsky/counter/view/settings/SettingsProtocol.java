package com.arudanovsky.counter.view.settings;

import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/7/17.
 * Протокол взаимодействия между фрагментом и презентером
 */

public interface SettingsProtocol {
    interface SettingsView extends IView {
        /**
         * Метод, предназначенный для отображения значений
         * @param maxValue максимально допустимое значение
         * @param step шаг инкрементации
         */
        void updateView(String maxValue, String step);
    }
    interface SettingsPresenter extends IPresenter {
        /**
         * Метод, для обработки событий по изменению текста в полях
         * @param type тип поля
         * @param value новое значение
         */
        void onTextChanged(SettingsFieldType type, String value);

        /**
         * Метод для обработки нажатий на кнопки
         * @param type тип кнопки
         */
        void onButtonClicked(SettingsFieldType type);
    }
}
