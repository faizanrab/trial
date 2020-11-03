package in.ecgc.erp.pebPolicy.model.underwriting;

import javax.validation.constraints.NotNull;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;
import lombok.Data;


public class PebPolicyDummyTermsOfPaymentWeightageDetailsBean {

	private Long dummyPolicyNumber;
	private Long tpwId;
	private Boolean isSupplyOrServiceOrBoth;
	private Double advanceWeightageValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{downWeightageValue.empty}")
	private Double downWeightageValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{pacWeightageValue.empty}")
	private Double pacWeightageValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{facWeightageValue.empty}")
	private Double facWeightageValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{progressWeightageValue.empty}")
	private Double progressWeightageValue;

	public Long getDummyPolicyNumber() {
		return dummyPolicyNumber;
	}

	public void setDummyPolicyNumber(Long dummyPolicyNumber) {
		this.dummyPolicyNumber = dummyPolicyNumber;
	}

	public Long getTpwId() {
		return tpwId;
	}

	public void setTpwId(Long tpwId) {
		this.tpwId = tpwId;
	}

	public Boolean getIsSupplyOrServiceOrBoth() {
		return isSupplyOrServiceOrBoth;
	}

	public void setIsSupplyOrServiceOrBoth(Boolean isSupplyOrServiceOrBoth) {
		this.isSupplyOrServiceOrBoth = isSupplyOrServiceOrBoth;
	}

	public Double getAdvanceWeightageValue() {
		return advanceWeightageValue;
	}

	public void setAdvanceWeightageValue(Double advanceWeightageValue) {
		this.advanceWeightageValue = advanceWeightageValue;
	}

	public Double getDownWeightageValue() {
		return downWeightageValue;
	}

	public void setDownWeightageValue(Double downWeightageValue) {
		this.downWeightageValue = downWeightageValue;
	}

	public Double getPacWeightageValue() {
		return pacWeightageValue;
	}

	public void setPacWeightageValue(Double pacWeightageValue) {
		this.pacWeightageValue = pacWeightageValue;
	}

	public Double getFacWeightageValue() {
		return facWeightageValue;
	}

	public void setFacWeightageValue(Double facWeightageValue) {
		this.facWeightageValue = facWeightageValue;
	}

	public Double getProgressWeightageValue() {
		return progressWeightageValue;
	}

	public void setProgressWeightageValue(Double progressWeightageValue) {
		this.progressWeightageValue = progressWeightageValue;
	}

	@Override
	public String toString() {
		return "PebPolicyDummyTermsOfPaymentWeightageDetailsBean [dummyPolicyNumber=" + dummyPolicyNumber + ", tpwId="
				+ tpwId + ", isSupplyOrServiceOrBoth=" + isSupplyOrServiceOrBoth + ", advanceWeightageValue="
				+ advanceWeightageValue + ", downWeightageValue=" + downWeightageValue + ", pacWeightageValue="
				+ pacWeightageValue + ", facWeightageValue=" + facWeightageValue + ", progressWeightageValue="
				+ progressWeightageValue + "]";
	}

	
	
}
