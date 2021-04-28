package appli;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TrisDuree  implements Tris{

	@Override
	public void sort(List<People> list) {
		
		Collections.sort(list, new Comparator<People>() {

			@Override
			public int compare(People o1, People o2) {
				// TODO Auto-generated method stub
				return (int)(o1.getTotalAttendanceDuration() - o2.getTotalAttendanceDuration());
			}
		
	

		});

}
}