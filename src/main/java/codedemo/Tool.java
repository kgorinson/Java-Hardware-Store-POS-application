package codedemo;

/**
 * This class stores Tool objects, containing the tool code, tool type, and tool brand
 * It also contains methods to get these values
 */
public class Tool {
    private String code;
    private String type;
    private String brand;

    public Tool(String code, String type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public String getToolCode() {
        return code;
    }

    public String getToolType() {
        return type;
    }

    public String getToolBrand() {
        return brand;
    }
}
