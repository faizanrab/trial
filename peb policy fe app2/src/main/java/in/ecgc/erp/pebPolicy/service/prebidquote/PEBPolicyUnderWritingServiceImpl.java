package in.ecgc.erp.pebPolicy.service.prebidquote;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

@Service
public class PEBPolicyUnderWritingServiceImpl implements PEBPolicyUnderWritingService {
	
	//@Autowired
	//private PEBPolicyUnderWritingDAO dao;
	
	
	public Map<String, String> getCountryList(){
		Map<String,String> countryMap = new TreeMap<String, String>();
		//countryMap.put("NA", "--Select--");
		countryMap.put("NPL", "Nepal");
		countryMap.put("SL", "SriLanka");
		countryMap.put("TH", "Thailand");
		countryMap.put("ML", "Malaysia");
		countryMap.put("GH", "Ghana");
		countryMap.put("NI", "Nigeria");
		countryMap.put("ET", "Ethiopia");
		return countryMap;
	}
	
	/*
	 * public String getCountryRating(String countryCode){ Map<String,String>
	 * countryMap = new HashMap<String, String>();
	 * 
	 * countryMap.put("NPL", "A2"); countryMap.put("SL", "A2"); countryMap.put("TH",
	 * "B1"); countryMap.put("ML", "A2"); countryMap.put("GH", "C1");
	 * countryMap.put("NI", "C1"); countryMap.put("ET", "C2"); return
	 * countryMap.get(countryCode.toUpperCase()); //return ""; }
	 */

