package com.namyang.nyorder.util;


import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.EscapeBodyTag;

import com.namyang.nyorder.comm.service.CommCodeService;
import com.namyang.nyorder.comm.vo.CommCodeVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("deprecation")
public class CodeSupport extends EscapeBodyTag {
	private static final long serialVersionUID = -2379548437347815146L;

	// *********************************************************************
	// Internal state
	protected Object value; // tag attribute
	protected boolean valueSpecified; // status
	protected Object target; // tag attribute
	protected String property; // tag attribute
	private String var; // tag attribute
	private int scope; // tag attribute
	private boolean scopeSpecified; // status

	private String commGrpCd; // 검색할 코드 아이디

	@Autowired
	CommCodeService commCodeService;

	// *********************************************************************
	// Construction and initialization

	/**
	 * Constructs a new handler. As with TagSupport, subclasses should not
	 * provide other constructors and are expected to call the superclass
	 * constructor.
	 */
	public CodeSupport() {
		super();
		init();
	}

	// resets local state
	private void init() {
		value = var = null;
		scopeSpecified = valueSpecified = false;
		scope = PageContext.PAGE_SCOPE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		super.release();
		init();
	}

	@Override
	public int doStartTagInternal() {
		/**
		 * Dao 연결
		 */
		WebApplicationContext webAppContext = getRequestContext().getWebApplicationContext();
		AutowireCapableBeanFactory autowireBeanFactory = webAppContext.getAutowireCapableBeanFactory();
		autowireBeanFactory.autowireBean(this);

		/**
		 * select Code
		 */
		try {
			this.value = commCodeService.selectCommCodeList(new CommCodeVO() {{
				setCommGrpCd(commGrpCd);
				setUseYn("Y");
			}});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	// *********************************************************************
	// Tag logic

	@SuppressWarnings("unchecked")
	@Override
	public int doEndTag() throws JspException {
		Object result; // what we'll store in scope:var

		// determine the value by...
		if (value != null) {
			// ... reading our attribute
			result = value;
		} else if (valueSpecified) {
			// ... accepting an explicit null
			result = null;
		} else {
			try {
				// ... retrieving and trimming our body
				if (this.readBodyContent() == null)
					result = "";
				else
					result = this.readBodyContent().trim();
			} catch (Exception e) {
				result = "";
			}
		}

		// decide what to do with the result
		if (var != null) {

			/*
			 * Store the result, letting an IllegalArgumentException propagate
			 * back if the scope is invalid (e.g., if an attempt is made to
			 * store something in the session without any HttpSession existing).
			 */
			if (result != null) {
				pageContext.setAttribute(var, result, scope);
			} else {
				if (scopeSpecified)
					pageContext.removeAttribute(var, scope);
				else
					pageContext.removeAttribute(var);
			}

		} else if (target != null) {

			// save the result to target.property
			if (target instanceof Map) {
				// ... treating it as a Map entry
				if (result == null)
					((Map<?, ?>) target).remove(property);
				else
					((Map<String, Object>) target).put(property, result);
			} else {
				// ... treating it as a bean property
				try {
					PropertyDescriptor pd[] = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
					boolean succeeded = false;
					for (int i = 0; i < pd.length; i++) {
						if (pd[i].getName().equals(property)) {
							Method m = pd[i].getWriteMethod();
							if (m == null) {
								throw new JspException(Resources.getMessage("SET_NO_SETTER_METHOD", property));
							}
							if (result != null) {
								try {
									m.invoke(target,
											new Object[] { convertToExpectedType(result, m.getParameterTypes()[0]) });
								} catch (javax.servlet.jsp.el.ELException ex) {
									throw new JspTagException(ex);
								}
							} else {
								m.invoke(target, new Object[] { null });
							}
							succeeded = true;
						}
					}
					if (!succeeded) {
						throw new JspTagException(Resources.getMessage("SET_INVALID_PROPERTY", property));
					}
				} catch (IllegalAccessException ex) {
					throw new JspException(ex);
				} catch (IntrospectionException ex) {
					throw new JspException(ex);
				} catch (InvocationTargetException ex) {
					throw new JspException(ex);
				}
			}
		} else {
			// should't ever occur because of validation in TLV and setters
			throw new JspTagException();
		}

		return EVAL_PAGE;
	}

	/**
	 * Convert an object to an expected type according to the conversion rules
	 * of the Expression Language.
	 */
	private Object convertToExpectedType(final Object value, Class<?> expectedType)
			throws javax.servlet.jsp.el.ELException {
		ExpressionEvaluator evaluator = pageContext.getExpressionEvaluator();
		return evaluator.evaluate("${result}", expectedType, new VariableResolver() {
			public Object resolveVariable(String pName) throws ELException {
				return value;
			}
		}, null);
	}

	// *********************************************************************
	
}
