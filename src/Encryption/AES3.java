package Encryption;

public class AES3 extends AEncryption {
    /**
     * Encryption Components
     */
    AEncryption[] encryptors = new AEncryption[3];

    /**
     * Constructor
     */
    public AES3() {
        encryptors[0] = new AES1();
        encryptors[1] = new AES1();
        encryptors[2] = new AES1();
    }

    /**
     * Constructor
     *
     * @param encryptors - Encryption.AEncryption[] of Encryption.AES1
     */
    public AES3(AEncryption[] encryptors) {
        if (encryptors.length != 3) {
            encryptors[0] = new AES1();
            encryptors[1] = new AES1();
            encryptors[2] = new AES1();
            System.out.println("Encryption.AES3 constructor error - wrong input");
            System.out.println("Encryption.AES3 constructor performed default constructor");
        } else {
            this.encryptors = encryptors;
        }
    }

    /**
     * Encrypt byte[] by Encryption.AES3
     *
     * @param plainText - byte[] input
     * @return cipherText
     */
    @Override
    public byte[] encrypt(byte[] plainText) {
        byte[][] blocks = getBlocks(plainText);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = encryptBlock(blocks[i]);
        }
        return combine(blocks);
    }

    /**
     * Encrypt byte[] by Encryption.AES3
     *
     * @param plainText - byte[] input
     * @return cipherText
     */
    private byte[] encryptBlock(byte[] plainText) {
        return encryptors[2].encrypt(encryptors[1].encrypt(encryptors[0].encrypt(plainText)));
    }

    /**
     * Decrypt byte[] by Encryption.AES3
     *
     * @param cipherText - byte[] input
     * @return plainText
     */
    @Override
    public byte[] decrypt(byte[] cipherText) {
        byte[][] blocks = getBlocks(cipherText);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = decryptBlock(blocks[i]);
        }
        return combine(blocks);
    }

    /**
     * Decrypt byte[] by Encryption.AES3
     *
     * @param cipherText - byte[] input
     * @return plainText
     */
    private byte[] decryptBlock(byte[] cipherText) {
        return encryptors[0].decrypt(encryptors[1].decrypt(encryptors[2].decrypt(cipherText)));
    }

    /**
     * Set encryption key
     *
     * @param keys - byte[][] set of keys
     */
    @Override
    public void setKeys(byte[]... keys) {
        if (keys.length != 3) {
            System.out.println("Wrong input - Encryption.AES3 must have 3 keys");
            return;
        }
        encryptors[0].setKeys(keys[0]);
        encryptors[1].setKeys(keys[1]);
        encryptors[2].setKeys(keys[2]);
    }

    /**
     * Split into blocks
     *
     * @param input - input bytes
     * @return byte[][] blocks array
     */
    private byte[][] getBlocks(byte[] input) {
        if (input == null)
            return null;
        byte[][] blocks = new byte[input.length / 16][];
        for (int i = 0; i < blocks.length; i++)
            blocks[i] = new byte[16];
        int keyIndex;
        for (int i = 0; i < input.length; i++) {
            keyIndex = i / 16;
            blocks[keyIndex][i % 16] = input[i];
        }
        return blocks;
    }

    /**
     * Combine function
     *
     * @param input - 2D byte array
     * @return byte[] output -> 1D byte array
     */
    private byte[] combine(byte[][] input) {
        byte[] output = new byte[input.length * input[0].length];
        int index;
        for (int i = 0; i < output.length; i++) {
            index = i / 16;
            output[i] = input[index][i % 16];
        }
        return output;
    }
}
