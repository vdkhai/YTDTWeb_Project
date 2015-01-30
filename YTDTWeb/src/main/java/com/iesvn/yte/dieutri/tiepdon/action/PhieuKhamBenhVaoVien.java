 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuKbVaoVienDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.PhieuKbVaoVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B120_Phieukhambenhvaovien")
 @Synchronized(timeout=6000000L)
 public class PhieuKhamBenhVaoVien
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String sMaPhieuKVV;
   private String ngaySinh = "";
   private String gioi = "";
   private PhieuKbVaoVien objPhieuKBVaoVien = new PhieuKbVaoVien();
   private String sShowDel = "";
   private String sShowPrint = "";
   private String ylenh;
   private String ngay;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToPhieuKhamBenhVaoVien;
   @Out(required=false)
   private String bacSiKCB;
   private BenhNhan benhNhan;
   private ThamKham thamkham;

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToPhieuKhamBenhVaoVien")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();


       this.thamkham = null;
       this.benhNhan = new BenhNhan();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if (this.thamkham != null)
       {
         this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
         this.objPhieuKBVaoVien = PhieuKbVaoVienDelegate.getInstance().findByMaThamKham(this.thamkham.getThamkhamMa());
         if (this.objPhieuKBVaoVien != null)
         {
           this.sShowDel = "true";
           this.sShowPrint = "true";
           this.sMaPhieuKVV = this.objPhieuKBVaoVien.getPkbvvMa();
         }
         else
         {
           this.objPhieuKBVaoVien = new PhieuKbVaoVien();
           this.sMaPhieuKVV = "";
           this.sShowDel = "false";
           this.sShowPrint = "false";




           String cacxetnghiem = "";

           ClsKhamDelegate clskhamDele = ClsKhamDelegate.getInstance();
           List<ClsKham> listCLS = clskhamDele.findByBanKhamVaMaTiepDon(this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), this.thamkham.getTiepdonMa(true).getTiepdonMa());
           cacxetnghiem = "";
           if ((listCLS != null) && (listCLS.size() > 0)) {
             for (ClsKham cls : listCLS) {
               cacxetnghiem = cacxetnghiem + cls.getClskhamMahang(true).getDtdmclsbgDiengiai() + "; ";
             }
           } else {
             cacxetnghiem = "Không có";
           }
           this.objPhieuKBVaoVien.setPkbvvTtketqualamsang(cacxetnghiem);


           DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
           String maChanDoan = "";
           String tenChanDoan = "";
           String chanDoan = "";
           if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
             }
           }
           chanDoan = maChanDoan + " " + tenChanDoan;

           this.objPhieuKBVaoVien.setPkbvvLydovaovien(chanDoan);
           if ((this.thamkham.getBenhicd10phu1() != null) && (this.thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
               chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
             }
           }
           if ((this.thamkham.getBenhicd10phu2() != null) && (this.thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
               if (chanDoan.equals("")) {
                 chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
               } else {
                 chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
               }
             }
           }
           if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
               if (chanDoan.equals("")) {
                 chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
               } else {
                 chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
               }
             }
           }
           if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
               if (chanDoan.equals("")) {
                 chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
               } else {
                 chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
               }
             }
           }
           if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
           {
             DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
             if (benh != null)
             {
               maChanDoan = benh.getDmbenhicdMa();
               tenChanDoan = benh.getDmbenhicdTen();
               if (chanDoan.equals("")) {
                 chanDoan = chanDoan + maChanDoan + " " + tenChanDoan;
               } else {
                 chanDoan = chanDoan + " , " + maChanDoan + " " + tenChanDoan;
               }
             }
           }
           this.objPhieuKBVaoVien.setPkbvvCdvaovien(chanDoan);


           SimpleDateFormat sdfformat = new SimpleDateFormat(FORMAT_DATE);
           if (this.thamkham.getThamkhamNgaygio() != null) {
             this.ngay = sdfformat.format(this.thamkham.getThamkhamNgaygio());
           }
           get_thuoc_info();

           this.objPhieuKBVaoVien.setPkbvvDaxuly(this.ylenh);
         }
         if (this.benhNhan.getDmgtMaso() != null)
         {
           if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
             this.gioi = "r1";
           } else {
             this.gioi = "r2";
           }
         }
         else {
           this.gioi = null;
         }
         setOtherValue();
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_THAMKHAM, new Object[0]);
         log.info(" ### Khong tim thay THAMKHAM nao ung voi Ma Ban kham: " + this.maBanKhamOut + " ### va Ma Tiep don: " + this.maTiepDonOut);
       }
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
     }
     log.info("***Finished init ***");
   }

   @End
   public void end() {}

   public String ghiNhan()
     throws Exception
   {
     log.info("*****Begin ghiNhan() *****");
     try
     {
       PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel = PhieuKbVaoVienDelegate.getInstance();

       this.objPhieuKBVaoVien.setPkbvvThamkham(this.thamkham);
       this.sMaPhieuKVV = PhieuKhamDtNgoaiTruDel.capNhatPhieuKbVaoVien(this.objPhieuKBVaoVien, this.sMaPhieuKVV);
       FacesMessages.instance().add(IConstantsRes.RPPKBVV_INSERT_SUCCESS, new Object[] { this.sMaPhieuKVV });
       log.info("*****CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
       this.sShowDel = "true";
       this.sShowPrint = "true";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.error("*************loi***********" + e.toString());
       return null;
     }
     log.info("*****End ghiNhan() *****");
     return null;
   }

   public void huyPhieu()
   {
     log.info("***** start  huyPhieu() *****");
     if ((this.sMaPhieuKVV == null) || (this.sMaPhieuKVV.equals(""))) {
       return;
     }
     PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel = PhieuKbVaoVienDelegate.getInstance();
     PhieuKbVaoVien obj = PhieuKhamDtNgoaiTruDel.find(this.sMaPhieuKVV);
     if (obj == null) {
       return;
     }
     PhieuKhamDtNgoaiTruDel.remove(obj);
     FacesMessages.instance().add(IConstantsRes.RPPKBVV_DELETE_SUCCESS, new Object[] { this.sMaPhieuKVV });
     log.info("***** XOA THANH CONG *****");
     this.objPhieuKBVaoVien = new PhieuKbVaoVien();
     this.sMaPhieuKVV = "";
     this.sShowDel = "false";
     this.sShowPrint = "false";
     log.info("***** end  huyPhieu() *****");
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToPhieuKhamBenhVaoVien = null;
     log.info("*****End quayLai() *****");
     return "ghinhan";
   }

   public void get_thuoc_info()
   {
     this.ylenh = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       Date tuNgay = Utils.getDBDateWithHour(0, this.ngay, true).getTime();
       Date denNgay = Utils.getDBDateWithHour(23, this.ngay, false).getTime();
       String thuocBK = "";
       String thuocBH = "";
       String thuocVe = "";


       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       List<ThuocPhongKham> listThuocPhongKham = tpkDelegate.findByThamKhamVaNgay(this.thamkham.getThamkhamMa(), tuNgay, denNgay);
       if ((listThuocPhongKham != null) && (listThuocPhongKham.size() > 0))
       {
         for (ThuocPhongKham thuoc : listThuocPhongKham) {
           if (thuoc.getThuocphongkhamLoai().equals("3"))
           {
             thuocBH = thuocBH + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBH = thuocBH + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBH = thuocBH + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             thuocBH = thuocBH + "; ";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("1"))
           {
             thuocBK = thuocBK + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBK = thuocBK + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBK = thuocBK + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             thuocBK = thuocBK + "; ";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("2"))
           {
             thuocVe = thuocVe + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocVe = thuocVe + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocVe = thuocVe + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             thuocVe = thuocVe + "; ";
           }
         }
         if (!thuocBH.equals("")) {
           this.ylenh = (this.ylenh + "+ " + IConstantsRes.THUOC_QUAY_BV + " " + thuocBH);
         }
         if (!thuocBK.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_BAN_KHAM + " " + thuocBK);
         }
         if (!thuocVe.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_TOA_VE + " " + thuocVe);
         }
       }
       else
       {
         this.ylenh = IConstantsRes.KHONG_DUNG_THUOC;
       }
     }
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeTD = "phieukhambenhvaovien";
     log.info("Vao Method XuatReport phieukhamchuyenkhoa");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieukhambenhvaovien.jrxml";
       log.info("Da thay file template: " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("HoTen", this.benhNhan.getBenhnhanHoten());
       if (this.benhNhan.getBenhnhanNgaysinh() != null) {
         params.put("NgaySinh", sdf.format(this.benhNhan.getBenhnhanNgaysinh()));
       } else {
         params.put("NgaySinh", this.benhNhan.getBenhnhanNamsinh());
       }
       params.put("Tuoi", this.benhNhan.getBenhnhanTuoi());

       params.put("DonViTuoi", this.benhNhan.getBenhnhanDonvituoi() == null ? new Integer(1) : new Integer(this.benhNhan.getBenhnhanDonvituoi().shortValue()));

       params.put("Gioi", this.benhNhan.getDmgtMaso(true).getDmgtTen());

       DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();

       DmDanToc danToc = (DmDanToc)dele.findByMa(this.benhNhan.getDantocMa(true).getDmdantocMa(), "DmDanToc", "dmdantocMa");
       if (danToc != null)
       {
         params.put("DanToc", danToc.getDmdantocTen());
         params.put("MaDanToc", danToc.getDmdantocMa());
       }
       else
       {
         params.put("DanToc", "");
         params.put("MaDanToc", "");
       }
       params.put("DiaChi", this.benhNhan.getBenhnhanDiachi());
       if ((this.benhNhan.getXaMa() != null) && (!this.benhNhan.getXaMa(true).getDmxaTen().equals(""))) {
         params.put("Xa", this.benhNhan.getXaMa(true).getDmxaTen());
       } else {
         params.put("Xa", "");
       }
       DmHuyen huyen = (DmHuyen)dele.findByMa(this.benhNhan.getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
       if (huyen != null)
       {
         params.put("Huyen", huyen.getDmhuyenTen());
         params.put("MaHuyen", huyen.getDmhuyenMa());
       }
       else
       {
         params.put("Huyen", "");
         params.put("MaHuyen", "");
       }
       DmTinh tinh = (DmTinh)dele.findByMa(this.benhNhan.getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
       if (tinh != null)
       {
         params.put("Tinh", tinh.getDmtinhTen());
         params.put("MaTinh", tinh.getDmtinhMa());
       }
       else
       {
         params.put("Tinh", "");
         params.put("MaTinh", "");
       }
       DmQuocGia quocGia = (DmQuocGia)dele.findByMa(this.benhNhan.getQuocgiaMa(true).getDmquocgiaMa(), "DmQuocGia", "dmquocgiaMa");
       if ((quocGia != null) && (!"VNA".equals(quocGia.getDmquocgiaMa())))
       {
         params.put("NgoaiKieu", quocGia.getDmquocgiaTen());
         params.put("MaNgoaiKieu", quocGia.getDmquocgiaMa());
       }
       else
       {
         params.put("NgoaiKieu", "");
         params.put("MaNgoaiKieu", "");
       }
       DmNgheNghiep ngheNghiep = (DmNgheNghiep)dele.findByMa(this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa(), "DmNgheNghiep", "dmnghenghiepMa");
       if (ngheNghiep != null)
       {
         params.put("NgheNghiep", ngheNghiep.getDmnghenghiepTen());
         params.put("MaNgheNghiep", ngheNghiep.getDmnghenghiepMa());
       }
       else
       {
         params.put("NgheNghiep", "");
         params.put("MaNgheNghiep", "");
       }
       log.info("nghe nghiep :" + ngheNghiep);

       String doiTuongMa = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       if ((doiTuongMa != null) && (doiTuongMa.equals("BH")))
       {
         params.put("DoiTuong_BHYT", "X");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("TP")))
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "X");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("MP")))
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "X");
         params.put("DoiTuong_Khac", "");
       }
       else
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "X");
       }
       params.put("NoiLamViec", this.thamkham.getTiepdonMa(true).getTiepdonMacoquan());
       params.put("KHOA", this.thamkham.getThamkhamBankham(true).getDmkhoaMaso(true).getDmkhoaTen());
       params.put("VAOKHOA", this.thamkham.getTiepdonMa(true).getTiepdonChkhoa(true).getDmkhoaTen());


       Date giaTri2 = this.thamkham.getTiepdonMa(true).getTiepdonGiatri2();
       if (giaTri2 != null) {
         params.put("BHYTGIATRIDEN", giaTri2);
       }
       params.put("Lydovaovien", this.objPhieuKBVaoVien.getPkbvvLydovaovien());
       params.put("QuaTrinhBenhLy", this.objPhieuKBVaoVien.getPkbvvQtbenhli());
       params.put("TienSuBenhBanThan", this.objPhieuKBVaoVien.getPkbvvTiensubenhbt());
       params.put("TienSuBenhGiaDinh", this.objPhieuKBVaoVien.getPkbvvTiensubenhgd());
       params.put("ToanThan", this.objPhieuKBVaoVien.getPkbvvToanthan());
       params.put("CacBoPhan", this.objPhieuKBVaoVien.getPkbvvCacbophan());
       params.put("DaXuLy", this.objPhieuKBVaoVien.getPkbvvDaxuly());
       params.put("TTLQLAMSANG", this.objPhieuKBVaoVien.getPkbvvTtketqualamsang());
       params.put("CHUY", this.objPhieuKBVaoVien.getPkbvvChuy());
       params.put("BUONG", this.thamkham.getThamkhamBankham(true).getDtdmbankhamTen());
       params.put("HuyetApMin", this.thamkham.getTiepdonMa(true).getTiepdonHamin());
       params.put("HuyetApMax", this.thamkham.getTiepdonMa(true).getTiepdonHamax());

       params.put("Mach", this.thamkham.getTiepdonMa(true).getTiepdonMach());
       params.put("NhietDo", this.thamkham.getTiepdonMa(true).getTiepdonNhietdo());
       params.put("NhipTho", this.thamkham.getTiepdonMa(true).getTiepdonNhiptho());
       params.put("CANNANG", this.thamkham.getTiepdonMa(true).getTiepdonTrluong());
       if ((this.thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!this.thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         String maBV = this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         params.put("SoTheBHYT", this.thamkham.getTiepdonMa().getTiepdonSothebh() + "-" + maBV.replace(".", "-"));


         log.info("SoTheBHYT:" + params.get("SoTheBHYT"));
       }
       else
       {
         params.put("SoTheBHYT", "");
       }
       params.put("NguoiBaoTin", this.thamkham.getTiepdonMa(true).getTiepdonBaotin());

       params.put("NgayGioVaoVien", this.thamkham.getTiepdonMa(true).getTiepdonNgaygio());
       if (this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa() == null) {
         params.put("ChanDoanTuyenTruoc", "");
       } else {
         params.put("ChanDoanTuyenTruoc", this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa() + " - " + this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen());
       }
       params.put("ChanDoanVaoVien", this.objPhieuKBVaoVien.getPkbvvCdvaovien());

       String bacsikhambenh = "";
       if (this.thamkham.getThamkhamBacsi() != null) {
         bacsikhambenh = bacsikhambenh + this.thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
       }
       params.put("bacsikhambenh", bacsikhambenh);

       params.put("MaTiepDon", this.thamkham.getTiepdonMa(true).getTiepdonMa());
       if ((this.thamkham.getTiepdonMa(true).getTiepdonMa() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonMa().equals("")))
       {
         HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
         Hsba hsba = hsbaDelegate.findByTiepDonMa(this.thamkham.getTiepdonMa(true).getTiepdonMa());
         if (hsba != null) {
           params.put("SoVaoVien", hsba.getHsbaSovaovien());
         } else {
           params.put("SoVaoVien", "");
         }
       }
       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "phieukhambenhvaovien");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);
       this.index += 1;
       log.info("Index :" + this.index);
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

   private int index = 0;

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.sMaPhieuKVV == null) || (this.sMaPhieuKVV.equals(""))) {
         return;
       }
       PhieuKbVaoVienDelegate PhieuKhamDtNgoaiTruDel = PhieuKbVaoVienDelegate.getInstance();
       this.objPhieuKBVaoVien = new PhieuKbVaoVien();
       List<PhieuKbVaoVien> ls = PhieuKhamDtNgoaiTruDel.findByPhieuKbVaoVien(getMaPhieuKhamBenhVaoVien());
       if ((ls == null) || (ls.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.RPPKBVV_NOT_EXIST, new Object[0]);
         this.sMaPhieuKVV = "";
         this.sShowDel = "false";
         this.sShowPrint = "false";
         return;
       }
       this.sShowDel = "true";
       this.sShowPrint = "true";
       this.objPhieuKBVaoVien = ((PhieuKbVaoVien)ls.get(0));
       this.sMaPhieuKVV = this.objPhieuKBVaoVien.getPkbvvMa();



       log.info("*****sMaPhieuKVV: " + this.sMaPhieuKVV);
     }
     catch (Exception e)
     {
       System.out.println("error on function displayInfor" + e);
     }
   }

   public void destroyService()
   {
     try
     {
       log.debug("===== begin destroyService() method");

       log.debug("***** End destroyService() method");
     }
     catch (Exception ex)
     {
       log.debug("*****destroyService Exception: " + ex);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("************************* destroy PhieuKbVaoVien");
   }

   public static void main(String[] args) {}

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public int getStt()
   {
     return this.stt;
   }

   public void setStt(int stt)
   {
     this.stt = stt;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToPhieuKhamBenhVaoVien()
   {
     return this.goToPhieuKhamBenhVaoVien;
   }

   public void setGoToPhieuKhamBenhVaoVien(String str)
   {
     this.goToPhieuKhamBenhVaoVien = str;
   }

   public String getMaPhieuKhamBenhVaoVien()
   {
     return this.sMaPhieuKVV;
   }

   public void setMaPhieuKhamBenhVaoVien(String maGiayChuyenVien)
   {
     this.sMaPhieuKVV = maGiayChuyenVien;
   }

   public String getShowDel()
   {
     return this.sShowDel;
   }

   public void setShowDel(String showDel)
   {
     this.sShowDel = showDel;
   }

   public String getShowPrint()
   {
     return this.sShowPrint;
   }

   public void setShowPrint(String showPrint)
   {
     this.sShowPrint = showPrint;
   }

   public PhieuKbVaoVien getPhieuKbVaoVien()
   {
     return this.objPhieuKBVaoVien;
   }

   public void setPhieuKbVaoVien(PhieuKbVaoVien objPhieuKhamDTNgoaiTru)
   {
     this.objPhieuKBVaoVien = objPhieuKhamDTNgoaiTru;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.PhieuKhamBenhVaoVien

 * JD-Core Version:    0.7.0.1

 */