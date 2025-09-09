package ru.netology.data;

import lombok.Value;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final Random random = new Random();

    // ======== ВАЛИДНЫЕ ДАННЫЕ ========
    public static CardInfo getApprovedCard() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444444444444442", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    // ======== НЕВАЛИДНЫЕ ДАННЫЕ ========

    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardNumberWithLetters() {
        return new CardInfo("TESTCARD", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardNumberAllZeros() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardNumber15Digits() {
        return new CardInfo("444444444444444", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getCardNumberWithSymbols() {
        return new CardInfo("@@@@####%%%%&&&&", getValidMonth(), getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getMonth00() {
        return new CardInfo("4444444444444441", "00", getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getMonth13() {
        return new CardInfo("4444444444444441", "13", getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getMonthSymbols() {
        return new CardInfo("4444444444444441", "!@", getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getMonthEmpty() {
        return new CardInfo("4444444444444441", "", getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getMonthLetters() {
        return new CardInfo("4444444444444441", "AB", getValidYear(1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getYearPast() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(-1), getValidOwner(), getValidCvc());
    }

    public static CardInfo getYearLetters() {
        return new CardInfo("4444444444444441", getValidMonth(), "AB", getValidOwner(), getValidCvc());
    }

    public static CardInfo getYearTooFarFuture() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(6), getValidOwner(), getValidCvc());
    }

    public static CardInfo getYearSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), "@#", getValidOwner(), getValidCvc());
    }

    public static CardInfo getYearEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), "", getValidOwner(), getValidCvc());
    }

    public static CardInfo getYear00() {
        return new CardInfo("4444444444444441", getValidMonth(), "00", getValidOwner(), getValidCvc());
    }

    public static CardInfo getOwnerOneWord() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), faker.name().firstName(), getValidCvc());
    }

    public static CardInfo getOwnerThreeWords() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().lastName(), getValidCvc());
    }

    public static CardInfo getOwnerDigits() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "123456", getValidCvc());
    }

    public static CardInfo getOwnerSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "@#%&*!", getValidCvc());
    }

    public static CardInfo getOwnerEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), "", getValidCvc());
    }

    public static CardInfo getCvcOneDigit() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), String.valueOf(random.nextInt(10)));
    }

    public static CardInfo getCvcTwoDigits() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), String.valueOf(10 + random.nextInt(90)));
    }

    public static CardInfo getCvcLetters() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), "ABC");
    }

    public static CardInfo getCvcSymbols() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), "@#&");
    }

    public static CardInfo getCvcEmpty() {
        return new CardInfo("4444444444444441", getValidMonth(), getValidYear(1), getValidOwner(), "");
    }

    // ======== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ========
    private static String getValidMonth() {
        return java.time.LocalDate.now().plusMonths(1).format(java.time.format.DateTimeFormatter.ofPattern("MM"));
    }

    private static String getValidYear(int shift) {
        return java.time.LocalDate.now().plusYears(shift).format(java.time.format.DateTimeFormatter.ofPattern("YY"));
    }

    private static String getValidOwner() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getValidCvc() {
        int cvc = 100 + random.nextInt(900);
        return String.valueOf(cvc);
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


