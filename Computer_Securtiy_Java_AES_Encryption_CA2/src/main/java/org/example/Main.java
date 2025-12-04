package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//https://www.geeksforgeeks.org/java/java-program-to-convert-file-to-a-byte-array/

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
//        Methods j = new Methods();

Scanner keyboard = new Scanner (System.in);
System.out.println("Enter file name :");
String filename = keyboard.nextLine();

  readTextFile(filename);


//        j.MainMenu();
        }

    public static void readTextFile(String  filename ) throws FileNotFoundException, IOException {
        File file = new File(filename);

        try (Scanner reader = new Scanner (file))
        {
            while ( reader.hasNextLine())
            {
                String data  = reader.nextLine();
//                System.out.println(data);
                byte[] dataInBytes = data.getBytes();

                for(int i = 0; i < dataInBytes.length; i++)
                {
                    System.out.print(dataInBytes[i]+" ");
                }
                System.out.println();
            }
        }catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }

    }
    }
