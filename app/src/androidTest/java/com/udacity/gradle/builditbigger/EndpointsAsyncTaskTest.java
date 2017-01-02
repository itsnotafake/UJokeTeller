package com.udacity.gradle.builditbigger;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import com.udacity.gradle.androidjoke.JokeActivity;

import junit.framework.Assert;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EndpointsAsyncTaskTest {

    private CountDownLatch signal;

    @Rule
    public ActivityTestRule<JokeActivity> mActivityRule = new ActivityTestRule<>(
            JokeActivity.class);

    @Before
    public void setUpStuff(){
        signal = new CountDownLatch(1);
    }

    @Test
    public void asyncTaskTest() throws Throwable{
        signal.await(30, TimeUnit.SECONDS);
        String s = getText(ViewMatchers.withId(R.id.joke_joke));
        Assert.assertNotNull(s);
    }

    String getText(final Matcher<View> matcher) {
        final String[] stringHolder = { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(TextView.class);
            }

            @Override
            public String getDescription() {
                return "getting text from a TextView";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextView tv = (TextView)view; //Save, because of check in getConstraints()
                stringHolder[0] = tv.getText().toString();
            }
        });
        return stringHolder[0];
    }
}
