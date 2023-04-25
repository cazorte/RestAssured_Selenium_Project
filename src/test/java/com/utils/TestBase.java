package com.utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;



    @Before
    public void setUp(){

        driver= Driver.get();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @After
    public void endTest() {

        Driver.closeDriver();

    }
}
