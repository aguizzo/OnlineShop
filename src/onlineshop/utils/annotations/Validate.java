package onlineshop.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // This annotation can be only used in class fields.
@Retention(RetentionPolicy.RUNTIME) // This annotation is used during run time.
public @interface Validate {
	
	String pattern();
	
}
