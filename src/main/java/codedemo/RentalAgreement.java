package codedemo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * This class creates a Rental Agreement object with the values calculated in Checkout
 * It then supports the toString method to print the Rental Agreement
 */
public class RentalAgreement {
    private final Tool tool;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final BigDecimal dailyCharge;
    private final BigDecimal preDiscountCharge;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;
    private final int rentalDays;
    private final int chargeDays;
    private final int discountPercent;

    public RentalAgreement(Tool tool, LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyCharge,
            BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge, 
            int rentalDays, int chargeDays, int discountPercent) { 
        this.tool = tool;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyCharge = dailyCharge;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
        this.rentalDays = rentalDays;
        this.chargeDays = chargeDays;
        this.discountPercent = discountPercent;
    }

    public String toString() {
        return  "Rental Agreement:" +
                "\nTool Code: " + tool.getToolCode() +
                "\nTool Type: " + tool.getToolType() +
                "\nTool Brand: " + tool.getToolBrand() +
                "\nRental Days: " + rentalDays +
                "\nCheck Out Date: "  + checkoutDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")) +
                "\nDue Date: " + dueDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")) +
                "\nDaily Rental Charge: " + NumberFormat.getCurrencyInstance().format(dailyCharge) +
                "\nCharge Days: " + chargeDays +
                "\nPre-discount Charge: " + NumberFormat.getCurrencyInstance().format(preDiscountCharge) +
                "\nDiscount Percent: " + discountPercent + "%" +
                "\nDiscount Amount: " + NumberFormat.getCurrencyInstance().format(discountAmount) +
                "\nFinal Charge: " + NumberFormat.getCurrencyInstance().format(finalCharge) +
                "\n\n\n\n";
    }
}
