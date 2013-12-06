package br.com.longcircuit.quest;

public enum QuestEventType {

	TALK_PERSON(0),
	FIND_PERSON(1),
	FIND_PLACE(2),
	FIX_ITEN(3),
	COLLECT_ITEN(4),	
	FIND_MONSTER(5),
	KILL_MONSTER(6);

	private final int code;

	QuestEventType(int code){
		this.code = code;
	}

	public final int getCode(){ 
		return code; 
	}

}