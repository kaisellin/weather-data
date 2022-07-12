package com.example.demo;

import com.codeborne.selenide.selector.ByTagAndText;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WeatherSearchingTests {

    @Test
    public void canSearchWeather() {
        open("https://www.visualcrossing.com");

        if ($(new ByTagAndText("button", "Accept all cookies")).isDisplayed()) {
            $(new ByTagAndText("button", "Accept all cookies")).click();
            sleep(10000);
        }

        new LocationWeather().locationWeatherSearch("Tartu");

        WeatherResultsPage results = new WeatherResultsPage();
        results.getResults().shouldHave(sizeGreaterThan(0));
        results.getResult(0).shouldHave(text("Weather History for Tartu"));
    }

}
