package com.example.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class AgeSuiteTest {

    CartPage cartPage = new CartPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:3000/");
    }

    @Test
    public void onexRoboBeerCheckoutGreater18() {
        cartPage.addRoboBeer();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("21");
        OrderPage orderPage = checkoutPage.order();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void onexRoboBeerCheckoutLess18() {
        cartPage.addRoboBeer();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("17");
        OrderPage orderPage = checkoutPage.order();
        orderPage.getAlertMessage().shouldBe(text("Only adults can buy alcohol!"));
    }

    @Test
    public void onexRoboColaCheckout() {
        cartPage.addRoboCola();
        CheckoutPage checkoutPage = cartPage.checkout();
        OrderPage orderPage = checkoutPage.order();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }
}
