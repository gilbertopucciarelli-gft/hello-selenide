package com.example.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// http://localhost:3000/#!/review
public class CheckoutPage {

    public SelenideElement ageInput = $("#ageInput" );

    public SelenideElement btnOrder = $(".btn-success");

    public void getAgeInput() {
        ageInput.click();
    }

    public void sendKeysAge(String age) {
        ageInput.sendKeys(age);
    }

    public OrderPage order() {
        btnOrder.click();
        return new OrderPage();
    }
}