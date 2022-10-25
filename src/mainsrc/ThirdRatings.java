package mainsrc;
import java.util.*;
import edu.duke.*;

public class ThirdRatings {
	
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;
	
	public ThirdRatings() {
		//default constructor
		this("ratings.csv");
	}
	
	public ThirdRatings(String ratingsfile) {
		FirstRatings firstRatings = new FirstRatings();
//		myMovies = firstRatings.loadMovies(moviefile);
		myRaters = firstRatings.loadRaters(ratingsfile);
				
	}
	
//	public int getMovieSize() {
//		return myMovies.size();
//	}
	
	public int getRaterSize() {
		return myRaters.size();
	}

	public double getAverageById(String movieId, int minimalRaters ) {
		int n = 0;
		double rating = 0.0;
		for(Rater r : myRaters) {
			if(r.getItemsRated().contains(movieId)) {
				n++;
				rating += r.getRating(movieId);
			}
		}
		
		if(n >= minimalRaters) {
			return (double)rating/n;
		}
		return 0.0;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> ratingInfo = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for (String id : movies) {
			double avg = getAverageById(id, minimalRaters);
			if (avg != 0.0) {
				Rating r = new Rating(id, avg);
				ratingInfo.add(r);
			}
		}
		return ratingInfo;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratingInfo = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for (String id : movies) {
			double avg = getAverageById(id, minimalRaters);
			if (avg != 0.0) {
				Rating r = new Rating(id, avg);
				ratingInfo.add(r);
			}
		}
		return ratingInfo;
	}

	
//	public String getTitle(String id) {
//        for(Movie m : myMovies) {
//            if(m.getID().equals(id)) return m.getTitle();
//        }
//        return "N/A";
//    }
//	
//	public String getID(String title) {
//        for(Movie m : myMovies) {
//            if(m.getTitle().equals(title)) return m.getID();
//        }
//        return "NO SUCH TITLE.";
//    }
}
