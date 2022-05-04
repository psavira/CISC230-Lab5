import java.util.Comparator;

/**
 * This class compares TodoItem objects based on their dates.
 * @author Pam Savira
 *
 */
public class DateTodoItemComparator implements Comparator<TodoItem>{
    /**
     *It compares one TodoItem object to another by their date
     *@return An integer representing how one another stacks up against each other based on their dates.
     */
    @Override
    public int compare(TodoItem todoItem1, TodoItem todoItem2) {
        ///*
        if (todoItem1.getDate().compareTo(todoItem2.getDate()) == -1) {
            return -1;
        } else if (todoItem1.getDate().compareTo(todoItem2.getDate()) == 1) {
            return 1;
        }
        return 0;
        
    }
}
