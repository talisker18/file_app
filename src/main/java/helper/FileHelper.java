package helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import general.CustomFile;

public class FileHelper {
	
	public CustomFile[] getFilesOfFolder(final File folder) throws IOException {
		
		List<CustomFile> list = new ArrayList<CustomFile>();
		
	    for (final File fileEntry : folder.listFiles()) {
	    	final CustomFile customeFile = new CustomFile(fileEntry);
            list.add(customeFile);
	    }
	    
	    CustomFile[] customFileArr = new CustomFile[list.size()];
	    
	    for(int i = 0; i<customFileArr.length; i++) {
	    	customFileArr[i] = list.get(i);
	    }
	    
	    return customFileArr;
	}

}
