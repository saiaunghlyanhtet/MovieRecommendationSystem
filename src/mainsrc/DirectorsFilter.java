package mainsrc;

public class DirectorsFilter implements Filter{
	private String myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors;
	}
	@Override
	public boolean satisfies(String id) {
		String directorArr[] = myDirectors.split(",");
		for (String director : directorArr) {
			if(MovieDatabase.getDirector(id).contains(director)) return true;
		}
		return false;
	}
	
}
