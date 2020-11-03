package in.ecgc.erp.pebPolicy.utils;



import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import in.ecgc.erp.pebPolicy.model.underwriting.PebPolicyProposalBean;





public  class CustomValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		 return PebPolicyProposalBean.class.isAssignableFrom(clazz); }

	@Override
	public void validate(Object target, Errors errors) {
		 PebPolicyProposalBean ppb = (PebPolicyProposalBean)target;
		  
		  if(ppb.getIsExporterInSAL() != null){ errors.rejectValue("isExporterInSAL",
				  "required.isExporterInSAL"); } 
		  }
		
	}

	
	 
	 

