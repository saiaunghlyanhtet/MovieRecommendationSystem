package mainsrc;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
	
	
	
	public double getAverageById(String movieId, int minimalRaters ) {
		int n = 0;
		double rating = 0.0;
		for(Rater r : RaterDatabase.getRaters()) {
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
	
	private double dotProduct(Rater me, Rater r) {
		double product = 0;
		ArrayList<String> rIDs = r.getItemsRated();
		ArrayList<String> meIDs = me.getItemsRated();
		for(String meID : meIDs) {
			if(rIDs.contains(meID)) {
				product += (me.getRating(meID)-5) * (r.getRating(meID)-5);
			}
		}
		return product;
	}
	
	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> similar = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : raters) {
            String raterID = r.getID();
            if(!raterID.equals(id)) {
                double product = dotProduct(me, r);
                if(product >= 0) {
                    Rating rating = new Rating(raterID, product);
                    similar.add(rating);
                }
            }
        }
        Collections.sort(similar, Collections.reverseOrder());
        return similar;
	}
	
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> ratingmw = new ArrayList<Rating>();
		ArrayList<Rating> list = getSimilarities(id);
		ArrayList<String> movie = MovieDatabase.filterBy(new TrueFilter());
		for (String mID : movie) {
			double rating = 0.0;
			int n = 0;
			for(int k = 0; k < numSimilarRaters;k++) {
				Rating r = list.get(k);
				String rID = r.getItem();
				double weightedRating = r.getValue();
				double sortedRating = r.getValue();
				try {
					sortedRating = RaterDatabase.getRater(rID).getRating(mID);
				}catch(NullPointerException e) {
					continue;
				}
				rating += weightedRating * sortedRating;
				n++;
			}
			if(n >= minimalRaters) ratingmw.add(new Rating(mID, (rating/n)));
		}
		
		Collections.sort(ratingmw, Collections.reverseOrder());
		return ratingmw;
	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratingmw = new ArrayList<Rating>();
		ArrayList<Rating> list = getSimilarities(id);
		ArrayList<String> movie = MovieDatabase.filterBy(filterCriteria);
		for (String mID : movie) {
			double rating = 0.0;
			int n = 0;
			for(int k = 0; k < numSimilarRaters;k++) {
				Rating r = list.get(k);
				String rID = r.getItem();
				double weightedRating = r.getValue();
				double sortedRating = r.getValue();
				try {
					sortedRating = RaterDatabase.getRater(rID).getRating(mID);
				}catch(NullPointerException e) {
					continue;
				}
				rating += weightedRating * sortedRating;
				n++;
			}
			if(n >= minimalRaters) ratingmw.add(new Rating(mID, (rating/n)));
		}
		
		Collections.sort(ratingmw, Collections.reverseOrder());
		return ratingmw;
	}

}
