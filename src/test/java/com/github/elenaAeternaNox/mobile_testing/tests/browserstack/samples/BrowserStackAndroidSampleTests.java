package com.github.elenaAeternaNox.mobile_testing.tests.browserstack.samples;

import annotations.Microservice;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static io.qameta.allure.Allure.step;

@Owner("ekomarova")
@Story("Android tests")
@Feature("Android")
@Tags({@Tag("android"),@Tag("browserstack_android")})
public class BrowserStackAndroidSampleTests {

    @Microservice("Search")
    @Test
    void SearchTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        step("Settings", () -> {
            // Set your access credentials
            caps.setCapability("browserstack.user", "elenak_jPEpsX");
            caps.setCapability("browserstack.key", "zx1FGzNVCDWj9pfGsYFR");

            // Set URL of the application under test
            caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

            // Specify device and os_version for testing
            caps.setCapability("device", "Google Pixel 3");
            caps.setCapability("os_version", "9.0");

            // Set other BrowserStack capabilities
            caps.setCapability("project", "First Java Project");
            caps.setCapability("build", "Java Android");
            caps.setCapability("name", "first_test");

        });

        step("Test case for the BrowserStack sample Android app", () -> {
            // Initialise the remote Webdriver using BrowserStack remote URL
            // and desired capabilities defined above
            AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                    new URL("http://hub.browserstack.com/wd/hub"), caps);

            // Test case for the BrowserStack sample Android app.
            // If you have uploaded your app, update the test case here.
            AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.elementToBeClickable(
                            MobileBy.AccessibilityId("Search Wikipedia")));
            searchElement.click();
            AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
                    ExpectedConditions.elementToBeClickable(
                            MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
            insertTextElement.sendKeys("BrowserStack");
            Thread.sleep(5000);
            List<AndroidElement> allProductsName = driver.findElementsByClassName(
                    "android.widget.TextView");
            assert (allProductsName.size() > 0);


            // Invoke driver.quit() after the test is done to indicate that the test is completed.
            driver.quit();
                });
    }
}
