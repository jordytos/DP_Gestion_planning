package appli;

import java.io.File;
import java.io.IOException;

/**
 * 
 * @author naiya
 *Class reliée à la creation du factory
 *
 */
public abstract class Hide {

	public abstract void hideCSS(File file) throws IOException;
	
	public abstract void setVariable(int lineNumber, String data, String filepath) throws IOException;
	
}
