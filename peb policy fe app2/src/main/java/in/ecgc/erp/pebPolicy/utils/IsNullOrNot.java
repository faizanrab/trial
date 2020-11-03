package in.ecgc.erp.pebPolicy.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Documented
@Constraint(validatedBy = RecoveryMasterBeanValidator.class)
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface IsNullOrNot {
	 String message() default "{RequestAnnotation}";

	    Class<?>[] groups() default {};
	    String field();
	  //  String fieldMatch();
	    Class<? extends Payload>[] payload() default {};
//	    @Target({ ElementType.TYPE })
//	    @Retention(RetentionPolicy.RUNTIME)
//	    @interface List {
//	    	IsNullOrNot[] value();
//	    }
	   
	    
	    String fieldMatch();
	 
	    @Target({ ElementType.TYPE })
	    @Retention(RetentionPolicy.RUNTIME)
	    @interface List {
	    	IsNullOrNot[] value();
	    }
}
