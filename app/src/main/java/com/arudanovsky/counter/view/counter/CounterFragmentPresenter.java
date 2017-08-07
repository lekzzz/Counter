package com.arudanovsky.counter.view.counter;

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

public class CounterFragmentPresenter extends BasePresenter implements CounterProtocol.CounterPresenter {
    private CounterProtocol.CounterView mView;
    private BigDecimal mIncrementStep, mCount, mMaxValue;

    /**
     * Общее описание в {@link IPresenter#subscribe()}
     * Частный случай:
     * Подготавливаемся к работе.
     * Подгружаем необходимые для работы данные
     */
    @Override
    public void subscribe() {
        mIncrementStep = BigDecimal.ONE;
        mCount = BigDecimal.ZERO;
        mMaxValue = BigDecimal.TEN;
        mView.setupNumber(mCount);
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
        mView.setupNumber(mCount);
    }

    /**
     * Описание в {@link IPresenter#onCreate(IView)}
     */
    @Override
    public void onCreate(IView view) {
        mView = (CounterProtocol.CounterView) view;
    }
}
