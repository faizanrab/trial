package in.ecgc.erp.pebPolicy.service.claim;

import java.util.List;

import in.ecgc.erp.pebPolicy.model.claim.ClaimApplicationBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDetailsAnnexureBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimDocumentChecklistBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimInvoiceProcessingBean;
import in.ecgc.erp.pebPolicy.model.claim.ClaimRepresentationBean;


public interface PolicyClaimService {

	public List<ClaimApplicationBean> getClaimApplicationBeanList();

	public int saveClaimApplicationDetails(ClaimApplicationBean claimBean);

	public List<ClaimDocumentChecklistBean> getClaimDocumentCheckList();

	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo);

	public ClaimDetailsAnnexureBean getInvoiceDetailsUsingInvoiceNo(String invoiceNo, Long claimNo);

	public int updateClaimApplicationDetails(ClaimApplicationBean claim, Long claimNo);

	public ClaimApplicationBean getClaimApplicationBeanById(Long claimNo, Long policyNo);

	public ClaimRepresentationBean getClaimRepresentationBeanById(Long claimNo, Long crId);

	public int saveClaimRepresentationDetails(ClaimRepresentationBean claimRBean);
	
	public int updateClaimRepresentationDetails(ClaimRepresentationBean claimRBean, Long crId);

}
