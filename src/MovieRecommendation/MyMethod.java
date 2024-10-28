package MovieRecommendation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import ExceptionsHandling.MovieNotFoundException;

public class MyMethod {
    // Display Method
    public static void display(HashMap<String, Movies> movieMap) {

        System.out.println("Moive Lists");
        System.out.println("---------------------------------");

        for (Movies movie : movieMap.values()) {
            System.out.println("Title: " + movie.getTitle());
        }

        System.out.println("=================================");
    }

    // Sorted Movie List after choosing search movie by Title
    public static void displayTitles(HashMap<String, Movies> movieMap) {
        String[] titles = movieMap.keySet().toArray(new String[0]);

        quickSort(titles, 0, titles.length - 1);

        for (String title : titles) {
            System.out.println("Title: " + title);
        }
    }

    // Quick Sort Method
    private static void quickSort(String[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Partition method for Quick Sort
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {

            // If current element is smaller than or equal to pivot
            if (arr[j].compareTo(pivot) <= 0) {
                i++;

                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // displayMovieDetails Method
    public static void displayMovieDetails(HashMap<String, Movies> movieMap, Scanner scan) {
        while (true) {
            try {
                System.out.print("\nEnter the title you want to get details: ");
                String title = scan.nextLine().trim();
    
                if (title.matches("\\d+")) {
                    throw new InputMismatchException("Sorry...number cannot be entered. Please enter the title only...");
                }
    
                // Case-insensitive search for the movie
                Movies foundMovie = null;
                for (String key : movieMap.keySet()) {
                    if (key.equalsIgnoreCase(title)) {
                        foundMovie = movieMap.get(key);
                        break;
                    }
                }
    
                // If movie was found, display details
                if (foundMovie != null) {
                    movieDetail(foundMovie);
                    break;
                } else {
                    throw new MovieNotFoundException("Movie cannot be found. Please enter only valid movie title from above list...");
                }
    
            } catch (MovieNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // movieDetail Method
    public static void movieDetail(Movies movie) {
        System.out.println("\nTitle: " + movie.getTitle());
        System.out.println("Genre: " + movie.getGenre());
        System.out.println("Rating: " + movie.getRating());
        System.out.println("Release Year: " + movie.getReleaseYear());
        System.out.println("Actor: " + movie.getActorName());
        System.out.println("Actress: " + movie.getActressName());
        System.out.println("");
    }

    // searchMovieByGenre method
    public static void searchMovieByGenre(HashMap<String, Movies> movieMap, Scanner scan) {
        HashMap<String, Boolean> genreMap = new HashMap<>();
    for (Movies movie : movieMap.values()) {
        genreMap.put(movie.getGenre(), true);
    }

    displayAvailableGenres(genreMap);

    while (true) {
        try {
            System.out.print("\nEnter the genre you want to search: ");
            String selectedGenre = scan.nextLine().trim();

            if (selectedGenre.matches("\\d+")) {
                throw new InputMismatchException("Sorry...number cannot be worked. Please enter the full name of genre only...");
            }

            boolean genreFound = false;

            // Check for genre with case insensitive
            for (String genre : genreMap.keySet()) {
                if (genre.equalsIgnoreCase(selectedGenre)) {
                    genreFound = true;
                    break;
                }
            }

            if (!genreFound) {
                throw new IllegalArgumentException("Genre not found. Please enter an existing genre.");
            }

            displayMoviesByGenre(movieMap, selectedGenre);
            break;
        } catch (InputMismatchException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}

    // displayAvailableGenres Method
    private static void displayAvailableGenres(HashMap<String, Boolean> genreMap) {
        String[] genres = genreMap.keySet().toArray(new String[0]);
        quickSort(genres, 0, genres.length - 1);

        System.out.println("\nAvailable Genres");
        System.out.println("-------------------");
        for (String genre : genres) {
            System.out.println(genre);
        }
        System.out.println("");
    }

    // displayMoviesByGenre Method
    private static void displayMoviesByGenre(HashMap<String, Movies> movieMap, String selectedGenre) {
        boolean found = false;
        System.out.println("\nMovies in Genre: " + selectedGenre);
        for (Movies movie : movieMap.values()) {
            if (movie.getGenre().equalsIgnoreCase(selectedGenre)) {
                movieDetail(movie);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No movies found in the selected genre.");
        }
    }

    // searchMovieByCast Method
    public static void searchMovieByCast(HashMap<String, Movies> movieMap, Scanner scan) {
        while (true) {
            try {
                System.out.print("\nEnter the name of the actor or actress: ");
                String castName = scan.nextLine().trim();

                if (castName.matches("\\d+")) {
                    throw new InputMismatchException("Sorry...number cannot be worked. Please enter the name of actor or actress...");
                }
    
                if (castName.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty. Please enter a valid name.");
                }
    
                boolean found = false;
                System.out.println("");
                System.out.println("Movies featuring: " + castName);
                for (Movies movie : movieMap.values()) {
                    if (movie.getActorName().equalsIgnoreCase(castName) || movie.getActressName().equalsIgnoreCase(castName)) {
                        movieDetail(movie);
                        found = true;
                    }
                }
    
                if (!found) {
                    System.out.println("No movies found with the selected cast.");
                    System.out.println("\nHere is the list of available actors and actresses:");
                    for (Movies movie : movieMap.values()) {
                        System.out.println(movie.getActorName() + " & " + movie.getActressName());
                    }
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // viewRating Method
    public static void viewRating(HashMap<String, Movies> movieMap, Scanner scan) {
        while (true) {
            try {
                System.out.print("\nEnter the rating to search (from 0.1 to 9.9): ");
                String ratingInput = scan.nextLine().trim(); // Read input as String
    
                // Check if the input is empty
                if (ratingInput.isEmpty()) {
                    System.out.println("Rating cannot be empty. Please enter a valid rating.");
                    continue;
                }
    
                double rating = Double.parseDouble(ratingInput); // Parse input to double
    
                if (rating < 0.1 || rating > 9.9) {
                    throw new IllegalArgumentException("Rating must be between 0.1 and 9.9.");
                }
    
                double lowerBound = rating;
                double upperBound = rating + 0.9;
    
                boolean found = false;
                System.out.println("Movies with rating between " + lowerBound + " and " + upperBound + ":");
                for (Movies movie : movieMap.values()) {
                    if (movie.getRating() >= lowerBound && movie.getRating() <= upperBound) {
                        movieDetail(movie);
                        found = true;
                    }
                }
    
                if (!found) {
                    System.out.println("No movies found within this rating range.");
                }
                break; // Exit loop after successful input
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 0.1 and 9.9.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // addMovieRecommend Method
    public static void addMovieRecommend(HashMap<String, Movies> movieMap, Scanner scan) {
        while (true) {
            try {
                System.out.print("\nEnter the title you want to recommend: ");
                String newTitle = scan.nextLine().trim();
    
                if (newTitle.isEmpty()) {
                    throw new IllegalArgumentException("Title cannot be empty.");
                }
    
                if (newTitle.matches("\\d+")) {
                    throw new IllegalArgumentException("Title cannot add with number.");
                }

                if (newTitle.matches(".*[@/^&]%^")) {
                    throw new IllegalArgumentException("Please make sure special characters are not contained.");
                }

                if (!Character.isUpperCase(newTitle.charAt(0))) {
                    throw new IllegalArgumentException("Title must start with an uppercase letter.");
                }
    
                for (String existingTitle : movieMap.keySet()) {
                    if (existingTitle.equalsIgnoreCase(newTitle)) {
                        throw new IllegalArgumentException("This movie already exists, please enter the new one.");
                    }
                }
    
                String[] validGenres = {"action", "comedy", "fantasy", "romance", "sci-fi"};
                String genre = "";
                while (true) {
                    System.out.print("\nEnter movie genre (action, comedy, fantasy, romance, sci-fi): ");
                    genre = scan.nextLine().trim().toLowerCase();
                    if (genre.isEmpty()) {
                        System.out.println("Genre cannot be empty. Please enter a valid genre.");
                    } else if (genre.matches("\\d+")) {
                        System.out.println("Genre cannot be described with a number...");
                    } else if (genre.matches(".*[@/^&]%^")) {
                        System.out.println("Genre cannot contain special characters...");
                    } else if (!Arrays.asList(validGenres).contains(genre)) { // Check if genre is not in valid list
                        System.out.println("Genre is not available. Please choose from: action, comedy, fantasy, romance, sci-fi.");
                    } else {
                        break;
                    }
                }
    
                double rating = 0; 
                boolean validRating = false;
                while (!validRating) {
                    try {
                        System.out.print("\nEnter the rating (between 0.1 to 9.9): ");
                        String ratingInput = scan.nextLine().trim(); 
                        
                        if (ratingInput.isEmpty()) {
                            System.out.println("Rating cannot be empty. Please enter a valid rating.");
                            continue; 
                        }
                        
                        rating = Double.parseDouble(ratingInput); 
                        if (rating < 0.1 || rating > 9.9) {
                            throw new IllegalArgumentException("Rating must be between 0.1 and 9.9.");
                        }
                        validRating = true; 
                    } catch (NumberFormatException e) {
                        System.out.println("Your input is not available. Please enter a valid number between 0.1 and 9.9.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
    
                int releaseYear = 0; 
                boolean validReleaseYear = false;
                while (!validReleaseYear) {
                    try {
                        System.out.print("\nEnter release year: ");
                        String yearInput = scan.nextLine().trim(); 
                        
                        if (yearInput.isEmpty()) {
                            System.out.println("Release year cannot be empty. Please enter a valid year.");
                            continue;
                        }
                        
                        releaseYear = Integer.parseInt(yearInput);
                        
                        if (releaseYear <= 1989 || releaseYear >= 2025) {
                            throw new IllegalArgumentException("Release year can only be between 1990 and 2024");
                        }
                        
                        validReleaseYear = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Input is not available. Please enter a valid release year.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
    
                String actorName;
                while (true) {
                    System.out.print("\nEnter actor's name: ");
                    actorName = scan.nextLine().trim();
                    if (actorName.isEmpty()) {
                        System.out.println("Actor's name cannot be empty.");
                    } else if (actorName.matches(".*[@/^&]%^")) { 
                        System.out.println("Actor's name cannot contain numbers.");
                    } else if (actorName.matches(".*[@/^&]%^")) {
                        System.out.println("Actor's name cannot contain special characters.");
                    } else {
                        break;
                    }
                }
    
                String actressName;
                while (true) {
                    System.out.print("\nEnter actress's name: ");
                    actressName = scan.nextLine().trim();
                    if (actressName.isEmpty()) {
                        System.out.println("Actress's name cannot be empty.");
                    } else if (actressName.matches(".*[@/^&]%^")) { 
                        System.out.println("Actress's name cannot contain numbers.");
                    } else if (actorName.matches(".*.*[@/^&]%^")) {
                        System.out.println("Actress's name cannot contain special characters.");
                    } else {
                        break;
                    }
                }
    
    
                // Add new movie to the HashMap
                Movies newMovie = new Movies(newTitle, genre, rating, releaseYear, actorName, actressName);
                movieMap.put(newTitle, newMovie);

                System.out.println("");
                System.out.println("Thanks for your recommendation. We will display this in our movie list.");
                break; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    // updateRating Method
    public static void updateRating(HashMap<String, Movies> movieMap, Scanner scan) {
        String title;
        while (true) {
            System.out.print("\nEnter the title of the movie you want to update the rating for: ");
            title = scan.nextLine().trim();
    
            // Check if the title is empty
            if (title.isEmpty()) {
                System.out.println("Title cannot be empty. Please enter a valid title.");
                continue;
            }
    
            // Check if the title is numeric
            if (title.matches("\\d+")) {
                System.out.println("Title cannot be a number. Please enter a valid movie title.");
                continue;
            }
    
            // Check if the movie exists in a case-insensitive
            Movies movieToUpdate = null;
            for (String key : movieMap.keySet()) {
                if (key.equalsIgnoreCase(title)) {
                    movieToUpdate = movieMap.get(key);
                    break;
                }
            }
    
            if (movieToUpdate == null) {
                System.out.println("Movie not found.");
                return; // Exit method if movie not found
            }
    
            double newRating;
            while (true) {
                try {
                    System.out.print("\nEnter new rating between 0.1 to 9.9: ");
                    String ratingInput = scan.nextLine().trim(); // Read as string for empty check
                    
                    // Check if the rating input is empty
                    if (ratingInput.isEmpty()) {
                        System.out.println("Rating cannot be empty. Please enter a valid rating.");
                        continue;
                    }
    
                    newRating = Double.parseDouble(ratingInput); // Parse input to double
                    if (newRating < 0.1 || newRating > 9.9) {
                        throw new IllegalArgumentException("Rating must be between 0.1 and 9.9...");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for rating. Please enter a number between 0.1 and 9.9 only...");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
    
            // Update the rating in the movie object
            movieToUpdate.rating = newRating;
    
            System.out.println("");
            System.out.println("Thanks for your rating. Here's the updated movie detail:");
            System.out.println("");
    
            MyMethod.movieDetail(movieToUpdate);
            break; // Exit loop after successful rating update
        }
    }

}
