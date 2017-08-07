package com.arudanovsky.counter;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.arudanovsky.counter.TestMatchers.hasTextInputLayoutErrorText;
import static com.arudanovsky.counter.TestMatchers.toolbarTitleMatches;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by arudanovskiy on 8/7/17.
 * Для тестирования общего взаимодействия активити и фрагментов
 */

@RunWith(AndroidJUnit4.class)
public class CompositeFragmentTest extends BaseFragmentTest {
    @Test
    public void activityTest() {
        onView(withId(R.id.counter)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(toolbarTitleMatches(activityRule.getActivity().getString(R.string.counter))));
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_settings));
        onView(withId(R.id.toolbar)).check(matches(toolbarTitleMatches(activityRule.getActivity().getString(R.string.settings))));
        onView(withId(R.id.settings_incr_step_et)).check(matches(isDisplayed()));
        onView(withId(R.id.settings_incr_step_et)).perform(clearText());
        onView(withId(R.id.settings_max_val_et)).perform(clearText());
        BigDecimal stepBefore = new AppPreferences(activityRule.getActivity()).getCounterIncrementationStep();
        BigDecimal maxValueBefore = new AppPreferences(activityRule.getActivity()).getCounterMaxValue();
        onView(withId(R.id.settings_incr_step_til)).check(matches(hasTextInputLayoutErrorText(activityRule.getActivity().getString(R.string.field_error))));
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.nav_counter));
        onView(withId(R.id.toolbar)).check(matches(toolbarTitleMatches(activityRule.getActivity().getString(R.string.counter))));
        onView(withText(R.string.not_valid_values)).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.counter)).check(matches(isDisplayed()));

        BigDecimal stepAfter = new AppPreferences(activityRule.getActivity()).getCounterIncrementationStep();
        BigDecimal maxValueAfter = new AppPreferences(activityRule.getActivity()).getCounterMaxValue();
        Assert.assertEquals(stepAfter, stepBefore);
        Assert.assertEquals(maxValueAfter, maxValueBefore);
    }
}
