package com.example.demo;

import com.codeborne.selenide.selector.ByTagAndText;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
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

        $(withText("Tartu")).shouldBe(visible);

    }

    @Test
    public void getWeatherForecast() {
        String apiKey = "BMU4JN68Q5EN7B6ZPQBPKCFV2";
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Tallinn?unitGroup=metric&key=__API_KEY__&contentType=json".replace("_API_KEY_",apiKey);

        ResponseEntity<String> response = new TestRestTemplate().getForEntity(url, String.class);
        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());
    }

}
