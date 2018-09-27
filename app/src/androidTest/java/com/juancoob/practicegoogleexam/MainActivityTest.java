package com.juancoob.practicegoogleexam;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Juan Antonio Cobos Obrero on 27/09/18.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final IntentsTestRule<MainActivity> mainActivityIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void onWriteEditText_checkField() {

        // Write something and check is not empty
        onView(withId(R.id.et_custom)).perform(typeText("Hello world!")).check(matches(not(withText(""))));
    }

    @Test
    public void onClickNewIntentFAB_displayDetailActivityAndCheckIt() {

        // Click the FAB
        onView(withId(R.id.fab_new_activity)).perform(click());

        // Check if the Activity is displayed
        intended(hasComponent(DetailActivity.class.getName()));

        // Check the text has that sentence
        onView(withId(R.id.tv_hello_world)).check(matches(withText("Hello world")));
    }

}
