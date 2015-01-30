 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;

 public class GetCLSActionNT
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### GetCLSAction #####");
     String hotenbenhnhan = "";
     String tuoi = "";
     String donvituoi = "";
     String namsinh = "";
     String gioitinh = "";
     String diachi = "";
     String mabaohiem = "";
     String noidangky = "";
     String madangky = "";
     String ngayhethan = "";
     String soluongCLS = "";

     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();
       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       if (request.startsWith("NO")) {
         request = request.replaceFirst("NO", "BD");
       }
       Hsba hsba = hsbaDelegate.find(request);
       if (hsba == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       System.out.println("hsbasovaovien: " + hsba.getHsbaSovaovien());

       List<HsbaBhyt> listTmp = hsbaBhytDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
       if ((listTmp != null) && (listTmp.size() > 0))
       {
         HsbaBhyt hsbaBhyt = (HsbaBhyt)listTmp.get(listTmp.size() - 1);
         mabaohiem = hsbaBhyt == null ? "" : hsbaBhyt.getHsbabhytSothebh();
         noidangky = hsbaBhyt.getHsbabhytMakcb() == null ? "" : hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen();
         madangky = hsbaBhyt.getHsbabhytMakcb() == null ? "" : hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa();
         if (hsbaBhyt.getHsbabhytGiatri1() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = hsbaBhyt.getHsbabhytGiatri1();
           ngayhethan = df.format(Long.valueOf(cal.getTime()));
         }
       }
       BenhNhan bn = hsba.getBenhnhanMa();
       if (bn != null)
       {
         hotenbenhnhan = bn.getBenhnhanHoten();
         tuoi = bn.getBenhnhanTuoi() + "";
         if (bn.getBenhnhanDonvituoi().shortValue() == 1) {
           donvituoi = "năm";
         } else if (bn.getBenhnhanDonvituoi().shortValue() == 2) {
           donvituoi = "tháng";
         } else if (bn.getBenhnhanDonvituoi().shortValue() == 3) {
           donvituoi = "ngày";
         }
         namsinh = bn.getBenhnhanNamsinh();
         gioitinh = bn.getDmgtMaso() == null ? "" : bn.getDmgtMaso().getDmgtTen();
         diachi = bn.getBenhnhanDiachi();
       }
       String bankham = "";
       if (hsba.getHsbaKhoadangdt() != null) {
         bankham = hsba.getHsbaKhoadangdt().getDmkhoaTen();
       }
       HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsba.getHsbaSovaovien());
       String chuandoan = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
       String chuandoanBD = "";
       String bacsi = hsbaChuyenMon.getHsbacmBacsi(true).getDtdmnhanvienTen();

       List<ClsMo> listClsMoTemp = clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
       List<ClsMo> listClsMo = new ArrayList();
       for (ClsMo c : listClsMoTemp) {
         if ((c.getClsmoKhoa() != null) && (c.getClsmoKhoa().getDmkhoaMaso().intValue() == 30)) {
           listClsMo.add(c);
         }
       }
       soluongCLS = listClsMo.size() + "";

       String result = "";
       result = result + "<RECORD ";
       result = result + "HOTEN='" + hotenbenhnhan + "' ";
       result = result + "TUOI='" + tuoi + "' ";
       result = result + "DONVITUOI='" + donvituoi + "' ";
       result = result + "NAMSINH='" + namsinh + "' ";
       result = result + "GIOITINH='" + gioitinh + "' ";
       result = result + "DIACHI='" + diachi + "' ";
       result = result + "MABAOHIEM='" + mabaohiem + "' ";
       result = result + "MADANGKY='" + madangky + "' ";
       result = result + "NOIDANGKY='" + noidangky + "' ";
       result = result + "NGAYHETHAN='" + ngayhethan + "' ";
       result = result + "BANKHAM='" + bankham + "' ";
       result = result + "CHUANDOAN='" + chuandoan + "' ";
       result = result + "CHUANDOANBD='" + chuandoanBD + "' ";
       result = result + "BACSI='" + bacsi + "' ";
       result = result + " >";

       result = result + "<LISTCLS ";
       result = result + "SOLUONG='" + soluongCLS + "' ";
       result = result + " >";
       for (int i = 0; i < listClsMo.size(); i++)
       {
         result = result + "<CLS ";
         ClsMo clsMo = (ClsMo)listClsMo.get(i);
         String loaiCLS = "";
         String ngaykham = "";
         String maCLS = "";
         if (clsMo.getClsmoNgay() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = clsMo.getClsmoNgay();
           ngaykham = df.format(Long.valueOf(cal.getTime()));
         }
         maCLS = clsMo.getClsmoMahang() == null ? "" : clsMo.getClsmoMahang().getDtdmclsbgMa();
         loaiCLS = clsMo.getClsmoMahang() == null ? "" : clsMo.getClsmoMahang().getDtdmclsbgDiengiai();
         result = result + "LOAICLS='" + loaiCLS + "' ";
         result = result + "NGAYKHAM='" + ngaykham + "' ";
         result = result + "MACLS='" + maCLS + "' ";
         result = result + " />";
       }
       result = result + " </LISTCLS>";

       result = result + " </RECORD>";
       buf.append(result);
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

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetCLSActionNT

 * JD-Core Version:    0.7.0.1

 */