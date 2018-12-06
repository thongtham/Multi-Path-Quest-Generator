package testing;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.pengyifan.commons.collections.tree.TreeNode;

public class testReverse {

	public static void main (String arg[]) {
		
		List<Integer> questTreeLeaves = new ArrayList<Integer>();
		List<Integer> questTreeLeavesReverse = new ArrayList<Integer>();
		
		questTreeLeaves.add(1);
		questTreeLeaves.add(2);
		questTreeLeaves.add(3);
		questTreeLeaves.add(4);
		questTreeLeaves.add(5);
		questTreeLeaves.add(6);
		questTreeLeaves.add(7);
		questTreeLeaves.add(8);
		
		ListIterator<Integer> itrTreeNode = questTreeLeaves.listIterator(questTreeLeaves.size());
		
		while (itrTreeNode.hasPrevious()) {
			questTreeLeavesReverse.add(itrTreeNode.previous());
		}
		
		System.out.println(questTreeLeavesReverse);
		
	}
}
