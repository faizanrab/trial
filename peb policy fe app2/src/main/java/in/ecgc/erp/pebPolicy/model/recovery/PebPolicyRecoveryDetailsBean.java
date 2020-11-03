package in.ecgc.erp.pebPolicy.model.recovery;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

public class PebPolicyRecoveryDetailsBean {
	private long claimNo; 
	
	private long recoveryId;
	
	@NotNull(message = "The RecoveryAmount field is required!")
	private Double recoveryAmount;
	
	@NotNull(message = "The DateOfRecovery field is required!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfRecovery;
	
//	@NotNull(message = "cannot be null")
//	private Integer transactionId;
	@NotEmpty(message = "The remarks field is required!")
	private String remarks;
	
	private String createdBy;
	private Date createdOn;
	private long recoveryInstance;
	@NotNull(message = "The DcaCharge field is required")
	private Double dcaCharge;
	
	private Long dcaId;
//	private Boolean isRecoveryBeforeClaimSettlement;
	private Integer receiptNo;
	
	@Override
	public String toString() {
		return "PebPolicyRecoveryDetailsBean [claimNo=" + claimNo + ", recoveryId=" + recoveryId + ", recoveryAmount="
				+ recoveryAmount + ", dateOfRecovery=" + dateOfRecovery + ", remarks=" + remarks + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", recoveryInstance=" + recoveryInstance + ", dcaCharge="
				+ dcaCharge + ", dcaId=" + dcaId + ", receiptNo=" + receiptNo + "]";
	}
	public long getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(long claimNo) {
		this.claimNo = claimNo;
	}
	public long getRecoveryId() {
		return recoveryId;
	}
	public void setRecoveryId(long recoveryId) {
		this.recoveryId = recoveryId;
	}
	public Double getRecoveryAmount() {
		return recoveryAmount;
	}
	public void setRecoveryAmount(Double recoveryAmount) {
		this.recoveryAmount = recoveryAmount;
	}
	public Date getDateOfRecovery() {
		return dateOfRecovery;
	}
	public void setDateOfRecovery(Date dateOfRecovery) {
		this.dateOfRecovery = dateOfRecovery;
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
	public long getRecoveryInstance() {
		return recoveryInstance;
	}
	public void setRecoveryInstance(long recoveryInstance) {
		this.recoveryInstance = recoveryInstance;
	}
	public Double getDcaCharge() {
		return dcaCharge;
	}
	public void setDcaCharge(Double dcaCharge) {
		this.dcaCharge = dcaCharge;
	}
	public Long getDcaId() {
		return dcaId;
	}
	public void setDcaId(Long dcaId) {
		this.dcaId = dcaId;
	}
	public Integer getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(Integer receiptNo) {
		this.receiptNo = receiptNo;
	}
	
	
}
