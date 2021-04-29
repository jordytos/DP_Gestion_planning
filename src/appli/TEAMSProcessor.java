package appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TEAMSProcessor {

	private Collection<People> _allpeople = null;
	private String _fileName;
	private String _libelle;
	private String _startTime;
	private String _endTime;
	private Tris _tris;

	public TEAMSProcessor(File _file, String _start, String _stop, Tris _t, String lib) {
		/*
		 * csv file to read start time of the course end time of the source
		 */
		this._startTime = _start;
		this._endTime = _stop;

		// load CSV file
		this._fileName = _file.getName();
		var teamsFile = new TEAMSAttendanceList(_file);

		// Sort people
		this._tris = _t;

		// Lesson Title
		this._libelle = lib;

		// filter to extract data for each people
		var lines = teamsFile.get_attlist();
		if (lines != null) {
			// convert lines in data structure with people & periods
			var filter = new TEAMSAttendanceListAnalyzer(lines);
			// cut periods before start time and after end time
			filter.setStartAndStop(_start, _stop);
			// sort
			Collection<People> peopleByDuration = new ArrayList<>(filter.get_peopleList().values());
			// Collections.sort(peopleByDuration);
			// init the people collection

			this._allpeople = peopleByDuration;// filter.get_peopleList().values();
		}
	}

	public Collection<People> get_allpeople() {
		return _allpeople;
	}

	public void sort() {
		List<People> list = (List<People>) this._allpeople;
		_tris.sort(list);
		this._allpeople = list;
	}

	public void setSorter(Tris tris) {
		this._tris = tris;
	}

	
}
