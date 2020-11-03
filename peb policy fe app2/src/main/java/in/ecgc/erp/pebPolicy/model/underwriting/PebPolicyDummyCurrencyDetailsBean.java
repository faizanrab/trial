package in.ecgc.erp.pebPolicy.model.underwriting;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import in.ecgc.erp.pebPolicy.exceptions.ProposalAssessmentInfo;
import lombok.Data;


public class PebPolicyDummyCurrencyDetailsBean {

	private Long dummyPolicyNumber;
	private Integer isSupplyOrServiceOrBoth;
	private Boolean isPreShipmentRider;
	
	@NotBlank(groups = ProposalAssessmentInfo.class,message = "{currencyCodeLocal.empty}")
	private String currencyCodeLocal;
	
	@NotBlank(groups = ProposalAssessmentInfo.class,message = "{currencyCodeForeign.empty}")
	private String currencyCodeForeign;
	
	@NotBlank(groups = ProposalAssessmentInfo.class,message = "{currencyCodeThirdParty.empty}")
	private String currencyCodeThirdParty;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{exchangeRateLocal.empty}")
	private Double exchangeRateLocal;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{exchangeRateForeign.empty}")
	private Double exchangeRateForeign;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{exchangeRateThirdParty.empty}")
	private Double exchangeRateThirdParty;
	
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{localValue.empty}")
	private Double localValue;
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{foreignValue.empty}")
	private Double foreignValue;
	@NotNull(groups = ProposalAssessmentInfo.class,message = "{thirdPartyValue.empty}")
	private Double thirdPartyValue;
	private Long cdId;
	private Integer version;
	public Long getDummyPolicyNumber() {
		return dummyPolicyNumber;
	}
	public void setDummyPolicyNumber(Long dummyPolicyNumber) {
		this.dummyPolicyNumber = dummyPolicyNumber;
	}
	public Integer getIsSupplyOrServiceOrBoth() {
		return isSupplyOrServiceOrBoth;
	}
	public void setIsSupplyOrServiceOrBoth(Integer isSupplyOrServiceOrBoth) {
		this.isSupplyOrServiceOrBoth = isSupplyOrServiceOrBoth;
	}
	public Boolean getIsPreShipmentRider() {
		return isPreShipmentRider;
	}
	public void setIsPreShipmentRider(Boolean isPreShipmentRider) {
		this.isPreShipmentRider = isPreShipmentRider;
	}
	public String getCurrencyCodeLocal() {
		return currencyCodeLocal;
	}
	public void setCurrencyCodeLocal(String currencyCodeLocal) {
		this.currencyCodeLocal = currencyCodeLocal;
	}
	public String getCurrencyCodeForeign() {
		return currencyCodeForeign;
	}
	public void setCurrencyCodeForeign(String currencyCodeForeign) {
		this.currencyCodeForeign = currencyCodeForeign;
	}
	public String getCurrencyCodeThirdParty() {
		return currencyCodeThirdParty;
	}
	public void setCurrencyCodeThirdParty(String currencyCodeThirdParty) {
		this.currencyCodeThirdParty = currencyCodeThirdParty;
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
	public Double getLocalValue() {
		return localValue;
	}
	public void setLocalValue(Double localValue) {
		this.localValue = localValue;
	}
	public Double getForeignValue() {
		return foreignValue;
	}
	public void setForeignValue(Double foreignValue) {
		this.foreignValue = foreignValue;
	}
	public Double getThirdPartyValue() {
		return thirdPartyValue;
	}
	public void setThirdPartyValue(Double thirdPartyValue) {
		this.thirdPartyValue = thirdPartyValue;
	}
	public Long getCdId() {
		return cdId;
	}
	public void setCdId(Long cdId) {
		this.cdId = cdId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "PebPolicyDummyCurrencyDetailsBean [dummyPolicyNumber=" + dummyPolicyNumber
				+ ", isSupplyOrServiceOrBoth=" + isSupplyOrServiceOrBoth + ", isPreShipmentRider=" + isPreShipmentRider
				+ ", currencyCodeLocal=" + currencyCodeLocal + ", currencyCodeForeign=" + currencyCodeForeign
				+ ", currencyCodeThirdParty=" + currencyCodeThirdParty + ", exchangeRateLocal=" + exchangeRateLocal
				+ ", exchangeRateForeign=" + exchangeRateForeign + ", exchangeRateThirdParty=" + exchangeRateThirdParty
				+ ", localValue=" + localValue + ", foreignValue=" + foreignValue + ", thirdPartyValue="
				+ thirdPartyValue + ", cdId=" + cdId + ", version=" + version + "]";
	}
	
	

}
