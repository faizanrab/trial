package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;






public class PebPolicyDeferredPaymentScheduleDetailsBean {
	private Long policyNumber;
	private Long dpsId;
	//private Long endorsementId;
	
	private PebPolicyEndorsementMstBean endorsementId;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{dateOfPaymentToBeDone.empty}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfPaymentToBeDone;
	
	//@NotNull(groups = AssessmentInfo.class,message = "{amountToBePaid.empty}")
	private Double amountToBePaid;
	
	//@NotBlank(groups = AssessmentInfo.class,message = "{remarks.empty}")
	private String remarks;
	
	private String createdBy;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;

	public Long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(Long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Long getDpsId() {
		return dpsId;
	}

	public void setDpsId(Long dpsId) {
		this.dpsId = dpsId;
	}

	public PebPolicyEndorsementMstBean getEndorsementId() {
		return endorsementId;
	}

	public void setEndorsementId(PebPolicyEndorsementMstBean endorsementId) {
		this.endorsementId = endorsementId;
	}

	public Date getDateOfPaymentToBeDone() {
		return dateOfPaymentToBeDone;
	}

	public void setDateOfPaymentToBeDone(Date dateOfPaymentToBeDone) {
		this.dateOfPaymentToBeDone = dateOfPaymentToBeDone;
	}

	public Double getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(Double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Override
	public String toString() {
		return "PebPolicyDeferredPaymentScheduleDetailsBean [policyNumber=" + policyNumber + ", dpsId=" + dpsId
				+ ", endorsementId=" + endorsementId + ", dateOfPaymentToBeDone=" + dateOfPaymentToBeDone
				+ ", amountToBePaid=" + amountToBePaid + ", remarks=" + remarks + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + "]";
	}
	
	
	

}
