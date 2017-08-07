package com.arudanovsky.counter.view.counter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arudanovsky.counter.R;
import com.arudanovsky.counter.view.MainActivityInterface;
import com.arudanovsky.counter.view.base.BaseFragment;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/6/17.
 * Фрагмент представляющий собой счетчик.
 * Вся логика вынесена в {@link CounterFragmentPresenter}
 * Взаимодействие с презентером описано в протоколе {@link CounterProtocol}
 */

public class CounterFragment extends BaseFragment<MainActivityInterface> implements CounterProtocol.CounterView {
    private CounterProtocol.CounterPresenter mPresenter;
    private TextView mCount;

    /**
     * Базовый метод для получения инстанса фрагмента
     * @return инстанс {@link CounterFragment}
     */
    public static CounterFragment newInstance() {
        return new CounterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CounterFragmentPresenter();
        mPresenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mListener != null)
            mListener.changeTitle("Counter");
        View view = inflater.inflate(R.layout.fragment_counter, container, false);
        mCount = (TextView) view.findViewById(R.id.counter);
        mCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onViewClicked();
            }
        });
        mPresenter.subscribe();
        return view;
    }

    /**
     * Описание в {@link IView#showError(String)}
     * @param message текст ошибки, которую увидит пользователь
     */
    @Override
    public void showError(String message) {
        //todo show error
    }

    /**
     * Описание в {@link CounterProtocol.CounterView#setupNumber(BigDecimal)}
     * @param number число для отображения
     */
    @Override
    public void setupNumber(BigDecimal number) {
        mCount.setText(number.toString());
    }
}
