 package com.iesvn.yte.util;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.StringTokenizer;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;

 public class ThamKhamUtil
 {
   public JasperPrint jasperPrintTD;
   public String reportPathTD;
   public String reportTypeTD;

   public void tinhToanChoHSTKKham(ThamKham thamkham, HsThtoank hsttk, Boolean ghinhan, List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(thamkham.getTiepdonMa());

     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);


     this.bVuotTuyen = hsthtoankUtilTmp.isbVuottuyen();
     if (ghinhan.booleanValue() == true)
     {
       listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp();



       clslist = hsthtoankUtilTmp.getListCtkq_temp();
     }
   }

   private boolean bVuotTuyen = false;

   public void XuatReport_don_thuoc_ketoa_quay(Logger log, ThamKham pthamkham, List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist, List<ThuocPhongKham> ctThuocPhongKhams, int index)
   {
     ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
     log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       String sub1Template = "";
       String sub2Template = "";
       String sub3Template = "";
       if (IConstantsRes.MAU_TOA_THUOC.equals("A4"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_main_A4.jrxml";


         sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub1_A4.jrxml";


         sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub2_A4.jrxml";


         sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub3_A4.jrxml";
       }
       else
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_main.jrxml";


         sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub1.jrxml";


         sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub2.jrxml";


         sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc_sub3.jrxml";
       }
       log.info("da thay file templete " + pathTemplate);
       log.info("da thay file sub 1 templete " + sub1Template);
       log.info("da thay file sub 2 templete " + sub2Template);
       log.info("da thay file sub 3 templete " + sub3Template);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);

       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);

       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);


       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);


       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);

       params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());


       params.put("MATHAMKHAM", thamkham.getThamkhamMa());
       if (thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen());
       }
       params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());

       String diachistr = "";
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ",";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);
       if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         String maTheBHYT = thamkham.getTiepdonMa().getTiepdonSothebh();
         params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));

         params.put("MABENHVIEN", maBV);
       }
       else
       {
         params.put("MATHEBHYT", "");
         params.put("MABENHVIEN", "");
       }
       if (thamkham.getThamkhamNgaygio() != null) {
         params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
       } else {
         params.put("NGAYKHAMBENH", new Date());
       }
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();

       DtDmNhanVien bacsi = (DtDmNhanVien)dtDele.findByMa(thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
       if (bacsi != null) {
         params.put("BASIKHAMBENH", bacsi.getDtdmnhanvienTen());
       }
       params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true).getDtdmnhanvienTen());


       HsThtoank hsttk = new HsThtoank();
       hsttk.setTiepdonMa(thamkham.getTiepdonMa());
       tinhToanChoHSTKKham(thamkham, hsttk, Boolean.valueOf(false), listCtTPK_temp, clslist);
       Utils.setInforForHsThToan(hsttk);

       params.put("BHXHTHANHTOAN", hsttk.getHsthtoankBhyt());
       params.put("NGUOIBENHTRA", hsttk.getHsthtoankThtoan());

       String tyleBNtra = "" + (100 - hsttk.getHsthtoankTylebh().shortValue());
       log.info("***********222tyleBNtra:" + tyleBNtra);
       if ("MP".equals(thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa())) {
         tyleBNtra = "0";
       }
       params.put("PHANTRAMBNTRA", tyleBNtra);

       int iTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null) {
         iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi().intValue();
       }
       int iDonviTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null) {
         iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
       }
       String sDonViTuoi = "";
       if (iDonviTuoi == 1) {
         sDonViTuoi = "";
       } else if (iDonviTuoi == 2) {
         sDonViTuoi = IConstantsRes.REPORT_THANG;
       } else if (iDonviTuoi == 3) {
         sDonViTuoi = IConstantsRes.REPORT_NGAY;
       }
       params.put("tuoi", iTuoi + " " + sDonViTuoi);
       params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen());
       if (this.bVuotTuyen) {
         params.put("VUOTTUYEN", "X");
       } else {
         params.put("VUOTTUYEN", "");
       }
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();

       String maChanDoan = "";
       String tenChanDoan = "";
       if ((thamkham.getBenhicd10() != null) && (thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String chanDoan = maChanDoan + " " + tenChanDoan;
       if ((thamkham.getThamkhamGhichu() != null) && (!thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + " , " + thamkham.getThamkhamGhichu();
       }
       if ((thamkham.getBenhicd10phu1() != null) && (thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((thamkham.getBenhicd10phu2() != null) && (thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((thamkham.getBenhicd10phu3() != null) && (thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((thamkham.getBenhicd10phu4() != null) && (thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((thamkham.getBenhicd10phu5() != null) && (thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       params.put("CHANDOAN", chanDoan);


       params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());


       String sHuyetApMin = "";
       String sHuyetApMax = "";
       String sHuyetAp = "";
       String sMach = "";
       String sNhietDo = "";
       if (thamkham.getTiepdonMa().getTiepdonHamin() != null) {
         sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonHamax() != null) {
         sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax().toString();
       }
       sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;
       if (thamkham.getTiepdonMa().getTiepdonMach() != null) {
         sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null) {
         sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo().toString();
       }
       params.put("NHIETDO", sNhietDo);

       params.put("HUYETAP", sHuyetAp);
       params.put("MACH", sMach);

       String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonChungsinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonChungsinh();
       }
       if ((ctThuocPhongKhams != null) && (ctThuocPhongKhams.size() > 0)) {
         for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
           if ((((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamLoai().equals("3")) && (((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan() != null) && (!((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan().equals("")))
           {
             params.put("LOIDAN", ((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan());
             break;
           }
         }
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);

       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", this.reportTypeTD);



       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);

       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   public String XuatReport_don_thuoc_ketoa_quay(Logger log, ThamKham pthamkham, List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist, List<ThuocPhongKham> ctThuocPhongKhams, boolean inTheoBK, boolean incls, int index)
   {
     ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
     log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham");
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       Map<String, Object> params = new HashMap();
       if (IConstantsRes.MAU_TOA_THUOC.equals("A4"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A4/toathuoc_main.jasper";

         params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A4/");
       }
       else
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A5/toathuoc_main.jasper";

         params.put("SUBREPORT_DIR", IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A5/");
       }
       log.info("da thay file templete " + pathTemplate);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       log.info("da thay file template ");

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       log.info("*****MATIEPDON: " + thamkham.getTiepdonMa().getTiepdonMa());
       params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());
       if (thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen() != null) {
         params.put("NOIDKKCBBANDAU", thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienTen());
       }
       params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
       String diachistr = "";
       if ((thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) && (!thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);


       String coquan = "";
       if (thamkham.getTiepdonMa().getTiepdonMacoquan() != null) {
         coquan = thamkham.getTiepdonMa().getTiepdonMacoquan();
       }
       params.put("COQUAN", coquan);
       if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         String maTheBHYT = thamkham.getTiepdonMa().getTiepdonSothebh();
         params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));

         params.put("MABENHVIEN", maBV);
       }
       else
       {
         params.put("MATHEBHYT", "");
         params.put("MABENHVIEN", "");
       }
       if (thamkham.getThamkhamNgaygio() != null) {
         params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
       } else {
         params.put("NGAYKHAMBENH", new Date());
       }
       params.put("BASIKHAMBENH", thamkham.getThamkhamBacsi() == null ? "" : thamkham.getThamkhamBacsi().getDtdmnhanvienTen());




















       int iTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null) {
         iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi().intValue();
       }
       int iDonviTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null) {
         iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
       }
       String sDonViTuoi = "";
       if (iDonviTuoi == 1) {
         sDonViTuoi = "";
       } else if (iDonviTuoi == 2) {
         sDonViTuoi = IConstantsRes.REPORT_THANG;
       } else if (iDonviTuoi == 3) {
         sDonViTuoi = IConstantsRes.REPORT_NGAY;
       }
       params.put("TUOI", iTuoi + " " + sDonViTuoi);
       if ((iDonviTuoi == 1) && (iTuoi > 6))
       {
         params.put("CHAME", "");
       }
       else if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame() != null)
       {
         String chame = "";
         chame = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame();
         params.put("CHAME", chame);
       }
       params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen());


       boolean bDoituongUutien = false;
       if ("BH".equals(thamkham.getTiepdonMa().getDoituongMa(true).getDmdoituongMa()))
       {
         Short tuyen = thamkham.getTiepdonMa().getTiepdonTuyen();
         DtDmKhoiBhyt khoiBHYT = thamkham.getTiepdonMa().getKhoibhytMa();
         String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
         int iTuoiKoXetTuyen = 200;
         if ((tuoikhongxettuyen != null) && (!tuoikhongxettuyen.equals(""))) {
           iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
         }
         if ((iTuoi >= iTuoiKoXetTuyen) && (iDonviTuoi == 1))
         {
           bDoituongUutien = false;
           if ((tuyen != null) && ((tuyen.shortValue() == 1) || (tuyen.shortValue() == 2))) {
             bDoituongUutien = true;
           }
         }
         else if ((tuyen != null) && (tuyen.shortValue() == 3))
         {
           bDoituongUutien = false;
         }
         else
         {
           bDoituongUutien = checkDTUuTien(khoiBHYT.getDtdmkhoibhytMa() == null ? "" : khoiBHYT.getDtdmkhoibhytMa());
         }
         if (!bDoituongUutien) {
           if ((tuyen != null) && ((tuyen.shortValue() == 2) || (tuyen.shortValue() == 3))) {
             if ((thamkham.getTiepdonMa().getTiepdonCoGiayGioiThieu() == null) || ((thamkham.getTiepdonMa().getTiepdonCoGiayGioiThieu() != null) && (!thamkham.getTiepdonMa().getTiepdonCoGiayGioiThieu().booleanValue()))) {
               this.bVuotTuyen = true;
             }
           }
         }
       }
       if (this.bVuotTuyen) {
         params.put("VUOTTUYEN", "X");
       } else {
         params.put("VUOTTUYEN", "");
       }
       String maChanDoan = "";
       String tenChanDoan = "";
       String chanDoan = "";
       if ((thamkham.getBenhicd10() != null) && (thamkham.getBenhicd10(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10(true).getDmbenhicdTen();
         chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getThamkhamGhichu() != null) && (!thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + ", " + thamkham.getThamkhamGhichu();
       }
       if ((thamkham.getBenhicd10phu1() != null) && (thamkham.getBenhicd10phu1(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu2() != null) && (thamkham.getBenhicd10phu2(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu3() != null) && (thamkham.getBenhicd10phu3(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu4() != null) && (thamkham.getBenhicd10phu4(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu5() != null) && (thamkham.getBenhicd10phu5(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       params.put("CHANDOAN", chanDoan);
       if (inTheoBK) {
         params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamMaso());
       } else {
         params.put("PHONGKHAM", null);
       }
       if (incls) {
         params.put("IS_PRINTCLS", "1");
       } else {
         params.put("IS_PRINTCLS", "0");
       }
       String sHuyetApMin = "";
       String sHuyetApMax = "";
       String sHuyetAp = "";
       String sMach = "";
       String sNhietDo = "";
       if (thamkham.getTiepdonMa().getTiepdonHamin() != null) {
         sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonHamax() != null) {
         sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax().toString();
       }
       sHuyetAp = "     " + sHuyetApMax + " / " + sHuyetApMin;
       if (thamkham.getTiepdonMa().getTiepdonMach() != null) {
         sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null) {
         sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo().toString();
       }
       params.put("NHIETDO", sNhietDo);

       params.put("HUYETAP", sHuyetAp);
       params.put("MACH", sMach);

       String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonChungsinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonChungsinh();
       }
       if ((ctThuocPhongKhams != null) && (ctThuocPhongKhams.size() > 0)) {
         for (int i = 0; i < ctThuocPhongKhams.size(); i++) {
           if ((((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamLoai().equals("3")) && (((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan() != null) && (!((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan().equals("")))
           {
             params.put("LOIDAN", ((ThuocPhongKham)ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan());
             break;
           }
         }
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(pathTemplate, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", this.reportTypeTD);


       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);


       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
       return "";
     }
     log.info("Thoat Method XuatReport");
     return baocao1;
   }

   public String XuatReportBNVe(Logger log, ThamKham pthamkham, List<ThuocPhongKham> listCtTPK_temp, List<ClsKham> clslist, List<ThuocPhongKham> ctThuocPhongKhams, int index)
   {
     log.info("Vao Method XuatReport bao cao xu tri thuoc tai ban kham");
     ThamKham thamkham = ThamKhamDelegate.getInstance().find(pthamkham.getThamkhamMa());
     String baocao1 = null;
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = "";
       Map<String, Object> params = new HashMap();
       if (IConstantsRes.MAU_TOA_THUOC.equals("A4")) {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A4/toathuocve.jasper";
       } else {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/toathuoc/A5/toathuocve.jasper";
       }
       log.info("da thay file templete " + pathTemplate);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       log.info("da thay file template ");

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("MATIEPDON", thamkham.getTiepdonMa().getTiepdonMa());

       params.put("NHANVIENCN", thamkham.getThamkhamNhanviencn(true).getDtdmnhanvienTen());
       params.put("MATHAMKHAM", thamkham.getThamkhamMa());

       params.put("HOTENBN", thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHoten());
       String diachistr = "";
       if ((thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() != null) && (!thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi().equals(""))) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDiachi() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ", ";
       }
       if (thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + thamkham.getTiepdonMa().getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);


       String coquan = "";
       if (thamkham.getTiepdonMa().getTiepdonMacoquan() != null) {
         coquan = thamkham.getTiepdonMa().getTiepdonMacoquan();
       }
       params.put("COQUAN", coquan);
       if (thamkham.getTiepdonMa().getTiepdonGiatri1() != null) {
         params.put("GTTU", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri1()));
       } else {
         params.put("GTTU", "");
       }
       if (thamkham.getTiepdonMa().getTiepdonGiatri2() != null) {
         params.put("GTDEN", sdf.format(thamkham.getTiepdonMa().getTiepdonGiatri2()));
       } else {
         params.put("GTDEN", "");
       }
       if ((thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         String maBV = thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         params.put("MATHEBHYT", thamkham.getTiepdonMa().getTiepdonSothebh() + "-" + thamkham.getTiepdonMa().getKhoibhytMa(true).getDtdmkhoibhytMa() + "-" + maBV.replace(".", "-"));




         log.info("MATHEBHYT:" + params.get("MATHEBHYT"));
       }
       else
       {
         params.put("MATHEBHYT", "");
       }
       if (thamkham.getThamkhamNgaygio() != null) {
         params.put("NGAYKHAMBENH", thamkham.getThamkhamNgaygio());
       } else {
         params.put("NGAYKHAMBENH", new Date());
       }
       int iTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi() != null) {
         iTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanTuoi().intValue();
       }
       int iDonviTuoi = 1;
       if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi() != null) {
         iDonviTuoi = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
       }
       String sDonViTuoi = "";
       if (iDonviTuoi == 1)
       {
         sDonViTuoi = "";
         params.put("TUOI", iTuoi + "");
       }
       else if (iDonviTuoi == 2)
       {
         sDonViTuoi = IConstantsRes.REPORT_THANG;
         params.put("TUOI", iTuoi + " " + sDonViTuoi);
       }
       else if (iDonviTuoi == 3)
       {
         sDonViTuoi = IConstantsRes.REPORT_NGAY;
         params.put("TUOI", iTuoi + " " + sDonViTuoi);
       }
       if ((iDonviTuoi == 1) && (iTuoi > 6))
       {
         params.put("CHAME", "");
       }
       else if (thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame() != null)
       {
         String chame = "";
         chame = thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanHotenchame();
         params.put("CHAME", chame);
       }
       DieuTriUtilDelegate dtDele = DieuTriUtilDelegate.getInstance();
       DtDmNhanVien bacsi = (DtDmNhanVien)dtDele.findByMa(thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");
       if (bacsi != null) {
         params.put("BASIKHAMBENH", bacsi.getDtdmnhanvienTen());
       }
       params.put("GIOITINH", thamkham.getTiepdonMa().getBenhnhanMa().getDmgtMaso().getDmgtTen());

       String maChanDoan = "";
       String tenChanDoan = "";
       String chanDoan = "";
       if ((thamkham.getBenhicd10() != null) && (thamkham.getBenhicd10(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10(true).getDmbenhicdTen();
         chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getThamkhamGhichu() != null) && (!thamkham.getThamkhamGhichu().equals(""))) {
         chanDoan = chanDoan + ", " + thamkham.getThamkhamGhichu();
       }
       if ((thamkham.getBenhicd10phu1() != null) && (thamkham.getBenhicd10phu1(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu1(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu2() != null) && (thamkham.getBenhicd10phu2(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu2(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu3() != null) && (thamkham.getBenhicd10phu3(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu3(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu4() != null) && (thamkham.getBenhicd10phu4(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu4(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       if ((thamkham.getBenhicd10phu5() != null) && (thamkham.getBenhicd10phu5(true).getDmbenhicdMaso() != null))
       {
         maChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdMa();
         tenChanDoan = thamkham.getBenhicd10phu5(true).getDmbenhicdTen();
         chanDoan = chanDoan + ", " + maChanDoan + " " + tenChanDoan;
       }
       params.put("CHANDOAN", chanDoan);

       params.put("PHONGKHAM", thamkham.getThamkhamBankham().getDtdmbankhamTen());

       String sHuyetApMin = "";
       String sHuyetApMax = "";
       String sHuyetAp = "";
       String sMach = "";
       String sNhietDo = "";
       if (thamkham.getTiepdonMa().getTiepdonHamin() != null) {
         sHuyetApMin = thamkham.getTiepdonMa().getTiepdonHamin().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonHamax() != null) {
         sHuyetApMax = thamkham.getTiepdonMa().getTiepdonHamax().toString();
       }
       sHuyetAp = sHuyetApMax + " / " + sHuyetApMin;
       if (thamkham.getTiepdonMa().getTiepdonMach() != null) {
         sMach = thamkham.getTiepdonMa().getTiepdonMach().toString();
       }
       if (thamkham.getTiepdonMa().getTiepdonNhietdo() != null) {
         sNhietDo = thamkham.getTiepdonMa().getTiepdonNhietdo().toString();
       }
       params.put("NHIETDO", sNhietDo);

       params.put("HUYETAP", sHuyetAp);
       params.put("MACH", sMach);

       String soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonSothete();
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonKhaisinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonKhaisinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonKhaisinh();
       }
       if ((soTheTe_KhaiSinh_ChungSinh == null) || (soTheTe_KhaiSinh_ChungSinh.equals("")))
       {
         if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (!thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
           soTheTe_KhaiSinh_ChungSinh = thamkham.getTiepdonMa(true).getTiepdonChungsinh();
         }
       }
       else if ((thamkham.getTiepdonMa(true).getTiepdonChungsinh() != null) && (thamkham.getTiepdonMa(true).getTiepdonChungsinh().equals(""))) {
         soTheTe_KhaiSinh_ChungSinh = soTheTe_KhaiSinh_ChungSinh + "/" + thamkham.getTiepdonMa(true).getTiepdonChungsinh();
       }
       if ((ctThuocPhongKhams != null) && (ctThuocPhongKhams.size() > 0)) {
         params.put("LOIDAN", ((ThuocPhongKham)ctThuocPhongKhams.get(0)).getThuocphongkhamTenloidan());
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
         return "";
       }
       this.jasperPrintTD = JasperFillManager.fillReport(pathTemplate, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", this.reportTypeTD);
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);

       log.info("Index :" + index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
       return "";
     }
     log.info("Thoat Method XuatReport");
     return baocao1;
   }

   public String getMaTheBHYTDisplay(String maThe)
   {
     String maKhoi = maThe.substring(0, 2);
     String maThe1 = maThe.substring(2, 3);
     String maThe2 = maThe.substring(3, 5);
     String maThe3 = maThe.substring(5, 7);
     String maThe4 = maThe.substring(7, 10);
     String maThe5 = maThe.substring(10, maThe.length());
     String strMaThe = maKhoi + " " + maThe1 + " " + maThe2 + " " + maThe3 + " " + maThe4 + " " + maThe5;

     return strMaThe;
   }

   private boolean checkDTUuTien(String maDT)
   {
     boolean dtUutien = false;
     String dsUuTien = IConstantsRes.DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN;
     if ((dsUuTien != null) && (!dsUuTien.equals("")))
     {
       StringTokenizer sTk = new StringTokenizer(dsUuTien, ",");
       ArrayList<String> arrayDS = new ArrayList();
       while (sTk.hasMoreTokens())
       {
         String khoi = sTk.nextToken();
         arrayDS.add(khoi);
       }
       if (arrayDS.contains(maDT)) {
         dtUutien = true;
       }
     }
     return dtUutien;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.ThamKhamUtil

 * JD-Core Version:    0.7.0.1

 */