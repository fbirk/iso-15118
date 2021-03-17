/*******************************************************************************
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2015 - 2019  Dr. Marc Mültin (V2G Clarity)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *******************************************************************************/
package com.v2gclarity.risev2g.secc.evseController;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.v2gclarity.risev2g.secc.session.V2GCommunicationSessionSECC;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.ACEVSEChargeParameterType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.ACEVSEStatusType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.EVSENotificationType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.MeterInfoType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.PhysicalValueType;

public class HSRMWallboxACEVSEController implements IACEVSEController {

	private V2GCommunicationSessionSECC commSessionContext;

	public HSRMWallboxACEVSEController() {
	}

	@Override
	public String getEvseID() {
		return "DE*V2G*E12345";
	}

	@Override
	public JAXBElement<ACEVSEChargeParameterType> getACEVSEChargeParameter() {
		String sessionID = commSessionContext.getSessionID().toString();

		ACEVSEChargeParameterType acEVSEChargeParameter = new ACEVSEChargeParameterType();

		PhysicalValueType evseNominalVoltage = commSessionContext.getWallboxServerEndpoint().getWallboxDAO(sessionID).getACNominalVoltage();
		acEVSEChargeParameter.setEVSENominalVoltage(evseNominalVoltage);

		PhysicalValueType evseMaxCurrent = commSessionContext.getWallboxServerEndpoint().getWallboxDAO(sessionID).getMaxCurrent();
		acEVSEChargeParameter.setEVSEMaxCurrent(evseMaxCurrent);

		acEVSEChargeParameter.setACEVSEStatus(getACEVSEStatus(commSessionContext.getWallboxServerEndpoint().getWallboxDAO(sessionID).getEvseNotificationType()));

		return new JAXBElement<ACEVSEChargeParameterType>(new QName("urn:iso:15118:2:2013:MsgDataTypes", "AC_EVSEChargeParameter"), ACEVSEChargeParameterType.class, acEVSEChargeParameter);
	}

	@Override
	public ACEVSEStatusType getACEVSEStatus(EVSENotificationType notification) {
		ACEVSEStatusType acEVSEStatus = new ACEVSEStatusType();
		acEVSEStatus.setEVSENotification((notification != null) ? notification : EVSENotificationType.NONE);
		acEVSEStatus.setNotificationMaxDelay(0);
		acEVSEStatus.setRCD(false);

		return acEVSEStatus;
	}

	@Override
	public void setCommSessionContext(V2GCommunicationSessionSECC commSessionContext) {
		this.commSessionContext = commSessionContext;
	}

	@Override
	public boolean closeContactor() {
		// A check for CP state B would be necessary
		return true;
	}

	@Override
	public boolean openContactor() {
		return true;
	}

	@Override
	public MeterInfoType getMeterInfo() {
		String sessionID = commSessionContext.getSessionID().toString();
		return commSessionContext.getWallboxServerEndpoint().getWallboxDAO(sessionID).getMeterInfo().poll();
	}
}
