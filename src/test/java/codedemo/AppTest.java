package codedemo;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.*;

import codedemo.Util.InputUtil;

/**
 * This is a jUnit test class for this application
 * It tests the Checkout class and the RentalAgreement class
 */
public class AppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    ToolLoader toolLoader = new ToolLoader();
    InputUtil inputUtil = new InputUtil();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    //Test 1: "JAKR", "5", "9/3/15", "101"
    //This should result in an error message because the discount is greater than 100
    @Test
    public void checkOutTest1(){
        try {
                if (inputUtil.isDiscountValid("101")
                    && inputUtil.isNumberRentalDaysValid("5")
                    && inputUtil.isToolCodeValid("JAKR")
                    && inputUtil.isDateValid("9/3/15")) {

                        new Checkout("JAKR", Integer.parseInt("5"),
                                LocalDate.parse("9/3/15", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                                Integer.parseInt("101"));
                
                        assert false;
                }
                assertEquals("Error: Discount must be between 0 and 100", outputStreamCaptor.toString().trim());
            } 
            catch (IllegalStateException e) {
                assert true;
        }
    }

    //Test 2: "LADW", "3", "7/2/20", "10"
    @Test
    public void checkoutTest2(){
        new Checkout("LADW", 3,
                LocalDate.parse("7/2/20", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                10);
        assertEquals("Rental Agreement:\n" +
                "Tool Code: LADW\n" +
                "Tool Type: Ladder\n" +
                "Tool Brand: Werner\n" +
                "Rental Days: 3\n" +
                "Check Out Date: 07/02/20\n" +
                "Due Date: 07/05/20\n" +
                "Daily Rental Charge: $1.99\n" +
                "Charge Days: 2\n" +
                "Pre-discount Charge: $3.98\n" +
                "Discount Percent: 10%\n" +
                "Discount Amount: $0.40\n" +
                "Final Charge: $3.58", outputStreamCaptor.toString().trim());
    }

    //Test 3: "CHNS", "5", "7/2/15", "25"
    @Test
    public void checkoutTest3(){
        new Checkout("CHNS", Integer.parseInt("5"),
                LocalDate.parse("7/2/15", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                Integer.parseInt("25"));
        assertEquals("Rental Agreement:\n" +
                "Tool Code: CHNS\n" +
                "Tool Type: Chainsaw\n" +
                "Tool Brand: Stihl\n" +
                "Rental Days: 5\n" +
                "Check Out Date: 07/02/15\n" +
                "Due Date: 07/07/15\n" +
                "Daily Rental Charge: $1.49\n" +
                "Charge Days: 3\n" +
                "Pre-discount Charge: $4.47\n" +
                "Discount Percent: 25%\n" +
                "Discount Amount: $1.12\n" +
                "Final Charge: $3.35", outputStreamCaptor.toString().trim());
    }

    //Test 4: "JAKD", "6", "9/3/15", "0"
    @Test
    public void checkoutTest4(){
        new Checkout("JAKD", Integer.parseInt("6"),
                LocalDate.parse("9/3/15", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                Integer.parseInt("0"));
        assertEquals("Rental Agreement:\n" +
                "Tool Code: JAKD\n" +
                "Tool Type: Jackhammer\n" +
                "Tool Brand: DeWalt\n" +
                "Rental Days: 6\n" +
                "Check Out Date: 09/03/15\n" +
                "Due Date: 09/09/15\n" +
                "Daily Rental Charge: $2.99\n" +
                "Charge Days: 3\n" +
                "Pre-discount Charge: $8.97\n" +
                "Discount Percent: 0%\n" +
                "Discount Amount: $0.00\n" +
                "Final Charge: $8.97", outputStreamCaptor.toString().trim());
    }

    //Test 5: "JAKR", "9", "7/2/15", "0"
    @Test
    public void checkoutTest5(){
        new Checkout("JAKR", Integer.parseInt("9"),
                LocalDate.parse("7/2/15", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                Integer.parseInt("0"));
        assertEquals("Rental Agreement:\n" +
                "Tool Code: JAKR\n" +
                "Tool Type: Jackhammer\n" +
                "Tool Brand: Ridgid\n" +
                "Rental Days: 9\n" +
                "Check Out Date: 07/02/15\n" +
                "Due Date: 07/11/15\n" +
                "Daily Rental Charge: $2.99\n" +
                "Charge Days: 5\n" +
                "Pre-discount Charge: $14.95\n" +
                "Discount Percent: 0%\n" +
                "Discount Amount: $0.00\n" +
                "Final Charge: $14.95", outputStreamCaptor.toString().trim());
    }

    //Test 6: "JAKR", "4", "7/2/20", "50"
    @Test
    public void checkoutTest6(){
        new Checkout("JAKR", Integer.parseInt("4"),
                LocalDate.parse("7/2/20", DateTimeFormatter.ofPattern("M/d/[yyyy][yy]")),
                Integer.parseInt("50"));
        assertEquals("Rental Agreement:\n" +
                "Tool Code: JAKR\n" +
                "Tool Type: Jackhammer\n" +
                "Tool Brand: Ridgid\n" +
                "Rental Days: 4\n" +
                "Check Out Date: 07/02/20\n" +
                "Due Date: 07/06/20\n" +
                "Daily Rental Charge: $2.99\n" +
                "Charge Days: 1\n" +
                "Pre-discount Charge: $2.99\n" +
                "Discount Percent: 50%\n" +
                "Discount Amount: $1.50\n" +
                "Final Charge: $1.49", outputStreamCaptor.toString().trim());
    }
}