	@Override
	public Map<String, String> getCountryRating() {
		Map<String,String> countryMap = new HashMap<String, String>();
		
		countryMap.put("NPL", "A2");
		countryMap.put("SL", "A2");
		countryMap.put("TH", "B1");
		countryMap.put("ML", "A2");
		countryMap.put("GH", "C1");
		countryMap.put("NI", "C1");
		countryMap.put("ET", "C2");
		//return countryMap.get(countryCode.toUpperCase());
		return countryMap;
	}


//	public long preBidQuoteOperation(PEBPolicyPrebidQuotationBean preBidBean){
//		
//		boolean isCrossCountry =  !preBidBean.getProjectCountry().equalsIgnoreCase(preBidBean.getPaymentCountry());
//		if("YES".equalsIgnoreCase(preBidBean.getIsPreshipmentCoverRequired())){
//			System.out.println("preshipment true");
//			
//			//boolean isCrossCountry =  preBidBean.getProjectCountry().equalsIgnoreCase(preBidBean.getPaymentCountry());
//			PEBPolicyPremiumParameterBean paramBean1 = new PEBPolicyPremiumParameterBean();
//			paramBean1.setCountryRating(getCountryRating(preBidBean.getProjectCountry()));
//			paramBean1.setRiskTypeId(preBidBean.getRiskType());
//			paramBean1.setTransactionTypeId(preBidBean.getTransactionType());
//			paramBean1.setTOPDuration(preBidBean.getPreshipmentCoverTenure());
//			paramBean1.setPaymentCountryRating(getCountryRating(preBidBean.getPaymentCountry()));
//			PEBPolicyPremiumCalculator premiumCalculator = new PEBPolicyPreshipmentPremiumCalculator();
//			double preShipmentPremiumRate = premiumCalculator.calculate(dao, paramBean1,isCrossCountry);
//			
//			/**
//			 * preShipmentPremiumRate = (double)rate/100.0;
//			 * round off to next nearest integer
//			 */						
//			// set the preshipment premium rate
//			preBidBean.setPreShipmentPremiumRate(preShipmentPremiumRate);
//			// to check for cross country
//			
//		}
//		
//		/*
//		 * if deferred payment
//		 * 	if cross country
//		 * 	else
//		 * 
//		 * set premium rate
//		 */
//		if(preBidBean.getMoratoriumPeriod() > 0){
//			//boolean isCrossCountry =  preBidBean.getProjectCountry().equalsIgnoreCase(preBidBean.getPaymentCountry());
//			PEBPolicyPremiumParameterBean paramBean1 = new PEBPolicyPremiumParameterBean();
//			paramBean1.setCountryRating(getCountryRating(preBidBean.getProjectCountry()));
//			paramBean1.setRiskTypeId(preBidBean.getRiskType());
//			paramBean1.setTransactionTypeId(preBidBean.getTransactionType());
//			paramBean1.setMoratoriumPeriod(preBidBean.getMoratoriumPeriod());
//			paramBean1.setTOPDuration(preBidBean.getDuration());
//			paramBean1.setPaymentCountryRating(getCountryRating(preBidBean.getPaymentCountry()));
//			PEBPolicyPremiumCalculator premiumCalculator = new PEBPolicyDeferredPaymentPremiumCalculator();
//			double premiumRate = premiumCalculator.calculate(dao, paramBean1,isCrossCountry);
//			preBidBean.setPremiumRate(premiumRate);
//			// to check for cross country
//		}
//		
//		/* 
//		 * if cash contract
//		 * 	if cross country
//		 * 
//		 *  else
//		 * 
//		 * set premium rate
//		 * 
//		 */
//		if(preBidBean.getTermsOfPayment() != null){
//			System.out.println("service impl not null for cash contract");
//			PEBPolicyPremiumParameterBean paramBean1 = new PEBPolicyPremiumParameterBean();
//			paramBean1.setCountryRating(getCountryRating(preBidBean.getProjectCountry()));
//			paramBean1.setRiskTypeId(preBidBean.getRiskType());
//			paramBean1.setTransactionTypeId(preBidBean.getTransactionType());
//			paramBean1.setTermsOfPayment(preBidBean.getTermsOfPayment());
//			paramBean1.setPaymentCountryRating(getCountryRating(preBidBean.getPaymentCountry()));
//			PEBPolicyPremiumCalculator premiumCalculator = new PEBPolicyCrossCountryPremiumCalculator();
//			double cashContractPremiumRate = premiumCalculator.calculate(dao, paramBean1, isCrossCountry);
//			preBidBean.setPremiumRate(cashContractPremiumRate);
//		}
//		 
//		/*
//		 * how to handle case where both cash contract as well as deferred payment is set
//		 */
//		
//		/*
//		 * insert the data in prebid quote table, returns preBid id 
//		 */
//		preBidBean.setProjectCountryRating(getCountryRating(preBidBean.getProjectCountry()));
//		/*
//		 * if cross country set payment country rating
//		 */
//		//preBidBean.setPaymentCountryRating(getCountryRating(preBidBean.getPaymentCountry()));
//		
//		preBidBean.setCoverValue(preBidBean.getContractValue()*0.9);
//		long preBidId = insertPreBidQuoteDetails(preBidBean);
//		return preBidId;
//	}
//
//	
//	public int getRateChartRating(PEBPolicyPremiumParameterBean paramBean) {
//		// TODO Auto-generated method stub
//		return dao.getRateChartRating(paramBean);
//	}
//
//	
//	public long insertPreBidQuoteDetails(PEBPolicyPrebidQuotationBean ppqb) {
//		// TODO Auto-generated method stub
//		return dao.insertPreBidQuoteDetails(ppqb);
//	}
//
//	
//	public PEBPolicyPrebidQuotationBean getPrebidQuotationDetails(long preBidId) {
//		// TODO Auto-generated method stub
//		return dao.getPrebidQuotationDetails(preBidId);
//	}
//
//	
//	public List<Integer> getRateChartRatingForDeferredPayment(PEBPolicyPremiumParameterBean paramBean) {
//		// TODO Auto-generated method stub
//		return dao.getRateChartRatingForDeferredPayment(paramBean);
//	}
//
//	
//	
}
