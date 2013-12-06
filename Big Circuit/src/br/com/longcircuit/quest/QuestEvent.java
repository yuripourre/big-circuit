package br.com.longcircuit.quest;

public class QuestEvent {

	private QuestEventType type;
	private int code;
	
	public QuestEvent(QuestEventType type, int code){
		super();
		this.type = type;
		this.code = code;
	}

	public QuestEventType getType() {
		return type;
	}

	public void setType(QuestEventType type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
		
}
