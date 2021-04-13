package com.v2gclarity.risev2g.secc.backend;

import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.v2gclarity.risev2g.secc.session.V2GCommunicationSessionSECC;
import com.v2gclarity.risev2g.secc.wallboxServerEndpoint.WallboxDAO;
import com.v2gclarity.risev2g.shared.enumerations.GlobalValues;
import com.v2gclarity.risev2g.shared.utils.SecurityUtils;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.CertificateChainType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.EMAIDType;
import com.v2gclarity.risev2g.shared.v2gMessages.msgDef.SAScheduleListType;

public class HSRMWallboxBackend implements IBackendInterface {

	private V2GCommunicationSessionSECC commSessionContext;
	private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
	private ECPrivateKey moSubCA2PrivateKey;
	
	@Override
	public void setCommSessionContext(V2GCommunicationSessionSECC commSessionContext) {
		this.commSessionContext = commSessionContext;
	}
	
	public V2GCommunicationSessionSECC getCommSessionContext() {
		return this.commSessionContext;
	}

	@Override
	public SAScheduleListType getSAScheduleList(int maxEntriesSAScheduleTuple, long departureTime, HashMap<String, byte[]> xmlSignatureRefElements) {
		return getSAScheduleList(maxEntriesSAScheduleTuple, departureTime, xmlSignatureRefElements, (short)-1);
	}

	@Override
	public SAScheduleListType getSAScheduleList(int maxEntriesSAScheduleTuple, long departureTime, HashMap<String, byte[]> xmlSignatureRefElements, short selectedSAScheduleTupleId) {
		String sessionID = commSessionContext.getSessionID().toString();
		
		WallboxDAO dao = commSessionContext.getWallboxServerEndpoint().getWallboxDAO(sessionID);
		
		return dao.getSaScheduleList();
	}

	@Override
	public CertificateChainType getContractCertificateChain(CertificateChainType oldContractCertChain) {
		/*
		 * Normally, a backend protocol such as OCPP would be used to retrieve the new contract certificate chain
		 * based on the to-be-updated old contract certificate chain
		 */
		EMAIDType providedEMAID = SecurityUtils.getEMAID(oldContractCertChain);
		
		/*
		 * NOTE 1: You need to agree with your test partner on valid, authorized EMAIDs that you put into this list.
		 * 
		 * NOTE 2: Not the EMAID given as a parameter of CertificateUpdateReq is checked (error prone), but the EMAID
		 * provided in the common name field of the to-be-updated contract certificate
		 */
		ArrayList<EMAIDType> authorizedEMAIDs = new ArrayList<EMAIDType>();
		
		// This is a list of EMAIDs used for testing purposes, like a whitelist 
		EMAIDType authorizedEMAID1 = new EMAIDType();
		authorizedEMAID1.setId("id1");
		authorizedEMAID1.setValue("DE1ABCD2EF357A");
		
		EMAIDType authorizedEMAID2 = new EMAIDType();
		authorizedEMAID2.setId("id2");
		authorizedEMAID2.setValue("DE1ABCD2EF357C");
		
		EMAIDType authorizedEMAID3 = new EMAIDType();
		authorizedEMAID3.setId("id2");
		authorizedEMAID3.setValue("DE1230000000021");
		
		authorizedEMAIDs.add(authorizedEMAID1);
		authorizedEMAIDs.add(authorizedEMAID2);
		authorizedEMAIDs.add(authorizedEMAID3);
		
		boolean emaidFound = false;
		
		for (EMAIDType emaid : authorizedEMAIDs) {
			if (emaid.getValue().equals(providedEMAID.getValue()))
				emaidFound = true;
		}
		
		if (emaidFound)
			return SecurityUtils.getCertificateChain("./moCertChain.p12");
		else {
			getLogger().warn("EMAID '" + providedEMAID.getValue() + "' (read from common name field of contract "
						   + "certificate) is not authorized");
			return null;
		}
	}

	@Override
	public CertificateChainType getContractCertificateChain(X509Certificate oemProvisioningCert) {
		/*
		 * Normally, a backend protocol such as OCPP would be used to retrieve the contract certificate chain
		 * based on the OEM provisioning certificate
		 */
		return SecurityUtils.getCertificateChain("./moCertChain.p12");
	}

	@Override
	public ECPrivateKey getContractCertificatePrivateKey() {
		KeyStore keyStore = SecurityUtils.getPKCS12KeyStore(
				"./moCertChain.p12", 
				GlobalValues.PASSPHRASE_FOR_CERTIFICATES_AND_KEYS.toString());
		ECPrivateKey privateKey = SecurityUtils.getPrivateKey(keyStore);
		
		if (privateKey == null) 
			getLogger().error("No private key available from contract certificate keystore");
		
		return privateKey;
	}

	@Override
	public CertificateChainType getCPSCertificateChain() {
		return SecurityUtils.getCertificateChain("./cpsCertChain.p12");
	}

	@Override
	public ECPrivateKey getCPSLeafPrivateKey() {
		KeyStore keyStore = SecurityUtils.getPKCS12KeyStore(
				"./cpsCertChain.p12", 
				GlobalValues.PASSPHRASE_FOR_CERTIFICATES_AND_KEYS.toString());
		ECPrivateKey privateKey = SecurityUtils.getPrivateKey(keyStore);
		
		if (privateKey == null) 
			getLogger().error("No private key available from Certificate Provisioning Service keystore");
		
		return privateKey;
	}

	@Override
	public ECPrivateKey getMOSubCA2PrivateKey() {
		return this.moSubCA2PrivateKey;
	}
	
	public void setMoSubCA2PrivateKey(ECPrivateKey moSubCA2PrivateKey) {
		this.moSubCA2PrivateKey = moSubCA2PrivateKey;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
