package appli;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrisId implements Tris{

	@Override
	public void sort(List<People> list) {
		
		Collections.sort(list, new Comparator<People>() {

			@Override
			public int compare(People o1, People o2) {
				// TODO Auto-generated method stub
				  return (int)(o1.get_id().compareToIgnoreCase(o2.get_id()));
			}
		
	

		});

}
	}


