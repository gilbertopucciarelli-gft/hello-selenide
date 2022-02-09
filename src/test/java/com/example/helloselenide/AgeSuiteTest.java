package com.example.helloselenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
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
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addRoboBeer();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.btnOrder.shouldBe(disabled);
        checkoutPage.ageInput.shouldBe(visible);
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("21");
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.order();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void onexRoboBeerCheckoutLess18() {
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addRoboBeer();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.btnOrder.shouldBe(disabled);
        checkoutPage.ageInput.shouldBe(visible);
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge("17");
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.order();
        orderPage.getAlertMessage().shouldBe(text("Only adults can buy alcohol!"));
    }

    @Test
    public void onexRoboColaCheckout() {
        cartPage.btnCheckout.shouldBe(disabled);
        cartPage.addRoboCola();
        cartPage.btnCheckout.shouldBe(enabled);

        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.ageInput.shouldBe(hidden);
        checkoutPage.btnCancel.shouldBe(enabled);
        checkoutPage.btnOrder.shouldBe(enabled);

        OrderPage orderPage = checkoutPage.order();
        orderPage.getConfirmationMessage().shouldBe(text("Coming right up! ~bzzzt~"));
    }

    @Test
    public void cancelOrder() {
        cartPage.addRoboCola();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.getBtnCancel();
    }
}
