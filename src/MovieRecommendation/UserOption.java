package MovieRecommendation;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserOption {
    public void showMenu(HashMap<String, Movies> movieMap) {
        Scanner scan = new Scanner(System.in);
        boolean continueProgram = true;

        // Main user options
        while (continueProgram) {
            System.out.println("Main Menu");
            System.out.println("1. Search movie ( sorted by Title )");
            System.out.println("2. Search movie by Genre ( sorted by Title )");
            System.out.println("3. Search movie by Cast");
            System.out.println("4. View Rating");
            System.out.println("5. Add movie recommendation");
            System.out.println("6. Update Rating");
            System.out.println("7. Exit");
            System.out.println("");

            boolean userInput = false;
            while (!userInput) {
                System.out.print("Choose the number from above: ");

                try {
                    String input = scan.nextLine().trim();

                    if (input.matches("\\d+")) {

                        int choice = Integer.parseInt(input);

                        if (choice < 1 || choice > 7) {
                            System.out.println("Your attempt is out of range. Number should be 1 to 7.");
                        } else {
                            userInput = true;
                            continueProgram = handleChoice(choice, movieMap, scan);
                        }
                    } else if (input.matches(".*\\d+.*")) {
                        throw new InputMismatchException(
                                "Your input data has included extra characters, please enter only number...");
                    } else {
                        throw new NumberFormatException(
                                "Sorry... input will accept only number. Please choose between 1 to 7.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("An unexpected error occurred: " + e.getMessage());
                }
            }

            System.out.println("=================================");
        }

        System.out.println("Thank you for using the Movie Recommendation System!");
        System.out.println("=================================");
        scan.close();

    }

    private boolean handleChoice(int choice, HashMap<String, Movies> movieMap, Scanner scan) {
        if (choice == 1) {
            // Display sorted movie list first before choosing one
            System.out.println("");
            System.out.println("Movie List");
            MyMethod.displayTitles(movieMap);
            System.out.println("");

            // Call method to display movie details
            MyMethod.displayMovieDetails(movieMap, scan);
        } else if (choice == 2) {
            MyMethod.searchMovieByGenre(movieMap, scan);
        } else if (choice == 3) {
            MyMethod.searchMovieByCast(movieMap, scan);
        } else if (choice == 4) {
            MyMethod.viewRating(movieMap, scan);
        } else if (choice == 5) {
            MyMethod.addMovieRecommend(movieMap, scan);
        } else if (choice == 6) {
            MyMethod.updateRating(movieMap, scan);
        } else if (choice == 7) {
            return false;
        } else {
            System.out.println("Invalid Option.");
        }
        return true;
    }

}
