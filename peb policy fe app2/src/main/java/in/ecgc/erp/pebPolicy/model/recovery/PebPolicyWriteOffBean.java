package in.ecgc.erp.pebPolicy.model.recovery;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

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

public class PebPolicyWriteOffBean {

	private Long recoveryId;
	@NotEmpty(message = "The remarks field is required!")
	private String remarks;
	private String writeOffUpdatedBy;
	@NotEmpty(message ="The RecommenderRemarks field is required!")
	private String recommenderRemarks;
	private String recommenderId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date recommenderDate;
	@NotEmpty(message ="The ApproverRemarks field is required!")
	private String approverRemarks;
	private String approverId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date approverDate;
	
	public String getRecommenderRemarks() {
		return recommenderRemarks;
	}

	public void setRecommenderRemarks(String recommenderRemarks) {
		this.recommenderRemarks = recommenderRemarks;
	}

	public String getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(String recommenderId) {
		this.recommenderId = recommenderId;
	}

	public Date getRecommenderDate() {
		return recommenderDate;
	}

	public void setRecommenderDate(Date recommenderDate) {
		this.recommenderDate = recommenderDate;
	}

	public String getApproverRemarks() {
		return approverRemarks;
	}

	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public Date getApproverDate() {
		return approverDate;
	}

	public void setApproverDate(Date approverDate) {
		this.approverDate = approverDate;
	}



	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeOffUpdatedOn;
	@NotEmpty(message = "The IsWriteOff field is required!")
	private String IsWriteOff;
	@NotNull(message = "The WriteOffDate field is required!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeOffDate;

	
	
	public Long getRecoveryId() {
		return recoveryId;
	}

	public void setRecoveryId(Long recoveryId) {
		this.recoveryId = recoveryId;
	}

	public String getIsWriteOff() {
		return IsWriteOff;
	}

	public void setIsWriteOff(String isWriteOff) {
		IsWriteOff = isWriteOff;
	}

	public Date getWriteOffDate() {
		return writeOffDate;
	}

	public void setWriteOffDate(Date writeOffDate) {
		this.writeOffDate = writeOffDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWriteOffUpdatedBy() {
		return writeOffUpdatedBy;
	}

	public void setWriteOffUpdatedBy(String writeOffUpdatedBy) {
		this.writeOffUpdatedBy = writeOffUpdatedBy;
	}

	public Date getWriteOffUpdatedOn() {
		return writeOffUpdatedOn;
	}

	public void setWriteOffUpdatedOn(Date writeOffUpdatedOn) {
		this.writeOffUpdatedOn = writeOffUpdatedOn;
	}

	@Override
	public String toString() {
		return "PebPolicyWriteOffBean [recoveryId=" + recoveryId + ", remarks=" + remarks + ", writeOffUpdatedBy="
				+ writeOffUpdatedBy + ", recommenderRemarks=" + recommenderRemarks + ", recommenderId=" + recommenderId
				+ ", recommenderDate=" + recommenderDate + ", approverRemarks=" + approverRemarks + ", approverId="
				+ approverId + ", approverDate=" + approverDate + ", writeOffUpdatedOn=" + writeOffUpdatedOn
				+ ", IsWriteOff=" + IsWriteOff + ", writeOffDate=" + writeOffDate + "]";
	}

	


	
}
