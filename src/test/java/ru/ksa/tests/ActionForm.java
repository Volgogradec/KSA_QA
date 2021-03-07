package ru.ksa.tests;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.github.javafaker.Faker;

import java.util.Locale;

public class ActionForm {
    public void sendFormVicolor(int cycleNumber) {
        for(int i = 0; i < cycleNumber; i++) {
            Faker faker = new Faker(new Locale("ru"));
            String customerName = faker.name().firstName() + " " + faker.name().lastName();
            String customerPhone = faker.phoneNumber().phoneNumber();
            open("https://vicolor-msk.ru/kontakty/");
            SelenideElement form = $("form.form-contacts__form.feedback");
            form.$("input[name=form_name]").setValue(customerName);
            form.$("input[name=form_phone]").setValue(customerPhone);
            form.$("textarea[name=form_message]").setValue("Пример текста");
            form.$("button[type=submit]").click();
            $(".feedback-send-message").waitUntil(visible, 15000);
        }
    }
}