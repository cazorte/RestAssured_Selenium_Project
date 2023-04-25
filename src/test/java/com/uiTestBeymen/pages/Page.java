package com.uiTestBeymen.pages;

import com.utils.*;
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




    public void goToMainPage() {
        Driver.get().get(ConfigReader.get("url"));

        BrowserUtils.waitForClickablility(cookies, 5);

        cookies.click();

        BrowserUtils.waitForClickablility(genderManButton, 5);

        genderManButton.click();
    }

    public void validateTitle(String title) {
        String actualTitle = Driver.get().getTitle();


        Assert.assertEquals(title, actualTitle);
    }

    public void textToSearchBar(String str) {
        searchBox.click();
        searchBox.sendKeys(str);
        BrowserUtils.waitFor(2);
    }

    public String[] readExcelFile() {

        ExcelUtil qa3short = new ExcelUtil("src/test/Resources/searchKeywords.xlsx", "Sheet1");

        String[][] dataArray = qa3short.getDataArray();
        String[] allURL = new String[qa3short.columnCount()];

        for (int i = 0; i < allURL.length; i++) {
            allURL[i] = dataArray[0][i].trim();
        }
        return allURL;
    }




}
