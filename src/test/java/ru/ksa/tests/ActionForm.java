package ru.ksa.tests;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
            form.$("textarea[name=form_message]").setValue("Сообщение заявки");
            form.$("button[type=submit]").click();
            $(".feedback-send-message").waitUntil(visible, 15000);
        }
    }

    public void sendFormOptima(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerRu.name().firstName() + " " + fakerRu.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            open("https://optima-fasad.ru/contact/");
            SelenideElement form = $("form.wpcf7-form.init");
            form.$("input[name=your-name]").setValue(customerName);
            form.$("input[name=your-email]").setValue(customerEmail);
            form.$("textarea[name=your-message]").setValue("Серёжа, долг сам себя не заплатит. Подключайся!");
            Thread.sleep(10000);
            form.$("input[type=submit]").click();
            Thread.sleep(1000);
            $(".wpcf7-response-output").shouldHave(text("Ваше сообщение было отправлено, Спасибо!"));
        }
    }

    public void sendFormDekot(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerRu.name().firstName() + " " + fakerRu.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            String customerAdress = fakerRu.address().cityName() + " " + fakerRu.address().streetName();
            String customerCompany = fakerRu.company().name();
            open("https://dekot21.ru/kontakty");
            SelenideElement form = $("form[name=mod-rscontact-contact-form]");
            form.$("input[name=mod_rscontact_full_name]").setValue(customerName);
            form.$("input[name=mod_rscontact_email]").setValue(customerEmail);
            form.$("input[name=mod_rscontact_address_1]").setValue(customerAdress);
            form.$("input[name=mod_rscontact_mobile_phone]").setValue(customerPhone);
            form.$("input[name=mod_rscontact_company]").setValue(customerCompany);
            form.$("select[name=mod_rscontact_subject]").selectOptionByValue("Фасадная подсистема");
            form.$("textarea[name=mod_rscontact_cf2]").setValue("...");
            form.$("input[name=mod_rscontact_display_consent]").click();
            Thread.sleep(10000);
            form.$("button[type=submit]").click();
            Thread.sleep(1000);
            $("div.alert.alert-success").shouldHave(text("Заявка принята"));
        }
    }
}