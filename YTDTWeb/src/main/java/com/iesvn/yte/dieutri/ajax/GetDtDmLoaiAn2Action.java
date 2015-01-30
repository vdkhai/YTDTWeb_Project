 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmLoaiAn2Action
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmLoaiAn2 = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmLoaiAn2 = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmLoaiAn2", "dtdmla2Ngaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listDtDmLoaiAn2 != null) {
       for (Object obj : listDtDmLoaiAn2)
       {
         DtDmLoaiAn2 dmLoaiAn2 = (DtDmLoaiAn2)obj;
         buf.append("<record MaSo='" + dmLoaiAn2.getDtdmla2Maso() + "' MaSo2='" + dmLoaiAn2.getDtdmlaMaso().getDtdmlaMaso() + "' Ma='" + dmLoaiAn2.getDtdmla2Ma() + "' Ten='" + dmLoaiAn2.getDtdmla2Ten() + "' NgayChinhSua='" + dmLoaiAn2.getDtdmla2Ngaygiocn() + "' DT='" + Utils.reFactorString(dmLoaiAn2.getDtdmla2Chon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmLoaiAn2Action

 * JD-Core Version:    0.7.0.1

 */