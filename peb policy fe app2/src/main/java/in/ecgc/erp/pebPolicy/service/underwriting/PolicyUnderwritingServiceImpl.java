package in.ecgc.erp.pebPolicy.service.underwriting;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.erp.pebPolicy.dao.underwriting.PolicyUnderwritingDao;
import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalBean;
import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalDocumentCheckListBean;

@Service
public class PolicyUnderwritingServiceImpl implements PolicyUnderwritingService{
	
	
	private static List<PebPolicyProposalBean> DB = null;
	
	@Autowired
	private PolicyUnderwritingDao dao;
	
	
	@Autowired
	private ObjectMapper mapper;
	
	InputStream inputStream = null;
	
	@PostConstruct
	public void mockAssessment() {
		try {
			inputStream = new FileInputStream("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockAssessment2.json");
			DB =  mapper.readValue(inputStream, new TypeReference<List<PebPolicyProposalBean>>() {});
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<PebPolicyProposalBean> selectAllProposals() {
		return DB;
	}
	
	
	
	

	private Map<Long, PebPolicyProposalBean> proposalDetailsStore = new HashMap<>();
	private Long id = 1L ;
	@Override
	public Map<Integer, String> getProductType() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		//map.put("", "--------------Please Select-----------");
		map.put(1, "SCP");
		map.put(2, "SSCP");
		map.put(3, "SSP");
		map.put(4, "CWP");
		return map;
		
	}

	@Override
	public Map<Integer, String> getModeOfPayment() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		//map.put(1, "-------Please Select------");
		map.put(2, "Cheque");
		map.put(3, "RTGS");
		map.put(4, "NEFT");
		map.put(5, "IMPS");
		return map;
	}

	@Override
	public PebPolicyProposalBean addBasicProposalDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		pebPolicyProposalBean.setProposalId(id);
		proposalDetailsStore.put(id++, pebPolicyProposalBean);
		
		return pebPolicyProposalBean;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Long, PebPolicyProposalBean> getProposalDetailsStore() {
		
		return proposalDetailsStore;
	}

	public void setProposalDetailsStore(Map<Long, PebPolicyProposalBean> proposalDetailsStore) {
		this.proposalDetailsStore = proposalDetailsStore;
	}

	@Override
	public List<PebPolicyProposalDocumentCheckListBean> getDocumentCheckList() {
		List<PebPolicyProposalDocumentCheckListBean> documentCheckList=Arrays.asList(
				new PebPolicyProposalDocumentCheckListBean(1,"Duly filled proposal form"),
				new PebPolicyProposalDocumentCheckListBean(2,"Copy of Contract"),
				new PebPolicyProposalDocumentCheckListBean(3,"Post Award Approval from Authorized Dealer"),
				new PebPolicyProposalDocumentCheckListBean(4,"KYC Documents"),
				new PebPolicyProposalDocumentCheckListBean(5,"Copy of L/C if applicable"),
				new PebPolicyProposalDocumentCheckListBean(6,"Proof of Remittance incase of Multilateral Funding Agency"),
				new PebPolicyProposalDocumentCheckListBean(7,"Balance sheet of last 2 years"),
				new PebPolicyProposalDocumentCheckListBean(8,"Country Report"),
				new PebPolicyProposalDocumentCheckListBean(9,"Country Profile"),
				new PebPolicyProposalDocumentCheckListBean(10,"Company Profile"),
				new PebPolicyProposalDocumentCheckListBean(11,"Exporter's past record in same type of projects"),
				new PebPolicyProposalDocumentCheckListBean(12,"Exporter experience with ECGC"),
				new PebPolicyProposalDocumentCheckListBean(13,"Project Profitability"),
				new PebPolicyProposalDocumentCheckListBean(14,"Exporter experience with NEIA"),
				new PebPolicyProposalDocumentCheckListBean(15,"Export From India"),
				new PebPolicyProposalDocumentCheckListBean(16,"Experience With EXIM Bank & Line Of Credit"),
				new PebPolicyProposalDocumentCheckListBean(17,"Clauses Of Contract")
				);
		return documentCheckList;
	}

	@Override
	public void addScrutinyDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		System.out.println("PebPolicyProposalBean of scrutiny mtd::"+pebPolicyProposalBean);
		System.out.println("In service id::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);	
	}

	@Override
	public void addMiscelleneousDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		System.out.println("PebPolicyProposalBean of misc mtd::"+pebPolicyProposalBean);
		System.out.println("In service id::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);	
	}
	
