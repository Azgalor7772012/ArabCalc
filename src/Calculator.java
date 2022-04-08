import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;

public class Calculator {
    public static void main(String[] args) {
        // Берем данные из ввода
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        // заносим данные в массив
        String[] components = line.split(" ");
        // больше чем 2 операнда - ошибка
        if (components.length > 3) {
            System.err.println("You have to write 2 numbers to calculate");
            System.exit(0);
        }
        // выделяем операнды и операцию из массива
        String num1 = components[0];
        String sym = components[1];
        String num2 = components[2];
        // Назначаем списки, куда будем обращаться
        String[] values1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] values2 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        // первый вариант неправильного ввода
        boolean firstVal1 = Arrays.asList(values1).contains(num1);
        boolean secVal1 = Arrays.asList(values2).contains(num2);
        // второй вариант неправильного ввода
        boolean firstVal2 = Arrays.asList(values2).contains(num1);
        boolean secVal2 = Arrays.asList(values1).contains(num2);
        // Проверяем чтобы опа операнда были либо арабские, либо римские цифры
        if (firstVal1 && secVal1 || firstVal2 && secVal2) {
            System.err.println("You have to write both Roman or both Arabic numbers");
            System.exit(0);
        }
        // переводим строки в числа в случае двух чисел арабскими
        if (firstVal1 && secVal2) {
            Integer digit1 = new Integer(num1);
            Integer digit2 = new Integer(num2);
            // проверяем, чтобы оба числа были от 1 до 10
            if (digit1 > 10 || digit1 < 1 || digit2 > 10 || digit2 < 1){
                System.err.println("You have to write numbers from 1 to 10");
                System.exit(0);
            }
            // переводим строку в арифметический оператор и выдаем ответ
            switch (sym) {
                case "+" -> System.out.println(digit1 + digit2);
                case "-" -> System.out.println(digit1 - digit2);
                case "/" -> System.out.println(digit1 / digit2);
                case "*" -> System.out.println(digit1 * digit2);
            }
            // Перевод двух чисел в случае римских
        } else {
            int digit1 = 0;
            int digit2 = 0;
            int digitAns = 0;
            String[] digits = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            for (int i = 0; i < digits.length; i++) {
                if (digits[i].equals(num1)) {
                    digit1 = i + 1;
                } else if (digits[i].equals(num2)) {
                    digit2 = i + 1;
                }
            }
            // проверяем, чтобы оба числа были от одного до 10
            if (digit1 > 10 || digit1 < 1 || digit2 > 10 || digit2 < 1){
                System.err.println("You have to write numbers from 1 to 10");
                System.exit(0);
            }
            // Снова переводим строку в арифметический оператор, пытался оптимизировать, чтобы 2 раза этот блок не писать, но тщетно
            switch (sym) {
                case "+" -> digitAns = digit1 + digit2;
                case "-" -> digitAns = digit1 - digit2;
                case "/" -> digitAns = digit1 / digit2;
                case "*" -> digitAns = digit1 * digit2;
            }
            // проверяем, чтобы ответ при римском вводе не был меньше 0
            if (digitAns <= 0) {
                System.err.println("Roman number can't be equal and less than 0");
                System.exit(0);
            }
            // печатаем ответ
            System.out.println(RomanNumerals(digitAns));

//            String[] ansDigits = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", ""}

        }


    }
    // Не знал как записать в массив все римские цифры до 100, нашел метод в интернете, который делает этот переход
    public static String RomanNumerals(int Int) {
        LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
        roman_numerals.put("M", 1000);
        roman_numerals.put("CM", 900);
        roman_numerals.put("D", 500);
        roman_numerals.put("CD", 400);
        roman_numerals.put("C", 100);
        roman_numerals.put("XC", 90);
        roman_numerals.put("L", 50);
        roman_numerals.put("XL", 40);
        roman_numerals.put("X", 10);
        roman_numerals.put("IX", 9);
        roman_numerals.put("V", 5);
        roman_numerals.put("IV", 4);
        roman_numerals.put("I", 1);
        StringBuilder res = new StringBuilder();
        for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
            int matches = Int/entry.getValue();
            res.append(repeat(entry.getKey(), matches));
            Int = Int % entry.getValue();
        }
        return res.toString();
    }
    public static String repeat(String s, int n) {
        if(s == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
