import java.util.Scanner;


public class Date {
    
    //these fields will hold the numbers corresponding to the month, date, and year respectivley 
    public int month;
    public int day; 
    public int year;

    //this scanner will be used to process any user input
    Scanner input = new Scanner(System.in);

    //this scanner will be used to process date input 
    Scanner date;

    //this is the default constructor 
    Date() throws InvalidSelectionException, InvalidDateException{

        //store user selection choice
        int selection = userSelection();

        //this method will store the values of the month, day, and year from user input
        extractDateInformation(selection);

        //this method will check to see if extracted date values create a valid date
        if(!isValidDate()){
            throw new InvalidDateException("\nThe date is not valid. Double check to make sure date makes logical sense, for example the day is not greater than 31, there aren't any negative values, or the month does not exceed 12.");
        }

        //will print the date in ISO 8601 form (YYYY--MM-DD)
        printDateISO();

    }

    /*User selection method, this method will prompt the user for the type of method it would like to use for date entry 
      and return the number corresponding to the method of date entry the user chooses */

      public int userSelection() throws InvalidSelectionException{

        //prompt the user 
        System.out.println("\nPlease choose the format of the date that you would like to enter.");
        System.out.println("\nSelection Menu");
        System.out.println("1: MM/DD/YYYY     [ex. 01/02/2024 or 1/2/2024]");
        System.out.println("2: MM-DD-YYYY     [ex. 01-02-2024 or 1-2-2024]");
        System.out.println("3: Month DD, YYYY [ex. January 02, 2024 or January 2, 2024]");
        System.out.print("Selection(Enter 1, 2, or 3): ");
        
        //will store user selection
        int selection = input.nextInt();

        //if the selection is not valid throw invalidSelectionException
        if(selection < 0 || selection > 3){
           throw new InvalidSelectionException("\nYou must only selection options 1, 2, or 3 from the selection menu.");
        }

        //return selection
        return selection;
      }

      //this method will prompt user to enter the date and store the month, day, and year of the date that the user entered to their respective field based on user selection choice
      public void extractDateInformation(int selection) throws InvalidDateException{

        System.out.print("\nEnter the date (be sure to match your input to your selection choice): ");
        
        input.nextLine();
        String userDateInput = input.nextLine();

        //use userDateInput as an argument in constructor of Scanner the process the date 
        date = new Scanner(userDateInput);

        //switch will process the date according to the user input
        switch(selection){

            //in this case the user choose the format MM/DD/YYYY
            case 1:
                date.useDelimiter("/");
                
                //store month, day, and year into their respective fields
                month = date.nextInt();
                day = date.nextInt();
                year = date.nextInt();
                
                break;

            //in this case the user choose the format MM-DD-YYYY
            case 2: 
                date.useDelimiter("-");

                //store month, day, and year into their respective fields
                month = date.nextInt();
                day = date.nextInt();
                year = date.nextInt();
                 
                break;

            //in this case the user choose the format Month DD, YYYY
            case 3:

                //remove comma from userDateInput for ease
                userDateInput = userDateInput.replace(",", "");

                //use new date in date processing Scanner constructor
                date = new Scanner(userDateInput);
                
                //method will determine the corresponding integer to the month of the year
                month = monthInteger(date.next());
                day = date.nextInt();
                year = date.nextInt();

                break;
        }
    
      }

      //will return an integer based on the string argument
      public int monthInteger(String month) throws InvalidDateException {
        
        //Create an array of all of the possible month names
        String[] months = new String[] {"January", "Febuary", "March", "April", "May", "June", 
                            "July", "August", "September", "October", "November", "December"};

        //If the month is contained in the array, it will return an integer that corresponds to the month name                  
        for (int i = 0; i < months.length; i++){
            if (month.equals(months[i])){
                return i + 1;
            }
        }

        //if the input does not match any of the months it will throw an invalid date exception.
        throw new InvalidDateException("You must enter the entire month name with capatalized first letter." +
                             "\nJanuary, Febuary, March, April, May, June, July, August, September," + 
                            " October, November, December are the only valid months that may be entered.");
        
      }

      //checks to see if the date is valid if not then caller will throw an invalid date input exception
      public boolean isValidDate(){
        
        if (year < 0){
            return false;
        }

        //if it is an invalid day it will return false
        // it is understood that some months have less than 31 days, but for simplicities sake it will only return false if a user enter a day greater than 31
        if (day < 1 || day > 31){
            return false;
        }

        //if it is an invalid month it will return false
        if (month < 1 || month > 12){
            return false;
        }

        //if it passes all of the tests then method will return true 
        return true;
      }

      //this method will print the date in ISO 8601 form. (YYYY-MM-DD)
      //method will print leading zeroes if necessary
      public void printDateISO(){
        //print year 
        if (year < 10)
            System.out.print("000");

        else if (year <100)
            System.out.print("00");

        else if (year <1000)
            System.out.print("0");

        System.out.print(year);

        //print dash
        System.out.print("-");

        //print month
        if (month < 10)
            System.out.print(0);

        System.out.print(month);

        //print dash
        System.out.print("-");

        //print day
        if (day < 10)
            System.out.print(0);
        
        System.out.print(day);
      }
}