	@Override
	public void updateBasicProposalDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		// TODO Auto-generated method stub
		System.out.println("PebPolicyProposalBean of update basicProp.::"+pebPolicyProposalBean);
		System.out.println("In service id::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);	
	}
	
	//tab 3 methods

	@Override
	public Map<Integer,String> typeOfWork() {
		Map<Integer, String> work = new HashMap<>();
		work.put(1, "Supply");
		work.put(2, "Service");
		work.put(3, "Both");
		return work;
	} 
	
	@Override
	public Map<Integer,String> transactionType() {
		Map<Integer, String> transaction = new HashMap<>();
		transaction.put(1, "LC");
		transaction.put(2, "NON-LC");
		return transaction;
	} 
	
	@Override
	public Map<Integer,String> riskType() {
		Map<Integer, String> risk = new HashMap<>();
		risk.put(1, "Political");
		risk.put(2, "Comprehensive");
		return risk;
	} 
	
	@Override
	public Map<Integer,String> fundingArrangement() {
		Map<Integer, String> arrangement = new HashMap<>();
		arrangement.put(1, "World Bank");
		arrangement.put(2, "JICA");
		arrangement.put(3, "IMF");
		return arrangement;
	} 
	
	@Override
	public Map<String,String> currencyCode() {
		Map<String, String> code = new HashMap<>();
		code.put("INR","India");
		code.put("AUD","Australia Dollar");
		code.put("GBP","Great Britain Pound");
		code.put("EUR","Euro");
		code.put("JPY","Japan Yen");
		code.put("USD","USA Dollar");
		return code;
	} 
	
	@Override
	public Map<String,String> countryRating() {
		Map<String, String> rating = new HashMap<>();
		rating.put("A1","A1");
		rating.put("A2","A2");
		rating.put("B1","B1");
		rating.put("B2","B2");
		rating.put("C1","C1");
		rating.put("C2","C2");
		return rating;
	} 
	

	@Override
	public Map<String, String> getCountryList(){
		Map<String,String> countryMap = new TreeMap<String, String>();
		countryMap.put("NPL", "Nepal");
		countryMap.put("SL", "SriLanka");
		countryMap.put("TH", "Thailand");
		countryMap.put("ML", "Malaysia");
		countryMap.put("GH", "Ghana");
		countryMap.put("NI", "Nigeria");
		countryMap.put("ET", "Ethiopia");
		return countryMap;
	}
	
	@Override
	public List<DummyPolicyDetailsBean> listOfDummyPolicy(){
		return dao.selectAllDummyPolicy();
	}
	
	@Override
	public DummyPolicyDetailsBean getDummyPolicyByDummyPolicyNumber(Long dummyPolicyNumber) {
		return dao.getDummyPolicyByDummyPolicyNumber(dummyPolicyNumber);
	}


	@Override
	public int insertAssessment(DummyPolicyDetailsBean dummy) {
		return dao.insertAssessment(dummy);
	}

	@Override
	public void updateAssessment(DummyPolicyDetailsBean dummy, Long dummyPolicyNumber) {
		dao.updateAssessment(dummy, dummyPolicyNumber);
	}

	@Override
	public PebPolicyProposalBean getProposalDetailsById(Long proposalId) {
		PebPolicyProposalBean p= proposalDetailsStore.get(proposalId);
		System.out.println("p::"+p.getProposalId());	
		return p;
	}

	@Override
	public void updateScrutinyDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		System.out.println("PebPolicyProposalBean of update scru.::"+pebPolicyProposalBean);
		System.out.println("In service id::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);	
		
	}

	@Override
	public void updateMiscDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		System.out.println("PebPolicyProposalBean of update misc.::"+pebPolicyProposalBean);
		System.out.println("In service id::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);	
		
	}

	@Override
	public PebPolicyProposalBean getProposalById(Long id) {
		// TODO Auto-generated method stub
		return DB.stream().filter(d -> d.getProposalId() == id).findAny().get();
	}

	@Override
	public DummyPolicyDetailsBean getDummyPolicyById(Long dummyPolicyNo) {
		// TODO Auto-generated method stub
		List<PebPolicyProposalBean> bean=DB;
		
		
		return null;
	}

	@Override
	public void addDecisionDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		// TODO Auto-generated method stub
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);
		
	}

	@Override
	public void updateDecisionDetails(PebPolicyProposalBean pebPolicyProposalBean) {
		// TODO Auto-generated method stub
		System.out.println("In update of dec::"+pebPolicyProposalBean.getProposalId());
		proposalDetailsStore.put(pebPolicyProposalBean.getProposalId(), pebPolicyProposalBean);
		
	}
	

}
