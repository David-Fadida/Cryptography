public interface IDecryption {

    byte[] decrypt(byte[] cipherText);

    void setKeys(byte[][] keys);
}
