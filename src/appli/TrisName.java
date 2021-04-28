package appli;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrisName implements Tris {

	@Override
	public void sort(List<People> list) {
		
		Collections.sort(list, new Comparator<People>() {

			@Override
			public int compare(People o1, People o2) {
				// TODO Auto-generated method stub
				 return (int)(o1.getName().compareToIgnoreCase(o2.getName()));
			}
		
	

		});

}
}
