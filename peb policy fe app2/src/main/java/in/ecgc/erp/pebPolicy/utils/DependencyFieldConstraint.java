package in.ecgc.erp.pebPolicy.utils;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
//@Target({ TYPE, ANNOTATION_TYPE })
@Target({ TYPE })
@Repeatable(DependencyFieldConstraint.List.class)
@Constraint(validatedBy = DependencyFieldConstraintValidator.class)
public @interface DependencyFieldConstraint {

	String fieldName();
	String neededValue();
	String dependantFieldName();
	
	String message() default "{DependencyFieldConstraint.message}";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    @Target({TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List{
    	DependencyFieldConstraint[] value();
    }

}
