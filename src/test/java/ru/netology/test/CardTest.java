package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.CardPage;
import ru.netology.pages.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {

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
    public void cleanBase() {
        SQLHelper.clearDB();
    }

    @Test
    void cardPositiveAllFieldValidApproved() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getApprovedCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationApproved();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    void cardPositiveAllFieldValidDeclined() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getDeclinedCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void cardNegativeEmptyNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCardNumberEmpty();
        orderPage.insertCardData(cardInfo);
        orderPage.waitRequiredFieldError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCardNumberWithLetters();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeInvalidNumberAllZeros() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCardNumberAllZeros();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeNumber15Digits() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCardNumber15Digits();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCardNumberWithSymbols();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeMonth00() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getMonth00();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeMonth13() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getMonth13();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInMonth() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getMonthSymbols();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyMonth() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getMonthEmpty();
        orderPage.insertCardData(cardInfo);
        orderPage.waitRequiredFieldError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInMonth() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getMonthLetters();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativePastYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearPast();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationExpiredError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearLetters();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeYearPlus6() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearTooFarFuture();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationExpirationDateError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearSymbols();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearEmpty();
        orderPage.insertCardData(cardInfo);
        orderPage.waitRequiredFieldError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeYear00() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYear00();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeOneWordHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOwnerOneWord();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeThreeWordsHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOwnerThreeWords();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeDigitsInHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOwnerDigits();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOwnerSymbols();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOwnerEmpty();
        orderPage.insertCardData(cardInfo);
        orderPage.waitRequiredFieldError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeOneDigitCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCvcOneDigit();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeTwoDigitsCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCvcTwoDigits();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCvcLetters();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCvcSymbols();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getCvcEmpty();
        orderPage.insertCardData(cardInfo);
        orderPage.waitRequiredFieldError(); // заменено
        assertEquals("0", SQLHelper.getOrderCount());
    }
}



