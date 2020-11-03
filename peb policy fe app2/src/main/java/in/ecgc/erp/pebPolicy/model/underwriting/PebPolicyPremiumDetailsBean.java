package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PebPolicyPremiumDetailsBean {

	private Long policyNumber;
	
	private PebPolicyEndorsementMstBean endorsementId;
	
	private String isPreshipmentRider;
	
	private String isSupplyOrServiceOrBoth;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date extStartDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date extEndDate;
	
	private Double premiumRate;
	
	private Double premiumAmount;
	
	private String isPreshipDiscountApplied;
	
	private Integer versionNo;

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public PebPolicyEndorsementMstBean getEndorsementId() {
		return endorsementId;
	}

	public void setEndorsementId(PebPolicyEndorsementMstBean endorsementId) {
		this.endorsementId = endorsementId;
	}

	public String getIsPreshipmentRider() {
		return isPreshipmentRider;
	}

	public void setIsPreshipmentRider(String isPreshipmentRider) {
		this.isPreshipmentRider = isPreshipmentRider;
	}

	public String getIsSupplyOrServiceOrBoth() {
		return isSupplyOrServiceOrBoth;
	}

	public void setIsSupplyOrServiceOrBoth(String isSupplyOrServiceOrBoth) {
		this.isSupplyOrServiceOrBoth = isSupplyOrServiceOrBoth;
	}

	public Date getExtStartDate() {
		return extStartDate;
	}

	public void setExtStartDate(Date extStartDate) {
		this.extStartDate = extStartDate;
	}

	public Date getExtEndDate() {
		return extEndDate;
	}

	public void setExtEndDate(Date extEndDate) {
		this.extEndDate = extEndDate;
	}

	public Double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
	}

	public Double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getIsPreshipDiscountApplied() {
		return isPreshipDiscountApplied;
	}

	public void setIsPreshipDiscountApplied(String isPreshipDiscountApplied) {
		this.isPreshipDiscountApplied = isPreshipDiscountApplied;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "PebPolicyPremiumDetailsBean [policyNumber=" + policyNumber + ", endorsementId=" + endorsementId
				+ ", isPreshipmentRider=" + isPreshipmentRider + ", isSupplyOrServiceOrBoth=" + isSupplyOrServiceOrBoth
				+ ", extStartDate=" + extStartDate + ", extEndDate=" + extEndDate + ", premiumRate=" + premiumRate
				+ ", premiumAmount=" + premiumAmount + ", isPreshipDiscountApplied=" + isPreshipDiscountApplied
				+ ", versionNo=" + versionNo + "]";
	}

	
	
}
