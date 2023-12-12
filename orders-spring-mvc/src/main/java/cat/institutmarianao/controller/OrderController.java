package cat.institutmarianao.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import jakarta.validation.Valid;

//TODO - Configure Spring element and add mappings
public class OrderController {

	@ModelAttribute("order")
	public Order setupOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User client = (User) authentication.getPrincipal();

		Order order = new Order();
		order.setClient(client);
		return order;
	}

	public ModelAndView orders() {
		// TODO - get authenticated user here
		// TODO - get user orders
		// TODO - Prepare the orders.jsp view and send user orders and Order.STATES as
		// parameter
		return null;
	}

	public ModelAndView newOrder() {
		// TODO - Prepare the newOrder.jsp view and send all the available items
		// TODO - The new user order is in session
		return null;
	}

	public String newOrderClearItems(@SessionAttribute("order") Order order) {

		order.getItems().clear();

		return "redirect:/users/orders/newOrder";
	}

	public String newOrderIncreaseItem(@SessionAttribute("order") Order order
	/* TODO - Get the reference parameter */) {

		// TODO - Get the item related to the reference passed as parameter
		// TODO - Increase item quantity
		return "redirect:/users/orders/newOrder";
	}

	public String newOrderDecreaseItem(@SessionAttribute("order") Order order
	/* TODO - Get the reference parameter */) {

		// TODO - Get the item related to the reference passed as parameter
		// TODO - Decrease item quantity

		return "redirect:/users/orders/newOrder";
	}

	public String finishOrder() {
		// Nothing to do. We have order attibute in session, so the view can take it
		// from there
		return "finishOrder";
	}

	public String finishOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
			SessionStatus sessionStatus) {

		if (bindingResult.hasErrors()) {
			return "finishOrder";
		}

		// TODO - Set order start date to current date
		// TODO - Save order
		sessionStatus.setComplete(); // Clean session attributes - leave a new order ready in session

		sessionStatus.setComplete();
		return "redirect:/users/orders";
	}
}
