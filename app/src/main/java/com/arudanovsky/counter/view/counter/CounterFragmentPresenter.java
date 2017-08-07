package com.arudanovsky.counter.view.counter;

import android.content.Context;

import com.arudanovsky.counter.AppPreferences;
import com.arudanovsky.counter.R;
import com.arudanovsky.counter.common.NumberUtils;
import com.arudanovsky.counter.view.base.BasePresenter;
import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/6/17.
 * Презентер для {@link CounterFragment}
 * Вычисления, отображение обрабатываются согласно протоколу {@link CounterProtocol}
 */

class CounterFragmentPresenter extends BasePresenter implements CounterProtocol.CounterPresenter {
    private CounterProtocol.CounterView mView;
    private BigDecimal mIncrementStep, mCount, mMaxValue;
    private AppPreferences mPreferences;
    private Context mContext;

    CounterFragmentPresenter (Context context) {
        mContext = context;
        mPreferences = new AppPreferences(context);
    }

    /**
     * Общее описание в {@link IPresenter#subscribe()}
     * Частный случай:
     * Подготавливаемся к работе.
     * Подгружаем необходимые для работы данные
     */
    @Override
    public void subscribe() {
        mIncrementStep = mPreferences.getCounterIncrementationStep();
        mCount = mPreferences.getCounterValue();
        mMaxValue = mPreferences.getCounterMaxValue();
        mView.setupNumber(mCount.toString());
    }

    /**
     * Общее описание {@link CounterProtocol.CounterPresenter#onViewClicked()}
     * Частный случай:
     * При нажатии на счетчик мы инкрементируем имеющееся значение {@link CounterFragmentPresenter#mCount}
     * на выставленный шаг {@link CounterFragmentPresenter#mIncrementStep}
     * В случае, если итоговое значение больше максимально возможного значения {@link CounterFragmentPresenter#mMaxValue},
     * то значение сбрасывается до 0
     */
    @Override
    public void onViewClicked() {
        mCount = NumberUtils.increment(mCount, mIncrementStep, mMaxValue);
        if (mCount.compareTo(BigDecimal.ZERO) == 0)
            mView.showShackbar(mContext.getString(R.string.counter_reach_max_value));
        mView.setupNumber(mCount.toString());
    }

    /**
     * Описание в {@link IPresenter#onCreate(IView)}
     */
    @Override
    public void onCreate(IView view) {
        mView = (CounterProtocol.CounterView) view;
    }

    /**
     * Общее описание в {@link IPresenter#unsubscribe()}
     * При описывании мы сохраняем данные.
     */
    @Override
    public void unsubscribe() {
        mPreferences.setCounterValue(mCount);
    }
}
