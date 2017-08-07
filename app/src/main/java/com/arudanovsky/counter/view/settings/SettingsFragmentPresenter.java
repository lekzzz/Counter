package com.arudanovsky.counter.view.settings;

import android.content.Context;

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
        switch (type) {
            case MAX_VALUE:
                mMaxValue = new BigDecimal(value);
                break;
            case INCREMENTATION_STEP:
                mIncrementationStep = new BigDecimal(value);
                break;
            default:
                break;
        }
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
        mPreferences.setCounterMaxValue(mMaxValue);
        mPreferences.setCounterIncrementationStep(mIncrementationStep);
    }
}
