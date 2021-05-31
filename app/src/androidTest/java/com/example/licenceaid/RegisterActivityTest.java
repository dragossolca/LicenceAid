package com.example.licenceaid;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void activityLaunch(){
        onView(withId(R.id.registerBtn)).perform(click());
    }

    @Test
    public void iterateSpinnerItems() {
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.user_types_array);
        // Iterate through the spinner array of items.
        int size = myArray.length;
        for (int i=0; i<size; i++) {
            onView(withId(R.id.userType)).perform(click());
            onData(is(myArray[i])).perform(click());

        }
    }


}

