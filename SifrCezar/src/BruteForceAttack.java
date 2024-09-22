public class BruteForceAttack {

    public static void bruteForce(String encryptedText) {
        for (int key = 0; key < CaesarCipher.ALPHABET_SIZE; key++) {
            String decrypted = CaesarCipher.decrypt(encryptedText, key);
            System.out.println("Key " + key + ": " + decrypted);
        }
    }
}
