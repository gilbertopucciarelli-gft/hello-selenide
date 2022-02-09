package com.example.helloselenide.cucumber;

import com.example.helloselenide.CartPage;
import com.example.helloselenide.CheckoutPage;
import com.example.helloselenide.OrderPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
        open("http://localhost:3000");
        cartPage = new CartPage();
    }

    @When("User adds a RoboCola to Cart")
    public void usersAddsARoboColaToCart() {
        cartPage.addRoboCola();
    }

    @Then("Total should be €{double}")
    public void totalShouldBe(Double total) {
        BigDecimal scaled = (BigDecimal.valueOf(total)).setScale(2, RoundingMode.HALF_DOWN);
        cartPage.total().shouldBe(exactText("€"+scaled.toString()));
    }

    @When("User adds a RoboBeer to Cart")
    public void userAddsARoboBeerToCart() {
        cartPage.addRoboBeer();
    }

    @When("User adds a RoboWine to Cart")
    public void userAddsARoboWineToCart() {
        cartPage.addRobowine();
    }

    @And("User proceeds to Checkout")
    public void userProceedsToCheckout() {
        checkoutPage = cartPage.checkout();
    }

    @And("User proceeds to complete his order")
    public void userProceedsToCompleteHisOrder() {
        orderPage = checkoutPage.order();
    }

    @Then("Confirmation Message should be {string}")
    public void confirmationMessageShouldBe(String message) {
        orderPage.getConfirmationMessage().shouldBe(exactText("Coming right up! ~bzzzt~"));
    }

    @And("User clicks Age Input")
    public void userClicksAgeInput() {
        checkoutPage.getAgeInput();
    }

    @And("User specify {int} as his age")
    public void userSpecifyAsHisAge(int age) {
        checkoutPage.sendKeysAge(String.valueOf(age));
    }

    @Then("Alert Message should be {string}")
    public void alertMessageShouldBe(String message) {
        orderPage.getAlertMessage().shouldBe(exactText("Only adults can buy alcohol!"));
    }
}
