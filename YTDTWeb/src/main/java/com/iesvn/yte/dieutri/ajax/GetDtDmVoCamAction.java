 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmVoCam;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmVoCamAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmVoCam = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmVoCam = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmVoCam", "dtdmvocamNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmVoCam != null) {
       for (Object obj : listDtDmVoCam)
       {
         DtDmVoCam dmvocam = (DtDmVoCam)obj;
         buf.append("<record MaSo='" + dmvocam.getDtdmvocamMaso() + "' Ma='" + dmvocam.getDtdmvocamMa() + "' Ten='" + dmvocam.getDtdmvocamDiengiai() + "' NgayChinhSua='" + dmvocam.getDtdmvocamNgaygiocn() + "' DTDMVOCAM_MAPHU='" + Utils.reFactorString(dmvocam.getDtdmvocamMaphu()) + "' DTDMVOCAM_PHANLOAI='" + Utils.reFactorString(dmvocam.getDtdmvocamPhanloai()) + "' DTDMVOCAM_GHICHU='" + Utils.reFactorString(dmvocam.getDtdmvocamGhichu()) + "' DT='" + Utils.reFactorString(dmvocam.getDtdmvocamChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmVoCamAction

 * JD-Core Version:    0.7.0.1

 */