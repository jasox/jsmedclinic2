package pl.jasox.medward.account;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.international.status.builder.BundleTemplateMessage;
import pl.jasox.medward.i18n.DefaultBundleKey;
import pl.jasox.medward.model.IMedwardUser;

/**
 * A JSF Validator, used to check that the password the user submits matches that on record.
 */
@FacesValidator("currentPassword")
public class CurrentPasswordValidator implements Validator {

  @Inject
  private BundleTemplateMessage messageBuilder;

  @Inject
  @Authenticated
  private IMedwardUser currentUser;

  @Inject
  Messages messages;
  
  // ---------------------------------------------------------------------------

  @Override
  public void validate(final FacesContext ctx, final UIComponent comp, final Object value) 
  throws ValidatorException 
  {
    String currentPassword = (String) value;
    if ( (currentUser.getPassword() != null) && 
         !currentUser.getPassword().equals(currentPassword) ) {
      /*
       * FIXME: This is an ugly way to put i18n in FacesMessages: 
       *        https://jira.jboss.org/browse/SEAMFACES-24
       */
      throw new ValidatorException(
        new FacesMessage(
          messageBuilder
            .key( new DefaultBundleKey("account_passwordsDoNotMatch") )
            .defaults("Passwords do not match")
            .build()
            .getText()));
    }
  }

}
