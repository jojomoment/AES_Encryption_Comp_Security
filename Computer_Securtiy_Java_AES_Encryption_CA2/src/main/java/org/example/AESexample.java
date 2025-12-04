package org.example;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

public class AESexample
{
public static void main (String [] args) throws Exception {
    //input from keyboard

    Scanner keyboard  = new Scanner(System.in);

    String plainText =  keyboard.nextLine();

    //generate key for alogrithim
    // gets key for aes algortithim
    KeyGenerator generatedKey = KeyGenerator.getInstance("AES");
    generatedKey.init(256); // keysize 256 bytes

    // creating iv value, iv is
    byte[] iv = new byte[16];
    Random random = new Random();
    random.nextBytes(iv);
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    //secret key
    SecretKey secretKey = generatedKey.generateKey();

    //Aes cipher instance
    Cipher cipher = Cipher.getInstance("AES");

    // pass secret key
    cipher.init(Cipher.ENCRYPT_MODE,secretKey);

    //start encryption
   byte[] encryptedBytes =  cipher.doFinal(plainText.getBytes());

   //encode encrypted data in bae64 format for more readabiltiy
    String encrytpedText = Base64.getEncoder().encodeToString(encryptedBytes);

    System.out.println("encrypted text using AES: "+encrytpedText);

}
}
