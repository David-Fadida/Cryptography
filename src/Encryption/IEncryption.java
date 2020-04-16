package Encryption;

public interface IEncryption {

    byte[] encrypt(byte[] plainText);

    void setKeys(byte[]... keys);
}
