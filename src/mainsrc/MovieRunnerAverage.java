package mainsrc;
import java.util.*;
import edu.duke.*;
public class MovieRunnerAverage {
	public void printAverageRatings() {
		SecondRatings secondRatings = new SecondRatings();
		System.out.println("No of movies" + secondRatings.getMovieSize());
		System.out.println("No of raters" + secondRatings.getRaterSize());
		
		
	}
	
	public void getAverageRatingOneMovie() {
		SecondRatings sr = new SecondRatings();
		String mov = "Vacation";
        String id = sr.getID(mov);
        System.out.println(sr.getAverageById(id, 3) + "\t" + mov);
	}
 }
