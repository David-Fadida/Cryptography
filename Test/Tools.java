import Encryption.AES3;
import Exploitation.ExploitAES3;
import Exploitation.IExploitation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Tools {
    /**
     * Encrypt function using AES3
     *
     * @param messagePath - path to message
     * @param keysPath    - path to keys
     * @return byte[] cipher
     */
    public static byte[] encrypt(Path messagePath, Path keysPath) {
        byte[] message = readFromFile(messagePath);
        byte[][] keys = readKeysFromFile(keysPath);
        AES3 testEncryption = new AES3();
        if (keys != null)
            testEncryption.setKeys(keys);
        return testEncryption.encrypt(message);
    }

    /**
     * Decrypt function using AES3
     *
     * @param cipherPath - path to cipher
     * @param keysPath   - path to keys
     * @return byte [] message
     */
    public static byte[] decrypt(Path cipherPath, Path keysPath) {
        byte[] cipher = readFromFile(cipherPath);
        byte[][] keys = readKeysFromFile(keysPath);
        AES3 testEncryption = new AES3();
        if (keys != null)
            testEncryption.setKeys(keys);
        return testEncryption.decrypt(cipher);
    }

    /**
     * Read File
     *
     * @param path - Path path
     * @return byte[] content
     */
    public static byte[] readFromFile(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException IOE) {
            IOE.printStackTrace();
            return null;
        }
    }

    /**
     * Write File
     *
     * @param path    - Path path
     * @param content - byte[] content
     */
    public static void writeToFile(Path path, byte[] content) {
        try {
            Files.write(path, content);
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }

    /**
     * Read keys from keys file
     *
     * @param path - path to keys file
     * @return byte[][] keys
     */
    public static byte[][] readKeysFromFile(Path path) {
        byte[] raw = readFromFile(path);
        if (raw == null)
            return null;
        byte[][] keys = new byte[3][];
        for (int i = 0; i < keys.length; i++)
            keys[i] = new byte[16];
        int keyIndex;
        for (int i = 0; i < 48; i++) {
            keyIndex = i / 16;
            keys[keyIndex][i % 16] = raw[i];
        }
        return keys;
    }

    /**
     * Exploit function
     *
     * @param plainText  - input massage
     * @param cipherText - input cipher
     * @return byte[][] extracted keys
     */
    public static byte[][] exploit(Path plainText, Path cipherText) {
        byte[] message = readFromFile(plainText);
        byte[] cipher = readFromFile(cipherText);
        IExploitation exploitAES3 = new ExploitAES3();
        return exploitAES3.exploit(message, cipher);
    }

    /**
     * Combine function
     *
     * @param input - 2D byte array
     * @return byte[] output -> 1D byte array
     */
    public static byte[] combine(byte[][] input) {
        byte[] output = new byte[input.length * input[0].length];
        int index;
        for (int i = 0; i < output.length; i++) {
            index = i / 16;
            output[i] = input[index][i % 16];
        }
        return output;
    }
}
