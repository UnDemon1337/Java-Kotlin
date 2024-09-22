public class CaesarCipher {

    public static final int ALPHABET_SIZE = 26;

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + key) % ALPHABET_SIZE + base));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int key) {
        return encrypt(text, ALPHABET_SIZE - (key % ALPHABET_SIZE));
    }
}
