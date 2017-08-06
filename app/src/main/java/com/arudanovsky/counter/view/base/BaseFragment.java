package com.arudanovsky.counter.view.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by arudanovskiy on 8/6/17.
 * Базовый фрагмент для работы с Activity
 * Для корректной работы, все активити, к которым будет прикрепляться
 * экземпляр этого класса, должны имплементировать {@link BaseActivityInterface}
 */

public abstract class BaseFragment<T extends BaseActivityInterface> extends Fragment {
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
}
