package com.arudanovsky.counter;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by arudanovskiy on 8/7/17.
 * Специальные Matcher'ы для тестирования TextInputLayout
 */

class TestMatchers {
    static Matcher<View> hasTextInputLayoutHintText(final String expectedText) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }
                CharSequence charSequence = ((TextInputLayout) view).getHint();
                if (charSequence == null) {
                    return false;
                }
                String hint = charSequence.toString();
                return expectedText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    static Matcher<View> hasTextInputLayoutErrorText(final String expectedText) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }
                CharSequence charSequence = ((TextInputLayout) view).getError();
                if (charSequence == null) {
                    return false;
                }
                String error = charSequence.toString();
                return expectedText.equals(error);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    static Matcher<View> toolbarTitleMatches(final String expectedText) {
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof Toolbar)) {
                    return false;
                }
                CharSequence charSequence = ((Toolbar) view).getTitle();
                if (charSequence == null) {
                    return false;
                }
                String title = charSequence.toString();
                return expectedText.equals(title);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
