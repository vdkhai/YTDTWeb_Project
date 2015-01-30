 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmGioiTinh;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;

 public class GetCLSAction
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
       TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       TiepDon td = tdDelegate.find(request);
       if (td == null)
       {
         buf.append("</list>");
         return buf.toString();
       }
       System.out.println("td ma " + td.getTiepdonMa());

       mabaohiem = td.getTiepdonSothebh() == null ? "" : td.getTiepdonSothebh();
       noidangky = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienTen();
       madangky = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienMa();
       if (td.getTiepdonGiatri2() != null)
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date cal = td.getTiepdonGiatri2();
         ngayhethan = df.format(Long.valueOf(cal.getTime()));
       }
       BenhNhan bn = td.getBenhnhanMa();
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
       ThamKham tk = tkDelegate.findByMaTiepDon(td.getTiepdonMa());
       String bankham = tk.getThamkhamBankham(true).getDtdmbankhamTen();
       String chuandoan = tk.getBenhicd10(true).getDmbenhicdTen();
       String chuandoanBD = td.getTiepdonMachdoanbd(true).getDmbenhicdTen();
       String bacsi = tk.getThamkhamBacsi() != null ? tk.getThamkhamBacsi().getDtdmnhanvienTen() : "";

       List<ClsKham> listClsKham = clsKhamDelegate.findByTiepdonmaAndKhoa(td.getTiepdonMa(), "30");
       soluongCLS = listClsKham.size() + "";

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
       for (int i = 0; i < listClsKham.size(); i++)
       {
         result = result + "<CLS ";
         ClsKham clsKham = (ClsKham)listClsKham.get(i);
         String loaiCLS = "";
         String ngaykham = "";
         String maCLS = "";
         if (clsKham.getClskhamNgaygio() != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date cal = clsKham.getClskhamNgaygio();
           ngaykham = df.format(Long.valueOf(cal.getTime()));
         }
         maCLS = clsKham.getClskhamMahang() == null ? "" : clsKham.getClskhamMahang().getDtdmclsbgMa();
         loaiCLS = clsKham.getClskhamMahang() == null ? "" : clsKham.getClskhamMahang().getDtdmclsbgDiengiai();
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

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetCLSAction

 * JD-Core Version:    0.7.0.1

 */