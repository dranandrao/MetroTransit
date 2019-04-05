package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.models.Departure;
import com.models.Directions;
import com.models.Routes;
import com.utils.DirectionUtil;
import com.utils.RoutesUtil;
import com.utils.StopsUtil;
import com.utils.TimePointDepartureUtil;

/**
 * Servlet implementation class NextArrivalBus
 */
@WebServlet("/NextArrivalBus")
public class NextArrivalBus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static ArrayList<Routes> routes = null;
	RoutesUtil routesUtil = null;
	DirectionUtil directionUtil = null;
	StopsUtil stopsUtil = null;
	TimePointDepartureUtil timePointDepartureUtil = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NextArrivalBus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String request_routeName = request.getParameter("route");
		String request_destination = request.getParameter("destination");
		String request_direction = request.getParameter("direction");

		if (!request_routeName.trim().isEmpty() && !request_destination.trim().isEmpty()
				&& !request_direction.trim().isEmpty()) {

			routesUtil = new RoutesUtil();
			int routeNumber = 0;
			int directionNumber = 0;
			String stopCode = null;

			/* 1.Get all routes & route code of the searching route */
			routes = routesUtil.getRoutes();
			if (routes != null && !routes.isEmpty()) {

				/* traverse through the routes and get the routenNumber */
				for (Routes route : routes) {
					if (route.getDescription().equals(request_routeName)) {
						routeNumber = route.getRoute();
						System.out.println("route number " + routeNumber);
					}
				}
			} else {
				request.setAttribute("errorMsg", "Network Error");
				request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
			}

			/* 2. get possible directions options to the route */
			if (routeNumber != 0) {
				directionUtil = new DirectionUtil();

				// get all the directions for the route.
				ArrayList<Directions> directions = directionUtil.getDirections(routeNumber);
				if (directions != null && !directions.isEmpty()) {
					for (Directions direction : directions) {
						/*
						 * Checks whether the direction is present in the
						 * possible directions
						 */
						if (direction.getText().contains(request_direction.toUpperCase())) {
							directionNumber = Integer.valueOf(direction.getValue());
							System.out.println("Direction number " + directionNumber);
						}
					}
				} else {
					request.setAttribute("errorMsg", "Netwrok Error");
					request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("errorMsg", "Route direction not possible");
				request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
			}

			/*
			 * 3. get the possible stops which are in that direction and route
			 */
			if (directionNumber != 0) {
				stopsUtil = new StopsUtil();

				// get all the stops that are in the direction and route.
				ArrayList<Directions> stops = stopsUtil.getStops(routeNumber, directionNumber);
				if (stops != null && !stops.isEmpty()) {
					for (Directions direction : stops) {
						if (direction.getText().equals(request_destination)) {
							stopCode = direction.getValue();
						}
					}
				} else {
					request.setAttribute("errorMsg", "Network Error");
					request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("errorMsg", "Route or From or Direction is empty");
				request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
			}

			/* 4. get time point departures for the route,direction and stop */
			if (stopCode != null) {
				timePointDepartureUtil = new TimePointDepartureUtil();

				/* get all possible departures */
				ArrayList<Departure> departures = timePointDepartureUtil.getTimePointDeparture(routeNumber,
						directionNumber, stopCode);
				if (departures != null && !departures.isEmpty()) {
					StringBuilder stringBuilder = new StringBuilder();
					// To show first actual element.
					for (Departure departure : departures) {
						if (departure.isActual()) {
							// TODO print mins.
							stringBuilder.append("Bus Arrives in " + departure.getDepatureText());
							break;
						} else {
							// current time
							Date currentDate = new Date();
							// buss arrival time
							Date busArrivalDate = departure.getDepartureTime();

							long differenceTime = busArrivalDate.getTime() - currentDate.getTime();
							long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceTime);
							stringBuilder.append("Bus Arrives in " + differenceInMinutes + " mins");
							break;
						}
					}
					request.setAttribute("successMsg", stringBuilder.toString());
					request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMsg", "No buses in this route");
					request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorMsg", "No stop code");
				request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
			}

		} else {
			request.setAttribute("errorMsg", "Route or From or Direction is empty");
			request.getRequestDispatcher("GetWaitingTime.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
