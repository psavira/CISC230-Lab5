
/**
 * This class creates the Date object that will be utilized by TodoItem class. In addition to its regular constructor, it also has two static 
 * method that takes in a string of predetermined date format. This class also check the validity of the Date object. It also implements 
 * comparable for the Date objects.
 * 
 * <p>bugs: cannot accept year = 0
 * 
 * @author Pam Savira
 *
 */
public class Date implements Comparable<Date> { //Comparable here?
    //instance variables
    /**
     * instance variables of month, day, and year represent month, day, and year in a Date object
     */
    private int month;
    private int day;
    private int year;
    
    //constructor
    /**
     * This constructor takes in month, day, and year and return a Date object accordingly
     * @param month An integer representing month
     * @param day An integer representing day
     * @param year An integer representing year
     */
    public Date(int month, int day, int year) {
        if(!(isValidDate(month, day, year))) {
            System.out.println("the date is not valid");
            throw new IllegalArgumentException();
        }
        this.month = month;
        this.day = day;
        this.year = year;
        
    }
    
    
    
    /**
     * This method convert YYYYMMDD string from a CSV file into a Date object
     * @param fileInput A string from a CSV file with YYYYMMDD format
     * @return A Date object representing the YYYYMMDD string
     */
    public static Date fromYYYYMMDDString(String fileInput) {
        String yearString = "";
        String monthString = "";
        String dayString = "";
        int yearFileInput = 0;
        int monthFileInput = 0;
        int dayFileInput = 0;
        /*
        if(fileInput.length() != 8) {
            System.out.println("Please only enter 8 numbers representing YYYYMMDD."); //do I have to do this?
            throw new IllegalArgumentException();
        }
        */
        //separating YYYYMMDD string into its corresponding year, month, and day string
        for(int i=fileInput.length()-2; i<fileInput.length(); i++) {
            dayString = dayString + fileInput.charAt(i);
        }
        for(int i=fileInput.length()-4; i<fileInput.length()-2; i++) {
            monthString = monthString +fileInput.charAt(i);
        }
        for(int i=0; i<fileInput.length()-4; i++) {
            yearString = yearString +fileInput.charAt(i);
        }
        
        //try blocks to check if the string inputs can be parsed into integer
        try {
            yearFileInput = Integer.parseInt(yearString);
        } catch (NumberFormatException e) {
            System.out.println("YYYY is not a number");
            throw new IllegalArgumentException();
        }
        try {
            monthFileInput = Integer.parseInt(monthString);
        } catch (NumberFormatException e) {
            System.out.println("MM is not a number");
            throw new IllegalArgumentException();
        }
        try {
            dayFileInput = Integer.parseInt(dayString);
        } catch (NumberFormatException e) {
            System.out.println("DD is not a number");
            throw new IllegalArgumentException();
        }
        
        //check if the date is valid
        if(!(isValidDate(monthFileInput, dayFileInput, yearFileInput))) {
            System.out.println("the date is not valid");
            throw new IllegalArgumentException();
        }
        return new Date(monthFileInput, dayFileInput, yearFileInput); //what if error caught??
    }
    
    
    
    
    /**
     * This method takes in YYYY-MM-DD string from the user and convert it into a Date object
     * @param userInput a YYYY-MM-DD string from the user
     * @return A Date object representing the YYYYMMDD string
     */
    public static Date fromYYYYMMDDDashString(String userInput) {
        String[] parts = userInput.split("-");
        int yearUserInput = 0;
        int monthUserInput = 0;
        int dayUserInput = 0;
        
        //there have to be 3 parts separated by the dashes
        if (parts.length != 3) {
            System.out.println("Please only enter numbers and dashes in YYYY-MM-DD format."); //this??
            throw new IllegalArgumentException();
        }
        
        //to check if there are the string length for YYYY, MM, and DD
        if (parts[0].length()!=4 || parts[1].length()!=2 || parts[2].length()!=2) {
            System.out.println("Please only enter numbers in YYYY-MM-DD format."); //this??
            throw new IllegalArgumentException();
        }
        
        //year cannot equal to 0
        try {
            yearUserInput = Integer.parseInt(parts[0]);
            if(yearUserInput == 0) {
                System.out.println("YYYY cannot be equal to 0 (zero)");
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("YYYY is not a number");
            throw new IllegalArgumentException();
        }
        
        //to check if the strings can be parsed into integers
        try {
            monthUserInput = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("MM is not a number");
            throw new IllegalArgumentException();
        }
        try {
            dayUserInput = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("DD is not a number");
            throw new IllegalArgumentException();
        }
        
        //to check if the date is valid
        if(!(isValidDate(monthUserInput, dayUserInput, yearUserInput))) {
            System.out.println("the date is not valid");
            throw new IllegalArgumentException();
        }
        
        return new Date(monthUserInput, dayUserInput, yearUserInput); 
    }
    
    
    
    
    /**
     * It converts a Date object into an integer representing YYYYMMDD
     * @return An integer representing YYYYMMDD
     */
    public int getAsYYYYMMDD() { //is it this short?
        String stringYYYYMMDD = "";
        //concatenating the string in regards to the number of digits within the year
        if(year<1000) {
            stringYYYYMMDD = stringYYYYMMDD+"0"+year;
        } else if(year<100) {
            stringYYYYMMDD = stringYYYYMMDD+"00"+year;
        } else if(year<10) {
            stringYYYYMMDD = stringYYYYMMDD+"000"+year;
        } else if (year == 0) { //doesnt work
            stringYYYYMMDD = stringYYYYMMDD+"0000";
        } else {
            stringYYYYMMDD = stringYYYYMMDD+year;
        }
        //concatenating the string in regards to the month's number of digits
        if(month<10) {
            stringYYYYMMDD = stringYYYYMMDD+"0"+month;
        } else {
            stringYYYYMMDD = stringYYYYMMDD+month;
        }
        //concatenating the string in regards to the day's number of digits
        if(day<10) {
            stringYYYYMMDD = stringYYYYMMDD+"0"+day;
        } else {
            stringYYYYMMDD = stringYYYYMMDD+day;
        }
        //parse the string containing YYYYMMDD into an integer
        int integerYYYYMMDD = Integer.parseInt(stringYYYYMMDD);
        return integerYYYYMMDD; 
        
    }
    
    
    
    /**
     * It checks if two Date objects are the same
     *@return A boolean, true if both Date objects are the same and false otherwise
     */
    public boolean equals(Object other) {
        if (other==null) {
            return false;
        } else if (other.getClass() != this.getClass()) { //if other is not a Rational object
            return false;
        }
        Date otherAsDate = (Date) other;
        if (this.year==otherAsDate.year && this.month==otherAsDate.month && this.day==otherAsDate.day) {
            return true;
        }
        return false;
    }
    
    
    
    /**
     * It compare one Date object to another one. returns -1 if this Date object is before the other one, 1 if after, and 0 if they are the same
     * @return An integer representing how a Date object compared to another one
     */
    public int compareTo(Date other) {
        if(this.getAsYYYYMMDD() < other.getAsYYYYMMDD()){
            return -1;
        } else if (this.getAsYYYYMMDD() > other.getAsYYYYMMDD()){
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
    /**
     * It prints a Date object in "Date: month/day/year" format.
     * @return A string representing the Date object
     */
    @Override
    public String toString() {
        return "Date: "+month+"/"+day+"/"+year;
    }
    
    
    /**
     * This method will check whether a Date's year is a leap year.
     * 
     * @param year : the date's birth year.
     * @return true/false statement on whether the user's birth year is a leap year.
     */
    private static boolean isLeapYear(int year) {
        //A leap year is every 4 years, but not every 100 years, then again every 400 years.
        if (year % 4 == 0 && (year % 100 != 0 || year % 4 == 0)) {
            if (year%100 == 0) {
                //if a year is divisible by 100 AND by 400, then it is a leap year.
                if (year%400 == 0) {
                    return true;
                    
                //if a year is divisible by 100 BUT NOT by 400, then it is NOT a leap year.	
                } else {
                    return false;
                }
               
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    
    
    /**
     * This method will check if the date is valid. This method is also used
     * in other methods to check the validity of dates given.
     * 
     * @param date: a Date object.
     * @return true/false on whether the Date's date is valid
     */
    private static boolean isValidDate(int month, int day, int year) {
        //to check if the month falls within January and December range.
        if (1>month || month>12) {
            return false;
        }
        
        //to check all possible days within months.
        if (1>day || day>31) {
            return false;
        }
        
        //the 1st, 3rd, 5th, 7th, 8th, 10th, 12th month of the year can have up to 31 days.
        if ((month == 1 || month == 3 ||month == 5 ||month == 7 ||month == 8
        	    || month == 10 || month == 12) && (1<=day && day<=31)) {
            return true;
            
        //the 4th, 6th, 9th, and 11th month of the year can have up to 30 days.
        } else if ((month == 4 ||month == 6 ||month == 9 ||month == 11) 
        	    && 1<=day && day<=30) {
            return true;
            
        //to check the dates in February	
        } else if (month == 2) {
            //if leap year, then February can have up to 29 days.
            if (isLeapYear(year)) {
                if (1<=day && day<=29) {
                    return true;
                } 
                
            //if NOT leap year, than February can have up to 28 days.
            } else {
                if (1<=day && day<=28) {
                    return true;
                    }
                }
            } 
        //else, the date is not valid
        return false;
    }

}
