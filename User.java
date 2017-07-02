import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.io.*;

public class User {

    public static int userId = 0;
    public static String firstname = null;
    public static String lastname = null;
    public static String username = null;
    public static String email = null;

    // Email address extentions
    private static List<String> emails = new ArrayList<String>(Arrays.asList("@gmail.com", "@hotmail.com", "@outlook.com", "@yahoo.com"));

    // Constructor
    public User(int userid, String firstname, String lastname, String username) {
        this.userId = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = createEmailAddress(username);
    }

    /**
    * Creates an email address using the username and one of the 4 available email providers
    */
    private static String createEmailAddress(String name) {

        int length = emails.size();
        int index = randInt(0, length);
        String email = name.toLowerCase() + emails.get(index);

        return email;
    }

    /**
    * Converts the first character of a given word to uppercase
    */
    private static String firstLetterUpperCase(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    /**
    * Random number generator. Returns a number between the given min and max ranges
    */
    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min)) - min;
        return randomNumber;
    }

    /**
    * Returns the user's userId
    */
    public int getUserId() {
        return userId;
    }

    /**
    * Returns the user's first name
    */
    public String getFirstname() {
        return firstname;
    }

    /**
    * Returns the user's last name
    */
    public String getLastname() {
        return lastname;
    }

    /**
    * Returns the user's username
    */
    public String getUsername() {
        return username;
    }

    /**
    * Returns the user's email address
    */
    public String getEmail() {
        return email;
    }

}
