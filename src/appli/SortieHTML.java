package appli;


import java.util.Collection;

public class SortieHTML implements Sortie {
	
	 private Collection<People> _allpeople = null;
	    private String _fileName;
	    private String _startTime;
	    private String _endTime;

		private String _libelle;
	    
	@Override
	public String sortie(Collection<People> _allpeople, String _fileName, String _startTime, String _endTime, String lib) {
		
		this._allpeople=_allpeople;
		this._startTime=_startTime;
		this._endTime=_endTime;
		this._fileName=_fileName;
		this._libelle = lib;
		
		 String html = "<!DOCTYPE html> \n <html lang=\"fr\"> \n <head> \n <meta charset=\"utf-8\"> ";
	        html += "<title> Attendance Report </title> \n <link rel=\"stylesheet\" media=\"all\" href=\"visu.css\"> \n";
	        html += "</head> \n <body> \n ";
	        html += "<h1> Rapport de connexion </h1>\n" +
	                "\n" +
	                "<div id=\"blockid\">\n" +
	                "<table>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Date : </th>\n" +
	                "\t\t<td> " + /*this._allpeople.iterator().next().getDate() +*/ " </td>\n" +
	                "\t</tr>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Heure début : </th>\n" +
	                "\t\t<td> " + this._startTime + " </td>\n" +
	                "\t</tr>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Heure fin : </th>\n" +
	                "\t\t<td> " + this._endTime + " </td>\n" +
	                "\t</tr>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Cours : </th>\n" +
	                "\t\t<td> " + this._libelle + " </td>\n" +
	                "\t</tr>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Fichier analysé : </th>\n" +
	                "\t\t<td> " + this._fileName + " </td>\n" +
	                "\t</tr>\n" +
	                "\t<tr>\n" +
	                "\t\t<th> Nombre de connectés : </th>\n" +
	                "\t\t<td> " + this._allpeople.size() + "  </td>\n" +
	                "\t</tr>\n" +
	                "</table>\n" +
	                "</div>\n" +
	                "\n" +
	                "<h2> Durées de connexion</h2>\n" +
	                "\n" +
	                "<p> Pour chaque personne ci-dessous, on retrouve son temps total de connexion sur la plage déclarée du cours, ainsi qu'un graphe qui indique les périodes de connexion (en vert) et d'absence de connexion (en blanc). En pointant la souris sur une zone, une bulle affiche les instants de début et de fin de période. \n" +
	                "</p>";
	        html += "<div id=\"blockpeople\"> ";

	        for (People people : this._allpeople) {

	            html += people.getHTMLCode();
	        }

		    html += "</div> \n </body> \n </html>";
	        return html;
	    }
	
	

}
