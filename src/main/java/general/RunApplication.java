package general;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import comparators.FileCreationDateComparator;
import comparators.FileNameComparator;
import helper.FileHelper;
import helper.SearchHelper;
import helper.SortingHelper;

public class RunApplication {
	
	private static Scanner scan = null;
	private static int choice;
	private final static FileHelper fileHelper = new FileHelper();
	private static SortingHelper sortingHelper = null;
	private final static FileNameComparator filenameComp = new FileNameComparator();
	private final static FileCreationDateComparator creationdateComp = new FileCreationDateComparator();
	private static String pathAsString;
	private static CustomFile[] customFiles;
	private static String fileToSearch;
	private static String fileToAdd;
	
	private static String[] arrayNamesOfOrderedFiles;
	private static int indexOfFoundFile;

	public static void main(String[] args) throws IOException {
		
		boolean exitProgram = false;
		
		System.out.println("(Important: this application uses "+System.getProperty("file.separator")+" as file separator.)");
		System.out.println("(Make sure that you use the same symbol when working with paths. For example: C:"+System.getProperty("file.separator")+"Users"+System.getProperty("file.separator")+"chgix)");
		System.out.println();
		
		System.out.println("*** Hello. Choose one of the following actions to proceed by typing the number. ***");
		System.out.println();
		while(!exitProgram) {
			
			System.out.println("*** 1 - Choose a directory     99 - exit program ***");
			
			try {
				scan = new Scanner(System.in);
				choice = scan.nextInt();
				
				System.out.println();
				
				switch (choice) { //choices: 1 - Choose a directory     99 - exit program
					case 1:
						System.out.println("*** please insert the absolute path of the directory ***");
						scan = new Scanner(System.in);
						pathAsString = scan.nextLine(); //use nextLine() because files and folders can contain whitespaces
						File file = new File(pathAsString);
						System.out.println();
						
						if(!file.exists()) {
							System.out.println("### This path does not exist, please try again :-( ###");
							System.out.println();
						}else if(!file.isDirectory()){
							System.out.println("### This path is not a directory, please try again :-( ###");
							System.out.println();
						}else {
							System.out.println("### Your path "+pathAsString+" is valid and a directory :-) . How do you want to proceed? ###");
							//get all files of folder indicated by user
							customFiles = fileHelper.getFilesOfFolder(file);
							System.out.println();
							System.out.println("*** 1 - List all files of current directory     2 - Search a file     3 - Add a new file     4 - Delete existing file     10 - go to start of program     99 - exit program ***");
							scan = new Scanner(System.in);
							choice = scan.nextInt();
							System.out.println();
							
							switch(choice) { //choices: 1 - List all files of current directory     2 - Search a file     3 - Add a new file     4 - Delete existing file     10 - go to start of program     99 - exit program
								case 1:
									System.out.println("*** How should the files be ordered? ***");
									System.out.println();
									System.out.println("*** 1 - by name (descending)   2 - by name (ascending)    3 - by creation date (old to new)   4 - by creation date (new to old)***");
									System.out.println();
									scan = new Scanner(System.in);
									choice = scan.nextInt();
									System.out.println();
									
									switch(choice) { //choices: 1 - by name (descending)   2 - by name (ascending)    3 - by creation date (descending)   4 - by creation date (ascending)
										case 1:
											System.out.println("### Files will be ordered by name (descending) ###");
											System.out.println();
											sortingHelper = new SortingHelper(customFiles);
											sortingHelper.sort(0, customFiles.length-1, filenameComp);
											sortingHelper.printArray();
											break;
										case 2:
											System.out.println("### Files will be ordered by name (ascending) ###");
											System.out.println();
											sortingHelper = new SortingHelper(customFiles);
											sortingHelper.sort(0, customFiles.length-1, filenameComp);
											sortingHelper.reverseOrderOfArray();
											sortingHelper.printArray();
											break;
										case 3:
											System.out.println("### Files will be ordered by creation date (old to new) ###");
											System.out.println();
											sortingHelper = new SortingHelper(customFiles);
											sortingHelper.sort(0, customFiles.length-1, creationdateComp);
											sortingHelper.printArray();
											break;
										case 4:
											System.out.println("### Files will be ordered by creation date (new to old) ###");
											System.out.println();
											sortingHelper = new SortingHelper(customFiles);
											sortingHelper.sort(0, customFiles.length-1, creationdateComp);
											sortingHelper.reverseOrderOfArray();
											sortingHelper.printArray();
											break;
										default:
											printDefaultCase();
											break;
									}
									
									break;							
								case 2:
									System.out.println("*** Type in the name of the file which you want to find (please insert full name with file type, e.g. 'test.txt') ***");
									System.out.println();
									scan = new Scanner(System.in);
									fileToSearch = scan.nextLine();

									sortingHelper = new SortingHelper(customFiles);
									//sort the files in the folder (descending, by name)
									sortingHelper.sort(0, customFiles.length-1, filenameComp);
									
									//create String array for the binary search
									arrayNamesOfOrderedFiles = new String[sortingHelper.getArrayWithFiles().length];
									
									for(int i = 0; i<arrayNamesOfOrderedFiles.length; i++) {
										arrayNamesOfOrderedFiles[i] = sortingHelper.getArrayWithFiles()[i].getFileName();
									}
									
									indexOfFoundFile = SearchHelper.binarySearch(arrayNamesOfOrderedFiles, 0, arrayNamesOfOrderedFiles.length-1, fileToSearch);
									
									System.out.println();
									System.out.println("### Files will be printed ordered by name (descending) ###");
									System.out.println();
									
									if(indexOfFoundFile<0) {
										
										for(String fileName: arrayNamesOfOrderedFiles) {
											System.out.println(fileName);
										}
										
										System.out.println();
										System.out.println("### File was not found :-( ###");
										System.out.println();
									}else {
										
										for(int i = 0; i<arrayNamesOfOrderedFiles.length; i++) {
											if(i == indexOfFoundFile) {
												System.out.println("### here is your file -----------> "+arrayNamesOfOrderedFiles[i]+" ###");
											}else {
												System.out.println(arrayNamesOfOrderedFiles[i]);
											}
										}
										
										System.out.println();
										System.out.println("### File was found on "+(indexOfFoundFile+1)+". place in the list :-) ###");
										System.out.println();
									}
									
									break;
								case 3:
									System.out.println("*** Type in the name of the file which you want to add (please insert full name with file type, e.g. 'test.txt') ***");
									System.out.println();
									scan = new Scanner(System.in);
									fileToAdd = scan.nextLine();
									
									File newFile = new File(pathAsString+"\\"+fileToAdd);
									
									if(newFile.createNewFile()) {
										System.out.println();
										System.out.println("### File created ###");
										System.out.println();
									}else {
										System.out.println();
										System.out.println("### This file already exists. ###");
										System.out.println();
									}
									
									break;
								case 4:
									System.out.println("*** Type in the name of the file which you want to delete (please insert full name with file type, e.g. 'test.txt') ***");
									System.out.println();
									scan = new Scanner(System.in);
									fileToSearch = scan.nextLine();

									sortingHelper = new SortingHelper(customFiles);
									//sort the files in the folder (descending, by name)
									sortingHelper.sort(0, customFiles.length-1, filenameComp);
									
									//create String array for the binary search
									arrayNamesOfOrderedFiles = new String[sortingHelper.getArrayWithFiles().length];
									
									for(int i = 0; i<arrayNamesOfOrderedFiles.length; i++) {
										arrayNamesOfOrderedFiles[i] = sortingHelper.getArrayWithFiles()[i].getFileName();
									}
									
									indexOfFoundFile = SearchHelper.binarySearch(arrayNamesOfOrderedFiles, 0, arrayNamesOfOrderedFiles.length-1, fileToSearch);
									
									System.out.println();
									System.out.println("### Files will be printed ordered by name (descending) ###");
									System.out.println();
									
									if(indexOfFoundFile<0) {
										
										for(String fileName: arrayNamesOfOrderedFiles) {
											System.out.println(fileName);
										}
										
										System.out.println();
										System.out.println("### File was not found :-( ###");
										System.out.println();
									}else {
										
										for(int i = 0; i<arrayNamesOfOrderedFiles.length; i++) {
											if(i == indexOfFoundFile) {
												System.out.println("### here is your file -----------> "+arrayNamesOfOrderedFiles[i]+" ###");
											}else {
												System.out.println(arrayNamesOfOrderedFiles[i]);
											}
										}
										
										System.out.println();
										System.out.println("### File was found on "+(indexOfFoundFile+1)+". place in the list. This file will be deleted ###");
										System.out.println();
										
										File fileToDelete = new File(pathAsString+"\\"+fileToSearch);
										
										fileToDelete.delete();
									}
									break;
								case 10:
									printGoBackToMainMenu();
									break;
								case 99:
									exitProgram = true;
									break;
								default:
									printDefaultCase();
									break;
							}
						}
						
						break; //break for first 'case 1'
					case 99:
						exitProgram = true;
						break;
					default: 
						printDefaultCase();
						break;
					}
				
			}catch(InputMismatchException e) {
				System.out.println("### your input was not valid, please try again :-( ###");
				System.out.println();

				//after the exception is caught, the wrong input stays in the buffer of scanner object
				//we have to consume the invalid input. If not, the exception will be thrown with infinite loop
				@SuppressWarnings("unused")
				String consumer = scan.nextLine();
				//create fresh Scanner object
				scan = new Scanner(System.in);
			}
			
		}
		
		scan.close();
		
		System.out.println();
		System.out.println("### Thanks for using this program. See you! ###");

	}
	
	//default case is printed when the user chooses a number that is not in the menu
	public static void printDefaultCase() {
		System.out.println("### your input was not valid, please try again :-( ###");
		System.out.println();
	}
	
	public static void printGoBackToMainMenu() {
		System.out.println("### You will be redirected to start menu ###");
		System.out.println();
	}
}
