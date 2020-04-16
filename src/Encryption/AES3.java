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

}
