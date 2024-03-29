package ru.ksa.tests;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FeedbackFormTest {
    Faker faker = new Faker(new Locale("ru"));
    String customerName = faker.name().firstName() + " " + faker.name().lastName();
    String customerPhone = faker.phoneNumber().phoneNumber();

//    @Test
    void sendHardCodeData() {
        open("https://vicolor-msk.ru/kontakty/");
        SelenideElement form = $("form.form-contacts__form.feedback");
        form.$("input[name=form_name]").setValue("Анатолий");
        form.$("input[name=form_phone]").setValue("9642682654");
        form.$("textarea[name=form_message]").setValue("Сообщение по заявке");
        form.$("button[type=submit]").click();
        $(".feedback-send-message").waitUntil(visible, 500);
    }

//    @Test
    void sendGeneratedData() {
        open("https://vicolor-msk.ru/kontakty/");
        SelenideElement form = $("form.form-contacts__form.feedback");
        form.$("input[name=form_name]").setValue(customerName);
        form.$("input[name=form_phone]").setValue(customerPhone);
        form.$("textarea[name=form_message]").setValue("Сообщение по заявке");
        form.$("button[type=submit]").click();
        $(".feedback-send-message").waitUntil(visible, 500);
    }

//    @Test
    void sendVicolorGeneratedDataCycle() {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormVicolor(1);
    }

//    @Test
    void sendOptimaGeneratedDataCycle() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormOptima(1);
    }

    @Test
    void sendDekotGeneratedDataCycle() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormDekot(1);
    }

    @Test
    void sendDekotGeneratedDataCycleB24() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormDekotB24(1);
    }

    @Test
    void sendVentfasadGeneratedDataCycle() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormVentfasad(1);
    }

//    @Test
    void sendVentfasadGeneratedDataCycleB24() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormVentfasadB24(1);
    }

//    @Test
    void sendSilmaGeneratedDataCycle() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormSilma(1);
    }

    @Test
    void sendSilmaGeneratedDataCycleB24() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormSilmaB24(90);
    }

    @Test
    void sendVentfasadeGeneratedDataCycle() throws InterruptedException {
        ActionForm actionForm = new ActionForm();
        actionForm.sendFormVentfasade(1);
    }
}
