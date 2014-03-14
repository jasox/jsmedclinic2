package pl.jasox.medward.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio Goncalves
 *           APress Book - 'Beginning Java EE 7 with Glassfish 4'
 *           http://www.apress.com/           <br/>
 *           http://www.antoniogoncalves.org  <br/>
 *         --
 */
@Constraint(validatedBy = {URLValidator.class})
@ReportAsSingleViolation
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface URL {

  String message() default "Malformed URL";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String protocol() default "";

  String host() default "";

  int port() default -1;
}
