import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in); // Make scanner obj
        String inputPath = "";
        PathChecker myPathChecker = new PathChecker();
        while(true) {
            System.out.println("""
                    0 – Exit
                    1 – Select directory
                    2 – List directory content (first level)
                    3 – List directory content (all levels)
                    4 – Delete file
                    5 – Display file (hexadecimal view)
                    6 – Encrypt file (XOR with password)
                    7 – Decrypt file (XOR with password)
                    Select option:""");
            int userInput = -1;
            try {
                userInput = myScanner.nextInt(); // Take whole line
            } catch (InputMismatchException exception) {
                System.out.println("An Invalid input was passed please try again! \n");
            }
            myScanner.nextLine();
            if (userInput == 0) {
                System.exit(0);
            } else if (userInput == 1) {

                System.out.println("what is the path to the directory? ");
                inputPath = myScanner.nextLine();

            } else if (userInput == 2) {
                if (myPathChecker.checkPath(inputPath)) {
                    try {
                        Files.list(Path.of(inputPath)).forEach(System.out::println);
                    } catch (IOException e) {
                        System.out.println("Error: The path could not be found please try " +
                                "performing option one again.");
                    }
                } else {
                    System.out.println("Error: You can not display the directory contents until after " +
                            "you complete option one to choose a path.");
                }
            } else if (userInput == 3) {
                if (myPathChecker.checkPath(inputPath)) {
                    try {
                        Files.walk(Path.of(inputPath),15).forEach(System.out::println);
                    } catch (IOException e) {
                        System.out.println("Error: The path could not be found please try " +
                                "performing option one again.");
                    }
                } else {
                    System.out.println("Error: You can not display the directory contents until after " +
                            "you complete option one to choose a path.");
                }
            } else if (userInput == 4) {
                System.out.println("Enter the name of the file in this directory " +
                        "that you would like to delete.  ");
                try {
                    String fileName = myScanner.nextLine();
                    fileName = inputPath + "/" + fileName;
                    File fileToDelete = new File(fileName);
                    fileToDelete.delete();
                } catch (Exception ex){
                    System.out.println("Error: The file could not be found to be" +
                            "deleted. ");
                }
            } else if (userInput == 5) {
                System.out.println("Enter the name of the file in this directory " +
                        "that you would like to display in Hex.  ");
                try {
                    String fileName = myScanner.nextLine();
                    fileName = inputPath + "/" + fileName;
                    File fileToDisplay = new File(fileName);
                    String hexFromFile = HexBuilder.getHex(fileToDisplay);
                    System.out.println(hexFromFile.toString());

                } catch (FileNotFoundException ex){
                    System.out.println("Error: The file could not be found to be " +
                            "displayed. ");
                } catch (IOException ex2) {
                    System.out.println("Error: There was an input error found.");
                }
            } else if (userInput == 6) {
                System.out.println("Enter the name of the file in this directory " +
                        "that you would like to encrypt. ");
                try {
                    String fileName = myScanner.nextLine();
                    fileName = inputPath + "/" + fileName;
                    File fileToEncrypt = new File(fileName);
                    System.out.println("What would you like to use as the passkey? ");
                    String myKey = myScanner.nextLine();
                    String encryptedHexa = FileEncryptor.encryptFile(fileToEncrypt, myKey);
                    System.out.println("here");
                    FileEncryptor.decryptFile(encryptedHexa, myKey);
                } catch (IOException e) {
                    System.out.println("Error: an invalid input format was given. ");;
                }
            } else if (userInput == 7) {

            } else {

            }
        }
    }
}