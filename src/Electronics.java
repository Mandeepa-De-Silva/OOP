import java.io.Serial;
import java.io.Serializable;

public class Electronics extends Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -299482056648963547L;
    private String Brand;
    private int warranty_period;


    public Electronics(String productID, String productName, int numberOfAvailability, double price , String Brand, int warranty_period) {
        super(productID, productName, numberOfAvailability, price);
        this.Brand = Brand;
        this.warranty_period = warranty_period;
    }

    public String getBrand() {
        return Brand;
    }

    public int getWarranty_period() {
        return warranty_period;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setWarranty_period(int warranty_period) {
        this.warranty_period = warranty_period;
    }

    @Override
    public String toString() {
        return "\tProduct ID: " + super.getProductID() +
                "\n" + "\tProduct Name: " + super.getProductName() +
                "\n" + "\tAvailability: " + super.getNumberOfAvailability() +
                "\n" + "\tPrice: " + super.getPrice() +
                "\n" + "\tBrand: " + Brand +
                "\n" + "\tWarranty Duration " + warranty_period +
                "\n";
    }

    @Override
    public String getProductType() {
        return "Electronic Product";
    }
}

