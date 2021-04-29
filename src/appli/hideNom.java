package appli;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class hideNom extends Hide{

	@Override
	public void hideCSS(File file) throws IOException {
		String filepath= "./src/data/visu.css";
		FileInputStream fis = new FileInputStream(filepath);
		InputStreamReader input = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(input);
		String data;
		String result = new String();
		int lineNumber=0;
		int i=0;

		while ((data = br.readLine()) != null) {
			i++;
			if(data.contains("/*Modif1*/")) {
				lineNumber=i;
				break;
			}
			else if(data.contains("/*1*/	display : none;")) {
				lineNumber=i;
				break;
			}
			result = result.concat(data + "\n");
		}
		br.close();
		//lineNumber=lineNumber+1;
		String Mystring ="/*1*/	display : none;";
		String Mystringline = Files.readAllLines(Paths.get(filepath)).get(lineNumber-1); // get method count from 0 so -1
		if(!Mystringline.equalsIgnoreCase(Mystring)) {
			setVariable(lineNumber, Mystring, filepath);
		}else {
			Mystring ="	/*Modif1*/";
			Mystringline = Files.readAllLines(Paths.get(filepath)).get(lineNumber-1);
			setVariable(lineNumber, Mystring, filepath);
		}		
		
	}

	@Override
	public void setVariable(int lineNumber, String data, String filepath) throws IOException {
		Path path = Paths.get(filepath);
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		lines.set(lineNumber - 1, data);
		Files.write(path, lines, StandardCharsets.UTF_8);
		
	}

}
