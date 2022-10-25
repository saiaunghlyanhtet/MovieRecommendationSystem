package mainsrc;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	public void printAverageRatings() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for" + RaterDatabase.size() + " raters.");
		//System.out.println("No of raters" + secondRatings.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
        
        ArrayList<Rating> rating = fourthRatings.getAverageRatings(35);
        System.out.println("found " + rating.size() + " movies");
        Collections.sort(rating);
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        	break;
        }
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for" + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        YearsAfterFilter yaf = new YearsAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilter af = new AllFilter();
        af.addFilter(yaf);
        af.addFilter(gf);
        ArrayList<Rating> rating = fourthRatings.getAverageRatingsByFilter(8, af);
        System.out.println("found " + rating.size() + " movies");
        Collections.sort(rating);
        for(Rating r : rating) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
                               + "\t" + MovieDatabase.getTitle(r.getItem()) + "\n\t"
                               + MovieDatabase.getGenres(r.getItem()));
                               break;
        }
    }
	
	public void printSimilarRatings() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatings("71", 20, 5);
		for(Rating r : ratings) {
			System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue());
			break;
		}
	}
	
	public void printSimilarRatingsByGenre() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
		for(Rating r : ratings) {
			System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue() + 
					"\n" + MovieDatabase.getGenres(r.getItem()));
			break;
		}
	}
	
	public void printSimilarRatingsByDirector() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
		for(Rating r : ratings) {
			System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + r.getValue() + 
					"\n" + MovieDatabase.getDirector(r.getItem()));
			break;
		}
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
		 AllFilter af = new AllFilter();
	     af.addFilter(new GenreFilter("Drama"));
	     af.addFilter(new MinutesFilter(80,160));
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, af);
		for(Rating r : ratings) {
			System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + MovieDatabase.getMinutes(r.getItem()) + "m\t" + r.getValue() + 
					"\n" + MovieDatabase.getGenres(r.getItem()));
			break;
		}
	}
	
	public void printSimilarRatingsByYearAfterAndMinutes() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		System.out.println("Read data for " + RaterDatabase.size() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
		 AllFilter af = new AllFilter();
	     af.addFilter(new YearsAfterFilter(1975));
	     af.addFilter(new MinutesFilter(70,200));
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, af);
		for(Rating r : ratings) {
			System.out.println(MovieDatabase.getTitle(r.getItem()) + "\t" + MovieDatabase.getMinutes(r.getItem()) + "m\t" + r.getValue() + 
					"\n" + MovieDatabase.getYear(r.getItem()));
			break;
		}
	}
	
			
}
