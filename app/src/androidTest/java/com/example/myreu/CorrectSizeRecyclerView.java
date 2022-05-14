package com.example.myreu;


import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class CorrectSizeRecyclerView {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void displayAllTheMeetings() {

        onView(ViewMatchers.withId(R.id.main_recycler_view))
                .check(matches(hasChildCount(7)));

    }
}
