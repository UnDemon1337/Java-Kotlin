import java.io.IOException;

public class CaesarCipherApp {

    public static void encryptFile(String inputFilePath, String outputFilePath, int key) throws IOException {
        String content = FileHandler.readFile(inputFilePath);
        String encryptedContent = CaesarCipher.encrypt(content, key);
        FileHandler.writeFile(outputFilePath, encryptedContent);
    }

    public static void decryptFile(String inputFilePath, String outputFilePath, int key) throws IOException {
        String content = FileHandler.readFile(inputFilePath);
        String decryptedContent = CaesarCipher.decrypt(content, key);
        FileHandler.writeFile(outputFilePath, decryptedContent);
    }
}
