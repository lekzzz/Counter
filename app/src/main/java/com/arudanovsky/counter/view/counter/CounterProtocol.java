package com.arudanovsky.counter.view.counter;

import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

/**
 * Created by arudanovskiy on 8/6/17.
 * Протокол взаимодействия между реализациями {@link CounterView} и {@link CounterPresenter}
 */

interface CounterProtocol {
    interface CounterView extends IView {
        /**
         * Метод для отображения числа
         * @param number число для отображения
         */
        void setupNumber(String number);
    }
    interface CounterPresenter extends IPresenter {
        /**
         * Метод для обработки события нажатия
         */
        void onViewClicked();
    }
}
