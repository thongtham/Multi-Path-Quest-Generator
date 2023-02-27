package testing;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class test_copy_folder {

	
	public static void main (String  args[]) 
	{
		
		String source = "c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog";
		File srcDir = new File(source);

		String destination = "c:/Users/user/Desktop/Prolog Test/AutoRecord";
		destination += "/Q";
		destination += 1;
		File destDir = new File(destination);

		try {
		    FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e) {
		    e.printStackTrace();
		}	
		
	}
	
}
