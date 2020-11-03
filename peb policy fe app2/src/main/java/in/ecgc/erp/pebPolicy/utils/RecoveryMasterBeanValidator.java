package in.ecgc.erp.pebPolicy.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;



public class RecoveryMasterBeanValidator implements 
ConstraintValidator<IsNullOrNot, Object> {
	 private String field;
	private String fieldMatch;
	 @Override
	 public void initialize(IsNullOrNot constraintAnnotation) {
	        this.field = constraintAnnotation.field();
	        this.fieldMatch = constraintAnnotation.fieldMatch();
	        
	    }
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
      
       
		if(fieldValue==null&&fieldMatchValue==null)
		{

		        return false;
		}
		else
		{
			return true;
		}

	}


}
