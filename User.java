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

    public User(int userid, String firstname, String lastname, String username) {
        this.userId = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = createEmailAddress(username);
    }

    private static String createEmailAddress(String name) {

        int length = emails.size();
        int index = randInt(0, length);
        String email = name.toLowerCase() + emails.get(index);

        return email;
    }

    public void show() {
        System.out.println(username);
        System.out.println(email);
    }

    private static String firstLetterUpperCase(String word) {
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    private static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNumber = rand.nextInt((max - min)) - min;
        return randomNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

}
