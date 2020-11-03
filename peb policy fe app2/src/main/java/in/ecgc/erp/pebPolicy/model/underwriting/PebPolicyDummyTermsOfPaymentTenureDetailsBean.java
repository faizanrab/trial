package in.ecgc.erp.pebPolicy.model.underwriting;

import javax.validation.constraints.NotNull;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;
import lombok.Data;


public class PebPolicyDummyTermsOfPaymentTenureDetailsBean {
	private Long dummyPolicyNumber;
	private Long tptId;
	private Boolean isSupplyOrServiceOrBoth;
	
	private Double advanceTenureValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{downTenureValue.empty}")
	private Double downTenureValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{pacTenureValue.empty}")
	private Double pacTenureValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{facTenureValue.empty}")
	private Double facTenureValue;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{progressTenureValue.empty}")
	private Double progressTenureValue;

	public Long getDummyPolicyNumber() {
		return dummyPolicyNumber;
	}

	public void setDummyPolicyNumber(Long dummyPolicyNumber) {
		this.dummyPolicyNumber = dummyPolicyNumber;
	}

	public Long getTptId() {
		return tptId;
	}

	public void setTptId(Long tptId) {
		this.tptId = tptId;
	}

	public Boolean getIsSupplyOrServiceOrBoth() {
		return isSupplyOrServiceOrBoth;
	}

	public void setIsSupplyOrServiceOrBoth(Boolean isSupplyOrServiceOrBoth) {
		this.isSupplyOrServiceOrBoth = isSupplyOrServiceOrBoth;
	}

	public Double getAdvanceTenureValue() {
		return advanceTenureValue;
	}

	public void setAdvanceTenureValue(Double advanceTenureValue) {
		this.advanceTenureValue = advanceTenureValue;
	}

	public Double getDownTenureValue() {
		return downTenureValue;
	}

	public void setDownTenureValue(Double downTenureValue) {
		this.downTenureValue = downTenureValue;
	}

	public Double getPacTenureValue() {
		return pacTenureValue;
	}

	public void setPacTenureValue(Double pacTenureValue) {
		this.pacTenureValue = pacTenureValue;
	}

	public Double getFacTenureValue() {
		return facTenureValue;
	}

	public void setFacTenureValue(Double facTenureValue) {
		this.facTenureValue = facTenureValue;
	}

	public Double getProgressTenureValue() {
		return progressTenureValue;
	}

	public void setProgressTenureValue(Double progressTenureValue) {
		this.progressTenureValue = progressTenureValue;
	}

	@Override
	public String toString() {
		return "PebPolicyDummyTermsOfPaymentTenureDetailsBean [dummyPolicyNumber=" + dummyPolicyNumber + ", tptId="
				+ tptId + ", isSupplyOrServiceOrBoth=" + isSupplyOrServiceOrBoth + ", advanceTenureValue="
				+ advanceTenureValue + ", downTenureValue=" + downTenureValue + ", pacTenureValue=" + pacTenureValue
				+ ", facTenureValue=" + facTenureValue + ", progressTenureValue=" + progressTenureValue + "]";
	}
	
	
	
	
}
