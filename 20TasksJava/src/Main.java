//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер задачи котоырй хотите проверить: ");
            int number = scanner.nextInt();
            switch (number) {
                case (1):
                    EvenOddChecker();
                    break;
                case (2):
                    MinOfThree();
                    break;
                case (3):
                    MultiplicationTable();
                    break;
                case (4):
                    SumToN();
                    break;
                case (5):
                    Fibonacci();
                    break;
                case (6):
                    SimpleOrNote();
                    break;
                case (7):
                    Reverse();
                    break;
                case (8):
                    EvenSum();
                    break;
                case (9):
                    StringReversal();
                    break;
                case (10):
                    DigitCount();
                    break;
                case (11):
                    Factorial();
                    break;
                case (12):
                    SumOfDigits();
                    break;
                case (13):
                    Palindrome();
                    break;
                case (14):
                    MaxInArray();
                    break;
                case (15):
                    ArraySum();
                    break;
                case (16):
                    PositiveNegativeCount();
                    break;
                case (17):
                    PrimeNumbersInRange();
                    break;
                case (18):
                    VowelsConsonantsCount();
                    break;
                case (19):
                    ReverseWords();
                    break;
                case (20):
                    ArmstrongNumber();
                    break;
                default:
                    break;
            }
        }
    }
    public static void EvenOddChecker() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        System.out.println(num % 2 == 0 ? "Четное": "Нечетное");

    }
    public static void MinOfThree() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        int min = Math.min(a, Math.min(b, c));
        System.out.println("Минимальное число: " + min);
    }
    public static void MultiplicationTable() {
        for (int i = 1; i < 11; i++) {
            System.out.println("5 * " + i + " = " + (5 * i));
        }
    }
    public static void SumToN() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        System.out.println("Сумма: " + sum);
    }
    public static void Fibonacci() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }
    public static void SimpleOrNote() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        boolean isPrime = num > 1;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }

        System.out.println(isPrime ? "Простое": "Не простое");
    }
    public static void Reverse() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        for (int i = n; i > 0; i--) {
            System.out.print(i + " ");
        }
    }
    public static void EvenSum() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int sum = 0;

        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        System.out.println("Сумма четных чисел: " + sum);
    }
    public static void StringReversal() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);
    }
    public static void DigitCount() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        System.out.println("Количество цифр: " + count);
    }
    public static void Factorial() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println("Факториал: " + factorial);
    }
    public static void SumOfDigits() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int sum = 0;

        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        System.out.println("Сумма цифр: " + sum);
    }
    public static void Palindrome() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String reversed = new StringBuilder(str).reverse().toString();

        System.out.println(str.equals(reversed) ? "Палиндром": "Не палиндром");
    }
    public static void MaxInArray() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int max = -2147483647;
        for (int i = 1; i < size; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        System.out.println("Максимальное число: " + max);
    }
    public static void ArraySum() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        int sum = 0;

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
            sum += array[i];
        }

        System.out.println("Сумма элементов массива: " + sum);
    }
    public static void PositiveNegativeCount() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        int positiveCount = 0, negativeCount = 0;

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
            if (array[i] > 0) {
                positiveCount++;
            } else if (array[i] < 0) {
                negativeCount++;
            }
        }

        System.out.println("Положительных чисел: " + positiveCount);
        System.out.println("Отрицательных чисел: " + negativeCount);
    }
    public static void PrimeNumbersInRange() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        for (int i = a; i <= b; i++) {
                System.out.print(isPrime(i) ? i : "NO");
            }
        }
    public static boolean isPrime(int num) {
        if (num <= 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void VowelsConsonantsCount() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String vowels = "aeiouAEIOU";
        String consonants = "qrtpsdfghjklzxcvbnmwyQRTPSDFGHJKLZXCVBNMWY";
        int vowelsCount = 0, consonantsCount = 0;

        for (char el : str.toCharArray()){
            if(vowels.indexOf(el) != -1){
                vowelsCount++;
            }
            else if(consonants.indexOf(el) != -1){
                consonantsCount++;
            }
        }
        System.out.println("This string has " + vowelsCount + " vowels and " + consonantsCount + " consonants");
    }
    public static void ReverseWords() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] words = input.split(" ");
        StringBuilder reversed = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]).append(" ");
        }

        System.out.println(reversed.toString().trim());
    }
    public static void ArmstrongNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int originalNumber = number;
        int sum = 0;
        int digits = String.valueOf(number).length();

        while (number > 0) {
            int digit = number % 10;
            sum += (int) Math.pow(digit, digits);
            number /= 10;
        }

        System.out.println(sum == originalNumber ? originalNumber + " является числом Армстронга.": originalNumber + " не является числом Армстронга.");
    }
}
