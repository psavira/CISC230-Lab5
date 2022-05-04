import java.util.Scanner;

/**
 * This driver will prompts the user to enter their user name, which is the name of a CSV file. If the CSV file is on file, then we will process
 * the existing file. If not, then we will create a new file. The user then needs to choose wheter they want to sort the tasks based on date,
 * sort the tasks based on importance level, add a new task, or save and exit the program; I also added an option to exit the program without
 * saving anything. if an error is caught in add a new task, then the program will prompt the user to re-choose one of the available options.
 * @author Pam Savira
 *
 */
public class TodoDriver {

    public static void main(String[] args) {
        /*
        System.out.println(Date.fromYYYYMMDDDashString("2020-05-05"));
        System.out.println(Date.fromYYYYMMDDString("20200102"));
        System.out.println(new Date(5,15,2020).getAsYYYYMMDD());
        */
        ///*
        Scanner keyboard = new Scanner(System.in);
        String username;
        TodoList todoList;
        String userChoice;
        
        System.out.println("Welcome to the TodoList application.");
        System.out.print("Enter your username: ");
        username = keyboard.nextLine();
        
        
        try { //if the file existed, then build TodoList object from the file
            todoList = TodoList.buildFromUsername(username);
        } catch(IllegalArgumentException e) { //if not then the following
            String yesOrNo;
            System.out.print("Create new Todo List? (Y/N) ");
            yesOrNo = keyboard.nextLine();
            if(yesOrNo.equals("Y")) { //if the user wants to create a new Todo List
                todoList = new TodoList(username); //use the regular TodoList constructor
            } else { //if the user doesn't want to create a new Todo List
                return; //stop the program
            }
            
        }
        
        while(true) {
            System.out.println("Options: ");
            System.out.println("1)  Show tasks sorted by date");
            System.out.println("2)  Show tasks sorted by importance");
            System.out.println("3)  Add new task");
            System.out.println("4)  Save and exit");
            System.out.print("Your choice: ");
            userChoice = keyboard.nextLine();
            if (userChoice.equals("1")) {
                System.out.println(todoList.getAsDateSortedString());
            } else if (userChoice.equals("2")) {
                System.out.println(todoList.getAsImportanceSortedString());
            } else if (userChoice.equals("3")) {
                String dateDashString;
                String dateDashStringClean;
                String importance;
                String description;
                TodoItem todoItem;
                System.out.print("Enter a date (YYYY-MM-DD): ");
                dateDashString = keyboard.nextLine();
                //check if the date is in the right format and is a valid date
                try {
                    dateDashStringClean = ""+(Date.fromYYYYMMDDDashString(dateDashString).getAsYYYYMMDD());
                } catch(IllegalArgumentException e) {
                    continue;
                }
                
                System.out.print("Enter an importance (HIGH, MEDIUM, LOW): ");
                importance = keyboard.nextLine();
                //check if the user write HIGH, MEDIUM, or LOW as an importance level
                if ((!(importance.contentEquals("HIGH"))) && (!(importance.contentEquals("MEDIUM"))) && (!(importance.contentEquals("LOW")))) {
                    System.out.println("Bad importance choice");
                    continue;
                }
                
                System.out.print("Enter a short description (no commas): ");
                description = keyboard.nextLine();
                //check if the user doesn't type any extra comma
                try {
                    todoItem = TodoItem.buildFromCSV(dateDashStringClean+","+description+","+importance);
                } catch(IllegalArgumentException e) {
                    continue;
                }
                todoList.addTask(todoItem);
            } else if (userChoice.equals("4")) {
                todoList.finalize();
                break;
            } else if(userChoice.contentEquals("exit")) {
                System.out.println("exitting without saving");
                break;
            } else {
                System.out.println("\n Please enter 1, 2, 3, or 4 to continue or \n Please enter 'exit' to exit without saving \n");
                continue;
            }
            
        }
        //*/
        
        
    }
    
}
