package com.arudanovsky.counter;

import android.support.test.runner.AndroidJUnit4;
import com.arudanovsky.counter.view.counter.CounterFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by arudanovskiy on 8/7/17.
 * UI тесты для CounterFragment
 */

@RunWith(AndroidJUnit4.class)
public class CounterFragmentTest extends BaseFragmentTest{

    @Test
    public void fragmentTest() {
        activityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startFragment(CounterFragment.newInstance());
            }
        });

        AppPreferences preferences = new AppPreferences(activityRule.getActivity());

        onView(withId(R.id.counter)).check(matches(isDisplayed()));
        onView(withId(R.id.counter)).check(matches(withText(preferences.getCounterValue().toString())));
        onView(withId(R.id.counter)).perform(click());
        if (preferences.getCounterValue().add(preferences.getCounterIncrementationStep()).compareTo(preferences.getCounterMaxValue()) > 0)
            onView(withId(R.id.counter)).check(matches(withText(BigDecimal.ZERO.toString())));
        else
            onView(withId(R.id.counter)).check(matches(withText(preferences.getCounterValue().add(preferences.getCounterIncrementationStep()).toString())));

        if (preferences.getCounterIncrementationStep().compareTo(BigDecimal.ZERO) > 0) {
            if (preferences.getCounterValue().add(preferences.getCounterIncrementationStep()).compareTo(preferences.getCounterMaxValue()) <= 0) {
                do {
                    onView(withId(R.id.counter)).perform(click());
                } while (preferences.getCounterMaxValue().subtract(preferences.getCounterValue()).compareTo(preferences.getCounterIncrementationStep()) < 0);
            }
            onView(withId(R.id.counter)).perform(click());
            onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(activityRule.getActivity().getString(R.string.counter_reach_max_value))))
                    .check(matches(isDisplayed()));
            onView(withId(R.id.counter)).check(matches(withText(BigDecimal.ZERO.toString())));
        } else {
            onView(withId(R.id.counter)).perform(click());
            onView(withId(R.id.counter)).check(matches(withText(preferences.getCounterValue().toString())));
        }
    }
}
