package com.mtuci.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Условие: Создайте приватный метод convert с двумя целочисленными параметрами. 1ый параметр - десятичное число которое
 * нужно перевести, 2ой параметр - система счисления в которую нужно перевести первый параметр. Этот метод должен
 * возвращать строку - результат перевода.
 * <p>
 * Внутри метода реализуйте логику перевода числа в указанную систему счисления и верните результат.
 */

public class NumberConverter {

  public static void main(String[] args) throws IOException {
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Vvedite chislo");
    String num = consoleReader.readLine();
    System.out.println("Vvedote sistemu schisleniya");
    String ind = consoleReader.readLine();
    int a = Integer.parseInt(num);
    int b = Integer.parseInt(ind);
    String convert = Integer.toString(a,b);
    System.out.println(convert);
//    // Бинарный формат числа
    String convert2 = Integer.toBinaryString(a);
    System.out.println(convert2);
//
//    // Восьмиричная форма
    String convert8 = Integer.toOctalString(a);
    System.out.println(convert8);
//
//    // Шеснадцатиричная форма
    String convert16 = Integer.toHexString(a);
    System.out.println(convert16);
  }
}