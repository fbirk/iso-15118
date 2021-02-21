package com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.DAOInterface.ChargeParameterRepository;

import de.hsrm.cs.wallbox.shared.model.ChargeParameter;

import java.util.Optional;

import javax.ejb.*;
import javax.validation.Valid;

@Path("/charge-parmeters")
public class ChargeParamterResource {

	
	@EJB
	private ChargeParameterRepository chargeParameterRepository;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewChargeParamter(final @Valid ChargeParameter chargeParameter) {
		Optional<ChargeParameter> createParameter = chargeParameterRepository.createChargeParameter(chargeParameter);
		
		if (createParameter.isPresent()) {
			return Response.ok(createParameter.get()).build();
		}
		
		return Response.serverError().build();
	}

	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateChargeParameter(final @Valid ChargeParameter chargeParameter) {
		Optional<ChargeParameter> updateParameter = chargeParameterRepository.updateChargeParameter(chargeParameter);
		
		return Response.ok(updateParameter.get()).build();
	}
}
