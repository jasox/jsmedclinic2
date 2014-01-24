/*
 * 
 */
package pl.jasox.medward.i18n;

import org.jboss.seam.international.status.builder.BundleKey;

/**
 * @author Janusz Swół
 */
public class DefaultBundleKey extends BundleKey {

	private static final long   serialVersionUID    = -1869573840974331392L;
	public  static final String DEFAULT_BUNDLE_NAME = "messages";

    public DefaultBundleKey(String key) {
        super(DEFAULT_BUNDLE_NAME, key);
    }
}
