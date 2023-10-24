package onlineshop.helpdesk.facades;

import onlineshop.helpdesk.enteties.SupportTicket;

public interface HelpDeskFacade {
	
	void addNewSupportTicket(SupportTicket supportTicket);
	
	SupportTicket getNextSupportTicket();
	
	/**
	 * @return amount of tickets that are no processed
	 */
	int getNumberOfTickets();
	
}
