package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import java.util.Queue;

import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.rest.openapi.model.Status;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.EVSENotificationType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.MeterInfoType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PhysicalValueType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.SAScheduleListType;

public class WallboxDAO {
	
	private PhysicalValueType minCurrent;
	private PhysicalValueType maxCurrent;
	private PhysicalValueType minVoltage;
	private PhysicalValueType maxVoltage;
	private PhysicalValueType minPower;
	private PhysicalValueType maxPower;
	private PhysicalValueType ripple;
	private PhysicalValueType acNominalVoltage;
	private boolean isAC;
	private EVSENotificationType evseNotificationType;
	private Queue<MeterInfoType> meterInfo;
	private SAScheduleListType saScheduleList;
	private Status status;
	
	public WallboxDAO() {
		evseNotificationType = EVSENotificationType.STOP_CHARGING;
		status = Status.STOPED;
	}
	

	public SAScheduleListType getSaScheduleList() {
		return saScheduleList;
	}

	public void setSaScheduleList(SAScheduleListType saScheduleList) {
		this.saScheduleList = saScheduleList;
	}

	public Queue<MeterInfoType> getMeterInfo() {
		return meterInfo;
	}

	public void setMeterInfo(Queue<MeterInfoType> meterInfo) {
		this.meterInfo = meterInfo;
	}

	public PhysicalValueType getRipple() {
		return ripple;
	}

	public void setRipple(PhysicalValueType ripple) {
		this.ripple = ripple;
	}

	public PhysicalValueType getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(PhysicalValueType maxPower) {
		this.maxPower = maxPower;
	}

	public boolean isAC() {
		return isAC;
	}

	public void setAC(boolean isAC) {
		this.isAC = isAC;
	}

	public PhysicalValueType getMinPower() {
		return minPower;
	}

	public void setMinPower(PhysicalValueType minPower) {
		this.minPower = minPower;
	}

	public PhysicalValueType getMaxVoltage() {
		return maxVoltage;
	}

	public void setMaxVoltage(PhysicalValueType maxVoltage) {
		this.maxVoltage = maxVoltage;
	}

	public PhysicalValueType getMinVoltage() {
		return minVoltage;
	}

	public void setMinVoltage(PhysicalValueType minVoltage) {
		this.minVoltage = minVoltage;
	}

	public PhysicalValueType getMaxCurrent() {
		return maxCurrent;
	}

	public void setMaxCurrent(PhysicalValueType maxCurrent) {
		this.maxCurrent = maxCurrent;
	}

	public PhysicalValueType getMinCurrent() {
		return minCurrent;
	}

	public void setMinCurrent(PhysicalValueType minCurrent) {
		this.minCurrent = minCurrent;
	}

	public PhysicalValueType getACNominalVoltage() {
		return acNominalVoltage;
	}

	public void setACNominalVoltage(PhysicalValueType acNominalVoltage) {
		this.acNominalVoltage = acNominalVoltage;
	}

	public EVSENotificationType getEvseNotificationType() {
		return evseNotificationType;
	}

	public void setEvseNotificationType(EVSENotificationType evseNotificationType) {
		this.evseNotificationType = evseNotificationType;
	}


	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

}
