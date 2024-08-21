
import java.util.ArrayList;

public class ShoppingCart {
    public double firstDiscountValue;
    public double threeItemsDiscountValue;

    public static ArrayList<Product> productArrayList = new ArrayList<>();

    public static ArrayList<Product> getProductsList() {
        return productArrayList;
    }

    public void addProduct(Product product) {
        productArrayList.add(product);
    }

    public void removeProduct(Product product) {
        productArrayList.remove(product);
    }

    public double getTotalCost(){
        double totalCost = 0;
        for(int i = 0; i < productArrayList.size(); i++){
            totalCost = totalCost + productArrayList.get(i).getPrice()*1;
        }
        return totalCost;
    }

    public static ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public double firstDiscount() {
        firstDiscountValue = getTotalCost() * 0.1;
        return firstDiscountValue;
    }

    public double threeItemSameDiscount() {
        if (productArrayList.size() >= 3){
            threeItemsDiscountValue = getTotalCost() * 0.2;
        }else{
            // if added items are less than 3 then no discount
            threeItemsDiscountValue = 0;
        }
        return threeItemsDiscountValue;
    }

    public double finalCost(){
        return getTotalCost() - firstDiscountValue - threeItemsDiscountValue;
    }
}
