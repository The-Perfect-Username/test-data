import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.*;

public class MainProgram {

   /* This is my first java program.
    * This will print 'Hello World' as the output
    */

    public static void main(String []args) throws IOException  {
        MainProgram m = new MainProgram();
        m.readFileToList("./source/input.txt", "\\s*\\.\\s*");
    }

    private void readFileToList(String input, String delimiter) {

        try {
            // Read entire file into a string
            String content = new Scanner(new File(input)).useDelimiter("\\Z").next();
            // Remove all newlines to join paragraphs
            content = content.replace("\n", "");
            // Tokenise string by breaking up each sentence into Array elements
            List<String> myList = new ArrayList<String>(Arrays.asList(content.split(delimiter)));
            // Print out each array element
            for (String s: myList) {
                System.out.println(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    

}
