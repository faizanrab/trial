package in.ecgc.erp.pebPolicy.model.claim;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import in.ecgc.erp.pebPolicy.exceptions.BasicClaimInfo;
import in.ecgc.erp.pebPolicy.exceptions.ClaimScrutinyInfo;

public class ClaimApplicationBean {
	
private Long claimNo;
	
	@NotBlank(groups = BasicClaimInfo.class,message = "{inwardNo.empty}")
	private String inwardNo;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{inwardItemNo.empty}")
	private Integer inwardItemNo;
	
	@NotNull(groups = BasicClaimInfo.class,message = "{policyNo.empty}")
	private Long policyNo;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{rodId.empty}")
	private Long rodId;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{rodDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date rodDate;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{grossLossValue.empty}")
	private BigDecimal grossLossValue;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{claimApplicationDate.empty}")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date claimApplicationDate;
	
	//@NotNull(groups = BasicClaimInfo.class,message = "{remarks.empty}")
	private String remarks;
	
	//@NotBlank(groups = BasicClaimInfo.class,message = "{reasonForClaim.empty}")
	private String reasonForClaim;
	
	private String claimStatus;
	private String createdBy;
	private Date createdOn;
	
	private String modifiedBy;
	private Date modifiedOn;
	private Integer version;
	private Integer riYear;
	private String quarter;
	
	private List<ClaimDetailsAnnexureBean> claimDetailsAnnexureBean;

	private List<ClaimExperienceWithBuyerAnnexureBean> claimExperienceWithBuyerAnnexureBean;
	
	private List<ClaimBankFilledDetailsAnnexureBean> claimBankFilledDetailsAnnexureBean;
	
	@Valid
	private ClaimProcessingBean claimProcessingBean;
	
	private List<ClaimRepresentationBean> claimRepresentationBeanList;
	
	
	@Size(groups = {ClaimScrutinyInfo.class},min = 1,message = "Please select atleast one")
	private List<String> claimDocumentChecklistBean;


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


	public Long getClaimNo() {
		return claimNo;
	}


	public void setClaimNo(Long claimNo) {
		this.claimNo = claimNo;
	}


	public Long getPolicyNo() {
		return policyNo;
	}


	public void setPolicyNo(Long policyNo) {
		this.policyNo = policyNo;
	}


	public Long getRodId() {
		return rodId;
	}


	public void setRodId(Long rodId) {
		this.rodId = rodId;
	}


	public Date getRodDate() {
		return rodDate;
	}


	public void setRodDate(Date rodDate) {
		this.rodDate = rodDate;
	}


	public BigDecimal getGrossLossValue() {
		return grossLossValue;
	}


	public void setGrossLossValue(BigDecimal grossLossValue) {
		this.grossLossValue = grossLossValue;
	}


	public Date getClaimApplicationDate() {
		return claimApplicationDate;
	}


	public void setClaimApplicationDate(Date claimApplicationDate) {
		this.claimApplicationDate = claimApplicationDate;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getReasonForClaim() {
		return reasonForClaim;
	}


	public void setReasonForClaim(String reasonForClaim) {
		this.reasonForClaim = reasonForClaim;
	}


	public String getClaimStatus() {
		return claimStatus;
	}


	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
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


	public Integer getRiYear() {
		return riYear;
	}


	public void setRiYear(Integer riYear) {
		this.riYear = riYear;
	}


	public String getQuarter() {
		return quarter;
	}


	public void setQuarter(String quarter) {
		this.quarter = quarter;
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

	public List<ClaimBankFilledDetailsAnnexureBean> getClaimBankFilledDetailsAnnexureBean() {
		return claimBankFilledDetailsAnnexureBean;
	}


	public void setClaimBankFilledDetailsAnnexureBean(List<ClaimBankFilledDetailsAnnexureBean> claimBankFilledDetailsAnnexureBean) {
		this.claimBankFilledDetailsAnnexureBean = claimBankFilledDetailsAnnexureBean;
	}


	public List<ClaimRepresentationBean> getClaimRepresentationBeanList() {
		return claimRepresentationBeanList;
	}


	public void setClaimRepresentationBeanList(List<ClaimRepresentationBean> claimRepresentationBeanList) {
		this.claimRepresentationBeanList = claimRepresentationBeanList;
	}


	@Override
	public String toString() {
		return "ClaimApplicationBean [claimNo=" + claimNo + ", inwardNo=" + inwardNo + ", inwardItemNo=" + inwardItemNo
				+ ", policyNo=" + policyNo + ", rodId=" + rodId + ", rodDate=" + rodDate + ", grossLossValue="
				+ grossLossValue + ", claimApplicationDate=" + claimApplicationDate + ", remarks=" + remarks
				+ ", reasonForClaim=" + reasonForClaim + ", claimStatus=" + claimStatus + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", modifiedBy=" + modifiedBy + ", modifiedOn=" + modifiedOn
				+ ", version=" + version + ", riYear=" + riYear + ", quarter=" + quarter + ", claimDetailsAnnexureBean="
				+ claimDetailsAnnexureBean + ", claimExperienceWithBuyerAnnexureBean="
				+ claimExperienceWithBuyerAnnexureBean + ", claimBankFilledDetailsAnnexureBean="
				+ claimBankFilledDetailsAnnexureBean + ", claimProcessingBean=" + claimProcessingBean
				+ ", claimRepresentationBeanList=" + claimRepresentationBeanList + ", claimDocumentChecklistBean="
				+ claimDocumentChecklistBean + "]";
	}	

}
