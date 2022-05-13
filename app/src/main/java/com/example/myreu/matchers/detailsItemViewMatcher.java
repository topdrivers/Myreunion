package com.example.myreu.matchers;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class detailsItemViewMatcher {

    public static Matcher<View> withTitle(final String titleTested) {
        return new BoundedMatcher<View, TextView>(TextView.class) {

            private boolean triedMatching;
            private String title;

            @Override
            public void describeTo(Description description) {
                if (triedMatching) {
                    description.appendText("with title: " + titleTested);
                    description.appendText("But was: " + String.valueOf(title));
                }
            }

            @Override
            protected boolean matchesSafely(TextView item) {
                this.triedMatching = true;
                this.title = item.getText().toString();
                return title.equals(titleTested);
            }
        };
    }
}
