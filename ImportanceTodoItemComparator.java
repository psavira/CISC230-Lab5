import java.util.Comparator;

/**
 * This class compares TodoItem based on its importance level in HIGH, MEDIUM, and LOW order.
 * @author Pam Savira
 *
 */
public class ImportanceTodoItemComparator implements Comparator<TodoItem> { //comparing date or importance?
    
    /**
     *It compares one TodoItem object to another by their importance level
     *@return An integer representing how one another stacks up against each other based on their importance level.
     */
    @Override
    public int compare(TodoItem todoItem2, TodoItem todoItem1) {
        if (todoItem1.getImportanceLevel() == Importance.LOW && todoItem2.getImportanceLevel() != Importance.LOW) {
            return -1;
        } else if (todoItem1.getImportanceLevel() == Importance.MEDIUM && todoItem2.getImportanceLevel() != Importance.MEDIUM) {
            if(todoItem2.getImportanceLevel() == Importance.HIGH) {
                return -1;
            } else {
                return 1;
            }
        } else if(todoItem1.getImportanceLevel() == Importance.HIGH && todoItem2.getImportanceLevel() != Importance.HIGH) {
            return 1;
        }
        return 0;
        
    }
    

}
