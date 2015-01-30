 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.util.Utils;

 public class GetPriceDtDmKyThuatAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     DtDmClsBangGia bangGia = null;
     try
     {
       DtDmClsBangGiaDelegate delegate = DtDmClsBangGiaDelegate.getInstance();
       Integer iRequest = new Integer(request);
       bangGia = delegate.find(iRequest);
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     buf.append("<record MaSo='' DonGia='' DonGiaBH=''  DonGiaYC='' />");
     if (bangGia != null) {
       buf.append("<record MaSo='" + bangGia.getDtdmclsbgMa() + "' DonGia='" + Utils.formatNumber(bangGia.getDtdmclsbgDongia(), "###.####") + "' DonGiaBH='" + Utils.formatNumber(bangGia.getDtdmclsbgDongiabh(), "###.####") + "' DonGiaMP='" + Utils.formatNumber(bangGia.getDtdmclsbgDongiamp(), "###.####") + "' DonGiaYC='" + Utils.formatNumber(bangGia.getDtdmclsbgDongiayc(), "###.####") + "' PhanDV='" + Utils.formatNumber(bangGia.getDtdmclsbgPhandv(), "###.####") + "' ngoaiDM='" + bangGia.getDtdmclsbgNDM().booleanValue() + "' mien='" + bangGia.getDtdmclsbgMien().booleanValue() + "' ktc='" + (bangGia.getDtdmclsbgPhanloai().getDtdmclsMaso().intValue() == 9) + "' NgayChinhSua='" + Utils.reFactorString(bangGia.getDtdmclsbgNgaygiocn()) + "' DT='" + bangGia.getDtdmclsbgChon() + "' />");
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetPriceDtDmKyThuatAction

 * JD-Core Version:    0.7.0.1

 */