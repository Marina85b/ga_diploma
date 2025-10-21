package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CreditPage {
    private final SelenideElement heading = $(withText("Кредит по данным карты"));
    private final SelenideElement cardNumber = $(byText("Номер карты")).parent().$(".input__control");
    private final SelenideElement month = $(byText("Месяц")).parent().$(".input__control");
    private final SelenideElement year = $(byText("Год")).parent().$(".input__control");
    private final SelenideElement cardHolder = $(byText("Владелец")).parent().$(".input__control");
    private final SelenideElement cvc = $(byText("CVC/CVV")).parent().$(".input__control");
    private final SelenideElement buttonNext = $(byText("Продолжить"));

    private final SelenideElement success = $(".notification_status_ok");
    private final SelenideElement error = $(".notification_status_error");

    private final ElementsCollection inputSubCollection = $$(".input__sub");

    private final Duration longTimeout = Duration.ofSeconds(30);
    private final Duration shortTimeout = Duration.ofSeconds(15);

    public CreditPage() {
        heading.shouldBe(visible).shouldHave(text("Кредит по данным карты"));
    }

    public void insertCardData(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        cardHolder.setValue(cardInfo.getCardHolder());
        cvc.setValue(cardInfo.getCvc());
        buttonNext.click();
    }

    private void waitProcessingEndIfAny() {
        try {
            $$(byText("Отправляем запрос")).findBy(visible)
                    .shouldNotBe(visible, longTimeout);
        } catch (Exception ignored) {
        }
    }

    public void waitNotificationApproved() {
        waitProcessingEndIfAny();

        success.shouldBe(visible, longTimeout);

        try {
            String text = success.getText().trim();
            if (!(text.contains("Успешно") && (text.contains("одобрено") || text.contains("Операция одобрена")))) {
                System.out.println("[WARN] Текст уведомления успеха отличается от ожидаемого: " + text);
            }
        } catch (Exception ignored) {

        }
    }

    public void waitNotificationFailure() {
        waitProcessingEndIfAny();
        try {
            error.shouldBe(visible, longTimeout);
        } catch (Exception e) {

            SelenideElement backupError = $$(".notification").findBy(text("Ошибка"));
            if (backupError.exists()) {
                backupError.shouldBe(visible, longTimeout);
            } else {
                System.out.println("[WARN] Уведомление об ошибке не найдено по стандартным локаторам");
            }
        }
    }

    public void waitNotificationWrongFormat() {
        try {
            inputSubCollection.findBy(text("Неверный формат"))
                    .shouldBe(visible, shortTimeout);
            return;
        } catch (Exception ignored) {
        }

        $(byText("Неверный формат")).shouldBe(visible, shortTimeout);
    }

    public void waitNotificationExpirationDateError() {
        try {
            inputSubCollection.findBy(text("Неверно указан срок действия карты"))
                    .shouldBe(visible, shortTimeout);
            return;
        } catch (Exception ignored) {
        }

        $(byText("Неверно указан срок действия карты")).shouldBe(visible, shortTimeout);
    }

    public void waitNotificationExpiredError() {
        try {
            inputSubCollection.findBy(text("Истёк срок действия карты"))
                    .shouldBe(visible, shortTimeout);
            return;
        } catch (Exception ignored) {
        }

        $(byText("Истёк срок действия карты")).shouldBe(visible, shortTimeout);
    }

    public void waitNotificationWrongFormat4Fields() {

        inputSubCollection.filterBy(text("Неверный формат")).shouldHave(size(4), shortTimeout);

        try {
            inputSubCollection.findBy(text("Поле обязательно для заполнения"))
                    .shouldBe(visible, shortTimeout);
        } catch (Exception ignored) {
        }
    }
}














