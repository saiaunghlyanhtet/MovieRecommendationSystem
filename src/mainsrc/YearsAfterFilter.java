package mainsrc;

public class YearsAfterFilter implements Filter {
	private int myYear;
	public YearsAfterFilter(int year) {
		myYear = year;
	}
	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getYear(id) >= myYear;
	}
	
	
}
