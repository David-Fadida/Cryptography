import java.nio.file.Paths;
import java.util.Arrays;

public class DecryptionTests {
    /**
     * Components
     */
    private static byte[] testMessageLong;
    private static byte[] testMessageShort;

    /**
     * Test Decryption main function
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
        String cipherPathLong = "D:/testFiles/cipher_long";
        String keysPathLong = "D:/testFiles/key_long";
        testMessageLong = Tools.decrypt(Paths.get(cipherPathLong), Paths.get(keysPathLong));
        String cipherPathShort = "D:/testFiles/cipher_short";
        String keysPathShort = "D:/testFiles/key_short";
        testMessageShort = Tools.decrypt(Paths.get(cipherPathShort), Paths.get(keysPathShort));
    }

    /**
     * Test Long
     */
    private static void testSolutionLong() {
        String messagePathLong = "D:/testFiles/message_long";
        byte[] actualMessageLong = Tools.readFromFile(Paths.get(messagePathLong));
        if(Arrays.equals(testMessageLong, actualMessageLong))
            System.out.println("Test Decryption Long - PASSED !");
        else
            System.out.println("Test Decryption Long - FAILED !");
    }

    /**
     * Test Short
     */
    private static void testSolutionShort() {
        String messagePathShort = "D:/testFiles/message_short";
        byte[] actualMessageShort = Tools.readFromFile(Paths.get(messagePathShort));
        if(Arrays.equals(testMessageShort, actualMessageShort))
            System.out.println("Test Decryption Short - PASSED !");
        else
            System.out.println("Test Decryption Short - FAILED !");
    }
}
