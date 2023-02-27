package testing;

import java.io.File;
import java.util.ArrayList;

public class testDeleteFolder {

	public static void main (String[] args) {
		
		String deleteDirectory = "C:/Users/user/Desktop/Prolog Test/TextPrintFromProlog";
		deleteDirectory(new File(deleteDirectory));
		
		
		
	}

	
	
    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDirectory(children[i]);
                if (!success) {
                    return false;
                }
            }
        }

        // either file or an empty directory
        System.out.println("removing file or directory : " + dir.getName());
        return dir.delete();
    }

}

