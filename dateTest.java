import java.util.InputMismatchException;
import java.util.NoSuchElementException;

class dateTest{
    public static void main(String[] args) {

        //to know when to end the loop, will turn false once the program runs to the end wihtout exception
        boolean flag = true;

        while(flag){
            try{
                //instanitaite the date method 
                Date date = new Date();

                flag = false;
            }

            catch(InvalidSelectionException e){
                System.out.println(e.getMessage());
            }

            catch(InvalidDateException e){
                System.out.println(e.getMessage());
            }
                
            catch(InputMismatchException e){
                System.out.println("\nInvalid input, Please enter the data in the format"
                + " as demonstrated corresponding to your selection. Remember that only values " +
                "1, 2, and 3, are allowed for selection input");
            }

            catch(NoSuchElementException e){
                System.out.println("\nInvalid input, Please enter the data in the format"
                + " as demonstrated corresponding to your selection. Remember that only values " +
                "1, 2, and 3, are allowed for selection input.");

            }
        } 
        
       
    
    }

}