package com.arudanovsky.counter.view.settings;

import android.app.Presentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arudanovsky.counter.R;
import com.arudanovsky.counter.view.MainActivityInterface;
import com.arudanovsky.counter.view.base.BaseFragment;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/7/17.
 * Фрагмент для отображения настроек приложения.
 * Вся логика вынесена в {@link SettingsFragmentPresenter}
 * Протокол взаимодействия описан в {@link SettingsProtocol}
 */

public class SettingsFragment extends BaseFragment<MainActivityInterface> implements SettingsProtocol.SettingsView {
    private SettingsProtocol.SettingsPresenter mPresenter;
    private EditText mMaxValue, mIncrementStep;
    private TextInputLayout mMaxValueLayout, mIncrementStepLayout;

    /**
     * Базовый метод для получения инстанса фрагмента
     * @return инстанс {@link SettingsFragment}
     */
    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SettingsFragmentPresenter(getContext());
        mPresenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mListener != null) mListener.changeTitle(getString(R.string.settings));
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        mMaxValue = (EditText) view.findViewById(R.id.settings_max_val_et);
        mIncrementStep = (EditText) view.findViewById(R.id.settings_incr_step_et);
        Button resetButton = (Button) view.findViewById(R.id.settings_reset_button);

        mMaxValueLayout = (TextInputLayout) view.findViewById(R.id.settings_max_val_til);
        mIncrementStepLayout = (TextInputLayout) view.findViewById(R.id.settings_incr_step_til);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onButtonClicked(SettingsFieldType.RESET);
            }
        });

        mPresenter.subscribe();

        mMaxValue.addTextChangedListener(new FieldWatcher(SettingsFieldType.MAX_VALUE));
        mIncrementStep.addTextChangedListener(new FieldWatcher(SettingsFieldType.INCREMENTATION_STEP));

        return view;
    }

    @Override
    public void showFieldError(SettingsFieldType type, String string) {
        switch (type) {
            case MAX_VALUE:
                mMaxValueLayout.setError(string);
                break;
            case INCREMENTATION_STEP:
                mIncrementStepLayout.setError(string);
                break;
            default:
                break;
        }
    }

    /**
     * Описание в {@link SettingsProtocol.SettingsView#updateView(String, String)}
     * @param maxValue максимально допустимое значение
     * @param step шаг инкрементации
     */
    @Override
    public void updateView(String maxValue, String step) {
        mMaxValue.setText(maxValue);
        mIncrementStep.setText(step);
    }

    @Override
    public void onDetach() {
        mPresenter.unsubscribe();
        super.onDetach();
    }

    /**
     * Общая реализация для отслеживания изменения в EditText
     */
    private class FieldWatcher implements TextWatcher {
        private SettingsFieldType mFieldType;

        FieldWatcher(SettingsFieldType type) {
            mFieldType = type;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mPresenter.onTextChanged(mFieldType, s.toString());
        }
    }
}
