package com.example.helloselenide.cucumber;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.helloselenide.CartPage;
import com.example.helloselenide.CheckoutPage;
import com.example.helloselenide.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;

public class RoboBarStepDefinitions {

    CartPage cartPage;
    CheckoutPage checkoutPage;
    OrderPage orderPage;

    @Given("User opens RoboBar Website")
    public void userOpensRoboBarWebsite() {
        //open("http://localhost:3000");
        open("https://robobar.sinensia.pw");
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
        cartPage = new CartPage();
    }

    @When("User adds {int} RoboCola to Cart")
    public void userAddsRoboColaToCart(int number) {
        for (int i = 1; i <= number; i++) {
            cartPage.addRoboCola();
        }
    }

    @When("User adds {int} RoboBeer to Cart")
    public void userAddsRoboBeerToCart(int number) {
        for (int i = 1; i <= number; i++) {
            cartPage.addRoboBeer();
        }
    }

    @When("User adds {int} RoboWine to Cart")
    public void userAddsRoboWineToCart(int number) {
        for (int i = 1; i <= number; i++) {
            cartPage.addRobowine();
        }
    }

    @When("User adds {int} RoboCola, {int} RoboBeer and {int} RoboWine to Cart")
    public void userAddsColaRoboColaBeerRoboBeerAndWineRoboWineToCart(int colas, int beers, int wines) {
        userAddsRoboColaToCart(colas);
        userAddsRoboBeerToCart(beers);
        userAddsRoboWineToCart(wines);
    }

    @Then("Total should be €{double}")
    public void totalShouldBe(Double total) {
        // BigDecimal scaled = (BigDecimal.valueOf(total)).setScale(2, RoundingMode.HALF_DOWN);
        // cartPage.total().shouldBe(exactText("€"+scaled.toString()));
        cartPage.total().shouldBe((exactText(String.format("€%.2f", total))));
    }

    @Then("User proceeds to Checkout")
    public void userProceedsToCheckout() {
        checkoutPage = cartPage.checkout();
    }

    @And("User proceeds to complete his order")
    public void userProceedsToCompleteHisOrder() {
        orderPage = checkoutPage.order();
    }

    @And("User specify {int} as his age")
    public void userSpecifyAsHisAge(int age) {
        checkoutPage.getAgeInput();
        checkoutPage.sendKeysAge(String.valueOf(age));
    }

    @Then("Confirmation Message should be {string}")
    public void confirmationMessageShouldBe(String message) {
        orderPage.getConfirmationMessage().shouldBe(exactText(message));
    }

    @Then("Alert Message should be {string}")
    public void alertMessageShouldBe(String message) {
        orderPage.getAlertMessage().shouldBe(exactText(message));
    }

    @Given("User proceeds to Checkout and order several RoboColas")
    public void userProceedsToCheckoutAndOrderSeveralRoboColas() {
        userOpensRoboBarWebsite();
        userAddsRoboColaToCart(1);
        userProceedsToCheckout();
    }

    @Given("User proceeds to Checkout and order several RoboBeers")
    public void userProceedsToCheckoutAndOrderSeveralRoboBeers() {
        userOpensRoboBarWebsite();
        userAddsRoboBeerToCart(1);
        userProceedsToCheckout();
    }

    @Given("User proceeds to complete his order Underage")
    public void userProceedsToCompleteHisOrderUnderage() {
        userProceedsToCheckoutAndOrderSeveralRoboBeers();
        userSpecifyAsHisAge(17);
    }

    @Given("User proceeds to complete his order Overage")
    public void userProceedsToCompleteHisOrderOverage() {
        userProceedsToCheckoutAndOrderSeveralRoboBeers();
        userSpecifyAsHisAge(21);
    }
}
