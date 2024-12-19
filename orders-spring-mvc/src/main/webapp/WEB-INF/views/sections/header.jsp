<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<spring:message var="currencySymbol" code="currency.symbol"
	scope="session" />

<header class="mb-4 p-5 bg-secondary-subtle bg-gradient rounded">
	<p class="display-1">
		<strong><spring:message code="header.title" /></strong>
	</p>
	<h3>
		<spring:message code="header.subtitle" />
	</h3>
</header>

<fmt:setLocale value="${pageContext.response.locale}" />

<nav
	class="navbar navbar-expand-lg sticky-top mb-4 bg-secondary-subtle bg-gradient rounded">
	<div class="container-fluid">
		<ul class="navbar-nav">
			<li class="navbar-brand">
				<sec:authorize access="isAuthenticated()">
					<i class="bi bi-person-fill"></i>
					<span>
						<sec:authentication var="user" property="principal" />
						${user.firstName}
					</span>
				</sec:authorize>
			</li>

			<sec:authorize access="hasAnyRole('USER')">
				<spring:url var="ordersUrl" value="/users/orders" />
				<li class="nav-item">
					<a
						class="nav-link ${requestScope['jakarta.servlet.forward.request_uri'] eq ordersUrl?'active':''}"
						href="${ordersUrl}">
						<i class="bi bi-list-ol"></i>
						<spring:message code="header.navbar.my.orders" />
					</a>
				</li>
				<spring:url var="newOrderUrl" value="/users/orders/newOrder" />
				<li class="nav-item">
					<a
						class="nav-link ${requestScope['jakarta.servlet.forward.request_uri'] eq newOrderUrl?'active':''}"
						href="${newOrderUrl}">
						<i class="bi bi-plus-square-fill"></i>
						<spring:message code="header.navbar.new.order" />
					</a>
				</li>
				<c:if test="${!empty order.items}">
					<spring:url var="finishOrderUrl"
						value="/users/orders/newOrder/finishOrder" />
					<li class="nav-item">
						<a
							class="nav-link ${requestScope['jakarta.servlet.forward.request_uri'] eq finishOrderUrl?'active':''}"
							href="${finishOrderUrl}">
							<i class="bi bi-bag-check-fill"></i>
							<spring:message code="header.navbar.finish.order" />
						</a>
					</li>
				</c:if>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('ADMIN')">
				<spring:url var="ordersUrl" value="/admin/orders" />
				<li class="nav-item">
					<a
						class="nav-link ${requestScope['jakarta.servlet.forward.request_uri'] eq ordersUrl?'active':''}"
						href="${ordersUrl}">
						<i class="bi bi-list-ol"></i>
						<spring:message code="header.navbar.orders" />
					</a>
				</li>
			</sec:authorize>
		</ul>
		<ul class="navbar-nav">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
					role="button" href="#">
					<i class="bi bi-flag"></i>
					<spring:message code="header.navbar.language" />
				</a>
				<ul class="dropdown-menu">
					<li>
						<a
							class="dropdown-item ${pageContext.response.locale.language eq 'es'?'disabled':''}"
							href="?language=es">
							<i class="bi bi-globe"></i>
							<spring:message code="header.navbar.language.spanish" />
						</a>
					</li>
					<li>
						<a
							class="dropdown-item ${pageContext.response.locale.language eq 'en'?'disabled':''}"
							href="?language=en">
							<i class="bi bi-globe"></i>
							<spring:message code="header.navbar.language.english" />
						</a>
					</li>
				</ul>
			</li>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item">
					<spring:url value="/logout" var="logoutUrl" />
					<a class="nav-link" href="${logoutUrl}">
						<i class="bi bi-box-arrow-right"></i>
						<spring:message code="header.navbar.logout" />
					</a>
				</li>
			</sec:authorize>
		</ul>
	</div>
</nav>

