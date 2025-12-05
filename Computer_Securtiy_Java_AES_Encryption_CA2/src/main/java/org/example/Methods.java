package org.example;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Methods
{

    SecretKey secretKey;
    private Cipher encryptionCipher;

    //making main menu
    public void MainMenu()
    {
// prob need to add try and catch, stuff along those lines to ensure code dosent break
        // what if file is already encrypted , would js need to decrypt
        // alow user to rencrypt decrypted file?
        // could implement a timeout if file isnt found after period of time
        // could use switch and case for menu, looks more cleanier

        int input = -1;

//        int test = Encryption.test();
//
//        System.out.println("testing cross method :"+test);







// gets re prompted to enter input, had to enter in quit input
// i called main menu in which made it need to enter it twice
        // how to tell user if input is inavlid menu range eg 10 20


        do {
            try {

                System.out.println(" password encoding software \n" +
                        "please select an option:\n" +
                        "1. Encrypt file\n" +
                        "2. Decrypt file\n" +
                        "3. Quit application");



                Scanner keyboard = new Scanner(System.in);
                input = keyboard.nextInt();

                if (input == 1) {

                    if(secretKey == null)
                    {
                        // generates secrert key if null
                        init();
                    }

                   // state what characters are valid
                    // access file
                    // convert file data into bytes
                    // save encrypted file, so that can decrypt
                    // re display main menu, while having encrypted file saved in background
                    // how do i prove file  is encrypted
                    // what characters are allowed for the filename
                    //dont allow spaces

                    System.out.println("Please enter the file name");
                    String fileName = keyboard.next();

                    //validataes file name
                    fileName =validateFileName(fileName);




                    //turns file data into array, encrypts data
                   byte[] encrypedData =  encrypt( filedDataToByteArray(fileName));



                    //save encrypted data to file
                    saveEncryptedDataToFile(fileName,encrypedData);

                    // displaying secret key
                    String readableSecretKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
                    System.out.println("secret key; "+readableSecretKey);

                    System.out.println("file has been encrypted");

                    System.out.println();


                    //redisplaying menu


                } else if (input == 2) {
                    //ask user to enter encrypted file
                    // state whether  vaild or not
                    //check if file is actually encrypted
                    // decrypt file, and save in background
                    // confirmation message
                    // redisplay main menu


                } else if (input == 3) {
                    //checking if code will end
                    System.out.println("Thank you for using  our software.\n" +
                            " See you again soon!!!");
                    System.out.println();

                }
                else
                {
                    System.out.println(+input+ " is not a valid menu option, please try again:");
                    System.out.println();
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("invalid input - please try again: ");
                System.out.println();
//            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }

        while (input != 3);

    }

    //generate random encryption key for file
    //aes is a symetric key
    //aes has shifting of bytes
    //




    //validating entered file

    public static String validateFileName (String filename)
    {
        if(filename.endsWith(".txt"))
        {

            return filename;
        }
        else
        {
            //forgot to include the .txt
            System.out.println(".txt has been added to your filename so it can be found:");
            System.out.println();
            return filename +".txt" ;
        }

    }

    //converting file data to bytes
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

    public void init() throws NoSuchAlgorithmException
    {
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);

        secretKey = generator.generateKey();




    }

    public byte[] encrypt(byte[] fileData) throws Exception
    {

        encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = encryptionCipher.doFinal(fileData);


        //generating and saving iv, needed for decryption
        byte[] iv = encryptionCipher.getIV();

        try(FileOutputStream fos = new FileOutputStream("IV.txt"))
        {
 fos.write(iv);
        }

        return encryptedBytes;




    }

    //      // Source - https://stackoverflow.com/a
//// Posted by bmargulies, modified by community. See post 'Timeline' for change history
//// Retrieved 2025-12-05, License - CC BY-SA 3.0

    public static void saveEncryptedDataToFile(String filename, byte[] encryptedData) throws Exception
    {

        try (FileOutputStream fos = new FileOutputStream("ciphertext.txt"))
     {
         fos.write(encryptedData);
     }

    }
//







}
