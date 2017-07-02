public class Document {

    public int userId = null;
    public String title = null;
    public String description = null;

    /**
    * Constructor
    */
    public Document(int userId, String t, String d) {
        this.userId = userId;
        this.title = t;
        this.description = d;
    }

    /**
    * Returns the document's title
    */
    public String getTitle() {
        return this.title;
    }

    /**
    * Returns the document's description
    */
    public String getDescription() {
        return this.description;
    }
    
    /**
    * Returns the document's userId
    */
    public int getUserId() {
        return this.userId;
    }

}
