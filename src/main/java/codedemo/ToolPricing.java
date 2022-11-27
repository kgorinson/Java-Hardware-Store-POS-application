package codedemo;

import java.math.BigDecimal;

/**
 * This class stores daily pricing information for each tool type
 * Including the daily cost, and whether or not there are charges
 * for weekdays, weekends, and holidays.
 * It also contains methods to get these values
 */
public class ToolPricing {
    private BigDecimal dailyCost;
    private boolean chargeWeekday;
    private boolean chargeWeekend;
    private boolean chargeHoliday;

    public ToolPricing(BigDecimal dailyCost, boolean chargeWeekday, boolean chargeWeekend, 
                        boolean chargeHoliday) {
        this.dailyCost = dailyCost;
        this.chargeWeekday = chargeWeekday;
        this.chargeWeekend = chargeWeekend;
        this.chargeHoliday = chargeHoliday;
    }

    public BigDecimal getDailyCost() {
        return dailyCost;
    }
   
    public boolean isWeekdayCharge() {
        return chargeWeekday;
    }

    public boolean isWeekendCharge() {
        return chargeWeekend;
    }

    public boolean isHolidayCharge() {
        return chargeHoliday;
    }
}
