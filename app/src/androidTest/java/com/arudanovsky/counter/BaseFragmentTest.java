package com.arudanovsky.counter;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.arudanovsky.counter.view.MainActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * Created by arudanovskiy on 8/7/17.
 * Базовый класс для тестирования фрагментов
 */

@RunWith(AndroidJUnit4.class)
public class BaseFragmentTest {
    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule<>(
            MainActivity.class);

    /**
     * Метод для отображения фрагмента и дальнейшего его тестирования
     * @param fragment инстанс фрагмента
     */
    void startFragment(Fragment fragment) {
        if (fragment != null) {
            MainActivity activity = (MainActivity) activityRule.getActivity();
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_container, fragment, "counter");
            transaction.commit();
        }
    }
}
