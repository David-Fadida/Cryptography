import Encryption.AES3;
import Exploitation.ExploitAES3;
import Exploitation.IExploitation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    /**
     * Program main function
     *
     * @param args - command flags
     */
    public static void main(String[] args) {
        switch (args[0]) {
            case "-e":
                if (args[1].equals("-k")) {
                    String keys = args[2];
                    if (args[3].equals("-i")) {
                        String input = args[4];
                        if (args[5].equals("-o")) {
                            String output = args[6];
                            writeToFile(Paths.get(output), encrypt(Paths.get(input), Paths.get(keys)));
                        } else {
                            System.out.println("ERROR : Wrong Flags Or Parameters");
                        }
                    } else {
                        System.out.println("ERROR : Wrong Flags Or Parameters");
                    }
                } else {
                    System.out.println("ERROR : Wrong Flags Or Parameters");
                }
                break;
            case "-d":
                if (args[1].equals("-k")) {
                    String keys = args[2];
                    if (args[3].equals("-i")) {
                        String input = args[4];
                        if (args[5].equals("-o")) {
                            String output = args[6];
                            writeToFile(Paths.get(output), decrypt(Paths.get(input), Paths.get(keys)));
                        } else {
                            System.out.println("ERROR : Wrong Flags Or Parameters");
                        }
                    } else {
                        System.out.println("ERROR : Wrong Flags Or Parameters");
                    }
                } else {
                    System.out.println("ERROR : Wrong Flags Or Parameters");
                }
                break;
            case "-b":
                if (args[1].equals("-m")) {
                    String message = args[2];
                    if (args[3].equals("-c")) {
                        String cipher = args[4];
                        if (args[5].equals("-o")) {
                            String output = args[6];
                            writeToFile(Paths.get(output), combine(exploit(Paths.get(message), Paths.get(cipher))));
                        } else {
                            System.out.println("ERROR : Wrong Flags Or Parameters");
                        }
                    } else {
                        System.out.println("ERROR : Wrong Flags Or Parameters");
                    }
                } else {
                    System.out.println("ERROR : Wrong Flags Or Parameters");
                }
                break;
            default:
                System.out.println("ERROR : Wrong Flags Or Parameters");
                break;
        }
    }

    /**
     * Encrypt function using AES3
     *
     * @param messagePath - path to message
     * @param keysPath    - path to keys
     * @return byte[] cipher
     */
    private static byte[] encrypt(Path messagePath, Path keysPath) {
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
    private static byte[] decrypt(Path cipherPath, Path keysPath) {
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
    private static byte[] readFromFile(Path path) {
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
    private static void writeToFile(Path path, byte[] content) {
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
    private static byte[][] readKeysFromFile(Path path) {
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
    private static byte[][] exploit(Path plainText, Path cipherText) {
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
    private static byte[] combine(byte[][] input) {
        byte[] output = new byte[input.length * input[0].length];
        int index;
        for (int i = 0; i < output.length; i++) {
            index = i / 16;
            output[i] = input[index][i % 16];
        }
        return output;
    }
}
