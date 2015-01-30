 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBacSiBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmBacSiBanKhamAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listObj = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmBacSiBanKham", "dtdmbankhamNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DtDmBacSiBanKham bsBk = (DtDmBacSiBanKham)obj;
         buf.append("<record MaSo='" + bsBk.getDtdmbacsibankhamMaso() + "' DTDMBANKHAM_MASO='" + bsBk.getDtdmbankhamMaso().getDtdmbankhamMaso() + "' DTDMNHANVIEN_MASO='" + bsBk.getDtdmnhanvienMaso().getDtdmnhanvienMaso() + "' NgayChinhSua='" + Utils.reFactorString(bsBk.getDtdmbankhamNgaygiocn()) + "' DT='" + Utils.reFactorString(bsBk.getDtdmbankhamChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmBacSiBanKhamAction

 * JD-Core Version:    0.7.0.1

 */