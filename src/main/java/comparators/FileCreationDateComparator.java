package comparators;

import java.util.Comparator;

import general.CustomFile;

public class FileCreationDateComparator implements Comparator<CustomFile>{

	public int compare(CustomFile o1, CustomFile o2) {
		
		return Long.compare(
				o1.getFileDate().toMillis(), 
				o2.getFileDate().toMillis()
		);
	}
	
	

}
