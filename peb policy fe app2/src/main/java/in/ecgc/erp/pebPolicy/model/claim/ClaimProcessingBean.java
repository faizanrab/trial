package in.ecgc.erp.pebPolicy.model.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.exceptions.ClaimDecisionInfo;




public class ClaimProcessingBean {
	
	private Long claimNo;
	private Long crId;
	
	@NotEmpty(groups = ClaimDecisionInfo.class, message = "Please Select ECGC Decision")
	private String ecgcDecision;
	
	@NotEmpty(groups = ClaimDecisionInfo.class, message = "Please Enter COD Decision")
	private String codDecision;
	
	
	private String ecgcRemarks;
	
	@NotEmpty(groups = ClaimDecisionInfo.class, message = "Please Enter ECGC Recommendation")
	private String ecgcRecommendation;
	
	@NotNull(groups = ClaimDecisionInfo.class, message = "Please Enter ECGC Decision Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ecgcDecisionDate;
	
	@NotNull(groups = ClaimDecisionInfo.class, message = "Please Enter COD Decision Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date codDecisionDate;
	private String codRemarks;
	
	@NotEmpty(groups = ClaimDecisionInfo.class, message = "Please Enter COD Recommendation")
	private String codRecommendation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date codMeetingDate;
	
	@NotEmpty(groups = ClaimDecisionInfo.class, message = "Please Enter Comments")
	private String processingOfficerComments;
	 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date processingOfficerCommentedDate;
	 
	 private String observerComments;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date observerCommentedDate;
	 
	 private String recommenderComments;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date recommenderCommentedDate;
	 
	 private String iaComments;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date iaCommentedDate;
	
	private BigDecimal claimAmountApproved;
	private String remarks;
	private String isReassessed;
	private String processedBy;
	private Date processedOn;
	
	private Integer version;
	
	private List<ClaimInvoiceProcessingBean> claimInvoiceProcessingBean;

	public Long getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}

	public Long getCrId() {
		return crId;
	}

	public void setCrId(Long crId) {
		this.crId = crId;
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

	public Date getEcgcDecisionDate() {
		return ecgcDecisionDate;
	}

	public void setEcgcDecisionDate(Date ecgcDecisionDate) {
		this.ecgcDecisionDate = ecgcDecisionDate;
	}

	public Date getCodDecisionDate() {
		return codDecisionDate;
	}

	public void setCodDecisionDate(Date codDecisionDate) {
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

	public Date getCodMeetingDate() {
		return codMeetingDate;
	}

	public void setCodMeetingDate(Date codMeetingDate) {
		this.codMeetingDate = codMeetingDate;
	}

	public BigDecimal getClaimAmountApproved() {
		return claimAmountApproved;
	}

	public void setClaimAmountApproved(BigDecimal claimAmountApproved) {
		this.claimAmountApproved = claimAmountApproved;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getIsReassessed() {
		return isReassessed;
	}

	public void setIsReassessed(String isReassessed) {
		this.isReassessed = isReassessed;
	}

	public String getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedOn() {
		return processedOn;
	}

	public void setProcessedOn(Date processedOn) {
		this.processedOn = processedOn;
	}
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<ClaimInvoiceProcessingBean> getClaimInvoiceProcessingBean() {
		return claimInvoiceProcessingBean;
	}

	public void setClaimInvoiceProcessingBean(List<ClaimInvoiceProcessingBean> claimInvoiceProcessingBean) {
		this.claimInvoiceProcessingBean = claimInvoiceProcessingBean;
	}

	public String getCodDecision() {
		return codDecision;
	}

	public void setCodDecision(String codDecision) {
		this.codDecision = codDecision;
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

	public String getIaComments() {
		return iaComments;
	}

	public void setIaComments(String iaComments) {
		this.iaComments = iaComments;
	}

	public Date getIaCommentedDate() {
		return iaCommentedDate;
	}

	public void setIaCommentedDate(Date iaCommentedDate) {
		this.iaCommentedDate = iaCommentedDate;
	}

	@Override
	public String toString() {
		return "ClaimProcessingBean [claimNo=" + claimNo + ", crId=" + crId + ", ecgcDecision=" + ecgcDecision
				+ ", codDecision=" + codDecision + ", ecgcRemarks=" + ecgcRemarks + ", ecgcRecommendation="
				+ ecgcRecommendation + ", ecgcDecisionDate=" + ecgcDecisionDate + ", codDecisionDate=" + codDecisionDate
				+ ", codRemarks=" + codRemarks + ", codRecommendation=" + codRecommendation + ", codMeetingDate="
				+ codMeetingDate + ", processingOfficerComments=" + processingOfficerComments
				+ ", processingOfficerCommentedDate=" + processingOfficerCommentedDate + ", observerComments="
				+ observerComments + ", observerCommentedDate=" + observerCommentedDate + ", recommenderComments="
				+ recommenderComments + ", recommenderCommentedDate=" + recommenderCommentedDate + ", iaComments="
				+ iaComments + ", iaCommentedDate=" + iaCommentedDate + ", claimAmountApproved=" + claimAmountApproved
				+ ", remarks=" + remarks + ", isReassessed=" + isReassessed + ", processedBy=" + processedBy
				+ ", processedOn=" + processedOn + ", version=" + version + ", claimInvoiceProcessingBean="
				+ claimInvoiceProcessingBean + "]";
	}
    

}
