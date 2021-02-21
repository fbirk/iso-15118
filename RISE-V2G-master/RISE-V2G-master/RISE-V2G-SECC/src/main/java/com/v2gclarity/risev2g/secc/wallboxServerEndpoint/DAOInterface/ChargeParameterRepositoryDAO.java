package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.DAOInterface;

import java.util.Optional;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.WallboxServerModelSingleton;

import de.hsrm.cs.wallbox.shared.model.ChargeParameter;

public class ChargeParameterRepositoryDAO implements ChargeParameterRepository {

	@Override
	public Optional<ChargeParameter> createChargeParameter(ChargeParameter chargeParameter) {
		WallboxServerModelSingleton dataStore = WallboxServerModelSingleton.getInstance();
		
		dataStore.setMaxCurrent(chargeParameter.getMaxCurrent());
		dataStore.setMaxPower(chargeParameter.getMaxPower());
		dataStore.setMaxVoltage(chargeParameter.getMaxVoltage());
		dataStore.setMinCurrent(chargeParameter.getMinCurrent());
		dataStore.setMinPower(chargeParameter.getMinPower());
		dataStore.setMinVoltage(chargeParameter.getMinVoltage());
		
		return null;
	}

	@Override
	public Optional<ChargeParameter> updateChargeParameter(ChargeParameter chargeParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
