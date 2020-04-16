import java.nio.file.Paths;
import java.util.Arrays;

public class EncryptionTests {
    /**
     * Components
     */
    private static byte[] testCipherLong;
    private static byte[] testCipherShort;

    /**
     * Test Encryption main function
     *
     * @param args - command flags
     */
    public static void main(String[] args) {
        init();
        testSolutionLong();
        testSolutionShort();
    }

    /**
     * Initialization
     */
    private static void init() {
        String messagePathLong = "D:/testFiles/message_long";
        String keysPathLong = "D:/testFiles/key_long";
        testCipherLong = Tools.encrypt(Paths.get(messagePathLong), Paths.get(keysPathLong));
        String messagePathShort = "D:/testFiles/message_short";
        String keysPathShort = "D:/testFiles/key_short";
        testCipherShort = Tools.encrypt(Paths.get(messagePathShort), Paths.get(keysPathShort));
    }

    /**
     * Test Long
     */
    private static void testSolutionLong() {
        String cipherPathLong = "D:/testFiles/cipher_long";
        byte[] actualCipherLong = Tools.readFromFile(Paths.get(cipherPathLong));
        if(Arrays.equals(testCipherLong, actualCipherLong))
            System.out.println("Test Encryption Long - PASSED !");
        else
            System.out.println("Test Encryption Long - FAILED !");
    }

    /**
     * Test Short
     */
    private static void testSolutionShort() {
        String cipherPathShort = "D:/testFiles/cipher_short";
        byte[] actualCipherShort = Tools.readFromFile(Paths.get(cipherPathShort));
        if(Arrays.equals(testCipherShort, actualCipherShort))
            System.out.println("Test Encryption Short - PASSED !");
        else
            System.out.println("Test Encryption Short - FAILED !");
    }
}
