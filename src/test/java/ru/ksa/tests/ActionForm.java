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

    // Optima-fasad.ru
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

    // dekot21.ru
    public void sendFormDekot(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerRu.name().firstName() + " " + fakerRu.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            String customerAdress = fakerRu.address().cityName() + ", " + fakerRu.address().streetName();
            String customerCompany = fakerRu.company().name();
            open("https://dekot21.ru");
            SelenideElement form = $("form[name=mod-rscontact-contact-form]");
            form.$("input[name=mod_rscontact_full_name]").setValue(customerName);
            form.$("input[name=mod_rscontact_email]").setValue(customerEmail);
            form.$("input[name=mod_rscontact_address_1]").setValue(customerAdress);
            form.$("input[name=mod_rscontact_mobile_phone]").setValue(customerPhone);
            form.$("input[name=mod_rscontact_company]").setValue(customerCompany);
            form.$("select[name=mod_rscontact_subject]").selectOptionByValue("Фасадная подсистема");
            form.$("textarea[name=mod_rscontact_cf2]").setValue("Серёжа, долг сам себя не заплатит. Подключайся!");
            form.$("input[name=mod_rscontact_display_consent]").click();
            Thread.sleep(10000);
            form.$("button[type=submit]").click();
            Thread.sleep(1000);
            $("div.alert.alert-success").shouldHave(text("Заявка принята"));
        }
    }

    // ventfasad.online
    public void sendFormVentfasad(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerEng.name().firstName() + " " + fakerRu.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            String customerCompany = fakerEng.company().name();
            open("https://ventfasad.online/");
            SelenideElement form1 = $("div[class=b24-widget-button-block]");
            form1.$("div[class=b24-widget-button-inner-block]").click();
            SelenideElement form2 = $("div[class=\"b24-widget-button-social b24-widget-button-show\"]");
            form2.$("a[class=\"b24-widget-button-social-item b24-widget-button-crmform\"]").click();
            SelenideElement form3 = $("div[class=\"b24-form-content b24-form-padding-side\"]");
            form3.$("input[name=phone]").setValue(customerPhone);
            form3.$("input[name=email]").setValue(customerEmail);
            form3.$("input[name=name]").setValue(customerName);
            form3.$("textarea[class=\"b24-form-control b24-form-control-not-empty\"]").setValue("Серёжа, долг сам себя не заплатит. Подключайся!");
            form3.$("input[class=b24-form-control]").setValue(customerCompany);
//            Клик по чекбоксу согласия работает. В форме по умолчанию она итак отмечена.
//            form3.$("div[class=\"b24-form-field b24-form-field-agreement b24-form-control-agreement\"]").click();
//            Thread.sleep(10000);
            form3.$("button[type=submit]").click();
            Thread.sleep(1000);
//          Проверка отбивки об отправке не отработано из-за 403 ошибки при нажатии на кнопку Отправить
//            $("div.alert.alert-success").shouldHave(text("Заявка принята"));
        }
    }

    // fasad-silma.ru
    // Отправка формы не работает
    public void sendFormSilma(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerRu.name().firstName() + " " + fakerRu.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            String customerAdress = fakerRu.address().cityName() + ", " + fakerRu.address().streetName();
            String customerCompany = fakerRu.company().name();
            open("https://www.fasad-silma.ru/");
            SelenideElement form = $("form[name=mod-rscontact-contact-form]");
            form.$("input[name=mod_rscontact_full_name]").setValue(customerName);
            form.$("input[name=mod_rscontact_email]").setValue(customerEmail);
            form.$("input[name=mod_rscontact_address_1]").setValue(customerAdress);
            form.$("input[name=mod_rscontact_mobile_phone]").setValue(customerPhone);
            form.$("input[name=mod_rscontact_company]").setValue(customerCompany);
            form.$("div[id=mod_rscontact_subject_797_chzn]").click();
            form.$("ul.chzn-results li:nth-of-type(2)").click();
//            form.$("select[name=mod_rscontact_subject]").selectOptionByValue("Фасадная подсистема");
            form.$("textarea[name=mod_rscontact_cf2]").setValue("Серёжа, долг сам себя не заплатит. Подключайся!");
            form.$("input[name=mod_rscontact_display_consent]").click();
            Thread.sleep(10000);
            form.$("button[type=submit]").click();
            Thread.sleep(1000);
            $("div.alert.alert-success").shouldHave(text("Заявка принята"));
        }
    }

    public void sendFormSilmaB24(int cycleNumber) throws InterruptedException {
        for(int i = 0; i < cycleNumber; i++) {
            Faker fakerRu = new Faker(new Locale("ru"));
            Faker fakerEng = new Faker(new Locale("en"));
            String customerName = fakerEng.name().firstName();
            String customerLastname = fakerEng.name().lastName();
            String customerPhone = fakerRu.phoneNumber().phoneNumber();
            String customerEmail = fakerEng.internet().safeEmailAddress();
            open("https://www.fasad-silma.ru/");
            SelenideElement form1 = $("div[class=b24-widget-button-block]");
            form1.$("div[class=b24-widget-button-inner-block]").click();
            SelenideElement form2 = $("div[class=\"b24-widget-button-social b24-widget-button-show\"]");
            form2.$("a[class=\"b24-widget-button-social-item b24-widget-button-crmform\"]").click();
            SelenideElement form3 = $("div[class=\"b24-form-content b24-form-padding-side\"]");
            form3.$("input[name=name]").setValue(customerName);
            form3.$("input[name=lastname]").setValue(customerLastname);
            form3.$("input[name=phone]").setValue(customerPhone);
            form3.$("input[name=email]").setValue(customerEmail);
            form3.$("textarea[class=\"b24-form-control\"]").setValue("Серёжа, долг сам себя не заплатит. Подключайся!");
//            Клик по чекбоксу согласия работает. В форме по умолчанию она итак отмечена.
//            form3.$("div[class=\"b24-form-field b24-form-field-agreement b24-form-control-agreement\"]").click();
//            Thread.sleep(10000);
            form3.$("button[type=submit]").click();
            Thread.sleep(1000);
//          Проверка отбивки об отправке не отработано, т.к. идёт переадресация на главную страницу
//            $("div.alert.alert-success").shouldHave(text("Заявка принята"));
        }
    }
}