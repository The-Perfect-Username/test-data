import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.io.*;
// import User.User;


public class MainProgram {

    // List to store all of the tokenised sentences from the input file
    private static List<String> sentences = new ArrayList<String>();
    // List to store all token words greater than than 2 characters
    private static List<String> tokens = new ArrayList<String>();
    // List to store all used email addresses to vaoid duplication
    private static List<String> usedEmailAddresses = new ArrayList<String>();
    // List to store all used usernames to vaoid duplication
    private static List<String> usernames = new ArrayList<String>();

    public static void main(String []args) throws IOException  {
        readFileToList("./source/input.txt", "\\s*\\.\\s*");
        createTestUsers();
    }

    private static void readFileToList(String input, String delimiter) {
        try {
            // Read entire file into a string
            String content = new Scanner(new File(input)).useDelimiter("\\Z").next();
            // Remove all newlines to join paragraphs
            content = content.replace("\n", "");
            // Tokenise string by breaking up each sentence into Array elements
            sentences = Arrays.asList(content.split(delimiter));
            // Print out each array element
            for (String s: sentences) {
                tokeniseWords(s);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private static void tokeniseWords(String sentence) {
        try {
            // Remove commas and semi-colons
            sentence = sentence.replace(",", "");
            sentence = sentence.replace(";", "");

            // Tokenise string and iterate through array
            for (String s: Arrays.asList(sentence.split(" "))) {
                // Convert to lowercase
                s = s.toLowerCase();
                // Add to tokens list if not already, and if character length is greater than 2
                if (tokens.contains(s) == false && s.length() > 2) {
                    tokens.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
    * Creates user data for a users table. Data include userid, username, first and last name,
    * email address, plaintext passwords, and creation date-times in YYY-MM-DD HH:ii:ss format.
    */
    private static void createTestUsers() {
        try {
            PrintStream out = new PrintStream("./output/output.csv");
            int numberOfTokens = tokens.size(), i = 0;
            String username, firstname, lastname;
            createUsernamesFromTokens();
            while (i < numberOfTokens) {
                username = usernames.get(i);
                firstname = createName();
                lastname = createName();

                User u = new User(i + 1, firstname, lastname, username);
                String user = String.format("%d,%s,%s,%s,%s,password", u.getUserId(), u.getFirstname(), u.getLastname(),u.getEmail(), u.getUsername());
                out.println(user);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
    * Recursively looks for a free username
    * If a username is not currently in use then add the username to the used List
    * and return for assignment
    */
    private static void createUsernamesFromTokens() {
        Set<String> set = new HashSet<String>();
        set.addAll(tokens);
        usernames.addAll(set);
    }

    private static String createName() {
        int length = tokens.size();
        int index = randInt(0, length);
        return firstLetterUpperCase(tokens.get(index));
    }


    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min)) - min;
        return randomNumber;
    }

    private static String firstLetterUpperCase(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

}
