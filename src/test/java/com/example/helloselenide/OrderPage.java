package com.example.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// http://localhost:3000/#!/review
public class OrderPage {

    public SelenideElement alertMessage = $("p");

    public SelenideElement confirmationMessage = $("p");

    public SelenideElement getAlertMessage() {
        return alertMessage;
    }

    public SelenideElement getConfirmationMessage() {
        return confirmationMessage;
    }
}