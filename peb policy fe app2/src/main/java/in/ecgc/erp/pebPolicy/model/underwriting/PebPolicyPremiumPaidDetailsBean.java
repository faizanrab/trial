package in.ecgc.erp.pebPolicy.model.underwriting;

import java.sql.Date;

public class PebPolicyPremiumPaidDetailsBean {

	private Long policyNumber;
	
	private PebPolicyEndorsementMstBean endorsementId;
	
	private String transactionId;
	
	private Date premiumPaidDate;

	private Double amountPaid;
	
	private String isFullInstallPayment;
	
	private Integer pisId;
	
	private String paymentmode;
	
	
}
