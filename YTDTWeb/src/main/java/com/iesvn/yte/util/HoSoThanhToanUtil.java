 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.delegate.ChuyenVaoNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HoanUngDelegate;
 import com.iesvn.yte.dieutri.delegate.HoanUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocDongYNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ChuyenVaoNoiTru;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
 import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.MienGiam;
 import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmThuoc;
 import java.io.PrintStream;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class HoSoThanhToanUtil
 {
   private static Logger log = Logger.getLogger(HoSoThanhToanUtil.class);
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private Double perbhytchi = new Double(0.0D);
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Hsba hsba;
   private MienGiamDelegate miengiamDelegate = MienGiamDelegate.getInstance();
   private List<MienGiam> lstMienGiam = new ArrayList();
   Double clsNDM = new Double(0.0D);
   Double clsTrongDM = new Double(0.0D);
   private Double khongThu = new Double(0.0D);
   Double thuocTrongDM = new Double(0.0D);
   Double thuocNDM = new Double(0.0D);
   Double vTTHTrongDM = new Double(0.0D);
   Double vTTHNDM = new Double(0.0D);
   Double cLSMauTrongDM = new Double(0.0D);
   Double clsMauNDM = new Double(0.0D);
   Double cLSPhongTrongDM = new Double(0.0D);
   Double clsPhongNDM = new Double(0.0D);
   Double tienCongKham = new Double(0.0D);
   Double chanDoanHinhAnh = new Double(0.0D);
   Double dichVuKTThongThuong = new Double(0.0D);
   Double dichVuKTC = new Double(0.0D);
   Double chiPhiVC = new Double(0.0D);
   Double xetNghiemTDCN = new Double(0.0D);
   Double ung = new Double(0.0D);
   Double tra = new Double(0.0D);
   Double soDu = new Double(0.0D);
   Double chiphiPT = new Double(0.0D);
   Double chiphiTT = new Double(0.0D);
   private Double mienTE = new Double(0.0D);
   private double tongChiPhiCLSBhTra = 0.0D;
   private double tongChiPhiCLSBnTra = 0.0D;
   private double tongChiPhiVCBhTra = 0.0D;
   private double tongChiPhiVCBnTra = 0.0D;
   private double tongChiPhiKTCBhTra = 0.0D;
   private double tongChiPhiKTCBnTra = 0.0D;
   private double tongChiPhiThuocBhTra = 0.0D;
   private double tongChiPhiThuocBnTra = 0.0D;
   private double tongMienGiam = 0.0D;
   private boolean bDoituongUutien = false;
   private boolean bVuottuyen = false;
   public Double DaThanhToan = new Double(0.0D);
   public double chiphiNguonKhacTra = 0.0D;
   Short tuyen = null;
   private HsThtoank hsttk;
   private HsbaBhyt hsbaBHYT;
   DtDmKhoiBhyt khoiBHYT;

   private void reset()
   {
     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = new Double(0.0D);
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.khongThu = new Double(0.0D);
     this.mienTE = new Double(0.0D);
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

   private boolean checkDTDuocNguonKhacTraPhi(String maDT)
   {
     log.info("Begin checkDTDuocNguonKhacTraPhi, maDT = " + maDT);
     boolean duocNguonKhacTraPhi = false;
     if ((IConstantsRes.NGUON_KHAC_TRA_CHI_PHI != null) && (IConstantsRes.NGUON_KHAC_TRA_CHI_PHI.equalsIgnoreCase("YES")))
     {
       String dsNguonKhacTraPhi = IConstantsRes.DOI_TUONG_DUOC_NGUON_KHAC_TRA_PHI;
       if ((dsNguonKhacTraPhi != null) && (!dsNguonKhacTraPhi.equals("")))
       {
         StringTokenizer sTk = new StringTokenizer(dsNguonKhacTraPhi, ",");
         ArrayList<String> arrayDS = new ArrayList();
         while (sTk.hasMoreTokens())
         {
           String khoi = sTk.nextToken();
           arrayDS.add(khoi);
         }
         if (arrayDS.contains(maDT)) {
           duocNguonKhacTraPhi = true;
         }
       }
     }
     log.info("End checkDTDuocNguonKhacTraPhi, duocNguonKhacTraPhi = " + duocNguonKhacTraPhi);
     return duocNguonKhacTraPhi;
   }

   public HoSoThanhToanUtil(Hsba hsba)
   {
     this.hsba = hsba;
     HsbaBhytDelegate hsbaBHYTDelegate = HsbaBhytDelegate.getInstance();
     this.hsbaBHYT = hsbaBHYTDelegate.findBySoVaoVienLastHsbaBhyt(hsba.getHsbaSovaovien());

     this.lstMienGiam = this.miengiamDelegate.getBySoVaoVien(hsba.getHsbaSovaovien());
     if (this.lstMienGiam == null) {
       this.lstMienGiam = new ArrayList();
     }
     if (this.hsbaBHYT != null)
     {
       this.khoiBHYT = this.hsbaBHYT.getHsbabhytKhoibh();
       if (this.khoiBHYT != null)
       {
         if (this.khoiBHYT.getDtdmkhoibhytMinKTC() != null) {
           this.minktc = this.khoiBHYT.getDtdmkhoibhytMinKTC().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytMaxKTC() != null) {
           this.maxktc = this.khoiBHYT.getDtdmkhoibhytMaxKTC().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytTLMinKTC() != null) {
           this.tyleminktc = this.khoiBHYT.getDtdmkhoibhytTLMinKTC().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytTLMaxKTC() != null) {
           this.tylemaxktc = this.khoiBHYT.getDtdmkhoibhytTLMaxKTC().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytTylektc() != null) {
           this.tyleKTC = this.khoiBHYT.getDtdmkhoibhytTylektc().shortValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru() != null) {
           this.gioiHanTyLe_noi_tru = this.khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytTLBHYT1_NoiTru() != null) {
           this.tylebhyt_noi_tru = this.khoiBHYT.getDtdmkhoibhytTLBHYT1_NoiTru().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytTLBHYT2_NoiTru() != null) {
           this.tylebhyt2_noi_tru = this.khoiBHYT.getDtdmkhoibhytTLBHYT2_NoiTru().doubleValue();
         }
         if (this.khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru() != null) {
           this.gioiHanTyLe_noi_tru = this.khoiBHYT.getDtdmkhoibhytGHTyLeNoiTru().doubleValue();
         }
         String strSotheBHYT = this.hsbaBHYT.getHsbabhytSothebh() == null ? "" : this.hsbaBHYT.getHsbabhytSothebh();
         if (strSotheBHYT.trim().length() > 3)
         {
           String strMaQuyenloi = strSotheBHYT.substring(2, 3);
           DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
           DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
           if (maQL != null)
           {
             this.minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0.0D : maQL.getDtdmmqlbhytMinktc().doubleValue());
             this.maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytMaxktc().doubleValue());
             this.tylebhyt_noi_tru = (maQL.getDtdmmqlbhytTylebhyt1NoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytTylebhyt1NoiTru().doubleValue());
             this.tylebhyt2_noi_tru = (maQL.getDtdmmqlbhytTylebhyt2NoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytTylebhyt2NoiTru().doubleValue());
             this.tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlminktc().doubleValue());
             this.tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlmaxktc().doubleValue());
             this.tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc().shortValue());
             this.gioiHanTyLe_noi_tru = (maQL.getDtdmmqlbhytGhtyleNoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytGhtyleNoiTru().doubleValue());
           }
         }
         if ("BH".equals(hsba.getDoituongMa(true).getDmdoituongMa()))
         {
           this.tuyen = this.hsbaBHYT.getHsbabhytTuyen();
           log.info("---tuyen():" + this.tuyen);



           Integer tuoi = hsba.getBenhnhanMa(true).getBenhnhanTuoi();
           if (tuoi == null) {
             tuoi = Integer.valueOf(0);
           }
           Short donvituoi = hsba.getBenhnhanMa(true).getBenhnhanDonvituoi();
           if (donvituoi == null) {
             donvituoi = Short.valueOf((short)0);
           }
           String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
           int iTuoiKoXetTuyen = 200;
           if ((tuoikhongxettuyen != null) && (!tuoikhongxettuyen.equals(""))) {
             iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
           }
           if ((tuoi.intValue() >= iTuoiKoXetTuyen) && (donvituoi.shortValue() == 1))
           {
             this.bDoituongUutien = false;
             if ((this.tuyen != null) && ((this.tuyen.shortValue() == 1) || (this.tuyen.shortValue() == 2))) {
               this.bDoituongUutien = true;
             }
           }
           else if ((this.tuyen != null) && (this.tuyen.shortValue() == 3))
           {
             this.bDoituongUutien = false;
           }
           else
           {
             this.bDoituongUutien = checkDTUuTien(this.khoiBHYT.getDtdmkhoibhytMa());
           }
           if (!this.bDoituongUutien) {
             if ((this.tuyen != null) && ((this.tuyen.shortValue() == 2) || (this.tuyen.shortValue() == 3))) {
               if ((this.hsbaBHYT.getHsbabhytCoGiayChuyenVien() == null) || ((this.hsbaBHYT.getHsbabhytCoGiayChuyenVien() != null) && (!this.hsbaBHYT.getHsbabhytCoGiayChuyenVien().booleanValue())))
               {
                 this.bVuottuyen = true;
                 this.tylebhyt_noi_tru = 50.0D;
                 this.tylebhyt2_noi_tru = 50.0D;
                 if ((IConstantsRes.TYLE_BH_VUOT_TUYEN.trim().length() > 0) && (IConstantsRes.TYLE_BH2_VUOT_TUYEN.trim().length() > 0)) {
                   try
                   {
                     this.tylebhyt_noi_tru = Integer.parseInt(IConstantsRes.TYLE_BH_VUOT_TUYEN);
                     this.tylebhyt2_noi_tru = Integer.parseInt(IConstantsRes.TYLE_BH2_VUOT_TUYEN);
                   }
                   catch (Exception ex)
                   {
                     this.tylebhyt_noi_tru = 50.0D;
                     this.tylebhyt2_noi_tru = 50.0D;
                   }
                 }
               }
             }
           }
         }
       }
     }
     else
     {
       this.hsbaBHYT = new HsbaBhyt();
     }
     Date ngayGioTinhToan_tmp = hsba.getHsbaNgaygiovaov();


     this.ngayGioTinhToan = Calendar.getInstance();
     this.ngayGioTinhToan.setTime(ngayGioTinhToan_tmp);
     this.ngayGioTinhToan.set(11, 0);
     this.ngayGioTinhToan.set(12, 0);
     this.ngayGioTinhToan.set(13, 0);


     Date ngayBHYT2_tmp = this.hsbaBHYT.getHsbabhytGiatri2();
     if (ngayBHYT2_tmp != null)
     {
       this.ngayBHYT2 = Calendar.getInstance();
       this.ngayBHYT2.setTime(ngayBHYT2_tmp);
     }
     Date ngayBHYT3_tmp = this.hsbaBHYT.getHsbabhytGiatri3();
     if (ngayBHYT3_tmp != null)
     {
       this.ngayBHYT3 = Calendar.getInstance();
       this.ngayBHYT3.setTime(ngayBHYT3_tmp);
     }
   }

   Calendar ngayGioTinhToan = null;
   Calendar ngayBHYT2 = null;
   Calendar ngayBHYT3 = null;
   double minktc = 0.0D;
   double maxktc = 0.0D;
   double tyleminktc = 0.0D;
   double tylemaxktc = 0.0D;
   int tyleKTC = 0;
   double tylebhyt_noi_tru = 0.0D;
   double tylebhyt2_noi_tru = 0.0D;
   double gioiHanTyLe_noi_tru = 0.0D;

   private void tinhChiPhiThuoc(List<ThuocNoiTru> listTnt, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh)
   {
     if ((listTnt == null) || (listTnt.size() == 0)) {
       return;
     }
     for (ThuocNoiTru tnt : listTnt) {
       if ((tnt.getThuocdongyNoiTru() == null) || (!maDoituong.equals("BH")) || (!IConstantsRes.DONG_Y_APPLY_ALL.equals("1")))
       {
         Double soLuongTra = tnt.getThuocnoitruTra() == null ? new Double(0.0D) : tnt.getThuocnoitruTra();

         double giaThuoc = tnt.getThuocnoitruSoluong().doubleValue() - soLuongTra.doubleValue() == 0.0D ? 0.0D : tnt.getThuocnoitruThanhtien().doubleValue();
         Date ngayNhanThuoc = tnt.getThuocnoitruNgaygio();







         ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
         if (((tnt.getThuocnoitruNDM() != null) && (tnt.getThuocnoitruNDM().booleanValue() == true)) || ((tnt.getThuocnoitruYeucau() != null) && (tnt.getThuocnoitruYeucau().booleanValue() == true))) {
           this.tongChiPhiThuocBnTra += giaThuoc;
         } else if ((tnt.getThuocnoitruKhongthu() == null) || (tnt.getThuocnoitruKhongthu().booleanValue() != true)) {
           if (maDoituong.equalsIgnoreCase("BH"))
           {
             if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh))) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             } else {
               this.tongChiPhiThuocBhTra += giaThuoc;
             }
           }
           else if (maDoituong.equalsIgnoreCase("MP"))
           {
             if ((tnt.getThuocnoitruMien() == null) || (tnt.getThuocnoitruMien().booleanValue() != true)) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             }
           }
           else {
             this.tongChiPhiThuocBnTra += giaThuoc;
           }
         }
         if ((tnt.getThuocnoitruKhongthu() == null) || (tnt.getThuocnoitruKhongthu().booleanValue() != true)) {
           if (((tnt.getThuocnoitruNDM() != null) && (tnt.getThuocnoitruNDM().booleanValue() == true)) || ((tnt.getThuocnoitruYeucau() != null) && (tnt.getThuocnoitruYeucau().booleanValue() == true)))
           {
             if ((tnt.getThuocnoitruMathuoc() != null) && (tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt() != null) && (tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10)) {
               this.vTTHNDM = Double.valueOf(this.vTTHNDM.doubleValue() + giaThuoc);
             } else {
               this.thuocNDM = Double.valueOf(this.thuocNDM.doubleValue() + giaThuoc);
             }
           }
           else if ((tnt.getThuocnoitruMathuoc() != null) && (tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt() != null) && (tnt.getThuocnoitruMathuoc().getDmthuocPlbhyt().intValue() == 10)) {
             this.vTTHTrongDM = Double.valueOf(this.vTTHTrongDM.doubleValue() + giaThuoc);
           } else {
             this.thuocTrongDM = Double.valueOf(this.thuocTrongDM.doubleValue() + giaThuoc);
           }
         }
       }
     }
   }

   public double miengiamdoituong = 0.0D;

   private void tinhTamUng()
   {
     TamUngDelegate tamUng = TamUngDelegate.getInstance();
     this.ung = tamUng.getTongTamUng(this.hsba.getHsbaSovaovien());



     TamUngKhamDelegate tamUngKham = TamUngKhamDelegate.getInstance();
     Double ungKham = tamUngKham.getTongTamUng(this.hsba.getTiepdonMa());
     if (ungKham != null) {
       this.ung = Double.valueOf(this.ung.doubleValue() + ungKham.doubleValue());
     }
   }

   private void tinhHoanUng()
   {
     HoanUngDelegate hoanung = HoanUngDelegate.getInstance();
     this.tra = hoanung.getTongHoanUng(this.hsba.getHsbaSovaovien());


     HoanUngKhamDelegate hoanUngKham = HoanUngKhamDelegate.getInstance();
     Double traKham = hoanUngKham.getTongHoanUng(this.hsba.getTiepdonMa());
     if (traKham != null) {
       this.tra = Double.valueOf(this.tra.doubleValue() + traKham.doubleValue());
     }
   }

   private List<ThuocNoiTru> capnhatDongiattTnt(List<ThuocNoiTru> listTnt)
   {
     if (listTnt.size() > 0)
     {
       Double thanhTien = null;
       for (int i = 0; i < listTnt.size(); i++) {
         if ((((ThuocNoiTru)listTnt.get(i)).getThuocnoitruKhongthu() != null) && (((ThuocNoiTru)listTnt.get(i)).getThuocnoitruKhongthu().booleanValue() == true))
         {
           ((ThuocNoiTru)listTnt.get(i)).setThuocnoitruDongiatt(Double.valueOf(0.0D));
           ((ThuocNoiTru)listTnt.get(i)).setThuocnoitruThanhtien(Double.valueOf(0.0D));
         }
         else
         {
           ((ThuocNoiTru)listTnt.get(i)).setThuocnoitruDongiatt(roundDoubleNumber(((ThuocNoiTru)listTnt.get(i)).getThuocnoitruDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));

           thanhTien = Double.valueOf((((ThuocNoiTru)listTnt.get(i)).getThuocnoitruSoluong().doubleValue() - (((ThuocNoiTru)listTnt.get(i)).getThuocnoitruTra() == null ? 0.0D : ((ThuocNoiTru)listTnt.get(i)).getThuocnoitruTra().doubleValue())) * ((ThuocNoiTru)listTnt.get(i)).getThuocnoitruDongiatt().doubleValue());

           ((ThuocNoiTru)listTnt.get(i)).setThuocnoitruThanhtien(roundDoubleNumber(thanhTien, IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));
         }
       }
     }
     return listTnt;
   }

   private List<ThuocDongYNoiTru> capnhatDongiattThuocDongYNoiTru(List<ThuocDongYNoiTru> listThuocDongY)
   {
     if (listThuocDongY.size() > 0)
     {
       Double thanhTien = null;
       for (int i = 0; i < listThuocDongY.size(); i++)
       {
         ((ThuocDongYNoiTru)listThuocDongY.get(i)).setThuocdongyDongiatt(roundDoubleNumber(Double.valueOf(((ThuocDongYNoiTru)listThuocDongY.get(i)).getThuocdongyDongia()), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));


         thanhTien = Double.valueOf(((ThuocDongYNoiTru)listThuocDongY.get(i)).getThuocdongySoluong() * ((ThuocDongYNoiTru)listThuocDongY.get(i)).getThuocdongyDongiatt().doubleValue());

         ((ThuocDongYNoiTru)listThuocDongY.get(i)).setThuocdongyThanhtien(roundDoubleNumber(thanhTien, IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));
       }
     }
     return listThuocDongY;
   }

   private Double roundDoubleNumber(Double dNum, String pattern)
   {
     if (dNum == null) {
       return new Double(0.0D);
     }
     DecimalFormat twoDigit = new DecimalFormat(pattern);
     return new Double(twoDigit.format(dNum.doubleValue()));
   }

   public void tinhToanChoHSTT(HsThtoan hstt)
   {
     ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();



     List<ClsMo> listCtkq = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
     ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();



     List<ThuocNoiTru> lstThuocNT = tntDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());

     Calendar myCal = Calendar.getInstance();
     myCal.set(2011, 11, 15, 0, 0, 0);
     Date dateUpdate = myCal.getTime();

     this.tongChiPhiCLSBhTra = 0.0D;
     this.tongChiPhiCLSBnTra = 0.0D;

     this.tongChiPhiVCBhTra = 0.0D;
     this.tongChiPhiVCBnTra = 0.0D;
     this.tongChiPhiKTCBhTra = 0.0D;
     this.tongChiPhiKTCBnTra = 0.0D;
     this.tongChiPhiThuocBhTra = 0.0D;
     this.tongChiPhiThuocBnTra = 0.0D;
     this.chiphiNguonKhacTra = 0.0D;

     boolean mienphiVC = false;

     String maDoituong = "";
     Date ngayBatDauBh = null;
     Date ngayHetHanBh = null;
     if (this.hsbaBHYT != null)
     {
       maDoituong = this.hsba.getDoituongMa() == null ? "" : this.hsba.getDoituongMa().getDmdoituongMa();

       ngayBatDauBh = this.hsbaBHYT.getHsbabhytGiatri2();
       if (ngayBatDauBh == null) {
         ngayBatDauBh = this.hsbaBHYT.getHsbabhytGiatri0();
       }
       ngayHetHanBh = this.hsbaBHYT.getHsbabhytGiatri3();
       if (ngayHetHanBh == null) {
         ngayHetHanBh = this.hsbaBHYT.getHsbabhytGiatri1();
       }
       if (ngayBatDauBh != null) {
         ngayBatDauBh = Utils.removeHourFromDate(ngayBatDauBh);
       }
       if (ngayHetHanBh != null) {
         ngayHetHanBh = Utils.removeHourFromDate(ngayHetHanBh);
       }
       String strSotheBHYT = this.hsbaBHYT.getHsbabhytSothebh() == null ? "" : this.hsbaBHYT.getHsbabhytSothebh();
       if (strSotheBHYT.trim().length() > 3)
       {
         String strMaQuyenloi = strSotheBHYT.substring(2, 3);
         DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
         DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
         if (maQL != null)
         {
           this.minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0.0D : maQL.getDtdmmqlbhytMinktc().doubleValue());
           this.maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytMaxktc().doubleValue());
           if (!this.bVuottuyen) {
             this.tylebhyt_noi_tru = (maQL.getDtdmmqlbhytTylebhyt1NoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytTylebhyt1NoiTru().doubleValue());
           }
           this.tylebhyt2_noi_tru = (maQL.getDtdmmqlbhytTylebhyt2NoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytTylebhyt2NoiTru().doubleValue());
           this.tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlminktc().doubleValue());
           this.tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlmaxktc().doubleValue());
           this.tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc().shortValue());
           this.gioiHanTyLe_noi_tru = (maQL.getDtdmmqlbhytGhtyleNoiTru() == null ? 0.0D : maQL.getDtdmmqlbhytGhtyleNoiTru().doubleValue());
           mienphiVC = maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen().booleanValue();
         }
       }
     }
     tinhChiPhiCLS(listCtkq, maDoituong, mienphiVC, ngayBatDauBh, ngayHetHanBh);


     tinhChiPhiThuoc(lstThuocNT, maDoituong, ngayBatDauBh, ngayHetHanBh);




     ChuyenVaoNoiTru cvnt = ChuyenVaoNoiTruDelegate.getInstance().findBySoVaoVien(this.hsba.getHsbaSovaovien());


     ThuocDongYNoiTruDelegate thuocDYNTDel = ThuocDongYNoiTruDelegate.getInstance();
     List<ThuocDongYNoiTru> listThuocDYNoiTru = thuocDYNTDel.findBySoVaoVien(this.hsba.getHsbaSovaovien());
     if ((maDoituong.equals("BH")) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1"))) {
       if (listThuocDYNoiTru.size() > 0)
       {
         if (this.hsba.getHsbaNgaygiovaov().before(dateUpdate)) {
           listThuocDYNoiTru = capnhatDongiattThuocDongYNoiTru(listThuocDYNoiTru);
         }
         for (ThuocDongYNoiTru each : listThuocDYNoiTru)
         {
           Date ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
           if ((ngayBatDauBh != null) && (ngayHetHanBh != null) && (!ngayNhanThuocDY.before(ngayBatDauBh)) && (!ngayNhanThuocDY.after(ngayHetHanBh)))
           {
             this.tongChiPhiThuocBhTra += each.getThuocdongyThanhtien().doubleValue();


             this.thuocTrongDM = Double.valueOf(this.thuocTrongDM.doubleValue() + each.getThuocdongyThanhtien().doubleValue());
           }
         }
       }
     }
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(new TiepDon());
     if (cvnt != null)
     {
       hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(cvnt.getTiepDon());
       this.hsttk = HsThtoankDelegate.getInstance().findBytiepdonMa(cvnt.getTiepDon().getTiepdonMa());
       if (this.hsttk == null) {
         this.hsttk = new HsThtoank();
       }
       this.hsttk.setTiepdonMa(cvnt.getTiepDon());
       hsthtoankUtilTmp.tinhToanChoHSTKKham(this.hsttk);
       log.info("Chi phi thuoc BH tra ngoai tru = " + hsthtoankUtilTmp.getTongChiPhiThuocBhTra());
       log.info("Chi phi CLS BH tra ngoai tru = " + hsthtoankUtilTmp.getTongChiPhiCLSBhTra());
     }
     double tongChiPhiThuocCLS = this.tongChiPhiCLSBhTra + this.tongChiPhiThuocBhTra;
     double tongChiPhiThuocCLS_NgoaiTru = 0.0D;
     if (cvnt != null) {
       tongChiPhiThuocCLS_NgoaiTru = hsthtoankUtilTmp.getTongChiPhiThuocBhTra() + hsthtoankUtilTmp.getTongChiPhiCLSBhTra();
     }
     double tyleBhApDung = 0.0D;

     tyleBhApDung = this.tylebhyt_noi_tru;
     if ((!this.bVuottuyen) && (tongChiPhiThuocCLS < this.gioiHanTyLe_noi_tru)) {
       tyleBhApDung = this.tylebhyt2_noi_tru;
     }
     double tongChiPhiThuocCLSBhTra = roundDoubleNumber(new Double(tongChiPhiThuocCLS * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue();

     double tongChiPhiThuocCLSBnTra = tongChiPhiThuocCLS - tongChiPhiThuocCLSBhTra;
     if (("BH".equals(this.hsba.getDoituongMa(true).getDmdoituongMa())) && (this.tuyen != null) && (this.tuyen.shortValue() == 1) && (!this.bDoituongUutien)) {
       if (checkDTDuocNguonKhacTraPhi(this.khoiBHYT.getDtdmkhoibhytMa())) {
         this.chiphiNguonKhacTra = tongChiPhiThuocCLSBnTra;
       }
     }
     double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra + this.tongChiPhiCLSBnTra + this.tongChiPhiVCBnTra + this.tongChiPhiKTCBnTra + this.tongChiPhiThuocBnTra;
     if (tongChiPhiBnTra < 0.0D) {
       tongChiPhiBnTra = 0.0D;
     }
     double tongChiPhiBhTra = tongChiPhiThuocCLSBhTra + this.tongChiPhiVCBhTra + this.tongChiPhiKTCBhTra;


     capNhatThuocNoiTru(lstThuocNT, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);


     capNhatClsMo(listCtkq, ngayBatDauBh, ngayHetHanBh, tyleBhApDung);
     Double thanhtien;
     if ((maDoituong.equals("BH")) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) && (listThuocDYNoiTru.size() > 0))
     {
       thanhtien = Double.valueOf(0.0D);
       for (ThuocDongYNoiTru each : listThuocDYNoiTru)
       {
         Date ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
         thanhtien = Double.valueOf(each.getThuocdongySoluong() * each.getThuocdongyDongia());
         if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
         {
           each.setThuocdongyTylebh(new Double(0.0D));
           each.setThuocdongyTienbntra(thanhtien);
         }
         else if ((ngayNhanThuocDY.before(ngayBatDauBh)) || (ngayNhanThuocDY.after(ngayHetHanBh)))
         {
           each.setThuocdongyTylebh(new Double(0.0D));
           each.setThuocdongyTienbntra(thanhtien);
         }
         else
         {
           each.setThuocdongyTylebh(Double.valueOf(tyleBhApDung));
           each.setThuocdongyTienbntra(Double.valueOf(thanhtien.doubleValue() - roundDoubleNumber(Double.valueOf(thanhtien.doubleValue() * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
         }
         thuocDYNTDel.edit(each);
       }
     }
     List<ClsKham> listClsKham = new ArrayList();
     List<ThuocPhongKham> listTpk_BK = new ArrayList();
     List<ThuocPhongKham> listTpk_BH = new ArrayList();
     if (cvnt != null)
     {
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

       listClsKham = clsKhamDelegate.findByTiepdonma(cvnt.getTiepDon().getTiepdonMa());


       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       listTpk_BK = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "1");


       listTpk_BH = tpkDelegate.findByMaTiepDon(cvnt.getTiepDon().getTiepdonMa(), "3");
     }
     listCtkq = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());


     String maMienGiam = "";
     Date ngayBatDauMG = null;
     Date ngayKetThucMG = null;
     double phantramMienGiam = 0.0D;
     List<MienGiam> listMienGiam = MienGiamDelegate.getInstance().getBySoVaoVien(this.hsba.getHsbaSovaovien());
     if ((listMienGiam != null) &&
       (listMienGiam.size() > 0))
     {
       log.info("mien giam so tien = " + ((MienGiam)listMienGiam.get(0)).getMiengiamSotien());
       maMienGiam = ((MienGiam)listMienGiam.get(0)).getMiengiamLoaimien().getDtdmloaimienMa();

       ngayBatDauMG = ((MienGiam)listMienGiam.get(0)).getMiengiamNgayd();






       ngayBatDauMG = Utils.removeHourFromDate(ngayBatDauMG);

       ngayKetThucMG = ((MienGiam)listMienGiam.get(0)).getMiengiamNgayc();






       ngayKetThucMG = Utils.removeHourFromDate(ngayKetThucMG);
       if (((MienGiam)listMienGiam.get(0)).getMiengiamTyle() == null) {
         phantramMienGiam = 0.0D;
       } else {
         phantramMienGiam = ((MienGiam)listMienGiam.get(0)).getMiengiamTyle().doubleValue();
       }
       if (((MienGiam)listMienGiam.get(0)).getMiengiamSotien() != null) {
         this.tongMienGiam += ((MienGiam)listMienGiam.get(0)).getMiengiamSotien().doubleValue();
       }
     }
     List<ClsMo> listClsMo = clsMoDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());



     List<ThuocNoiTru> lstTNT = tntDelegate.findBySoVaoVien(this.hsba.getHsbaSovaovien());
     if (!maMienGiam.equals("3")) {
       if (maMienGiam.equals("2"))
       {
         this.tongMienGiam += tongChiPhiBnTra * phantramMienGiam / 100.0D;
         if (this.hsttk != null) {
           this.hsttk.setHsthtoankXetgiam(Double.valueOf((this.hsttk.getHsthtoankBntra() == null ? 0.0D : this.hsttk.getHsthtoankBntra().doubleValue()) * phantramMienGiam / 100.0D));
         }
       }
       else
       {
         tinhTongMienGiam(listClsMo, listClsKham, maMienGiam, ngayBatDauMG, ngayKetThucMG);
         log.info("tongMienGiam = " + this.tongMienGiam + ", phantramMienGiam = " + phantramMienGiam);

         this.tongMienGiam += tinhTongMienGiamThuoc(lstTNT, listTpk_BK, listTpk_BH, maMienGiam, ngayBatDauMG, ngayKetThucMG, ngayBatDauBh, ngayHetHanBh, maDoituong);
         this.tongMienGiam = (this.tongMienGiam * phantramMienGiam / 100.0D);
       }
     }
     hstt.setHsthtoanMau(Double.valueOf(this.clsMauNDM.doubleValue() + this.cLSMauTrongDM.doubleValue()));
     hstt.setHsthtoanCongkham(this.tienCongKham);
     hstt.setHsthtoanXntdcn(this.xetNghiemTDCN);
     hstt.setHsthtoanCdha(this.chanDoanHinhAnh);

     hstt.setHsthtoanDvktc(this.dichVuKTC);
     hstt.setHsthtoanCpvc(this.chiPhiVC);

     log.info("chiphiPT = " + this.chiphiPT);
     hstt.setHsthtoanPhauthuat(this.chiphiPT);
     log.info("chiphiTT = " + this.chiphiTT);
     hstt.setHsthtoanDvkttt(this.chiphiTT);



     this.perbhytchi = Double.valueOf(tyleBhApDung);
     hstt.setHsthtoanTylebh(new Short("" + new Double(tyleBhApDung).intValue()));

     tinhTamUng();
     tinhHoanUng();




     hstt.setHsthtoanTamung(this.ung);
     hstt.setHsthtoanHoanung(this.tra);







     hstt.setHsthtoanBntra(Double.valueOf(tongChiPhiBnTra));

     hstt.setHsthtoanBhyt(Double.valueOf(tongChiPhiBhTra));

     hstt.setHsthtoanThuoc(this.thuocTrongDM);
     hstt.setHsthtoanThuocndm(this.thuocNDM);
     hstt.setHsthtoanVtth(this.vTTHTrongDM);
     hstt.setHsthtoanVtthndm(this.vTTHNDM);

     hstt.setHsthtoanCls(this.clsTrongDM);
     hstt.setHsthtoanClsndm(this.clsNDM);





     hstt.setHsthtoanPhong(this.cLSPhongTrongDM);
     hstt.setHsthtoanPhongndm(this.clsPhongNDM);

     hstt.setHsthtoanMiente(this.mienTE);
     if ("TE".equals(this.hsba.getDoituongMa(true).getDmdoituongMa())) {
       hstt.setHsthtoanBhyt(hstt.getHsthtoanMiente());
     }
     hstt.setHsthtoanMiendt(Double.valueOf(this.tongMienGiam));
     System.out.println("========hstt.getHsthtoanBntra()============2==  : " + hstt.getHsthtoanBntra());




     this.soDu = Double.valueOf((hstt.getHsthtoanBntra() == null ? 0.0D : hstt.getHsthtoanBntra().doubleValue()) - (this.ung == null ? 0.0D : this.ung.doubleValue()) + (this.tra == null ? 0.0D : this.tra.doubleValue()) - this.tongMienGiam);

     System.out.println("========soDu============: " + this.soDu);

     hstt.setHsthtoanThtoan(this.soDu);
     hstt.setHsthtoanKhongthu(this.khongThu);
     hstt.setHsthtoanNguonkhactra(Double.valueOf(this.chiphiNguonKhacTra));
   }

   private void tinhChiPhiCLS(List<ClsMo> clslist, String maDoituong, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh)
   {
     log.info("Begin tinhChiPhiCLS(), maDoituong = " + maDoituong + "ngayHetHanBh = " + ngayHetHanBh);
     if ((clslist == null) || (clslist.size() == 0)) {
       return;
     }
     if (this.hsba == null) {
       return;
     }
     if ((clslist != null) && (clslist.size() > 0))
     {
       ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
       try
       {
          SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           Date ngayThucHienCLS = null;
         for (ClsMo clsmo : clslist)
         {
           Short lanKham = clsmo.getClsmoLan();
           if (lanKham == null) {
             lanKham = Short.valueOf((short)0);
           }
           clsmo.setClsmoLan(lanKham);


           Short tra = clsmo.getClsmoTra();
           if (tra == null) {
             tra = Short.valueOf((short)0);
           }
           if ((clsmo.getClsmoDatt() != null) && (clsmo.getClsmoDatt().booleanValue() == true))
           {
             this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue();
             this.DaThanhToan = Double.valueOf(this.DaThanhToan.doubleValue() + clsmo.getClsmoDongia().doubleValue());
             if (clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa().equalsIgnoreCase("KH")) {
               this.tienCongKham = Double.valueOf(this.tienCongKham.doubleValue() + clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
             }
             if ((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true)) {
               this.clsNDM = Double.valueOf(this.clsNDM.doubleValue() + clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
             } else {
               this.clsTrongDM = Double.valueOf(this.clsTrongDM.doubleValue() + clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
             }
           }
           else if (lanKham.shortValue() > tra.shortValue())
           {
             if (clsmo.getClsmoDongia() == null) {
               clsmo.setClsmoDongia(new Double(0.0D));
             }
             if ((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true)) {
               this.clsNDM = Double.valueOf(this.clsNDM.doubleValue() + clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
             } else {
               this.clsTrongDM = Double.valueOf(this.clsTrongDM.doubleValue() + clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
             }
             ngayThucHienCLS = clsmo.getClsmoNgay() == null ? null : df.parse(df.format(clsmo.getClsmoNgay()));







             ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
             if ((clsmo.getClsmoKtcao() != null) && (clsmo.getClsmoKtcao().booleanValue() == true)) {
               tinhChiPhiClsKTC(clsMoDel, clsmo, maDoituong, lanKham.shortValue(), tra.shortValue(), ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
             } else if ((clsmo.getClsmoLoaicls() != null) && (clsmo.getClsmoLoaicls().getDtdmclsMaso().intValue() == 12)) {
               tinhChiPhiClsVanChuyen(clsMoDel, clsmo, maDoituong, lanKham.shortValue(), tra.shortValue(), mienphiVC, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
             } else {
               tinhChiPhiCLSThuong(clsmo, maDoituong, lanKham.shortValue(), tra.shortValue(), ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS);
             }
             tinhChiPhiTheoMaCLS(clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa(), clsmo.getClsmoDongia() == null ? 0.0D : clsmo.getClsmoDongia().doubleValue(), clsmo.getClsmoNDM() != null ? clsmo.getClsmoNDM().booleanValue() : true, lanKham.shortValue(), tra.shortValue());
           }
         }
       }
       catch (Exception ex)
       {
         SimpleDateFormat df;
         Date ngayThucHienCLS;
         ex.printStackTrace();
       }
     }
   }

   private void tinhChiPhiCLSThuong(ClsMo clsmo, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS)
   {
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clsmo.getClsmoYeucau() != null) && (clsmo.getClsmoYeucau().booleanValue() == true))
     {
       this.tongChiPhiCLSBnTra += clsmo.getClsmoPhandv().doubleValue() * (lanKham - tra);
       this.tongChiPhiCLSBhTra += clsmo.getClsmoDongiabh().doubleValue() * (lanKham - tra);
     }
     else
     {
       this.tongChiPhiCLSBhTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
   }

   private void tinhChiPhiClsVanChuyen(ClsMoDelegate clsMoDel, ClsMo clsmo, String maDoituong, int lanKham, int tra, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS)
   {
     clsmo.setClsmoDongiabntra(Double.valueOf(clsmo.getClsmoDongia().doubleValue() * (lanKham - tra)));
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true))
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if (this.bDoituongUutien)
     {
       if (mienphiVC)
       {
         this.tongChiPhiVCBhTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);

         clsmo.setClsmoDongiabntra(new Double(0.0D));
       }
       else
       {
         this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
       }
     }
     else if (this.bVuottuyen)
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if (mienphiVC)
     {
       this.tongChiPhiVCBhTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);

       clsmo.setClsmoDongiabntra(new Double(0.0D));
     }
     else
     {
       this.tongChiPhiVCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     clsMoDel.edit(clsmo);
   }

   private void tinhChiPhiClsKTC(ClsMoDelegate clsMoDel, ClsMo clsmo, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS)
   {
     clsmo.setClsmoDongiabntra(Double.valueOf(clsmo.getClsmoDongia().doubleValue() * (lanKham - tra)));
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clsmo.getClsmoYeucau() != null) && (clsmo.getClsmoYeucau().booleanValue() == true))
     {
       this.tongChiPhiKTCBnTra += clsmo.getClsmoPhandv().doubleValue() * (lanKham - tra);
       double bnTraTmp = tinhTheoTyleKTC(clsmo.getClsmoDongiabh() == null ? 0.0D : clsmo.getClsmoDongiabh().doubleValue(), lanKham, tra);

       clsmo.setClsmoDongiabntra(Double.valueOf(clsmo.getClsmoPhandv().doubleValue() * (lanKham - tra) + bnTraTmp));
     }
     else
     {
       double bnTraTmp = tinhTheoTyleKTC(clsmo.getClsmoDongiabh() == null ? 0.0D : clsmo.getClsmoDongiabh().doubleValue(), lanKham, tra);

       clsmo.setClsmoDongiabntra(Double.valueOf(bnTraTmp));
     }
     clsMoDel.edit(clsmo);
   }

   private double tinhTheoTyleKTC(double donGiaThucHienCLS, int lanKham, int tra)
   {
     if ((this.bVuottuyen) && (!this.bDoituongUutien))
     {
       this.tyleminktc = this.tylebhyt_noi_tru;
       this.tyleKTC = new Double(this.tylebhyt_noi_tru).intValue();
       this.tylemaxktc = 0.0D;
     }
     double giaBhTra = 0.0D;
     double giaBnTra = 0.0D;
     if (donGiaThucHienCLS < this.minktc)
     {
       giaBhTra = donGiaThucHienCLS * this.tyleminktc / 100.0D * (lanKham - tra);
       giaBnTra = donGiaThucHienCLS * (lanKham - tra) - giaBhTra;
     }
     else if ((donGiaThucHienCLS >= this.minktc) && (donGiaThucHienCLS <= this.maxktc))
     {
       giaBhTra = donGiaThucHienCLS * this.tyleKTC / 100.0D * (lanKham - tra);
       giaBnTra = donGiaThucHienCLS * (lanKham - tra) - giaBhTra;
     }
     else if (donGiaThucHienCLS > this.maxktc)
     {
       if ((this.bVuottuyen) && (!this.bDoituongUutien)) {
         this.maxktc = this.minktc;
       }
       double trenMaxKTC = donGiaThucHienCLS - this.maxktc;
       double giaBhTraTrenMax = trenMaxKTC * this.tylemaxktc / 100.0D * (lanKham - tra);
       double giaBnTraTrenMax = trenMaxKTC * (lanKham - tra) - giaBhTraTrenMax;

       giaBhTra = this.maxktc * this.tyleKTC / 100.0D * (lanKham - tra);
       giaBnTra = this.maxktc * (lanKham - tra) - giaBhTra;

       giaBhTra += giaBhTraTrenMax;
       giaBnTra += giaBnTraTrenMax;
     }
     this.tongChiPhiKTCBnTra += giaBnTra;
     this.tongChiPhiKTCBhTra += giaBhTra;
     return giaBnTra;
   }

   private void tinhChiPhiTheoMaCLS(String maCls, double dongia, boolean ngoaiDM, int lanKham, int tra)
   {
     if (maCls.equalsIgnoreCase("GI"))
     {
       if (ngoaiDM) {
         this.clsPhongNDM = Double.valueOf(this.clsPhongNDM.doubleValue() + dongia * (lanKham - tra));
       } else {
         this.cLSPhongTrongDM = Double.valueOf(this.cLSPhongTrongDM.doubleValue() + dongia * (lanKham - tra));
       }
     }
     else if (maCls.equalsIgnoreCase("MA"))
     {
       if (ngoaiDM) {
         this.clsMauNDM = Double.valueOf(this.clsMauNDM.doubleValue() + dongia * (lanKham - tra));
       } else {
         this.cLSMauTrongDM = Double.valueOf(this.cLSMauTrongDM.doubleValue() + dongia * (lanKham - tra));
       }
     }
     else if (maCls.equalsIgnoreCase("KH")) {
       this.tienCongKham = Double.valueOf(this.tienCongKham.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("CD")) {
       this.chanDoanHinhAnh = Double.valueOf(this.chanDoanHinhAnh.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("TT")) {
       this.chiphiTT = Double.valueOf(this.chiphiTT.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("PT")) {
       this.chiphiPT = Double.valueOf(this.chiphiPT.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("KT")) {
       this.dichVuKTC = Double.valueOf(this.dichVuKTC.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("VC")) {
       this.chiPhiVC = Double.valueOf(this.chiPhiVC.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("XN")) {
       this.xetNghiemTDCN = Double.valueOf(this.xetNghiemTDCN.doubleValue() + dongia * (lanKham - tra));
     }
   }

   private void capNhatClsMo(List<ClsMo> clslist, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung)
   {
     if (clslist.size() < 1) {
       return;
     }
     ClsMoDelegate clsMoDel = ClsMoDelegate.getInstance();
     Short lanKham = Short.valueOf((short)0);
     Short tra = Short.valueOf((short)0);
     try
     {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       Date ngayThucHienCLS = null;
       for (ClsMo clsmo : clslist) {
         if (((clsmo.getClsmoDatt() == null) || (clsmo.getClsmoDatt().booleanValue() != true)) &&



           ((clsmo.getClsmoKtcao() == null) || (clsmo.getClsmoKtcao().booleanValue() != true)) && ((clsmo.getClsmoLoaicls() == null) || (clsmo.getClsmoLoaicls().getDtdmclsMaso().intValue() != 12)))
         {
           lanKham = clsmo.getClsmoLan();
           if (lanKham == null) {
             lanKham = Short.valueOf((short)0);
           }
           clsmo.setClsmoLan(lanKham);

           tra = clsmo.getClsmoTra();
           if (tra == null) {
             tra = Short.valueOf((short)0);
           }
           ngayThucHienCLS = clsmo.getClsmoNgay() == null ? null : df.parse(df.format(clsmo.getClsmoNgay()));
           double giaCLS = clsmo.getClsmoDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue());
           if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
           {
             clsmo.setClsmoDongiabntra(Double.valueOf(giaCLS));
           }
           else if (((clsmo.getClsmoNDM() != null) && (clsmo.getClsmoNDM().booleanValue() == true)) || (ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
           {
             clsmo.setClsmoDongiabntra(Double.valueOf(giaCLS));
           }
           else if ((clsmo.getClsmoYeucau() != null) && (clsmo.getClsmoYeucau().booleanValue() == true))
           {
             double giaCLSDv = clsmo.getClsmoPhandv().doubleValue() * (lanKham.shortValue() - tra.shortValue());
             double giaCLSBh = clsmo.getClsmoDongiabh().doubleValue() * (lanKham.shortValue() - tra.shortValue());
             giaCLS = giaCLSDv + (giaCLSBh - giaCLSBh * (tyleBhApDung / 100.0D));
             clsmo.setClsmoDongiabntra(Double.valueOf(giaCLS));
           }
           else
           {
             clsmo.setClsmoDongiabntra(Double.valueOf(giaCLS - giaCLS * (tyleBhApDung / 100.0D)));
           }
           clsMoDel.edit(clsmo);
         }
       }
     }
     catch (Exception ex)
     {

       log.info("ERROR : " + ex.toString());
     }
   }

   private void capNhatThuocNoiTru(List<ThuocNoiTru> tntlist, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung)
   {
     if ((tntlist == null) || (tntlist.size() < 1)) {
       return;
     }
     ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
     for (ThuocNoiTru tnt : tntlist) {
       if (tnt.getThuocnoitruNgaytt() == null)
       {
         double giaThuoc = tnt.getThuocnoitruThanhtien().doubleValue();
         Date ngayNhanThuoc = tnt.getThuocnoitruNgaygio();






         ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
         if ((tnt.getThuocnoitruKhongthu() != null) && (tnt.getThuocnoitruKhongthu().booleanValue() == true)) {
           tnt.setThuocnoitruTienbntra(new Double(0.0D));
         } else if (maDoituong.equalsIgnoreCase("BH"))
         {
           if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
             tnt.setThuocnoitruTienbntra(Double.valueOf(giaThuoc));
           } else if ((tnt.getThuocdongyNoiTru() != null) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) && (!ngayNhanThuoc.before(ngayBatDauBh)) && (!ngayNhanThuoc.after(ngayHetHanBh))) {
             tnt.setThuocnoitruTienbntra(new Double(0.0D));
           } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh)) || ((tnt.getThuocnoitruNDM() != null) && (tnt.getThuocnoitruNDM().booleanValue() == true)) || ((tnt.getThuocnoitruYeucau() != null) && (tnt.getThuocnoitruYeucau().booleanValue() == true))) {
             tnt.setThuocnoitruTienbntra(Double.valueOf(giaThuoc));
           } else {
             tnt.setThuocnoitruTienbntra(Double.valueOf(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
           }
         }
         else {
           tnt.setThuocnoitruTienbntra(Double.valueOf(giaThuoc));
         }
         tntDel.edit(tnt);
       }
     }
   }

   private double tinhTongMienGiamThuoc(List<ThuocNoiTru> lstThuocNT, List<ThuocPhongKham> listTpk_BK, List<ThuocPhongKham> listTpk_BH, String maMienGiam, Date ngayBatDauMG, Date ngayKetThucMG, Date ngayBatDauBh, Date ngayHetHanBh, String maDoiTuong)
   {
     double mienThuoc = 0.0D;
     if ((lstThuocNT == null) || (lstThuocNT.size() < 1) || (maMienGiam.trim().length() < 1) || (ngayBatDauMG == null) || (ngayKetThucMG == null)) {
       return mienThuoc;
     }
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     if ((maMienGiam.equalsIgnoreCase("02B")) || (maMienGiam.equalsIgnoreCase("ALL"))) {
       try
       {
         for (ThuocNoiTru tnt : lstThuocNT) {
           if (tnt.getThuocnoitruNgaytt() == null)
           {
             Date ngayMuaThuoc = df.parse(df.format(tnt.getThuocnoitruNgaygio()));






             ngayMuaThuoc = Utils.removeHourFromDate(ngayMuaThuoc);
             if ((!ngayMuaThuoc.before(ngayBatDauMG)) && (!ngayMuaThuoc.after(ngayKetThucMG))) {
               mienThuoc += (tnt.getThuocnoitruTienbntra() == null ? 0.0D : tnt.getThuocnoitruTienbntra().doubleValue());
             }
           }
         }
         listTpk_BK.addAll(listTpk_BH);
         for (ThuocPhongKham eachTpk : listTpk_BK) {
           if (eachTpk.getThuocphongkhamDatt() == null)
           {
             Date ngayMuaThuoc = df.parse(df.format(eachTpk.getThuocphongkhamNgaygio()));

             ngayMuaThuoc = Utils.removeHourFromDate(ngayMuaThuoc);
             if ((!ngayMuaThuoc.before(ngayBatDauMG)) && (!ngayMuaThuoc.after(ngayKetThucMG))) {
               if (this.hsttk != null) {
                 this.hsttk.setHsthtoankXetgiam(Double.valueOf(this.hsttk.getHsthtoankXetgiam().doubleValue() + eachTpk.getThuocphongkhamTienbntra().doubleValue()));
               }
             }
           }
         }
       }
       catch (Exception e)
       {

         e.printStackTrace();
       }
     }
     log.info("mienThuoc  = " + mienThuoc);
     return mienThuoc;
   }

   private void tinhTongMienGiam(List<ClsMo> clslist, List<ClsKham> clskham, String maMienGiam, Date ngayBatDauMG, Date ngayKetThucMG)
   {
     if (((clslist.size() < 1) && (clskham.size() < 1)) || (maMienGiam.trim().length() < 1) || (ngayBatDauMG == null) || (ngayKetThucMG == null)) {
       return;
     }
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       this.tongMienGiam = 0.0D;
     try
     {
       for (ClsMo clsmo : clslist) {
         if (((clsmo.getClsmoDatt() == null) || (clsmo.getClsmoDatt().booleanValue() != true)) && (clsmo.getClsmoNgay() != null))
         {
           Date ngayThHienCLS = df.parse(df.format(clsmo.getClsmoNgay()));







           ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
           if ((!ngayThHienCLS.before(ngayBatDauMG)) && (!ngayThHienCLS.after(ngayKetThucMG)))
           {
             String maCLS = clsmo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
             if (((maMienGiam.equalsIgnoreCase("02B")) && (!maCLS.equalsIgnoreCase("MA")) && (!maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("1")) && (maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("4")) && (maCLS.equalsIgnoreCase("MA"))) || ((maMienGiam.equalsIgnoreCase("6")) && (maCLS.equalsIgnoreCase("KT"))) || (maMienGiam.equalsIgnoreCase("ALL"))) {
               this.tongMienGiam += clsmo.getClsmoDongiabntra().doubleValue();
             }
           }
         }
       }
       if (this.hsttk != null) {
         this.hsttk.setHsthtoankXetgiam(new Double(0.0D));
       }
       for (ClsKham each : clskham) {
         if ((each.getClskhamDatt() == null) || (each.getClskhamDatt().booleanValue() != true))
         {
           Date ngayThHienCLS = df.parse(df.format(each.getClskhamNgaygiocn()));

           ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
           String maCLS = each.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa();
           if (((ngayThHienCLS.after(ngayBatDauMG)) || (ngayThHienCLS.equals(ngayBatDauMG))) && ((ngayThHienCLS.before(ngayKetThucMG)) || (ngayThHienCLS.equals(ngayKetThucMG)))) {
             if (((maMienGiam.equalsIgnoreCase("02B")) && (!maCLS.equalsIgnoreCase("MA")) && (!maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("1")) && (maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("4")) && (maCLS.equalsIgnoreCase("MA"))) || ((maMienGiam.equalsIgnoreCase("6")) && (maCLS.equalsIgnoreCase("KT"))) || (maMienGiam.equalsIgnoreCase("ALL"))) {
               if (this.hsttk != null) {
                 this.hsttk.setHsthtoankXetgiam(Double.valueOf(this.hsttk.getHsthtoankXetgiam().doubleValue() + each.getClskhamDongiabntra().doubleValue()));
               }
             }
           }
         }
       }
     }
     catch (Exception e)
     {

       e.printStackTrace();
     }
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public int getPermiengiam()
   {
     return this.permiengiam;
   }

   public void setPermiengiam(int permiengiam)
   {
     this.permiengiam = permiengiam;
   }

   public Double getMiengiam()
   {
     return this.miengiam;
   }

   public void setMiengiam(Double miengiam)
   {
     this.miengiam = miengiam;
   }

   public Double getThatthu()
   {
     return this.thatthu;
   }

   public void setThatthu(Double thatthu)
   {
     this.thatthu = thatthu;
   }

   public Double getPerbhytchi()
   {
     return this.perbhytchi;
   }

   public void setPerbhytchi(Double perbhytchi)
   {
     this.perbhytchi = perbhytchi;
   }

   public Double getBhytchi()
   {
     return this.bhytchi;
   }

   public void setBhytchi(Double bhytchi)
   {
     this.bhytchi = bhytchi;
   }

   public Double getThanhtien1()
   {
     return this.thanhtien1;
   }

   public void setThanhtien1(Double thanhtien1)
   {
     this.thanhtien1 = thanhtien1;
   }

   public int getPerbntra()
   {
     return this.perbntra;
   }

   public void setPerbntra(int perbntra)
   {
     this.perbntra = perbntra;
   }

   public Double getBntra()
   {
     return this.bntra;
   }

   public void setBntra(Double bntra)
   {
     this.bntra = bntra;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public Double getKhongThu()
   {
     return this.khongThu;
   }

   public void setKhongThu(Double khongThu)
   {
     this.khongThu = khongThu;
   }

   public Double getThuocTrongDM()
   {
     return this.thuocTrongDM;
   }

   public void setThuocTrongDM(Double thuocTrongDM)
   {
     this.thuocTrongDM = thuocTrongDM;
   }

   public Double getThuocNDM()
   {
     return this.thuocNDM;
   }

   public void setThuocNDM(Double thuocNDM)
   {
     this.thuocNDM = thuocNDM;
   }

   public Double getVTTHTrongDM()
   {
     return this.vTTHTrongDM;
   }

   public void setVTTHTrongDM(Double trongDM)
   {
     this.vTTHTrongDM = trongDM;
   }

   public Double getVTTHNDM()
   {
     return this.vTTHNDM;
   }

   public void setVTTHNDM(Double vtthndm)
   {
     this.vTTHNDM = vtthndm;
   }

   public Double getCLSMauTrongDM()
   {
     return this.cLSMauTrongDM;
   }

   public void setCLSMauTrongDM(Double mauTrongDM)
   {
     this.cLSMauTrongDM = mauTrongDM;
   }

   public Double getClsMauNDM()
   {
     return this.clsMauNDM;
   }

   public void setClsMauNDM(Double clsMauNDM)
   {
     this.clsMauNDM = clsMauNDM;
   }

   public Double getUng()
   {
     return this.ung;
   }

   public void setUng(Double ung)
   {
     this.ung = ung;
   }

   public Double getTra()
   {
     return this.tra;
   }

   public void setTra(Double tra)
   {
     this.tra = tra;
   }

   public Double getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(Double soDu)
   {
     this.soDu = soDu;
   }

   public Double getCLSPhongTrongDM()
   {
     return this.cLSPhongTrongDM;
   }

   public void setCLSPhongTrongDM(Double phongTrongDM)
   {
     this.cLSPhongTrongDM = phongTrongDM;
   }

   public Double getClsPhongNDM()
   {
     return this.clsPhongNDM;
   }

   public void setClsPhongNDM(Double clsPhongNDM)
   {
     this.clsPhongNDM = clsPhongNDM;
   }

   public HsbaBhyt getHsbaBHYT()
   {
     return this.hsbaBHYT;
   }

   public void setHsbaBHYT(HsbaBhyt hsbaBHYT)
   {
     this.hsbaBHYT = hsbaBHYT;
   }

   public DtDmKhoiBhyt getKhoiBHYT()
   {
     return this.khoiBHYT;
   }

   public void setKhoiBHYT(DtDmKhoiBhyt khoiBHYT)
   {
     this.khoiBHYT = khoiBHYT;
   }

   public double getMinktc()
   {
     return this.minktc;
   }

   public void setMinktc(double minktc)
   {
     this.minktc = minktc;
   }

   public double getMaxktc()
   {
     return this.maxktc;
   }

   public void setMaxktc(double maxktc)
   {
     this.maxktc = maxktc;
   }

   public double getTyleminktc()
   {
     return this.tyleminktc;
   }

   public void setTyleminktc(double tyleminktc)
   {
     this.tyleminktc = tyleminktc;
   }

   public double getTylemaxktc()
   {
     return this.tylemaxktc;
   }

   public void setTylemaxktc(double tylemaxktc)
   {
     this.tylemaxktc = tylemaxktc;
   }

   public int getTyleKTC()
   {
     return this.tyleKTC;
   }

   public void setTyleKTC(int tyleKTC)
   {
     this.tyleKTC = tyleKTC;
   }

   public Double getMienTE()
   {
     return this.mienTE;
   }

   public void setMienTE(Double mienTE)
   {
     this.mienTE = mienTE;
   }

   public HsThtoank getHsttk()
   {
     return this.hsttk;
   }

   public void setHsttk(HsThtoank hsttk)
   {
     this.hsttk = hsttk;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.HoSoThanhToanUtil

 * JD-Core Version:    0.7.0.1

 */