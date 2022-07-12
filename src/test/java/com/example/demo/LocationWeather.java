package com.example.demo;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LocationWeather {

    public void locationWeatherSearch(String text) {
        $(By.id("wxlocation")).val(text).pressEnter();
    }

}
