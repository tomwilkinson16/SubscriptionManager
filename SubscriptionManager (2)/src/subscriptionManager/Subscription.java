package subscriptionManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**This class is made so that the user can make a new subscription and write it
 * to a file named "Subscription.txt".
 * Inside this class all the information that the user input for option 1 will
 * be populated here and wrote to the file named "Subscription.txt" Subscription
 * has instance variables and can be used anywhere in the class.
 * 
 *
 * @author a0307220
 */
public class Subscription {
    
    
    private String name;
    private String packages;
    private int duration;
    private String discount;
    private String paymentTerm;
    private double price;
    private String date;

    /**This method is created so that the mainMenu creates a Subscription object.
     * This method inside the optionsMenu creates a new sub called "newSub" as
     * an object and all the parameters are initialized and populated.
     *
     */
    public Subscription(){
        
    

    }
    
    /** The Subscription method takes all the parameters that are entered and
     * populates them.
     * Each variable  is used when the user writes a new summary at the end of 
     * option1 all of the values have been populated and then displayed back to
     * the user.
     *
     * 
     * @param date
     * @param packages
     * @param duration
     * @param discount
     * @param paymentterm
     * @param price
     * @param name
     */
    public Subscription(String date, String packages, int duration, 
            String discount, String paymentterm, int price, String name) {
        
        
        this.name = name;
        this.packages = packages;
        this.duration = duration;
        this.discount = discount;
        this.paymentTerm = paymentterm;
        this.price = price;
        this.date = date;
    }
    
    /**This method is called into the MainMenu class and is all of the methods
     * used to create a new Subscription.
     * This method draws all of the methods that are used to create a new
     * subscription inside this class and are called back to the main menu using
     * the method name newSub.run();. It gathers the date, name, package type,
     * the duration of the sub, how the user is paying for the sub, the discount
     * that is added, and displays the price based on whats been inputted. This 
     * method also displays the summary of the object created and writes it to
     * a new file called "Subscription.txt"
     *
     */
    public void run(){
        date = Date.createDate();
        requestName();
        requestPackage();
        requestDuration();
        requestPaymentterm();
        discount = ValidateDiscount.getDiscountCode();
        createPrice();
        createNewSummary(); 
        writeFile();
        
    }
    

    @Override
    public String toString() {
        return date + "\t" + packages + " \t" + duration + "\t" + discount 
                + "\t" + paymentTerm + "\t" + price + "\t" + name;
    }

    /**This method is a getter and it gets the date.
     * This method gets the date and writes it to a new variable named date, 
     * it returns it back to the new subscription.
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**This method is a getter and it gets the price.
     * This method gets the price from the user and writes it to a variable 
     * named price and returns.
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**This method is a getter and it gets the name from the user.
     * After getting the name from the user this then stores it in a variable
     * named name, it then returns it back to subscription.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**This method is a getter and it gets the package from the user.
     * When this method gets the packageType from the user it stores it within 
     * a variable named packages and returns the data back to the user.
     * @return packages
     */
    public String getPackage() {
        return packages;
    }

    /**This method is a getter and it gets the duration from the user.
     * This getter gets the duration from the user and stores it inside a 
     * variable named duration and returns it back to the user.
     * @return duration
     */
    public int getDuration() {
        return duration;
    }

    /**This method is a getter and it gets the discount from the user.
     * This getter gets the discount that is created from a new subscription
     * and stores it inside a variable named discount. It then returns the data
     * back to the user.
     * @return discount
     */
    public String getDiscount() {
        return discount;
    }

    /**This method is a getter and it gets the payment term from the user.
     * This method grabs either one off payment or monthly payment from the user.
     * It then stores it in a variable name paymentTerm and returns it back
     * to the user.
     * @return paymentTerm
     */
    public String getPaymentterm() {
        return paymentTerm;
    }

    /**This method is user to get the new subscription name.
     * Inside this method the user is requested to input a valid name, I have
     * opted to allow integers as some modern names have integers. It creates a
     * new scanner object and waits for an input from the user. After getting the
     * name from the user, the data is stored in a variable named "name" and
     * returned.
     * @return name
     */
    public String requestName(){
        //This is the naming convention
        Scanner scan = new Scanner(System.in);
        System.out.println("\nPlease enter your name (2-25 Characters)");
        name = scan.nextLine();
        while(name.length() > 25 || name.length() < 2) {
            System.out.println("\nPlease enter your name (2-25 characters): ");
            name = scan.nextLine();
        }
        return name;    
    }    
    
