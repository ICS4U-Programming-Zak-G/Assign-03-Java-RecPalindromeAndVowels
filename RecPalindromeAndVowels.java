// Import libraries
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

/** .
* This program performs recursion to make a number into a palindrome
* or checks the number of vowels in a string.
*
* @author  Zak Goneau
* @version 1.0
* @since   2025-05-08
*/

// Creating class
public final class RecPalindromeAndVowels {
    /**
     * This is a private constructor used to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private RecPalindromeAndVowels() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {

        // Initialize output string
        String outputStr = "";

        // Introduce program to user
        System.out.println("This program uses recursion to make a "
                + "number into a palindrome (1) or checks the number "
                    + "of vowels in a string(2). ");
        System.out.println("The results will be written to a file "
            + "called output.txt.\n");

        // Get user input for program choice
        System.out.println("Please enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        String choiceString = scanner.nextLine();

        // Try casting user input to an integer
        try {
            int choice = Integer.parseInt(choiceString);

            // Check if choice is first one
            if (choice == 1) {
                // Scan integers file
                File file = new File("integers.txt");
                Scanner scannerTwo = new Scanner(file);

                // Read each line of the file
                while (scannerTwo.hasNextLine()) {
                    // Assign each line to a string
                    String integerString = scannerTwo.nextLine();

                    // Try casting user input to an integer
                    try {
                        // Cast string to integer
                        int number = Integer.parseInt(integerString);

                        // Check if number is negative
                        if (number < 0) {
                            // Add to output string if input is negative
                            outputStr += "Your number cannot be negative.\n";

                            // Continue to next line
                            continue;

                        // Check if number is a single digit
                        } else if (number > -10 && number < 10) {
                            // Add to output string if input is a single digit
                            outputStr += "Your number must be "
                                + "more than one digit.\n";

                            // Continue to next line
                            continue;

                        // Otherwise, check if number is palindrome
                        } else {
                            // Initialize counter
                            int counter = 0;

                            // Call function to check for palindrome
                            int palindromeResult = recPalindrome(
                                number, counter);

                            // Add result to output string
                            outputStr += "The amount of times it "
                                + "took to make the number "
                                    + number + " into a palindrome is: "
                                        + palindromeResult + "\n";
                        }

                    // Catch invalid conversion errors
                    } catch (NumberFormatException error) {
                        // Add to output string if input is not an integer
                        outputStr += "Your number was not an integer.\n";

                        // Continue to next line
                        continue;
                    }
                }
                // Close scanner
                scannerTwo.close();

            // Check if choice is second one
            } else if (choice == 2) {
                // Scan words file
                File file = new File("words.txt");
                Scanner scannerThree = new Scanner(file);

                // Read each line of the file
                while (scannerThree.hasNextLine()) {
                    // Assign each line to a string
                    String word = scannerThree.nextLine();

                    // Initialize counter
                    int counter = 0;

                    // Check if string has anything besides characters
                    // Matches functionality:
                    // https://docs.vultr.com/java/examples/check-whether-a-character-is-alphabet-or-not
                    if (word.matches("[a-zA-Z]+")) {
                        // Call function to check for vowels
                        int vowelResult = recVowels(word, counter);

                        // Add result to output string
                        outputStr += "The amount of vowels in the string "
                                + word + " is: " + vowelResult + "\n";

                    // Otherwise string has something besides letters
                    } else {
                        // Add to output string if input is not a string
                        outputStr += "Your string must be only letters, "
                            + "no symbols or numbers.\n";
                    }
                }
                // Close scanner
                scannerThree.close();

            // Otherwise choice is invalid
            } else {
                // Add to output string if input isn't valid number
                outputStr += "Your choice was not 1 or 2.\n";
            }

        // Catch invalid conversion errors
        } catch (NumberFormatException error) {
            // Add to output string if input is not an integer
            outputStr += "Your choice was not a number.\n";
        }

        // Open writer to write to file
        FileWriter myWriter = new FileWriter("output.txt");

        // Write to file
        myWriter.write(outputStr);

        // Print success message
        System.out.println("Successfully wrote to the file.");

        // Close scanner and writer
        scanner.close();
        myWriter.close();
    }

    /**
    * This is the method for determining if a number is a palindrome.
    *
    * @param number the number to check if it is a palindrome
    * @return true or false
    */

    // Declare function to check if number is palindrome
    public static boolean fullPalindrome(final int number) {
        // Check if number is negative
        if (number < 0) {
            // Return false
            return false;

        // Otherwise, check if number is palindrome
        } else {
            // Get reverse number
            int reverseNum = recRevNum(number, 0);

            // Check if number is equal to reverse number
            return number == reverseNum;
        }
    }

    /**
    * Method for making a number into a palindrome and getting attempts.
    *
    * @param number the number to make into a palindrome
    * @param counter the number of times it took to make the number a palindrome
    * @return counter
    */

    // Declare function to make number palindrome
    public static int recPalindrome(final int number, int counter) {
        // Set base case once number is a palindrome
        if (fullPalindrome(number)) {

            // Return counter
            return counter;

        // Otherwise, check if number is palindrome recursively
        } else {
            // Increment counter
            counter++;

            // Call function recursively
            return recPalindrome(number + recRevNum(number, 0), counter);
        }
    }

    /**
    * This is the method for reversing an integer.
    *
    * @param number the number to reverse
    * @param reverseNum the reversed number
    * @return reversed number
    */

    // Declare function to reverse integer
    public static int recRevNum(final int number, int reverseNum) {
        // Set base case, once number is 0
        if (number == 0) {

            // Return reverse number
            return reverseNum;

        // Otherwise, reverse number recursively
        } else {
            // Get remainder
            int remainder = number % 10;

            // Get reverse number
            reverseNum = (reverseNum * 10) + remainder;

            // Call function recursively
            return recRevNum(number / 10, reverseNum);
        }
    }

    /**
    * This is the method for checking for vowels in a string.
    *
    * @param word the word to check for vowels
    * @param vowels the number of vowels in the word
    * @return number of vowels
    */

    // Declare function to make number palindrome
    public static int recVowels(final String word, final int vowels) {
        // Set base case
        if (word.length() == 0) {
            return vowels;

        // Otherwise, check if first character is vowel
        } else {
            // Get first character of word
            char firstChar = word.charAt(0);

            // Check if first character is a vowel
            if (Character.toLowerCase(firstChar) == 'a'
                || Character.toLowerCase(firstChar) == 'e'
                    || Character.toLowerCase(firstChar) == 'i'
                        || Character.toLowerCase(firstChar) == 'o'
                            || Character.toLowerCase(firstChar) == 'u') {

                // If it is a vowel, add to counter recursively
                return recVowels(word.substring(1), vowels + 1);

            // Call function recursively, removing first character
            } else {
                return recVowels(word.substring(1), vowels);
            }
        }
    }

}
