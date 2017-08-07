package com.arudanovsky.counter.view.base;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

/**
 * Created by arudanovskiy on 8/6/17.
 * Базовый фрагмент для работы с Activity
 * Для корректной работы, все активити, к которым будет прикрепляться
 * экземпляр этого класса, должны имплементировать {@link BaseActivityInterface}
 */

public abstract class BaseFragment<T extends BaseActivityInterface> extends Fragment implements IView{
    protected T mListener;

    /**
     * При вызове этого метода, сохраняем ссылку на родительскую активити
     * @param context контекст родительской активити. Она обязана имлпементировать {@link BaseActivityInterface}
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivityInterface) {
            mListener = (T) context;
        } else
            throw new ClassCastException(context.toString() + " must implement interface");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Описание в {@link IView#showError(String)}
     * @param message текст ошибки, которую увидит пользователь
     */
    @Override
    public void showError(String message) {

    }

    /**
     * Описание в {@link IView#showShackbar(String)}
     * @param message текст сообщения
     */
    @Override
    public void showShackbar(String message) {
        if (getView() != null)
            Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }
}