    /**This method requests the user to enter B for Bronze, S for Silver and G 
     * for Gold.
     * Inside this method the user has the option to enter B, S or G. This 
     * is handled via the scanner utility that java offers inside Netbeans and all 
     * inputs are validated so that if the user enters a lowercase version of
     * what is asked, it is converted to the correct format. All other inputs 
     * for this method will be denied and the user will be re-prompted to enter
     * b,s or g. It is inside of a while loop so whilst the user does not enter
     * a correct value, they will be re-prompted.
     */
    public void requestPackage(){
        //This is checking to see if the user inputs the correct package
        Scanner scan = new Scanner (System.in);
        System.out.println("\nPlease enter the package (B for Bronze, "
                + "S for Silver or G for Gold)");
        packages = scan.next().toUpperCase();
        while (!(packages.equals("B") || (packages.equals("S")) || 
                (packages.equals("G")))) {
            System.out.println("\nInvalid. \nPlease enter the package "
                    + "(B for Bronze, S for Silver or G for Gold)");
            packages = scan.next().toUpperCase();
        }
    }
    
    /**This method is used to validate the duration that the user is tasked to 
     * input.
     * The duration method is inside of a do while loop in which the user is 
     * prompted for an input and it is validated until they enter a correct
     * value. This is handled using the Scanner object that java offers. The 
     * duration is initially set to "-0" so that the Boolean invalid stays true.
     * Inside the do while loop that is created, the user can only enter 1,3,6
     * and 12. If the user enters a correct input, invalid is set to false so that
     * the user breaks out of the loop. All other duration inputs are invalid
     * and are handled via a try catch with an InputMismatchException.
     * 
     * @exception InputMismatchException e
     */
    public void requestDuration(){
        boolean invalid = true;
        
        Scanner scan = new Scanner (System.in);
       
        duration = -0;
        do{
            try{
            System.out.println("\nPlease enter a duration (1, 3, 6, 12)");
            duration = scan.nextInt();
            if (duration == 1 || duration == 3 ||duration == 6 
                    ||duration == 12){
                invalid = false;
            }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid duration, please enter "
                        + "(1, 3, 6, 12)");
                scan.nextLine();
            }
        }while (invalid);
    }
        
    /**This method requests the user to enter a either "o" or "m" for one off
     * payment and monthly payments.
     * Inside this method the user is tasked to enter either "o" or "m" only.
     * This option is initially set to a value of "" so that the input is 
     * automatically invalid. It is validated within a do while loop and 
     * any other entry that is entered will be set to invalid and the user will
     * be re-prompted to enter a correct input. After the user enters a correct 
     * value, the user breaks out of the loop via the Boolean invalid. 
     *
     */
    public void requestPaymentterm(){
        Scanner scan = new Scanner (System.in);
        //This is to see if its a monthly payment or a one off payment
        
        paymentTerm = "";
        boolean invalid = true;
        do{ 
            System.out.println("\nPlease choose monthly payments or a one "
                    + "off payment (O or M)");
            paymentTerm = scan.next().toUpperCase();
            if (paymentTerm.equals ("M") || paymentTerm.equals ("O")){
                invalid = false;
            }
        }while (invalid);

        if (paymentTerm.equals("O")) {
            paymentTerm = "One off Subscription";
        } else if (paymentTerm.equals("M")) {
            paymentTerm = "Monthly Subscription";
        }
    }
    
    /**This method creates the price in which the user has chosen based on either
     * the bronze, silver or gold package. It is held within a switch statement
     * as their are only 3 options in which the user can choose for bronze 
     * silver and gold. I wrote a second switch statement and made it nest from
     * the original switch statement so i could bring the value of duration 
     * from the user input into play. Each value that is 1,3,6 or 12 have 
     * separate prices for each package. The packageCost variable is then created
     * from the input.
     * This method also holds the discountPercentage so that what ever input the
     * user enters for a packageCost they are automatically enrolled onto the
     * discount variable. 
     *
     */
    public void createPrice() {
        
        int packageCost = 0;
               
        switch (this.packages){
            case "B":
                switch (this.duration){
                    case 1:
                        packageCost = 600;
                        break;
                    case 3:
                        packageCost = 500;
                        break;
                    case 6:
                        packageCost = 400;
                        break;
                    case 12:
                        packageCost = 300;
                        break;
                }break;
            case "S":
                switch (this.duration){
                    case 1:
                        packageCost = 800;
                        break;
                    case 3:
                        packageCost = 700;
                        break;
                    case 6:
                        packageCost = 600;
                        break;
                    case 12:
                        packageCost = 500;
                        break;
                }break;
            case "G":
                switch (this.duration){
                    case 1:
                        packageCost = 999;
                        break;
                    case 3:
                        packageCost = 899;
                        break;
                    case 6:
                        packageCost = 799;
                        break;
                    case 12:
                        packageCost = 699;
                        break;
                }break;          
        }
        
        
                
        double discountPercentage = 0.0;
        if (!this.discount.equals("-")){
            discountPercentage = Integer.valueOf(this.discount.substring(5));
        }
        // Conver percentage to decimal.
        discountPercentage = (discountPercentage / 100);

        
        
        if (paymentTerm.equals("One off Subscription")){
            discountPercentage = discountPercentage + 0.05;
            this.price = (packageCost * duration) -  
                    ((double)discountPercentage * (packageCost * duration));
        }
        else {
            this.price = (packageCost * duration) -  
                    (((double)discountPercentage /100) * 
                    (packageCost * duration));
        }
    } 
     
    /**This method creates a FileWriter object and creates a new file.
     * This method is used after the user has finished creating the Subscription.
     * It creates a print writer object and creates a file writer called subsFile.
     * When the method prints to the new Subscription.txt file it takes all of 
     * the variables that have been inputted and validated and tokenises them into
     * the file. It's all wrapped into a try and catch where for any reason the
     * file can't write, the exception is handled.
     * 
     * @exception IOException
     */
    public void writeFile(){
        File subsFile = new File("Subscription.txt");
        try{

            
            try (PrintWriter writer = new PrintWriter(new BufferedWriter 
        (new FileWriter (subsFile, true)))) {
                writer.print(date + "\t" + packages + "\t" + duration 
                        + "\t" + discount +"\t" + paymentTerm + "\t" + price 
                        + "\t" + name + "\t" +"\r\n");
            }
        }
        catch (IOException e){
            System.out.println("ERROR");  
        }       
    }
    
    /**This method creates a summary for option 1 and option 4. 
     * Inside this method I have used printF to format print statements as well 
     * as line spacing for empty whitespace. All the variables for a new sub and
     * the variables for the displaying subscriptions are within this method.
     */
    public void createNewSummary() {

        String durationConvert = "";

        switch (duration) {

            case 1:
                durationConvert = "One";
                break;
            case 3:
                durationConvert = "Three";
                break;
            case 6:
                durationConvert = "Six";
                break;
            case 12:
                durationConvert = "Twelve";
                break;
        }
        
        
        
        String packageConvert = "";
        
        switch (packages){
        
            case "B":
                packageConvert = "Bronze";
                break;
            case "S":
                packageConvert = "Silver";
                break;
            case "G":
                packageConvert = "Bronze";
                break;
        
        }

        price = price / 100;
        String whitespace = "";
        String emptyLine = "|                                               |";
        System.out.println("+===============================================+");
        System.out.println(emptyLine);
        System.out.printf("| Customer: %-35s |\n", name);
        System.out.println(emptyLine);
        if (discount == "-") {
            System.out.printf("|     Date: %-14s Discount Code: %-5s |",
                    date, discount);
        } else if (discount != "-") {
            System.out.printf("|     Date: %-14s Discount Code: %-3s|",
                    date, discount);
        }
        System.out.printf("\n|  Package: %-19s Duration:%-5s |\n",
                packageConvert, durationConvert);
        System.out.printf("|    Terms: %-35s |\n", paymentTerm);
        System.out.print(emptyLine);
        System.out.printf("\n|          %s : £%.2f  %-6s |\n",
                paymentTerm, price, whitespace);
        System.out.println(emptyLine);
        System.out.println("+============================================="
                + "==+\n");
    }
}
