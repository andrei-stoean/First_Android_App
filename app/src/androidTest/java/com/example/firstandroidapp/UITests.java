package com.example.firstandroidapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class UITests {
    private static final String PACKAGE_NAME
            = "com.example.firstandroidapp";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice device;

    HelperMethods helperMethods = new HelperMethods();

    private String screenshotName;

    @Before
    public void startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();

        Context context = ApplicationProvider.getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        device.wait(Until.hasObject(By.pkg(PACKAGE_NAME).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void checkErrorMessageForEmptyField() throws UiObjectNotFoundException, InterruptedException {
        screenshotName = "EmptyField";

        helperMethods.pressContinueButton();

        UiObject errorMessage = device.findObject(new UiSelector()
                .resourceIdMatches(".*errorMessage"));

        assertThat(errorMessage.getText(), is(equalTo("You must insert a name !")));
    }

    @Test
    public void checkErrorMessageForSpecialCharacters() throws UiObjectNotFoundException {
        screenshotName = "SpecialCharacters";

        UiObject textInput = device.findObject(new UiSelector()
                .resourceIdMatches(".*name"));

        textInput.setText("And!");
        helperMethods.pressContinueButton();

        UiObject errorMessage = device.findObject(new UiSelector()
                .resourceIdMatches(".*errorMessage"));

        assertThat(errorMessage.getText(), is(equalTo("This field does not accept special characters!")));
    }

    @Test
    public void checkErrorMessageForUnknownUser() throws UiObjectNotFoundException {
        screenshotName = "UnknownUser";

        UiObject textInput = device.findObject(new UiSelector()
                .resourceIdMatches(".*name"));

        textInput.setText("Catalin");
        helperMethods.pressContinueButton();

        UiObject errorMessage = device.findObject(new UiSelector()
                .resourceIdMatches(".*errorMessage"));

        assertThat(errorMessage.getText(), is(equalTo("Unknown User !")));
    }

    @Test
    public void checkForCorrectUser() throws UiObjectNotFoundException {
        screenshotName = "CorrectUser";

        UiObject textInput = device.findObject(new UiSelector()
                .resourceIdMatches(".*name"));

        textInput.setText("Andrei");
        helperMethods.pressContinueButton();

        UiObject successMessage = device.findObject(new UiSelector()
                .resourceIdMatches(".*successMessage"));

        assertThat(successMessage.getText(), is(equalTo("Welcome back, Andrei!")));
    }

    @After
    public void takeScreenShot(){
        device.takeScreenshot(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + screenshotName + ".png"));
    }
}
