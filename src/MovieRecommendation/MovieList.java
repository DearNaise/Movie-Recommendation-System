package MovieRecommendation;

import java.util.HashMap;

public class MovieList {
        public static HashMap<String, Movies> getMovies() {

                HashMap<String, Movies> movieMap = new HashMap<>();

                movieMap.put("Titanic", new Movies("Titanic", "Romance", 7.8,
                                1997, "Leonardo", "Kate Winslet"));
                movieMap.put("Little Women",new Movies("Little Women", "Romance", 7.9,
                                2019, "Timothee", "Emma Watson"));

                movieMap.put("Inception", new Movies("Inception", "Sci-Fi", 8.8,
                                2010, "Leonardo", "Joseph"));
                movieMap.put("Dune", new Movies("Dune", "Sci-Fi", 8,
                                2010, "Timothee", "Zendaya"));

                movieMap.put("The Avengers", new Movies("Avengers", "Action", 8.4,
                                2019, "Robert Downey Jr.", "Scarlett Johansson"));
                movieMap.put("John Wick", new Movies("John Wick", "Action", 7.4,
                                2014, "Keanu Reeves", "Ian McShane"));
                
                
                movieMap.put("Rush Hour", new Movies("Rush Hour", "Comedy", 7.0,
                                1998, "Jackie Chan", "Chris Tucker"));
                movieMap.put("The Hangover", new Movies("The Hangover", "Comedy", 7.7,
                                2009, "Bradley Cooper", "Zach Galifianakis"));
                
                
                movieMap.put("Harry Potter", new Movies("Harry Potter", "Fantasy", 7.6,
                                2001, "Daniel Radcliffe", "Emma Watson"));
                movieMap.put("The Lord of the Rings", new Movies("The Lord of the Rings", "Fantasy", 8.8,
                                2001, "Elijah Wood", "Ian McKellen"));

        
                return movieMap;

        }

}
