package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;


//@Component
public class PebPolicyEndorsementMstBean {
	
	private String inwardNumber;
	 
	private Integer inwardItemNumber;
	 
	private Long policyNumber;
	
	private Long endorsementId;
	@NotNull( message = "{NotNull.endorsementTypeId}")
	private Integer endorsementTypeId;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endorsementDate;
	
	private String codDecision;
	
	private String codDecisionDate;
	
	private String codRemarks;
	
	private String codRecommendation;
	
	private String ecgcDecision;
	
	private Date ecgcDecisionDate;
	
	private String ecgcRemarks;
	
	private String ecgcRecommendation;
	
	private String reason; 
	
	private Double exchangeRateLocal;
	
	private Double exchangeRateForeign;
	
	private Double exchangeRateThirdParty;
	
	private String isReassessed;
	
	private String isCEL;
	
	private String isML;
	
	private String projectCountryRating;
	
	private String paymentCountryRating;
	
	private String isExporterInSAL;
	 
	private String isBuyerInBSAL;
	
	private Date codMeetingDate;
 
	private String processingOfficerComments;
 
	private Date processingOfficerCommentedDate;
 
	private String observerComments;
 
	private Date observerCommentedDate;
 
	private String recommenderComments;
 
	private Date recommenderCommentedDate;
	
	private String createdBy;
	
	private Date createdOn;
	
	private Integer versionNo;
	
	private Date modifiedOn;
	
	private String modifiedBy;
	//private Integer codId;

	private List<String> documentCheckList;
	
	@Autowired
	private PebPolicyPremiumDetailsBean pebPolicyPremiumDetailsBean;

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

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Long getEndorsementId() {
		return endorsementId;
	}

	public void setEndorsementId(Long endorsementId) {
		this.endorsementId = endorsementId;
	}

	public Integer getEndorsementTypeId() {
		return endorsementTypeId;
	}

	public void setEndorsementTypeId(Integer endorsementTypeId) {
		this.endorsementTypeId = endorsementTypeId;
	}

	public Date getEndorsementDate() {
		return endorsementDate;
	}

	public void setEndorsementDate(Date endorsementDate) {
		this.endorsementDate = endorsementDate;
	}

	public String getCodDecision() {
		return codDecision;
	}

	public void setCodDecision(String codDecision) {
		this.codDecision = codDecision;
	}

	public String getCodDecisionDate() {
		return codDecisionDate;
	}

	public void setCodDecisionDate(String codDecisionDate) {
		this.codDecisionDate = codDecisionDate;
	}

	public String getCodRemarks() {
		return codRemarks;
	}

	public void setCodRemarks(String codRemarks) {
		this.codRemarks = codRemarks;
	}

	public String getCodRecommendation() {
		return codRecommendation;
	}

	public void setCodRecommendation(String codRecommendation) {
		this.codRecommendation = codRecommendation;
	}

	public String getEcgcDecision() {
		return ecgcDecision;
	}

	public void setEcgcDecision(String ecgcDecision) {
		this.ecgcDecision = ecgcDecision;
	}

	public Date getEcgcDecisionDate() {
		return ecgcDecisionDate;
	}

	public void setEcgcDecisionDate(Date ecgcDecisionDate) {
		this.ecgcDecisionDate = ecgcDecisionDate;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Double getExchangeRateLocal() {
		return exchangeRateLocal;
	}

	public void setExchangeRateLocal(Double exchangeRateLocal) {
		this.exchangeRateLocal = exchangeRateLocal;
	}

	public Double getExchangeRateForeign() {
		return exchangeRateForeign;
	}

	public void setExchangeRateForeign(Double exchangeRateForeign) {
		this.exchangeRateForeign = exchangeRateForeign;
	}

	public Double getExchangeRateThirdParty() {
		return exchangeRateThirdParty;
	}

	public void setExchangeRateThirdParty(Double exchangeRateThirdParty) {
		this.exchangeRateThirdParty = exchangeRateThirdParty;
	}

	public String getIsReassessed() {
		return isReassessed;
	}

	public void setIsReassessed(String isReassessed) {
		this.isReassessed = isReassessed;
	}

	public String getIsCEL() {
		return isCEL;
	}

	public void setIsCEL(String isCEL) {
		this.isCEL = isCEL;
	}

	
	public String getIsML() {
		return isML;
	}

	public void setIsML(String isML) {
		this.isML = isML;
	}

	public String getProjectCountryRating() {
		return projectCountryRating;
	}

	public void setProjectCountryRating(String projectCountryRating) {
		this.projectCountryRating = projectCountryRating;
	}

	public String getPaymentCountryRating() {
		return paymentCountryRating;
	}

	public void setPaymentCountryRating(String paymentCountryRating) {
		this.paymentCountryRating = paymentCountryRating;
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

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
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

	public List<String> getDocumentCheckList() {
		return documentCheckList;
	}

	public void setDocumentCheckList(List<String> documentCheckList) {
		this.documentCheckList = documentCheckList;
	}

	public PebPolicyPremiumDetailsBean getPebPolicyPremiumDetailsBean() {
		return pebPolicyPremiumDetailsBean;
	}

	public void setPebPolicyPremiumDetailsBean(PebPolicyPremiumDetailsBean pebPolicyPremiumDetailsBean) {
		this.pebPolicyPremiumDetailsBean = pebPolicyPremiumDetailsBean;
	}

	@Override
	public String toString() {
		return "PebPolicyEndorsementMstBean [inwardNumber=" + inwardNumber + ", inwardItemNumber=" + inwardItemNumber
				+ ", policyNumber=" + policyNumber + ", endorsementId=" + endorsementId + ", endorsementTypeId="
				+ endorsementTypeId + ", endorsementDate=" + endorsementDate + ", codDecision=" + codDecision
				+ ", codDecisionDate=" + codDecisionDate + ", codRemarks=" + codRemarks + ", codRecommendation="
				+ codRecommendation + ", ecgcDecision=" + ecgcDecision + ", ecgcDecisionDate=" + ecgcDecisionDate
				+ ", ecgcRemarks=" + ecgcRemarks + ", ecgcRecommendation=" + ecgcRecommendation + ", reason=" + reason
				+ ", exchangeRateLocal=" + exchangeRateLocal + ", exchangeRateForeign=" + exchangeRateForeign
				+ ", exchangeRateThirdParty=" + exchangeRateThirdParty + ", isReassessed=" + isReassessed + ", isCEL="
				+ isCEL + ", projectCountryRating=" + projectCountryRating + ", paymentCountryRating="
				+ paymentCountryRating + ", isExporterInSAL=" + isExporterInSAL + ", isBuyerInBSAL=" + isBuyerInBSAL
				+ ", codMeetingDate=" + codMeetingDate + ", processingOfficerComments=" + processingOfficerComments
				+ ", processingOfficerCommentedDate=" + processingOfficerCommentedDate + ", observerComments="
				+ observerComments + ", observerCommentedDate=" + observerCommentedDate + ", recommenderComments="
				+ recommenderComments + ", recommenderCommentedDate=" + recommenderCommentedDate + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", versionNo=" + versionNo + ", modifiedOn=" + modifiedOn
				+ ", modifiedBy=" + modifiedBy + ", documentCheckList=" + documentCheckList
				+ ", pebPolicyPremiumDetailsBean=" + pebPolicyPremiumDetailsBean + "]";
	}

	
	
}
