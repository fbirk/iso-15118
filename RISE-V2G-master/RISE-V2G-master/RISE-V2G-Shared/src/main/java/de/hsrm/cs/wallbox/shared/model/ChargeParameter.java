package de.hsrm.cs.wallbox.shared.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PhysicalValueType;

@XmlRootElement
//@Entity(name = "chargeParameter")
public class ChargeParameter implements Serializable {
	
	private PhysicalValueType eAmount;
	private PhysicalValueType maxVoltage;
	private PhysicalValueType minVoltage;
	private PhysicalValueType maxCurrent;
	private PhysicalValueType minCurrent;
	private PhysicalValueType maxPower;
	private PhysicalValueType minPower;
	public PhysicalValueType geteAmount() {
		return eAmount;
	}
	public void seteAmount(PhysicalValueType eAmount) {
		this.eAmount = eAmount;
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
	public PhysicalValueType getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(PhysicalValueType maxPower) {
		this.maxPower = maxPower;
	}
	public PhysicalValueType getMinPower() {
		return minPower;
	}
	public void setMinPower(PhysicalValueType minPower) {
		this.minPower = minPower;
	}
}
