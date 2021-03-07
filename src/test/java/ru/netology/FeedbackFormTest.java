package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FeedbackFormTest {
    Faker faker = new Faker(new Locale("ru"));
    String customerName = faker.name().firstName() + " " + faker.name().lastName();
    String customerPhone = faker.phoneNumber().phoneNumber();

    String meetingDay(int day){
        return LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void sendHardCodeData() {
        open("https://vicolor-msk.ru/kontakty/");
        SelenideElement form = $("form.form-contacts__form.feedback");
        form.$("input[name=form_name]").setValue("Анатолий");
        form.$("input[name=form_phone]").setValue("9642682654");
        form.$("textarea[name=form_message]").setValue("Сообщение по заявке");
        form.$("button[type=submit]").click();
        $(".feedback-send-message").waitUntil(visible, 500);
    }

    @Test
    void sendGeneratedData() {
        open("https://vicolor-msk.ru/kontakty/");
        SelenideElement form = $("form.form-contacts__form.feedback");
        form.$("input[name=form_name]").setValue(customerName);
        form.$("input[name=form_phone]").setValue(customerPhone);
        form.$("textarea[name=form_message]").setValue("Пример текста");
        form.$("button[type=submit]").click();
        $(".feedback-send-message").waitUntil(visible, 500);
    }

    @Test
    void testPositiveAllInput() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(5));
        form.$("[data-test-id='name'] input").setValue("Анатолий");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification']").waitUntil(visible, 15000).shouldHave(text(meetingDay(5)));
    }

    @Test
    void testNegativeCityEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(meetingDay(3));
        form.$("[data-test-id='name'] input").setValue("Анатолий");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        form.$("[data-test-id='city'].input_invalid .input__sub").shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

}
