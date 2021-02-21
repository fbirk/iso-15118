package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.DAOInterface;

import java.util.Optional;

import de.hsrm.cs.wallbox.shared.model.ChargeParameter;

public interface ChargeParameterRepository {

	Optional<ChargeParameter> createChargeParameter(ChargeParameter chargeParameter);

	Optional<ChargeParameter> updateChargeParameter(ChargeParameter chargeParameter);
}
