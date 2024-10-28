package MovieRecommendation;

import java.util.HashMap;

public class MainApp {
    public static void main(String[] args) {

        HashMap<String, Movies> movieMap = MovieList.getMovies();

        // Display all movie list
        MyMethod.display(movieMap);

        // Options like menu to choose action
        UserOption options = new UserOption();
        options.showMenu(movieMap);

    }

}
