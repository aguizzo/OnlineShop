package onlineshop.helpdesk.enteties;

public enum RequestType {
	
	OTHER(Priority.LOW),
	CHANGE_ACCOUNT_DETAILS(Priority.LOW),
	
	CAN_NOT_LOGIN(Priority.MEDIUM),
	ACCOUNT_IS_BLOCKED(Priority.MEDIUM),
	COOPERATION(Priority.MEDIUM),
	
	ACCOUNT_IS_HACKED(Priority.HIGH),
	CAN_NOT_COMPLETE_PURCHASE(Priority.HIGH),
	ORDER_IS_NOT_RECEIVED(Priority.HIGH);
	
	private Priority priority;
	
	private RequestType(Priority priority) {
		this.priority = priority;
	}
	
	public Priority getPriority() {
		return priority;
	}
}
