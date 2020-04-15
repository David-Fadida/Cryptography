public class AES3 extends AEncryption{

    AEncryption encryptors[] = new AEncryption[3];

    public AES3() {
        for (AEncryption encryption: encryptors) {
            encryption = new AES1();
        }
    }

    public AES3(AEncryption[] encryptors) {
        if (encryptors.length != 3){
            System.out.println("AEncryptors array must have 3 encryptors");
            for (AEncryption encryption: encryptors) {
                encryption = new AES1();
            }
        }
        else{
            this.encryptors = encryptors;
        }
    }

    @Override
    public byte[] encrypt(byte[] plainText) {

        return plainText;
    }

    @Override
    public byte[] decrypt(byte[] cipherText) {

        return cipherText;
    }

    @Override
    public void setKeys(byte[][] keys) {
//        if(keys.length == encryptors.length)
    }

}
