package com.example.helloselenide;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

// https://www.jetbrains.com/
public class MainPage {

    public SelenideElement toolsMenu = $(".main-menu div[data-test-marker~='Developer'] button");

    public SelenideElement seeAllToolsButton = $(".wt-button_mode_primary");

    public SelenideElement searchButton = $("button[data-test='site-header-search-action'] svg");
}
