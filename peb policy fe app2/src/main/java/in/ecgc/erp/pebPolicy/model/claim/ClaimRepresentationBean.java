package in.ecgc.erp.pebPolicy.model.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import in.ecgc.erp.pebPolicy.exceptions.BasicClaimRepresentationInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimScrutinyInfo;
import in.ecgc.erp.pebPolicy.model.claim.ClaimBankFilledDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimExperienceWithBuyerAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimProcessingBean;

public class ClaimRepresentationBean {
	
	private Long claimNo;
	
	@NotBlank(groups = BasicClaimRepresentationInfo.class,message = "{inwardNo.empty}")
	private String inwardNo;
	
	//@NotNull(groups = BasicClaimRepresentationInfo.class,message = "{inwardItemNo.empty}")
	private Integer inwardItemNo;
	
	//@NotNull(groups = BasicClaimRepresentationInfo.class,message = "{policyNo.empty}")
	private Long policyNo;
	
	private Long crId;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date claimRepresentationDate;
	private BigDecimal grossLossValueINR;
	private String reasonForClaimRepresentation;
	
	private String createdBy;
	private Date createdOn;
	
	private String modifiedBy;
	private Date modifiedOn;
	private Integer version;
	
	private String remarks;
	
	private List<ClaimDetailsAnnexureBean> claimDetailsAnnexureBean;
	
	private List<String> claimDetailsAnnexureBeanList;

	private List<ClaimExperienceWithBuyerAnnexureBean> claimExperienceWithBuyerAnnexureBean;
	
	private List<ClaimBankFilledDetailsAnnexureBean> claimBankFilledDetailsAnnexureBean;
	
	private ClaimProcessingBean claimProcessingBean;
	
	@Size(groups = {ClaimScrutinyInfo.class},min = 1,message = "Please select atleast one")
	private List<String> claimDocumentChecklistBean;
	

	public Long getClaimNo() {
		return claimNo;
	}


	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}


	public String getInwardNo() {
		return inwardNo;
	}


	public void setInwardNo(String inwardNo) {
		this.inwardNo = inwardNo;
	}


	public Integer getInwardItemNo() {
		return inwardItemNo;
	}


	public void setInwardItemNo(Integer inwardItemNo) {
		this.inwardItemNo = inwardItemNo;
	}


	public Long getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(Long policyNo) {
		this.policyNo = policyNo;
	}


	public Long getCrId() {
		return crId;
	}


	public void setCrId(Long crId) {
		this.crId = crId;
	}


	public Date getClaimRepresentationDate() {
		return claimRepresentationDate;
	}


	public void setClaimRepresentationDate(Date claimRepresentationDate) {
		this.claimRepresentationDate = claimRepresentationDate;
	}


	public BigDecimal getGrossLossValueINR() {
		return grossLossValueINR;
	}


	public void setGrossLossValueINR(BigDecimal grossLossValueINR) {
		this.grossLossValueINR = grossLossValueINR;
	}


	public String getReasonForClaimRepresentation() {
		return reasonForClaimRepresentation;
	}


	public void setReasonForClaimRepresentation(String reasonForClaimRepresentation) {
		this.reasonForClaimRepresentation = reasonForClaimRepresentation;
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


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public Date getModifiedOn() {
		return modifiedOn;
	}


	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public List<ClaimDetailsAnnexureBean> getClaimDetailsAnnexureBean() {
		return claimDetailsAnnexureBean;
	}


	public void setClaimDetailsAnnexureBean(List<ClaimDetailsAnnexureBean> claimDetailsAnnexureBean) {
		this.claimDetailsAnnexureBean = claimDetailsAnnexureBean;
	}


	public List<ClaimExperienceWithBuyerAnnexureBean> getClaimExperienceWithBuyerAnnexureBean() {
		return claimExperienceWithBuyerAnnexureBean;
	}


	public void setClaimExperienceWithBuyerAnnexureBean(
			List<ClaimExperienceWithBuyerAnnexureBean> claimExperienceWithBuyerAnnexureBean) {
		this.claimExperienceWithBuyerAnnexureBean = claimExperienceWithBuyerAnnexureBean;
	}


	public List<ClaimBankFilledDetailsAnnexureBean> getClaimBankFilledDetailsAnnexureBean() {
		return claimBankFilledDetailsAnnexureBean;
	}


	public void setClaimBankFilledDetailsAnnexureBean(
			List<ClaimBankFilledDetailsAnnexureBean> claimBankFilledDetailsAnnexureBean) {
		this.claimBankFilledDetailsAnnexureBean = claimBankFilledDetailsAnnexureBean;
	}


	public ClaimProcessingBean getClaimProcessingBean() {
		return claimProcessingBean;
	}


	public void setClaimProcessingBean(ClaimProcessingBean claimProcessingBean) {
		this.claimProcessingBean = claimProcessingBean;
	}


	public List<String> getClaimDocumentChecklistBean() {
		return claimDocumentChecklistBean;
	}


	public void setClaimDocumentChecklistBean(List<String> claimDocumentChecklistBean) {
		this.claimDocumentChecklistBean = claimDocumentChecklistBean;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<String> getClaimDetailsAnnexureBeanList() {
		return claimDetailsAnnexureBeanList;
	}


	public void setClaimDetailsAnnexureBeanList(List<String> claimDetailsAnnexureBeanList) {
		this.claimDetailsAnnexureBeanList = claimDetailsAnnexureBeanList;
	}


	@Override
	public String toString() {
		return "ClaimRepresentationBean [claimNo=" + claimNo + ", inwardNo=" + inwardNo + ", inwardItemNo="
				+ inwardItemNo + ", policyNo=" + policyNo + ", crId=" + crId + ", claimRepresentationDate="
				+ claimRepresentationDate + ", grossLossValueINR=" + grossLossValueINR
				+ ", reasonForClaimRepresentation=" + reasonForClaimRepresentation + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn
				+ ", version=" + version + ", remarks=" + remarks + ", claimDetailsAnnexureBean="
				+ claimDetailsAnnexureBean + ", claimDetailsAnnexureBeanList=" + claimDetailsAnnexureBeanList
				+ ", claimExperienceWithBuyerAnnexureBean=" + claimExperienceWithBuyerAnnexureBean
				+ ", claimBankFilledDetailsAnnexureBean=" + claimBankFilledDetailsAnnexureBean
				+ ", claimProcessingBean=" + claimProcessingBean + ", claimDocumentChecklistBean="
				+ claimDocumentChecklistBean + "]";
	}
	

	

}
