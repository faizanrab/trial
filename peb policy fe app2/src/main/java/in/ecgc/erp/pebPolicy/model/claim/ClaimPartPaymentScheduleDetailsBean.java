package in.ecgc.erp.pebPolicy.model.claim;

public class ClaimPartPaymentScheduleDetailsBean {
	
	private Long claimNo;
	private Long ppsId;
	private String monthYear;
	private Double partClaimAmount;
	private Double partClaimAmountApproved;
	private String remarks;
	public Long getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}
	public Long getPpsId() {
		return ppsId;
	}
	public void setPpsId(Long ppsId) {
		this.ppsId = ppsId;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public Double getPartClaimAmount() {
		return partClaimAmount;
	}
	public void setPartClaimAmount(Double partClaimAmount) {
		this.partClaimAmount = partClaimAmount;
	}
	public Double getPartClaimAmountApproved() {
		return partClaimAmountApproved;
	}
	public void setPartClaimAmountApproved(Double partClaimAmountApproved) {
		this.partClaimAmountApproved = partClaimAmountApproved;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "ClaimPartPaymentScheduleDetailsBean [claimNo=" + claimNo + ", ppsId=" + ppsId + ", monthYear="
				+ monthYear + ", partClaimAmount=" + partClaimAmount + ", partClaimAmountApproved="
				+ partClaimAmountApproved + ", remarks=" + remarks + "]";
	}
	
	

}
