/*
* Author: Tyrell Robbins
* CSCI 112
* This program will copy folders the files from a source location and move them
* to a new destination location based on the source and lactation that the user will specify
*/
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Driver {
    public static void main(String[] args) throws Exception{

        // Create a scanner object to make user input
        Scanner userInput = new Scanner(System.in);

        // get source location from user
        System.out.println("Please enter the location of the source file: ");
        String sourceName = userInput.nextLine();
        File sourceFolder = new File(sourceName);

        // this will get the destination information to place the file from the user
        System.out.println("Please enter the destination location to move files to: ");
        String destinationName = userInput.nextLine();
        File destinationFolder = new File(destinationName);

        // this will call the copy method
        copy(sourceFolder, destinationFolder);

    }//end main Method

/*
This method will be responsible for performing the operations to copy and move the files and folders to a new location
 */
    public static void copy(File sourceFolder, File destinationFolder) throws IOException {


        if (sourceFolder.isDirectory()) {
            // Checks if folder already exists, don't make another.
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
                System.out.println("Directory created: " + destinationFolder);
            }

            // create an array to move files from one file location to another.
            String files[] = sourceFolder.list();

            // for loop to copy files from the source to the destination.
            for (String file : files) {
                File sourceLocation = new File(sourceFolder, file);
                File destinationLocation = new File(destinationFolder, file);


                // this recursive call will allow me to check to see if there is any other files to be transferred
                copy(sourceLocation, destinationLocation);
            }
        } else {
            // this will moves the desired data from the source locations to the destination
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);

        }//end else

    }//end copy

}//end Driver Class
