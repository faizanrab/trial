package in.ecgc.erp.pebPolicy.dao.claim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ecgc.erp.pebPolicy.model.claim.ClaimApplicationBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDocumentChecklistBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimRepresentationBean;
import in.ecgc.erp.pebPolicy.model.underwriting.DummyPolicyDetailsBean;

@Repository
public class PolicyClaimDaoImpl implements PolicyClaimDao {

	private static List<ClaimApplicationBean> DB = null;

	@Autowired
	private ObjectMapper mapper;

	InputStream inputStream = null;

	@PostConstruct
	public void mockAssessment() {
		try {
			inputStream = new FileInputStream(
					"C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockClaimData.json");

			DB = mapper.readValue(inputStream, new TypeReference<List<ClaimApplicationBean>>() {
			});
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<ClaimApplicationBean> getClaimApplicationBeanList() {
		// TODO Auto-generated method stub
		//System.out.println(DB.getClaimBankFilledDetailsAnnexureBean());
		//System.out.println(DB.getClaimExperienceWithBuyerAnnexureBean());
		//System.out.println(DB.getClaimDocumentChecklistBean());
		return DB;
	}

	@Override
	public int saveClaimApplicationDetails(ClaimApplicationBean claimBean) {
		// TODO Auto-generated method stub
		System.out.println(claimBean);
		if(DB==null) {
			DB=new ArrayList<ClaimApplicationBean>();
		}
		DB.add(claimBean);
		try {
			mapper.writeValue(new File("C:/Users/CMJ-SENG-LAPTOP27/Documents/sts/PEB/pebpolicyfeapplication/src/main/resources/static/mockdata/mockClaimData.json"), DB);
			//mapper.writeValue(new File("D:/STS_Projects/pebpolicyfeapp1/src/main/resources/static/mockdata/mockRiXolYearWise.json"), reinsurerDB);
			System.out.println("adding into DB::"+DB);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<ClaimDocumentChecklistBean> getClaimDocumentCheckList() {
		// TODO Auto-generated method stub
		List<ClaimDocumentChecklistBean> claimCheckList = Arrays.asList(
				new ClaimDocumentChecklistBean(1l,"Contract Order"),
				new ClaimDocumentChecklistBean(2l,"Correspondence with Buyer"),
				new ClaimDocumentChecklistBean(3l,"Bill of Exchange"),
				new ClaimDocumentChecklistBean(4l,"Non-Payment Advice")
			);
			
			return claimCheckList;
	}

	@Override
	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo) {
		// TODO Auto-generated method stub
		 ClaimApplicationBean claimApplicationBean = DB.stream().filter(c -> c.getClaimNo().longValue() == claimNo.longValue()).findFirst().get();
		// System.out.println(claimApplicationBean);
		 return claimApplicationBean;
	}

	@Override
	public ClaimDetailsAnnexureBean getInvoiceDetailsUsingInvoiceNo(String invoiceNo, Long claimNo) {
		// TODO Auto-generated method stub
		ClaimApplicationBean claimBean = DB.stream().filter(c -> c.getClaimNo().longValue() == claimNo.longValue()).findFirst().get();
		
		List<ClaimDetailsAnnexureBean> annexure = claimBean.getClaimDetailsAnnexureBean();
		
		ClaimDetailsAnnexureBean ann1 = annexure.stream().filter(i -> i.getInvoiceNo().equals(invoiceNo)).findFirst().get();
		
		return ann1;
	}

	@Override
	public int updateClaimApplicationDetails(ClaimApplicationBean claim,Long claimNo) {
		// TODO Auto-generated method stub
		int counter = 0;
		for(ClaimApplicationBean claimBean : DB) {
				if(claimBean.getClaimNo()==claimNo) {
					DB.set(counter, claim);
				}
			counter++;
		}
		try {
			mapper.writeValue(new File("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockClaimData.json"), DB);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo, Long policyNo) {
		// TODO Auto-generated method stub
		return DB.stream().filter(c->c.getClaimNo().longValue() == claimNo.longValue()).filter(p->p.getPolicyNo().longValue() == policyNo.longValue()).findFirst().get();
	}
	
	//claim representation dao here

	@Override
	public ClaimRepresentationBean getClaimRepresentationBeanById(Long claimNo, Long crId) {
		// TODO Auto-generated method stub
		ClaimApplicationBean claimBean=DB.stream().filter(c->c.getClaimNo().longValue() == claimNo.longValue()).findFirst().get();
		Iterator<ClaimRepresentationBean> itr=claimBean.getClaimRepresentationBeanList().iterator();
		ClaimRepresentationBean crBean=new ClaimRepresentationBean();
		while(itr.hasNext()) {
			crBean=itr.next();
			if(crBean.getCrId().equals(crId)) {
				return crBean;
			}
		}
		
		return null;
	}

	@Override
	public int saveClaimRepresentationDetails(ClaimRepresentationBean claimRBean) {
		// TODO Auto-generated method stub
		ClaimApplicationBean claimBean=DB.stream().filter(c->c.getClaimNo().longValue() == claimRBean.getClaimNo().longValue()).findFirst().get();
		//error code
		//claimBean.getClaimRepresentationBeanList().add(claimRBean);
		if(claimBean.getClaimRepresentationBeanList()!=null) {
			claimBean.getClaimRepresentationBeanList().add(claimRBean);
		}else {
			List<ClaimRepresentationBean> crlist = new ArrayList<>();
			crlist.add(claimRBean);
			claimBean.setClaimRepresentationBeanList(crlist);
		}
		int counter = 0;
		for(ClaimApplicationBean claim : DB) {
				if(claim.getClaimNo()==claimRBean.getClaimNo()) {
					DB.set(counter, claimBean);
				}
			counter++;
		}
		try {
			mapper.writeValue(new File("C:/Users/eeee/Downloads/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockClaimData.json"), DB);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateClaimRepresentationDetails(ClaimRepresentationBean claimRBean, Long crId) {
		
		ClaimApplicationBean claimBean=DB.stream().filter(c->c.getClaimNo().longValue() == claimRBean.getClaimNo().longValue()).findFirst().get();
		
		//update claim representation bean in claim bean
		int count = 0;
		List<ClaimRepresentationBean> claimRepresentationBeanList = claimBean.getClaimRepresentationBeanList();
		for(ClaimRepresentationBean crBean : claimRepresentationBeanList) {
			if(crBean.getCrId().longValue() == crId) {
				claimRepresentationBeanList.set(count, claimRBean);
			}
			count++;
		}
		
		//update the claim bean with updated crbean
		claimBean.setClaimRepresentationBeanList(claimRepresentationBeanList);
		
		//update the claim bean in mock file
		int counter = 0;
		for(ClaimApplicationBean claim : DB) {
				if(claim.getClaimNo().longValue()==claimRBean.getClaimNo().longValue()) {
					DB.set(counter, claimBean);
				}
			counter++;
		}
		try {
			mapper.writeValue(new File("/home/faizan/Downloads/peb policy fe app-20200909T074814Z-001/peb policy fe app/pebpolicyfeapplication11/src/main/resources/static/mockdata/mockClaimData.json"), DB);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
