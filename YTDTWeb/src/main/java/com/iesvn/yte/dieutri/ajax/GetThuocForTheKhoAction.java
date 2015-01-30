 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;

 public class GetThuocForTheKhoAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println(request);


     String masoThuoc = request.substring(0, request.indexOf("___"));
     request = request.substring(request.indexOf("___") + 3);


     String masoKhoa = request.substring(0, request.indexOf("___"));
     request = request.substring(request.indexOf("___") + 3);

     String tungay = request.substring(0, request.indexOf("___"));
     request = request.substring(request.indexOf("___") + 3);

     String denngay = request.substring(0, request.indexOf("___"));


     System.out.println("maThuoc:" + masoThuoc);

     System.out.println("maKhoa:" + masoKhoa);
     Integer thuocMaso = Integer.valueOf(0);
     if (masoThuoc != null) {
       thuocMaso = Integer.valueOf(masoThuoc);
     }
     Integer khoaMaso = Integer.valueOf(masoKhoa);

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     List<TonKho> listTonKho = null;
     Date fromDate = new Date();
     Date toDate = new Date();
     try
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       fromDate = df.parse(tungay);
       toDate = df.parse(denngay);
     }
     catch (ParseException e)
     {
       e.printStackTrace();
     }
     try
     {
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       listTonKho = tkDelegate.getTonKhoForTheKho(thuocMaso, khoaMaso, fromDate, toDate);
       if (listTonKho != null) {
         for (TonKho tonkho : listTonKho)
         {
           System.out.println("Ton kho ma " + tonkho.getTonkhoMa());
           String tonkhoma = tonkho.getTonkhoMa() == null ? "" : tonkho.getTonkhoMa().toString();
           String dmthuocma = tonkho.getDmthuocMaso() == null ? "" : tonkho.getDmthuocMaso().getDmthuocMa();
           String dmthuocten = tonkho.getDmthuocMaso() == null ? "" : tonkho.getDmthuocMaso().getDmthuocTen();
           String donvi = (tonkho.getDmthuocMaso() == null) || (tonkho.getDmthuocMaso().getDmdonvitinhMaso() == null) ? "" : tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMa();
           String dongia = tonkho.getTonkhoDongia() == null ? "" : Utils.formatNumber(tonkho.getTonkhoDongia(), "##.##");
           String ton = tonkho.getTonkhoTon() == null ? "" : Utils.formatNumber(tonkho.getTonkhoTon(), "##.##");
           String loai = tonkho.getDmthuocMaso().getDmthuocYcu() == null ? "" : tonkho.getDmthuocMaso().getDmthuocYcu().toString();
           String mathangloai = "";
           if (loai == "true") {
             mathangloai = "Y";
           } else {
             mathangloai = "T";
           }
           String hamLuong = "";
           if (tonkho.getDmthuocMaso().getDmthuocHamluong() != null) {
             hamLuong = tonkho.getDmthuocMaso().getDmthuocHamluong();
           }
           String nuocSx = "";
           if (tonkho.getDmquocgiaMaso(true).getDmquocgiaTen() != null) {
             nuocSx = tonkho.getDmquocgiaMaso(true).getDmquocgiaTen();
           }
           String hangSx = "";
           if (tonkho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen() != null) {
             nuocSx = tonkho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen();
           }
           String hanDung = "";
           if ((tonkho.getTonkhoNgayhandung() != null) && (tonkho.getTonkhoThanghandung() != null) && (tonkho.getTonkhoNamhandung() != null)) {
             hanDung = tonkho.getTonkhoNgayhandung() + "/" + tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
           } else if ((tonkho.getTonkhoThanghandung() != null) && (tonkho.getTonkhoNamhandung() != null)) {
             hanDung = tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
           }
           Boolean YC = tonkho.getDmthuocMaso().getDmthuocYeucau();
           if (YC == null) {
             YC = new Boolean(false);
           }
           Boolean khongThu = tonkho.getDmthuocMaso().getDmthuocKhongthu();
           if (khongThu == null) {
             khongThu = new Boolean(false);
           }
           Boolean mienPhi = tonkho.getDmthuocMaso().getDmthuocMien();
           if (mienPhi == null) {
             mienPhi = new Boolean(false);
           }
           Boolean trongDm = tonkho.getDmthuocMaso().getDmthuocIndanhmuc();
           Boolean ndm = Boolean.valueOf(false);
           if ((trongDm == null) || (!trongDm.booleanValue())) {
             ndm = new Boolean(true);
           } else {
             ndm = new Boolean(false);
           }
           buf.append("<record TonKhoMa='" + tonkhoma + "' MaHang='" + dmthuocma + "' TenHang='" + dmthuocten + "' HamLuong='" + hamLuong + "' TonKho='" + ton + "' HangSx='" + hangSx + "' DonVi='" + donvi + "' HanDung='" + hanDung + "' DonGia='" + dongia + "' Loai='" + mathangloai + "' NuocSx='" + nuocSx + "' MP='" + mienPhi + "' YC='" + YC + "' NDM='" + ndm + "' KhongThu='" + khongThu + "' Malk='" + tonkho.getTonkhoMalienket() + "' />");
         }
       } else {
         System.out.println("Khong co ton tai ma lien ket trong thoi gian nay");
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetThuocForTheKhoAction

 * JD-Core Version:    0.7.0.1

 */