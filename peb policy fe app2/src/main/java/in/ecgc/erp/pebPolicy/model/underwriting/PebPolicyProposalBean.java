package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;
import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalBasicInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalDecisionInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalMiscellaneousInfo;
import in.ecgc.erp.pebPolicy.exceptions.ProposalScrutinyInfo;
import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;
//import in.ecgc.erp.pebPolicy.model.DummyPolicyDetailsBean;
import lombok.Data;


//@GroupSequence({BasicInfo.class, ScrutinyInfo.class})
public class PebPolicyProposalBean {
	
 private Long proposalId;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.proposalDate}")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date proposalDate;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.projectId}")
	 private Long projectId;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.productId}")
	 private String productId;
	 
	 @NotNull(groups = ProposalDecisionInfo.class, message = "{NotNull.codDecisionDate}")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date codDecisionDate;
	 
	 @NotEmpty(groups = ProposalDecisionInfo.class, message = "{NotEmpty.codDecision}")
	 private String codDecision;
	 private String codRemarks;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date codMeetingDate;
	 
	 private String processingOfficerComments;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date processingOfficerCommentedDate;
	 
	 private String observerComments;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date observerCommentedDate;
	 
	 private String recommenderComments;
	 
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date recommenderCommentedDate;
	 
	 
	 private String codRecommendation;
	 
	 @NotNull(groups =ProposalDecisionInfo.class, message = "{NotNull.ecgcDecisionDate}")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date ecgcDecisionDate;
	 
	 @NotEmpty(groups = ProposalDecisionInfo.class, message = "{NotEmpty.ecgcDecision}")
	 private String ecgcDecision;
	 private String ecgcRemarks;
	 private String ecgcRecommendation;
	 private String remarks;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.contractValue}")
	 //@Min(value = 1 , message = "{min.contractValue}")
	 private Double contractValue;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.bankName}")
	 private String bankName;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.processingFeePaidDate}")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date processingFeePaidDate;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.processingFeePaidAmount}")
	 //@Min(value = 0 , message = "{min.processingFeePaidAmount}")
	 private Double processingFeePaidAmount;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.proposalStatus}")
	 private String proposalStatus;
	 private Date approvedDate;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.modeOfPayment}")
	 private String modeOfPayment;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.instrumentDate}")
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date instrumentDate;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.instrumentNumber}")
	 private String instrumentNumber;
	 
	 @NotNull(groups = ProposalBasicInfo.class, message = "{NotNull.instrumentAmount}")
	 //@Min(value = 0 , message = "{min.instrumentAmount}")
	 private Double instrumentAmount;
	 
	 @NotEmpty(groups = ProposalBasicInfo.class, message = "{NotEmpty.receiptNumber}")
	 private String receiptNumber;
	 private String createdBy;
	 private Date createdOn;
	 private String tags;
	 
	// @NotNull(groups = BasicInfo.class, message = "{NotNull.neiaSharePercentage}")
	// @Min(value = 50, message = "{min.neiaSharePercentage}")
	 //private Double neiaSharePercentage;
	 
	 private String inwardNumber;
	 private Integer inwardItemNumber;
	 private Date modifiedOn;
	 private String modifiedBy;
	 private Integer version;
	 
	 @NotEmpty(groups = ProposalScrutinyInfo.class, message = "{NotEmpty.isExporterInSAL}")
	 private String isExporterInSAL;
	 @NotEmpty(groups = ProposalScrutinyInfo.class, message = "{NotEmpty.isBuyerInBSAL}")
	 private String isBuyerInBSAL;
	 private Double contractValueLocal;
	 private Double contractValueForeign;
	 private Double contractValueThirdParty;
	 private List<DummyPolicyDetailsBean> dummyPolicyDetailsBean;
	 
	 //public List<PebPolicyProposalDocumentCheckListBean> documentCheckList;
	 
	 @Size(groups = {ProposalScrutinyInfo.class},min = 1,message = "Please select atleast one")
	 public List<String> documentCheckList;
	
	 @NotNull(groups = ProposalMiscellaneousInfo.class, message = "{NotNull.totalPremiumAmount}")
	 private Double totalPremiumAmount;
	 
	 @NotNull(groups = ProposalMiscellaneousInfo.class, message = "{NotNull.discountScore}")
	 private Double discountScore;
	 
	 @NotNull(groups = ProposalMiscellaneousInfo.class, message = "{NotNull.discountPercentage}")
	 private Double discountPercentage;

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Date getProposalDate() {
		return proposalDate;
	}

	public void setProposalDate(Date proposalDate) {
		this.proposalDate = proposalDate;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCodDecisionDate() {
		return codDecisionDate;
	}

	public void setCodDecisionDate(Date codDecisionDate) {
		this.codDecisionDate = codDecisionDate;
	}

	public String getCodDecision() {
		return codDecision;
	}

	public void setCodDecision(String codDecision) {
		this.codDecision = codDecision;
	}

	public String getCodRemarks() {
		return codRemarks;
	}

	public void setCodRemarks(String codRemarks) {
		this.codRemarks = codRemarks;
	}

	public Date getCodMeetingDate() {
		return codMeetingDate;
	}

	public void setCodMeetingDate(Date codMeetingDate) {
		this.codMeetingDate = codMeetingDate;
	}

	public String getProcessingOfficerComments() {
		return processingOfficerComments;
	}

	public void setProcessingOfficerComments(String processingOfficerComments) {
		this.processingOfficerComments = processingOfficerComments;
	}

	public Date getProcessingOfficerCommentedDate() {
		return processingOfficerCommentedDate;
	}

	public void setProcessingOfficerCommentedDate(Date processingOfficerCommentedDate) {
		this.processingOfficerCommentedDate = processingOfficerCommentedDate;
	}

	public String getObserverComments() {
		return observerComments;
	}

	public void setObserverComments(String observerComments) {
		this.observerComments = observerComments;
	}

	public Date getObserverCommentedDate() {
		return observerCommentedDate;
	}

	public void setObserverCommentedDate(Date observerCommentedDate) {
		this.observerCommentedDate = observerCommentedDate;
	}

	public String getRecommenderComments() {
		return recommenderComments;
	}

	public void setRecommenderComments(String recommenderComments) {
		this.recommenderComments = recommenderComments;
	}

	public Date getRecommenderCommentedDate() {
		return recommenderCommentedDate;
	}

	public void setRecommenderCommentedDate(Date recommenderCommentedDate) {
		this.recommenderCommentedDate = recommenderCommentedDate;
	}

	public String getCodRecommendation() {
		return codRecommendation;
	}

	public void setCodRecommendation(String codRecommendation) {
		this.codRecommendation = codRecommendation;
	}

	public Date getEcgcDecisionDate() {
		return ecgcDecisionDate;
	}

	public void setEcgcDecisionDate(Date ecgcDecisionDate) {
		this.ecgcDecisionDate = ecgcDecisionDate;
	}

	public String getEcgcDecision() {
		return ecgcDecision;
	}

	public void setEcgcDecision(String ecgcDecision) {
		this.ecgcDecision = ecgcDecision;
	}

	public String getEcgcRemarks() {
		return ecgcRemarks;
	}

	public void setEcgcRemarks(String ecgcRemarks) {
		this.ecgcRemarks = ecgcRemarks;
	}

	public String getEcgcRecommendation() {
		return ecgcRecommendation;
	}

	public void setEcgcRecommendation(String ecgcRecommendation) {
		this.ecgcRecommendation = ecgcRecommendation;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getContractValue() {
		return contractValue;
	}

	public void setContractValue(Double contractValue) {
		this.contractValue = contractValue;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getProcessingFeePaidDate() {
		return processingFeePaidDate;
	}

	public void setProcessingFeePaidDate(Date processingFeePaidDate) {
		this.processingFeePaidDate = processingFeePaidDate;
	}

	public Double getProcessingFeePaidAmount() {
		return processingFeePaidAmount;
	}

	public void setProcessingFeePaidAmount(Double processingFeePaidAmount) {
		this.processingFeePaidAmount = processingFeePaidAmount;
	}

	public String getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Date getInstrumentDate() {
		return instrumentDate;
	}

	public void setInstrumentDate(Date instrumentDate) {
		this.instrumentDate = instrumentDate;
	}

	public String getInstrumentNumber() {
		return instrumentNumber;
	}

	public void setInstrumentNumber(String instrumentNumber) {
		this.instrumentNumber = instrumentNumber;
	}

	public Double getInstrumentAmount() {
		return instrumentAmount;
	}

	public void setInstrumentAmount(Double instrumentAmount) {
		this.instrumentAmount = instrumentAmount;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getInwardNumber() {
		return inwardNumber;
	}

	public void setInwardNumber(String inwardNumber) {
		this.inwardNumber = inwardNumber;
	}

	public Integer getInwardItemNumber() {
		return inwardItemNumber;
	}

	public void setInwardItemNumber(Integer inwardItemNumber) {
		this.inwardItemNumber = inwardItemNumber;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getIsExporterInSAL() {
		return isExporterInSAL;
	}

	public void setIsExporterInSAL(String isExporterInSAL) {
		this.isExporterInSAL = isExporterInSAL;
	}

	public String getIsBuyerInBSAL() {
		return isBuyerInBSAL;
	}

	public void setIsBuyerInBSAL(String isBuyerInBSAL) {
		this.isBuyerInBSAL = isBuyerInBSAL;
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

	public List<DummyPolicyDetailsBean> getDummyPolicyDetailsBean() {
		return dummyPolicyDetailsBean;
	}

	public void setDummyPolicyDetailsBean(List<DummyPolicyDetailsBean> dummyPolicyDetailsBean) {
		this.dummyPolicyDetailsBean = dummyPolicyDetailsBean;
	}

	public List<String> getDocumentCheckList() {
		return documentCheckList;
	}

	public void setDocumentCheckList(List<String> documentCheckList) {
		this.documentCheckList = documentCheckList;
	}

	public Double getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	public void setTotalPremiumAmount(Double totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	public Double getDiscountScore() {
		return discountScore;
	}

	public void setDiscountScore(Double discountScore) {
		this.discountScore = discountScore;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	@Override
	public String toString() {
		return "PebPolicyProposalBean [proposalId=" + proposalId + ", proposalDate=" + proposalDate + ", projectId="
				+ projectId + ", productId=" + productId + ", codDecisionDate=" + codDecisionDate + ", codDecision="
				+ codDecision + ", codRemarks=" + codRemarks + ", codMeetingDate=" + codMeetingDate
				+ ", processingOfficerComments=" + processingOfficerComments + ", processingOfficerCommentedDate="
				+ processingOfficerCommentedDate + ", observerComments=" + observerComments + ", observerCommentedDate="
				+ observerCommentedDate + ", recommenderComments=" + recommenderComments + ", recommenderCommentedDate="
				+ recommenderCommentedDate + ", codRecommendation=" + codRecommendation + ", ecgcDecisionDate="
				+ ecgcDecisionDate + ", ecgcDecision=" + ecgcDecision + ", ecgcRemarks=" + ecgcRemarks
				+ ", ecgcRecommendation=" + ecgcRecommendation + ", remarks=" + remarks + ", contractValue="
				+ contractValue + ", bankName=" + bankName + ", processingFeePaidDate=" + processingFeePaidDate
				+ ", processingFeePaidAmount=" + processingFeePaidAmount + ", proposalStatus=" + proposalStatus
				+ ", approvedDate=" + approvedDate + ", modeOfPayment=" + modeOfPayment + ", instrumentDate="
				+ instrumentDate + ", instrumentNumber=" + instrumentNumber + ", instrumentAmount=" + instrumentAmount
				+ ", receiptNumber=" + receiptNumber + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", tags=" + tags + ", inwardNumber=" + inwardNumber + ", inwardItemNumber=" + inwardItemNumber
				+ ", modifiedOn=" + modifiedOn + ", modifiedBy=" + modifiedBy + ", version=" + version
				+ ", isExporterInSAL=" + isExporterInSAL + ", isBuyerInBSAL=" + isBuyerInBSAL + ", contractValueLocal="
				+ contractValueLocal + ", contractValueForeign=" + contractValueForeign + ", contractValueThirdParty="
				+ contractValueThirdParty + ", dummyPolicyDetailsBean=" + dummyPolicyDetailsBean
				+ ", documentCheckList=" + documentCheckList + ", totalPremiumAmount=" + totalPremiumAmount
				+ ", discountScore=" + discountScore + ", discountPercentage=" + discountPercentage + "]";
	}
	 
	 
		

	
}
