import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {

    private static List<Product> products; //creating a array list to store the products
    private static final int MAX_PRODUCTS = 50; //creating a constant variable to store the maximum number of products
    private static final Scanner sc = new Scanner(System.in); //creating a scanner object to get the user inputs

    public WestminsterShoppingManager() {
        products = new ArrayList<>();
    } //constructor to initialize the array list

    public static List<Product> getProducts() {
        return products;
    } //getter method to get the products

    @Override
    public void addProduct(Product product) {//addProduct method to add the product to the system
        products.add(product);
        if (product.getProductType().equals("Clothing Product")) {
            System.out.println("Your product "+ product.productName + " being adding to the system");
            try {
                Thread.sleep(3000); //thread sleep method to stop the execution for 3 seconds
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(product.productID+" Clothing Product added to the products list successfully(^_^)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Your product "+ product.productName + " being adding to the system");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(product.productID+" Electronic Product added to the products list successfully(^_^)");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addProductDetails() { //addProductDetails method to add the product details to the system
        int productOption = Integer.parseInt(integerValidator("""
                \nSelect product type to add to the system
                
                1.Clothing
                2.Electronics
                """));
        if (productOption == 1 || productOption == 2) {
            System.out.println("Enter the ID of the Product:");
            String productID = sc.next();

            for (Product product : products) { //for each loop to check the product ID is already in the system or not
                if (product.getProductID().equals(productID)) {
                    System.out.println("Entered Product ID is already in the list(-_-)!\nTry again..");
                    return;
                }
            }

            String productName = nameValidator("Enter the Name of Product :");

            int numberOfAvailability = Integer.parseInt(integerValidator("Enter the quantity :"));

            double price = priceValidator("Enter the Price of added Product:");


            if (productOption == 1) {
                String size = String.valueOf(sizeValidator("Enter the size od added Product (XS,S,M,L,XL or plus Size):"));

                System.out.println("Enter the colour of added Product :");
                String colour = sc.next();
                Product clothing = new Clothing(productID, productName, numberOfAvailability, price, size, colour);
                addProduct(clothing); //calling the addProduct method to add the clothing product to the system

            } else if (productOption == 2) {
                System.out.println("Enter the Name of Brand :");
                String brand = sc.next();

                int warranty_period = Integer.parseInt(integerValidator("Enter the warranty duration of the Product :"));
                Product electronics = (new Electronics(productID, productName, numberOfAvailability, price, brand, warranty_period));
                addProduct(electronics); //calling the addProduct method to add the electronic product to the system
            }
        }
    }

    @Override
    public void deleteProduct() {//deleteProduct method to delete the product from the system
        System.out.println("Enter the product ID to delete :");
        String deletedProductID;
        boolean isDeleted = false;
        while(!isDeleted){//while loop to check the product ID is in the system or not
            deletedProductID = sc.next();
            for (Product product : products) {
                if (product.getProductID().equals(deletedProductID)) {//equals method to check the product ID
                    System.out.println(product.productID +"Product deleted successfully(*_*)");
                    System.out.println(product);
                    products.remove(product);
                    isDeleted = true;//if the product ID is in the system then it will delete the product
                    break; //break the loop
                }
            }
            if(!isDeleted){//if the product ID is not in the system then it will print the error message
                System.out.println("Product ID not found(-_-)\n Add Correct Product ID to Delete From the System");
                break;
            }
        }
    }


    @Override
    public void displayProducts() {
        ArrayList<Product> sortedProducts = new ArrayList<>(products);//creating a new array list to sort the products
        sortedProducts.sort(Comparator.comparing(Product::getProductID));//sorting the products according to the product ID
        System.out.println("\nProducts are sorted according to the product ID(*_*)\n");

        for(Product product : sortedProducts){
            if(product instanceof Clothing){
                System.out.println("Sorted Clothing Products :");
                System.out.println(product.toString());
            }
        }

        for(Product product : sortedProducts){
            if(product instanceof Electronics ){
                System.out.println("Sorted Electronic Products :");
                System.out.println(product.toString());
            }
        }
    }

    @Override
    public void saveProductsToFile(){
        FileOutputStream fis;
        ObjectOutputStream oos;
        try{
            fis = new FileOutputStream("Products.txt");
            oos = new ObjectOutputStream(fis);
            for(Product product: products){
                if (product instanceof Clothing){ //instanceof operator to check the product type
                    oos.writeObject(product);
                }
            }
            for(Product product : products){
                if(product instanceof Electronics){
                    oos.writeObject(product);
                }
            }
            oos.flush();//flush method to clear the buffer
            oos.close();
            fis.close();
            System.out.println("Successfully saved the products to the file (*_*)");
        }catch(IOException e){
            System.out.println("Error occurred" + e);
        }
    }

    @Override
    public void loadProductsFromFile(){//loadProductsFromFile method to load the products from the file
        FileInputStream fis;
        ObjectInputStream ois;
        products.clear();
        try{
            fis = new FileInputStream("Products.txt");
            ois = new ObjectInputStream(fis);

            for(;;){
                try{
                    Product loadProduct = (Product) ois.readObject();
                    products.add(loadProduct);
                }catch (EOFException e){
                    break;
                }
            }
            System.out.println("Successfully loaded the products from the file(*_*)");
            ois.close();
            fis.close();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error occurred" + e);
        }
    }

    public String nameValidator(String message){//nameValidator method to validate the name
        String input;
        do{
            System.out.println(message);
            input = sc.next();
            if(!input.matches("[A-Za-z]+")){
                System.out.println("Invalid input(-_-)\nPlease enter valid name");
            }
        }while (!input.matches("[A-Za-z]+"));
        return input;
    }

    public double priceValidator(String message){//priceValidator method to validate the price
        String input;
        do{
            System.out.println(message);
            input = sc.next();
            if(!input.matches("\\d+\\.\\d+")){
                System.out.println("Invalid input(-_-)\nPlease enter valid price");
            }
        }while (!input.matches("\\d+\\.\\d+"));
        return Double.parseDouble(input);
    }

    public String sizeValidator(String message){//sizeValidator method to validate the size
        String input;
        do{
            System.out.println(message);
            input = sc.next().toUpperCase();
            if(!input.matches("XS|S|M|L|XL|2XL|3XL|[4-9]XL|\\\\d{1,2}[+]")){
                System.out.println("Invalid input(-_-)\nPlease enter valid size (XS,S,M,L,XL or Plus Sizes)");
            }
        }while (!input.matches("XS|S|M|L|XL|2XL|3XL|[4-9]XL|\\\\d{1,2}[+]"));
        return input;
    }

    public String integerValidator(String message){//integerValidator method to validate the integer values
        String input;
        do{
            System.out.println(message);
            input = sc.next();
            if(!input.matches("\\d+")){
                System.out.println("Invalid input(-_-)\nPlease enter an integer value for the execution");
            }
        }while (!input.matches("\\d+"));
        return input;
    }

    public void menuRun() {//menuRun method to run the program
        int option;
        do {
            System.out.println("""
                                
                                Westminster Shopping Center
                    Select the following option to proceed the system
                                        
                    1. Add a product
                    2. Delete a product
                    3. Display all products
                    4. Save the list of products to the file
                    5. Load the lis of products from the file
                    6. Go to the GUI and view
                    7.Exit
                    """);

            try {
                System.out.println("Enter the option you want to proceed : ");
                option = Integer.parseInt(sc.next()); //parsing the user input to integer
            } catch (NumberFormatException e) {
                option = 0;
            }

            switch (option) {
                case 1 -> {
                    if(products.size()<MAX_PRODUCTS){
                    addProductDetails();
                    }else{
                        System.out.println("Product list is full (-_-) ");
                    }
                }
                case 2 -> {
                    if (products.size() > 0) {
                        deleteProduct();
                    } else {
                        System.out.println("Product List is empty, there's nothing to delete (-_-)");
                    }
                }
                case 3 -> {
                    if (products.size() > 0) {
                        displayProducts();
                    } else {
                        System.out.println("Product List is empty, there's nothing to display (-_-)");
                    }
                }
                case 4 -> {
                    if (products.size() > 0) {
                        saveProductsToFile();
                    } else {
                        System.out.println("Product List is empty, there's nothing to save (-_-)");
                    }
                }
                case 5 -> loadProductsFromFile();
                case 6 -> {
                    if(products.size() >0){
                        new ShoppingCenterGUI();
                    }else{
                        System.out.println("No products in GUI(-_-) Could you please add and run...");
                    }
                }
                case 7 -> {
                    System.out.println("Exiting the program Thank you for being with us...\nGoodbye..(*_*)");
                    saveProductsToFile();
                    System.exit(0);
                }default -> System.out.println("Invalid option (-_-), try again with correct option.");
            }
        } while (true);

    }

    public static void main(String[] args) {

        new WestminsterShoppingManager().menuRun();//calling the menuRun method to run the program
    }
}