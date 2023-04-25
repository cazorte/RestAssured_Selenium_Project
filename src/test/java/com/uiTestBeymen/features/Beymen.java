package com.uiTestBeymen.features;

import com.uiTestBeymen.pages.Page;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

public class Beymen extends Page {

    @Test
    public void e2eTest(){


        goToMainPage();

        validateTitle("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");

        textToSearchBar(readExcelFile()[0]);

        clearSearchBar();

        textToSearchBar(readExcelFile()[1]);

        searchBox.sendKeys(Keys.ENTER);

        chooseOneProduct();

        gatherProductInfo();

        addToBasket();

        goToBasket();











    }
}
