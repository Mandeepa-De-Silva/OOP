import java.io.Serial;
import java.io.Serializable;

public class Clothing extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -299482056648963147L;
    private String size;
    private String colour;

    public Clothing(String productID,String productName, int numberOfavailability, double price, String size, String colour) {
        super(productID,productName,numberOfavailability,price);
        this.size = size;
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "\tProduct ID: " + super.getProductID() +
                "\n" + "\tProduct Name: " + super.getProductName() +
                "\n" + "\tAvailability: " + super.getNumberOfAvailability() +
                "\n" + "\tPrice: " + super.getPrice() +
                "\n" + "\tSize: " + size +
                "\n" + "\tColour " + colour +
                "\n";
    }

    @Override
    public String getProductType() {
        return "Clothing Product";
    }
}
