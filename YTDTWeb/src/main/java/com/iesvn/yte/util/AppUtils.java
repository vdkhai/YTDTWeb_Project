 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.vienphi.action.ChonXuatFileBaoCao_VienPhi;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;

 public class AppUtils
 {
   public static ChonXuatFileBaoCao_VienPhi getChonXuatFileBaoCao_VienPhiTrongContexts()
   {
     FacesUtils.getValueOfExpr("#{ChonXuatFileBaoCao_VienPhi}", String.class);
     return (ChonXuatFileBaoCao_VienPhi)Contexts.getSessionContext().get("ChonXuatFileBaoCao_VienPhi");
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.AppUtils

 * JD-Core Version:    0.7.0.1

 */