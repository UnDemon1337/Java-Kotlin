//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Введите номер задачи котоырй хотите проверить: ");
            int number = scanner.nextInt();
            switch (number) {
                case (1):
                    System.out.println("Введите 1 число для нахождения максимального из этих 2: ");
                    int number1 = scanner.nextInt();
                    System.out.println("Введите 2 число для нахождения максимального из этих 2: ");
                    int number2 = scanner.nextInt();
                    try {
                        maxoftwo(number1,number2);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case (2):
                    System.out.println("Введите Частное: ");
                    double number3 = scanner.nextDouble();
                    System.out.println("Введите делитель не равный 0: ");
                    double number4 = scanner.nextDouble();
                    DivisionCalculator(number3, number4);
                    break;
                case (3):
                    System.out.println("Напиши строку: ");
                    String string = scanner1.nextLine();
                    StringToIntConverter(string);
                    break;
                case (4):
                    System.out.println("Напиши возраст от 0 до 150: ");
                    int number5 = scanner.nextInt();
                    AgeValidator(number5);
                    break;
                case (5):
                    System.out.println("Введите число для нахождения корня : ");
                    double number6 = scanner.nextDouble();
                    SquareRootCalculator(number6);
                    break;
                case (6):
                    System.out.println("Напиши число для нахождения его факториала: ");
                    int number7 = scanner.nextInt();
                    FactorialCalculator(number7);
                    break;
                case (7):
                    System.out.println("Введите величину массива: ");
                    int size = scanner.nextInt();
                    int[] array = new int[size];
                    for (int i = 0; i < size; i++) {
                        System.out.println("Введите число массива не равное 0: ");
                        array[i] = scanner.nextInt();
                    }
                    checkForZeros(array);
                    break;
                case (8):
                    System.out.println("введите число для возведения в степенень: ");
                    double base = scanner.nextInt();
                    System.out.println("введите степень: ");
                    int exp = scanner.nextInt();
                    PowerCalculator(base, exp);
                    break;
                case (9):
                    System.out.println("Напиши строку: ");
                    String string2 = scanner1.nextLine();
                    System.out.println("Напиши на сколько элементов ее обрезать: ");
                    int length = scanner.nextInt();
                    Stringsub(string2, length);
                    break;
                case (10):
                    System.out.println("Введите величину массива: ");
                    int size1 = scanner.nextInt();
                    int[] array1 = new int[size1];
                    for (int i = 0; i < size1; i++) {
                        System.out.println("Введите число массива не равное 0: ");
                        array1[i] = scanner.nextInt();
                    }
                    System.out.println("Введите элемент для поиска: ");
                    int el = scanner.nextInt();
                    findElement(array1, el);
                    break;
                case (11):
                    System.out.println("Введите не отриц число: ");
                    int el1 = scanner.nextInt();
                    toBinary(el1);
                    break;
                case (12):
                    System.out.println("Введите 1 число: ");
                    int number8 = scanner.nextInt();
                    System.out.println("Введите 2 число: ");
                    int number9 = scanner.nextInt();
                    isDivisible(number8,number9);
                    break;
                case (13):
                    System.out.println("Введите величину Листа: ");
                    int size2 = scanner.nextInt();
                    List<Integer> list1 = new ArrayList<>();
                    for (int i = 0; i < size2; i++) {
                        System.out.println("Введите число: ");
                        int number10 = scanner.nextInt();
                        list1.add(number10);
                    }
                    System.out.println("Введите элемент для поиска: ");
                    int el3 = scanner.nextInt();
                    getElement(list1, el3);
                    break;
                case (14):
                    System.out.println("Введите строку больше 8: ");
                    String str = scanner1.nextLine();
                    try {
                        validatePassword(str);
                    } catch (WeakPasswordException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case (15):
                    System.out.println("Введите строку в формате dd.MM.yyyy: ");
                    String str1 = scanner1.nextLine();
                    validateDate(str1);
                    break;
                case (16):
                    System.out.println("Введите строку: ");
                    String str2 = scanner1.nextLine();
                    System.out.println("Введите строку 2: ");
                    String str3 = scanner1.nextLine();
                    concatenate(str2, str3);
                    break;
                case (17):
                    System.out.println("введите число 1: ");
                    int int1 = scanner.nextInt();
                    System.out.println("введите число 2 не равно 0: ");
                    int int2 = scanner.nextInt();
                    ModulusCalculator(int1, int2);
                    break;
                case (18):
                    System.out.println("введите число для нахожднения корня : ");
                    double base1 = scanner.nextInt();
                    calculateSquareRoot(base1);
                    break;
                case (19):
                    System.out.println("введите темпу в цельсиях: ");
                    double base2 = scanner.nextInt();
                    celsiusToFahrenheit(base2);
                    break;
                case (20):
                    System.out.println("Введите строку: ");
                    String str4 = scanner1.nextLine();
                    validateString(str4);
                    break;
                default:
                    break;
            }
        }
    }
    public static int maxoftwo(int a, int b) throws Exception {
        if (a == b) {
            throw new Exception("Числа равны");
        }
        return Math.max(a, b);
    }
    public static double DivisionCalculator(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return a / b;
    }
    public static int StringToIntConverter(String str){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Невозможно конвертировать строку в число");
        }
    }
    public static void AgeValidator(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Возраст должен быть между 0 и 150");
        }
    }
    public static double SquareRootCalculator(double num) {
        if (num < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        return Math.sqrt(num);
    }
    public static int FactorialCalculator (int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    public static void checkForZeros(int [] array) {
        for (int num : array) {
            if (num == 0) {
                throw new IllegalArgumentException("Массив содержит нули");
            }
        }
    }
    public static double PowerCalculator(double base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Степень не может быть отрицательной");
        }
        return Math.pow(base, exponent);
    }
    public static String Stringsub(String str, int length) {
        if (length > str.length()) {
            throw new IllegalArgumentException("Длина обрезки больше длины строки");
        }
        return str.substring(0, length);
    }
    public static int findElement(int[] array, int element) {
        for (int num : array) {
            if (num == element) {
                return num;
            }
        }
        throw new IllegalArgumentException("Элемент не найден");
    }
    public static String toBinary(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Число должно быть неотрицательным");
        }
        return Integer.toBinaryString(number);
    }
    public static boolean isDivisible(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return a % b == 0;
    }
    public static <Integer> Integer getElement(List<Integer> list, int index) {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException("Индекс за пределами списка");
        }
        return list.get(index);
    }
    public static void validatePassword(String password) throws WeakPasswordException {
        if (password.length() < 8) {
            throw new WeakPasswordException("Пароль слишком короткий");
        }
    }
    public static void validateDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Неверный формат даты");
        }
    }
    public static String concatenate(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("Одна из строк равна null");
        }
        return str1 + str2;
    }
    public static int ModulusCalculator (int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return a % b;
    }
    public static double calculateSquareRoot(double num) {
        if (num < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        return Math.sqrt(num);
    }
    public static double celsiusToFahrenheit(double celsius) {
        double absoluteZeroCelsius = -273.15;
        if (celsius < absoluteZeroCelsius) {
            throw new IllegalArgumentException("Температура ниже абсолютного нуля");
        }
        return celsius * 9/5 + 32;
    }
    public static void validateString(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Строка пуста или равна null");
        }
    }
}
