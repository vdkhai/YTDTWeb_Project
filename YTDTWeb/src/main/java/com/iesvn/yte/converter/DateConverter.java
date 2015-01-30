 package com.iesvn.yte.converter;

 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.faces.convert.ConverterException;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.intercept.BypassInterceptors;

 @Name("com.iesvn.yte.converter.DateConverter")
 @org.jboss.seam.annotations.faces.Converter
 @BypassInterceptors
 public class DateConverter
   implements javax.faces.convert.Converter, Serializable
 {
   public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String param)
   {
     try
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       String dateString = "";
       if ((param == null) || (param.trim().length() == 0)) {
         dateString = df.format(Utils.getCurrentDate());
       }
       return df.format(param);
     }
     catch (Exception exception)
     {
       throw new ConverterException(exception);
     }
   }

   public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj)
   {
     try
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       String dateString = "";
       if (obj == null) {
         dateString = df.format(Utils.getCurrentDate());
       }
       return df.format(obj);
     }
     catch (Exception exception)
     {
       throw new ConverterException(exception);
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.converter.DateConverter

 * JD-Core Version:    0.7.0.1

 */