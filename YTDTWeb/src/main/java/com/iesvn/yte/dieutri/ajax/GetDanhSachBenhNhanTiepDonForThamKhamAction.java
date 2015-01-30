 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class GetDanhSachBenhNhanTiepDonForThamKhamAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetDanhSachBenhNhanTiepDonForThamKhamAction.class);
   private static String FORMAT_DATE = "dd/MM/yyyy";

   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     String banKhamMa = "";
     String ngayThamKham = "";

     StringTokenizer sToken = new StringTokenizer(request, ";");
     if (sToken != null)
     {
       banKhamMa = sToken.nextToken();
       ngayThamKham = sToken.nextToken();
     }
     banKhamMa = banKhamMa.trim();
     ngayThamKham = ngayThamKham.trim();

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

     Date dNgayThamKham = new Date();
     if (!ngayThamKham.equals("")) {
       dNgayThamKham = formatter.parse(ngayThamKham);
     }
     ArrayList<String> lstBenhNhanMa = new ArrayList();



     buf.append("<list>");
     buf.append("<record  matiepdon='' hoten='' tuoi='' namsinh='' cmnd='' />");
     try
     {
       TiepDonDelegate tdDelagate = TiepDonDelegate.getInstance();
       List<TiepDon> listTd;
       if ("".equals(banKhamMa)) {
         listTd = tdDelagate.findTiepDonByNgay(dNgayThamKham);
       } else {
         listTd = tdDelagate.findTiepDonByBanKhamMaAndNgay(banKhamMa, dNgayThamKham);
       }
       if ((listTd != null) && (listTd.size() > 0)) {
         for (TiepDon td : listTd)
         {
           BenhNhan benhNhan = td.getBenhnhanMa();
           if (benhNhan != null)
           {
             Date ngaySinh = benhNhan.getBenhnhanNgaysinh();

             String sNgaySinh = "";
             if (ngaySinh != null) {
               sNgaySinh = formatter.format(Long.valueOf(ngaySinh.getTime()));
             }
             lstBenhNhanMa.add(benhNhan.getBenhnhanMa());

             buf.append("<record  matiepdon='" + td.getTiepdonMa() + "' hoten='" + benhNhan.getBenhnhanHoten() + "' tuoi='" + "" + "' namsinh='" + sNgaySinh + "' cmnd='" + "" + "' />");
           }
         }
       }
     }
     catch (Exception e)
     {
       log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
     }
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       List<ThamKham> arrayThamKham;
       if ("".equals(banKhamMa)) {
         arrayThamKham = tkDelegate.findThamKhamByNgay(dNgayThamKham);
       } else {
         arrayThamKham = tkDelegate.findThamKhamByBanKhamMaAndNgay(banKhamMa, dNgayThamKham);
       }
       if ((arrayThamKham != null) && (arrayThamKham.size() > 0)) {
         for (ThamKham thamKham : arrayThamKham)
         {
           BenhNhan benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
           if (benhNhan != null)
           {
             Date ngaySinh = benhNhan.getBenhnhanNgaysinh();

             String sNgaySinh = "";
             if (ngaySinh != null) {
               sNgaySinh = formatter.format(Long.valueOf(ngaySinh.getTime()));
             }
             boolean bContain = false;
             for (String str : lstBenhNhanMa) {
               if (str.equals(benhNhan.getBenhnhanMa()))
               {
                 bContain = true;
                 break;
               }
             }
             if (!bContain) {
               buf.append("<record  matiepdon='" + thamKham.getTiepdonMa().getTiepdonMa() + "' hoten='" + benhNhan.getBenhnhanHoten() + "' tuoi='" + "" + "' namsinh='" + sNgaySinh + "' cmnd='" + "" + "' />");
             }
           }
         }
       }
     }
     catch (Exception e)
     {
       log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
     }
     buf.append("</list>");




     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDanhSachBenhNhanTiepDonForThamKhamAction

 * JD-Core Version:    0.7.0.1

 */