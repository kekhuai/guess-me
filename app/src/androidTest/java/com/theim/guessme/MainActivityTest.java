package com.theim.guessme;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Field;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.RootMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;

    private String userInputNumber;

    @Before
    public void init() {
        mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testInputTextIsEqualAsWeTypeIn() {
        userInputNumber = "1234";
        onView(withId(R.id.guess_input_text)).check(matches(withText(userInputNumber)));
    }

    @Test
    public void testCorrectGuess() throws NoSuchFieldException, IllegalAccessException {
        setupAndPerformButtonClicked("1234");
        onView(withText(String.format(mainActivity.getResources().getString(R.string.correct_message), 1234))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testUserInputNumberIsTooLower() throws NoSuchFieldException, IllegalAccessException {
        setupAndPerformButtonClicked("1000");
        onView(withText(String.format(mainActivity.getResources().getString(R.string.incorrect_message), "น้อย"))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testUserInputNumberIsTooGreater() throws NoSuchFieldException, IllegalAccessException {
        setupAndPerformButtonClicked("2000");
        onView(withText(String.format(mainActivity.getResources().getString(R.string.incorrect_message), "มาก"))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    private void setupAndPerformButtonClicked(String userInput) throws NoSuchFieldException, IllegalAccessException {
        Field answer = mainActivity.getClass().getDeclaredField("answer");
        answer.setAccessible(true);
        answer.set(mainActivity, 1234);
        onView(withId(R.id.guess_input_text)).perform(clearText(), typeText(userInput));
        onView(withId(R.id.guess_button)).perform(click());
    }
}
