 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmThuoc;
 import org.apache.log4j.Logger;

 public class GetGiaBQGiaCuoiTonAction
   extends Action
 {
   private static Logger logger = Logger.getLogger(GetGiaBQGiaCuoiTonAction.class);

   public String performAction(String request)
   {
     StringBuffer buf = new StringBuffer();

     buf.append("<list>");
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       DmThuoc dmt = (DmThuoc)dtutilDelegate.findByMa(request.substring(0, request.lastIndexOf("|")), "DmThuoc", "dmthuocMa");
       for (int i = 0; i < 3; i++) {
         buf.append(String.format("<result value='%s' />", new Object[] { Integer.valueOf(0) }));
       }
       buf.append(String.format("<result value='%s' />", new Object[] { dmt.getDmdonvitinhMaso().getDmdonvitinhMa() }));
     }
     catch (Exception e)
     {
       logger.error(String.format("-----Error: %s at line %s -----", new Object[] { e, Integer.valueOf(e.getStackTrace()[0].getLineNumber()) }));
     }
     buf.append("</list>");
     logger.debug("-----result: " + buf.toString());
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetGiaBQGiaCuoiTonAction

 * JD-Core Version:    0.7.0.1

 */