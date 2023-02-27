package testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testFileFolderReader {

	public static void main (String arg[]) throws IOException
	{
	

	File folder = new File("C:/Users/user/Desktop/Text");
	ArrayList<String> fileList = listFilesForFolder(folder);
	System.out.println(fileList.toString());
	// *******
	// This will print -> [testA.txt, testB.txt]	
	// *********
	
	folder = new File("C:/Users/user/Desktop/Text/New folder");
	fileList = listFilesForFolder(folder);
	System.out.println(fileList.toString());
	
	
	ArrayList<String> fileFolder = findFoldersInDirectory("C:/Users/user/Desktop/Text");
	System.out.println(fileFolder.toString());
	

	/*
	File f = new File("C:/Users/user/Desktop/Text");// your folder path
	
	//**Edit** It is array of Strings
	String[] fileList = f.list(); // It gives list of all files in the folder.
	
	for(String str : fileList){
    if(str.endsWith(".txt")){

        // Read the content of file "str" and store it in some variable

         FileReader reader = new FileReader("C:/Users/user/Desktop/Text"+str);
        char[] chars = new char[(int) new File("C:/Users/user/Desktop/Text"+str).length()];
        reader.read(chars);
       String content = new String(chars);
        reader.close(); 

        // now write the content in xml file

         BufferedWriter bw = new BufferedWriter(
         new FileWriter("\"C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog\""+str.replace(".txt",".xml")));
         bw.write(content); //now you can  write that variable in your file.

         bw.close();
         
         */
         
   }
    
    
    public static ArrayList<String> listFilesForFolder(final File folder) {
    	ArrayList<String> returnSTR = new ArrayList<String>();
    	
	    for (final File fileEntry : folder.listFiles()){
	        if (fileEntry.isDirectory()){
	            listFilesForFolder(fileEntry);
	        } 
	        else{
	        	returnSTR.add(fileEntry.getName());
	            //System.out.println(fileEntry.getName());
	        }
	    }
	    return returnSTR;
    }
    
    
    public static ArrayList<String> findFoldersInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
    	
        FileFilter directoryFileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
    		
        File[] directoryListAsFile = directory.listFiles(directoryFileFilter);
        ArrayList<String> foldersInDirectory = new ArrayList<String>(directoryListAsFile.length);
        for (File directoryAsFile : directoryListAsFile) {
            foldersInDirectory.add(directoryAsFile.getName());
        }

        return foldersInDirectory;
    }
    
    
}

