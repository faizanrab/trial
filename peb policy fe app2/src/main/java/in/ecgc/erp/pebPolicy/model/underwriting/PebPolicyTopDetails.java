package in.ecgc.erp.pebPolicy.model.underwriting;

/* Use PebPolicyTopDetails POJO instead of PebPolicyTermsOfPaymentTenureDetailsBean & PebPolicyTermsOfPaymentWeightageDetailsBean */
public class PebPolicyTopDetails {
	
	private Long policyNumber;
	
	private Double downPercentage;
	
	private Double advancePercentage;
	
	private Double pacPercentage;
	
	private Double facPercentage;
	
	private Integer downDuration;
	
	private Integer progressDuration;
	
	private Integer pacDuration;
	
	private Integer facDuration;
	
	private Double advanceAmount;
	
	private Double downAmount;
	
	private Double progressAmount;
	
	private Double pacAmount;
	
	private Double facAmount;
	
	private String isSupplyService;
	
	private Double  downPremiumRate;
	
	private Double progressPremiumRate;
	
	private Double pacPremiumRate;
	
	private Double facPremiumRate;
	
	private PebPolicyEndorsementMstBean endorsementId;
	
	private Integer versionNo;

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Double getDownPercentage() {
		return downPercentage;
	}

	public void setDownPercentage(Double downPercentage) {
		this.downPercentage = downPercentage;
	}

	public Double getAdvancePercentage() {
		return advancePercentage;
	}

	public void setAdvancePercentage(Double advancePercentage) {
		this.advancePercentage = advancePercentage;
	}

	public Double getPacPercentage() {
		return pacPercentage;
	}

	public void setPacPercentage(Double pacPercentage) {
		this.pacPercentage = pacPercentage;
	}

	public Double getFacPercentage() {
		return facPercentage;
	}

	public void setFacPercentage(Double facPercentage) {
		this.facPercentage = facPercentage;
	}

	public Integer getDownDuration() {
		return downDuration;
	}

	public void setDownDuration(Integer downDuration) {
		this.downDuration = downDuration;
	}

	public Integer getProgressDuration() {
		return progressDuration;
	}

	public void setProgressDuration(Integer progressDuration) {
		this.progressDuration = progressDuration;
	}

	public Integer getPacDuration() {
		return pacDuration;
	}

	public void setPacDuration(Integer pacDuration) {
		this.pacDuration = pacDuration;
	}

	public Integer getFacDuration() {
		return facDuration;
	}

	public void setFacDuration(Integer facDuration) {
		this.facDuration = facDuration;
	}

	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public Double getDownAmount() {
		return downAmount;
	}

	public void setDownAmount(Double downAmount) {
		this.downAmount = downAmount;
	}

	public Double getProgressAmount() {
		return progressAmount;
	}

	public void setProgressAmount(Double progressAmount) {
		this.progressAmount = progressAmount;
	}

	public Double getPacAmount() {
		return pacAmount;
	}

	public void setPacAmount(Double pacAmount) {
		this.pacAmount = pacAmount;
	}

	public Double getFacAmount() {
		return facAmount;
	}

	public void setFacAmount(Double facAmount) {
		this.facAmount = facAmount;
	}

	public String getIsSupplyService() {
		return isSupplyService;
	}

	public void setIsSupplyService(String isSupplyService) {
		this.isSupplyService = isSupplyService;
	}

	public Double getDownPremiumRate() {
		return downPremiumRate;
	}

	public void setDownPremiumRate(Double downPremiumRate) {
		this.downPremiumRate = downPremiumRate;
	}

	public Double getProgressPremiumRate() {
		return progressPremiumRate;
	}

	public void setProgressPremiumRate(Double progressPremiumRate) {
		this.progressPremiumRate = progressPremiumRate;
	}

	public Double getPacPremiumRate() {
		return pacPremiumRate;
	}

	public void setPacPremiumRate(Double pacPremiumRate) {
		this.pacPremiumRate = pacPremiumRate;
	}

	public Double getFacPremiumRate() {
		return facPremiumRate;
	}

	public void setFacPremiumRate(Double facPremiumRate) {
		this.facPremiumRate = facPremiumRate;
	}

	public PebPolicyEndorsementMstBean getEndorsementId() {
		return endorsementId;
	}

	public void setEndorsementId(PebPolicyEndorsementMstBean endorsementId) {
		this.endorsementId = endorsementId;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "PebPolicyTopDetails [policyNumber=" + policyNumber + ", downPercentage=" + downPercentage
				+ ", advancePercentage=" + advancePercentage + ", pacPercentage=" + pacPercentage + ", facPercentage="
				+ facPercentage + ", downDuration=" + downDuration + ", progressDuration=" + progressDuration
				+ ", pacDuration=" + pacDuration + ", facDuration=" + facDuration + ", advanceAmount=" + advanceAmount
				+ ", downAmount=" + downAmount + ", progressAmount=" + progressAmount + ", pacAmount=" + pacAmount
				+ ", facAmount=" + facAmount + ", isSupplyService=" + isSupplyService + ", downPremiumRate="
				+ downPremiumRate + ", progressPremiumRate=" + progressPremiumRate + ", pacPremiumRate="
				+ pacPremiumRate + ", facPremiumRate=" + facPremiumRate + ", endorsementId=" + endorsementId
				+ ", versionNo=" + versionNo + "]";
	}
	
	

}
