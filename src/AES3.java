public class AES3 extends AEncryption{
    /** Encryption Components */
    AEncryption encryptors[] = new AEncryption[3];

    /**
     * Constructor
     */
    public AES3() {
        for (AEncryption encryption: encryptors) {
            encryption = new AES1();
        }
    }

    /**
     * Constructor
     * @param encryptors - AEncryption[] of AES1
     */
    public AES3(AEncryption[] encryptors) {
        if (encryptors.length != 3){
            for (AEncryption encryption: encryptors) {
                encryption = new AES1();
            }
            System.out.println("AES3 constructor error - wrong input");
            System.out.println("AES3 constructor performed default constructor");
        }
        else{
            this.encryptors = encryptors;
        }
    }

    /**
     * Encrypt byte[] by AES3
     * @param plainText - byte[] input
     * @return cipherText
     */
    @Override
    public byte[] encrypt(byte[] plainText) {
        return encryptors[2].encrypt(encryptors[1].encrypt(encryptors[0].encrypt(plainText)));
    }

    /**
     * Decrypt byte[] by AES3
     * @param cipherText - byte[] input
     * @return plainText
     */
    @Override
    public byte[] decrypt(byte[] cipherText) {
        return encryptors[0].decrypt(encryptors[1].decrypt(encryptors[2].decrypt(cipherText)));
    }

    /**
     * Set encryption key
     * @param keys - byte[][] set of keys
     */
    @Override
    public void setKeys(byte[]... keys) {
        if(keys.length != 3) {
            System.out.println("Wrong input - AES3 must have 3 keys");
            return;
        }
        encryptors[0].setKeys(keys[0]);
        encryptors[1].setKeys(keys[1]);
        encryptors[2].setKeys(keys[2]);
    }

}
