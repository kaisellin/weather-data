package com.example.demo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.selector.ByTagAndText;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static com.codeborne.selenide.Selenide.*;

public class WeatherSearchingTests {

    @Test
    void weatherTest() {
        open("https://www.visualcrossing.com");

        sleep(2000);


        $(new ByTagAndText("button", "Accept all cookies")).click();

        sleep(2000);

        $(By.linkText("Weather Data")).click();

        $(By.id("wxlocation")).setValue("Tartu");

        sleep(2000);

        $(new ByTagAndText("button", "Search")).click();

        sleep(2000);

        $(new ByTagAndText("h1", "Weather History Dashboard for Tartu")).shouldBe(Condition.visible);
        $("#locationDropdownMenuButton").shouldHave(Condition.text("Tartu"));


        sleep(2000);
    }

    @Test
    public void getWeatherForecast() throws JSONException {
        String apiKey = "BMU4JN68Q5EN7B6ZPQBPKCFV2";
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Tallinn?unitGroup=metric&key=_API_KEY_&contentType=json".replace("_API_KEY_",apiKey);

        ResponseEntity<String> response = new TestRestTemplate().getForEntity(url, String.class);

        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());

        JSONObject json = new JSONObject(response.getBody());
        Assertions.assertEquals(json.getString("address"), "Tallinn");

    }

}
