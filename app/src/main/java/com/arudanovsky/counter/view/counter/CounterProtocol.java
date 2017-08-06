package com.arudanovsky.counter.view.counter;

import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

/**
 * Created by arudanovskiy on 8/6/17.
 * Протокол взаимодействия между реализациями {@link CounterView} и {@link CounterPresenter}
 */

public interface CounterProtocol {
    interface CounterView extends IView {

    }
    interface CounterPresenter extends IPresenter {

    }
}
