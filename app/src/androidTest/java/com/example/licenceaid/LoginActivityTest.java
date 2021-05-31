package com.example.licenceaid;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.EnumSet.allOf;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    private LoginActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.loginBtn);

        assertNotNull(view);
    }

    @Test
    public void testInput(){
        onView(withId(R.id.emailLogin)).perform(typeText(""));
        onView(withId(R.id.passwordLogin)).perform(typeText(""));
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.emailLogin)).check(matches(hasErrorText("Email is Required.")));

    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}