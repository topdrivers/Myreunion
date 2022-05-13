package com.example.myreu;

import static android.view.View.AUTOFILL_HINT_NAME;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.myreu.matchers.detailsItemNameViewMatcher.childAtPosition;
import static com.example.myreu.matchers.detailsItemViewMatcher.withTitle;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMeetingTest {

    private static final String AUTOFILL_HINT_NAME = "Eclair";


    // FOR DATA
    private Context context;

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        this.context = InstrumentationRegistry.getTargetContext();
    }


    @BindView(R.id.activity_add_meeting_date)
    TextView date;
    private IdlingResource mIdlingResource;
    private IdlingResource mIdlingResource2;

    public static void setDate(int year, int monthOfYear, int dayOfMonth) {
        onView(withId(R.id.activity_add_meeting_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static void setTime(int timePickerLanchViewId, int hour,int minutes){
        onView(withId(timePickerLanchViewId)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minutes));
        onView(withId(android.R.id.button1)).perform(click());
    }


    @Test
    public void checkAddMeetingInRecyclerView() throws Exception {//perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        this.waitForNetworkCall();
        onView(ViewMatchers.withId(R.id.main_activity_add_button)).perform(click());
        Thread.sleep( 3000);
        setDate(2020, 9, 25);
        setTime(R.id.activity_add_meeting_begin,3,10);
        Thread.sleep( 3000);
        setTime(R.id.activity_add_meeting_end,5,8);

       onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        //onView(withClassName(Matchers.equalTo(Spinner.class.getName()))).perform(Spinner.g);

/*
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatSpinner.perform(scrollTo(), click());

 */

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(withClassName(is("androidx.appcompat.widget.DropDownListView")))
                .atPosition(6);
        appCompatTextView.perform(click());

        Thread.sleep( 3000);

        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.participant_autoCompleteTextView),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText("ab@gmail.com"), closeSoftKeyboard());
/*
        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.addParticipant_button), withText("Ajouter"), isDisplayed()));

 */
        //materialButton7.perform(scrollTo(), click());
        onView(ViewMatchers.withId(R.id.addParticipant_button)).perform(click());

        Thread.sleep( 3000);
        onView(ViewMatchers.withId(R.id.activity_add_meeting_button)).perform(click());

        onView(allOf(withId(R.id.activity_main_item_name), withText("Text1"), isDisplayed()));
        onView(allOf(withId(R.id.activity_main_participants), withText("Text2"), isDisplayed()));
        //onView(allOf(withId(R.id.activity_main_room), withText("Text2"), isDisplayed()));
        Thread.sleep(3000);



        //onView(ViewMatchers.withId(R.id.activity_details_item_name)).check(matches(withTitle(context.getString((R.string.social_media_name_test)))));
        //onView(ViewMatchers.withId(R.id.activity_details_item_name)).check(matches())
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
