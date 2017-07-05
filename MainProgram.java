import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class MainProgram {

    // List to store all of the tokenised sentences from the input file
    private static List<String> sentences = new ArrayList<String>();
    // List to store all token words greater than than 2 characters
    private static List<String> tokens = new ArrayList<String>();
    // List to store all used email addresses to vaoid duplication
    private static List<String> usedEmailAddresses = new ArrayList<String>();
    // List to store all used usernames to vaoid duplication
    private static List<String> usernames = new ArrayList<String>();
    // List to store all created users
    private static List<User> users = new ArrayList<User>();
    // List to store all documents
    private static List<Document> documents = new ArrayList<Document>();

    public static void main(String []args) throws IOException  {
        
        readFileToList("./source/input.txt", "\\s*\\.\\s*");

        createTestUsers();

        assignDocumentsToUsers();

        createDocumentsFile();
    }

    /**
    * Reads the input file for all sentences for later processing
    */
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

    /**
    * Tokenises all words in a sentence and stores it into the token list.
    */
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
    * email address, plaintext passwords.
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

                // Create new user account
                User u = new User(i + 1, firstname, lastname, username);
                String user = String.format("%d,%s,%s,%s,%s,password", u.getUserId(), u.getFirstname(), u.getLastname(),u.getEmail(), u.getUsername());
                // Write string to csv file
                out.println(user);
                // Add user to users list
                users.add(u);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
    * Converts the tokens list to a hashset to remove any duplicates and
    * adds all of the set values to the usernames list
    */
    private static void createUsernamesFromTokens() {
        Set<String> set = new HashSet<String>();
        set.addAll(tokens);
        usernames.addAll(set);
    }

    /**
    * Randomly chooses a word from the tokens list as a name
    */
    private static String createName() {
        int length = tokens.size();
        int index = randInt(0, length);
        return firstLetterUpperCase(tokens.get(index));
    }

    /**
    * Random number generator. Returns a random number between the given min and max ranges
    */
    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min)) - min;
        return randomNumber;
    }

    /**
    * Converts the first character of a given word to uppercase
    */
    private static String firstLetterUpperCase(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    /**
    * Randomly generates titles from the sentences list
    */
    private static String createTitle() {
        int min = 0, max = sentences.size();
        int index = randInt(min, max);
        return sentences.get(index);
    }

    /**
    * Randomly generates parapgraphs of different sizes, using sentences from the sentences list
    */
    private static String createParagraph() {
        int pSizeMax = randInt(0, 20), numberOfSentences = sentences.size(), index;
        String paragraph = "";

        for (int i = 0; i < pSizeMax; i++) {
            index = randInt(0, numberOfSentences);
            paragraph += String.format("%s. ", firstLetterUpperCase(sentences.get(index)));
        }

        return paragraph;
    }

    /**
    * Assign documents to users
    */
    private static void assignDocumentsToUsers() {
        for (User u: users) {
            assignDocumentsToUser(u.getUserId());
        }
    }
    /**
    * Assign documents to a user and adds the documents to an array list
    */
    private static void assignDocumentsToUser(int userId) {
        // Number of documents to be created
        int number = randInt(0, 5), i;

        for (i = 0; i < number; i++) {
            Document d = new Document(userId, createTitle(), createParagraph());
            documents.add(d);
        }
    }

    /**
    * Writes the documents to a csv file
    */
    private static void createDocumentsFile() {
        try {
            PrintStream out = new PrintStream("./output/documents.csv");
            for (Document d : documents) {
                String line = String.format("%d,\"%s\",\"%s\"", d.getUserId(), d.getTitle(), d.getDescription());
                out.println(line);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }
    }



}
