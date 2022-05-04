import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class creates a list of TodoItem object(s). It will also read from and write on a CSV file based on the username.
 * @author Pam Savira
 *
 */
public class TodoList {
    //instance variables
    /**
     * theTasks is an ArrayList of TodoItem object(s).
     * username is a String representing the name the user wants the file to be or the name of the file the user already created.
     */
    private ArrayList<TodoItem> theTasks;
    private String username;
    
    
    
    //constructors
    /**
     * This constructor takes in a username and initiates a new ArrayList of TodoItem object(s).s
     * @param username A String a username the user wants to name the new CSV file to be.
     */
    public TodoList(String username) { //new empty 
        this.username = username;
        this.theTasks = new ArrayList<TodoItem>();
        
    }
    
    
    
    /**
     * This static method will find the user's CSV file name based on their username, read the file, and converts to file into a TodoList object.
     * @param user A string representing the username or the CSV file's name
     * @return A TodoList representing the CSV file.
     */
    public static TodoList buildFromUsername(String user) { //do file READing here, exceptions too
        List<String> theLines;
        String CSVString="";
        TodoList fromUsername;
        TodoItem todo;
        
        //check if the CSV file is available in this lab's folder
        try {
            theLines = Files.readAllLines(Paths.get(user+".txt"), StandardCharsets.UTF_8);
            fromUsername = new TodoList(user);
            
            //for each line in the file, create a TodoItem object and add it into the TodoList object
            for(int i=0; i<theLines.size(); i++) {
                CSVString = theLines.get(i);
                todo = TodoItem.buildFromCSV(CSVString);
                fromUsername.addTask(todo);
            }
        } catch (IOException e) { //if the file is not found
            System.out.println("An error occurred trying to read the file for your username.");
            throw new IllegalArgumentException();
        }
        return fromUsername;
        
    }
    
    
    
    /**
     * it adds a TodoItem object into our TodoList object
     * @param todo a TodoItem object from a line in CSV File.
     */
    public void addTask(TodoItem todo) {
        theTasks.add(todo);
    }
    
    
    
    /**
     * It prints TodoItem objects in TodoList object, sorted by the date
     * @return A String with date-sorted TodoItem objects in a desired format
     */
    public String getAsDateSortedString() { 
        String dateSortedString = "";
        Collections.sort(theTasks, new DateTodoItemComparator());
        if(theTasks.size()==0) { //if the TodoList object is empty
            dateSortedString = dateSortedString+"No tasks in list";
        } else {
            for(TodoItem todo : theTasks) {
                //print each TodoItem object
                dateSortedString = dateSortedString+todo;
            }
        }
        return dateSortedString;
    }
    
    
    
    /**
    * It prints TodoItem objects in TodoList object, sorted by the importance level from high to low
    * @return A String with importance level-sorted TodoItem objects in a desired format
    */
    public String getAsImportanceSortedString() { 
        String importanceSortedString = "";
        Collections.sort(theTasks, new ImportanceTodoItemComparator()); 
        if(theTasks.size()==0) { //if the TodoList object is empty
            importanceSortedString = importanceSortedString+"No tasks in list";
        } else {
            for(TodoItem todo : theTasks) {
                //print each TodoItem object
                importanceSortedString = importanceSortedString+todo;
            }
        }
        return importanceSortedString;
    }
    
    
    
    /**
     *It saves the current TodoList object into the user's CSV file.
     */
    public void finalize() {
        String toWrite = "";
        for (TodoItem todo : theTasks) { //for each todoItem object in the TodoList object
            //concatenate the CSV line into toWrite string
            toWrite = toWrite+todo.getAsCSV()+"\n";
        }
        //write the whole string into user's CSV file
        try {
            Files.write(Paths.get(username+".txt"), toWrite.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) { //if the file is not found
            System.out.println("Problem writing file");
            return;
        }
        
    }

}
