package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.pages.CardPage;

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
    void cardPositiveAl€lFieldValidDeclined() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getDeclinedCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFailure();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void cardNegativeEmptyNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getEmptyNumberCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFieldRequired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getLettersInNumberCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeInvalidNumberAllZeros() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getAllZerosNumberCard();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeNumber15Digits() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getShortNumber15Digits();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInNumber() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getSymbolsInNumberCard();
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
        var cardInfo = DataHelper.getSymbolsInMonth();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyMonth() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getEmptyMonth();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFieldRequired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInMonth() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getLettersInMonth();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativePastYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getPastYear();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationCardExpired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getLettersInYear();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeYearPlus6() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getYearPlus6();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongYear();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getSymbolsInYear();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyYear() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getEmptyYear();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFieldRequired();
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
        var cardInfo = DataHelper.getOneWordHolder();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeThreeWordsHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getThreeWordsHolder();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeDigitsInHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getDigitsInHolder();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getSymbolsInHolder();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyHolder() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getEmptyHolder();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFieldRequired();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeOneDigitCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getOneDigitCVC();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeTwoDigitsCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getTwoDigitsCVC();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeLettersInCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getLettersInCVC();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeSymbolsInCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getSymbolsInCVC();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationWrongFormat();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void cardNegativeEmptyCVC() {
        CardPage orderPage = startPage.debitCardPage();
        var cardInfo = DataHelper.getEmptyCVC();
        orderPage.insertCardData(cardInfo);
        orderPage.waitNotificationFieldRequired();
        assertEquals("0", SQLHelper.getOrderCount());
    }
}

