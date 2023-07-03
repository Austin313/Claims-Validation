package com.aa.POJOS;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CLAIM {
	private String CMPCOD;
	private String BTCHID;
	private String SERNUM;
	private String MALSEQNUM;
	private String MALIDR;
	private String CSGDOCNUM;
	private String ACPSCNCAR;
	private String POSSCNAPCOD;
	private String ACPDAT;
	private String LODSCNCARCOD;
	private String LODSCNARPCOD;
	private String LODSCNDAT;
	private String DLVSCNCAR;
	private String DLVARPCOD;
	private String ACTDLVDAT;
	private String ACTWGT;
	private String ACTWGTUNT;
	private String ACTDSTCOD;
	private String ACTORGCOD;
	private String CLMTYP;
	private String ACTREGCOD;
	private String ACTCTRNUM;
	
	public CLAIM(String cMPCOD, String bTCHID, String sERNUM, String mALSEQNUM, String mALIDR, String cSGDOCNUM,
			String aCPSCNCAR, String pOSSCNAPCOD, String aCPDAT, String lODSCNCARCOD, String lODSCNARPCOD,
			String lODSCNDAT, String dLVSCNCAR, String dLVARPCOD, String aCTDLVDAT, String aCTWGT, String aCTWGTUNT,
			String aCTDSTCOD, String aCTORGCOD, String cLMTYP, String aCTREGCOD, String aCTCTRNUM) {
		super();
		CMPCOD = cMPCOD;
		BTCHID = bTCHID;
		SERNUM = sERNUM;
		MALSEQNUM = mALSEQNUM;
		MALIDR = mALIDR;
		CSGDOCNUM = cSGDOCNUM;
		ACPSCNCAR = aCPSCNCAR;
		POSSCNAPCOD = pOSSCNAPCOD;
		ACPDAT = aCPDAT;
		LODSCNCARCOD = lODSCNCARCOD;
		LODSCNARPCOD = lODSCNARPCOD;
		LODSCNDAT = lODSCNDAT;
		DLVSCNCAR = dLVSCNCAR;
		DLVARPCOD = dLVARPCOD;
		ACTDLVDAT = aCTDLVDAT;
		ACTWGT = aCTWGT;
		ACTWGTUNT = aCTWGTUNT;
		ACTDSTCOD = aCTDSTCOD;
		ACTORGCOD = aCTORGCOD;
		CLMTYP = cLMTYP;
		ACTREGCOD = aCTREGCOD;
		ACTCTRNUM = aCTCTRNUM;
	}
	public String getCMPCOD() {
		return CMPCOD;
	}
	public void setCMPCOD(String cMPCOD) {
		CMPCOD = cMPCOD;
	}
	public String getBTCHID() {
		return BTCHID;
	}
	public void setBTCHID(String bTCHID) {
		BTCHID = bTCHID;
	}
	public String getSERNUM() {
		return SERNUM;
	}
	public void setSERNUM(String sERNUM) {
		SERNUM = sERNUM;
	}
	public String getMALSEQNUM() {
		return MALSEQNUM;
	}
	public void setMALSEQNUM(String mALSEQNUM) {
		MALSEQNUM = mALSEQNUM;
	}
	public String getMALIDR() {
		return MALIDR;
	}
	public void setMALIDR(String mALIDR) {
		MALIDR = mALIDR;
	}
	public String getCSGDOCNUM() {
		return CSGDOCNUM;
	}
	public void setCSGDOCNUM(String cSGDOCNUM) {
		CSGDOCNUM = cSGDOCNUM;
	}
	public String getACPSCNCAR() {
		return ACPSCNCAR;
	}
	public void setACPSCNCAR(String aCPSCNCAR) {
		ACPSCNCAR = aCPSCNCAR;
	}
	public String getPOSSCNAPCOD() {
		return POSSCNAPCOD;
	}
	public void setPOSSCNAPCOD(String pOSSCNAPCOD) {
		POSSCNAPCOD = pOSSCNAPCOD;
	}
	public String getACPDAT() {
		return ACPDAT;
	}
	public void setACPDAT(String aCPDAT) throws ParseException {
		ACPDAT = formatTime(aCPDAT);
	}
	public String getLODSCNCARCOD() {
		return LODSCNCARCOD;
	}
	public void setLODSCNCARCOD(String lODSCNCARCOD) {
		LODSCNCARCOD = lODSCNCARCOD;
	}
	public String getLODSCNARPCOD() {
		return LODSCNARPCOD;
	}
	public void setLODSCNARPCOD(String lODSCNARPCOD) {
		LODSCNARPCOD = lODSCNARPCOD;
	}
	public String getLODSCNDAT() {
		return LODSCNDAT;
	}
	public void setLODSCNDAT(String lODSCNDAT) throws ParseException {
		LODSCNDAT = formatTime(lODSCNDAT);
	}
	public String getDLVSCNCAR() {
		return DLVSCNCAR;
	}
	public void setDLVSCNCAR(String dLVSCNCAR) {
		DLVSCNCAR = dLVSCNCAR;
	}
	public String getDLVARPCOD() {
		return DLVARPCOD;
	}
	public void setDLVARPCOD(String dLVARPCOD) {
		DLVARPCOD = dLVARPCOD;
	}
	public String getACTDLVDAT() {
		return ACTDLVDAT;
	}
	public void setACTDLVDAT(String aCTDLVDAT) throws ParseException {
		ACTDLVDAT = formatTime(aCTDLVDAT);
	}
	public String getACTWGT() {
		return ACTWGT;
	}
	public void setACTWGT(String aCTWGT) {
		ACTWGT = aCTWGT;
	}
	public String getACTWGTUNT() {
		return ACTWGTUNT;
	}
	public void setACTWGTUNT(String aCTWGTUNT) {
		ACTWGTUNT = aCTWGTUNT;
	}
	public String getACTDSTCOD() {
		return ACTDSTCOD;
	}
	public void setACTDSTCOD(String aCTDSTCOD) {
		ACTDSTCOD = aCTDSTCOD;
	}
	public String getACTORGCOD() {
		return ACTORGCOD;
	}
	public void setACTORGCOD(String aCTORGCOD) {
		ACTORGCOD = aCTORGCOD;
	}
	public String getCLMTYP() {
		return CLMTYP;
	}
	public void setCLMTYP(String cLMTYP) {
		CLMTYP = cLMTYP;
	}
	public String getACTREGCOD() {
		return ACTREGCOD;
	}
	public void setACTREGCOD(String aCTREGCOD) {
		ACTREGCOD = aCTREGCOD;
	}
	public String getACTCTRNUM() {
		return ACTCTRNUM;
	}
	public void setACTCTRNUM(String aCTCTRNUM) {
		ACTCTRNUM = aCTCTRNUM;
	}
	
	public String[] toArray() {
		String[] sol = {CMPCOD,BTCHID,SERNUM,MALSEQNUM,MALIDR,CSGDOCNUM,ACPSCNCAR,POSSCNAPCOD,ACPDAT,LODSCNCARCOD,LODSCNARPCOD,LODSCNDAT,DLVSCNCAR,DLVARPCOD,ACTDLVDAT,ACTWGT,ACTWGTUNT,ACTDSTCOD,ACTORGCOD,CLMTYP,	ACTREGCOD,	ACTCTRNUM};
		return sol;
	}
	
	public static String formatTime(String time) throws ParseException{
		DateTimeFormatter df = DateTimeFormatter.ofPattern("uuMMddHHmm");
		LocalDateTime date = LocalDateTime.parse(time,df);
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("M/d/u H:m").withLocale(Locale.ENGLISH);
		String newTime = date.format(df2);
		return newTime;
}

}
