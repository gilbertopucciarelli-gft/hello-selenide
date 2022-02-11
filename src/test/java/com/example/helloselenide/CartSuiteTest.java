package com.example.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class CartSuiteTest {

    CartPage cartPage = new CartPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        //open("http://localhost:3000/");
        open("http://10.250.6.1:3000");
    }

    @Test
    public void onexRoboCola() {
        cartPage.addRoboCola();
        cartPage.total().shouldBe(text("€1.25"));
    }

    @Test
    public void onexRoboBeer() {
        cartPage.addRoboBeer();
        cartPage.total().shouldBe(text("€2.00"));
    }

    @Test
    public void onexRobwine() {
        cartPage.addRobowine();
        cartPage.total().shouldBe(text("€3.00"));
    }

    @Test
    public void onexRoboCola1xRoboBeer() {
        cartPage.addRoboCola();
        cartPage.addRoboBeer();
        cartPage.total().shouldBe(text("€3.25"));
    }

    @Test
    public void onexRoboCola1xRoboBeer1xRobwine() {
        cartPage.addRoboCola();
        cartPage.addRoboBeer();
        cartPage.addRobowine();
        cartPage.total().shouldBe(text("€6.25"));
    }

    @Test
    public void twoxRobwine() {
        cartPage.addRobowine();
        cartPage.addRobowine();
        cartPage.total().shouldBe(text("€6.00"));
    }

    @Test
    public void twoxRoboBeer() {
        cartPage.addRoboBeer();
        cartPage.addRoboBeer();
        cartPage.total().shouldBe(text("€4.00"));
    }

    @Test
    public void twoxRoboCola() {
        cartPage.addRoboCola();
        cartPage.addRoboCola();
        cartPage.total().shouldBe(text("€2.50"));
    }
}
