package com.uiTestBeymen.pages;

import com.utils.*;
import org.junit.Assert;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

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

    @FindBy(xpath = "//*[@class='m-productImageList']")
    public List<WebElement> allProductList;

    @FindBy(xpath = "//*[@class='o-productDetail__description']")
    public WebElement productName;

    @FindBy(css = "#priceNew")
    public WebElement productPrice;

    @FindBy(xpath = "//*[contains(@class, 'm-variation__item')]")
    public List<WebElement> allBodySizeList;

    @FindBy(css = "#addBasket")
    public WebElement addBasketButton;

    @FindBy(xpath = "//*[@class='m-notification__button btn']")
    public WebElement goToBasketButton;

    String prodPrice;


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

    public void clearSearchBar() {
        searchBox.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        BrowserUtils.waitFor(2);
    }

    public void chooseOneProduct () {

        BrowserUtils.waitFor(3);

        Random rn = new Random();
        int a = rn.nextInt(allProductList.size());
        BrowserUtils.waitFor(1);
        allProductList.get(a).click();
        BrowserUtils.waitFor(1);
    }

    public void gatherProductInfo () {

        BrowserUtils.waitFor(2);
        try {
            FileWriter fileWriter = new FileWriter("prod.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Ürün bilgisi : " + productName.getText());
            printWriter.println("Fiyat : " + productPrice.getText());
            printWriter.close();

            prodPrice = productPrice.getText();
            System.out.println("prodPrice = " + prodPrice);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void addToBasket () {
        for (int i = 0; i < allBodySizeList.size(); i++) {
            allBodySizeList.get(i).click();

            if (allBodySizeList.get(i).getAttribute("class").contains("active")) {
                break;
            }
        }

        addBasketButton.click();

        BrowserUtils.waitFor(1);
    }

    public void goToBasket(){
        BrowserUtils.waitForClickablility(goToBasketButton, 5);
        goToBasketButton.click();

        BrowserUtils.waitFor(1);
    }










}
