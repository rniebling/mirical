/* 
 * 
 * $Id: ViewController.java 86367 2015-10-02 13:35:03Z markuss $
 */
package de.nieblings.webapp.spring;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.orchestra.conversation.ConversationContext;
import org.apache.myfaces.orchestra.conversation.ConversationManager;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import de.nieblings.webapp.core.Mandant;
import de.nieblings.webapp.core.SecurityUtils;
import de.nieblings.webapp.core.User;
import de.nieblings.webapp.exceptions.ExceptionService;
import de.nieblings.webapp.util.MandantUtils;
import de.nieblings.webapp.util.Messages;
import de.nieblings.webapp.util.MsgService;
import de.nieblings.webapp.util.ParameterCache;


/**
 * 
 * @author $Author: markuss $
 * @version $Revision: 86367 $
 */
public abstract class ViewController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4240905322075092719L;

	private static final Log LOG = LogFactory.getLog(ViewController.class);

	public static final String OUTCOME_FAILURE = "failure";
	public static final String OUTCOME_SUCCESS = "success";
	public static final String OUTCOME_LOGIN = "login";

	/** @deprecated */
	@Deprecated
	public static final String ERROR_SAVE = Messages.ERROR_SAVE;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_CREATE = Messages.ERROR_CREATE;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_PRINT = Messages.ERROR_PRINT;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_EMAIL = Messages.ERROR_EMAIL;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_LOAD = Messages.ERROR_LOAD;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_REMOVE = Messages.ERROR_REMOVE;

	/** @deprecated */
	@Deprecated
	public static final String ERROR_COMPUTE = Messages.ERROR_COMPUTE;

	@Resource
	private transient MsgService msgService;

	@Resource
	private transient MessageSource messageSource;

	@Resource
	private ExceptionService exceptionService;

	@Resource
	protected transient ParameterCache parameterCache;

	public boolean isPressed(final UIComponent button) {
		if (button == null) {
			return false;
		}
		final FacesContext fc = FacesContext.getCurrentInstance();
		final Map<?, ?> reqParams = fc.getExternalContext().getRequestParameterMap();
		return reqParams.containsKey(button.getClientId(fc));
	}

	public String getConversationContextId() {
		final ConversationManager cm = ConversationManager.getInstance();
		if (cm == null) {
			return null;
		}
		final ConversationContext cc = cm.getCurrentConversationContext();
		if (cc == null) {
			return null;
		}
		return Long.toString(cc.getId(), Character.MAX_RADIX);
	}

	protected final boolean isGranted(final String authorityType, final User user) {
		if (user == null) {
			return false;
		}
		return SecurityUtils.hasRight(authorityType);
	}

	protected User getCurrentUser() {
		return SecurityUtils.getCurrentUser();
	}

	/**
	 * Liefert den derzeit ausgewählten Mandanten oder <code>null</code> wenn keiner ausgewählt
	 * wurde. Beim Neuanlegen von Datenensätzen sollte diese Methode <b>nicht</b> verwendet werden.
	 * 
	 * @return den eingestellten Mandanten oder <code>null</code>
	 */
	protected final Mandant getCurrentMandant() {
		return MandantUtils.getCurrentMandant();
	}

	/**
	 * Liefert den derzeit ausgewählten Mandanten oder den default Mandaten des aktuellen Benutzers.
	 * <b>Beim Neuanlegen von Datenensätzen sollte diese Methode verwendet werden.</b>
	 * 
	 * @return den einen Mandanten
	 */
	public Mandant getMandant() {
		return MandantUtils.getMandant();
	}

	public String getText(final String key) {
		return msgService.getText(key);
	}

	public String getText(final String key, final Object arg) {
		return msgService.getText(key, arg);
	}

	public String getText(final String key, final Object... arg) {
		return msgService.getText(key, arg);
	}

	private void addFaceMessage(final String key, final Object[] arg, final String clientId,
			final FacesMessage.Severity s) {
		addFaceMessage(getText(key, arg), clientId, s);

	}

	/**
	 * Zeigt eine Nachricht an ohne die muss davor erfolgen
	 */

	protected void addFaceMessage(final String text, final String clientId, final FacesMessage.Severity s) {
		final FacesMessage msg = new FacesMessage(text);
		msg.setSeverity(s);
		FacesContext.getCurrentInstance().addMessage(clientId, msg);
	}

	protected void addMessage(final String key, final String clientId, final Object[] arg) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_INFO);
	}

	protected void addMessage(final String key, final Object[] arg) {
		addMessage(key, null, arg);
	}

	protected void addMessage(final String key) {
		addMessage(key, (Object[]) null);
	}

	protected void addError(final String key, final String clientId, final Object... arg) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_ERROR);
	}

	protected void addError(final String key, final Object... arg) {
		addError(key, null, arg);
	}

	protected void addHint(final String key, final String clientId, final Object... arg) {
		addFaceMessage(key, arg, clientId, FacesMessage.SEVERITY_WARN);
	}

	protected void addHint(final String key, final Object... arg) {
		addHint(key, null, arg);
	}

	/**
	 * @deprecated use Error.compute(e), Error.load(e), Error.save(e) ...
	 */
	@Deprecated
	protected String addError(final String key, final Throwable e) {
		LOG.error("presentation layer exception.", e);
		addError(key, null, exceptionService.getMessage(e));
		return null;
	}

	protected void addError(final String key) {
		addError(key, (Object[]) null);
	}

	/**
	 * Convenience method for unit tests.
	 */
	public boolean hasErrors() {
		return getSession().getAttribute("errors") != null;
	}

	/**
	 * Servlet API Convenience method.
	 * 
	 * @return der HTTP-Request
	 */
	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	/**
	 * Servlet API Convenience method.
	 * 
	 * @return die HTTP-Session
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * Servlet API Convenience method.
	 * 
	 * @return der HTTP-Response
	 */
	protected HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	/**
	 * Servlet API Convenience method.
	 * 
	 * @return der Servlet Context
	 */
	protected ServletContext getServletContext() {
		return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected MessageSourceAccessor getMessageSourceAccessor() {
		return new MessageSourceAccessor(messageSource);
	}

}
