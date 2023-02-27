package testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.apache.commons.io.FileUtils;

public class test_write_to_text {

	
	public static void main(String[] args) {
		
		String outputToText = "test1";
		int numberOfAutoRecord = 1;
		
		// Automatically record the generated quest and loop for next quest

		String source = "c:/Users/user/Desktop/Prolog Test/TextPrintFromProlog";
		File srcDir = new File(source);

		String destination = "c:/Users/user/Desktop/Prolog Test/AutoRecord";
		destination += "/";
		destination += 99;
		destination += "/Q";
		destination += numberOfAutoRecord;
		File destDir = new File(destination);

		try {
		    FileUtils.copyDirectory(srcDir, destDir);
		    
		    String destinationText = destination;
		    destinationText += "/Q";
		    destinationText += numberOfAutoRecord;
		    destinationText += "summary.txt";
		    
		    System.out.println(destination);
		    System.out.println(destinationText);
		    
			BufferedWriter writerBW = new BufferedWriter(new FileWriter(destinationText));
			writerBW.write(outputToText);
			writerBW.close();
			
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		//If less than desired number of quest, generate more by looping back to top
	
		// END OF MAIN WHILE LOOP
	}
		
		
}

