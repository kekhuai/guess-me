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

public class GuessMeTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;

    private String userInputNumber;

    @Before
    public void init() throws NoSuchFieldException, IllegalAccessException {
        mainActivity = mainActivityActivityTestRule.getActivity();
        Field answer = mainActivity.getClass().getDeclaredField("answer");
        answer.setAccessible(true);
        answer.set(mainActivity, 1234);
    }

    @Test
    public void testFunctional() {
        // user เปิดแอป GuessMe ขึ้นมา จากนั้นกรอกตัวเลข 1000 ลงไป
        userInputNumber = "1000";
        onView(withId(R.id.guess_input_text)).perform(clearText(), typeText(userInputNumber));
        onView(withId(R.id.guess_input_text)).check(matches(withText(userInputNumber)));

        // user กดที่ปุ่ม Guess
        onView(withId(R.id.guess_button)).perform(click());

        // มี Toast message แจ้งว่าเลขที่กรอกนั้นน้อยเกินไป
        onView(withText(String.format(mainActivity.getResources().getString(R.string.incorrect_message), "น้อย"))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
        waitForToastToDisappear();

        // user กรอกตัวเลขลงไปใหม่ ครั้งนี้กรอกไปเป็น 2000
        userInputNumber = "2000";
        onView(withId(R.id.guess_input_text)).perform(clearText(), typeText(userInputNumber));
        onView(withId(R.id.guess_input_text)).check(matches(withText(userInputNumber)));

        // user กดที่ปุ่ม Guess
        onView(withId(R.id.guess_button)).perform(click());

        // มี Toast message แจ้งว่าเลขที่กรอกนั้นมากเกินไป
        onView(withText(String.format(mainActivity.getResources().getString(R.string.incorrect_message), "มาก"))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
        waitForToastToDisappear();

        // user ลองกรอกใหม่ คราวนี้กรอกเลข 1234 ลงไป
        userInputNumber = "1234";
        onView(withId(R.id.guess_input_text)).perform(clearText(), typeText(userInputNumber));
        onView(withId(R.id.guess_input_text)).check(matches(withText(userInputNumber)));

        // user กดที่ปุ่ม Guess
        onView(withId(R.id.guess_button)).perform(click());

        // ครั้งนี้ user ทายถูก มี Toast message แจ้งว่าคุณทายได้ถูกต้องแล้วและพร้อมเริ่มเกมใหม่
        onView(withText(String.format(mainActivity.getResources().getString(R.string.correct_message), Integer.parseInt(userInputNumber)))).inRoot(withDecorView(not(mainActivity.getWindow().getDecorView()))).check(matches(isDisplayed()));
        waitForToastToDisappear();
    }

    private void waitForToastToDisappear() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
