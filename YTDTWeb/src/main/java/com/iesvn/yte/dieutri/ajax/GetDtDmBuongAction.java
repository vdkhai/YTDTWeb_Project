 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmBuongAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmBuong = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmBuong = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmBuong", "dtdmbNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listDtDmBuong != null) {
       for (Object obj : listDtDmBuong)
       {
         DtDmBuong dmBuong = (DtDmBuong)obj;
         buf.append("<record MaSo='" + dmBuong.getDtdmbMaso() + "' MaSo2='" + dmBuong.getDmkhoaMaso().getDmkhoaMaso() + "' Ma='" + dmBuong.getDtdmbMa() + "' Ten='" + dmBuong.getDtdmbTen() + "' NgayChinhSua='" + dmBuong.getDtdmbNgaygiocn() + "' DT='" + Utils.reFactorString(dmBuong.getDtdmbChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmBuongAction

 * JD-Core Version:    0.7.0.1

 */