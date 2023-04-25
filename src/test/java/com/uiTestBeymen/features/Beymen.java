package com.uiTestBeymen.features;

import com.uiTestBeymen.pages.Page;
import org.junit.jupiter.api.Test;

public class Beymen extends Page {

    @Test
    public void e2eTest(){


        goToMainPage();

        validateTitle("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");

        textToSearchBar(readExcelFile()[0]);





    }
}
