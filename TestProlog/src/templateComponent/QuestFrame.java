package templateComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import enviroment.GameState;
import enviroment.GameWorld;
import gameObject.Item;
import gameObject.Character;
import gameObject.Location;
import gameObject.Occupation;
import gameObject.Property;
import gameObject.Race;
import gameObject.Relationship;
import gameObject.Skill;
import gameObject.Status;

import templateComponent.AtomAction;
import templateComponent.AtomItem;
import templateComponent.AtomParent;
import templateComponent.Component;
import templateComponent.QuestFrame;
import templateComponent.Template;


//This class will oversee all templates that are connected to create
//a complete quest. 
//This class will oversee and store the 'rule > atom' process and
//store all of the result in itself.

public class QuestFrame {

	//This use to be  List<Template>, but change to single Template instead
	private Template questFrameTemplate = new Template();
	private ArrayList<GameState> listGameState = new ArrayList<GameState>();
	private ArrayList<Component> listComponent = new ArrayList<Component>();
	
	
	// To be Coded
	//
	//Restriction State
	//Completion State
	//
	
	public QuestFrame()
	{
		
	}
	
	public QuestFrame(Template NewTemplate)
	{
		questFrameTemplate = NewTemplate;
	}
	
	public void setListTemplateEmpty()
	{

	}
	public void setListTemplateBasicKill()
	{
		/*
		Template BasicTemplate = new Template();
		BasicTemplate.setTemplateBasicEndKill();
		questFrameTemplate.add(BasicTemplate);
		System.out.println("setQFBasicKill");
		String result = listTemplate.stream().map(Object::toString).collect(Collectors.joining(", "));
		System.out.println(result);
		*/
		
		
		Template BasicTemplate = new Template();
		BasicTemplate.setTemplateConquest_Attack_Enemy();
		questFrameTemplate = BasicTemplate;

	}
	
	public void setTemplate(Template newTemplate) 
	{
		questFrameTemplate = newTemplate;
	}
	
	public Template getListTemplate()
	{
		return questFrameTemplate;
	}
	
	public void printAllInfo()
	{
		
	}
	
	public String toString()
	{
		System.out.println("QuestFrameToString");
		String strReturn = "";
		String result = questFrameTemplate.toString();
		strReturn += result;
		
		return strReturn;
	}

	
}
