package subscriptionManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The Summary class is made to create option 2, 3 and 4. Inside the summary
 * class is instance variables that are used through the class in separate
 * methods. This class uses file reader to read all subs inside the current and
 * sample text files and displays them to the user in various formats
 *
 *
 */
public class Summary {

    private int currentTotalSubs;
    private double averageMonthlyFee = 0.0;
    private double bronzePackPercent;
    private double silverPackPercent;
    private double goldPackPercent;
    protected String[] orderMonths = new DateFormatSymbols().getShortMonths();
    protected ArrayList<Subscription> subs = new ArrayList<>();

    protected String fileName;
    private int averageMonthlySub;
    private String month;
    protected final HashMap<String, Integer> monthHash = new HashMap<>();
    private final Scanner scan = new Scanner(System.in);

    /**
     * This method inside Summary is used to create the Summary object. This
     * object is created inside the MainMenu class and creates an instance of
     * the summary object.
     */
    public Summary() {

    }

    /**
     * This method is in place to read the text files. The text files that have
     * been read are split into an array so they can be tokenised. The
     * tokenisation is used to grab all data from the file and read it as
     * variables. The date is split up into separate integers within the array
     * so that the file can grab the month values and the current date. A new
     * subscription within the summary class is then made within the object
     * newSub. The method is wrapped in a try and catch structure to ensure
     * there is no file reading errors.
     *
     * @exception IOException
     */
    public void readFile() {

        System.out.println("\n");
        File readThis = new File(fileName);

        try (BufferedReader readBuff = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lines = 0;

            while ((line = readBuff.readLine()) != null) {
                String[] fileData = line.split("\t");
                String date = fileData[0];
                String[] dateSplit = date.split("-");
                String month = dateSplit[1];
                lines++;

                String packages = fileData[1];
                int duration = Integer.valueOf(fileData[2]);
                String discount = fileData[3];
                String term = fileData[4];
                int price = Integer.valueOf(fileData[5]);
                String name = fileData[6];

                Subscription newSub = new Subscription(date, packages, duration,
                        discount, term, price, name);

                subs.add(newSub);
            }

        } catch (IOException e) {
            System.out.println("A read error has occured");
        }
    }

