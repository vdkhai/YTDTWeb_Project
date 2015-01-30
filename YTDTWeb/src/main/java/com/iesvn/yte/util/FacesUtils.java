 package com.iesvn.yte.util;

 import javax.el.ExpressionFactory;
 import javax.el.ValueExpression;
 import javax.faces.application.Application;
 import javax.faces.context.ExternalContext;
 import javax.faces.context.FacesContext;
 import javax.portlet.PortletRequest;
 import javax.servlet.http.HttpServletRequest;

 public class FacesUtils
 {
   public static String getParameter(String param)
   {
     Object oReq = FacesContext.getCurrentInstance().getExternalContext().getRequest();
     String paramValue = "";
     try
     {
       paramValue = ((HttpServletRequest)oReq).getParameter(param);
     }
     catch (Exception e)
     {
       paramValue = ((PortletRequest)oReq).getParameter(param);
     }
     return paramValue;
   }

   public static Object getValueOfExpr(String expression, Class expectedType)
   {
     FacesContext context = FacesContext.getCurrentInstance();
     Application app = context.getApplication();
     ValueExpression valueEx = app.getExpressionFactory().createValueExpression(context.getELContext(), expression, expectedType);
     return valueEx.getValue(context.getELContext());
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.FacesUtils

 * JD-Core Version:    0.7.0.1

 */