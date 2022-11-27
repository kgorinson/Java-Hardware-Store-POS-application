package codedemo.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import codedemo.ToolLoader;

/** 
 * Class to perform input validation
 * This class has methods to validate discount percents, rental days,
 * tool codes and dates.
 */
public class InputUtil {
    //checks if the discount amount is valid given @param Stringdiscount
    //Returns true only if discount is a valid number between 0 and 100
    public boolean isDiscountValid(String discount) {
        if (discount.matches("[0-9]+") && Integer.parseInt(discount) >= 0 && 
                Integer.parseInt(discount) <= 100) {
            return true;
        } else if (Integer.parseInt(discount) < 0 || Integer.parseInt(discount) > 100) {
            System.out.println("Error: Discount must be between 0 and 100");
            return false;
        } else {
            System.out.println("Error: Invalid input");
            return false;
        } 
    }

    /* Checks if number of rental days is valid given @param String rentalDays
     * Returns true only if rentalDays is a valid number >=1
     */
    public boolean isNumberRentalDaysValid(String rentalDays) {
        if (rentalDays.matches("[0-9]+") && Integer.parseInt(rentalDays) >= 1) {
            return true;
        } else if (Integer.parseInt(rentalDays) < 1) {
            System.out.println("Rental days must be greater than or equal to 1");
            return false;
        } else {
            System.out.println("Rental days must be a number");
            return false;
        }
    }

    /*
     * Checks if the tool code is valid given @param String toolCode
     * Checks the map of tools to see if the tool code is in the map
     * Returns true only if toolCode is a valid tool code
     */
    public boolean isToolCodeValid(String toolCode){
        if (ToolLoader.getToolMap().containsKey(toolCode)) {
            return true;
        }
        System.out.println("The tool code is invalid");
        return false;
    }

    public boolean isDateValid(String date) {
        try {
            //converts the string date to a LocalDate object in the format MM/dd/yy
            LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/[yyyy][yy]"));
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Enter the date in the form MM/dd/yy");
            return false;
        }
    }
}
