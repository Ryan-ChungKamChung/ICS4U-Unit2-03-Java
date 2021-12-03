/*
* This program generates 250 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Ryan Chung
* @version 1.0
* @since   2021-12-03
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * This is the BinarySearch program.
 * */
final class BinarySearch {

    /**
    * The min number for array.
    */
    public static final int MIN = 0;
    /**
    * The max number for array.
    */
    public static final int MAX = 999;

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private BinarySearch() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param numberArray randomly generated array
    * @param userNumber user-inputted number to search
    * @param lowIndex lower range to search
    * @param highIndex higher range to search
    * @return the index after recursively finding the userNumber
    */
    static int binarySearch(final int[] numberArray, final int userNumber,
                          final int lowIndex, final int highIndex) {

        final int returnValue;

        if (highIndex >= lowIndex) {
            final int mid = (highIndex + lowIndex) / 2;

            if (numberArray[mid] == userNumber) {
                returnValue = mid;
            } else if (numberArray[mid] > userNumber) {
                returnValue =
                    binarySearch(numberArray, userNumber, lowIndex, mid - 1);
            } else {

                returnValue =
                    binarySearch(numberArray, userNumber, mid + 1, highIndex);
            }
        } else {
            returnValue = -1;
        }

        return returnValue;
    }

    /**
    * Generates a random array of integers.
    *
    * @return a random array of integers
    */
    static int[] generateRandomArray() {

        final Random randNumber = new Random();

        final int arraySize = 250;
        final int[] randomNumberArray = new int[arraySize];

        for (int counter = 0; counter < randomNumberArray.length; counter++) {
            randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
        }

        final int[] numberArray = randomNumberArray;
        Arrays.sort(numberArray);

        return randomNumberArray;
    }

    /**
    * Takes user input and finds if it is in a random generated array.
    *
    * @param args No args used.
    */
    public static void main(final String[] args) {
        try {

            final int searchNumber;
            final int searchResult;
            final int[] numberArray = generateRandomArray();

            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }

            System.out.print("\n\n");

            System.out.print("What number are you searching for in the array"
                    + "(integer between 0 and 999): ");

            final String searchNumberString = new BufferedReader(
                    new InputStreamReader(System.in)
            ).readLine();

            searchNumber = Integer.parseInt(searchNumberString);

            if (searchNumber < MIN || searchNumber > MAX) {
                throw new IOException();
            } else {
                searchResult = binarySearch(
                        numberArray, searchNumber, 0, numberArray.length - 1);

                if (searchResult != -1) {
                    System.out.println("Your number is in index: "
                            + searchResult);
                } else {
                    System.out.println("The number isn't in the array!");
                }
            }

        } catch (IOException | NumberFormatException exception) {
            System.out.println("ERROR: Invalid Input");
        }

        System.out.println("\nDone.");
    }
}

