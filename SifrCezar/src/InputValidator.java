import java.io.File;

public class InputValidator {

    public static boolean ValidateKey(int key) {
        return key < 0 || key > 25;
    }

    public static boolean validateFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }
}
