package com.apap.tutorial5.service;
import com.apap.tutorial5.model.PilotModel;

import java.util.List;

public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void addPilot(PilotModel pilot);
	void deleteByLicenseNumber(String licenseNumber);
	void updatePilot(String licenseNumber, PilotModel newPilot);

}
