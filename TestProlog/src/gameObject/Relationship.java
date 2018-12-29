package gameObject;

public class Relationship {
	
	//[Friend:player:jack]
			
	String type;
	String mainCharacter;
	String objectCharacter;
	
	public Relationship(String mainChar, String objectChar, String typeNew) {
		type = typeNew;
		mainCharacter = mainChar;
		objectCharacter = objectChar;
	}
	
	public Relationship() {
		
	}
	
	public void setRelationship(String mainChar, String objectChar, String typeNew) 
	{	
		type = typeNew;
		mainCharacter = mainChar;
		objectCharacter = objectChar;
	}
		
	public void setType(String typeNew) {
		type = typeNew;
	}
	
	public void setMainChar(String mainChar) {
		mainCharacter = mainChar;
	}
	
	public void setObject(String objectChar) {
		objectCharacter = objectChar;
	}
	
	//relationship(jack,john,friend)
	public String getRelationship() {
		String output = "relationship(";
		output += mainCharacter;
		output += ",";
		output += objectCharacter;
		output += ",";
		output += type;
		output += ")";
		return output;
	}
	
	
	
	
	
	//-------------------- ToString()---------------------------
	
	public String toStringPrologFormat()
	{
		String output = "";
		output += "[";
		output += type;
		output += ",";
		output += mainCharacter;
		output += ",";
		output += objectCharacter;
		output += "]";
		
		return output;
	}
	
	//[Friend:player:jack]
	
	
	
	
	
	
	//--------------------Override---------------------------
	
	@Override
	public String toString() {
		String output = "relationship(";
		output += type;
		output += ",";
		output += mainCharacter;
		output += ",";
		output += objectCharacter;
		output += ")";
		return output;
	}
	
	
}
