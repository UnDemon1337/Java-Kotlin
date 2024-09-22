import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим работы:");
        System.out.println("1 - Шифрование");
        System.out.println("2 - Расшифровка с известным ключом");
        System.out.println("3 - Brute Force расшифровка");
        int mode = scanner.nextInt();

        // Ввод и валидация пути к файлу
        System.out.println("Введите путь к файлу:");
        String inputFile = scanner.next();
        if (!InputValidator.validateFileExists(inputFile)) {
            System.out.println("Файл не существует. Пожалуйста, укажите правильный файл.");
            return;
        }

        System.out.println("Введите путь для сохранения результата (если применимо):");
        String outputFile = scanner.next();

        try {
            if (mode == 1) {
                // Шифрование
                System.out.println("Введите ключ для шифрования (от 0 до 25):");
                int key = scanner.nextInt();
                if (InputValidator.ValidateKey(key)) {
                    System.out.println("Недопустимый ключ. Пожалуйста, введите значение от 0 до 25.");
                    return;
                }
                CaesarCipherApp.encryptFile(inputFile, outputFile, key);
                System.out.println("Файл зашифрован.");
            } else if (mode == 2) {
                // Расшифровка с известным ключом
                System.out.println("Введите ключ для расшифровки (от 0 до 25):");
                int key = scanner.nextInt();
                if (InputValidator.ValidateKey(key)) {
                    System.out.println("Недопустимый ключ. Пожалуйста, введите значение от 0 до 25.");
                    return;
                }
                CaesarCipherApp.decryptFile(inputFile, outputFile, key);
                System.out.println("Файл расшифрован.");
            } else if (mode == 3) {
                String encryptedText = FileHandler.readFile(inputFile);
                System.out.println("Попытка brute force расшифровки:");
                BruteForceAttack.bruteForce(encryptedText);
            } else {
                System.out.println("Неверный режим работы.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }
}
