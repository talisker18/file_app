package comparators;

import java.util.Comparator;

import general.CustomFile;


public class FileNameComparator implements Comparator<CustomFile>{

	public int compare(CustomFile o1, CustomFile o2) {
		return o1.getFileName().compareToIgnoreCase(o2.getFileName());
	}

}
