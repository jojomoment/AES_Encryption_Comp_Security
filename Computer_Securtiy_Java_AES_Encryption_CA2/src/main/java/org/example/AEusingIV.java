package org.example;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class AEusingIV
{

    SecretKey secretKey;
    private Cipher encryptionCipher;

    public void init() throws NoSuchAlgorithmException
    {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);

        secretKey = generator.generateKey();
    }

    //replace with file byte array
    public byte[] encrypt(byte[] fileData) throws Exception
    {

        encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = encryptionCipher.doFinal(fileData);
        return Base64.getEncoder().encodeToString(encryptedBytes).getBytes();

    }


//    public String decrypt(String encryptedMessage) throws Exception
//    {
//
//        byte[] messageInBytes = Base64.getDecoder().decode(encryptedMessage);
//
//        Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        IvParameterSpec ivSpec = new IvParameterSpec(encryptionCipher.getIV());
//
//        decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
//        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
//
//        return new String(decryptedBytes);
//
//    }

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner (System.in);
        System.out.println("Enter file name :");
        String filename = keyboard.nextLine();

        byte[] fileByteData =filedDataToByteArray(filename);



        try {
            AEusingIV aus = new AEusingIV();
            aus.init();
            byte[] encryptedData = aus.encrypt(fileByteData);

            System.out.println("byte data  :");

            for(int i=0;i<encryptedData.length;i++)
            {

                System.out.print(encryptedData[i]+ " ");

            }

            System.out.println();



//            String encrypted = aus.encrypt("Hello World");
//            String decrypted = aus.decrypt(encrypted);

//            System.out.println("Encrypted: " + encrypted);
//            System.out.println("Decrypted: " + decrypted);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] filedDataToByteArray(String filename) throws FileNotFoundException, IOException
    {
        //intialiing the file
        File file = new File(filename);

        FileInputStream f1 = new FileInputStream(file);

        byte[]data = new byte[(int) file.length()];
        f1.read(data);
        f1.close();

        return data;


    }
}
