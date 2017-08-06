package com.arudanovsky.counter.view.base;

/**
 * Created by arudanovskiy on 8/6/17.
 * Базовый презентер.
 * Абстрактный класс для работы с презентерами.
 */

public abstract class BasePresenter implements IPresenter {

    /**
     * Описание в {@link IPresenter#unsubscribe()}
     */
    @Override
    public void unsubscribe() {

    }

    /**
     * Описание в {@link IPresenter#handleError(Throwable)}
     */
    @Override
    public void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
