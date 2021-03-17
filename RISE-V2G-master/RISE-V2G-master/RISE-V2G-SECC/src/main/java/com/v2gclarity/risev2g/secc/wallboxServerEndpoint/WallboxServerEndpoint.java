package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.v2gclarity.risev2g.secc.backend.IBackendInterface;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.CommunicationSessionApiService;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.NotFoundException;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameterAcChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.ChargeParameterDcChargeParameter;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.CommunicationSessionSessionIdStatusDuration;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.InlineObject;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.SASchedule;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Status;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.WallboxAPIPhysicalValueType;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.WallboxAPIPhysicalValueType.UnitEnum;
import com.v2gclarity.risev2g.shared.utils.SecurityUtils;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.EVSENotificationType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PMaxScheduleEntryType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PMaxScheduleType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PhysicalValueType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.RelativeTimeIntervalType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.SAScheduleTupleType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.SalesTariffEntryType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.SalesTariffType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.UnitSymbolType;

public class WallboxServerEndpoint extends CommunicationSessionApiService {

	private Hashtable<String, WallboxDAO> sessions;

	public WallboxServerEndpoint() {
		sessions = new Hashtable<String, WallboxDAO>();
	}

	public void sendMessage(String string) {
		// TODO Auto-generated method stub
	}

	@Override
	public Response addCommunicationSession(SecurityContext securityContext) throws NotFoundException {

		return Response.ok().entity("1").build();
	}

	@Override
	public Response findCommunicationSessionByStatus(Status status, SecurityContext securityContext) throws NotFoundException {

		if (sessions.isEmpty()) {
			throw new NotFoundException(400, "The communication sessions list is empty! No active sessions were found.");
		}

		// get all sessions with given status, retrieve the session-id's, convert to
		// list
		List<String> list = sessions.entrySet().stream().filter(x -> x.getValue().getStatus().equals(status)).map(val -> val.getKey()).collect(Collectors.toList());

		return Response.ok().entity(list).build();
	}

	@Override
	public Response communicationSessionSessionIdChargeParameterGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
		if (!sessions.containsKey(sessionId)) {
			throw new NotFoundException(400, "No session was found with id " + sessionId);
		}

		WallboxDAO dao = sessions.get(sessionId);
		ChargeParameter param = new ChargeParameter();

