package subscriptionManager;

import java.time.LocalDate;
import java.util.Scanner;


public class ValidateDiscount {
    /**
     * * This class is dedicated to the discount in option1.Inside this class there
     * is 1 method in which the user has to enter a correct code or "-". Every
     * letter s validated in its own if statement using charAt. If any incorrect
     * character is entered, the user will be re-prompted to re-enter a valid
     * discount code. To find out if the user has entered a correct year, there is
     * validation to make sure that they enter the current year and there is
     * validation in which checks to see if the user has entered a correct first or
     * second half of the year code. This makes the discount code re-useable and
     * will be valid for any year or month entered for any future use. To finish
     * this method off the discount percentage is calculated and distributed based
     * on user entry. Discount is then returned back to option 1.
     *
     *
     * @return discount
     */
    public static String getDiscountCode() {
        String discount = "";
        Scanner scan = new Scanner(System.in);
        boolean validCheck = true;

        do {
            LocalDate date = LocalDate.now();
            int currentYear = date.getYear();
            int currentMonth = date.getMonthValue();

            //This checks to see if a discount has been added and if its 
            //been added, is it correct?
            System.out.print("\nPlease enter a discount code: ");
            System.out.println("\nEnter '-' if you do not have a code.");
            discount = scan.nextLine().toUpperCase();

            if (discount.equals("-")) {
                discount = "-";
                break;
            } else if (discount.length() != 6) {
                System.out.println("Invalid length.");
                continue;
            } else if (!Character.isLetter(discount.charAt(0))) {
                System.out.println("Invalid first character, must be letter.");
                continue;
            } else if (!Character.isLetter(discount.charAt(1))) {
                System.out.println("Invalid second character, must be letter.");
                continue;
            } else if (!Character.isDigit(discount.charAt(2))) {
                System.out.println("Invalid third character, must be number.");
                continue;
            } else if (!Character.isDigit(discount.charAt(3))) {
                System.out.println("Invalid fourth character, must be number.");
                continue;
            } else if (!Character.isLetter(discount.charAt(4))) {
                System.out.println("Invalid fifth character, must be letter.");
                continue;
            } else if (!Character.isDigit(discount.charAt(5))) {
                System.out.println("Invalid sixth character, must be number.");
                continue;
            }

            // Check year
            int subYear = Integer.parseInt(String.valueOf(discount.charAt(2))
                    + String.valueOf(discount.charAt(3))) + 2000;
            if (subYear != currentYear) {
                System.out.println("Invalid year, please enter the current "
                        + "year as 'YY'.");
                continue;
            }

            // Check month
            if (discount.charAt(4) != 'E' && discount.charAt(4) != 'L') {
                System.out.println("Invalid month char.");
                continue;
            }
            if (discount.charAt(4) == 'E' && (currentMonth < 1
                    || currentMonth > 6)) {
                System.out.println("Invalid code, month is not in "
                        + "the first half of the year.");
                continue;
            }
            if (discount.charAt(4) == 'L' && (currentMonth < 6
                    || currentMonth > 12)) {
                System.out.println("Invalid code, month is not in "
                        + "the second half of year.");
                continue;
            }
            int discountPercentage = Integer.valueOf(String.valueOf(discount.charAt(5)));
            if (discountPercentage < 1) {
                System.out.println("Invalid discount percentage");
                continue;
            }
            break;
        } while (validCheck);

        return discount;
    }
}
