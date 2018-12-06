package templateComponent;

import java.util.ArrayList;
import java.util.List;


public class Template {
	
	String templateName;
	
	
	List<Component> listComponent = new ArrayList<Component>();
	
	
	
	
	public Template(){
		
	}
	
	public Template(String name){
		templateName = name;
	}
	
	public Template(Template newTemplate){
		templateName = newTemplate.getName();
		listComponent = newTemplate.getComponent();
		
	}
	
	
	
	public String getName(){
		return templateName;
	}
	
	public List<Component> getComponent(){
		return listComponent;
	}
	
	public void appendTemplate(Template inputTemplate){
		
		List<Component> newListComponent = new ArrayList<Component>(listComponent);
		List<Component> inputListComponent = inputTemplate.getComponent();
		newListComponent.addAll(inputListComponent);
		listComponent = newListComponent;
		
		
	}
	
	
}










