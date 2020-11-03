package in.ecgc.erp.pebPolicy.model.recovery;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.ecgc.erp.pebPolicy.exceptions.ClaimScrutinyInfo;
import in.ecgc.erp.pebPolicy.utils.*;
import lombok.Data;
//@IsNullOrNot.List({ 
//	@IsNullOrNot(
//    	      field = "IsWriteOff", 
//    	     
//    	      message = "This field is required!", fieldMatch = "IsBlockedFund"
//    	    ), 
//	
//    	})

public class PebPolicyRecoveryMasterBean {
	//boolean sectionAEnabled;
	  // boolean sectionBEnabled;

	private Long recoveryId;
	PebPolicyBlockedFundBean pebPolicyBlockedFundBean;
	PebPolicyWriteOffBean 	pebPolicyWriteOffBean;
	@JsonIgnore
	private MultipartFile recoveryDocument;





	

	

	public MultipartFile getRecoveryDocument() {
		return recoveryDocument;
	}

	public void setRecoveryDocument(MultipartFile recoveryDocument) {
		this.recoveryDocument = recoveryDocument;
	}

	public PebPolicyBlockedFundBean getPebPolicyBlockedFundBean() {
		return pebPolicyBlockedFundBean;
	}

	public void setPebPolicyBlockedFundBean(PebPolicyBlockedFundBean pebPolicyBlockedFundBean) {
		this.pebPolicyBlockedFundBean = pebPolicyBlockedFundBean;
	}

	public PebPolicyWriteOffBean getPebPolicyWriteOffBean() {
		return pebPolicyWriteOffBean;
	}

	public void setPebPolicyWriteOffBean(PebPolicyWriteOffBean pebPolicyWriteOffBean) {
		this.pebPolicyWriteOffBean = pebPolicyWriteOffBean;
	}

	public String getDcaId() {
		return dcaId;
	}

	public void setDcaId(String dcaId) {
		this.dcaId = dcaId;
	}
	@NotEmpty(message = "The DcaId field is required!")
	private String dcaId;
	@NotNull(message = "The ClaimNo field is required!")
	private Long claimNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dcaDate;
	
	@NotNull(message = "The RecoveryInitiatedDate field is required!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recoveryInitiatedDate;
	
	@NotEmpty(message = "The Status field is required!")
	private String status;
	@NotEmpty(message = "The remarks field is required!")
	private String remarks;
	
	private String createdBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;
	
	private String riYear;
	private Integer quarter;
	private Integer versionNo;
	private List<PebPolicyRecoveryDetailsBean> recoveryDetailsList;
	
	
	
	









	@Override
	public String toString() {
		return "PebPolicyRecoveryMasterBean [recoveryId=" + recoveryId + ", pebPolicyBlockedFundBean="
				+ pebPolicyBlockedFundBean + ", pebPolicyWriteOffBean=" + pebPolicyWriteOffBean + ", recoveryDocument="
				+ recoveryDocument + ", dcaId=" + dcaId + ", claimNo=" + claimNo + ", dcaDate=" + dcaDate
				+ ", recoveryInitiatedDate=" + recoveryInitiatedDate + ", status=" + status + ", remarks=" + remarks
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", riYear=" + riYear + ", quarter="
				+ quarter + ", versionNo=" + versionNo + ", recoveryDetailsList=" + recoveryDetailsList + "]";
	}

	public Long getRecoveryId() {
		return recoveryId;
	}
	public void setRecoveryId(Long recoveryId) {
		this.recoveryId = recoveryId;
	}
	public Long getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}
	public Date getDcaDate() {
		return dcaDate;
	}
	public void setDcaDate(Date dcaDate) {
		this.dcaDate = dcaDate;
	}
	public Date getRecoveryInitiatedDate() {
		return recoveryInitiatedDate;
	}
	public void setRecoveryInitiatedDate(Date recoveryInitiatedDate) {
		this.recoveryInitiatedDate = recoveryInitiatedDate;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	
	public String getRiYear() {
		return riYear;
	}
	public void setRiYear(String riYear) {
		this.riYear = riYear;
	}
	public Integer getQuarter() {
		return quarter;
	}
	public void setQuarter(Integer quarter) {
		this.quarter = quarter;
	}
	public Integer getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	public List<PebPolicyRecoveryDetailsBean> getRecoveryDetailsList() {
		return recoveryDetailsList;
	}
	public void setRecoveryDetailsList(List<PebPolicyRecoveryDetailsBean> recoveryDetailsList) {
		this.recoveryDetailsList = recoveryDetailsList;
	}
	

}
