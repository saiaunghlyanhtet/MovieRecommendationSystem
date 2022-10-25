package mainsrc;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	public void printAverageRatings() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		//System.out.println("No of raters" + secondRatings.getRaterSize());
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
		
        
        ArrayList<Rating> rating = thirdRatings.getAverageRatings(35);
        System.out.println("found " + rating.size() + " movies");
        Collections.sort(rating);
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem()));
        	break;
        }
	}
	
	public void printAverageRatingsByYear() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        YearsAfterFilter yaf = new YearsAfterFilter(2000);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(20, yaf);
        System.out.println("Found " + rating.size() + " moives.");
        Collections.sort(rating);
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
            + "\t" + MovieDatabase.getTitle(r.getItem()));
            break;
        }
     
	}
	
	public void printAverageRatingsByGenre() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        GenreFilter genre = new GenreFilter("Comedy");
        ArrayList<Rating> rating = thirdRatings.getAverageRatings(20);
        System.out.println("Found " + rating.size() + " movies.");;
        Collections.sort(rating);;
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem() + 
        			"\n\t" + MovieDatabase.getGenres(r.getItem())));
        			break;
        	
        }
           
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        MinutesFilter minutes = new MinutesFilter(105, 135);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(5, minutes);
        System.out.println("Found " + rating.size() + " movies.");;
        Collections.sort(rating);
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getMinutes(r.getItem() + " min")
        	+ "\t" + MovieDatabase.getTitle(r.getItem()));
        	break;
        }
	}
	
	public void printAverageRatingsByDirectors() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        DirectorsFilter directors = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(4, directors);
        System.out.println("Found " + rating.size() + " movies.");;
        Collections.sort(rating);
        for (Rating r : rating) {
        	System.out.println(r.getValue() + "\t" + MovieDatabase.getTitle(r.getItem())
        	+ "\n\t" + MovieDatabase.getDirector(r.getItem()));
        	break;
        }
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        YearsAfterFilter yaf = new YearsAfterFilter(1990);
        GenreFilter gf = new GenreFilter("Drama");
        AllFilter af = new AllFilter();
        af.addFilter(yaf);
        af.addFilter(gf);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(8, af);
        System.out.println("found " + rating.size() + " movies");
        Collections.sort(rating);
        for(Rating r : rating) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getYear(r.getItem())
                               + "\t" + MovieDatabase.getTitle(r.getItem()) + "\n\t"
                               + MovieDatabase.getGenres(r.getItem()));
                               break;
        }
    }
	
	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
		System.out.println("Read data for" + thirdRatings.getRaterSize() + " raters.");
		MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Read data for " + MovieDatabase.size() + " movies.");
        
        DirectorsFilter df = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        MinutesFilter mf = new MinutesFilter(90, 180);
        AllFilter af = new AllFilter();
        af.addFilter(df);
        af.addFilter(mf);
        ArrayList<Rating> rating = thirdRatings.getAverageRatingsByFilter(3, af);
        System.out.println("found " + rating.size() + " movies");
        Collections.sort(rating);
        for(Rating r : rating) {
            System.out.println(r.getValue() + "\t" + MovieDatabase.getMinutes(r.getItem() + " min")
                               + "\t" + MovieDatabase.getTitle(r.getItem()) + "\n\t"
                               + MovieDatabase.getDirector(r.getItem()));
                               break;
        }
    }
	
	
	
	
	
	
}
