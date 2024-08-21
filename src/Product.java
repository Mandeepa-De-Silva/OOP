import java.io.Serializable;

public abstract class Product implements Serializable {
    protected String productID;
    protected  String productName;
    protected  int numberOfAvailability;
    protected  double price;

    public Product(String productID, String productName, int numberOfAvailability, double price ){
        this.productID = productID;
        this.productName = productName;
        this.numberOfAvailability = numberOfAvailability;
        this.price = price;
    }



    public abstract String getProductType();

    public String getProductID(){
        return productID;
    }

    public void setProductID(String productID){
        this.productID =productID;
    }

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public int getNumberOfAvailability(){
        return numberOfAvailability;
    }

    public void setNumberOfAvailability(int numberOfAvailability){
        this.numberOfAvailability = numberOfAvailability;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }


}
