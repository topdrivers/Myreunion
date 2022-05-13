package com.example.myreu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static com.example.myreu.matchers.detailsItemViewMatcher.withTitle;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DetailsActivityTest {


    // FOR DATA
    private Context context;

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
    }



    private IdlingResource mIdlingResource;
    private IdlingResource mIdlingResource2;


    @Test
    public void checkIfRecyclerViewIsNotEmpty() throws Exception {
        this.waitForNetworkCall();
        onView(ViewMatchers.withId(R.id.main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep( 3000);
        onView(ViewMatchers.withId(R.id.activity_details_item_name)).check(matches(withTitle(context.getString((R.string.social_media_name_test)))));
    }





    private void waitForNetworkCall(){
        this.mIdlingResource = mActivityRule.getActivity().getEspressoIdlingResourceForMainFragment();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }



/*
    @Test
    public void checkBottomNavigationButtonTitle(){
        onView(ViewMatchers.withId(R.id.action_android)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_android))));
        onView(ViewMatchers.withId(R.id.action_landscape)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_landscape))));
        onView(ViewMatchers.withId(R.id.action_logo)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_logos))));
    }

 */



}
