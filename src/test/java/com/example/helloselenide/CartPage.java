package com.example.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// http://localhost:3000/#!/
public class CartPage {

    public SelenideElement btnAddRoboCola = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(1) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    public SelenideElement btnAddRoboBeer = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(2) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    public SelenideElement btnAddRoboWine = $("html > body > robo-robobar > div > div:nth-of-type(2) > robo-place-order > div:nth-of-type(2) > table > tbody > tr:nth-of-type(3) > td:nth-of-type(3) > div > div > div:nth-of-type(2) > button");

    public SelenideElement textTotal = $("th[class='ng-binding']");

    public SelenideElement btnCheckout = $(".btn-success");

    public void addRoboCola() {
        btnAddRoboCola.click();
    }

    public void addRoboBeer() {
        btnAddRoboBeer.click();
    }

    public void addRobowine() {
        btnAddRoboWine.click();
    }

    public SelenideElement total() {
        return textTotal;
    }

    public CheckoutPage checkout() {
        btnCheckout.click();
        return new CheckoutPage();
    }
}