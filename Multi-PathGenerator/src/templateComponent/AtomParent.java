package templateComponent;

// This will be extended to AtomAction and AtomItem.
// This is so that the list of Atoms in QuestFrame can contain both 
// Action and Item.
// This should help generalizes the smallest part of the generated quest
// so that the quest can be breakdown to just 'atom'.

// AtomAction = gamestate that must be met in order to advance the quest.
// AtomItem = item/object/etc. related to the Action
//            such as [Item to be delivered],[NPC to talk to],[etc.] .

public class AtomParent {
	String type = "Parent, U are not suppose to use this Atom type";
	
	
	
	public String getAtomType() {
		return type;
	}
}
