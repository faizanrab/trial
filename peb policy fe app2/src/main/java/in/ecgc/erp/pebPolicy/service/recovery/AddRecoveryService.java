package in.ecgc.erp.pebPolicy.service.recovery;

import java.util.List;

import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyRecoveryMasterBean;



public interface AddRecoveryService {
	public List<PebPolicyRecoveryMasterBean> getRecoveryList(PebPolicyRecoveryMasterBean recoveryMasterBean);
	public List<PebPolicyRecoveryMasterBean> getRecoveryList();
}
