 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVn;
 import com.iesvn.yte.entity.DmChuongBenh;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetBenhIcdAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmBenhIcd = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmBenhIcd = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmBenhIcd", "dmbenhicdNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmBenhIcd != null) {
       for (Object obj : listDmBenhIcd)
       {
         DmBenhIcd dmIcd = (DmBenhIcd)obj;
         String ten = dmIcd.getDmbenhicdTen();

         Boolean bTacNhan = dmIcd.getDmbenhicdTacnhan();
         String sTacNhan = "0";
         if ((bTacNhan != null) && (bTacNhan.booleanValue())) {
           sTacNhan = "1";
         }
         DmChuongBenh chuongBenh = dmIcd.getDmchuongbenhMaso();
         String maChuong = "";
         if (chuongBenh != null) {
           maChuong = String.valueOf(chuongBenh.getDmchuongbenhMaso());
         }
         DmBenhVn benhVN = dmIcd.getDmbenhicdBenhvn();
         String maBenhVN = "";
         if (benhVN != null) {
           maBenhVN = String.valueOf(benhVN.getDmbenhvnMaso());
         }
         ten = Utils.findAndreplace(ten);
         buf.append("<record  MaSo='" + dmIcd.getDmbenhicdMaso() + "' Ma='" + dmIcd.getDmbenhicdMa() + "' Ten='" + ten + "' DMBENHICD_TACNHAN='" + sTacNhan + "' NgayChinhSua='" + Utils.reFactorString(dmIcd.getDmbenhicdNgaygiocn()) + "' DMBENHICD_MACHUNG='" + Utils.reFactorString(dmIcd.getDmbenhicdMachung()) + "' DMCHUONGBENH_MASO='" + maChuong + "' DMBENHICD_LIENHE='" + Utils.reFactorString(dmIcd.getDmbenhicdLienhe()) + "' DMBENHICD_BENHVN='" + maBenhVN + "' DT='" + Utils.reFactorString(dmIcd.getDmbenhicdDt()) + "' QL='" + Utils.reFactorString(dmIcd.getDmbenhicdQl()) + "' DP='" + Utils.reFactorString(dmIcd.getDmbenhicdDp()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetBenhIcdAction

 * JD-Core Version:    0.7.0.1

 */