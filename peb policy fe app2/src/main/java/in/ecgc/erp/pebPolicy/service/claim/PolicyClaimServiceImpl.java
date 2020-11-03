package in.ecgc.erp.pebPolicy.service.claim;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecgc.erp.pebPolicy.dao.claim.PolicyClaimDao;
import in.ecgc.erp.pebPolicy.model.claim.ClaimApplicationBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDocumentChecklistBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimInvoiceProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimRepresentationBean;


@Service
public class PolicyClaimServiceImpl implements PolicyClaimService{

	@Autowired
	private PolicyClaimDao policyClaimDao;
	
	@Override
	public List<ClaimApplicationBean> getClaimApplicationBeanList() {
		// TODO Auto-generated method stub
		return policyClaimDao.getClaimApplicationBeanList();
	}

	@Override
	public int saveClaimApplicationDetails(ClaimApplicationBean claimBean) {
		// TODO Auto-generated method stub
		return policyClaimDao.saveClaimApplicationDetails(claimBean);
	}

	@Override
	public List<ClaimDocumentChecklistBean> getClaimDocumentCheckList() {
		// TODO Auto-generated method stub
		return policyClaimDao.getClaimDocumentCheckList();
	}

	@Override
	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo) {
		// TODO Auto-generated method stub
		return policyClaimDao.getClaimApplicationBeanById(claimNo);
	}

	@Override
	public ClaimDetailsAnnexureBean getInvoiceDetailsUsingInvoiceNo(String invoiceNo, Long claimNo) {
		// TODO Auto-generated method stub
		return policyClaimDao.getInvoiceDetailsUsingInvoiceNo(invoiceNo,claimNo);
	}

	@Override
	public int updateClaimApplicationDetails(ClaimApplicationBean claim, Long claimNo) {
		// TODO Auto-generated method stub
		return policyClaimDao.updateClaimApplicationDetails(claim,claimNo);
	}

	@Override
	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo, Long policyNo) {
		// TODO Auto-generated method stub
		return policyClaimDao.getClaimApplicationBeanById(claimNo,policyNo);
	}

	@Override
	public ClaimRepresentationBean getClaimRepresentationBeanById(Long claimNo, Long crId) {
		// TODO Auto-generated method stub
		return policyClaimDao.getClaimRepresentationBeanById(claimNo,crId);
	}

	@Override
	public int saveClaimRepresentationDetails(ClaimRepresentationBean claimRBean) {
		// TODO Auto-generated method stub
		return policyClaimDao.saveClaimRepresentationDetails(claimRBean);
	}

	@Override
	public int updateClaimRepresentationDetails(ClaimRepresentationBean claimRBean, Long crId) {
		return policyClaimDao.updateClaimRepresentationDetails(claimRBean, crId);
	}

	

}
