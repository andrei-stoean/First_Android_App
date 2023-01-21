package com.example.firstandroidapp;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

public class HelperMethods {
    private UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    public void pressContinueButton() throws UiObjectNotFoundException {
        UiObject continueButton = device.findObject(new UiSelector()
                .text("CONTINUE"));

        if (continueButton.exists() && continueButton.isEnabled()) {
            continueButton.click();
        }
    }
}
