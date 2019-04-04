/**
 * 
 */
package com.models;

import java.util.Date;

/**
 * @author dranandrao
 *
 */
public class Departure {

	private boolean Actual;
	private int BlockNumber;
	private String DepartureText;
	private Date DepartureTime;
	private String Description;
	private String Gate;
	private String Route;
	private String RouteDirection;
	private String Terminal;
	private int VehicleHeading;
	private String VehicleLatitude;
	private String VehicleLongitude;

	public boolean isActual() {
		return Actual;
	}

	public void setActual(boolean actual) {
		Actual = actual;
	}

	public int getBlockNumber() {
		return BlockNumber;
	}

	public void setBlockNumber(int blockNumber) {
		BlockNumber = blockNumber;
	}

	public String getDepatureText() {
		return DepartureText;
	}

	public void setDepatureText(String depatureText) {
		DepartureText = depatureText;
	}

	public Date getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(Date departureTime) {
		DepartureTime = departureTime;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getGate() {
		return Gate;
	}

	public void setGate(String gate) {
		Gate = gate;
	}

	public String getRoute() {
		return Route;
	}

	public void setRoute(String route) {
		Route = route;
	}

	public String getRouteDirection() {
		return RouteDirection;
	}

	public void setRouteDirection(String routeDirection) {
		RouteDirection = routeDirection;
	}

	public String getTerminal() {
		return Terminal;
	}

	public void setTerminal(String terminal) {
		Terminal = terminal;
	}

	public int getVehicleHeading() {
		return VehicleHeading;
	}

	public void setVehicleHeading(int vehicleHeading) {
		VehicleHeading = vehicleHeading;
	}

	public String getVehicleLatitude() {
		return VehicleLatitude;
	}

	public void setVehicleLatitude(String vehicleLatitude) {
		VehicleLatitude = vehicleLatitude;
	}

	public String getVehicleLongitude() {
		return VehicleLongitude;
	}

	public void setVehicleLongitude(String vehicleLongitude) {
		VehicleLongitude = vehicleLongitude;
	}

}
