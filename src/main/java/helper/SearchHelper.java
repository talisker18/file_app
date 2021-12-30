package helper;

/*
 * this class is used to find a file in a list of files
 * the search is done by file name
 * 
 * */
public final class SearchHelper {
	
	private SearchHelper() {
		//prevent instantiation
	}
	
	public static int binarySearch(String[] arr, int l, int r, String fileToSearch){
		
        if (r >= l) {
            int mid = l + (r - l) / 2;
 
            // If the element is present at the
            // middle itself
            if (arr[mid].equals(fileToSearch)) { //here we use case sensitivity to check
            	return mid;
            }
 
            // If element is smaller than mid, then
            // it can only be present in left subarray              
            if (fileToSearch.compareToIgnoreCase(arr[mid]) < 0) { //important: here we have to use compareToIgnoreCase
            	return binarySearch(arr, l, mid - 1, fileToSearch);
            }
                
            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, r, fileToSearch);
        }
 
        // We reach here when element is not present
        // in array
        return -1;
    }

}
