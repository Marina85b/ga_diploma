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

    StartPage startPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openStartPage() {
        startPage = open("http://localhost:8080/", StartPage.class);
    }

    @AfterEach
    void cleanBase() {
        SQLHelper.clearDB();
    }

    @Test
    void creditPositiveAllFieldValidApproved() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getApprovedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void creditPositiveAllFieldValidDeclined() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getDeclinedCard();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getCreditRequestStatus());
    }

    @Test
    void creditNegativeCardNumberEmpty() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void creditNegativeCardNumberWithLetters() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCardNumberWithLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCardNumberAllZeros() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCardNumberAllZeros();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationFailure();
    }

    @Test
    void creditNegativeCardNumber15Digits() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCardNumber15Digits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCardNumberWithSymbols() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCardNumberWithSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeMonth00() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getMonth00();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeMonth13() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getMonth13();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeMonthSymbols() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getMonthSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeMonthEmpty() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getMonthEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeMonthLetters() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getMonthLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearPast() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYearPast();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
    }

    @Test
    void creditNegativeYearLetters() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYearLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearTooFarFuture() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYearTooFarFuture();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpirationDateError();
    }

    @Test
    void creditNegativeYearSymbols() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYearSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeYearEmpty() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYearEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeYear00() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getYear00();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationExpiredError();
    }

    @Test
    void creditNegativeOwnerOneWord() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getOwnerOneWord();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerThreeWords() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getOwnerThreeWords();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerDigits() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getOwnerDigits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerSymbols() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getOwnerSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeOwnerEmpty() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }

    @Test
    void creditNegativeCvcOneDigit() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCvcOneDigit();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcTwoDigits() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCvcTwoDigits();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcLetters() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCvcLetters();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcSymbols() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCvcSymbols();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat();
    }

    @Test
    void creditNegativeCvcEmpty() {
        CreditPage creditPage = startPage.creditPage();
        var cardInfo = DataHelper.getCvcEmpty();
        creditPage.insertCardData(cardInfo);
        creditPage.waitNotificationWrongFormat4Fields();
    }
}



