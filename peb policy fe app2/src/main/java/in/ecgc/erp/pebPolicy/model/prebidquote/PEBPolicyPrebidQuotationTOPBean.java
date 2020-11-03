package in.ecgc.erp.pebPolicy.model.prebidquote;





public class PEBPolicyPrebidQuotationTOPBean {

	private PEBPolicyPrebidQuotationBean pbId;
	
	private String isSupplyFlag; // TO show/hide corresponding divs of TOP not 
	
	private Integer isSupplyService;
	
	private Double advancePercentage;
	
	private Double advanceAmount;
	
	private Double downPercentage;
	
	private Double downAmount;
	
	private Double progressPercentage;
	
	private Double progressAmount;
	
	private Double pacPercentage;
	
	private Double pacAmount;
	
	private Double facPercentage;
	
	private Double facAmount;

	private Integer downDuration;
	
	private Integer progressDuration;
	
	private Integer pacDuration;
	
	private Integer facDuration;
	
	private Double  downPremiumRate;
	
	private Double progressPremiumRate;
	
	private Double pacPremiumRate;
	
	private Double facPremiumRate;
	
	private Integer versionNo;

	public PEBPolicyPrebidQuotationTOPBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PEBPolicyPrebidQuotationBean getPbId() {
		return pbId;
	}

	public void setPbId(PEBPolicyPrebidQuotationBean pbId) {
		this.pbId = pbId;
	}

	public String getIsSupplyFlag() {
		return isSupplyFlag;
	}

	public void setIsSupplyFlag(String isSupplyFlag) {
		this.isSupplyFlag = isSupplyFlag;
	}

	public Integer getIsSupplyService() {
		return isSupplyService;
	}

	public void setIsSupplyService(Integer isSupplyService) {
		this.isSupplyService = isSupplyService;
	}

	public Double getAdvancePercentage() {
		return advancePercentage;
	}

	public void setAdvancePercentage(Double advancePercentage) {
		this.advancePercentage = advancePercentage;
	}

	public Double getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(Double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public Double getDownPercentage() {
		return downPercentage;
	}

	public void setDownPercentage(Double downPercentage) {
		this.downPercentage = downPercentage;
	}

	public Double getDownAmount() {
		return downAmount;
	}

	public void setDownAmount(Double downAmount) {
		this.downAmount = downAmount;
	}

	public Double getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(Double progressPercentage) {
		this.progressPercentage = progressPercentage;
	}

	public Double getProgressAmount() {
		return progressAmount;
	}

	public void setProgressAmount(Double progressAmount) {
		this.progressAmount = progressAmount;
	}

	public Double getPacPercentage() {
		return pacPercentage;
	}

	public void setPacPercentage(Double pacPercentage) {
		this.pacPercentage = pacPercentage;
	}

	public Double getPacAmount() {
		return pacAmount;
	}

	public void setPacAmount(Double pacAmount) {
		this.pacAmount = pacAmount;
	}

	public Double getFacPercentage() {
		return facPercentage;
	}

	public void setFacPercentage(Double facPercentage) {
		this.facPercentage = facPercentage;
	}

	public Double getFacAmount() {
		return facAmount;
	}

	public void setFacAmount(Double facAmount) {
		this.facAmount = facAmount;
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

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	@Override
	public String toString() {
		return "PEBPolicyPrebidQuotationTOPBean [pbId=" + pbId + ", isSupplyFlag=" + isSupplyFlag + ", isSupplyService="
				+ isSupplyService + ", advancePercentage=" + advancePercentage + ", advanceAmount=" + advanceAmount
				+ ", downPercentage=" + downPercentage + ", downAmount=" + downAmount + ", progressPercentage="
				+ progressPercentage + ", progressAmount=" + progressAmount + ", pacPercentage=" + pacPercentage
				+ ", pacAmount=" + pacAmount + ", facPercentage=" + facPercentage + ", facAmount=" + facAmount
				+ ", downDuration=" + downDuration + ", progressDuration=" + progressDuration + ", pacDuration="
				+ pacDuration + ", facDuration=" + facDuration + ", downPremiumRate=" + downPremiumRate
				+ ", progressPremiumRate=" + progressPremiumRate + ", pacPremiumRate=" + pacPremiumRate
				+ ", facPremiumRate=" + facPremiumRate + ", versionNo=" + versionNo + "]";
	}

	

}
