package com.example.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

// http://localhost:3000/#!/review
public class CheckoutPage {

    public SelenideElement ageInput = $("#ageInput" );

    public SelenideElement btnOrder = $(".btn-success");

    public SelenideElement btnCancel = $(".btn-default");

    public void getAgeInput() {
        ageInput.click();
    }

    public void sendKeysAge(String age) {
        ageInput.sendKeys(age);
    }

    public CartPage getBtnCancel() {
        btnCancel.click();
        return page(CartPage.class);
    }

    public OrderPage order() {
        btnOrder.click();
        return page(OrderPage.class);
    }
}