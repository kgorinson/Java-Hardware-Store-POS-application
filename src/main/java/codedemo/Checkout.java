package codedemo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import codedemo.Util.DateUtil;

/* This class represents a checkout
 * It calculates the due date, charge days, pre-discount charge, discount amount, and final charge
 * It then creates a new RentalAgreement object and prints it
 */
public class Checkout {
    private final Map<String, Tool> toolMap;
    private final Map<String, ToolPricing> toolPricingMap;

    private String toolCode;
    private LocalDate checkoutDate;
    private int rentalDays;
    private int discountPercent;

    public Checkout(String toolCode, int rentalDays, LocalDate checkoutDate, int discountPercent) 
            throws DateTimeParseException{
        this.toolCode = toolCode;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;
        toolMap = ToolLoader.getToolMap();
        toolPricingMap = ToolLoader.getToolPricingMap();
        processCheckout();
    }

    public void processCheckout() {
        //Using weekdayCharge, weekendCharge, and holidayCharge, calculates the total charge days, and stores it as a BigDecimal, using the DateUtil class
        int chargeDays = DateUtil.getChargeDays(toolCode, checkoutDate, rentalDays);

        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        Tool tool = toolMap.get(toolCode);
        //gets the tool type from the tool map using the tool code as the key

        //gets a string of the tool type
        String toolType = tool.getToolType();

        //calculate the daily charge for the tool
        BigDecimal dailyCost = toolPricingMap.get(toolType).getDailyCost();
        BigDecimal subTotal = calculateSubTotal(toolType, chargeDays);
        BigDecimal discountAmount = calculateDiscountAmount(subTotal);
        BigDecimal finalCharge = subTotal.subtract(discountAmount);

        //we create a Rental Agreement object with the values we calculated
        RentalAgreement rentalAgreement = new RentalAgreement(tool, checkoutDate, dueDate, 
            dailyCost, subTotal, discountAmount, finalCharge, rentalDays, chargeDays, discountPercent);
        System.out.println(rentalAgreement.toString());
    }

    private BigDecimal calculateSubTotal(String toolType, int chargeDays){
        //gets the daily cost from the toolPricingMap
        BigDecimal dailyCost = toolPricingMap.get(toolType).getDailyCost();

        //calculates the total charge
        BigDecimal subTotal = dailyCost.multiply(new BigDecimal(chargeDays));

        //rounds the total charge to the nearest cent
        subTotal = subTotal.setScale(2, RoundingMode.HALF_UP);

        return subTotal;
    }

    private BigDecimal calculateDiscountAmount(BigDecimal subTotal){
        //calculates the discount amount
        BigDecimal discountAmount = subTotal.multiply(new BigDecimal(discountPercent)).divide(new BigDecimal(100));

        //rounds the discount amount to the nearest cent
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);

        return discountAmount;
    }
}
