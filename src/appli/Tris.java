package appli;

import java.util.List;

/**
 * 
 * @author naiya
 *
 *Interface suivant la logique du DP Strategy
 *On peut trier les fichiers de différentes façons
 *On isole différents mode de tri afin que chaque modèle puisse évoluer si besoin
 */
public interface Tris {
	
	public  void sort(List<People> list);

}
