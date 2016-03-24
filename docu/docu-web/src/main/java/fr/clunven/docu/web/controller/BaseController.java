package fr.clunven.docu.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import fr.clunven.docu.dao.db.DocumentaryDbDao;
import fr.clunven.docu.service.MenuService;
import fr.clunven.docu.web.domain.DocuConstants;
import fr.clunven.docu.web.domain.DocuManifest;
import fr.clunven.docu.web.domain.DocuUser;

/**
 * Injection of DAO(s).
 * 
 * @author Cedrick LUNVEN (@clunven)
 */
public class BaseController implements ServletContextAware, DocuConstants {

    /** Logger. */
    protected Logger log = LoggerFactory.getLogger(getClass());

    /** Formatter for the date. */
    protected static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    /** Formatter to get time. */
    protected static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    @Autowired
    protected DocumentaryDbDao documentaryDbDao;
    
    @Autowired
    protected MenuService menuService;
    
    @Value("#{'${core.version:1.0}'}")
    protected String versionNumber = "0.0";

    @Autowired(required = false)
    protected Validator validator;

    /** Target Servlet context. */
    private ServletContext servletContext;

    /** Autowired through getter. */
    private MessageSourceAccessor messages;

    /** target manifest. */
    private DocuManifest manifest;

    /** Page sor the success view. */
    private String successView;

    /** Page for the cancelled view. */
    private String cancelView;

    /**
     * Access authenticated user.
     *
     * @return authenticated user
     */
    protected DocuUser getUser() {
        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof DocuUser) {
                return (DocuUser) principal;
            }
        }
        return null;
    }

    /**
     * Prepare Page.
     *
     * @return target model
     */
    protected ModelAndView renderPage(HttpServletRequest request) {
        flushMessages(request);
        ModelAndView mav = new ModelAndView(getSuccessView());
        mav.addObject(BEAN_USER, getUser());
        mav.addObject(BEAN_MENU, menuService.getMenu());
        mav.addObject("versionNumber", versionNumber);
        return mav;
    }

    /**
     * Set up a custom property editor for converting form inputs to real objects.
     *
     * @param request
     *            the current request
     * @param binder
     *            the data binder
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
        // binder.setMessageCodesResolver(new DefaultMessageCodesResolver());

    }

    /**
     * Convenience method for getting a i18n key's value. Calling getMessageSourceAccessor() is used because the RequestContext
     * variable is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale
     *            the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return messages.getMessage(msgKey, locale);
    }

    /**
     * Convenient method for getting a i18n key's value with a single string argument.
     *
     * @param msgKey
     * @param arg
     * @param locale
     *            the current locale
     * @return
     */
    public String getText(String msgKey, String arg, Locale locale) {
        return getText(msgKey, new Object[] {arg}, locale);
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @param locale
     *            the current locale
     * @return
     */
    public String getText(String msgKey, Object[] args, Locale locale) {
        return messages.getMessage(msgKey, args, locale);
    }

    /** {@inheritDoc} */
    @Override
    public void setServletContext(ServletContext ctx) {
        this.servletContext = ctx;
    }

    /**
     * Work with error in session.
     *
     * @param request
     *            current http session
     * @param msg
     *            current error message
     */
    public void saveError(HttpServletRequest request, String msg) {
        saveMsg(request, PARAM_ERRORS, msg);
    }

    /**
     * Work with error in session.
     *
     * @param request
     *            current http session
     * @param msg
     *            current error message
     */
    public void saveMessage(HttpServletRequest request, String msg) {
        saveMsg(request, PARAM_MSG, msg);
    }

    /**
     * Allow to flush messages.
     *
     * @param request
     *            flush target messages
     */
    public void flushMessages(HttpServletRequest request) {
        if (request.getSession().getAttribute(PARAM_ERRORS) != null) {
            ((List<?>) request.getSession().getAttribute(PARAM_ERRORS)).clear();
        }
        if (request.getSession().getAttribute(PARAM_MSG) != null) {
            ((List<?>) request.getSession().getAttribute(PARAM_MSG)).clear();
        }
    }

    /**
     * Utility method to update session attribute.
     *
     * @param key
     *            attribute name
     * @param msg
     *            attribute value
     */
    @SuppressWarnings("unchecked")
    private void saveMsg(HttpServletRequest request, String key, String msg) {
        List<String> msgList = (List<String>) request.getSession().getAttribute(key);
        if (msgList == null) {
            msgList = new ArrayList<String>();
        }
        msgList.add(msg);
        request.getSession().setAttribute(key, msgList);
    }

    /**
     * Getter accessor for attribute 'servletContext'.
     *
     * @return current value of 'servletContext'
     */
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * Getter accessor for attribute 'successView'.
     *
     * @return current value of 'successView'
     */
    public String getSuccessView() {
        return successView;
    }

    /**
     * Setter accessor for attribute 'successView'.
     * 
     * @param successView
     *            new value for 'successView '
     */
    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    /**
     * Setter accessor for attribute 'cancelView'.
     * 
     * @param cancelView
     *            new value for 'cancelView '
     */
    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }

    /**
     * Access cancel view.
     * 
     * @return target cancel view mode.
     */
    public final String getCancelView() {
        // Default to successView if cancelView is invalid
        if (this.cancelView == null || this.cancelView.length() == 0) {
            return getSuccessView();
        }
        return this.cancelView;
    }

    /**
     * Getter accessor for attribute 'validator'.
     *
     * @return current value of 'validator'
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * Setter accessor for attribute 'validator'.
     * 
     * @param validator
     *            new value for 'validator '
     */
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    /**
     * Getter accessor for attribute 'messages'.
     *
     * @return current value of 'messages'
     */
    public MessageSourceAccessor getMessages() {
        return messages;
    }

    /**
     * Provide messages.
     *
     * @param messageSource
     */
    @Autowired
    public void setMessages(MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }

}
