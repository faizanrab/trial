package in.ecgc.erp.pebPolicy.model.underwriting;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyDeferredPaymentScheduleDetailsBean;




public class PolicyDetailsBean {
	
	private Long policyNumber;
	
	private Long proposalId;
	
	private Integer isSupplyOrServiceOrBoth;
	
	private Date postShipmentStartDate;
	
	private Date postShipmentEndDate;
	
	private Double postShipmentPremiumRate;
	
	private Double postShipmentPremiumAmount;
	
	private Integer transactionTypeId;
	
	private Integer riskTypeId;
	
	private String isInstallmentFacility;
	
	private Double coverValue;
	
	private Double coverPercentage;
	
	private Date extendedEndDate;
	
	private Double maximumLiability;
	
	private Integer moratoriumPeriod;
	
	private Integer moratoriumNumberOfInstallment;
	
	private Integer moratoriumFreqOfInstallment;
	
	private Double advancePaidAmount;
	
	private Double advanceWeightage;
	
	private Date advancePaidDate;
	
	private String isPreshipmentRider;
	
	private Double totalSupplyValue;
	
	private Date preShipmentStartDate;
	
	private Date preShipmentEndDate;
	
	private Double preShipmentContractValueLocal;
	
	private Double preShipmentContractValueThirdParty;
	
	private Double preShipmentContractValueForeign;
	
	private Double preShipmentPremiumRate;
	
	private Double preShipmentPremiumAmount;
	
	private Double preShipmentDiscount;
	
	private Double totalPremiumAmount;
	
	private String isCrossCountry;
	
	private String paymentCountry;
	
	private String paymentCountryRating;
	
	private String fundingArrangement;
	
	private String fundingAgencyName;
	
	private Double loadingPercentage;
	
	private Double contractValueLocal;
	
	private Double contractValueForeign;
	
	private Double contractValueThirdParty;
	
	private String riYear;
	
	private String quarter;
	
	private String tags;
	
	private String createdBy;
	
	private Date createdOn;
	
	private String modifiedBy;
	
	private Date modifiedOn;
	
	private PebPolicyTermsOfPaymentTenureDetailsBean paymentTenureDetails;
	
	private PebPolicyTermsOfPaymentWeightageDetailsBean paymentWeightageDetails;
	
	private List<PebPolicyDeferredPaymentScheduleDetailsBean> policyDeferredPaymentScheduleDetails;
	
	private PebPolicyCurrencyDetailsBean pebPolicyCurrencyDetails;
	
	private List<PebPolicyEndorsementMstBean> endorsementMasterBeans;

}
