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

public class PebPolicyBlockedFundBean {
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
	private Long recoveryId;
	@NotEmpty(message = "The remarks field is required!")
	private String remarks;
	@NotEmpty(message = "The IsBlockedFund field is required!")
	private String IsBlockedFund;
	private String blockedFundUpdatedBy;

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
	private Date blockedFundUpdatedOn;
	@NotNull(message = "The BlockedfundDate field is required!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date blockedfundDate;

	public Long getRecoveryId() {
		return recoveryId;
	}

	public void setRecoveryId(Long recoveryId) {
		this.recoveryId = recoveryId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsBlockedFund() {
		return IsBlockedFund;
	}

	public void setIsBlockedFund(String isBlockedFund) {
		IsBlockedFund = isBlockedFund;
	}

	public String getBlockedFundUpdatedBy() {
		return blockedFundUpdatedBy;
	}

	public void setBlockedFundUpdatedBy(String blockedFundUpdatedBy) {
		this.blockedFundUpdatedBy = blockedFundUpdatedBy;
	}

	public Date getBlockedFundUpdatedOn() {
		return blockedFundUpdatedOn;
	}

	public void setBlockedFundUpdatedOn(Date blockedFundUpdatedOn) {
		this.blockedFundUpdatedOn = blockedFundUpdatedOn;
	}

	public Date getBlockedfundDate() {
		return blockedfundDate;
	}

	public void setBlockedfundDate(Date blockedfundDate) {
		this.blockedfundDate = blockedfundDate;
	}

	@Override
	public String toString() {
		return "PebPolicyBlockedFundBean [recommenderRemarks=" + recommenderRemarks + ", recommenderId=" + recommenderId
				+ ", recommenderDate=" + recommenderDate + ", approverRemarks=" + approverRemarks + ", approverId="
				+ approverId + ", approverDate=" + approverDate + ", recoveryId=" + recoveryId + ", remarks=" + remarks
				+ ", IsBlockedFund=" + IsBlockedFund + ", blockedFundUpdatedBy=" + blockedFundUpdatedBy
				+ ", blockedFundUpdatedOn=" + blockedFundUpdatedOn + ", blockedfundDate=" + blockedfundDate + "]";
	}


}
