 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmLoaiMienAction
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
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmLoaiMien", "dtdmloaimienNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DtDmLoaiMien loaiMien = (DtDmLoaiMien)obj;
         buf.append("<record  MaSo='" + loaiMien.getDtdmloaimienMaso() + "'" + " Ma='" + loaiMien.getDtdmloaimienMa() + "'" + " Ten='" + loaiMien.getDtdmloaimienTen() + "'" + " NgayChinhSua='" + loaiMien.getDtdmloaimienNgaygiocn() + "'" + " DT='" + Utils.reFactorString(loaiMien.getDtdmloaimienChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmLoaiMienAction

 * JD-Core Version:    0.7.0.1

 */