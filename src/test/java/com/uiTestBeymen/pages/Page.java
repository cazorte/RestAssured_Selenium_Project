package com.uiTestBeymen.pages;

import com.utils.BrowserUtils;
import com.utils.ConfigReader;
import com.utils.Driver;
import com.utils.TestBase;
import org.junit.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page extends TestBase {

    public Page() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//input[@class='default-input o-header__search--input']")
    public WebElement searchBox;

    @FindBy(css = "button#onetrust-accept-btn-handler")
    public WebElement cookies;

    @FindBy(css = "button#genderManButton")
    public WebElement genderManButton;




    public void mainPage() {
        Driver.get().get(ConfigReader.get("url"));

        BrowserUtils.waitForClickablility(cookies, 5);

        cookies.click();

        BrowserUtils.waitForClickablility(genderManButton, 5);

        genderManButton.click();
    }

    public void checkMainPage() {
        String actualTitle = Driver.get().getTitle();
        String expectedTitle = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";

        Assert.assertEquals(expectedTitle, actualTitle);
    }




}
