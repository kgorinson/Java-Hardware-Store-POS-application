package codedemo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
*  This class loads the tool and tool pricing data from the CSV files
*  It stores the data in a HashMap, using the tool code as the key for the tool map
*  And the tool type as the key for the tool pricing map
*  It also contains methods to get the tool and tool pricing maps
*/
public class ToolLoader {
    private static final String TOOLS_FILE = "/Tools.csv";
    private static final String PRICING_FILE = "/Pricing.csv";

    private static final Map<String, Tool> TOOL_MAP = new HashMap<>();
    private static final Map<String, ToolPricing> TOOL_PRICING_MAP = new HashMap<>();

    public ToolLoader() {
        try{
            loadTools();
            loadToolPricing();
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

    /**
     * This method loads the tool data from the Tools.csv file
     * For each line in the file, it creates a new Tool object and adds it to the tool map
     * The tool code is used as the key
     * @throws URISyntaxException if the file path is invalid or doesn't exist
     */
    private void loadTools() throws URISyntaxException {
        //Check if the file exists because .toURI() throws a NullPointerException if the file doesn't exist
        if (Files.exists(Paths.get(getClass().getResource(TOOLS_FILE).toURI()))) {
            try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(TOOLS_FILE).toURI()))) {
                stream.forEach(line -> {
                    String[] toolData = line.split(",");
                    TOOL_MAP.put(toolData[0], new Tool(toolData[0], toolData[1], toolData[2]));
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method loads the tool pricing data from the Pricing.csv file
     * For each line in the file, it creates a new ToolPricing object and adds it to the tool pricing map
     * The tool type is used as the key
     * @throws URISyntaxException if the file path is invalid or doesn't exist
     */
    private void loadToolPricing() throws URISyntaxException {
        //Check if the file exists because .toURI() throws a NullPointerException if the file doesn't exist
        if (Files.exists(Paths.get(getClass().getResource(PRICING_FILE).toURI()))) {
            try(Stream<String> lines = Files.lines(Paths.get(getClass().getResource(PRICING_FILE).toURI())).skip(1)){
                lines.forEach(line -> {
                    String[] toolPricing = line.split(",");
                    Boolean[] chargeDay = new Boolean[3];
                    for (int i = 2; i < toolPricing.length; i++) {
                        //if the string equals "YES", then the corresponding day is true
                        chargeDay[i-2] = toolPricing[i].trim().equalsIgnoreCase("YES");
                    }

                    TOOL_PRICING_MAP.put(toolPricing[0].trim(), new ToolPricing(new BigDecimal(toolPricing[1].replaceAll("\\$", "").trim()), 
                        chargeDay[0], chargeDay[1], chargeDay[2]));
                });
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns the tool map
     * @returns the tool map
     */
    public static Map<String, Tool> getToolMap() {
        return TOOL_MAP;
    }

    /**
     * This method returns the tool pricing map
     * @returns the tool pricing map
     */
    public static Map<String, ToolPricing> getToolPricingMap() {
        return TOOL_PRICING_MAP;
    }
}
