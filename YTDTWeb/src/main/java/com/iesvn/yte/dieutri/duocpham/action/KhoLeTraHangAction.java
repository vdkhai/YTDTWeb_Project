 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.TonKhoUtil;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4162_Kholetrahang")
 @Synchronized(timeout=6000000L)
 public class KhoLeTraHangAction
   implements Serializable
 {
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(KhoLeTraHangAction.class);
   @DataModel
   private ArrayList<CtTraKhoExt> listCtKhoLeTraEx;
   @DataModelSelection
   private CtTraKhoExt selected;
   private PhieuTraKho phieuTra;
   private String maPhieu;
   private String ngayXuat;
   private String dmtMa;
   private Double tonkho;
   private Double xuat;
   private String tonkhoMa;
   private Integer updateItem;
   private Integer count;
   private String ngayHienTai;
   private String nguonMa;
   private String kpMa;
   private String malk;
   private Double tongTien;
   private String isFound;
   private String isUpdate;
   String dmKhoXuat;
   String dmKhoNhan;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC;

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String khoNhan, String khoXuat)
   {
     log.info("***** init KhoLeTraHangAction() *****");
     reset();
     this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     this.dmKhoXuat = khoXuat;
     this.dmKhoNhan = khoNhan;
     return "QuanLyKhoChinh_NhapKhoChinh_KhoLeTraHang";
   }

   @Factory("listCtKhoLeTraEx")
   public void createTable()
   {
     log.info("-----createTable()-----");
     this.listCtKhoLeTraEx = new ArrayList();
   }

   public void deleteCt()
   {
     log.info("-----deleteCt()-----");

     this.listCtKhoLeTraEx.remove(this.selected);
     this.count = Integer.valueOf(this.listCtKhoLeTraEx.size());
     tinhTien();
   }

   public String getDmKhoNhan()
   {
     return this.dmKhoNhan;
   }

   public void setDmKhoNhan(String dmKhoNhan)
   {
     this.dmKhoNhan = dmKhoNhan;
   }

   public void selectCt(Integer index)
   {
     log.info("-----selectCt()-----");
     log.info(String.format("-----selected: %s", new Object[] { this.selected.getCtTraKho().getCttrakhoThutu() }));

     TonKho tk = this.selected.getTonKho();
     CtTraKho ctx = this.selected.getCtTraKho();
     this.updateItem = index;
     this.tonkhoMa = tk.getTonkhoMa().toString();
     log.info("-----ton kho: " + this.tonkho);


     this.tonkho = tk.getTonkhoTon();
     this.dmtMa = ctx.getDmthuocMaso().getDmthuocMa();
     this.xuat = ctx.getCttrakhoSoluong();
   }

   public void tiepTucNhap()
   {
     log.info("-----tiepTucNhap()-----");
     log.info(String.format("-----index: %s", new Object[] { this.updateItem }));

     log.info("tonkhoMa:" + this.tonkhoMa);
     log.info("xuat:" + this.xuat);
     log.info("dmtMa:" + this.dmtMa);
     log.info("updateItem:" + this.updateItem);
     log.info("tonkho:" + this.tonkho);
     if ((this.xuat == null) || (this.xuat.equals("")) || (this.tonkho == null) || (this.tonkho.equals(""))) {
       return;
     }
     if (("".equals(this.tonkhoMa)) && (this.tonkhoMa == null))
     {
       log.info("-----tonkho ma is null.");
     }
     else
     {
       log.info(String.format("-----tonkho ma: %s", new Object[] { this.tonkhoMa }));
       TonKho tk = null;
       try
       {
         TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();

         tk = tkDelegate.find(Integer.valueOf(this.tonkhoMa));
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
       CtTraKho ctx = new CtTraKho();

       Double slXuat = new Double("0");
       for (int i = 0; i < this.listCtKhoLeTraEx.size(); i++)
       {
         CtTraKho ctxk = ((CtTraKhoExt)this.listCtKhoLeTraEx.get(i)).getCtTraKho();
         if (this.malk.equals(ctxk.getCttrakhoMalk()))
         {
           log.info("-----malk " + this.malk);
           slXuat = Double.valueOf(slXuat.doubleValue() + ctxk.getCttrakhoSoluong().doubleValue());
           this.updateItem = Integer.valueOf(i);
         }
       }
       slXuat = Double.valueOf(slXuat.doubleValue() + Double.valueOf(this.xuat.doubleValue()).doubleValue());
       ctx.setCttrakhoSoluong(slXuat);
       CtTraKhoExt ctxEx = createCtXuatKho(ctx, tk);
       log.info("-----xuat: " + slXuat);
       if (this.updateItem.equals(Integer.valueOf(-1)))
       {
         log.info("-----them moi ct");
         this.listCtKhoLeTraEx.add(ctxEx);
       }
       else
       {
         log.info("-----Cap nhat ct-----");
         if (tk != null) {
           this.listCtKhoLeTraEx.set(this.updateItem.intValue(), ctxEx);
         } else {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_TK_NULL, new Object[] { this.dmtMa });
         }
         log.info(String.format("-----update ct: %s", new Object[] { ctx.getCttrakhoThutu() }));
       }
       this.count = Integer.valueOf(this.listCtKhoLeTraEx.size());
       log.info(String.format("-----listCtXuatKho: %s", new Object[] { Integer.valueOf(this.listCtKhoLeTraEx.size()) }));
       this.tonkhoMa = "";
       this.dmtMa = "";
       this.tonkho = new Double(0.0D);
       this.xuat = new Double(0.0D);
       this.updateItem = Integer.valueOf(-1);
     }
     tinhTien();
   }

   public void end()
   {
     log.info("-----end()-----");
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
     if (this.listCtKhoLeTraEx.size() > 0)
     {
       if (!this.ngayXuat.equals(""))
       {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           Date dtNgayXuat = df.parse(this.ngayXuat);
           Calendar cal = Calendar.getInstance();
           cal.setTime(dtNgayXuat);
           this.phieuTra.setPhieutrakhoNgaygiocn(cal.getTime());
         }
         catch (ParseException e)
         {
           log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
         }
       }
       try
       {
         for (int i = 0; i < this.listCtKhoLeTraEx.size(); i++)
         {
           CtTraKho ct = ((CtTraKhoExt)this.listCtKhoLeTraEx.get(i)).getCtTraKho();


           boolean tinhtrangKiemKe = kiemkeDel.dangKiemKe(ct.getCttrakhoMalk(), this.phieuTra.getDmkhoaNhan().getDmkhoaMaso());
           if (tinhtrangKiemKe == true)
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DANGKIEMKE, new Object[] { ct.getCttrakhoMalk() });
             return;
           }
           Double tonkhoHienTai = tkDelegate.findByTonkhoKhoaMalienketHienTai(ct.getCttrakhoMalk(), this.phieuTra.getDmkhoaTra().getDmkhoaMaso());
           if (tonkhoHienTai.doubleValue() < ct.getCttrakhoSoluong().doubleValue())
           {
             FacesMessages.instance().add(IConstantsRes.PHIEUTRAKHO_SLKHONGDUTRA, new Object[0]);
             return;
           }
         }
         double tt = Double.valueOf("0").doubleValue();
         ArrayList<CtTraKho> listCtx = new ArrayList();
         ArrayList<TonKho> listTkTra = new ArrayList();
         ArrayList<TonKho> listTkNhan = new ArrayList();
         for (int i = 0; i < this.listCtKhoLeTraEx.size(); i++)
         {
           CtTraKho ct = ((CtTraKhoExt)this.listCtKhoLeTraEx.get(i)).getCtTraKho();
           tt += ct.getCttrakhoSoluong().doubleValue() * ct.getCttrakhoDongia().doubleValue();
           ct.setCttrakhoThutu(Short.valueOf("" + (i + 1)));
           listCtx.add(ct);
           TonKho tkTra = createTonKhoTra((CtTraKhoExt)this.listCtKhoLeTraEx.get(i));

           listTkTra.add(tkTra);

           TonKho tkNhan = createTonKhoNhan((CtTraKhoExt)this.listCtKhoLeTraEx.get(i));

           listTkNhan.add(tkNhan);
         }
         this.phieuTra.setPhieutrakhoThanhtien(Double.valueOf(tt));
         this.phieuTra.setPhieutrakhoNgaygiocn(new Date());
         this.phieuTra.setPhieutrakhoNgaygiophat(new Date());

         PhieuTraKhoDelegate pxDelegate = PhieuTraKhoDelegate.getInstance();
         clearInfor();
         this.maPhieu = pxDelegate.thucHienTraKho(this.phieuTra, listCtx, listTkNhan, listTkTra);
         if (this.maPhieu != "")
         {
           resetInfo();
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { this.maPhieu });

           this.isUpdate = "0";
           this.isFound = "true";
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
         log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
       }
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("-----destroy()-----");
   }

   public void displayPhieuXuatKho()
   {
     log.info("-----displayPhieuXuatKho()-----");
     if (!this.maPhieu.equals("")) {
       try
       {
         DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
         PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
         CtTraKhoDelegate ctxWS = CtTraKhoDelegate.getInstance();
         DmKhoa dmKhoaNhan = new DmKhoa();
         dmKhoaNhan = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
         this.phieuTra = pxkWS.findByPhieutrakhoByKhoNhan(this.maPhieu, dmKhoaNhan.getDmkhoaMaso());
         if (this.phieuTra != null)
         {
           this.maPhieu = this.phieuTra.getPhieutrakhoMa();
           resetInfo();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayXuat = df.format(this.phieuTra.getPhieutrakhoNgay());
           for (CtTraKho ct : ctxWS.findByphieutrakhoMa(this.phieuTra.getPhieutrakhoMa()))
           {
             CtTraKhoExt ctxEx = new CtTraKhoExt();
             ctxEx.setCtTraKho(ct);
             this.listCtKhoLeTraEx.add(ctxEx);
           }
           this.count = Integer.valueOf(this.listCtKhoLeTraEx.size());
           this.isFound = "true";
           if (this.phieuTra.getPhieutrakhoNgaygiophat() == null) {
             this.isUpdate = "1";
           } else {
             this.isUpdate = "0";
           }
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
           reset();
         }
         tinhTien();
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_NULL, new Object[] { this.maPhieu });
         reset();
         log.error(String.format("-----Error: %s", new Object[] { e }));
       }
     }
   }

   private CtTraKhoExt createCtXuatKho(CtTraKho ctx, TonKho tk)
   {
     log.info(String.format("-----createCtXuatKho()-----", new Object[0]));

     TonKho tkTra = null;
     try
     {
       tkTra = (TonKho)BeanUtils.cloneBean(tk);
       tkTra.setTonkhoNgaygiocn(new Date());
       tkTra.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkTra != null)
     {
       tkTra.setTonkhoXuat(ctx.getCttrakhoSoluong());
       tkTra.setTonkhoTra(null);
       tkTra.setTonkhoNhap(null);
       tkTra.setTonkhoMa(tk.getTonkhoMa());
     }
     TonKho tkNhan = null;
     try
     {
       tkNhan = (TonKho)BeanUtils.cloneBean(tk);
       tkNhan.setTonkhoNgaygiocn(new Date());
       tkNhan.setTonkhoMa(null);
     }
     catch (IllegalAccessException e)
     {
       e.printStackTrace();
     }
     catch (InstantiationException e)
     {
       e.printStackTrace();
     }
     catch (InvocationTargetException e)
     {
       e.printStackTrace();
     }
     catch (NoSuchMethodException e)
     {
       e.printStackTrace();
     }
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(ctx.getCttrakhoSoluong());
       tkNhan.setTonkhoTra(null);
       tkNhan.setTonkhoXuat(null);
     }
     ctx.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     ctx.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     ctx.setDmthuocMaso(tk.getDmthuocMaso());
     ctx.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     ctx.setDmnctMaso(tk.getDmnctMaso());
     ctx.setCttrakhoDongia(tk.getTonkhoDongia());
     ctx.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
     ctx.setCttrakhoLo(tk.getTonkhoLo());
     ctx.setCttrakhoMalk(tk.getTonkhoMalienket());
     ctx.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
     ctx.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
     ctx.setCttrakhoNgaygiocn(new Date());
     ctx.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
     ctx.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
     ctx.setPhieutrakhoMa(this.phieuTra);
     ctx.setCttrakhoNgaygiocn(new Date());
     ctx.setCtxuatkhoSodangky(tk.getTonkhoSodangky());

     CtTraKhoExt ctxEx = new CtTraKhoExt();
     ctxEx.setCtTraKho(ctx);
     ctxEx.setTonKhoTra(tkTra);
     ctxEx.setTonKhoNhan(tkNhan);
     return ctxEx;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public KhoLeTraHangAction()
   {
     this.updateItem = Integer.valueOf(-1);









     this.dmKhoXuat = "";
     this.dmKhoNhan = "";




     this.isReport = "false";










































































































































































































































































































































































     this.index = 0;




     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "TraHangKhoChinh";
     log.info("Vao Method XuatReport TraHangKhoChinh");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.maPhieu.equals("")) {
       try
       {
         log.info("bat dau method XuatReport ");
         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieutrakho_01.jrxml";
         log.info("da thay file templete 5" + pathTemplate);

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
         log.info("da thay file template ");
         Map<String, Object> params = new HashMap();

         PhieuTraKhoDelegate pxkWS = PhieuTraKhoDelegate.getInstance();
         PhieuTraKho px = pxkWS.find(this.maPhieu);

         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
         params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);
         log.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         Calendar cal = Calendar.getInstance();
         cal.setTime(px.getPhieutrakhoNgay());
         if (cal != null)
         {
           log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
           params.put("ngayHt", "" + cal.get(5));
           params.put("thangHt", "" + (cal.get(2) + 1));
           params.put("namHt", "" + cal.get(1));
         }
         else
         {
           log.info("-----ngay lap is null");
           params.put("ngayHt", "");
           params.put("thangHt", "");
           params.put("namHt", "");
         }
         SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
         String ngayGioHt = df.format(Calendar.getInstance().getTime());
         log.info(String.format("-----ngay gio hien tai: %s", new Object[] { ngayGioHt }));
         params.put("gioHt", ngayGioHt);

         params.put("pxMa", px.getPhieutrakhoMa());
         if (px.getDmkhoaNhan() != null) {
           params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (px.getDmkhoaTra() != null) {
           params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format("-----khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", px.getPhieutrakhoThanhtien());
         log.info(String.format("-----tongTien: %s", new Object[] { params.get("tongTien") }));

         params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());

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
         this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "xuathangdenkhokhac");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathKC);
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
     }
     log.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4444_Phieutrahangbhytchokhochinh";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void reset()
   {
     log.info("-----reset()-----");
     this.isFound = "false";
     this.maPhieu = "";
     this.updateItem = Integer.valueOf(-1);
     this.tonkhoMa = "";
     this.dmtMa = "";
     this.tonkho = new Double(0.0D);
     this.xuat = new Double(0.0D);
     this.nguonMa = "";
     this.kpMa = "";
     this.count = Integer.valueOf(0);
     this.phieuTra = new PhieuTraKho();
     this.listCtKhoLeTraEx = new ArrayList();
     resetInfo();
     this.ngayXuat = Utils.getCurrentDate();
     this.ngayHienTai = Utils.getCurrentDate();
     this.isUpdate = "0";
     log.info("-----identity: " + this.identity.getUsername());
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
     if (nv != null) {
       this.phieuTra.setDtdmnhanvienCn(nv);
     }
     this.tongTien = new Double("0");
   }

   private void tinhTien()
   {
     this.tongTien = new Double("0");
     for (CtTraKhoExt ctExt : this.listCtKhoLeTraEx)
     {
       Double sl = ctExt.getCtTraKho().getCttrakhoSoluong();
       if (sl == null) {
         sl = new Double("0");
       }
       Double dg = ctExt.getCtTraKho().getCttrakhoDongia();
       if (dg == null) {
         dg = new Double("0");
       }
       this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
     }
     log.info("-----tong tien: " + this.tongTien);
   }

   private void resetInfo()
   {
     log.info("-----resetInfo()-----");
     if (this.phieuTra.getDmkhoaNhan() == null) {
       this.phieuTra.setDmkhoaNhan(new DmKhoa());
     }
     if (this.phieuTra.getDmkhoaTra() == null) {
       this.phieuTra.setDmkhoaTra(new DmKhoa());
     }
     if (this.phieuTra.getDmdoituongMaso() == null) {
       this.phieuTra.setDmdoituongMaso(new DmDoiTuong());
     }
     if (this.phieuTra.getDtdmnhanvienBacsi() == null) {
       this.phieuTra.setDtdmnhanvienBacsi(new DtDmNhanVien());
     }
     if (this.phieuTra.getDtdmnhanvienCn() == null) {
       this.phieuTra.setDtdmnhanvienCn(new DtDmNhanVien());
     }
     if (this.phieuTra.getDtdmnhanvienPhat() == null) {
       this.phieuTra.setDtdmnhanvienPhat(new DtDmNhanVien());
     }
   }

   private void clearInfor()
   {
     log.info("***** begin clearInfor() *****");
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmkhoaNhan().getDmkhoaMaso()))) {
       this.phieuTra.setDmkhoaNhan(new DmKhoa());
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmkhoaTra().getDmkhoaMaso()))) {
       this.phieuTra.setDmkhoaTra(new DmKhoa());
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDmdoituongMaso().getDmdoituongMaso()))) {
       this.phieuTra.setDmdoituongMaso(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienBacsi().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienBacsi(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienCn().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienCn(null);
     }
     if ("".equals(Utils.reFactorString(this.phieuTra.getDtdmnhanvienPhat().getDtdmnhanvienMaso()))) {
       this.phieuTra.setDtdmnhanvienPhat(null);
     }
     log.info("***** end clearInfor() *****");
   }

   public void setPhieuTra(PhieuTraKho phieuXuat)
   {
     this.phieuTra = phieuXuat;
   }

   public PhieuTraKho getPhieuTra()
   {
     return this.phieuTra;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setDmtMa(String dmtMa)
   {
     this.dmtMa = dmtMa;
   }

   public String getDmtMa()
   {
     return this.dmtMa;
   }

   public void setTonkho(Double tonkho)
   {
     this.tonkho = tonkho;
   }

   public Double getTonkho()
   {
     return this.tonkho;
   }

   public void setXuat(Double xuat)
   {
     this.xuat = xuat;
   }

   public Double getXuat()
   {
     return this.xuat;
   }

   public void setTonkhoMa(String tonkhoMa)
   {
     this.tonkhoMa = tonkhoMa;
   }

   public String getTonkhoMa()
   {
     return this.tonkhoMa;
   }

   public void setUpdateItem(Integer updateItem)
   {
     this.updateItem = updateItem;
   }

   public Integer getUpdateItem()
   {
     return this.updateItem;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public static Logger getLogger()
   {
     return log;
   }

   public static void setLogger(Logger logger)
   {
     log = logger;
   }

   public ArrayList<CtTraKhoExt> getListCtXuatKhoEx()
   {
     return this.listCtKhoLeTraEx;
   }

   public void setListCtXuatKhoEx(ArrayList<CtTraKhoExt> listCtTraKhoEx)
   {
     this.listCtKhoLeTraEx = listCtTraKhoEx;
   }

   public CtTraKhoExt getSelected()
   {
     return this.selected;
   }

   public void setSelected(CtTraKhoExt selected)
   {
     this.selected = selected;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public void setIsUpdate(String isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setKpMa(String kpMa)
   {
     this.kpMa = kpMa;
   }

   public String getKpMa()
   {
     return this.kpMa;
   }

   public void setNguonMa(String nguonMa)
   {
     this.nguonMa = nguonMa;
   }

   public String getNguonMa()
   {
     return this.nguonMa;
   }

   public void setTenChuongTrinh(String tenChuongTrinh)
   {
     this.tenChuongTrinh = tenChuongTrinh;
   }

   public String getTenChuongTrinh()
   {
     return this.tenChuongTrinh;
   }

   public String getMalk()
   {
     return this.malk;
   }

   public void setMalk(String malk)
   {
     this.malk = malk;
   }

   public Double getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(Double tongTien)
   {
     this.tongTien = tongTien;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public String getIsFound()
   {
     return this.isFound;
   }

   public void setIsFound(String notFound)
   {
     this.isFound = notFound;
   }

   public TonKho createTonKhoTra(CtTraKhoExt ct)
   {
     TonKho tk = new TonKho();
     try
     {
       tk = TonKhoUtil.getTonKhoHienTai(ct.getCtTraKho().getCttrakhoMalk(), this.phieuTra.getDmkhoaTra().getDmkhoaMaso());
       tk.setDtdmnhanvienCn(this.phieuTra.getDtdmnhanvienCn());
       tk.setTonkhoNhap(null);
       tk.setTonkhoXuat(ct.getCtTraKho().getCttrakhoSoluong());
       tk.setTonkhoTra(null);
       tk.setTonkhoNgaygiocn(new Date());
       tk.setTonkhoMa(null);
     }
     catch (Exception e1)
     {
       e1.printStackTrace();
     }
     return tk;
   }

   public TonKho createTonKhoNhan(CtTraKhoExt ct)
   {
     TonKho tk = new TonKho();
     try
     {
       tk = TonKhoUtil.getTonKhoHienTai(ct.getCtTraKho().getCttrakhoMalk(), this.phieuTra.getDmkhoaNhan().getDmkhoaMaso());
       if (tk != null)
       {
         tk.setDtdmkhoMaso(null);
         tk.setDtdmnhanvienCn(this.phieuTra.getDtdmnhanvienCn());
         tk.setTonkhoHienthi(Boolean.valueOf(true));
         tk.setTonkhoNhap(null);
         tk.setTonkhoXuat(null);
         tk.setTonkhoTra(ct.getCtTraKho().getCttrakhoSoluong());
         Date dNgayCn = new Date();
         tk.setTonkhoMa(null);
         tk.setTonkhoNgaygiocn(dNgayCn);
       }
     }
     catch (Exception e1)
     {
       e1.printStackTrace();
     }
     return tk;
   }

   @End
   public void endConversation() {}

   public class CtTraKhoExt
     implements Serializable
   {
     private static final long serialVersionUID = 0L;
     private CtTraKho ctTraKho;
     private TonKho tonKho;
     private TonKho tonKhoTra;
     private TonKho tonKhoNhan;
     private Double thanhTien;

     public Double getThanhTien()
     {
       return this.thanhTien;
     }

     public void setThanhTien(Double thanhTien)
     {
       this.thanhTien = thanhTien;
     }

     public CtTraKhoExt()
     {
       this.ctTraKho = new CtTraKho();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhTien = new Double(0.0D);
     }

     public CtTraKho getCtTraKho()
     {
       if (this.ctTraKho == null) {
         this.ctTraKho = new CtTraKho();
       }
       return this.ctTraKho;
     }

     public void setCtTraKho(CtTraKho ctTraKho)
     {
       this.ctTraKho = ctTraKho;
     }

     public TonKho getTonKho()
     {
       if (this.tonKho == null) {
         this.tonKho = new TonKho();
       }
       return this.tonKho;
     }

     public void setTonKho(TonKho tonKho)
     {
       this.tonKho = tonKho;
     }

     public TonKho getTonKhoTra()
     {
       if (this.tonKhoTra == null) {
         this.tonKhoTra = new TonKho();
       }
       return this.tonKhoTra;
     }

     public void setTonKhoTra(TonKho tonKhoTra)
     {
       this.tonKhoTra = tonKhoTra;
     }

     public TonKho getTonKhoNhan()
     {
       if (this.tonKhoNhan == null) {
         this.tonKhoNhan = new TonKho();
       }
       return this.tonKhoNhan;
     }

     public void setTonKhoNhan(TonKho tonKhoNhan)
     {
       this.tonKhoNhan = tonKhoNhan;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.KhoLeTraHangAction

 * JD-Core Version:    0.7.0.1

 */