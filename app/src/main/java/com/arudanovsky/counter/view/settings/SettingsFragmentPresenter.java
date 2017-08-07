package com.arudanovsky.counter.view.settings;

import android.content.Context;
import android.text.TextUtils;

import com.arudanovsky.counter.AppPreferences;
import com.arudanovsky.counter.R;
import com.arudanovsky.counter.view.base.BasePresenter;
import com.arudanovsky.counter.view.base.IPresenter;
import com.arudanovsky.counter.view.base.IView;

import java.math.BigDecimal;

/**
 * Created by arudanovskiy on 8/7/17.
 * Презентер для обработки событий во фрагменте {@link SettingsFragment}
 */

class SettingsFragmentPresenter extends BasePresenter implements SettingsProtocol.SettingsPresenter {
    private SettingsProtocol.SettingsView mView;
    private BigDecimal mMaxValue, mIncrementationStep;
    private AppPreferences mPreferences;
    private Context mContext;
    private boolean mMaxValueValid = true;
    private boolean mStepValueValid = true;

    SettingsFragmentPresenter(Context context) {
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
        mMaxValue = mPreferences.getCounterMaxValue();
        mIncrementationStep = mPreferences.getCounterIncrementationStep();
        mView.updateView(mMaxValue.toString(), mIncrementationStep.toString());
    }

    /**
     * Описание в {@link IPresenter#onCreate(IView)}
     */
    @Override
    public void onCreate(IView view) {
        mView = (SettingsProtocol.SettingsView) view;
    }

    /**
     * Общее описание {@link SettingsProtocol.SettingsPresenter#onTextChanged(SettingsFieldType, String)}
     * Частный случай:
     * Отлавливаем изменения в EditText'ах, простовляем соотв. значения
     * @param type тип поля
     * @param value значение
     */
    @Override
    public void onTextChanged(SettingsFieldType type, String value) {
        String errorText = null;
        switch (type) {
            case MAX_VALUE:
                mMaxValueValid = isValid(value);
                if (mMaxValueValid) {
                    mMaxValue = new BigDecimal(value);
                } else {
                    errorText = mContext.getString(R.string.field_error);
                }
                break;
            case INCREMENTATION_STEP:
                mStepValueValid = isValid(value);
                if (mStepValueValid) {
                    mIncrementationStep = new BigDecimal(value);
                } else {
                    errorText = mContext.getString(R.string.field_error);
                }
                break;
            default:
                break;
        }
        mView.showFieldError(type, errorText);
    }

    /**
     * Метод проверяет, валидный ли введенный текст
     * @param value текст, который нужно проверить
     * @return решение, валидный он или нет
     */
    private static boolean isValid(String value) {
        boolean valid = true;
        if (TextUtils.isEmpty(value))
            valid = false;
        return valid;
    }

    /**
     * Общее описание {@link SettingsProtocol.SettingsPresenter#onButtonClicked(SettingsFieldType)}
     * Частный случай:
     * При нажатии на кнопку мы сбрасываем значение счетчика до 0
     * @param type тип кнопки
     */
    @Override
    public void onButtonClicked(SettingsFieldType type) {
        switch (type) {
            case RESET:
                mPreferences.setCounterValue(BigDecimal.ZERO);
                mView.showShackbar(mContext.getString(R.string.counter_reset));
                break;
            default:
                break;
        }
    }

    /**
     * Общее описание в {@link IPresenter#unsubscribe()}
     * При описывании мы сохраняем данные.
     */
    @Override
    public void unsubscribe() {
        if (mMaxValueValid)
            mPreferences.setCounterMaxValue(mMaxValue);
        if (mStepValueValid)
            mPreferences.setCounterIncrementationStep(mIncrementationStep);

        if (!mMaxValueValid || !mStepValueValid)
            mView.showError(mContext.getString(R.string.not_valid_values));
    }
}
