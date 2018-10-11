package com.apap.tutorial5.service;
import com.apap.tutorial5.model.FlightModel;
import java.util.List;

public interface FlightService {
	List<FlightModel> getFlightDetailByPilot(String licenseNumber);
	FlightModel getFlightDetailById(long id);
	void addFlight(FlightModel flight);
	void updateFlight(long id, FlightModel newFlight);
	void deleteById(long id);
	
	
}
