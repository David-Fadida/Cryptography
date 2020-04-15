public class AES1 extends AEncryption {
    /** KEY */
    private byte[] key;

    /**
     * Constructor
     */
    public AES1() {

    }

    /**
     * Constructor
     * @param key - byte[] key
     */
    public AES1(byte[] key) {
        this.key = key;
    }

    /**
     * Encrypt byte[] by AES
     * @param plainText - byte[] input
     * @return cipherText
     */
    @Override
    public byte[] encrypt(byte[] plainText) {
        return addRoundKey(shiftColumns(plainText), this.key);
    }

    /**
     * Decrypt byte[] by AES
     * @param cipherText - byte[] input
     * @return plainText
     */
    @Override
    public byte[] decrypt(byte[] cipherText) {
        return reShiftColumns(addRoundKey(cipherText, this.key));
    }

    /**
     * Set encryption key
     * @param keys - byte[][] set of keys
     */
    @Override
    public void setKeys(byte[][] keys) {
        this.key = keys[0];
    }

    /**
     * Operation shift columns
     * @param input - byte[] input
     * @return shifted input
     */
    private static byte[] shiftColumns(byte[] input){
        byte[] output = new byte[16];
        for (int i = 0; i < 16; i++) {
            output[shift(i)] = input[i];
        }
        return output;
    }

    /**
     * Operation shift
     * @param i - int index
     * @return new index
     */
    private static int shift(int i) {
        int times = i/4;
        int place = (i-times)%4;
        return place+times*4;
    }

    /**
     * Operation add round key
     * @param input - byte[] input
     * @param key - byte[] key
     * @return input XOR key
     */
    private byte[] addRoundKey(byte[] input, byte[] key) {
        byte[] output = new byte[16];
        int i = 0;
        for (byte b : input){
            output[i] = (byte)(b^key[i]);
            i++;
        }
        return output;
    }

    /**
     * Operation re-shift columns
     * @param input - byte[] input
     * @return re-shifted input
     */
    private static byte[] reShiftColumns(byte[] input){
        byte[] output = new byte[16];
        for (int i = 0; i < 16; i++) {
            output[reShift(i)] = input[i];
        }
        return output;
    }

    /**
     * Operation re-shift
     * @param i - int index
     * @return new index
     */
    private static int reShift(int i) {
        int times = i/4;
        int place = (i+times)%4;
        return place+times*4;
    }
}
