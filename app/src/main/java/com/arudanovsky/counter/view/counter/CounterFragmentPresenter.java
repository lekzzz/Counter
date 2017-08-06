package com.arudanovsky.counter.view.counter;

import com.arudanovsky.counter.view.base.BasePresenter;
import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

/**
 * Created by arudanovskiy on 8/6/17.
 * Презентер для {@link CounterFragment}
 * Вычисления, отображение обрабатываются согласно протоколу {@link CounterProtocol}
 */

public class CounterFragmentPresenter extends BasePresenter implements CounterProtocol.CounterPresenter {
    private CounterProtocol.CounterView mView;

    /**
     * Описание в {@link IPresenter#subscribe()}
     */
    @Override
    public void subscribe() {

    }


    /**
     * Описание в {@link IPresenter#onCreate(IView)}
     */
    @Override
    public void onCreate(IView view) {
        mView = (CounterProtocol.CounterView) view;
    }
}
