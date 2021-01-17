package com.v2gclarity.risev2g.secc.wallboxServerEndpoint;

import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.UnitSymbolType;

public class PhysicalUnit {
	
	private int multiplier;
	private UnitSymbolType unit;
	private short value;
	
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public UnitSymbolType getUnit() {
		return unit;
	}
	public void setUnit(UnitSymbolType unit) {
		this.unit = unit;
	}
	public short getValue() {
		return value;
	}
	public void setValue(short value) {
		this.value = value;
	}
}
