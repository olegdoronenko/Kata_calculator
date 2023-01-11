/*
Программа - калькулятор. Работает или с арабскими или с римскими цифрами до 10.
Ожидает ввода строки вида "3 + 2" или "III - I" в консоль.
Либо ожидает вызова метода calc(String).
Тестовое задание для поступления в КАТА академию на курс JAVA
Сделал Олег Дороненко
 */

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static String[] romaNumbers = {"null", "I", "II", "III", "IV", "V", "VI", "VII", "VIII","IX", "X"};
    //массив с римскими цифрами. Через него проверяем на входные данные и берем индексы римских цифр
    static String[] arabNumbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    //массив с арабскими цифрами. просто для быстрой проверки входящей строки
    static String[] operators = {"+", "-", "*", "/"};
    //массив для проверки оператора входящей строки
    static String[] romaAnswers = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII",
            "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXIV", "XXV", "XXVII",
            "XXVIII", "XXX", "XXXII", "XXXV", "XXXVI", "XL", "XLII", "XLV", "XLVIII", "XLIX",
            "L", "LIV", "LVI", "LX", "LXIII", "LXIV", "LXX", "LXXII", "LXXX", "LXXXI", "XC", "C"
    }; //массив с возможными ответами римскими цифрами
    static Integer[] arabAnswers = {
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 24, 25, 27, 28,
            30, 32, 35, 36, 40, 42, 45, 48, 49, 50, 54, 56, 60, 63, 64, 70, 72, 80, 81, 90, 100
    }; //массив с возможными ответами арабскими цифрами, для получения индексов))))
    static boolean roma = false;
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine(); //ввод строки пользователем
        System.out.println(calc(expression)); //вызов метода обработки по заданию
        }
    public static String calc(String input) throws Exception { //метод calc получает на ввод строку, выдает результат
        String[] components = input.split(" "); //делим входную строку по пробелу
        //проинициализируем переменные слагаемых и оператора
        int termOne;
        int termTwo;
        int answer;
        String operator;

        //запустим калькулятор
        romaOrArab(components); //проверим римские или арабские цифры

        if (roma) {
            termOne = Arrays.asList(romaNumbers).indexOf(components[0]);
            termTwo = Arrays.asList(romaNumbers).indexOf(components[2]);
        } else {
            termOne = Arrays.asList(arabNumbers).indexOf(components[0]);
            termTwo = Arrays.asList(arabNumbers).indexOf(components[2]);
        }

        rangeNumber(termOne, termTwo); //проверим, что числа в диапазоне 1 - 10

        operator = components[1];
        switch (operator) { //выберем арифметическое действие
            case "+" -> answer = termOne + termTwo;
            case "-" -> answer = termOne - termTwo;
            case "*" -> answer = termOne * termTwo;
            default -> answer = termOne / termTwo;
        }
        if (roma) {
            if (answer < 1) {
                throw new Exception();
            }
            return romaAnswers[Arrays.asList(arabAnswers).indexOf(answer)];
        } else {
            return Integer.toString(answer);
        }
    }
    static void romaOrArab (String[] components) throws Exception {
        //метод проверяет корректны ли цифры и операторы
        if (Arrays.asList(romaNumbers).contains(components[0]) &&
                Arrays.asList(romaNumbers).contains(components[2]) &&
                Arrays.asList(operators).contains(components[1])) {
            roma = true;
        } else if (Arrays.asList(arabNumbers).contains(components[0]) &&
                Arrays.asList(arabNumbers).contains(components[2]) &&
                Arrays.asList(operators).contains(components[1])) {
            roma = false;
        } else {
            throw new Exception();
        }
    }
    static void rangeNumber (int termOne, int termTwo) throws Exception {
        //метод проверяет что цифры в нужном диапазоне от 1 до 10
        if ((termOne < 0 || termOne > 10) || (termTwo < 0 || termTwo > 10)) {
            throw new Exception();
        }
    }
}