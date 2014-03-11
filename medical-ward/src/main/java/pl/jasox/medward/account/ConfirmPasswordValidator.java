/*
 */
package pl.jasox.medward.account;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.jboss.seam.faces.validation.InputField;
import org.jboss.seam.international.status.builder.BundleTemplateMessage;

import pl.jasox.medward.i18n.DefaultBundleKey;

/**
 * Validate that both the password fields contain the same value.
 * Implements the classic password change validation.
 *
 */
@FacesValidator("confirmPassword")
public class ConfirmPasswordValidator implements Validator {

  @Inject
  private BundleTemplateMessage messageBuilder;
  
  @Inject
  @InputField
  private String password;
  
  @Inject
  @InputField
  private String confirmPassword;

  @Override
  public void validate(          
          final FacesContext ctx,
          final UIComponent form,
          final Object components)
  throws ValidatorException {
    if (password == null || confirmPassword == null) {
      return;
    }

    if (!password.equals(confirmPassword)) {
      throw new ValidatorException(
        new FacesMessage(
          messageBuilder.key(
            new DefaultBundleKey("account_passwordsDoNotMatch"))
              .defaults("Passwords do not match").build().getText()));
    }
  }
}
