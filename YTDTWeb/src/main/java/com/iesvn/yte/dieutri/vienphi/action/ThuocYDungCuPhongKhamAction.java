 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.rmi.RemoteException;
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
 import javax.xml.rpc.ServiceException;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3231_Thuocydungcuphongkham")
 @Synchronized(timeout=6000000L)
 public class ThuocYDungCuPhongKhamAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(ThuocYDungCuPhongKhamAction.class);
   @DataModel
   private ArrayList<ThuocPhongKham> listTpk;
   @DataModelSelection
   @Out(required=false)
   private ThuocPhongKham tpkSelected;
   private TiepDon tiepDon;
   private BenhNhan benhNhan;
   private ThamKham thamKham;
   private String ngayHienTai;
   private String maThuocPk;
   private Integer gioiTinh;
   private String ngaySinh;
   private String tkMa;
   private String soLuong;
   private Integer updateItem;
   private String isUpdate;
   private String maThuoc;
   private int count;
   private String kyhieu;
   private String quyen;
   private String bienlai;
   private DtDmNhanVien nhanVienThungan = new DtDmNhanVien();
   private int permiengiam = 0;
   private Double miengiam = new Double(0.0D);
   private Double thatthu = new Double(0.0D);
   private int perbhytchi = 0;
   private Double bhytchi = new Double(0.0D);
   private Double thanhtien1 = new Double(0.0D);
   private int perbntra = 0;
   private Double bntra = new Double(0.0D);
   private Double khongThu = new Double(0.0D);
   private Double cls;
   private Double thuoc;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private DtDmCum cum = null;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private List<ThuocPhongKham> listCtTPK_temp = null;
   private List<ClsKham> clslist = null;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String loaiToaThuocThamKhamVaXuTri;
   @In(required=false)
   @Out(required=false)
   private String goToThuoc;
   @In(required=false)
   @Out(required=false)
   private String goToCLSPhongKham;

   private void tinhToanChoHSTKKham(HsThtoank hsttk, Boolean ghinhan)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);
     if (ghinhan.booleanValue() == true)
     {
       this.listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp();
       this.clslist = hsthtoankUtilTmp.getListCtkq_temp();
     }
   }

   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     this.goToThuoc = "done";

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
     logger.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     logger.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       this.nhanVienThungan = pc.getDtdmnhanvienMa();
       logger.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     reset();
     return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
   }

   @End
   public void endFunc() {}

   @Factory("goToThuoc")
   public void goToThuoc()
     throws Exception
   {
     this.goToThuoc = "done";




     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienThungan = nvDelegate.findByNd(this.identity.getUsername());
     if (this.nhanVienThungan == null) {
       this.nhanVienThungan = new DtDmNhanVien();
     }
     logger.info("nhanVienThungan1" + this.nhanVienThungan);
     PcCumThuPhiDelegate pcCumThuPhiDelegate = PcCumThuPhiDelegate.getInstance();
     PcCumThuPhi pc = pcCumThuPhiDelegate.findPcCumThuPhiByNgayAndNhanVien(new Date(), this.nhanVienThungan.getDtdmnhanvienMa());

     logger.info("PcCumThuPhi" + pc);
     if ((pc != null) && (pc.getDtdmnhanvienMa() != null))
     {
       this.nhanVienThungan = pc.getDtdmnhanvienMa();
       logger.info("nhanVienThungan:" + this.nhanVienThungan);
       this.cum = pc.getDtdmcumMa();
     }
     reset();

     logger.info("maTiepDonOut:" + this.maTiepDonOut);

     this.tiepDon.setTiepdonMa(this.maTiepDonOut);
     loadBenhNhan();
   }

   @Factory("returnToThuocYDungCuPhongKham")
   public void initForReturn()
     throws Exception
   {
     this.goToThuoc = "done";
     logger.info("-----begin initForReturn-----");
     loadBenhNhan();

     logger.info("-----end initForReturn-----");
   }

   public String clsphongkham()
     throws ServiceException, RemoteException
   {
     if ((this.tiepDon.getTiepdonMa() == null) || (this.tiepDon.getTiepdonMa().equals(""))) {
       return "";
     }
     this.maTiepDonOut = this.tiepDon.getTiepdonMa();

     this.goToCLSPhongKham = null;

     return "ThuVienPhi_SoLieuKhamBenh_CanLamSanPhongKham";
   }

   public void loadBenhNhan()
   {
     logger.info("-----loadBenhNhan()-----");
     logger.info("--***---tiepDon.getTiepdonMa()()---***--:" + this.tiepDon.getTiepdonMa());
     if ((this.tiepDon.getTiepdonMa() != null) && (!"".equals(this.tiepDon.getTiepdonMa())))
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       try
       {
         List<ThamKham> lstThamKham = tkDelegate.findAllByMaTiepDon(this.tiepDon.getTiepdonMa());
         if ((lstThamKham == null) || (lstThamKham.size() == 0))
         {
           FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepDon.getTiepdonMa(), new Object[0]);
           return;
         }
         this.thamKham = ((ThamKham)lstThamKham.get(0));
         logger.info("-----tham kham: " + this.thamKham.getThamkhamMa());

         this.tiepDon = this.thamKham.getTiepdonMa();
         this.benhNhan = this.tiepDon.getBenhnhanMa();
         logger.info("-----benh nhan: " + this.benhNhan.getBenhnhanMa());
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         if (this.benhNhan.getBenhnhanNgaysinh() != null) {
           this.ngaySinh = df.format(this.benhNhan.getBenhnhanNgaysinh());
         }
         this.count = 0;
         this.listTpk = new ArrayList();
         for (ThamKham tkTemp : lstThamKham)
         {
           ArrayList<ThuocPhongKham> listTpkTmp = (ArrayList)tpkDelegate.findByThamKham(tkTemp.getThamkhamMa(), Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
           if ((listTpkTmp != null) && (listTpkTmp.size() > 0))
           {
             this.count += listTpkTmp.size();
             for (ThuocPhongKham tpkTemp : listTpkTmp) {
               this.listTpk.add(tpkTemp);
             }
             logger.info("-----listTpk: " + this.listTpk.size());
           }
         }
         tinhToanChiPhi(this.listTpk);
       }
       catch (Exception e)
       {
         logger.error(e);
         e.printStackTrace();
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.tiepDon.getTiepdonMa(), new Object[0]);
         reset();
       }
     }
   }

   private void tinhToanChiPhi(ArrayList<ThuocPhongKham> listTpk)
   {
     HsThtoank hsttk = new HsThtoank();
     hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());
     HoSoThanhToanKhamUtil hsthtoankUtil = new HoSoThanhToanKhamUtil(this.thamKham.getTiepdonMa());
     hsthtoankUtil.tinhToanChoHSTKKham(hsttk);

     this.permiengiam = 0;
     this.miengiam = hsthtoankUtil.getMiengiam();
     this.thatthu = hsthtoankUtil.getThatthu();
     this.perbhytchi = hsthtoankUtil.getPerbhytchi();
     this.bhytchi = hsthtoankUtil.getBhytchi();
     this.thanhtien1 = hsthtoankUtil.getThanhtien1();
     this.perbntra = hsthtoankUtil.getPerbntra();
     this.bntra = hsthtoankUtil.getBntra();
     this.khongThu = hsthtoankUtil.getKhongThu();


     this.cls = Double.valueOf((hsttk.getHsthtoankCls() == null ? 0.0D : hsttk.getHsthtoankCls().doubleValue()) + (hsttk.getHsthtoankClsndm() == null ? 0.0D : hsttk.getHsthtoankClsndm().doubleValue()));


     this.thuoc = Double.valueOf((hsttk.getHsthtoankThuoc() == null ? 0.0D : hsttk.getHsthtoankThuoc().doubleValue()) + (hsttk.getHsthtoankThuocndm() == null ? 0.0D : hsttk.getHsthtoankThuocndm().doubleValue()) + (hsttk.getHsthtoankVtth() == null ? 0.0D : hsttk.getHsthtoankVtth().doubleValue()) + (hsttk.getHsthtoankVtthndm() == null ? 0.0D : hsttk.getHsthtoankVtthndm().doubleValue()));
   }

   public void tiepTucNhap()
   {
     logger.info("-----tiepTucNhap()-----");
     logger.info("-----tkMa: " + this.tkMa);
     logger.info("-----isUpdate: " + this.isUpdate);
     if (!this.isUpdate.equals("1"))
     {
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       TonKho tk = null;
       try
       {
         tk = tkDelegate.find(Integer.valueOf(this.tkMa));
       }
       catch (Exception e) {}
       if (this.updateItem.intValue() != -1)
       {
         logger.info("-----Cap nhat thuoc phong kham: " + this.updateItem);
         ThuocPhongKham tpk = (ThuocPhongKham)this.listTpk.get(this.updateItem.intValue());
         if (tk == null)
         {
           logger.info("-----Cap nhat so luong");
           tpk.setThuocphongkhamSoluong(Double.valueOf(this.soLuong));
         }
         else
         {
           logger.info("-----Cap nhat toan bo");
           tpk = createThuocPhongKhamFromTk(tk);
           tpk.setThuocphongkhamLoai(Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           try
           {
             tpk.setThuocphongkhamNgaygio(df.parse(this.ngayHienTai));
           }
           catch (ParseException e)
           {
             e.printStackTrace();
           }
           tpk.setThuocphongkhamSoluong(Double.valueOf(this.soLuong));
           tpk.setThuocphongkhamThamkham(this.thamKham);
           tpk.setThuocphongkhamBacsi(this.tiepDon.getTiepdonBacsi());
         }
         this.listTpk.set(this.updateItem.intValue(), tpk);
       }
       else
       {
         logger.info("-----Them moi thuoc phong kham");
         if (tk != null)
         {
           ThuocPhongKham tpk = createThuocPhongKhamFromTk(tk);
           tpk.setThuocphongkhamLoai("1");
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           try
           {
             tpk.setThuocphongkhamNgaygio(df.parse(this.ngayHienTai));
           }
           catch (ParseException e)
           {
             e.printStackTrace();
           }
           tpk.setThuocphongkhamSoluong(Double.valueOf(this.soLuong));
           tpk.setThuocphongkhamThamkham(this.thamKham);
           tpk.setThuocphongkhamBacsi(this.tiepDon.getTiepdonBacsi());
           tpk.setThuocphongkhamNgaygio(new Date());
           this.listTpk.add(tpk);
         }
       }
       logger.info("-----listTpk: " + this.listTpk.size());
       this.updateItem = Integer.valueOf(-1);
       this.count = this.listTpk.size();
       this.maThuoc = "";
     }
   }

   public void end()
     throws ParseException
   {
     logger.info("-----end()-----");
     if (this.listTpk.size() > 0)
     {
       for (ThuocPhongKham tpk : this.listTpk) {
         if ((tpk.getThuocphongkhamMaphieud() == null) || (tpk.getThuocphongkhamMaphieud().equals("")))
         {
           tpk.setThuocphongkhamThungan(this.nhanVienThungan);

           tpk.setThuocphongkhamCum(this.cum);


           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);


           tpk.setThuocphongkhamKyhieu(this.kyhieu);
           tpk.setThuocphongkhamQuyen(this.quyen);
           tpk.setThuocphongkhamBienlai(this.bienlai);


           Date d = formatter.parse(this.ngayHienTai);
           Calendar dCalendar = Calendar.getInstance();
           dCalendar.setTime(d);

           tpk.setThuocphongkhamNgaygiott(dCalendar.getTime());
         }
       }
       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       try
       {
         this.maThuocPk = tpkDelegate.createThuocPhongKham(this.listTpk, null, Utils.XU_TRI_THUOC_TAI_BAN_KHAM);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
       logger.info("-----ma phieu: " + this.maThuocPk);
       if (!this.maThuocPk.equals(""))
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         this.isUpdate = "1";

         HsThtoank hsttk = new HsThtoank();
         hsttk.setTiepdonMa(this.thamKham.getTiepdonMa());
         tinhToanChoHSTKKham(hsttk, Boolean.valueOf(true));
         Utils.setInforForHsThToan(hsttk);
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
       SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     }
   }

   public void select(int index)
   {
     logger.info("-----select(" + index + ")-----");
     this.updateItem = Integer.valueOf(index);
     DmThuoc thuoc = this.tpkSelected.getThuocphongkhamMathuoc();
     if (thuoc != null)
     {
       this.maThuoc = thuoc.getDmthuocMa();
       this.soLuong = this.tpkSelected.getThuocphongkhamSoluong().toString();
     }
   }

   public void delete(int index)
   {
     logger.info("-----delete(" + index + ")-----");
     this.listTpk.remove(index);
     this.count = this.listTpk.size();
     logger.info("-----deleted item: " + index);
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

   public String inPhieu()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   int index = 0;

   public void XuatReport()
   {
     this.reportTypeVP = "Thuocydungcuphongkham";
     logger.info("Vao Method XuatReport thuoc y dung cu phong kham");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "vienphi/bienlaithutien_sub2.jrxml";

       logger.info("da thay file templete " + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);

       logger.info("****Finish creating jasperReport*****");

       Map<String, Object> params = new HashMap();

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);

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
       params.put("DIACHI", diachistr);

       params.put("UBNDTINH", IConstantsRes.REPORT_DIEUTRI_UBNDTINH);
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("HOTEN", this.benhNhan.getBenhnhanHoten());

       logger.info("*****SOTIEN: " + this.bntra);
       logger.info("*****So tien format: " + Utils.formatNumberWithSeprator(this.bntra));

       params.put("SOTIEN", Utils.formatNumberWithSeprator(this.bntra));
       params.put("TIENBANGCHU", Utils.NumberToString(this.bntra.doubleValue()));
       logger.info("*****SOTIEN: " + this.bntra);

       logger.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       logger.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintVP = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintVP, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "vienphi/", "pdf", "Thuocydungcuphongkham");
       this.reportPathVP = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan -------------------- :" + this.reportPathVP);
       this.index += 1;
       logger.info("Index :" + this.index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public void displayInfo()
   {
     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();

     List<ThuocPhongKham> listThuoc = null;
     try
     {
       listThuoc = tpkDelegate.findByMaPhieu(this.maThuocPk);
     }
     catch (Exception e)
     {
       logger.error(e);
     }
     if (listThuoc != null)
     {
       if (listThuoc.size() > 0)
       {
         this.kyhieu = ((ThuocPhongKham)listThuoc.get(0)).getThuocphongkhamKyhieu();
         this.quyen = ((ThuocPhongKham)listThuoc.get(0)).getThuocphongkhamQuyen();
         this.bienlai = ((ThuocPhongKham)listThuoc.get(0)).getThuocphongkhamBienlai();
       }
       this.listTpk = ((ArrayList)listThuoc);
       for (ThuocPhongKham tpk : this.listTpk)
       {
         this.thamKham = tpk.getThuocphongkhamThamkham();
         this.tiepDon = this.thamKham.getTiepdonMa();
         this.benhNhan = this.tiepDon.getBenhnhanMa();





         logger.info("-----benh nhan: " + this.benhNhan.getBenhnhanMa());
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         if (this.benhNhan.getBenhnhanNgaysinh() != null) {
           this.ngaySinh = df.format(this.benhNhan.getBenhnhanNgaysinh());
         }
         this.ngayHienTai = df.format(tpk.getThuocphongkhamNgaygio());

         this.maThuocPk = tpk.getThuocphongkhamMaphieud();
         this.count = this.listTpk.size();
       }
       this.isUpdate = "1";
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_ID, new Object[] { "thuá»‘c phÃ²ng khÃ¡m", "mÃ£ " + this.maThuocPk });

       reset();
     }
   }

   public void reset()
   {
     logger.info("-----reset()-----");
     this.listTpk = new ArrayList();
     this.tpkSelected = new ThuocPhongKham();
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);
     this.thamKham = new ThamKham();
     SetInforUtil.setInforIfNullForThamKham(this.thamKham);
     this.tiepDon = new TiepDon();
     SetInforUtil.setInforIfNullForTiepDon(this.tiepDon);



     this.maThuocPk = "";
     this.updateItem = Integer.valueOf(-1);
     this.maThuoc = "";
     this.count = 0;
     this.isUpdate = "0";
     this.ngaySinh = "";


     this.permiengiam = 0;
     this.miengiam = new Double(0.0D);
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.khongThu = new Double(0.0D);


     this.kyhieu = "";
     this.quyen = "";
     this.bienlai = "";

     this.cls = null;
     this.thuoc = null;
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "B3231_Thuocydungcuphongkham";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   public ThuocPhongKham createThuocPhongKhamFromTk(TonKho tk)
   {
     ThuocPhongKham tpk = new ThuocPhongKham();
     tpk.setThuocphongkhamDongia(tk.getTonkhoDongia());
     tpk.setThuocphongkhamDongiaban(tk.getTonkhoDongiaban());
     tpk.setThuocphongkhamDongiabh(null);
     tpk.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
     tpk.setThuocphongkhamKhoa(tk.getDmkhoaMaso());
     tpk.setThuocphongkhamLo(tk.getTonkhoLo());
     tpk.setThuocphongkhamLoai(null);
     tpk.setThuocphongkhamMathuoc(tk.getDmthuocMaso());
     tpk.setThuocphongkhamNgaygiocn(new Date());
     tpk.setThuocphongkhamNhanviencn(null);
     tpk.setThuocphongkhamPhanloai(tk.getDmthuocMaso().getDmphanloaithuocMaso());
     tpk.setThuocphongkhamQuocgia(tk.getDmquocgiaMaso());
     tpk.setDmnctMaso(tk.getDmnctMaso());
     tpk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     tpk.setThuocphongkhamNgayhd(tk.getTonkhoNgayhandung());
     tpk.setThuocphongkhamThanghd(tk.getTonkhoThanghandung());
     tpk.setThuocphongkhamNamhd(tk.getTonkhoNamhandung());
     tpk.setThuocphongkhamNamnhap(tk.getTonkhoNamnhap());
     tpk.setThuocphongkhamMalk(tk.getTonkhoMalienket());

     return tpk;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setThamKham(ThamKham thamKham)
   {
     this.thamKham = thamKham;
   }

   public ThamKham getThamKham()
   {
     return this.thamKham;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     Date dHt = new Date();
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

     return df.format(dHt);
   }

   public void setMaThuocPk(String maThuocPk)
   {
     this.maThuocPk = maThuocPk;
   }

   public String getMaThuocPk()
   {
     return this.maThuocPk;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setGioiTinh(Integer gioiTinh)
   {
     this.gioiTinh = gioiTinh;
   }

   public Integer getGioiTinh()
   {
     return this.gioiTinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setTkMa(String tkMa)
   {
     this.tkMa = tkMa;
   }

   public String getTkMa()
   {
     return this.tkMa;
   }

   public void setListTpk(ArrayList<ThuocPhongKham> listTpk)
   {
     this.listTpk = listTpk;
   }

   public ArrayList<ThuocPhongKham> getListTpk()
   {
     return this.listTpk;
   }

   public void setTpkSelected(ThuocPhongKham tpkSelected)
   {
     this.tpkSelected = tpkSelected;
   }

   public ThuocPhongKham getTpkSelected()
   {
     return this.tpkSelected;
   }

   public void setSoLuong(String soLuong)
   {
     this.soLuong = soLuong;
   }

   public String getSoLuong()
   {
     return this.soLuong;
   }

   public void setUpdateItem(Integer updateItem)
   {
     this.updateItem = updateItem;
   }

   public Integer getUpdateItem()
   {
     return this.updateItem;
   }

   public void setMaThuoc(String maThuoc)
   {
     this.maThuoc = maThuoc;
   }

   public String getMaThuoc()
   {
     return this.maThuoc;
   }

   public void setCount(int count)
   {
     this.count = count;
   }

   public int getCount()
   {
     return this.count;
   }

   public void setUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getUpdate()
   {
     return this.isUpdate;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public String getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setIsUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
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

   public String getLoaiToaThuocThamKhamVaXuTri()
   {
     return this.loaiToaThuocThamKhamVaXuTri;
   }

   public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri)
   {
     this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public Double getKhongThu()
   {
     return this.khongThu;
   }

   public void setKhongThu(Double khongThu)
   {
     this.khongThu = khongThu;
   }

   public DtDmNhanVien getNhanVienThungan()
   {
     return this.nhanVienThungan;
   }

   public void setNhanVienThungan(DtDmNhanVien nhanVienThungan)
   {
     this.nhanVienThungan = nhanVienThungan;
   }

   public DtDmCum getCum()
   {
     return this.cum;
   }

   public void setCum(DtDmCum cum)
   {
     this.cum = cum;
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

   public Double getCls()
   {
     return this.cls;
   }

   public void setCls(Double cls)
   {
     this.cls = cls;
   }

   public Double getThuoc()
   {
     return this.thuoc;
   }

   public void setThuoc(Double thuoc)
   {
     this.thuoc = thuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThuocYDungCuPhongKhamAction

 * JD-Core Version:    0.7.0.1

 */