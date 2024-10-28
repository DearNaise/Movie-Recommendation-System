package MovieRecommendation;

public class Movies {
    String title;
    String genre;
    double rating;
    int releaseYear;
    String actorName;
    String actressName;

    Movies(String title, String genre, double rating, int releaseYear, String actorName, String actressName){
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.actorName = actorName;
        this.actressName = actressName;
    }

    public String getTitle(){
        return title;
    }

    public String getGenre(){
        return genre;
    }

    public double getRating(){
        return rating;
    }

    public int getReleaseYear(){
        return releaseYear;
    }

    public String getActorName(){
        return actorName;
    }
    
    public String getActressName(){
        return actressName;
    }

}