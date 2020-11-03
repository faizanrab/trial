package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
//import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;





public class DummyPolicyDetailsBean {

	private Long dummyPolicyNumber;
	
	private Long proposalId;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{postShipmentStartDate.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postShipmentStartDate;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{postShipmentEndDate.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date postShipmentEndDate;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentDiscount.empty}")
	private Double preShipmentDiscount;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{transactionTypeId.empty}")
	private Integer transactionTypeId;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{riskTypeId.empty}")
	private Integer riskTypeId;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{paymentCountry.empty}")
	private String paymentCountry;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{paymentCountryRating.empty}")
	private String paymentCountryRating;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{fundingArrangement.empty}")
	private String fundingArrangement;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{fundingAgencyName.empty}")
	private String fundingAgencyName;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{coverValue.empty}")
	private Double coverValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{coverPercentage.empty}")
	private Double coverPercentage;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{tags.empty}")
	private String tags;
	
	private String createdBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{isInstallmentFacility.empty}")
	private String isInstallmentFacility;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{maximumLiability.empty}")
	private Double maximumLiability;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{moratoriumPeriod.empty}")
	private Integer moratoriumPeriod;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{moratoriumNumberOfInstallment.empty}")
	private Integer moratoriumNumberOfInstallment;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{moratoriumFreqOfInstallment.empty}")
	private Integer moratoriumFreqOfInstallment;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{advancePaidAmount.empty}")
	private Double advancePaidAmount;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{advanceWeightage.empty}")
	private Double advanceWeightage;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{advancePaidDate.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date advancePaidDate;
	
	//@NotBlank(groups = AssessmentInfo.class, message = "{isPreshipmentRider.empty}")
	private String isPreshipmentRider;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentStartDate.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date preShipmentStartDate;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentEndDate.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date preShipmentEndDate;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentContractValueLocal.empty}")
	private Double preShipmentContractValueLocal;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentContractValueThirdParty.empty}")
	private Double preShipmentContractValueThirdParty;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentContractValueForeign.empty}")
	private Double preShipmentContractValueForeign;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentPremiumRate.empty}")
	private Double preShipmentPremiumRate;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{preShipmentPremiumAmount.empty}")
	private Double preShipmentPremiumAmount;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{postShipmentPremiumRate.empty}")
	private Double postShipmentPremiumRate;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{postShipmentPremiumAmount.empty}")
	private Double postShipmentPremiumAmount;
	
	@NotBlank(groups = ProposalAssessmentInfo.class, message = "{isCrossCountry.empty}")
	private String isCrossCountry;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{isSupplyOrServiceOrBoth.empty}")
	private Integer isSupplyOrServiceOrBoth;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{neiaSharePercentage.empty}")
	private Double neiaSharePercentage;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{loadingPercentage.empty}")
	private Double loadingPercentage;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{contractValueLocal.empty}")
	private Double contractValueLocal;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{contractValueForeign.empty}")
	private Double contractValueForeign;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{contractValueThirdParty.empty}")
	private Double contractValueThirdParty;
	
	private Double totalPremiumAmount;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{totalSupplyValue.empty}")
	private Double totalSupplyValue;
	
	//@NotNull(groups = AssessmentInfo.class)
	@Valid
	private PebPolicyDummyTermsOfPaymentTenureDetailsBean paymentTenureDetails;
	
	//@NotNull(groups = AssessmentInfo.class)
	@Valid
	private PebPolicyDummyTermsOfPaymentWeightageDetailsBean paymentWeightageDetails;
	
	//@NotNull(groups = AssessmentInfo.class)
	@Valid
	private List<PebPolicyDeferredPaymentScheduleDetailsBean> policyDeferredPaymentScheduleDetails;
	
	//@NotNull(groups = AssessmentInfo.class)
	@Valid
	private PebPolicyDummyCurrencyDetailsBean pebPolicyDummyCurrencyDetails;

	//@NotBlank(groups = AssessmentInfo.class, message = "{remarks.empty}")
	private String remarks;

	public Long getDummyPolicyNumber() {
		return dummyPolicyNumber;
	}

	public void setDummyPolicyNumber(Long dummyPolicyNumber) {
		this.dummyPolicyNumber = dummyPolicyNumber;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Date getPostShipmentStartDate() {
		return postShipmentStartDate;
	}

	public void setPostShipmentStartDate(Date postShipmentStartDate) {
		this.postShipmentStartDate = postShipmentStartDate;
	}

	public Date getPostShipmentEndDate() {
		return postShipmentEndDate;
	}

	public void setPostShipmentEndDate(Date postShipmentEndDate) {
		this.postShipmentEndDate = postShipmentEndDate;
	}

	public Double getPreShipmentDiscount() {
		return preShipmentDiscount;
	}

	public void setPreShipmentDiscount(Double preShipmentDiscount) {
		this.preShipmentDiscount = preShipmentDiscount;
	}

	public Integer getTransactionTypeId() {
		return transactionTypeId;
	}

	public void setTransactionTypeId(Integer transactionTypeId) {
		this.transactionTypeId = transactionTypeId;
	}

	public Integer getRiskTypeId() {
		return riskTypeId;
	}

	public void setRiskTypeId(Integer riskTypeId) {
		this.riskTypeId = riskTypeId;
	}

	public String getPaymentCountry() {
		return paymentCountry;
	}

	public void setPaymentCountry(String paymentCountry) {
		this.paymentCountry = paymentCountry;
	}

	public String getPaymentCountryRating() {
		return paymentCountryRating;
	}

	public void setPaymentCountryRating(String paymentCountryRating) {
		this.paymentCountryRating = paymentCountryRating;
	}

	public String getFundingArrangement() {
		return fundingArrangement;
	}

	public void setFundingArrangement(String fundingArrangement) {
		this.fundingArrangement = fundingArrangement;
	}

	public String getFundingAgencyName() {
		return fundingAgencyName;
	}

	public void setFundingAgencyName(String fundingAgencyName) {
		this.fundingAgencyName = fundingAgencyName;
	}

	public Double getCoverValue() {
		return coverValue;
	}

	public void setCoverValue(Double coverValue) {
		this.coverValue = coverValue;
	}

	public Double getCoverPercentage() {
		return coverPercentage;
	}

	public void setCoverPercentage(Double coverPercentage) {
		this.coverPercentage = coverPercentage;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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

	public String getIsInstallmentFacility() {
		return isInstallmentFacility;
	}

	public void setIsInstallmentFacility(String isInstallmentFacility) {
		this.isInstallmentFacility = isInstallmentFacility;
	}

	public Double getMaximumLiability() {
		return maximumLiability;
	}

	public void setMaximumLiability(Double maximumLiability) {
		this.maximumLiability = maximumLiability;
	}

	public Integer getMoratoriumPeriod() {
		return moratoriumPeriod;
	}

	public void setMoratoriumPeriod(Integer moratoriumPeriod) {
		this.moratoriumPeriod = moratoriumPeriod;
	}

	public Integer getMoratoriumNumberOfInstallment() {
		return moratoriumNumberOfInstallment;
	}

	public void setMoratoriumNumberOfInstallment(Integer moratoriumNumberOfInstallment) {
		this.moratoriumNumberOfInstallment = moratoriumNumberOfInstallment;
	}

	public Integer getMoratoriumFreqOfInstallment() {
		return moratoriumFreqOfInstallment;
	}

	public void setMoratoriumFreqOfInstallment(Integer moratoriumFreqOfInstallment) {
		this.moratoriumFreqOfInstallment = moratoriumFreqOfInstallment;
	}

	public Double getAdvancePaidAmount() {
		return advancePaidAmount;
	}

	public void setAdvancePaidAmount(Double advancePaidAmount) {
		this.advancePaidAmount = advancePaidAmount;
	}

	public Double getAdvanceWeightage() {
		return advanceWeightage;
	}

	public void setAdvanceWeightage(Double advanceWeightage) {
		this.advanceWeightage = advanceWeightage;
	}

	public Date getAdvancePaidDate() {
		return advancePaidDate;
	}

	public void setAdvancePaidDate(Date advancePaidDate) {
		this.advancePaidDate = advancePaidDate;
	}

	public String getIsPreshipmentRider() {
		return isPreshipmentRider;
	}

	public void setIsPreshipmentRider(String isPreshipmentRider) {
		this.isPreshipmentRider = isPreshipmentRider;
	}

	public Date getPreShipmentStartDate() {
		return preShipmentStartDate;
	}

	public void setPreShipmentStartDate(Date preShipmentStartDate) {
		this.preShipmentStartDate = preShipmentStartDate;
	}

	public Date getPreShipmentEndDate() {
		return preShipmentEndDate;
	}

	public void setPreShipmentEndDate(Date preShipmentEndDate) {
		this.preShipmentEndDate = preShipmentEndDate;
	}

	public Double getPreShipmentContractValueLocal() {
		return preShipmentContractValueLocal;
	}

	public void setPreShipmentContractValueLocal(Double preShipmentContractValueLocal) {
		this.preShipmentContractValueLocal = preShipmentContractValueLocal;
	}

	public Double getPreShipmentContractValueThirdParty() {
		return preShipmentContractValueThirdParty;
	}

	public void setPreShipmentContractValueThirdParty(Double preShipmentContractValueThirdParty) {
		this.preShipmentContractValueThirdParty = preShipmentContractValueThirdParty;
	}

	public Double getPreShipmentContractValueForeign() {
		return preShipmentContractValueForeign;
	}

	public void setPreShipmentContractValueForeign(Double preShipmentContractValueForeign) {
		this.preShipmentContractValueForeign = preShipmentContractValueForeign;
	}

	public Double getPreShipmentPremiumRate() {
		return preShipmentPremiumRate;
	}

	public void setPreShipmentPremiumRate(Double preShipmentPremiumRate) {
		this.preShipmentPremiumRate = preShipmentPremiumRate;
	}

	public Double getPreShipmentPremiumAmount() {
		return preShipmentPremiumAmount;
	}

	public void setPreShipmentPremiumAmount(Double preShipmentPremiumAmount) {
		this.preShipmentPremiumAmount = preShipmentPremiumAmount;
	}

	public Double getPostShipmentPremiumRate() {
		return postShipmentPremiumRate;
	}

	public void setPostShipmentPremiumRate(Double postShipmentPremiumRate) {
		this.postShipmentPremiumRate = postShipmentPremiumRate;
	}

	public Double getPostShipmentPremiumAmount() {
		return postShipmentPremiumAmount;
	}

	public void setPostShipmentPremiumAmount(Double postShipmentPremiumAmount) {
		this.postShipmentPremiumAmount = postShipmentPremiumAmount;
	}

	public String getIsCrossCountry() {
		return isCrossCountry;
	}

	public void setIsCrossCountry(String isCrossCountry) {
		this.isCrossCountry = isCrossCountry;
	}

	public Integer getIsSupplyOrServiceOrBoth() {
		return isSupplyOrServiceOrBoth;
	}

	public void setIsSupplyOrServiceOrBoth(Integer isSupplyOrServiceOrBoth) {
		this.isSupplyOrServiceOrBoth = isSupplyOrServiceOrBoth;
	}

	public Double getNeiaSharePercentage() {
		return neiaSharePercentage;
	}

	public void setNeiaSharePercentage(Double neiaSharePercentage) {
		this.neiaSharePercentage = neiaSharePercentage;
	}

	public Double getLoadingPercentage() {
		return loadingPercentage;
	}

	public void setLoadingPercentage(Double loadingPercentage) {
		this.loadingPercentage = loadingPercentage;
	}

	public Double getContractValueLocal() {
		return contractValueLocal;
	}

	public void setContractValueLocal(Double contractValueLocal) {
		this.contractValueLocal = contractValueLocal;
	}

	public Double getContractValueForeign() {
		return contractValueForeign;
	}

	public void setContractValueForeign(Double contractValueForeign) {
		this.contractValueForeign = contractValueForeign;
	}

	public Double getContractValueThirdParty() {
		return contractValueThirdParty;
	}

	public void setContractValueThirdParty(Double contractValueThirdParty) {
		this.contractValueThirdParty = contractValueThirdParty;
	}

	public Double getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	public void setTotalPremiumAmount(Double totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	public Double getTotalSupplyValue() {
		return totalSupplyValue;
	}

	public void setTotalSupplyValue(Double totalSupplyValue) {
		this.totalSupplyValue = totalSupplyValue;
	}

	public PebPolicyDummyTermsOfPaymentTenureDetailsBean getPaymentTenureDetails() {
		return paymentTenureDetails;
	}

	public void setPaymentTenureDetails(PebPolicyDummyTermsOfPaymentTenureDetailsBean paymentTenureDetails) {
		this.paymentTenureDetails = paymentTenureDetails;
	}

	public PebPolicyDummyTermsOfPaymentWeightageDetailsBean getPaymentWeightageDetails() {
		return paymentWeightageDetails;
	}

	public void setPaymentWeightageDetails(PebPolicyDummyTermsOfPaymentWeightageDetailsBean paymentWeightageDetails) {
		this.paymentWeightageDetails = paymentWeightageDetails;
	}

	public List<PebPolicyDeferredPaymentScheduleDetailsBean> getPolicyDeferredPaymentScheduleDetails() {
		return policyDeferredPaymentScheduleDetails;
	}

	public void setPolicyDeferredPaymentScheduleDetails(
			List<PebPolicyDeferredPaymentScheduleDetailsBean> policyDeferredPaymentScheduleDetails) {
		this.policyDeferredPaymentScheduleDetails = policyDeferredPaymentScheduleDetails;
	}

	public PebPolicyDummyCurrencyDetailsBean getPebPolicyDummyCurrencyDetails() {
		return pebPolicyDummyCurrencyDetails;
	}

	public void setPebPolicyDummyCurrencyDetails(PebPolicyDummyCurrencyDetailsBean pebPolicyDummyCurrencyDetails) {
		this.pebPolicyDummyCurrencyDetails = pebPolicyDummyCurrencyDetails;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "DummyPolicyDetailsBean [dummyPolicyNumber=" + dummyPolicyNumber + ", proposalId=" + proposalId
				+ ", postShipmentStartDate=" + postShipmentStartDate + ", postShipmentEndDate=" + postShipmentEndDate
				+ ", preShipmentDiscount=" + preShipmentDiscount + ", transactionTypeId=" + transactionTypeId
				+ ", riskTypeId=" + riskTypeId + ", paymentCountry=" + paymentCountry + ", paymentCountryRating="
				+ paymentCountryRating + ", fundingArrangement=" + fundingArrangement + ", fundingAgencyName="
				+ fundingAgencyName + ", coverValue=" + coverValue + ", coverPercentage=" + coverPercentage + ", tags="
				+ tags + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", isInstallmentFacility="
				+ isInstallmentFacility + ", maximumLiability=" + maximumLiability + ", moratoriumPeriod="
				+ moratoriumPeriod + ", moratoriumNumberOfInstallment=" + moratoriumNumberOfInstallment
				+ ", moratoriumFreqOfInstallment=" + moratoriumFreqOfInstallment + ", advancePaidAmount="
				+ advancePaidAmount + ", advanceWeightage=" + advanceWeightage + ", advancePaidDate=" + advancePaidDate
				+ ", isPreshipmentRider=" + isPreshipmentRider + ", preShipmentStartDate=" + preShipmentStartDate
				+ ", preShipmentEndDate=" + preShipmentEndDate + ", preShipmentContractValueLocal="
				+ preShipmentContractValueLocal + ", preShipmentContractValueThirdParty="
				+ preShipmentContractValueThirdParty + ", preShipmentContractValueForeign="
				+ preShipmentContractValueForeign + ", preShipmentPremiumRate=" + preShipmentPremiumRate
				+ ", preShipmentPremiumAmount=" + preShipmentPremiumAmount + ", postShipmentPremiumRate="
				+ postShipmentPremiumRate + ", postShipmentPremiumAmount=" + postShipmentPremiumAmount
				+ ", isCrossCountry=" + isCrossCountry + ", isSupplyOrServiceOrBoth=" + isSupplyOrServiceOrBoth
				+ ", neiaSharePercentage=" + neiaSharePercentage + ", loadingPercentage=" + loadingPercentage
				+ ", contractValueLocal=" + contractValueLocal + ", contractValueForeign=" + contractValueForeign
				+ ", contractValueThirdParty=" + contractValueThirdParty + ", totalPremiumAmount=" + totalPremiumAmount
				+ ", totalSupplyValue=" + totalSupplyValue + ", paymentTenureDetails=" + paymentTenureDetails
				+ ", paymentWeightageDetails=" + paymentWeightageDetails + ", policyDeferredPaymentScheduleDetails="
				+ policyDeferredPaymentScheduleDetails + ", pebPolicyDummyCurrencyDetails="
				+ pebPolicyDummyCurrencyDetails + ", remarks=" + remarks + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private Double discountScore;
//	private Double discountPercentage;
	
	
//	 @AssertTrue(message="Please provide all fields of cross country.")
//	  private boolean isNotNullForCrossCountry() {
//	    if(this.isCrossCountry.equals("yes")) {
//	    	if(this.paymentCountry == null || this.paymentCountryRating == null || 
//	    			this.fundingAgencyName ==null || this.fundingArrangement == null) {
//	    		return false;
//	    	}
//	    }else {
//	    	return true;
//	    }
//	    return true;
//	  }
}
