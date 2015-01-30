 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmCachDungThuoc;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmThuocCachDungAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDieutri = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDieutri = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmCachDungThuoc", "dmcachdungthuocNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDieutri != null) {
       for (Object obj : listDieutri)
       {
         DmCachDungThuoc dieutri = (DmCachDungThuoc)obj;
         buf.append("<record  MaSo='" + dieutri.getDmcachdungthuocMaso() + "' Ma='" + dieutri.getDmcachdungthuocMa() + "' Ten='" + dieutri.getDmcachdungthuocTen() + "' DMCACHDUNGTHUOC_MAPHU='" + Utils.reFactorString(dieutri.getDmcachdungthuocMaphu()) + "' NgayChinhSua='" + dieutri.getDmcachdungthuocNgaygiocn() + "' DT='" + Utils.reFactorString(dieutri.getDmcachdungthuocDt()) + "' QL='" + Utils.reFactorString(dieutri.getDmcachdungthuocQl()) + "' DP='" + Utils.reFactorString(dieutri.getDmcachdungthuocDp()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmThuocCachDungAction

 * JD-Core Version:    0.7.0.1

 */