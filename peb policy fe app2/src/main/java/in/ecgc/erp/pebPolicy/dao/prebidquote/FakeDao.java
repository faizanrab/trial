package in.ecgc.erp.pebPolicy.dao.prebidquote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import in.ecgc.erp.pebPolicy.model.prebidquote.PEBPolicyPrebidQuotationBean;
import in.ecgc.erp.pebPolicy.model.prebidquote.PEBPolicyPrebidQuotationTOPBean;



@Repository
public class FakeDao {

	static List<PEBPolicyPrebidQuotationBean> DB = new ArrayList<PEBPolicyPrebidQuotationBean>();
	static {
		PEBPolicyPrebidQuotationBean p = new PEBPolicyPrebidQuotationBean();
		p.setPbId(345l);
		p.setInwardNumber("in"+100);
		p.setExporterName("Ravi Prajapati");
		p.setBuyerName("Gada Electronics");
		p.setBuyerType("Private");
		p.setContractValue(1000.00);
		//p.setCoverValue(900.00);
		p.setDuration(1);
		p.setFundedBy("WWE");
		p.setIsPreshipmentCoverRequired("no");
		p.setSupplyMoratoriumFreqOfInstallments(2);
		p.setSupplyMoratoriumNoOfInstallments(2);
		p.setSupplyMoratoriumPeriod(2);
		p.setPaymentCountry("NPL");
		p.setPaymentCountryRating("A2");
		p.setPreshipmentCoverTenure(155);
		p.setProjectCountry("SL");
		p.setProjectCountryRating("B1");
		p.setProjectDetails("First Prebid quote of ECGC");
		p.setRiskType(1);
		p.setTransactionType(2);
		//p.setTermsOfPayment(new PEBPolicyPrebidQuotationTOPBean(1, 2, 3, 4, 5, 6, 7, 8));
		p.setTermsOfPayment(new ArrayList<PEBPolicyPrebidQuotationTOPBean>());
		DB.add(p);
	}
	
	public Boolean insert(PEBPolicyPrebidQuotationBean p) {
		System.out.println("p dao:"+p);
		Double contractValue = p.getContractValue();
		boolean response = DB.add(p);
		return response;
	}
	
	public List<PEBPolicyPrebidQuotationBean> getAll(){
		return DB;
	}
	
	public PEBPolicyPrebidQuotationBean getOne(Long pbId) {
		System.out.println(pbId);
		PEBPolicyPrebidQuotationBean find = DB.stream().filter(pb->pb.getPbId().longValue() == pbId.longValue()).findAny().orElse(null);
		System.out.println("find:" + find);
//		for(PEBPolicyPrebidQuotationBean pb : DB) {
//			System.out.println(pb.getPBId());
//			if(pb.getPBId() == pbId) {
//				return pb;
//			}
//		}
		return find;
	}
}
