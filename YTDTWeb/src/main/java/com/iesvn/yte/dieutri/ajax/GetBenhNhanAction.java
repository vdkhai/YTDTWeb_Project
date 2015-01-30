 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;

 public class GetBenhNhanAction
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### GetBenhNhanAction #####");

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
       BenhNhan bn = bnDelegate.find(request);
       if (bn != null)
       {
         System.out.println("bn ma " + bn.getBenhnhanMa());

         String hoten = bn.getBenhnhanHoten() == null ? "" : bn.getBenhnhanHoten();
         String ngaysinh = "";
         String dantoc = bn.getDantocMa() == null ? "" : bn.getDantocMa().getDmdantocMaso().toString();
         String quocgia = bn.getQuocgiaMa() == null ? "" : bn.getQuocgiaMa().getDmquocgiaMa();
         String tinh = bn.getTinhMa() == null ? "" : bn.getTinhMa().getDmtinhMa();
         String huyen = bn.getHuyenMa() == null ? "" : bn.getHuyenMa().getDmhuyenMa();
         String xa = bn.getXaMa() == null ? "" : bn.getXaMa().getDmxaMa();
         String diachi = bn.getBenhnhanDiachi() == null ? "" : bn.getBenhnhanDiachi();
         String nghe = bn.getBenhnhanNghe() == null ? "" : bn.getBenhnhanNghe().getDmnghenghiepMaso().toString();


         String nhanvien = bn.getNhanvienMa() == null ? "" : bn.getNhanvienMa().getDtdmnhanvienMa();
         String ngaygiocn = "";
         String gioitinh = bn.getDmgtMaso() == null ? "" : bn.getDmgtMaso().getDmgtMa();
         String tuoi = bn.getBenhnhanTuoi() == null ? "" : bn.getBenhnhanTuoi().toString();
         String donvituoi = bn.getBenhnhanDonvituoi() == null ? "" : bn.getBenhnhanDonvituoi().toString();
         String cmd = bn.getBenhnhanCmnd() == null ? "" : bn.getBenhnhanCmnd();
         String namsinh = bn.getBenhnhanNamsinh() == null ? "" : bn.getBenhnhanNamsinh();
         if (bn.getBenhnhanNgaysinh() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = bn.getBenhnhanNgaysinh();
           ngaysinh = df.format(Long.valueOf(cal.getTime()));
         }
         if (bn.getBenhnhanNgaygiocn() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = bn.getBenhnhanNgaygiocn();
           ngaygiocn = df.format(Long.valueOf(cal.getTime()));
         }
         buf.append("<BENH_NHAN MAPHU='' BENHNHAN_MA='" + bn.getBenhnhanMa() + "' BENHNHAN_HOTEN='" + hoten + "' BENHNHAN_NGAYSINH='" + ngaysinh + "' BENHNHAN_GIOI='" + gioitinh + "' BENHNHAN_TUOI='" + tuoi + "' BENHNHAN_NAMSINH='" + namsinh + "' BENHNHAN_DONVITUOI='" + donvituoi + "' BENHNHAN_CMND='" + cmd + "' DANTOC_MA='" + dantoc + "' QUOCGIA_MA='" + quocgia + "' TINH_MA='" + tinh + "' HUYEN_MA='" + huyen + "' XA_MA='" + xa + "' BENHNHAN_DIACHI='" + diachi + "' BENHNHAN_NGHE='" + nghe + "' BENHNHAN_NGAYGIOCN='" + ngaygiocn + "' NHANVIEN_MA='" + nhanvien + "'/>");
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

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetBenhNhanAction

 * JD-Core Version:    0.7.0.1

 */