package com.example.demo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

    public class WeatherResultsPage {
        public ElementsCollection getResults() {
            return $$(".results .result");
        }
        public SelenideElement getResult(int index) {
            return $("#r1-" + index);
        }

}
