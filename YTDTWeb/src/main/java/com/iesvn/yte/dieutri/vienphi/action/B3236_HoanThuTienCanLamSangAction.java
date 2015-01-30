 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.commons.beanutils.BeanUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3236_Hoanthutiencanlamsang")
 @Synchronized(timeout=6000000L)
 public class B3236_HoanThuTienCanLamSangAction
   implements Serializable
 {
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String position = IConstantsRes.WEB_PATH + "/" + IConstantsRes.WEB_NAME + "/" + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/";
   private String resultReportName = "";
   private String reportFileNameHid = "";
   private String resultReportFileName = "";
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private static final long serialVersionUID = 10L;
   private BenhNhan benhNhan;
   private ThamKham thamKham;
   private ClsKham clskham;
   private String gioi;
   private String tuoi;
   private String ngaySinh;
   private String ngayTt;
   private String maBanKham;
   private String loai = "";
   private String maCLS = "";
   private Integer maSoCLS = null;
   private String tenCLS = "";
   private String maKhoa = "";
   private Boolean mien = new Boolean(false);
   private Boolean ngoaiDanhMuc = new Boolean(false);
   private Boolean yeuCau = new Boolean(false);
   private Boolean kyThuatCao = new Boolean(false);
   private short soLuong = 0;
   private Double donGia = new Double(0.0D);
   private Boolean dichVu = new Boolean(false);
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private String resultHidden = "";
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private String kyhieu;
   private String quyen;
   private String bienlai;
   private String maPhieu;

   public void refreshnhanvienthungan()
   {
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
   }

   private static Logger log = Logger.getLogger(B3236_HoanThuTienCanLamSangAction.class);
   @DataModel
   private List<ClsKham> listCtkq = new ArrayList();
   @DataModelSelection
   @Out(required=false)
   private ClsKham nhapctSelected;
   private ClsKham ctCLSKhamSelectedOld = null;
   private ClsKham ctCLSKham = null;
   private boolean updateNhapct = false;
   private DtDmCum cum;
   @In(required=false)
   @Out(required=false)
   Identity identity;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");






     refreshnhanvienthungan();

     log.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     log.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       log.info("nhanVienThungan:" + this.nhanVienThungan);

       this.cum = pc.getDtdmcumMa();
     }
     return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
   }

   private void emtyData()
   {
     this.ctCLSKham = new ClsKham();
     SetInforUtil.setInforIfNullForClsKham(this.ctCLSKham);

     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();

     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     this.thamKham.setTiepdonMa(new TiepDon());
     this.thamKham.getTiepdonMa().setBenhnhanMa(this.benhNhan);
     SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());

     this.clskham = new ClsKham();
     this.clskham.setClskhamThamkham(this.thamKham);

     setInforIfNullForClsKham(this.clskham);
     this.nhapctSelected = new ClsKham();
     setInforIfNullForClsKham(this.nhapctSelected);
     this.tuoi = "";
     this.ngaySinh = "";
     this.ngayTt = "";

     this.resultHidden = "";
     this.reportFinished = "";
     this.reportFileNameHid = "";

     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
   }

   private void hasNoMaTiepDon()
   {
     emtyData();
     resetValue();
   }

   public void Caculation(List<ClsKham> clslist)
   {
     HsThtoank hsttk = new HsThtoank();
     hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());
     HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtil.tinhToanChoHSTKKham(hsttk);

     this.permiengiam = 0;
     this.miengiam = hsttk.getHsthtoankXetgiam();


     this.thatthu = Double.valueOf(hsttk.getHsthtoankThatthu() == null ? 0.0D : hsttk.getHsthtoankThatthu().doubleValue());

     this.perbhytchi = hsttk.getHsthtoankTylebh().shortValue();

     this.bhytchi = hsttk.getHsthtoankBhyt();

     this.thanhtien1 = hsttk.getHsthtoankTongchi();

     this.perbntra = (100 - this.perbhytchi);

     this.bntra = hsttk.getHsthtoankBntra();
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "ThuVienPhi_SoLieuKhamBenh_HoanThuTienCanLamSam";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void resetValue()
   {
     refreshnhanvienthungan();
     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.maKhoa = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.dichVu = new Boolean(false);

     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";
   }

   public void nhapctAjax()
     throws Exception
   {
     log.info("*****Begin nhapctAjax() *****");
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       return;
     }
     this.ctCLSKham = ((ClsKham)BeanUtils.cloneBean(this.nhapctSelected));
     if ((this.ctCLSKham.getClskhamTra() == null) || (this.ctCLSKham.getClskhamTra().shortValue() == 0)) {
       this.ctCLSKham.setClskhamTra(this.ctCLSKham.getClskhamLan());
     }
     this.ctCLSKhamSelectedOld = this.nhapctSelected;


     this.loai = this.ctCLSKham.getClskhamLoai();
     this.maSoCLS = this.ctCLSKham.getClskhamMahang(true).getDtdmclsbgMaso();
     this.maCLS = this.ctCLSKham.getClskhamMahang(true).getDtdmclsbgMa();
     this.mien = this.ctCLSKham.getClskhamMien();
     this.yeuCau = this.ctCLSKham.getClskhamYeucau();
     this.soLuong = this.ctCLSKham.getClskhamLan().shortValue();
     this.donGia = this.ctCLSKham.getClskhamDongia();
     this.dichVu = this.ctCLSKham.getClskhamDichvu();
     this.ngoaiDanhMuc = this.ctCLSKham.getClskhamNdm();
     this.kyThuatCao = this.ctCLSKham.getClskhamKtcao();


     this.maKhoa = this.nhapctSelected.getClskhamKhoa(true).getDmkhoaMa();


     log.info("***********end nhapctAjax***********");
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       return;
     }
     if (this.maCLS.equals(""))
     {
       resetValue();
       return;
     }
     if (this.soLuong == 0)
     {
       resetValue();
       return;
     }
     if (this.listCtkq == null) {
       this.listCtkq = new ArrayList();
     }
     if ((this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa().equals("")) || (this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       resetValue();
       return;
     }
     if ((this.ctCLSKham.getClskhamMaphieu() == null) || (this.ctCLSKham.getClskhamMaphieu().equals("")))
     {
       resetValue();
       return;
     }
     if ((this.ctCLSKham.getClskhamTra() == null) || (this.ctCLSKham.getClskhamTra().shortValue() <= 0))
     {
       resetValue();
       return;
     }
     this.thanhtien1 = Double.valueOf(this.thanhtien1.doubleValue() + this.donGia.doubleValue() * this.ctCLSKham.getClskhamTra().shortValue());
     if (this.ctCLSKhamSelectedOld != null)
     {
       this.listCtkq.remove(this.ctCLSKhamSelectedOld);
       this.ctCLSKhamSelectedOld = null;
     }
     this.listCtkq.add(this.ctCLSKham);


     this.ctCLSKham = new ClsKham();
     SetInforUtil.setInforIfNullForClsKham(this.ctCLSKham);

     resetValue();

     log.info("*****End Enter() *****");
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       return;
     }
     ClsKham clsKham_tmp = (ClsKham)this.listCtkq.get(index);
     if (clsKham_tmp.getClskhamMaphieud() != null) {
       return;
     }
     this.listCtkq.remove(index);

     this.ctCLSKham = new ClsKham();
     SetInforUtil.setInforIfNullForClsKham(this.ctCLSKham);

     Caculation(this.listCtkq);
     log.info("*****End delete() *****");
   }

   public void ghinhan()
     throws Exception
   {
     refreshnhanvienthungan();
     if ((this.maPhieu != null) && (!this.maPhieu.equals(""))) {
       return;
     }
     if ((this.listCtkq == null) || (this.listCtkq.size() == 0))
     {
       FacesMessages.instance().add(IConstantsRes.CHON_IT_NHAT_MOT_CLS_DE_HOAN_TRA, new Object[0]);
       return;
     }
     boolean hasCLSDeHoanTra = false;
     for (ClsKham cls : this.listCtkq) {
       if ((cls.getClskhamMaphieu() != null) && (!cls.getClskhamMaphieu().equals("")) &&


         (cls.getClskhamTra() != null) && (cls.getClskhamTra().shortValue() > 0)) {
         hasCLSDeHoanTra = true;
       }
     }
     if (!hasCLSDeHoanTra)
     {
       FacesMessages.instance().add(IConstantsRes.CHON_IT_NHAT_MOT_CLS_DE_HOAN_TRA, new Object[0]);
       return;
     }
     log.info("*****Begin Ghi nhan() *****");
     log.info("*****so phan tu insert *****" + this.listCtkq.size());
     if ((this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa().equals("")) || (this.benhNhan.getBenhnhanHoten() == null) || (this.benhNhan.getBenhnhanHoten().equals("")))
     {
       resetValue();
       return;
     }
     try
     {
       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
       for (int i = 0; i < this.listCtkq.size(); i++) {
         removeIfNullForClsKham((ClsKham)this.listCtkq.get(i));
       }
       ArrayList<ClsKham> arrayListHasMaPhieuH = new ArrayList();
       for (ClsKham cls : this.listCtkq)
       {
         if ((cls.getClskhamMaphieu() != null) && (!cls.getClskhamMaphieu().equals("")) && ((cls.getClskhamMaphieud() == null) || (cls.getClskhamMaphieud().equals(""))) && (cls.getClskhamTra() != null) && (cls.getClskhamTra().shortValue() > 0))
         {
           cls.setClskhamCumHoan(this.cum);

           cls.setClskhamKyHieuHoan(this.kyhieu);
           cls.setClskhamQuyenHoan(this.quyen);
           cls.setClskhamBienLaiHoan(this.bienlai);

           cls.setClskhamThunganHoan(this.nhanVienThungan);


           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);


           Date d = formatter.parse(this.ngayTt);
           Calendar dCalendar = Calendar.getInstance();
           dCalendar.setTime(d);

           cls.setClskhamNgaygioHoanThu(dCalendar.getTime());
         }
         if ((cls.getClskhamMaphieud() != null) && (!cls.getClskhamMaphieud().equals(""))) {
           arrayListHasMaPhieuH.add(cls);
         }
       }
       String maPhieu;

       if ((this.maBanKham == null) || (this.maBanKham.equals(""))) {
         maPhieu = clsKhamDelegate.createClsKhamHoanTra(this.listCtkq, this.thamKham.getTiepdonMa().getTiepdonMa());
       } else {
         maPhieu = clsKhamDelegate.createClsKhamHoanTra(this.listCtkq, this.thamKham.getTiepdonMa().getTiepdonMa(), this.maBanKham);
       }
       this.maPhieu = maPhieu;

       log.info("this.maPhieu------------:" + this.maPhieu);

       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.resultHidden = "success";
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
       log.error("*************loi***********" + e.toString());
       e.printStackTrace();
     }
     try
     {
       for (int i = 0; i < this.listCtkq.size(); i++)
       {
         setInforIfNullForClsKham((ClsKham)this.listCtkq.get(i));

         SetInforUtil.setInforIfNullForThamKham(((ClsKham)this.listCtkq.get(i)).getClskhamThamkham());

         SetInforUtil.setInforIfNullForTiepDon(((ClsKham)this.listCtkq.get(i)).getClskhamThamkham().getTiepdonMa());
       }
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
       e.printStackTrace();
     }
     log.info("*****End Ghi nhan() *****");
     this.reportFinished = "";
   }

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   public void sualai()
     throws Exception
   {
     try
     {
       ResetForm();
       this.resultHidden = "";
       this.reportFinished = "";
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;

   public String inphieu()
     throws Exception
   {
     log.info("Begining inphieu()");
     if ((this.maPhieu == null) || (this.maPhieu.equals(""))) {
       return "";
     }
     try
     {
       XuatReport();
     }
     catch (Exception e)
     {
       log.info("Loi trong khi xuat report" + e.toString());
     }
     log.info("End inphieu()");
     return "B3360_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport()
   {
     this.reportTypeVP = "Hoanthutiencanlamsang";
     log.info("Vao Method XuatReport thuoc y dung cu phong kham");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/PhieuChi.jrxml";

       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       Map<String, Object> params = new HashMap();
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("tenBenhNhan", this.benhNhan.getBenhnhanHoten());

       String diachistr = "";
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhNhan.getBenhnhanDiachi() + ",";
       }
       if (this.benhNhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.benhNhan.getXaMa(true).getDmxaTen() + ",";
       }
       if (this.benhNhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.benhNhan.getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.benhNhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.benhNhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("diaChiBenhNhan", diachistr);




       params.put("tienThuoc", this.thanhtien1);
       params.put("TIENBANGCHU", Utils.NumberToString(this.thanhtien1.doubleValue()));

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
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Hoanthutiencanlamsang");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathVP);
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

   private void setOtherValue()
   {
     log.info("Begining setOtherValue()");
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if (this.thamKham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null)
     {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamKham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));

       log.info("Ngay sinh :" + this.ngaySinh);
     }
     if ((this.clskham.getClskhamNgaygiott() != null) && (!this.clskham.getClskhamNgaygiott().equals("")))
     {
       this.ngayTt = formatter.format(Long.valueOf(this.clskham.getClskhamNgaygioHoanThu().getTime()));
       log.info("Ngay thanh toan :" + this.ngayTt);
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
     log.info("End setOtherValue()");
   }

   private void setinfor(ClsKham nhapctSelected)
   {
     DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();

     nhapctSelected.setClskhamMahang(dmkythuat);
     if (this.thamKham != null) {
       nhapctSelected.setClskhamThamkham(this.thamKham);
     }
     try
     {
       if (!"".equals(this.ngayTt))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(this.ngayTt));
         nhapctSelected.setClskhamNgaygio(cal.getTime());
       }
     }
     catch (Exception e) {}
     log.info(" tenCLS3 " + this.tenCLS);
   }

   public void subDisplayInfor()
   {
     if ((this.thamKham.getTiepdonMa() == null) || (this.thamKham.getTiepdonMa().getTiepdonMa().equals("")))
     {
       hasNoMaTiepDon();
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, new Object[0]);
       this.listCtkq = new ArrayList();
       return;
     }
     ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
     ThamKham tk = tkDelegate.findByMaTiepDon(this.thamKham.getTiepdonMa().getTiepdonMa());
     if (tk != null)
     {
       this.thamKham = tk;
       SetInforUtil.setInforIfNullForThamKham(this.thamKham);
       SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());
       this.benhNhan = this.thamKham.getTiepdonMa().getBenhnhanMa();
       SetInforUtil.setInforIfNullForBN(this.benhNhan);
     }
     else
     {
       hasNoMaTiepDon();
       FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND, new Object[0]);
       this.listCtkq = new ArrayList();
       return;
     }
   }

   public void SetInforAllCLS(List<ClsKham> clskham_tmp)
   {
     this.listCtkq.clear();
     if ((clskham_tmp == null) || (clskham_tmp.size() == 0))
     {
       log.info("displayInfor   tham kham 22 ....." + this.clskham);
       this.listCtkq = new ArrayList();
       log.info("displayInfor   tham kham 23 ....." + this.clskham);
     }
     else
     {
       log.info("clskham_tmp khac null va co' data");
       for (int i = 0; i < clskham_tmp.size(); i++)
       {
         ClsKham cls = (ClsKham)clskham_tmp.get(i);
         if ((cls.getClskhamMaphieu() == null) || (cls.getClskhamMaphieu().equals("")))
         {
           log.info("clskham ko co ma phieu (chua tinh tien) , return");
         }
         else
         {
           setInforIfNullForClsKham(cls);
           cls.setClskhamThamkham(this.thamKham);

           this.listCtkq.add(cls);
           log.info("listCtkq co du lieu voi kich thuoc:" + this.listCtkq.size());
         }
       }
     }
   }

   public void displayInforMaPhieu()
   {
     if ((this.maPhieu == null) || (this.maPhieu.equals(""))) {
       return;
     }
     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

     List<ClsKham> clskham_tmp = clsKhamDelegate.findByMaPhieuHoan(this.maPhieu);
     if ((clskham_tmp == null) || (clskham_tmp.size() == 0))
     {
       FacesMessages.instance().add(IConstantsRes.KHONG_TIM_THAY_PHIEU, new Object[] { "" });

       return;
     }
     this.kyhieu = ((ClsKham)clskham_tmp.get(0)).getClskhamKyHieuHoan();
     this.quyen = ((ClsKham)clskham_tmp.get(0)).getClskhamQuyenHoan();
     this.bienlai = ((ClsKham)clskham_tmp.get(0)).getClskhamBienLaiHoan();


     this.maPhieu = ((ClsKham)clskham_tmp.get(0)).getClskhamMaphieud();

     this.thamKham = ((ClsKham)clskham_tmp.get(0)).getClskhamThamkham();
     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());
     this.benhNhan = this.thamKham.getTiepdonMa().getBenhnhanMa();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     SetInforAllCLS(clskham_tmp);

     Caculation(this.listCtkq);

     setOtherValue();

     this.thanhtien1 = new Double(0.0D);
     for (ClsKham clsTmp : clskham_tmp) {
       this.thanhtien1 = Double.valueOf(this.thanhtien1.doubleValue() + clsTmp.getClskhamDongia().doubleValue() * clsTmp.getClskhamTra().shortValue());
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       log.info("Begining displayInfor()");


       subDisplayInfor();

       ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

       List<ClsKham> clskham_tmp = new ArrayList();
       if ((this.maBanKham == null) || (this.maBanKham.equals("")))
       {
         log.info("no ban kham");
         clskham_tmp = clsKhamDelegate.findByTiepdonma(this.thamKham.getTiepdonMa().getTiepdonMa());
       }
       else
       {
         log.info("having ban kham");
         clskham_tmp = clsKhamDelegate.findByBanKhamVaMaTiepDon(this.maBanKham, this.thamKham.getTiepdonMa().getTiepdonMa());
       }
       this.listCtkq.clear();
       for (ClsKham eachCls : clskham_tmp)
       {
         int lan = eachCls.getClskhamLan() == null ? 0 : eachCls.getClskhamLan().intValue();
         int tra = eachCls.getClskhamTra() == null ? 0 : eachCls.getClskhamTra().intValue();
         if (lan == tra) {
           this.listCtkq.add(eachCls);
         }
       }
       for (ClsKham eachCls : this.listCtkq) {
         clskham_tmp.remove(eachCls);
       }
       this.listCtkq.clear();
       this.maPhieu = "";


       SetInforAllCLS(clskham_tmp);

       Caculation(this.listCtkq);


       setOtherValue();

       this.thanhtien1 = new Double(0.0D);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("error on function displayInfor" + e);
     }
     log.info("End displayInfor()");
   }

   private void removeIfNullForClsKham(ClsKham cls)
   {
     log.info("**********Begin setAllNullForClsKham()***********");
     if ("".equals(Utils.reFactorString(cls.getClskhamMahang().getDtdmclsbgMa())))
     {
       cls.setClskhamMahang(null);
       log.info("Ma hang null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa().getDmkhoaMa())))
     {
       cls.setClskhamKhoa(null);
       log.info("Khoa null");
     }
     log.info("----------cls.getClskhamChedott():" + cls.getClskhamChedott());
     if ("".equals(Utils.reFactorString(cls.getClskhamChedott().getDmdoituongMa())))
     {
       cls.setClskhamChedott(null);
       log.info("Che do tt null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamKhoa2().getDmkhoaMa())))
     {
       cls.setClskhamKhoa2(null);
       log.info("Khoa 2 null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMabs().getDtdmnhanvienMa())))
     {
       cls.setClskhamMabs(null);
       log.info("Bac si null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamMaloai().getDtdmclsMa())))
     {
       cls.setClskhamMaloai(null);
       log.info("Ma loai null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamNhanviencn().getDtdmnhanvienMa())))
     {
       cls.setClskhamNhanviencn(null);
       log.info("Nhan vien cn null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThuchien().getDtdmnhanvienMa())))
     {
       cls.setClskhamThuchien(null);
       log.info("Thuc hien  null");
     }
     if ("".equals(Utils.reFactorString(cls.getClskhamThungan().getDtdmnhanvienMa())))
     {
       cls.setClskhamThungan(null);
       log.info("Thu ngan null");
     }
     log.info("**********End setAllNullForClsKham()***********");
   }

   private void setInforIfNullForClsKham(ClsKham cls)
   {
     log.info("Begining setInforIfNullForClsKham (): " + cls);
     if (cls.getClskhamMahang() == null) {
       cls.setClskhamMahang(new DtDmClsBangGia());
     }
     if (cls.getClskhamKhoa() == null) {
       cls.setClskhamKhoa(new DmKhoa());
     }
     if (cls.getClskhamChedott() == null) {
       cls.setClskhamChedott(new DmDoiTuong());
     }
     log.info("----------cls.getClskhamChedott():" + cls.getClskhamChedott());
     if (cls.getClskhamKhoa2() == null) {
       cls.setClskhamKhoa2(new DmKhoa());
     }
     if (cls.getClskhamMabs() == null) {
       cls.setClskhamMabs(new DtDmNhanVien());
     }
     if (cls.getClskhamMaloai() == null) {
       cls.setClskhamMaloai(new DtDmCls());
     }
     if (cls.getClskhamNhanviencn() == null) {
       cls.setClskhamNhanviencn(new DtDmNhanVien());
     }
     if (cls.getClskhamThuchien() == null) {
       cls.setClskhamThuchien(new DtDmNhanVien());
     }
     if (cls.getClskhamThungan() == null) {
       cls.setClskhamThungan(new DtDmNhanVien());
     }
     log.info("End setInforIfNullForClsKham (): ");
   }

   private void ResetForm()
   {
     refreshnhanvienthungan();
     log.info("Begining ResetForm(): ");
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();
     this.thamKham.setTiepdonMa(new TiepDon());
     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     SetInforUtil.setInforIfNullForTiepDon(this.thamKham.getTiepdonMa());

     this.clskham = new ClsKham();
     this.clskham.setClskhamThamkham(this.thamKham);
     setInforIfNullForClsKham(this.clskham);
     this.gioi = "";
     this.tuoi = "";
     this.ngaySinh = "";
     this.ngayTt = "";

     this.loai = "";
     this.maCLS = "";
     this.maSoCLS = null;
     this.tenCLS = "";
     this.maKhoa = "";
     this.mien = new Boolean(false);
     this.ngoaiDanhMuc = new Boolean(false);
     this.yeuCau = new Boolean(false);
     this.kyThuatCao = new Boolean(false);
     this.soLuong = 0;
     this.donGia = new Double(0.0D);
     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.listCtkq = new ArrayList();
     this.resultHidden = "";
     this.maPhieu = "";

     this.maBanKham = "";

     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";

     log.info("End ResetForm(): ");
   }

   private String reportFinished = "";

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

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
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

   public List<ClsKham> getListCtkq()
   {
     return this.listCtkq;
   }

   public void setListCtkq(List<ClsKham> listCtkq)
   {
     this.listCtkq = listCtkq;
   }

   public ClsKham getNhapctSelected()
   {
     return this.nhapctSelected;
   }

   public void setNhapctSelected(ClsKham nhapctSelected)
   {
     this.nhapctSelected = nhapctSelected;
   }

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public ThamKham getThamKham()
   {
     return this.thamKham;
   }

   public void setThamKham(ThamKham thamKham)
   {
     this.thamKham = thamKham;
   }

   public ClsKham getClskham()
   {
     return this.clskham;
   }

   public void setClskham(ClsKham clskham)
   {
     this.clskham = clskham;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getLoai()
   {
     return this.loai;
   }

   public void setLoai(String loai)
   {
     this.loai = loai;
   }

   public String getMaCLS()
   {
     return this.maCLS;
   }

   public void setMaCLS(String maCLS)
   {
     this.maCLS = maCLS;
   }

   public Integer getMaSoCLS()
   {
     return this.maSoCLS;
   }

   public void setMaSoCLS(Integer maSoCLS)
   {
     this.maSoCLS = maSoCLS;
   }

   public short getSoLuong()
   {
     return this.soLuong;
   }

   public void setSoLuong(short soLuong)
   {
     this.soLuong = soLuong;
   }

   public Double getDonGia()
   {
     return this.donGia;
   }

   public void setDonGia(Double donGia)
   {
     this.donGia = donGia;
   }

   public Boolean getYeuCau()
   {
     return this.yeuCau;
   }

   public void setYeuCau(Boolean yeuCau)
   {
     this.yeuCau = yeuCau;
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

   public int getPerbhytchi()
   {
     return this.perbhytchi;
   }

   public void setPerbhytchi(int perbhytchi)
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

   public String getNgayTt()
   {
     return this.ngayTt;
   }

   public void setNgayTt(String ngayTt)
   {
     this.ngayTt = ngayTt;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public Boolean getMien()
   {
     return this.mien;
   }

   public void setMien(Boolean mien)
   {
     this.mien = mien;
   }

   public Boolean getNgoaiDanhMuc()
   {
     return this.ngoaiDanhMuc;
   }

   public void setNgoaiDanhMuc(Boolean ngoaiDanhMuc)
   {
     this.ngoaiDanhMuc = ngoaiDanhMuc;
   }

   public Boolean getKyThuatCao()
   {
     return this.kyThuatCao;
   }

   public void setKyThuatCao(Boolean kyThuatCao)
   {
     this.kyThuatCao = kyThuatCao;
   }

   public String getPosition()
   {
     return this.position;
   }

   public void setPosition(String position)
   {
     this.position = position;
   }

   public String getReportFinished()
   {
     return this.reportFinished;
   }

   public void setReportFinished(String reportFinished)
   {
     this.reportFinished = reportFinished;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public String getTenCLS()
   {
     return this.tenCLS;
   }

   public void setTenCLS(String tenCLS)
   {
     this.tenCLS = tenCLS;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getReportFileNameHid()
   {
     return this.reportFileNameHid;
   }

   public void setReportFileNameHid(String reportFileNameHid)
   {
     this.reportFileNameHid = reportFileNameHid;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getMaBanKham()
   {
     return this.maBanKham;
   }

   public void setMaBanKham(String maBanKham)
   {
     this.maBanKham = maBanKham;
   }

   public ClsKham getCtCLSKham()
   {
     return this.ctCLSKham;
   }

   public void setCtCLSKham(ClsKham ctCLSKham)
   {
     this.ctCLSKham = ctCLSKham;
   }

   public Boolean getDichVu()
   {
     return this.dichVu;
   }

   public void setDichVu(Boolean dichVu)
   {
     this.dichVu = dichVu;
   }

   public ClsKham getCtCLSKhamSelectedOld()
   {
     return this.ctCLSKhamSelectedOld;
   }

   public void setCtCLSKhamSelectedOld(ClsKham ctCLSKhamSelectedOld)
   {
     this.ctCLSKhamSelectedOld = ctCLSKhamSelectedOld;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public String getKyhieu()
   {
     return this.kyhieu;
   }

   public void setKyhieu(String kyhieu)
   {
     this.kyhieu = kyhieu;
   }

   public String getQuyen()
   {
     return this.quyen;
   }

   public void setQuyen(String quyen)
   {
     this.quyen = quyen;
   }

   public String getBienlai()
   {
     return this.bienlai;
   }

   public void setBienlai(String bienlai)
   {
     this.bienlai = bienlai;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3236_HoanThuTienCanLamSangAction

 * JD-Core Version:    0.7.0.1

 */