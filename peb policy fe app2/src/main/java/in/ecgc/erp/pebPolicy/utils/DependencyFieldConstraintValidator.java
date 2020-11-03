package in.ecgc.erp.pebPolicy.utils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class DependencyFieldConstraintValidator implements ConstraintValidator<DependencyFieldConstraint, Object> {
	
	private String fieldName;
	private String neededValue;
	private String dependentField;
	
	@Override
    public void initialize(final DependencyFieldConstraint constraintAnnotation){
		this.fieldName = constraintAnnotation.fieldName();
		this.neededValue = constraintAnnotation.neededValue();
		this.dependentField = constraintAnnotation.dependantFieldName();
    }


	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("Inside validator... DependencyFieldConstraintValidator");
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
		System.out.println("fieldName: "+fieldName);
		System.out.println("neededValue: "+neededValue);
		System.out.println("dependentField: "+dependentField);
		System.out.println("value: "+value);
		
		Object dependentFieldValue = beanWrapper.getPropertyValue(dependentField);
		Object fieldValue = beanWrapper.getPropertyValue(fieldName);
		System.out.println("dependentFieldValue: "+dependentFieldValue);
		System.out.println("fieldValue: "+fieldValue);
		System.out.println("----------------------------------------");
		
		int length = dependentFieldValue == null ? 0 : ((String)dependentFieldValue).trim().length();
		
		/*if(neededValue.equals(fieldValue) && dependentFieldValue == null ||(dependentFieldValue != null && ((String) dependentFieldValue).isEmpty())) {
			System.out.println("in if");
			return false;
		}*/
		if(neededValue.equals(fieldValue) && length <= 0) {
			System.out.println("in if");
			return false;
		}
		return true;
	}

}
