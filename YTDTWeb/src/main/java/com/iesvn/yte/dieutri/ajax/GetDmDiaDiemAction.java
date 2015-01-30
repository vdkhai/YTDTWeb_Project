 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDiaDiem;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmDiaDiemAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List list = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmDiaDiem", "dmdiadiemNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DmDiaDiem diadiem = (DmDiaDiem)obj;
         buf.append("<record   MaSo='" + diadiem.getDmdiadiemMaso() + "' Ma='" + diadiem.getDmdiadiemMa() + "' Ten='" + diadiem.getDmdiadiemTen() + "' NgayChinhSua='" + diadiem.getDmdiadiemNgaygiocn() + "' QL='" + Utils.reFactorString(diadiem.getDmdiadiemQl()) + "' DT='" + Utils.reFactorString(diadiem.getDmdiadiemDt()) + "' DP='" + Utils.reFactorString(diadiem.getDmdiadiemDp()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmDiaDiemAction

 * JD-Core Version:    0.7.0.1

 */