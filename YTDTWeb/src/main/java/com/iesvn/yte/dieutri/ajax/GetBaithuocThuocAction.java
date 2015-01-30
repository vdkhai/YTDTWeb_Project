 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
 import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import java.util.List;

 public class GetBaithuocThuocAction
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

       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "BaithuocThuoc", "baithuocthuocNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         BaithuocThuoc pt = (BaithuocThuoc)obj;
         buf.append("<record  BAITHUOCTHUOC_MASO='" + pt.getBaithuocthuocMaso() + "' " + " DMBAITHUOC_MASO='" + pt.getDmbaithuocMaso().getDmbaithuocMaso() + "' " + " DMTHUOC_MASO='" + pt.getDmthuocMaso().getDmthuocMaso() + "' " + " NgayChinhSua='" + pt.getBaithuocthuocNgaygiocn() + "' " + " BAITHUOCTHUOC_SOLUONG='" + pt.getBaithuocthuocSoluong() + "' " + "/>");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetBaithuocThuocAction

 * JD-Core Version:    0.7.0.1

 */