 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDoiTuongAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmDoiTuong = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmDoiTuong = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmDoiTuong", "dmdoituongNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmDoiTuong != null) {
       for (Object obj : listDmDoiTuong)
       {
         DmDoiTuong doituong = (DmDoiTuong)obj;
         String ten = Utils.findAndreplace(doituong.getDmdoituongTen());
         buf.append("<record  MaSo='" + doituong.getDmdoituongMaso() + "' Ma='" + doituong.getDmdoituongMa() + "' Ten='" + ten + "' DMDOITUONG_TYLEMIEN='" + doituong.getDmdoituongTylemien() + "' NgayChinhSua='" + Utils.reFactorString(doituong.getDmdoituongNgaygiocn()) + "' DMDOITUONG_MAGOM='" + Utils.reFactorString(doituong.getDmdoituongMagom()) + "' DMDOITUONG_THUTUBC='" + Utils.reFactorString(doituong.getDmdoituongThutubc()) + "' DMDOITUONG_CHON1='" + Utils.reFactorString(doituong.getDmdoituongChon1()) + "' DMDOITUONG_CHON2='" + Utils.reFactorString(doituong.getDmdoituongChon2()) + "' DMDOITUONG_KHOBHYT='" + Utils.reFactorString(doituong.getDmdoituongKhobhyt()) + "' DMDOITUONG_KHOLE='" + Utils.reFactorString(doituong.getDmdoituongKhole()) + "' DMDOITUONG_KHOCHINH='" + Utils.reFactorString(doituong.getDmdoituongKhochinh()) + "' DMDOITUONG_BENHAN='" + Utils.reFactorString(doituong.getDmdoituongBenhan()) + "' DMDOITUONG_THUPHI='" + Utils.reFactorString(doituong.getDmdoituongThuphi()) + "' DMDOITUONG_TIEUHAO='" + Utils.reFactorString(doituong.getDmdoituongTieuhao()) + "' DMDOITUONG_DINHDUONG='" + Utils.reFactorString(doituong.getDmdoituongDinhduong()) + "' DMDOITUONG_TENBC='" + Utils.reFactorString(doituong.getDmdoituongTenbc()) + "' DMDOITUONG_MAUPHIEU='" + Utils.reFactorString(doituong.getDmdoituongMauphieu()) + "' DT='" + Utils.reFactorString(doituong.getDmdoituongDt()) + "' QL='" + Utils.reFactorString(doituong.getDmdoituongQl()) + "' DP='" + Utils.reFactorString(doituong.getDmdoituongDp()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDoiTuongAction

 * JD-Core Version:    0.7.0.1

 */