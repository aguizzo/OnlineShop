package onlineshop.helpdesk.facades.impl;

import java.util.PriorityQueue;
import java.util.Queue;

import onlineshop.helpdesk.enteties.SupportTicket;
import onlineshop.helpdesk.facades.HelpDeskFacade;
import onlineshop.helpdesk.utils.CustomSupportTicketsComparator;

public class DefaultHelpDeskFacade implements HelpDeskFacade{
	
	private Queue<SupportTicket> tickets = new PriorityQueue<SupportTicket>(new CustomSupportTicketsComparator());
	
	@Override
	public void addNewSupportTicket(SupportTicket supportTicket) {
		if (supportTicket == null)
			return;
		tickets.offer(supportTicket);
		
	}

	@Override
	public SupportTicket getNextSupportTicket() {
		return tickets.poll();
	}

	@Override
	public int getNumberOfTickets() {
		return tickets.size();
	}

}
