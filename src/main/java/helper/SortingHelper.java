package helper;

import java.util.Comparator;

import general.CustomFile;

public class SortingHelper {

	private CustomFile [] arrayWithFiles;
	
	public SortingHelper(CustomFile [] arrayWithFiles) {
		this.arrayWithFiles = arrayWithFiles;
	}
	
	private void merge(int l, int m, int r, Comparator<CustomFile> comparator){
        
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        CustomFile L[] = new CustomFile [n1];
        CustomFile R[] = new CustomFile [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) {
        	L[i] = this.arrayWithFiles[l + i];
        }
           
        for (int j=0; j<n2; ++j) {
        	R[j] = this.arrayWithFiles[m + 1+ j];
        }

        int i = 0, j = 0;

        int k = l;
        
        while (i < n1 && j < n2){
        	
        	if (comparator.compare(L[i], R[j]) <= comparator.compare(R[j], L[i])){
            	this.arrayWithFiles[k] = L[i];
                i++;
            }else{
            	this.arrayWithFiles[k] = R[j];
                j++;
            }
            
            k++;
        }
        
        while (i < n1){
        	this.arrayWithFiles[k] = L[i];
            i++;
            k++;
        }

        
        while (j < n2){
        	this.arrayWithFiles[k] = R[j];
            j++;
            k++;
        }
    }
	
	public void sort(int l, int r, Comparator<CustomFile> comparator){
        if (l < r){
            
            int m = (l+r)/2;

            sort(l, m, comparator);
            
            sort(m+1, r, comparator);

            merge(l, m, r, comparator);
        }
    }

    //call this method for order a list descending
    public void reverseOrderOfArray() {

    	CustomFile[] copy = new CustomFile[this.arrayWithFiles.length];
    	
        int j = this.arrayWithFiles.length;
        for (int i = 0; i < this.arrayWithFiles.length; i++) {
            copy[j - 1] = this.arrayWithFiles[i];
            j = j - 1;
        }
        
        this.arrayWithFiles = copy;
    }
    
    public void printArray(){
    	
        int n = this.arrayWithFiles.length;
        for (int i=0; i<n; ++i) {
        	System.out.println(this.arrayWithFiles[i].getFileName()+", created on: "+this.arrayWithFiles[i].getFileDate());
        }

        System.out.println();
    }
    
    public CustomFile [] getArrayWithFiles() {
    	return this.arrayWithFiles;
    }
}
