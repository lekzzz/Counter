package com.arudanovsky.counter.view.counter;

import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/6/17.
 * Протокол взаимодействия между реализациями {@link CounterView} и {@link CounterPresenter}
 */

public interface CounterProtocol {
    interface CounterView extends IView {
        /**
         * Метод для отображения числа
         * @param number число для отображения
         */
        void setupNumber(BigDecimal number);
    }
    interface CounterPresenter extends IPresenter {
        /**
         * Метод для обработки события нажатия
         */
        void onViewClicked();
    }
}
