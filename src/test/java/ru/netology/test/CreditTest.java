package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.StartPage;
import ru.netology.pages.CreditPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

    StartPage startPage = open("http://localhost:8080/", StartPage.class);

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void cleanBase() {
        SQLHelper.clearDB();
    }

    @Test
    void creditPositiveAllFieldValidApproved() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getApprovedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void creditPositiveAllFieldValidDeclined() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getDeclinedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void creditNegativeCardNumberEmpty() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCardNumberEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCardNumberWithLetters() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCardNumberWithLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCardNumberAllZeros() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCardNumberAllZeros();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
    }

    @Test
    void creditNegativeCardNumber15Digits() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCardNumber15Digits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCardNumberWithSymbols() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCardNumberWithSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeMonth00() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getMonth00();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeMonth13() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getMonth13();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeMonthSymbols() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getMonthSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeMonthEmpty() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getMonthEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeMonthLetters() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getMonthLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearPast() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYearPast();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
    }

    @Test
    void creditNegativeYearLetters() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYearLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearTooFarFuture() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYearTooFarFuture();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeYearSymbols() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYearSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearEmpty() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYearEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeYear00() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getYear00();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
    }

    @Test
    void creditNegativeOwnerOneWord() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getOwnerOneWord();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerThreeWords() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getOwnerThreeWords();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerDigits() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getOwnerDigits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerSymbols() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getOwnerSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerEmpty() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getOwnerEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeCvcOneDigit() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCvcOneDigit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcTwoDigits() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCvcTwoDigits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcLetters() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCvcLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcSymbols() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCvcSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcEmpty() {
        var creditPage = open("/credit", CreditPage.class);
        var cardInfo = DataHelper.getCvcEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }
}



