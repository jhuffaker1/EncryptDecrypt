import java.io.*;

public class FileEncryptor {
    public FileEncryptor() {

    }

    public static String encryptFile(File inputFileName, String key) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        System.out.println(content + " check here");
        String encryptedHex = "";
        for (int i = 0; i < content.length(); i++) {
            int charInteger = content.charAt(i) ^ key.charAt(i % key.length());
            encryptedHex += String.format("%02x", (byte) charInteger);
        }
        System.out.println(encryptedHex);
        return encryptedHex;
    }

    public static void decryptFile(String encryptedText, String key) {
        System.out.println("here 001");
        String hexToDeci = "";
        for (int i = 0; i < encryptedText.length() - 1; i += 2) {
            // splitting hex into a pair of two
            String output = encryptedText.substring(i, (i + 2));
            int decimal = Integer.parseInt(output, 16);
            hexToDeci += (char) decimal;
        }
        System.out.println(hexToDeci);
        // decryption
        String decrypText = "";
        int keyItr = 0;
        for (int i = 0; i < hexToDeci.length(); i++) {
            // XOR Operation
            int temp = hexToDeci.charAt(i) ^ key.charAt(keyItr);

            decrypText += (char) temp;
            keyItr++;
            if (keyItr >= key.length()) {
                // once all of key's letters are used, repeat the key
                keyItr = 0;
            }
        }
        System.out.println("Decrypted Text: " + decrypText);
    }
}