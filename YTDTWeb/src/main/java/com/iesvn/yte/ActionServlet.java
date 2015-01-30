 package com.iesvn.yte;

 import java.io.IOException;
 import java.io.PrintWriter;
 import java.util.logging.Level;
 import java.util.logging.Logger;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.richfaces.json.XML;

 public class ActionServlet
   extends HttpServlet
 {
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, Exception
   {
     request.setCharacterEncoding("UTF-8");
     String actionType = request.getParameter("urlAction");
     String myActionType = "com.iesvn.yte.dieutri.ajax." + actionType;

     String xmlData = request.getParameter("xmlData");


     Action action = (Action)Class.forName(myActionType).newInstance();

     String myResult = action.performAction(xmlData);
     if ((actionType.equals("GetCLSAction")) || (actionType.equals("GetCLSActionNT")) || (actionType.equals("GetCLSBangGiaCDHA")))
     {
       response.setContentType("text/xml;charset=UTF-8");
       response.getWriter().print(myResult);
     }
     else
     {
       response.setContentType("application/x-json;charset=UTF-8");
       response.getWriter().print(XML.toJSONObject(myResult));
     }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     try
     {
       processRequest(request, response);
     }
     catch (ClassNotFoundException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (InstantiationException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (IllegalAccessException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (Exception ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     try
     {
       processRequest(request, response);
     }
     catch (ClassNotFoundException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (InstantiationException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (IllegalAccessException ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
     catch (Exception ex)
     {
       Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.ActionServlet

 * JD-Core Version:    0.7.0.1

 */