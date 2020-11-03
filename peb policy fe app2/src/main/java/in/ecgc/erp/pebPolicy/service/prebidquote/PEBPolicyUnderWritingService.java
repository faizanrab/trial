package in.ecgc.erp.pebPolicy.service.prebidquote;

import java.util.Map;


public interface PEBPolicyUnderWritingService {

	//public long preBidQuoteOperation(PEBPolicyPrebidQuotationBean preBidBean);
	
	//public int getRateChartRating(PEBPolicyPremiumParameterBean paramBean);
	
	//public long insertPreBidQuoteDetails(PEBPolicyPrebidQuotationBean ppqb);
	
	//public PEBPolicyPrebidQuotationBean getPrebidQuotationDetails(long preBidId);
	
	//public List<Integer> getRateChartRatingForDeferredPayment(PEBPolicyPremiumParameterBean paramBean);
	
	public Map<String, String> getCountryList();
	
	public Map<String, String> getCountryRating();

}
