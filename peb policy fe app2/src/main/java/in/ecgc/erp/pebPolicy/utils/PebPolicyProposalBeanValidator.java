package in.ecgc.erp.pebPolicy.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalBean;




public class PebPolicyProposalBeanValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return PebPolicyProposalBean.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
