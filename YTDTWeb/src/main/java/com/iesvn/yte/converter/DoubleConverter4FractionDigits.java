 package com.iesvn.yte.converter;

 import com.sun.faces.util.MessageFactory;
 import java.io.Serializable;
 import java.text.NumberFormat;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.faces.convert.ConverterException;
 import javax.faces.convert.DoubleConverter;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.faces.Converter;
 import org.jboss.seam.annotations.intercept.BypassInterceptors;

 @Name("com.iesvn.yte.converter.DoubleConverter4FractionDigits")
 @Converter
 @BypassInterceptors
 public class DoubleConverter4FractionDigits
   extends DoubleConverter
   implements Serializable
 {
   public Object getAsObject(FacesContext context, UIComponent component, String value)
   {
     if ((context == null) || (component == null)) {
       throw new NullPointerException();
     }
     if (value == null) {
       return null;
     }
     value = value.trim();
     if (value.length() < 1) {
       return null;
     }
     value = value.replaceAll(",", "");
     try
     {
       return Double.valueOf(value);
     }
     catch (NumberFormatException nfe)
     {
       throw new ConverterException(MessageFactory.getMessage(context, "javax.faces.converter.DoubleConverter.DOUBLE", new Object[] { value, "1999999", MessageFactory.getLabel(context, component) }));
     }
     catch (Exception e)
     {
       throw new ConverterException(e);
     }
   }

   static NumberFormat formatter = null;

   private NumberFormat getNumberFormat()
   {
     if (formatter == null)
     {
       formatter = NumberFormat.getNumberInstance();
       formatter.setGroupingUsed(true);
       formatter.setMaximumFractionDigits(4);
     }
     return formatter;
   }

   public String getAsString(FacesContext context, UIComponent component, Object value)
   {
     if ((context == null) || (component == null)) {
       throw new NullPointerException();
     }
     if (value == null) {
       return "";
     }
     if ((value instanceof String)) {
       return (String)value;
     }
     try
     {
       return getNumberFormat().format(((Number)value).doubleValue());
     }
     catch (Exception e)
     {
       throw new ConverterException(MessageFactory.getMessage(context, "javax.faces.converter.STRING", new Object[] { value, MessageFactory.getLabel(context, component) }), e);
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.converter.DoubleConverter4FractionDigits

 * JD-Core Version:    0.7.0.1

 */