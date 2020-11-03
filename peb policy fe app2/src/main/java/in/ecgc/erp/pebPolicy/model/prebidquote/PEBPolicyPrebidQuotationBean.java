package in.ecgc.erp.pebPolicy.model.prebidquote;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class PEBPolicyPrebidQuotationBean {

	private String inwardNumber;
	
	private String inwardItemNumber;
	
	private Long pbId;

	@NotBlank(message = "{exporterName.empty}")
	@Size(min=2, max=99)
	private String exporterName;
	
	private String exporterCode;
	
	@NotBlank(message = "{projectCountry.empty}")
	@Size(min=2, max=3)
	private String projectCountry;
	
	@NotBlank(message = "{projectCountryRating.empty}")
	private String projectCountryRating;
	
	@NotNull(message = "{contractValue.empty}")
	@Min(value=10)
	private Double contractValue;
	
	@NotNull(message = "{isSupplyServiceBoth.empty}")
	private String isSupplyServiceBoth;
	
	//private String isSupplyFlag;
	
	@NotNull(message = "{isPostBidFlag.empty}")
	private String isPostBidFlag;
	
	@NotNull(message = "{supplyValue.empty}")
	private Double supplyValue;
	
	@NotNull(message = "{serviceValue.empty}")
	private Double serviceValue;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date advancePaidDate;
	
	//@NotNull(message = "{coverValue.empty}")
	//private Double coverValue;

	@NotBlank(message = "{paymentCountry.empty}")
	@Size(min=2, max=3)
	private String paymentCountry;
	
	@NotBlank(message = "{paymentCountryRating.empty}")
	private String paymentCountryRating;
	
	@NotBlank(message = "{fundingArrangement.empty}")
	private String fundingArrangement;
	
	@NotBlank(message = "{fundingAgencyType.empty}")
	private String fundingAgencyType;
	
	@NotBlank(message = "{fundedBy.empty}")
	@Size(max=45)
	private String fundedBy;
	
	
	
	@NotBlank(message = "{buyerName.empty}")
	@Size(min=2,max=99)
	private String buyerName;

	@NotBlank(message = "{buyerType.empty}")
	@Size(min=2,max=45)
	private String buyerType;

	@NotNull(message = "{duration.empty}")
	@Min(value=1)
	@Max(value=180)
	private Integer duration;
	
	@NotNull(message = "{riskType.empty}")
	@Min(value=1)
	@Max(value=2)
	private Integer riskType;
	
	@NotNull(message = "{transactionType.empty}")
	@Min(value=1)
	@Max(value=2)	
	private Integer transactionType;

	@NotBlank(message = "{projectDetails.empty}")
	@Size(min=2,max=2499)
	private String projectDetails;
	
	@NotBlank(message = "{security.empty}")
	private String security;
	
	@NotBlank(message = "{isPreshipmentCoverRequired.empty}")
	private String isPreshipmentCoverRequired;
	
	//@NotNull(message = "{preshipmentCoverTenure.empty}")
	//@Max(value=180)
	private Integer preshipmentCoverTenure;
	
	private Double preShipmentPremiumRate;
	
	//@NotNull(message = "{moratoriumPeriod.empty}")
	private Integer supplyMoratoriumPeriod;
	
	//@NotNull(message = "{moratoriumNoOfInstallments.empty}")
	private Integer supplyMoratoriumNoOfInstallments;
	
	//@NotNull(message = "{moratoriumFreqOfInstallments.empty}")
	private Integer supplyMoratoriumFreqOfInstallments;
	
	private Integer serviceMoratoriumPeriod;
	
	//@NotNull(message = "{moratoriumNoOfInstallments.empty}")
	private Integer serviceMoratoriumNoOfInstallments;
	
	//@NotNull(message = "{moratoriumFreqOfInstallments.empty}")
	private Integer serviceMoratoriumFreqOfInstallments;
	
	private Integer versionNo;
	
	private Double premiumRate;
	
	private String createdBy;
	
	private Date createdOn;
	
	private String contractValueString; //This is a transient field used only for view purpose on listprebid screen ,this should not be considered during insertion in DB. 
	
	
	/*
	 * @NotNull
	 * 
	 * @Valid
	 */
	 
	 
	public String getContractValueString() {
		return contractValueString;
	}

	public void setContractValueString(String contractValueString) {
		this.contractValueString = contractValueString;
	}

	private List <PEBPolicyPrebidQuotationTOPBean> termsOfPayment;

	public PEBPolicyPrebidQuotationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getInwardNumber() {
		return inwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.inwardNumber = inwardNumber;
	}

	public String getInwardItemNumber() {
		return inwardItemNumber;
	}

	public void setInwardItemNumber(String inwardItemNumber) {
		this.inwardItemNumber = inwardItemNumber;
	}

	public Long getPbId() {
		return pbId;
	}

	public void setPbId(Long pbId) {
		this.pbId = pbId;
	}

	public String getExporterName() {
		return exporterName;
	}

	public void setExporterName(String exporterName) {
		this.exporterName = exporterName;
	}

	public String getExporterCode() {
		return exporterCode;
	}

	public void setExporterCode(String exporterCode) {
		this.exporterCode = exporterCode;
	}

	public String getProjectCountry() {
		return projectCountry;
	}

	public void setProjectCountry(String projectCountry) {
		this.projectCountry = projectCountry;
	}

	public String getProjectCountryRating() {
		return projectCountryRating;
	}

	public void setProjectCountryRating(String projectCountryRating) {
		this.projectCountryRating = projectCountryRating;
	}

	public Double getContractValue() {
		return contractValue;
	}

	public void setContractValue(Double contractValue) {
		this.contractValue = contractValue;
	}

	public String getIsSupplyServiceBoth() {
		return isSupplyServiceBoth;
	}

	public void setIsSupplyServiceBoth(String isSupplyServiceBoth) {
		this.isSupplyServiceBoth = isSupplyServiceBoth;
	}

	public String getIsPostBidFlag() {
		return isPostBidFlag;
	}

	public void setIsPostBidFlag(String isPostBidFlag) {
		this.isPostBidFlag = isPostBidFlag;
	}

	public Double getSupplyValue() {
		return supplyValue;
	}

	public void setSupplyValue(Double supplyValue) {
		this.supplyValue = supplyValue;
	}

	public Double getServiceValue() {
		return serviceValue;
	}

	public void setServiceValue(Double serviceValue) {
		this.serviceValue = serviceValue;
	}

	public Date getAdvancePaidDate() {
		return advancePaidDate;
	}

	public void setAdvancePaidDate(Date advancePaidDate) {
		this.advancePaidDate = advancePaidDate;
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

	public String getFundingAgencyType() {
		return fundingAgencyType;
	}

	public void setFundingAgencyType(String fundingAgencyType) {
		this.fundingAgencyType = fundingAgencyType;
	}

	public String getFundedBy() {
		return fundedBy;
	}

	public void setFundedBy(String fundedBy) {
		this.fundedBy = fundedBy;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(String buyerType) {
		this.buyerType = buyerType;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getRiskType() {
		return riskType;
	}

	public void setRiskType(Integer riskType) {
		this.riskType = riskType;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getIsPreshipmentCoverRequired() {
		return isPreshipmentCoverRequired;
	}

	public void setIsPreshipmentCoverRequired(String isPreshipmentCoverRequired) {
		this.isPreshipmentCoverRequired = isPreshipmentCoverRequired;
	}

	public Integer getPreshipmentCoverTenure() {
		return preshipmentCoverTenure;
	}

	public void setPreshipmentCoverTenure(Integer preshipmentCoverTenure) {
		this.preshipmentCoverTenure = preshipmentCoverTenure;
	}

	public Double getPreShipmentPremiumRate() {
		return preShipmentPremiumRate;
	}

	public void setPreShipmentPremiumRate(Double preShipmentPremiumRate) {
		this.preShipmentPremiumRate = preShipmentPremiumRate;
	}

	public Integer getSupplyMoratoriumPeriod() {
		return supplyMoratoriumPeriod;
	}

	public void setSupplyMoratoriumPeriod(Integer supplyMoratoriumPeriod) {
		this.supplyMoratoriumPeriod = supplyMoratoriumPeriod;
	}

	public Integer getSupplyMoratoriumNoOfInstallments() {
		return supplyMoratoriumNoOfInstallments;
	}

	public void setSupplyMoratoriumNoOfInstallments(Integer supplyMoratoriumNoOfInstallments) {
		this.supplyMoratoriumNoOfInstallments = supplyMoratoriumNoOfInstallments;
	}

	public Integer getSupplyMoratoriumFreqOfInstallments() {
		return supplyMoratoriumFreqOfInstallments;
	}

	public void setSupplyMoratoriumFreqOfInstallments(Integer supplyMoratoriumFreqOfInstallments) {
		this.supplyMoratoriumFreqOfInstallments = supplyMoratoriumFreqOfInstallments;
	}

	public Integer getServiceMoratoriumPeriod() {
		return serviceMoratoriumPeriod;
	}

	public void setServiceMoratoriumPeriod(Integer serviceMoratoriumPeriod) {
		this.serviceMoratoriumPeriod = serviceMoratoriumPeriod;
	}

	public Integer getServiceMoratoriumNoOfInstallments() {
		return serviceMoratoriumNoOfInstallments;
	}

	public void setServiceMoratoriumNoOfInstallments(Integer serviceMoratoriumNoOfInstallments) {
		this.serviceMoratoriumNoOfInstallments = serviceMoratoriumNoOfInstallments;
	}

	public Integer getServiceMoratoriumFreqOfInstallments() {
		return serviceMoratoriumFreqOfInstallments;
	}

	public void setServiceMoratoriumFreqOfInstallments(Integer serviceMoratoriumFreqOfInstallments) {
		this.serviceMoratoriumFreqOfInstallments = serviceMoratoriumFreqOfInstallments;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public Double getPremiumRate() {
		return premiumRate;
	}

	public void setPremiumRate(Double premiumRate) {
		this.premiumRate = premiumRate;
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

	public List<PEBPolicyPrebidQuotationTOPBean> getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(List<PEBPolicyPrebidQuotationTOPBean> termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}

	@Override
	public String toString() {
		return "PEBPolicyPrebidQuotationBean [inwardNumber=" + inwardNumber + ", inwardItemNumber=" + inwardItemNumber
				+ ", pbId=" + pbId + ", exporterName=" + exporterName + ", exporterCode=" + exporterCode
				+ ", projectCountry=" + projectCountry + ", projectCountryRating=" + projectCountryRating
				+ ", contractValue=" + contractValue + ", isSupplyServiceBoth=" + isSupplyServiceBoth
				+ ", isPostBidFlag=" + isPostBidFlag + ", supplyValue=" + supplyValue + ", serviceValue=" + serviceValue
				+ ", advancePaidDate=" + advancePaidDate + ", paymentCountry=" + paymentCountry
				+ ", paymentCountryRating=" + paymentCountryRating + ", fundingArrangement=" + fundingArrangement
				+ ", fundingAgencyType=" + fundingAgencyType + ", fundedBy=" + fundedBy + ", buyerName=" + buyerName
				+ ", buyerType=" + buyerType + ", duration=" + duration + ", riskType=" + riskType
				+ ", transactionType=" + transactionType + ", projectDetails=" + projectDetails + ", security="
				+ security + ", isPreshipmentCoverRequired=" + isPreshipmentCoverRequired + ", preshipmentCoverTenure="
				+ preshipmentCoverTenure + ", preShipmentPremiumRate=" + preShipmentPremiumRate
				+ ", supplyMoratoriumPeriod=" + supplyMoratoriumPeriod + ", supplyMoratoriumNoOfInstallments="
				+ supplyMoratoriumNoOfInstallments + ", supplyMoratoriumFreqOfInstallments="
				+ supplyMoratoriumFreqOfInstallments + ", serviceMoratoriumPeriod=" + serviceMoratoriumPeriod
				+ ", serviceMoratoriumNoOfInstallments=" + serviceMoratoriumNoOfInstallments
				+ ", serviceMoratoriumFreqOfInstallments=" + serviceMoratoriumFreqOfInstallments + ", versionNo="
				+ versionNo + ", premiumRate=" + premiumRate + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", termsOfPayment=" + termsOfPayment + "]";
	}

	
		
	}

	
	

	

