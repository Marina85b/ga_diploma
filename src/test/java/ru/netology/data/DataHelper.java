package ru.netology.data;

import lombok.Value;

public class DataHelper {

    // ======== ВАЛИДНЫЕ ДАННЫЕ ========
    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    // ======== НЕВАЛИДНЫЕ ДАННЫЕ ========

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getCardNumberWithLetters() {
        return new CardInfo("TESTCARD", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getCardNumberAllZeros() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getCardNumber15Digits() {
        return new CardInfo("444444444444444", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getCardNumberWithSymbols() {
        return new CardInfo("@@@@####%%%%&&&&", getValidMonth(), getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getMonth00() {
        return new CardInfo("4444444444444441", "00", getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getMonth13() {
        return new CardInfo("4444444444444441", "13", getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getMonthSymbols() {
        return new CardInfo("4444444444444441", "!@", getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getMonthEmpty() {
        return new CardInfo("4444444444444441", "", getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getMonthLetters() {
        return new CardInfo("4444444444444441", "AB", getValidYear(1), "Ivan Ivanov", "123");
    }

    public static CardInfo getYearPast() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(-1), "Ivan Ivanov", "123");
    }

    public static CardInfo getYearLetters() {
        return new CardInfo("4444444444444441", getValidMonth(), "AB", "Ivan Ivanov", "123");
    }

    public static CardInfo getYearTooFarFuture() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(6), "Ivan Ivanov", "123");
    }

    public static CardInfo getYearSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), "@#", "Ivan Ivanov", "123");
    }

    public static CardInfo getYearEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), "", "Ivan Ivanov", "123");
    }

    public static CardInfo getYear00() {
        return new CardInfo("4444444444444441", getValidMonth(), "00", "Ivan Ivanov", "123");
    }

    public static CardInfo getOwnerOneWord() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Pavel", "123");
    }

    public static CardInfo getOwnerThreeWords() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Pavel Ivanov Pavlovich", "123");
    }

    public static CardInfo getOwnerDigits() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "123456", "123");
    }

    public static CardInfo getOwnerSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "@#%&*!", "123");
    }

    public static CardInfo getOwnerEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "", "123");
    }

    public static CardInfo getCvcOneDigit() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "5");
    }

    public static CardInfo getCvcTwoDigits() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "12");
    }

    public static CardInfo getCvcLetters() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "ABC");
    }

    public static CardInfo getCvcSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "@#&");
    }

    public static CardInfo getCvcEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "Ivan Ivanov", "");
    }

    // ======== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ========
    private static String getValidMonth() {
        return java.time.LocalDate.now().plusMonths(1).format(java.time.format.DateTimeFormatter.ofPattern("MM"));
    }

    private static String getValidYear(int shift) {
        return java.time.LocalDate.now().plusYears(shift).format(java.time.format.DateTimeFormatter.ofPattern("YY"));
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String cardHolder;
        String cvc;
    }
}

