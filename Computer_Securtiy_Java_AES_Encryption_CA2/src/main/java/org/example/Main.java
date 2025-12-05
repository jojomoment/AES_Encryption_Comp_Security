package org.example;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

//https://www.geeksforgeeks.org/java/java-program-to-convert-file-to-a-byte-array/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException
    {
//        Methods j = new Methods();

Scanner keyboard = new Scanner (System.in);
System.out.println("Enter file name :");
String filename = keyboard.nextLine();

  byte[] fileByteData =filedDataToByteArray(filename);


 // checking if encryption is working
  System.out.println("byte data  :");

  for(int i=0;i<fileByteData.length;i++)
  {

      System.out.print(fileByteData[i]+ " ");

  }



  try{
      AEusingIV aes = new  AEusingIV();
      aes.init();


      // encrypts file bytes
      byte[] encryptedData = aes.encrypt(fileByteData);

      // Source - https://stackoverflow.com/a
// Posted by bmargulies, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-05, License - CC BY-SA 3.0

      try (FileOutputStream fos = new FileOutputStream("ciphertext.txt"))
      {
          fos.write(encryptedData);
          //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
      }


  } catch (Exception e)
  {
      throw new RuntimeException(e);
  }

        byte[] savedBytes = java.nio.file.Files.readAllBytes(new File("ciphertext.txt").toPath());

        System.out.println("Bytes in ciphertext.bin:");
        for (int i = 0; i < savedBytes.length; i++)
        {
            System.out.print(savedBytes[i] + " ");

        }
        System.out.println();




//        j.MainMenu();
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
