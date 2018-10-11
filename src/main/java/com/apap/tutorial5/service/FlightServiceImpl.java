package com.apap.tutorial5.service;
import com.apap.tutorial5.repository.FlightDb;
import com.apap.tutorial5.model.FlightModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDb flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}
	
	@Override
	public List<FlightModel> getFlightDetailByPilot(String licenseNumber) {
		return flightDb.findByPilotLicenseNumber(licenseNumber);
	}

	@Override
	public void deleteById(long id) {
		flightDb.deleteById(id);
	}

	@Override
	public void updateFlight(long id, FlightModel newFlight) {
		FlightModel updateFlight = flightDb.findById(id);
		updateFlight.setTime(newFlight.getTime());	
		updateFlight.setFlightNumber(newFlight.getFlightNumber());
		updateFlight.setDestination(newFlight.getDestination());
		updateFlight.setOrigin(newFlight.getOrigin());
		flightDb.save(updateFlight);
	}

	@Override
	public FlightModel getFlightDetailById(long id) {
		return flightDb.findById(id);
	}

}
