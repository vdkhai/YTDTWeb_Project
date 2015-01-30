 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmHocVi;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetNhanVienAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listNV = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listNV = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmNhanVien", "dtdmnhanvienNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listNV != null) {
       for (Object obj : listNV)
       {
         DtDmNhanVien nv = (DtDmNhanVien)obj;
         buf.append("<record MaSo='" + nv.getDtdmnhanvienMaso() + "' Ma='" + nv.getDtdmnhanvienMa() + "' HocVi='" + nv.getDmhocviMaso(true).getDmhocviMaso() + "' Ten='" + nv.getDtdmnhanvienMa() + " " + nv.getDtdmnhanvienTen() + "' DT='" + Utils.reFactorString(nv.getDtdmnhanvienChon()) + "' NgayChinhSua='" + nv.getDtdmnhanvienNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetNhanVienAction

 * JD-Core Version:    0.7.0.1

 */