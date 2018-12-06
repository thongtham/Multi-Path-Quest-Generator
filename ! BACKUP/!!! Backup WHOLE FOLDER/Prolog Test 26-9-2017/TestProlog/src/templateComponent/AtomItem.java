package templateComponent;

public class AtomItem extends AtomParent{
	final String type = "Item";
	
	
	
	public AtomItem() {
	}
	
	@Override
	public String getAtomType() {
		return type;
	}
}
