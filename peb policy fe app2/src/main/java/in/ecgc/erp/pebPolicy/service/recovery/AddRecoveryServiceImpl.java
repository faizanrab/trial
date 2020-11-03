package in.ecgc.erp.pebPolicy.service.recovery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.ecgc.erp.pebPolicy.model.recovery.PebPolicyRecoveryMasterBean;


@Service
public class AddRecoveryServiceImpl implements AddRecoveryService{
	List<PebPolicyRecoveryMasterBean> recoveryList;
	@Override
	public List<PebPolicyRecoveryMasterBean> getRecoveryList(PebPolicyRecoveryMasterBean recoveryMasterBean) {
		
		recoveryList.add(recoveryMasterBean);
		
		return recoveryList;
	}
	public AddRecoveryServiceImpl(ArrayList<PebPolicyRecoveryMasterBean> recoveryList) {
		super();
		this.recoveryList = recoveryList;
	}
	@Override
	public List<PebPolicyRecoveryMasterBean> getRecoveryList() {
		// TODO Auto-generated method stub
		return recoveryList;
	}
	

}
