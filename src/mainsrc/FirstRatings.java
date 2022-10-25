package mainsrc;

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
	ArrayList<Movie> loadMovies (String filename) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		FileResource file = new FileResource(filename);
		CSVParser parser = file.getCSVParser();
		for(CSVRecord rec : parser) {
            Movie m = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"),
                                rec.get("director"),rec.get("country"),rec.get("poster"),
                                Integer.parseInt(rec.get("minutes")));
            movieList.add(m);
        }
		return movieList;
	}
	
	public void testLoadMovies() {
		//ArrayList<Movie> movieList = loadMovies("ratedmovies_short.csv");
		ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
		System.out.println("# movies: " + movieList.size());
		System.out.println(movieList.get(1));
		
		// Comedy Calling
		int comedyG = 0;
		int greaterThan150 = 0;
		HashMap<String, Integer> dirmov = new HashMap<String, Integer>();
		for(Movie m : movieList) {
			if(m.getGenres().contains("Comedy")) {
				comedyG++;
			};
			if(m.getMinutes() > 150) {
				greaterThan150++;
			}
			
			String director = m.getDirector();
            if(! dirmov.containsKey(director) ) {
                dirmov.put(director,1);
            }
            else {
                int n = dirmov.get(director);
                dirmov.put(director,n+1);
            }
            
            
//            System.out.println("# Comedy: " + comedyG);
//            System.out.println("# >150 min. " + greaterThan150);
            
		}
		System.out.println("# Comedy: " + comedyG);
        System.out.println("# >150 min. " + greaterThan150);
        
        int maxMov = 0;
        for (String director: dirmov.keySet()) {
        	if (dirmov.get(director) > maxMov) {
        		maxMov = dirmov.get(director);
        	}
        }
        System.out.println("Maximum movies of a single director: " + maxMov);
        
        int maxMovDir = 0;
        for (String director: dirmov.keySet()) {
        	if (dirmov.get(director) == maxMov) {
        		maxMovDir++;
        	}
        }
        
        System.out.println("Directors having max. movies are " + maxMovDir + " and are:");
        
        for(String director : dirmov.keySet()) {
            if( dirmov.get(director) == maxMov ) System.out.println(director);
        }
	
	}
	
	//LoadRater Function
	ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> rateList = new ArrayList<Rater>();
		FileResource file = new FileResource(filename);
        CSVParser parser = file.getCSVParser();
        for (CSVRecord record : parser) {
        	String raterId = record.get("rater_id");
        	boolean set = false;
        	for(int i = 0; i < rateList.size(); i++) {
        		if (rateList.get(i).getID().equals(raterId)) {
        			Rater r = rateList.get(i);
        			r.addRating(record.get("movie_id"), Double.parseDouble(raterId));
        			rateList.set(i, r);
        			set = true;
        			break;
        		}
        	}
        	
        	if(!set) {
        		Rater r = new EfficientRater(raterId);
                r.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
                rateList.add(r);
        	}
        }
        return rateList;
	}
	
	//Test LoadRaters
	public void testLoadRaters() {
		//ArrayList<Rater> rateList = loadRaters("ratings_short.csv");
		ArrayList<Rater> rateList = loadRaters("ratings.csv");
		System.out.println("#Raters : " + rateList.size());
		
		int maxRating = 0;
        for(Rater r : rateList) {
//            System.out.println(r.getID() + "\t" + r.getItemsRated().size() );
//            ArrayList<String> rt = r.getItemsRated();
//            for(int i = 0; i < rt.size(); i++) {
//                System.out.println(rt.get(i) + "\t" + r.getRating(rt.get(i)));
//             }
            int id = 193;
            if(r.getID().equals("" + id)) 
                System.out.println("# of ratings by ID " + id + " is " + r.getItemsRated().size());
            if(r.getItemsRated().size() > maxRating) maxRating = r.getItemsRated().size();
        }
        
        System.out.println("Max # ratings: " + maxRating);
        
        
        int maxmovraters = 0;
        for(Rater r : rateList) {
            if(r.getItemsRated().size() == maxRating) {
                maxmovraters++;
                System.out.println("Id of Raters who rated max. movies: " + r.getID());
            }
        }
        
        System.out.println("# having maximum ratings: " + maxmovraters);
		
        String movID = "1798709";
        int nmovr = 0;
        for(Rater r : rateList) {
            if(r.getItemsRated().contains(movID)) nmovr++;
        }
        System.out.println("# ratings for " + movID + " is: " + nmovr);
        ArrayList<String> nmovies = new ArrayList<String>();
        for(Rater r : rateList) {
            for(int i = 0; i < r.getItemsRated().size(); i++)
                if(! nmovies.contains(r.getItemsRated().get(i)) ) 
                    nmovies.add(r.getItemsRated().get(i));
        }
        System.out.println("Diff. movies rated by all raters: " + nmovies.size());
		
		
	}


}
