package testing;

import com.pengyifan.commons.collections.tree.TreeNode;

public class test {

	public static void main (String[] args)
	{

		label1:
		    for (; ; ) {
		        
		    	System.out.println("label2 reach");
		    	label2:
		        for (; ; ) {
		        	System.out.println("inside reach");
		            if (true) {
		                // break outer loop
		                continue label1;
		            }
		            if (false) {
		                // break inner loop
		                break label2;
		            }
		            if (false) {
		                // break inner loop
		                break;
		            }
		        }
		    }

		
	}
		
		
		
		
}
