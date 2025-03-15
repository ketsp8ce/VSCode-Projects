import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        // main function
        Scanner sc = new Scanner(System.in);
        // instantiates a scanner object called 'sc'
        int userChoice = 0; // user choice for switch statement
        String states = "Alabama\nAlaska\nArizona\nArkansas\nCalifornia\nColorado\nConnecticut\nDelaware\nFlorida\nGeorgia\nHawaii\nIdaho\nIllinois\nIndiana\nIowa\nKansas\nKentucky\nLouisiana\nMaine\nMaryland\nMassachusetts\nMichigan\nMinnesota\nMississippi\nMissouri\nMontana\nNebraska\nNevada\nNewHampshire\nNewJersey\nNewMexico\nNewYork\nNorthCarolina\nNorthDakota\nOhio\nOklahoma\nOregon\nPennsylvania\nRhodeIsland\nSouthCarolina\nSouthDakota\nTennessee\nTexas\nUtah\nVermont\nVirginia\nWashington\nWestVirginia\nWisconsin\nWyoming"; // list of states in one long String
        boolean exited = false; // condition for do-while loop
        do
        {
            // print the main menu selection text
            System.out.println("\n\nMain Menu:\n");
            System.out.println("1. Display The Text");
            System.out.println("2. Search");
            System.out.println("3. Exit Program");
            System.out.println("enter your choice (1, 2 or 3)");
            while (!sc.hasNextInt())
            {
                // input validation for an invalid user selection
                System.out.println("Haha, nice try but you won't catch me skimping on input validation!");
                sc.nextLine();
                // consume new line
                System.out.println("enter your choice (1, 2 or 3)");
            }
            userChoice = sc.nextInt();
            sc.nextLine();
            // consume new line
            // switch statement for user selections
            switch (userChoice)
            {
                case 1:
                System.out.println(
                "the following string contains all US states in alphabetical order. This string can be parsed with various algorithms");
                // description
                System.out.print(states);
                // print the string
                break;
                case 2:
                System.out.println("Please enter a segment or full name of a state");
                // prompt
                String inputText = sc.nextLine();
                boyerMooreSearch(states, inputText);
                // call the Boyer Moore search method with the states string as the
                // 'text' parameter and the inputText as the 'pattern' parameter
                break;
                case 3:
                System.out.println("alright bye hope this was helpful");
                // exit message
                exited = true;
                break;
                default:
                System.out.println("haha! Nice try but I know not to skip my input validation!");
                // another cheeky comment
                // regarding input
                // validation
            }
        }
        while (exited == false);
        System.out.println("bye byeeee!");
        // another exit message
        sc.close();
    }
    // method that encloses the Boyer Moore Algorithm
    public static void boyerMooreSearch(String text, String pattern)
    {
        // the parameters are 'text' for the string to be
        // search and 'pattern' for the pattern to be
        // searched for
        text = text.toLowerCase();
        // Convert the entire text to lowercase
        pattern = pattern.toLowerCase();
        // Convert the entire pattern to lowercase
        // SETUP:
        int[] badChar = new int[256];
        // 256 is the number of ASCII characters. Initalize this 'bad character' array.
        // The keys are all of the ASCII characters and the values stored are the last
        // index where the character last appeared in the pattern.
        for (int i = 0; i < 256; i++)
        {
            badChar[i] = -1;
            // initialize all values in the badChar array to -1, indication that the
            // character does not exist in the pattern
        }
        for (int i = 0; i < pattern.length(); i++)
        {
            // for each character in the pattern
            badChar[Character.toLowerCase(pattern.charAt(i))] = i;
            // store the index of this character as the value of that
            // same character's position in the badChar array. For
            // example, if the pattern contains 'a', the value of the
            // 'a' character in the badChar array will be 0
            // keep everything in lowercase so that the algorithm is case insensitive
        }
        // initialize new variables to be used in the Boyer Moore algorithm
        boolean foundPattern = false;
        // check if the patten has been found in the text (initialize to false)
        int shift = 0;
        // initialized to zero but will be changed to represent the number of characters
        // to shift the text to the right to account for the pattern
        int m = pattern.length();
        // define a variable to represent the length of the user input, the 'pattern'
        int n = text.length();
        // define a variable to represent the length of the original very long string,
        // the 'text'
        while (shift <= n - m) // while the shift is less than or equal to the length of the text minus the
        // length of the pattern
        {
            int q = m - 1;
            // define variable 'q' to be one left from m
            while (q >= 0 && pattern.charAt(q) == text.charAt(shift + q)) // while q is nonzero and the character at the index
            // of q in the pattern is equal to the character at
            // the index of q in the text
            {
                q--;
                // move left to the previous character in the pattern (user input)
            }
            if (q < 0) // if q is less than zero, meaning that the pattern has been found in the text
            {
                System.out.println("the pattern you entered was found at index " + shift + " in the text");
                foundPattern = true;
                // set the foundPattern variable to true
                shift += (shift + m < n) ? m - badChar[text.charAt(shift + m)] : 1; // reset shift for next iteration of the
                // while loop
            }
            else // A mismatch occurred at position 'q'
            // Shift the pattern based on the bad character rule
            {
                shift += Math.max(1, q - badChar[text.charAt(shift + q)]);
            }
        }
        if (foundPattern == false)
        {
            // if the pattern has not been found in the text
            System.out.println("that pattern is not a valid criteria segment for a state");
        }
    }
}