package com.example.myreu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;


import android.content.Context;



import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MainActivityTests {

    // FOR DATA
    private Context context;

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
    }
/*
    @Test
    public void checkBottomNavigationButtonSelection(){
        onView(ViewMatchers.withId(R.id.action_android)).check(matches(withIsChecked(true)));
        onView(ViewMatchers.withId(R.id.action_landscape)).check(matches(withIsChecked(false)));
        onView(ViewMatchers.withId(R.id.action_logo)).check(matches(withIsChecked(false)));
    }

    @Test
    public void checkBottomNavigationButtonTitle(){
        onView(ViewMatchers.withId(R.id.action_android)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_android))));
        onView(ViewMatchers.withId(R.id.action_landscape)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_landscape))));
        onView(ViewMatchers.withId(R.id.action_logo)).check(matches(withTitle(context.getString(R.string.bottom_navigation_menu_logos))));
    }

 */

    private IdlingResource mIdlingResource;


    @Test
    public void checkIfRecyclerViewIsNotEmpty() throws Exception {
        this.waitForNetworkCall();
        onView(ViewMatchers.withId(R.id.main_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }



    private void waitForNetworkCall(){
        this.mIdlingResource = mActivityRule.getActivity().getEspressoIdlingResourceForMainFragment();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }
}
