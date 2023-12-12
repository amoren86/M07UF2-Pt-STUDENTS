package cat.institutmarianao.controller;

import org.springframework.web.servlet.ModelAndView;

//TODO - Configure Spring element and add mappings
public class AdminController {

	public ModelAndView orders() {

		// TODO - get all user orders
		// TODO - Prepare the orders.jsp view and send user orders and Order.STATES as
		// STATES
		// TODO - parameter
		return null;
	}

	public String setDeliveryDate(/* TODO - Get reference parameter */
	/* TODO - Get deliveryDate parameter */) {

		// TODO - Get the order related to the reference passed as parameter
		// TODO - Set the order delivery date with the deliveryDate value
		// TODO - Update the order
		return "redirect:/admin/orders";
	}

	public String setState(/* TODO - Get reference parameter */
	/* TODO - Get state parameter */) {

		// TODO - Get the order related to the reference passed as parameter
		// TODO - Set the order state with the state value
		// TODO - Update the order
		return "redirect:/admin/orders";
	}
}
