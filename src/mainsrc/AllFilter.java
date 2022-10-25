package mainsrc;
import java.util.*;

public class AllFilter implements Filter {
	ArrayList<Filter> filters;
	
	public AllFilter() {
		filters = new ArrayList<Filter>();
		
	}
	
	public void addFilter(Filter f) {
		filters.add(f);
	}

	@Override
	public boolean satisfies(String id) {
		for (Filter f : filters) {
			if(!f.satisfies(id)) {
				return false;
			}
		}
		return true;
	}
}
