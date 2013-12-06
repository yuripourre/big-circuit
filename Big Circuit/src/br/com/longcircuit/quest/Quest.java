package br.com.longcircuit.quest;

import java.util.ArrayList;
import java.util.List;

public class Quest {

	private String name;
	
	private List<QuestEvent> events = new ArrayList<QuestEvent>();
	
	private boolean completed = false;
		
	public Quest(String name){
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<QuestEvent> getEvents() {
		return events;
	}

	public void setEvents(List<QuestEvent> events) {
		this.events = events;
	}
	
	public void addQuestEvent(QuestEvent event){
		this.events.add(event);
	}
	
	public QuestEvent getCurrentEvent(){
		
		return	events.get(events.size()-1);
		 
	}
		
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void eventConcluded(){
		this.events.remove(0);
		
		if(events.size()==0){
			System.err.println("Quest Completed!");
			completed = true;
		}		
		
	}
	
}
