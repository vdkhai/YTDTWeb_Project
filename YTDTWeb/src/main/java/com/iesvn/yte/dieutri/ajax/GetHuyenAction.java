 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetHuyenAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmHuyen = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmHuyen = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmHuyen", "dmhuyenNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmHuyen != null) {
       for (Object obj : listDmHuyen)
       {
         DmHuyen huyen = (DmHuyen)obj;
         String ten = huyen.getDmhuyenTen();
         ten = Utils.findAndreplace(ten);
         String tinhMa = "";
         if (huyen.getDmtinhMaso() != null) {
           tinhMa = huyen.getDmtinhMaso().getDmtinhMaso().toString();
         }
         buf.append("<record MaSo='" + huyen.getDmhuyenMaso() + "' Ma='" + huyen.getDmhuyenMa() + "' DMTINH_MASO='" + tinhMa + "' Ten='" + ten + "' DMHUYEN_DEFA='" + Utils.reFactorString(huyen.getDmhuyenDefa()) + "' DT='" + Utils.reFactorString(huyen.getDmhuyenChon()) + "' NgayChinhSua='" + huyen.getDmhuyenNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetHuyenAction

 * JD-Core Version:    0.7.0.1

 */