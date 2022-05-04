
/**
 * This class creates individual to-do items that will be utilized by TodoList class. It also has a static method to create
 * TodoItem objects from CSV line.
 * @author Pam Savira
 *
 */
public class TodoItem {
    //instance variables
    /**
     * date is a Date object that represents when the to-do item has to be completed by.
     * description is a String that represents what has to be completed.
     * importanceLevel is an Importance object that represents how importance the to-do item is.
     */
    private Date date;
    private String description;
    private Importance importanceLevel;
    
    
    //constructors
    /**
     * It takes in a Date object, a string of description, and an Importance object representing its importance level.
     * @param date A Date object representing when the to-do item has to be completed by.
     * @param description A String that represents what has to be completed.
     * @param importanceLevel An Importance object that represents how importance the to-do item is.
     */
    public TodoItem(Date date, String description, Importance importanceLevel){
        this.date = date;
        this.description = description;
        this.importanceLevel = importanceLevel;
    }
    
    
    
    /**
     * It is a static method that builds a TodoItem object from a line in a CSV file.
     * @param stringCSV A String in "YYYYMMDD date, description, importance" level format.
     * @return A TodoItem object representing the CSV String.
     */
    public static TodoItem buildFromCSV(String stringCSV) {
        String[] parts = stringCSV.split(",");
        String dateString="";
        String descriptionStringCSV="";
        String importanceLevelStringCSV="";
        Importance importanceLevelCSV;
        
        //check if there are more than 2 commas
        int count = 0;
        for(int i=0; i<stringCSV.length(); i++) {
            if(stringCSV.charAt(i) == ',') {
                count = count+1;
            }
        }
        if(count!=2) {
            System.out.println("no commas allowed");
            throw new IllegalArgumentException();
        }
        
        //if there are more than three strings separated by a comma, is not really needed maybe?
        if (parts.length != 3) {
            System.out.println("please enter string in this format: date, importance, description");
            throw new IllegalArgumentException();
        }
        
        //make sure that there is no space in the string separated by commas
        for(int i=0; i<parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        
        
        //CHECKING: Date date (more reassign the date)
        dateString = dateString+parts[0];
        
        //CHECKING: String description (more reassign the description)
        descriptionStringCSV = descriptionStringCSV+parts[1];
        
        
        //CHECKING: Importance importanceLevel (more reassign the importance level)
        importanceLevelStringCSV = importanceLevelStringCSV+parts[2];
        
        if(importanceLevelStringCSV.contentEquals("HIGH")) {
            importanceLevelCSV = Importance.HIGH;
        } else if(importanceLevelStringCSV.contentEquals("MEDIUM")) {
            importanceLevelCSV = Importance.MEDIUM;
        } else if(importanceLevelStringCSV.contentEquals("LOW")) {
            importanceLevelCSV = Importance.LOW;
        } else {
            System.out.println("Bad importance choice");
            throw new IllegalArgumentException();
        }
        return new TodoItem(Date.fromYYYYMMDDString(dateString), descriptionStringCSV, importanceLevelCSV);
        
    }
    
    
    
    /**
     * It converts a TodoItem object into a CSV String
     * @return A String in YYYYMMDD, description, importance level format
     */
    public String getAsCSV() {
        return date.getAsYYYYMMDD()+","+description+","+importanceLevel;
        
    }
    
    
    
    /**
     * It's a date getter from the TodoItem object
     * @return A Date object from a TodoItem object
     */
    public Date getDate() {
        return date;
    }
    
    
    
    /**
     * It's an importance level getter from the TodoItem object
     * @return An Importance object from a TodoItem object
     */
    public Importance getImportanceLevel() {
        return importanceLevel;
    }
    
    
    
    /**
     * It's a description getter from the TodoItem object
     * @return A String representing description from a TodoItem object
     */
    public String getDescription() {
        return description;
    }
    
    
    
    /**
     *This method makes a TodoItem object printable in the desired format based on the sample output
     *@return A String object representing a TodoItem object in the desired format.
     */
    @Override
    public String toString() {
        return "*"+"\n"+"\t"+date+"\n"+"\t"+"Importance :"+importanceLevel+"\n"+"\t"+description+"\n";
    }
    
}
