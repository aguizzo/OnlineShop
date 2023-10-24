package onlineshop.helpdesk.enteties.impl;

import onlineshop.helpdesk.enteties.Priority;
import onlineshop.helpdesk.enteties.RequestType;
import onlineshop.helpdesk.enteties.SupportTicket;

public class DefaultSupportTicket implements SupportTicket {
	
	private static int nextSequentialNumber = 1;
	
	private int sequentialNumber;
	private RequestType requestType;
	
	{
		sequentialNumber = nextSequentialNumber++;
	}
	
	public DefaultSupportTicket() {
		// Default empty constructor
	}
	
	public DefaultSupportTicket(RequestType requestType) {
		this.requestType = requestType;
	}

	@Override
	public Priority getPriority() {
		if (requestType == null) {
			return null;
		}
		return requestType.getPriority();
	}

	@Override
	public int getSequentialNumber() {
		return sequentialNumber;
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}

}
