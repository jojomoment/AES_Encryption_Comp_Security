    package org.example;


    import javax.crypto.*;
    import javax.crypto.spec.IvParameterSpec;
    import java.io.*;
    import java.security.InvalidAlgorithmParameterException;
    import java.security.InvalidKeyException;
    import java.security.NoSuchAlgorithmException;
    import java.util.Base64;
    import java.util.InputMismatchException;
    import java.util.Scanner;


    public class Methods
    {

        //creates secrey key  and encryption cipher
        SecretKey secretKey;
        private Cipher encryptionCipher;

        //main display method
        public void MainMenu()
        {

            // could use switch and case for menu, looks more cleanier

            //ensures menu loop runs atleast oncc
            int input = -1;



            do {
                try {

                    // displaying menu
                    System.out.println(" password encoding software \n" +
                                        "please select an option:\n" +
                                        "1. Encrypt file\n" +
                                        "2. Decrypt file\n" +
                                        "3. Quit application");


                     // user input
                    Scanner keyboard = new Scanner(System.in);
                    input = keyboard.nextInt();

                    if (input == 1)
                    {

                        if(secretKey == null)
                        {
                            // generates secrert key if null
                            init();
                        }



                        System.out.println("Please enter the file name");
                        //user entered file name
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




                    } else if (input == 2)
                    {
                        System.out.println();
                        System.out.println("Please enter the  encrypted file name:");
                        String encryptedFileName = keyboard.next();

                        //getting encrypted data
                            byte [] encryptdData  = filedDataToByteArray(encryptedFileName);


                        //converts users input into  actual secret key object

                        SecretKey userInputKey = null;

                        //loops until user enters right key
                        while(userInputKey == null)
                        {
                            System.out.println("please enter a valid key:\n");
                            String enteredKey = keyboard.next();

                            userInputKey = convertUserInputKey(enteredKey);


                        }

                        // decrypt attempt, if decrypt works, correct key, if not incorrect key
                        byte[] decryptedData = decrypt(encryptdData,userInputKey);

                        // save decrypted data to file
                        saveDecryptedDataToFile(decryptedData);

                        System.out.println("file has been decrypted");
                        System.out.println("file data has ben placed in plaintext.txt file: ");


                    }

                    else if (input == 3)
                    {
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



                } catch (FileNotFoundException e)
                {
                    System.out.println("file not found - please try again: ");
                    System.out.println();

                }

                //if user enters incorect key
                catch (BadPaddingException e)
                {
                        System.out.println("Error Incorrect Key or corrupted ciphertext, please try again: ");
                }

                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

                catch (Exception e)
                {
                    throw new RuntimeException(e);
                }


            }

            while (input != 3);

        }






        public static String validateFileName (String filename)
        {

            if(filename.endsWith(".txt"))
            {

                return filename;
            }

            else
            {

                System.out.println(".txt has been added to your filename so it can be found:");
                System.out.println();
                return filename +".txt" ;
            }

        }

        //converting file data to bytes
        public static byte[] filedDataToByteArray(String filename) throws IOException
        {
            //intialiing the file

            // file object
            File file = new File(filename);

            // opens file and reads raw bytes
            FileInputStream f1 = new FileInputStream(file);

            //creates byte array big enough for data
            byte[]data = new byte[(int) file.length()];


            f1.read(data);

            // closes file
            f1.close();

            return data;

        }


        //generates secretkey
        public void init() throws NoSuchAlgorithmException
        {
            //generating secret key for AES algoritihm
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128);

            secretKey = generator.generateKey();


        }

        //decryption
        //https://www.javacodegeeks.com/2018/03/aes-encryption-and-decryption-in-javacbc-mode.html

        public byte [] decrypt (byte[] fileData ,SecretKey secretKey)
        {
            try
            {
                //getting iv from file
             FileInputStream fis = new FileInputStream("IV.txt");
             byte[] ivByteData = fis.readAllBytes();
             fis.close();

             //creating iv object to use
                IvParameterSpec iv = new IvParameterSpec(ivByteData);

                // creating new cipher
                Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

                    //initalising in decrypt mode
                decryptCipher.init(Cipher.DECRYPT_MODE,secretKey,iv);

                //decypted bytes
               byte [] decyptdByteData = decryptCipher.doFinal(fileData);


                return decyptdByteData;




            }

            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }

            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            catch (NoSuchPaddingException e)
            {
                throw new RuntimeException(e);
            }

            catch (NoSuchAlgorithmException e)
            {
                throw new RuntimeException(e);
            }

            catch (InvalidAlgorithmParameterException e)
            {
                throw new RuntimeException(e);
            }
            catch (InvalidKeyException e)
            {
                throw new RuntimeException(e);
            }
            catch (IllegalBlockSizeException e)
            {
                throw new RuntimeException(e);
            }
            catch (BadPaddingException e)
            {
                throw new RuntimeException(e);
            }


        }

        public byte[] encrypt(byte[] fileData) throws Exception
        {

            //creates cipher in cbc mode with PKCS5Padding
            encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            //initalizing encryption cipher
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



        public static void saveDecryptedDataToFile( byte[] decrytpedByteData) throws Exception
        {

            try (FileOutputStream fos = new FileOutputStream("plaintext.txt"))
            {
                fos.write(decrytpedByteData);
            }

        }

        public static void saveEncryptedDataToFile( String filename,byte[] encryptedData) throws Exception
        {

            try (FileOutputStream fos = new FileOutputStream("ciphertext.txt"))
            {
                fos.write(encryptedData);
            }

        }

        public SecretKey convertUserInputKey(String inputtedKey)
        {
            try {
                //converts users inputed plaintext into bytes
                byte[] usersSecretKey = Base64.getDecoder().decode(inputtedKey);

                // creates new secret key object based off of users input
                return new javax.crypto.spec.SecretKeySpec(usersSecretKey, "AES");
            }
            // if user inputs wrong key format eg a
            catch (IllegalArgumentException e)
            {
                System.out.println("invalid key format entered, please try again");
                System.out.println();
            }

            return null;
        }


    //







    }
