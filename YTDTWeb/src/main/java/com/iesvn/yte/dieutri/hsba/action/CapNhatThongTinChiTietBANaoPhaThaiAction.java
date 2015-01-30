 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNaophathaiCtDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChiTietNaophathaiDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;
 import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathaiCt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
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
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B211_3_CapNhatThongTinNaoPhaThai")
 @Synchronized(timeout=600000L)
 public class CapNhatThongTinChiTietBANaoPhaThaiAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(CapNhatThongTinChiTietBANaoPhaThaiAction.class);
   @Out(required=false)
   @In(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintDT = null;
   @In(required=false)
   @Out(required=false)
   private String soBenhAn;
   @In(required=false)
   @Out(required=false)
   private String khoaMa;
   private String ghiNhanException;
   private String hoTen;
   private String ngay;
   private String dienbienbenh;
   private String thuoc;
   private String lieuluong;
   private String cachtiem;
   private String anuongholy;
   @DataModel
   private List<HsbaChiTietNaophathaiCt> listCtNaophathais;
   @DataModelSelection("listCtNaophathais")
   private HsbaChiTietNaophathaiCt ctNaophathaiSelection;
   private HsbaChiTietNaophathaiCtDelegate ctNaophathaiDAO;
   private HsbaChuyenMon hsbaChuyenMon;
   private HsbaChiTietNaophathai hsbaCTNaophathai;
   private Hsba hosobenhan;
   private TiepDon tiepDon;
   private BenhNhan benhnhan;
   private String kinhlancuoingay;
   private int index = 0;

   @Create
   @Begin(nested=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init Chi tiet BA Nao pha thai ***");

     this.listCtNaophathais = new ArrayList();
     this.ctNaophathaiDAO = HsbaChiTietNaophathaiCtDelegate.getInstance();

     HsbaChuyenMonDelegate hsbacmDel = HsbaChuyenMonDelegate.getInstance();
     this.hsbaChuyenMon = hsbacmDel.findBySoVaoVien_MaKhoa(this.soBenhAn, this.khoaMa);
     if (this.hsbaChuyenMon != null)
     {
       if (this.hsbaChuyenMon.getHsbaSovaovien() != null)
       {
         this.hosobenhan = this.hsbaChuyenMon.getHsbaSovaovien();
         if ((this.hosobenhan.getTiepdonMa() != null) && (!this.hosobenhan.getTiepdonMa().equals("")))
         {
           TiepDonDelegate tiepdonDel = TiepDonDelegate.getInstance();
           this.tiepDon = tiepdonDel.find(this.hosobenhan.getTiepdonMa());
           if (this.tiepDon == null) {
             this.tiepDon = new TiepDon();
           }
         }
         else
         {
           this.tiepDon = new TiepDon();
         }
         if (this.hosobenhan.getBenhnhanMa() != null) {
           this.benhnhan = this.hosobenhan.getBenhnhanMa();
         } else {
           this.benhnhan = new BenhNhan();
         }
         this.hoTen = this.benhnhan.getBenhnhanHoten();

         HsbaChiTietNaophathai hsbaCTNaophathaiTemp = null;
         try
         {
           hsbaCTNaophathaiTemp = HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
         }
         catch (Exception e)
         {
           log.info("error:" + e);
         }
         if (hsbaCTNaophathaiTemp != null)
         {
           this.hsbaCTNaophathai = hsbaCTNaophathaiTemp;

           SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
           if (this.hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi() != null) {
             this.kinhlancuoingay = sdf.format(this.hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi());
           }
           this.listCtNaophathais = this.ctNaophathaiDAO.findByHsbaChiTietNaophathaiMaso(hsbaCTNaophathaiTemp.getHsbactnaophathaiMa());
         }
         else
         {
           this.hsbaCTNaophathai = new HsbaChiTietNaophathai();
         }
       }
       else
       {
         this.hosobenhan = new Hsba();
         this.tiepDon = new TiepDon();
         this.benhnhan = new BenhNhan();
       }
     }
     else {
       return "DieuTri_CapNhat_CapNhatThongTinChung";
     }
     log.info("***Finished init **");
     return "DieuTri_CapNhat_CapNhatThongTinChiTietBANaophathai";
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");
     log.info("***Finished back **");
     return "DieuTri_CapNhat_CapNhatThongTinChung";
   }

   public void get_thuoc_info()
   {
     this.thuoc = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       if (Utils.getDBDate("00:00", this.ngay) == null)
       {
         log.info("### Cap nhat BA Nao pha thai -- get_thuoc_info(): ERROR Parse NGAY !! ");
         return;
       }
       ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
       List<ThuocNoiTru> listThuocNoiTru = tntDelegate.findBySoVaoVienAndKhoaMaAndNgay(this.hsbaChuyenMon.getHsbaSovaovien().getHsbaSovaovien(), this.hsbaChuyenMon.getKhoaMa().getDmkhoaMa(), this.ngay);
       if ((listThuocNoiTru != null) && (listThuocNoiTru.size() > 0)) {
         for (ThuocNoiTru each : listThuocNoiTru)
         {
           this.thuoc = (this.thuoc + "- " + each.getThuocnoitruMathuoc(true).getDmthuocTen());
           if (each.getThuocnoitruSoluong() != null) {
             this.thuoc = (this.thuoc + ", " + each.getThuocnoitruSoluong().toString());
           }
           this.thuoc = (this.thuoc + " " + each.getThuocnoitruMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen());
           this.thuoc += "\n";
         }
       } else {
         this.thuoc = IConstantsRes.KHONG_DUNG_THUOC;
       }
     }
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     insertRow();
     reset_ctbant();
     log.info("*****End Enter() *****");
   }

   private void insertRow()
   {
     HsbaChiTietNaophathaiCt each = new HsbaChiTietNaophathaiCt();
     each.setHsbactnaophathaictDienbien(this.dienbienbenh);
     each.setHsbactnaophathaictThuoc(this.thuoc);
     each.setHsbactnaophathaictAnuongholy(this.anuongholy);
     each.setHsbactnaophathaictCachtiem(this.cachtiem);
     each.setHsbactnaophathaictLieuluong(this.lieuluong);
     if ((this.ngay != null) && (!this.ngay.equals(""))) {
       if (Utils.getDBDate("00:00", this.ngay) == null) {
         each.setHsbactnaophathaictNgay(new Date());
       } else {
         each.setHsbactnaophathaictNgay(Utils.getDBDate("00:00", this.ngay).getTime());
       }
     }
     each.setHsbactnaophathaiMa(this.hsbaCTNaophathai);
     this.listCtNaophathais.add(each);
   }

   public void delete(int index)
     throws Exception
   {
     log.info("***** Begin delete() *****");
     this.listCtNaophathais.remove(index);
     reset_ctbant();
     log.info("***** End delete() *****");
   }

   public void reset_ctbant()
   {
     this.anuongholy = "";
     this.cachtiem = "";
     this.lieuluong = "";
     this.dienbienbenh = "";
     this.thuoc = "";
   }

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");
     this.ghiNhanException = null;
     this.hsbaCTNaophathai.setHsbacmMa(this.hsbaChuyenMon);
     SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
     try
     {
       this.hsbaCTNaophathai.setHsbactnaophathaiNgaydaukinhcuoi(sdf.parse(this.kinhlancuoingay));
     }
     catch (ParseException e) {}
     RemoveUtil.removeAllNullFromHSBACTNaophathai(this.hsbaCTNaophathai);
     if (this.hsbaCTNaophathai.getHsbactnaophathaiMa() == null) {
       this.hsbaCTNaophathai = HsbaChiTietNaophathaiDelegate.getInstance().create(this.hsbaCTNaophathai);
     } else {
       HsbaChiTietNaophathaiDelegate.getInstance().edit(this.hsbaCTNaophathai);
     }
     List<HsbaChiTietNaophathaiCt> listTempForRemove = this.ctNaophathaiDAO.findByHsbaChiTietNaophathaiMaso(this.hsbaCTNaophathai.getHsbactnaophathaiMa());

     List<Integer> listIndexTemp = new ArrayList();
     for (HsbaChiTietNaophathaiCt each : this.listCtNaophathais)
     {
       each.setHsbactnaophathaiMa(this.hsbaCTNaophathai);
       if (each.getHsbactnaophathaictMaso() == null) {
         each = this.ctNaophathaiDAO.create(each);
       } else {
         this.ctNaophathaiDAO.edit(each);
       }
       listIndexTemp.add(each.getHsbactnaophathaictMaso());
     }
     log.info("***listIndexTemp: **" + listIndexTemp.toString());
     for (HsbaChiTietNaophathaiCt each2 : listTempForRemove)
     {
       log.info("***each2: **" + each2.getHsbactnaophathaictMaso());
       if (!listIndexTemp.contains(each2.getHsbactnaophathaictMaso()))
       {
         log.info("***VAO REMOVE **");
         this.ctNaophathaiDAO.remove(each2);
       }
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     log.info("***Finished ghinhan **");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       resetForm();
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void resetForm()
   {
     log.info("Begining ResetForm(): ");
     this.ghiNhanException = null;
     log.info("End ResetForm(): ");
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "CapNhatThongTinChiTietBANaophathai";
     log.info("Vao Method XuatReport bao cao xcap nhat thong tin chi tiet benh an naophathai");
     String baocao1 = null;
     if (this.hsbaChuyenMon != null)
     {
       HsbaChiTietNaophathai hsbaCTNaophathaiTemp = null;
       try
       {
         hsbaCTNaophathaiTemp = HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM(this.hsbaChuyenMon.getHsbacmMa());
       }
       catch (Exception e)
       {
         log.info("error - XuatReport - HsbaChiTietNaophathaiDelegate.getInstance().findByHsbaCM: " + e);
       }
       if (hsbaCTNaophathaiTemp != null) {
         this.hsbaCTNaophathai = hsbaCTNaophathaiTemp;
       }
     }
     else
     {
       return;
     }
     try
     {
       log.info("bat dau method XuatReport ");
       String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/N011_benhannaothai.jrxml";

       log.info("da thay file templete " + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);








       String sHoTenBN = "";
       if (this.benhnhan.getBenhnhanHoten() != null) {
         sHoTenBN = this.benhnhan.getBenhnhanHoten();
       }
       params.put("HOTENBN", sHoTenBN);

       String diachistr = "";
       if (this.benhnhan.getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.benhnhan.getBenhnhanDiachi();
       }
       if (this.benhnhan.getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getXaMa(true).getDmxaTen();
       }
       if (this.benhnhan.getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getHuyenMa(true).getDmhuyenTen();
       }
       if (this.benhnhan.getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + ", " + this.benhnhan.getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHIBN", diachistr);

       String sNgheNghiep = "";
       if (this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null)
       {
         sNgheNghiep = this.benhnhan.getBenhnhanNghe(true).getDmnghenghiepTen();
         params.put("NGHENGHIEPBN", sNgheNghiep);
       }
       String sDonViGoi = "";
       if (this.hosobenhan.getHsbaDonvigoi() != null) {
         sDonViGoi = this.hosobenhan.getHsbaDonvigoi(true).getDmbenhvienTen();
       }
       params.put("DONVIGOI", sDonViGoi);
       if ((sDonViGoi != null) && (!sDonViGoi.trim().equals(""))) {
         params.put("NOIGIOITHIEU", sDonViGoi);
       } else {
         params.put("NOIGIOITHIEU", "");
       }
       int iTuoi = this.benhnhan.getBenhnhanTuoi().intValue();
       int iDonviTuoi = this.benhnhan.getBenhnhanDonvituoi().shortValue();
       String sDonViTuoi = "";
       if (iDonviTuoi == 1) {
         sDonViTuoi = "";
       } else if (iDonviTuoi == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (iDonviTuoi == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOIBN", iTuoi + " " + sDonViTuoi);

       params.put("HOTENCHONG", this.hsbaCTNaophathai.getHsbactnaophathaiHotenchong());
       params.put("TUOICHONG", this.hsbaCTNaophathai.getHsbactnaophathaiTuoichong());
       params.put("NGHENGHIEPCHONG", this.hsbaCTNaophathai.getHsbactnaophathaiNghenghiepchong());
       params.put("DIACHICHONG", this.hsbaCTNaophathai.getHsbactnaophathaiDiachichong());
       params.put("LYDOVAOVIEN", this.hsbaCTNaophathai.getHsbactnaophathaiLydovaov());

       params.put("SOLANDE", this.hsbaCTNaophathai.getHsbactnaophathaiSolande());
       params.put("SOLANDEKHO", this.hsbaCTNaophathai.getHsbactnaophathaiSolandekho());
       params.put("SOLANSAY", this.hsbaCTNaophathai.getHsbactnaophathaiSolansay());
       params.put("SOLANNAO", this.hsbaCTNaophathai.getHsbactnaophathaiSolannao());
       params.put("LYDONAO", this.hsbaCTNaophathai.getHsbactnaophathaiLydonao());

       params.put("APDUNGPPHANCHESINHDE", this.hsbaCTNaophathai.getHsbactnaophathaiApdungpphanchesinhde());
       params.put("DACOBENHPHUKHOAGI", this.hsbaCTNaophathai.getHsbactnaophathaiDacobenhphukhoagi());
       params.put("DACHUALANH", this.hsbaCTNaophathai.getHsbactnaophathaiDachualanh());
       params.put("DANGCOBENHPHUKHOANAO", this.hsbaCTNaophathai.getHsbactnaophathaiDangcobenhphukhoagi());
       params.put("DANGCHUAHAYKHONG", this.hsbaCTNaophathai.getHsbactnaophathaiDangchuahaykhong());
       if (this.hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi() != null) {
         params.put("NGAYDAUCUAKINHCUOI", sdf.format(this.hsbaCTNaophathai.getHsbactnaophathaiNgaydaukinhcuoi()));
       }
       params.put("CHIEUCAOTC", this.hsbaCTNaophathai.getHsbactnaophathaiChieucaotucung());
       params.put("AMHO", this.hsbaCTNaophathai.getHsbactnaophathaiAmho());
       params.put("AMDAO", this.hsbaCTNaophathai.getHsbactnaophathaiAmdao());
       params.put("COTUCUNG", this.hsbaCTNaophathai.getHsbactnaophathaiCotucung());
       params.put("TUICUNG", this.hsbaCTNaophathai.getHsbactnaophathaiTuicung());
       params.put("TUCUNG", this.hsbaCTNaophathai.getHsbactnaophathaiTucung());
       params.put("PHANPHU", this.hsbaCTNaophathai.getHsbactnaophathaiPhanphu());

       params.put("MACH", this.tiepDon.getTiepdonMach());
       params.put("NHIETDO", this.tiepDon.getTiepdonNhietdo());
       params.put("HUYETAPMAX", this.tiepDon.getTiepdonHamax());
       params.put("HUYETAPMIN", this.tiepDon.getTiepdonHamin());
       params.put("NHIPTHO", this.tiepDon.getTiepdonNhiptho());
       params.put("CANNANG", this.tiepDon.getTiepdonTrluong());

       params.put("PHANUNGSINHVAT", this.hsbaCTNaophathai.getHsbactnaophathaiPhanungsinhvat());
       params.put("CONGTHUCBACHCAU", this.hsbaCTNaophathai.getHsbactnaophathaiCongthucbanhcau());
       params.put("SOLUONGHONGCAU", this.hsbaCTNaophathai.getHsbactnaophathaiSoluonghongcau());
       params.put("MAUDONG", this.hsbaCTNaophathai.getHsbactnaophathaiMaudong());
       params.put("MAUCHAY", this.hsbaCTNaophathai.getHsbactnaophathaiMauchay());
       params.put("DIENTIM", this.hsbaCTNaophathai.getHsbactnaophathaiDientim());
       params.put("CHUPPHOITIM", this.hsbaCTNaophathai.getHsbactnaophathaiChupphoitim());

       String sBenhKemTheo = "";
       if (this.hsbaChuyenMon.getHsbacmBenhphu() != null) {
         sBenhKemTheo = this.hsbaChuyenMon.getHsbacmBenhphu(true).getDmbenhicdTen();
       }
       params.put("BENHKEMTHEOCANLUUY", sBenhKemTheo);

       params.put("CHIDINHTHUTHUAT", this.hsbaCTNaophathai.getHsbactnaophathaiChidinhthuthuat());
       params.put("CHIEUSAUTUCUNG", this.hsbaCTNaophathai.getHsbactnaophathaiChieusautucung());
       params.put("NONGHEGAR", this.hsbaCTNaophathai.getHsbactnaophathaiNonghegar());
       params.put("THUOCTEME", this.hsbaCTNaophathai.getHsbactnaophathaiThuocteme());
       params.put("OCYTOCINE", this.hsbaCTNaophathai.getHsbactnaophathaiOcytocime());
       params.put("OCYTOCINE_DONVI", this.hsbaCTNaophathai.getHsbactnaophathaiOcytocimeDonvi());
       params.put("NAODUNGCU", this.hsbaCTNaophathai.getHsbactnaophathaiNaodungcu());
       params.put("GAP", this.hsbaCTNaophathai.getHsbactnaophathaiGap());
       params.put("DATTUINUOC", this.hsbaCTNaophathai.getHsbactnaophathaiDattuinuoc());
       params.put("THUTHUATKETHOPKHAC", this.hsbaCTNaophathai.getHsbactnaophathaiThuthuatkethopkhac());

       params.put("SL_CL_TRUNGTHAINHAU", this.hsbaCTNaophathai.getHsbactnaophathaiSlClTrungthainhaulayra());
       params.put("BIENCHUNGLUCLAYTHAI", this.hsbaCTNaophathai.getHsbactnaophathaiBienchungluclaythai());
       params.put("KETQUAXUATVIEN", this.hsbaCTNaophathai.getHsbactnaophathaiKetquakhixuatvien());
       params.put("HSBACTNAOPHATHAI_MA", this.hsbaCTNaophathai.getHsbactnaophathaiMa());
       if (this.hsbaCTNaophathai.getHsbactnaophathaiBslamba() != null) {
         params.put("BACSINAOPHATHAI", this.hsbaCTNaophathai.getHsbactnaophathaiBslamba(true).getDtdmnhanvienTen());
       }
       if (this.hsbaCTNaophathai.getHsbactnaophathaiBsdieutri() != null) {
         params.put("BACSIDT", this.hsbaCTNaophathai.getHsbactnaophathaiBsdieutri(true).getDtdmnhanvienTen());
       }
       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);


         log.info("Da connect DATABASE");
         this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
         log.info("fillReport THANHCONG");
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
         e.printStackTrace();
       }
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "capNhatThongTinChiTietBANaophathai");


       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
       this.index += 1;
       log.info("Index :" + getIndex());
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

   public Integer getSeconds(Date d)
   {
     long seconds = DateUtils.getFragmentInHours(d, 5) * 60L * 60L + DateUtils.getFragmentInMinutes(d, 11) * 60L;




     return Integer.valueOf(seconds + "");
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public String getReportPathTD()
   {
     return this.duongdanStrDT;
   }

   public void setReportPathTD(String reportPathTD)
   {
     this.duongdanStrDT = reportPathTD;
   }

   public String getReportTypeTD()
   {
     return this.loaiBCDT;
   }

   public void setReportTypeTD(String reportTypeTD)
   {
     this.loaiBCDT = reportTypeTD;
   }

   public JasperPrint getJasperPrintTD()
   {
     return this.jasperPrintDT;
   }

   public void setJasperPrintTD(JasperPrint jasperPrintTD)
   {
     this.jasperPrintDT = jasperPrintTD;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public HsbaChiTietNaophathai getHsbaCTNaophathai()
   {
     return this.hsbaCTNaophathai;
   }

   public void setHsbaCTNaophathai(HsbaChiTietNaophathai hsbaCTNaophathai)
   {
     this.hsbaCTNaophathai = hsbaCTNaophathai;
   }

   public String getHoTen()
   {
     return this.hoTen;
   }

   public void setHoTen(String hoTen)
   {
     this.hoTen = hoTen;
   }

   public String getKinhlancuoingay()
   {
     return this.kinhlancuoingay;
   }

   public void setKinhlancuoingay(String kinhlancuoingay)
   {
     this.kinhlancuoingay = kinhlancuoingay;
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getDienbienbenh()
   {
     return this.dienbienbenh;
   }

   public void setDienbienbenh(String dienbienbenh)
   {
     this.dienbienbenh = dienbienbenh;
   }

   public String getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(String thuoc)
   {
     this.thuoc = thuoc;
   }

   public String getLieuluong()
   {
     return this.lieuluong;
   }

   public void setLieuluong(String lieuluong)
   {
     this.lieuluong = lieuluong;
   }

   public String getCachtiem()
   {
     return this.cachtiem;
   }

   public void setCachtiem(String cachtiem)
   {
     this.cachtiem = cachtiem;
   }

   public String getAnuongholy()
   {
     return this.anuongholy;
   }

   public void setAnuongholy(String anuongholy)
   {
     this.anuongholy = anuongholy;
   }

   public List<HsbaChiTietNaophathaiCt> getListCtNaophathais()
   {
     return this.listCtNaophathais;
   }

   public void setListCtNaophathais(List<HsbaChiTietNaophathaiCt> listCtNaophathais)
   {
     this.listCtNaophathais = listCtNaophathais;
   }

   public HsbaChiTietNaophathaiCt getCtNaophathaiSelection()
   {
     return this.ctNaophathaiSelection;
   }

   public void setCtNaophathaiSelection(HsbaChiTietNaophathaiCt ctNaophathaiSelection)
   {
     this.ctNaophathaiSelection = ctNaophathaiSelection;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatThongTinChiTietBANaoPhaThaiAction

 * JD-Core Version:    0.7.0.1

 */