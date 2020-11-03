package in.ecgc.erp.pebPolicy.model.claim;

import java.util.Date;
import java.util.List;

public class ClaimPartPaymentScheduleMasterBean {
	
	private Long claimNo;
	private String createdBy;
	private Date createdOn;
	
	private List<ClaimPartPaymentScheduleDetailsBean> claimPartPaymentScheduleDetailsBeanList;

	public Long getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
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
	public List<ClaimPartPaymentScheduleDetailsBean> getClaimPartPaymentScheduleDetailsBeanList() {
		return claimPartPaymentScheduleDetailsBeanList;
	}

	public void setClaimPartPaymentScheduleDetailsBeanList(
			List<ClaimPartPaymentScheduleDetailsBean> claimPartPaymentScheduleDetailsBeanList) {
		this.claimPartPaymentScheduleDetailsBeanList = claimPartPaymentScheduleDetailsBeanList;
	}

	@Override
	public String toString() {
		return "ClaimPartPaymentScheduleMasterBean [claimNo=" + claimNo + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", claimPartPaymentScheduleDetailsBeanList=" + claimPartPaymentScheduleDetailsBeanList
				+ "]";
	}

	
	
	

}