		if (dao.isAC()) {
			ChargeParameterAcChargeParameter acParam = new ChargeParameterAcChargeParameter();

			acParam.setMaxCurrent(ConvertToWallboxAPIPhyicalValueType(dao.getMaxCurrent()));
			acParam.setNominalVoltage(ConvertToWallboxAPIPhyicalValueType(dao.getACNominalVoltage()));

			param.acChargeParameter(acParam);
			param.dcChargeParameter(null);
		} else {
			ChargeParameterDcChargeParameter dcParam = new ChargeParameterDcChargeParameter();

			dcParam.setMaxCurrentLimit(ConvertToWallboxAPIPhyicalValueType(dao.getMaxCurrent()));
			dcParam.setMaxPowerLimit(ConvertToWallboxAPIPhyicalValueType(dao.getMaxPower()));
			dcParam.setMaxVoltageLimit(ConvertToWallboxAPIPhyicalValueType(dao.getMaxVoltage()));
			dcParam.setMinCurrentLimit(ConvertToWallboxAPIPhyicalValueType(dao.getMinCurrent()));
			dcParam.setMinVoltageLimit(ConvertToWallboxAPIPhyicalValueType(dao.getMinCurrent()));
			dcParam.setPeakCurrentRipple(ConvertToWallboxAPIPhyicalValueType(dao.getRipple()));

			param.dcChargeParameter(dcParam);
			param.acChargeParameter(null);
		}
		return Response.ok().entity(param).build();
	}

	@Override
	public Response communicationSessionSessionIdChargeParameterPost(String sessionId, ChargeParameter body, SecurityContext securityContext) throws NotFoundException {

		if (!sessions.contains(sessionId)) {
			throw new NotFoundException(400, "No session was found with id " + sessionId);
		}

		WallboxDAO dao = sessions.get(sessionId);
		dao.setEvseNotificationType(EVSENotificationType.NONE);
		dao.setStatus(Status.CHARGING);

		dao.setACNominalVoltage(ConvertToRISEPhysicalValueType(body.getAcChargeParameter().getNominalVoltage()));
		dao.setMaxCurrent(ConvertToRISEPhysicalValueType(body.getAcChargeParameter().getMaxCurrent()));

		dao.setMaxPower(ConvertToRISEPhysicalValueType(body.getDcChargeParameter().getMaxPowerLimit()));
		dao.setMaxVoltage(ConvertToRISEPhysicalValueType(body.getDcChargeParameter().getMaxVoltageLimit()));
		dao.setMinCurrent(ConvertToRISEPhysicalValueType(body.getDcChargeParameter().getMinCurrentLimit()));
		dao.setMinVoltage(ConvertToRISEPhysicalValueType(body.getDcChargeParameter().getMinVoltageLimit()));
		dao.setRipple(ConvertToRISEPhysicalValueType(body.getDcChargeParameter().getPeakCurrentRipple()));

		return Response.ok().build();
	}

	@Override
	public Response communicationSessionSessionIdChargeParameterPut(String sessionId, ChargeParameter body, SecurityContext securityContext) throws NotFoundException {

		if (!sessions.contains(sessionId)) {
			throw new NotFoundException(400, "No session was found with id " + sessionId);
		}

		WallboxDAO dao = sessions.get(sessionId);

		dao.setEvseNotificationType(EVSENotificationType.RE_NEGOTIATION);

		return communicationSessionSessionIdChargeParameterPost(sessionId, body, securityContext);
	}

	@Override
	public Response communicationSessionSessionIdScheduleGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
		if (sessions.containsKey(sessionId)) {
			try {
				WallboxDAO dao = sessions.get(sessionId);

				SASchedule schedule = new SASchedule();

				schedule.setpMaxSchedule((List<Object>) dao.getSaScheduleList());

				return Response.ok().entity(schedule).build();
			} catch (Exception e) {
				throw new NotFoundException(400, e.getMessage());
			}
		}
		throw new NotFoundException(400, "No session was found with id " + sessionId);
	}

	@Override
	public Response communicationSessionSessionIdStatusGet(String sessionId, SecurityContext securityContext) throws NotFoundException {
		if (!sessions.containsKey(sessionId)) {
			throw new NotFoundException(400, "No session was found with id " + sessionId);
		}

		return Response.ok().entity(sessions.get(sessionId).getStatus()).build();
	}

	@Override
	public Response communicationSessionSessionIdStatusPut(String sessionId, InlineObject inlineObject, SecurityContext securityContext) throws NotFoundException {
		if (!sessions.containsKey(sessionId)) {
			throw new NotFoundException(400, "No session was found with id " + sessionId);
		}
		WallboxDAO dao = sessions.get(sessionId);

		List<SAScheduleTupleType> list = dao.getSaScheduleList().getSAScheduleTuple();
		SAScheduleTupleType tuple = new SAScheduleTupleType();

		SalesTariffType salesTariff = new SalesTariffType();
		PMaxScheduleType pmax = new PMaxScheduleType();

		// New entry with pMax 0W - indicating a charging pause
		pmax.getPMaxScheduleEntry().add(createPMaxScheduleEntry("1", (short) 0, convertTimeIntervalToDuration(inlineObject.getDuration())));

		// a new empty sales tariff entry
		salesTariff.setId("ID1");
		salesTariff.getSalesTariffEntry().add(createSalesTariffEntry(0L, (short) 1));

		tuple.setSAScheduleTupleID((short) 1);
		tuple.setSalesTariff(salesTariff);
		tuple.setPMaxSchedule(pmax);

		// increase all tuple id's by one
		for (SAScheduleTupleType element : list) {
			element.setSAScheduleTupleID((short) (element.getSAScheduleTupleID() + 1));

			for (PMaxScheduleEntryType pmaxEntry : element.getPMaxSchedule().getPMaxScheduleEntry()) {
				JAXBElement<RelativeTimeIntervalType> timeInterval = (JAXBElement<RelativeTimeIntervalType>) pmaxEntry.getTimeInterval();
				RelativeTimeIntervalType relTimeInterval = timeInterval.getValue();
				relTimeInterval.setStart(relTimeInterval.getStart() + convertTimeIntervalToDuration(inlineObject.getDuration()));

				timeInterval.setValue(relTimeInterval);
				pmaxEntry.setTimeInterval(timeInterval);
			}
		}

		// set the newly created tuple as the first tuple
		list.add(0, tuple);

		// TODO
		throw new NotFoundException(400, "You have to unlock this path first!");
	}

	@Override
	public Response getTest(SecurityContext securityContext) throws NotFoundException {
		return Response.ok().entity("Success!").build();
	}

	private SalesTariffEntryType createSalesTariffEntry(long start, short ePriceLevel) {
		RelativeTimeIntervalType salesTariffTimeInterval = new RelativeTimeIntervalType();
		salesTariffTimeInterval.setStart(start);

		SalesTariffEntryType salesTariffEntry = new SalesTariffEntryType();
		salesTariffEntry
				.setTimeInterval(new JAXBElement<RelativeTimeIntervalType>(new QName("urn:iso:15118:2:2013:MsgDataTypes", "RelativeTimeInterval"), RelativeTimeIntervalType.class, salesTariffTimeInterval));
		salesTariffEntry.setEPriceLevel(ePriceLevel);

		return salesTariffEntry;
	}

	private PMaxScheduleEntryType createPMaxScheduleEntry(String multiplier, short pMax, long start) {
		PhysicalValueType pMaxValue = new PhysicalValueType();
		pMaxValue.setMultiplier(new Byte(multiplier));
		pMaxValue.setUnit(UnitSymbolType.W);
		pMaxValue.setValue(pMax);

		RelativeTimeIntervalType pMaxTimeInterval = new RelativeTimeIntervalType();
		pMaxTimeInterval.setStart(start);

		PMaxScheduleEntryType pMaxScheduleEntry = new PMaxScheduleEntryType();
		pMaxScheduleEntry
				.setTimeInterval(new JAXBElement<RelativeTimeIntervalType>(new QName("urn:iso:15118:2:2013:MsgDataTypes", "RelativeTimeInterval"), RelativeTimeIntervalType.class, pMaxTimeInterval));
		pMaxScheduleEntry.setPMax(pMaxValue);

		return pMaxScheduleEntry;
	}

	private PMaxScheduleEntryType createPMaxScheduleEntry(String multiplier, short pMax, long start, long duration) {
		PMaxScheduleEntryType pMaxScheduleEntry = createPMaxScheduleEntry(multiplier, pMax, start);
		((RelativeTimeIntervalType) pMaxScheduleEntry.getTimeInterval().getValue()).setDuration(duration);

		return pMaxScheduleEntry;
	}

	private long convertTimeIntervalToDuration(CommunicationSessionSessionIdStatusDuration duration) throws NotFoundException {
		// convert time interval to seconds
		long result = (duration.getHours() * 3600) + (duration.getMinutes() * 60) + duration.getSeconds();

		if (result > 86400) {
			throw new NotFoundException(400, "Duration can not exceed 24h!");
		}

		return result;
	}

	/**
	 * Converts the RISEV2G PhysicalValueType to JSON-serializable Wallbox REST-API
	 * PhysicalValueType.
	 * 
	 * @param type the type that needs to be converted
	 * @return the converted PhysicalValueType
	 */
	private WallboxAPIPhysicalValueType ConvertToWallboxAPIPhyicalValueType(PhysicalValueType type) {
		WallboxAPIPhysicalValueType wallboxAPIType = new WallboxAPIPhysicalValueType();

		wallboxAPIType.setMultiplier((int) type.getMultiplier());
		wallboxAPIType.setUnit(UnitEnum.fromValue(type.getUnit().name()));
		wallboxAPIType.setValue((int) type.getValue());

		return wallboxAPIType;
	}

	private PhysicalValueType ConvertToRISEPhysicalValueType(WallboxAPIPhysicalValueType wallboxAPIType) {

		PhysicalValueType valueType = new PhysicalValueType();

		valueType.setMultiplier(wallboxAPIType.getMultiplier().byteValue());
		valueType.setUnit(UnitSymbolType.fromValue(wallboxAPIType.getUnit().name()));
		valueType.setValue(wallboxAPIType.getValue().shortValue());

		return valueType;
	}

	/**
	 * Returns the data object of given communication session
	 * 
	 * @param sessionID the ID which identifies a communication session
	 * @return a WallboxDAO object
	 */
	public WallboxDAO getWallboxDAO(String sessionID) {
		return sessions.get(sessionID);
	}
}
