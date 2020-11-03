package in.ecgc.erp.pebPolicy.service.underwriting;

import java.util.List;
import java.util.Map;

import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalDocumentCheckListBean;


public interface PolicyUnderwritingService {

	//tab 1 methods
	public Map<Integer, String> getProductType();
	public Map<Integer, String> getModeOfPayment();
	public Map<Long, PebPolicyProposalBean> getProposalDetailsStore();
	public PebPolicyProposalBean addBasicProposalDetails(PebPolicyProposalBean pebPolicyProposalBean);
	public PebPolicyProposalBean getProposalDetailsById(Long proposalId);
	public void updateBasicProposalDetails(PebPolicyProposalBean pebPolicyProposalBean);
	public void updateScrutinyDetails(PebPolicyProposalBean pebPolicyProposalBean);
	public void updateMiscDetails(PebPolicyProposalBean pebPolicyProposalBean);
	List<PebPolicyProposalDocumentCheckListBean> getDocumentCheckList();
	
	public void addScrutinyDetails(PebPolicyProposalBean pebPolicyProposalBean);
	public void addMiscelleneousDetails(PebPolicyProposalBean pebPolicyProposalBean);
	
	
	//tab 3 methods
	Map<Integer, String> typeOfWork();

	Map<Integer, String> fundingArrangement();

	Map<String, String> currencyCode();

	Map<String, String> countryRating();

	Map<String, String> getCountryList();

	Long getId();

	void setId(Long id);

	void setProposalDetailsStore(Map<Long, PebPolicyProposalBean> proposalDetailsStore);
	
	List<DummyPolicyDetailsBean> listOfDummyPolicy();
	
	DummyPolicyDetailsBean getDummyPolicyByDummyPolicyNumber(Long dummyPolicyNumber);
	
	Map<Integer,String> transactionType();
	
	Map<Integer,String> riskType();
	
	int insertAssessment(DummyPolicyDetailsBean dummy);
	
	void updateAssessment(DummyPolicyDetailsBean dummy, Long dummyPolicyNumber);
	public List<PebPolicyProposalBean>  selectAllProposals();
	public PebPolicyProposalBean getProposalById(Long id);
	public DummyPolicyDetailsBean getDummyPolicyById(Long dummyPolicyNo);
	public void addDecisionDetails(PebPolicyProposalBean pBean);
	public void updateDecisionDetails(PebPolicyProposalBean pebPolicyProposalBean);
	
}
