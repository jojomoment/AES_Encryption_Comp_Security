package org.example;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Methods
{
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
                    // does redisplay menu
                    // user enters file name, state what characters are valid
                    // check wheter its vaild or not, exception handeling
                    // confirmation message
                    // access file
                    // convert file data into bytes

                    // save encrypted file, so that can decrypt
                    // re display main menu, while having encrypted file saved in background
                    // how do i prove file  is encrypted
                    // what characters are allowed for the filename
                    //dont allow spaces

                    System.out.println("Please enter the file name");
                    String fileName = keyboard.nextLine();
                    fileName =validateFileName(fileName);
                    System.out.println("Searching for file:");
                    //access file, turn data into bytes
//                      readTextFile(fileName);


                    System.out.println(fileName + " has been found:");
//                    Encryption.encryptFile(fileName);
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
            }


        }

        while (input != 3);

    }

    //generate random encryption key for file
    //aes is a symetric key
    //aes has shifting of bytes
    //

    public static int RandomEncryptionKey()
    {
        Random rand = new Random();

        int randomKey = rand.nextInt(10000);

        return randomKey;
    }


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






}
