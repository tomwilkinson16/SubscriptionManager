package subscriptionManager;

import java.util.Scanner;

/**When the program has been started, this is the first thing to show when the
 * user see's the program.
 * This menu class has all the options in which the user has to enter a new sub,
 * display a summary of subs, display a summary based on a selected month and 
 * find & display subscriptions.
 *
 * @author a0307220
 */
public class MainMenu {

    /**The mainMenu Class has only one method, this is optionsMenu(). This is 
     * the user view when they are tasked for an input.
     * This method first displays a String variable named line, this displays 
     * all available options that the user has the opportunity to view. Its all
     * neatly been tucked into a do while loop so that when the user has finished
     * with one option they have the chance to keep going until they finally 
     * press 0 to quit. Inside the switch statements there are multiple methods 
     * being called from various classes to tie together each option and each option 
     * has a new instance of the methods made. So no incorrect variables are 
     * stored.
     *
     *
     */
    public void optionsMenu() {
        int options;
         Scanner scan = new Scanner(System.in);
  
        do {
            
                        
        String line = "\n1. Enter a new Subscription \n"
                + "2. Display Summary of Subsciptions \n"
                + "3. Display Summary of Subscriptions for Selected "
                + "months \n"
                + "4. Find and Display Subscription \n"
                + "0. Exit";
        
                System.out.println("\n" + line);

            if (scan.hasNextInt()){
                options = scan.nextInt();
                if (String.valueOf(options).length()>1){
                    System.out.println("Please enter only a single number.");
                    scan.nextLine();
                    continue;
                }
                           
            switch (options) {
                case 1:

                    Subscription newSub = new Subscription();
                    newSub.run();
                    break;
                case 2:
                    Summary summarySubs = new Summary();
                    summarySubs.findFileName();
                    summarySubs.createHashMap();
                    summarySubs.calculateSummaryTwoTotals();
                    break;
                case 3:

                    Summary monthSubs = new Summary();
                    monthSubs.findFileName();
                    monthSubs.createHashMap();
                    monthSubs.requestMonth();
                    monthSubs.calculateSummaryThreeTotals();

                    break;
                case 4:
                    Summary summaryFindSubs = new Summary();
                    summaryFindSubs.findFileName();
                    summaryFindSubs.requestSearchTerm();

                    break;
                case 0:
                    System.out.println("Thank you for using the "
                            + "program, goodbye");
                    System.exit(0);
                    
                default:
                    
                    System.out.println("Incorrect input. Please enter a number");
                    scan.nextLine();
                    
            }
            }else{
                    System.out.println("Incorrect input. Please enter a number.");
                    scan.nextLine();
                    }
                                       
        }while (true);
    }
}