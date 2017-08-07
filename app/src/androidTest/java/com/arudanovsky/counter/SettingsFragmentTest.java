package com.arudanovsky.counter;

import android.support.test.runner.AndroidJUnit4;
import android.text.InputType;
import android.view.KeyEvent;
import com.arudanovsky.counter.view.settings.SettingsFragment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by arudanovskiy on 8/7/17.
 * UI тесты для SettingsFragment
 */

@RunWith(AndroidJUnit4.class)
public class SettingsFragmentTest extends BaseFragmentTest {

    @Test
    public void fragmentTest() {
        activityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startFragment(SettingsFragment.newInstance());
            }
        });

        onView(withId(R.id.settings_incr_step_til)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_incr_step_et)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_incr_step_til)).check(matches(TestMatchers.hasTextInputLayoutHintText(activityRule.getActivity().getString(R.string.incrementation_step))));
        onView(withId(R.id.settings_incr_step_et)).check(matches(withInputType(InputType.TYPE_CLASS_NUMBER)));
        onView(withId(R.id.settings_incr_step_et)).perform(clearText());
        onView(withId(R.id.settings_incr_step_et)).check(matches(withText("")));
        onView(withId(R.id.settings_incr_step_et)).perform(typeText("some non-number text"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.settings_incr_step_et)).perform(closeSoftKeyboard());
        onView(withId(R.id.settings_incr_step_et)).check(matches(withText("")));
        onView(withId(R.id.settings_incr_step_et)).perform(typeText("some non-number text with number 10"));
        onView(withId(R.id.settings_incr_step_et)).check(matches(withText("10")));
        onView(withId(R.id.settings_incr_step_et)).perform(typeText("20.,"));
        onView(withId(R.id.settings_incr_step_et)).check(matches(withText("1020")));
        onView(withId(R.id.settings_incr_step_et)).perform(clearText());
        onView(withId(R.id.settings_incr_step_et)).check(matches(withText("")));
        onView(withId(R.id.settings_incr_step_til)).check(matches(TestMatchers.hasTextInputLayoutErrorText(activityRule.getActivity().getString(R.string.field_error))));

        onView(withId(R.id.settings_max_val_til)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_max_val_et)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_max_val_til)).check(matches(TestMatchers.hasTextInputLayoutHintText(activityRule.getActivity().getString(R.string.max_value))));
        onView(withId(R.id.settings_max_val_et)).check(matches(withInputType(InputType.TYPE_CLASS_NUMBER)));
        onView(withId(R.id.settings_max_val_et)).perform(clearText());
        onView(withId(R.id.settings_max_val_et)).check(matches(withText("")));
        onView(withId(R.id.settings_max_val_et)).perform(typeText("some non-number text"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.settings_max_val_et)).perform(closeSoftKeyboard());
        onView(withId(R.id.settings_max_val_et)).check(matches(withText("")));
        onView(withId(R.id.settings_max_val_et)).perform(typeText("some non-number text with number 10"));
        onView(withId(R.id.settings_max_val_et)).check(matches(withText("10")));
        onView(withId(R.id.settings_max_val_et)).perform(typeText("20.,"));
        onView(withId(R.id.settings_max_val_et)).check(matches(withText("1020")));
        onView(withId(R.id.settings_max_val_et)).perform(clearText());
        onView(withId(R.id.settings_max_val_et)).check(matches(withText("")));
        onView(withId(R.id.settings_max_val_til)).check(matches(TestMatchers.hasTextInputLayoutErrorText(activityRule.getActivity().getString(R.string.field_error))));

        new AppPreferences(activityRule.getActivity()).setCounterValue(BigDecimal.ONE);
        onView(withId(R.id.settings_reset_button)).check(matches(isDisplayed()));
        Assert.assertEquals(1, new AppPreferences(activityRule.getActivity()).getCounterValue().compareTo(BigDecimal.ZERO));
            onView(withId(R.id.settings_reset_button)).perform(click());
            onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(activityRule.getActivity().getString(R.string.counter_reset))))
                    .check(matches(isDisplayed()));
        Assert.assertEquals(0, new AppPreferences(activityRule.getActivity()).getCounterValue().compareTo(BigDecimal.ZERO));
    }
}