    /**
     * The method fildFileName is in place for the user to choose current or
     * sample. The main aim of this method is to ensure the user gets the data
     * from the correct file name and they have the choice of either Current or
     * Sample. The user has to enter a correct integer of either 1 or 2 to get
     * the data. This method is wrapped inside of a do while loop with a switch
     * statement. If the user enters a correct entry, they use the break OUTER
     * statement to break out of the loop and continue using the program. If the
     * inputs are correct the readFile() method is called and the data is read
     * from the file of their choice.
     */
    public void findFileName() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        OUTER:
        do {
            System.out.println("\nPlease choose from the following options - "
                    + "1 for current or 2 for sample");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        fileName = "current.txt";
                        break OUTER;
                    case 2:
                        fileName = "sample.txt";
                        break OUTER;
                    default:
                        System.out.println("\nPlease enter a valid option "
                                + "(1 or 2)");
                        scanner.nextLine();
                        break;
                }
            } else {
                System.out.println("\nIncorrect input");
                scanner.nextLine();
            }
        } while (true);
        readFile();
    }

    /**
     * This method creates a HashMap in which my month data is stored and
     * ordered. The month data is created and stored from the String Array
     * instance variable. The orderMonths Array is formatted and the months are
     * ordered from Jan-December with a counter which stops counting at the
     * enter of the array.
     */
    public void createHashMap() {
        for (int i = 0; i < orderMonths.length - 1; i++) {
            this.monthHash.put(orderMonths[i], 0);
        }

    }

    /**
     * This method is the maths behind option2 - displaying summaries Inside
     * this method the subscriptions are read from the file and a new summary
     * will be created with the percentage of Bronze, Silver and Gold
     * Subscriptions in the files. The Initial for loop counts the total subs in
     * the file and an average fee is calculated based on the current total
     * subs. The dates are split again and read. Every month is added into a sub
     * cate- gory and the total amount of subs with each month is counted and
     * stored in the variable monthTotal. The month is used in a Hashmap and the
     * months followed by the total subs for each month. The calculations for
     * bronze, silver and gold percentages are then calculated below then the
     * summary for option two is created and displayed with each variable
     * created.
     *
     */
    public void calculateSummaryTwoTotals() {

        for (int j = 0; j < subs.size(); j++) {
            currentTotalSubs++;
            averageMonthlyFee = averageMonthlyFee + subs.get(j).getPrice();

            //bsg percentages
            if (null != subs.get(j).getPackage()) {
                switch (subs.get(j)
                        .getPackage()) {
                    case "B":
                        bronzePackPercent++;
                        break;
                    case "S":
                        silverPackPercent++;
                        break;
                    case "G":
                        goldPackPercent++;
                        break;
                    default:
                        break;
                }
            }

            int eachMonthTotals;
            String subDate = subs.get(j).getDate();
            String[] dateSplit = subDate.split("-");
            String month = dateSplit[1];

            if (this.monthHash.containsKey(month)) {
                eachMonthTotals = this.monthHash.get(month);
                eachMonthTotals++;
                this.monthHash.put(month, eachMonthTotals);
            }
        }

        //working out the percentage
        bronzePackPercent = (bronzePackPercent / currentTotalSubs) * 100.0;
        silverPackPercent = (silverPackPercent / currentTotalSubs) * 100.0;
        goldPackPercent = (goldPackPercent / currentTotalSubs) * 100.0;
        averageMonthlyFee = (averageMonthlyFee / currentTotalSubs) / 100;
        averageMonthlySub = currentTotalSubs / 12;

        System.out.println("Total number of subscriptions: "
                + currentTotalSubs);
        System.out.println("Average Monthly Sub: " + averageMonthlySub);
        System.out.printf("Average Monthly Fee: £%.2f", averageMonthlyFee);
        System.out.println("");
        System.out.println("Percentage of subscriptions: ");
        System.out.println("Bronze: " + bronzePackPercent + "%");
        System.out.println("Silver: " + silverPackPercent + "%");
        System.out.println("Gold: " + goldPackPercent + "%");
        System.out.println("");

        for (String m : orderMonths) {
            System.out.print(m);
            if (this.monthHash.containsKey(m)) {
                System.out.printf(": %-8d", this.monthHash.get(m));
            }
        }
    }

    /**
     * This method is the start of option3. The method requests a month from the
     * user and it is validated and format- ted. The user first has the option
     * to choose a month, if they enter a month in any format in any
     * capitalisation, it is valid. The first 3 characters from the user input
     * are validated and any other input is disregarded. It is wrapped inside of
     * a do while loop to keep the user entering a valid month, the user breaks
     * out of the loop as soon as they enter a correct month.
     */
    public void requestMonth() {
        boolean monthInvalid = true;

        Scanner scanner = new Scanner(System.in);
        String userMonth = "";
        String correctMonth;
        do {
            System.out.println("\nPlease choose a month: ");

            userMonth = scanner.next();
            if (userMonth.length() < 3) {
                System.out.println("\nPlease enter 3 characters minimum");
                continue;
            }
            userMonth = userMonth.substring(0, 1).toUpperCase()
                    + userMonth.substring(1).toLowerCase();
            correctMonth = userMonth.substring(0, 3);
            System.out.println(correctMonth);
            if (this.monthHash.containsKey(correctMonth)) {
                this.month = correctMonth;
                monthInvalid = false;

            }

        } while (monthInvalid);
    }

    /**
     * This method co-insides with the requestMonth method, it is summary for
     * option 3. This method splits the date into the correct month, and if a
     * correct month is entered the values for all subscriptions for the users
     * chosen month are displayed in a summary including the percentage of
     * bronze, silver and gold members. The amount of subs are counted with a
     * for loop to ensure, the correct amount of subs from the reading of the
     * chosen
     *
     *
     */
    public void calculateSummaryThreeTotals() {

        for (int i = 0; i < subs.size(); i++) {
            String subDate = subs.get(i).getDate();
            String[] dateSplit = subDate.split("-");
            String subMonth = dateSplit[1];

            if (subMonth.equals(month)) {
                averageMonthlyFee = averageMonthlyFee + subs.get(i).getPrice();
                currentTotalSubs++;
                //bsg percentages
                if (null != subs.get(i).getPackage()) {
                    switch (subs.get(i)
                            .getPackage()) {
                        case "B":
                            bronzePackPercent++;
                            break;
                        case "S":
                            silverPackPercent++;
                            break;
                        case "G":
                            goldPackPercent++;
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        bronzePackPercent = (bronzePackPercent / currentTotalSubs) * 100.0;
        silverPackPercent = (silverPackPercent / currentTotalSubs) * 100.0;
        goldPackPercent = (goldPackPercent / currentTotalSubs) * 100.0;
        averageMonthlyFee = (averageMonthlyFee / currentTotalSubs) / 100;

        System.out.println("Total number of subscriptions for " + month);
        System.out.printf("Average Monthly Fee: £%.2f", averageMonthlyFee);
        System.out.println("");
        System.out.println("Percentage of subscriptions: ");
        System.out.printf("Bronze: %.2f", bronzePackPercent);
        System.out.printf("\nSilver: %.2f", silverPackPercent);
        System.out.printf("\nGold: %.2f\n", goldPackPercent);

    }

    /**This method is option 4 of the MainMenu.
     * Inside this method the user can use a full and a partial search to find
     * a subscription. The method contains 2 do while loops and a switch statem-
     * ent to make sure the user is happy with their search, if they are happy,
     * they can go ahead and view their search for a name. If they are unhappy,
     * they have the opportunity to re-enter a search. Or they can return back
     * to the main menu. Once the user is happy with their search they can view,
     * this then shows if they user has chosen a correct/incorrect entry and
     * returns the user back to the main menu.
     */
    public void requestSearchTerm() {

        String userName;
        int choice;
        OUTER:
        do {

            System.out.println("\nplease enter a name to search for:");
            userName = scan.nextLine();
            System.out.println("\nYou entered: " + userName);
            System.out.println("\nPress '1' View search, '2' To re-enter search"
                    + " 3 to go back to main menu ");
            if (scan.hasNextInt()) {
                choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {

                    case 1:
                        int subCount = 0;
                        do {
                            for (Subscription sub : subs) {
                                if (sub.getName().toLowerCase().contains(userName.toLowerCase())) {
                                    sub.createNewSummary();
                                    subCount++;
                                }
                            }
                            System.out.println("You have found " + subCount + " subscriptions.");
                            System.out.println("Returning to main menu...");

                            break OUTER;
                        } while (true);
                    case 2:
                        break;
                    case 3:
                        System.out.println("\nHeading back to main menu ");
                        break OUTER;
                    default:
                        System.out.println("\nThat choice is invalid");
                        break;
                }
            } else {
                System.out.println("\nPlease enter a number");
            }
        } while (true);

    }

}
