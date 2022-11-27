package codedemo.Util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import codedemo.Tool;
import codedemo.ToolLoader;
import codedemo.ToolPricing;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/** 
 * Class to perform date calculations, including calculating due dates, charge days, and holidays
 */
public class DateUtil {

    public static int getChargeDays(String toolCode, LocalDate checkoutDate, int rentalDays) {
        LocalDate dueDate = checkoutDate.plusDays(rentalDays+1);
        LocalDate tempDate = checkoutDate.plusDays(1);
        int chargeDays = 0;

        //gets tool map
        Map<String, Tool> toolMap = ToolLoader.getToolMap();

        //gets the tool type from the tool map using the tool code as the key
        Tool tool = toolMap.get(toolCode);

        //stores the tool type
        String toolType = tool.getToolType();

        //get pricing map
        Map<String, ToolPricing> toolPricingMap = ToolLoader.getToolPricingMap();

        //gets weekday, weekend, and holiday charge booleans from toolPricingMap
        boolean weekdayCharge = toolPricingMap.get(toolType).isWeekdayCharge();
        boolean weekendCharge = toolPricingMap.get(toolType).isWeekendCharge();
        boolean holidayCharge = toolPricingMap.get(toolType).isHolidayCharge();

        while (tempDate.isBefore(dueDate)) {
            if (weekdayCharge && tempDate.getDayOfWeek() != DayOfWeek.SATURDAY && 
                    tempDate.getDayOfWeek() != DayOfWeek.SUNDAY && !isHoliday(tempDate)) {
                chargeDays++;
            } else if (weekendCharge && (tempDate.getDayOfWeek() == DayOfWeek.SATURDAY || 
                    tempDate.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                chargeDays++;
            } else if (holidayCharge && isHoliday(tempDate)) {
                chargeDays++;
            }
            tempDate = tempDate.plusDays(1);
        }
        return chargeDays;
    }

    public static boolean isHoliday(LocalDate date) {
        return isIndependenceDayHoliday(date) || isLaborDayHoliday(date);
    }

    /////////////////////////
    //These next 2 methods are used to check if a date is a holiday
    /////////////////////////
    public static boolean isIndependenceDayHoliday(LocalDate date) {
        //If July 4 falls on a Saturday, the holiday is observed on Friday, July 3.
        //If July 4 falls on a Sunday, the holiday is observed on Monday, July 5.
        LocalDate independenceDay = LocalDate.of(date.getYear(), 7, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return date.equals(independenceDay.minusDays(1));
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return date.equals(independenceDay.plusDays(1));
        } else {
            return date.equals(independenceDay);
        }
    }

    /**
     * Determines if a date is a Labor Day holiday
     * @param date, checks if the date is a Labor Day holiday
     * @returns a boolean
     */
    public static boolean isLaborDayHoliday(LocalDate date) {
        //Labor Day is the first Monday in September.
        LocalDate firstMondayInSeptember = date.withMonth(9).with(firstInMonth(DayOfWeek.MONDAY));

        //return true if the date is the first Monday in September
        return date.equals(firstMondayInSeptember);
    }
}
