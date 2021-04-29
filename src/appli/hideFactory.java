package appli;

public class hideFactory {
	
	public static Hide getInstance(String type) {
		if(type == "sansNom") {
			return new hideNom();
		}
		
		else if(type == "sansPlanning") {
			return new hideDuree();
		}
		
		else if(type == "sansId") {
			return new hideId();
		}
		
		return null;
	}

}
