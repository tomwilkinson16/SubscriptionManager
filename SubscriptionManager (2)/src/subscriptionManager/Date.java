package subscriptionManager;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author a0307220
 */
public class Date {
    
    /**This method creates a date and formats it, then returns the formatted date
     * back to the method in which it is needed.
     * Inside this method the LocalDate ld variable is used to get the date as
     * a current date, this sets the new subscription to today's date\n.
     * The DateTimeFormatter.ofPattern is user to set the new subscription in a 
     * format of "dd-MMM-yyyy". Using this the "MMM" will give the user the date
     * as 3 characters.
     * 
     * @return dtf.format(ld);
     */
    public static String createDate() {

        LocalDate ld = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        return dtf.format(ld);

    }

    /**This method creates a month in which the user can use to display values.
     * Inside the method the local date is set to a random date and the DateTime
     * formatter is in place to format the month so it's consistent with the 
     * ICA brief. 
     * 
     * @return dtf.format(lm);
     */
    public static String createMonth() {

        LocalDate lm = LocalDate.of(2019, 12, 13);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        Month month = lm.getMonth();
        System.out.println(month);

        return dtf.format(lm);
    }
}
    
