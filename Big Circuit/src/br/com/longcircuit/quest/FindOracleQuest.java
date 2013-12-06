package br.com.longcircuit.quest;

public class FindOracleQuest extends Quest{

	public FindOracleQuest(){
		super("Find The Oracle");
		addQuestEvent(new QuestEvent(QuestEventType.TALK_PERSON,0));	
	}
		
}
