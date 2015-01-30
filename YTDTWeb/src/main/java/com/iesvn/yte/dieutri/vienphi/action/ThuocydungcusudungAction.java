 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocDongYNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HsttThreadUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import javax.faces.component.html.HtmlSelectOneRadio;
 import javax.faces.event.ValueChangeEvent;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
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
 @Name("B3111_Thuocydungcusudung")
 @Synchronized(timeout=6000000L)
 public class ThuocydungcusudungAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(ThuocydungcusudungAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;
   private int index = 0;
   @DataModel
   private List<TonKhoEx> listTkEx;
   private List<TonKhoEx> listTkEx_Tutruc;
   private List<TonKhoEx> listTkEx_Pdt;
   private List<TonKhoEx> listTkEx_KeToa;
   @DataModelSelection("listTkEx")
   private TonKhoEx tkExSelected;
   private ArrayList<ThuocNoiTru> listTntDel;
   private ArrayList<ThuocNoiTru> listTntDel_Tutruc;
   private ArrayList<ThuocNoiTru> listTntDel_Pdt;
   private ArrayList<ThuocNoiTru> listTntDel_KeToa;
   private String sovaovienOld;
   private String ngaySoLieuOld;
   private String khoaMaOld;
   private TonKhoEx tkEx;
   private Hsba hsba;
   private HsbaKhoa hsbaKhoa;
   private HsThtoan hsthtoan;
   private HsbaBhyt hsbaBhyt;
   private DmKhoa khoa;
   private String lanVao;
   private String ngayVv;
   private String ngayHt = Utils.getCurrentDate();
   private int tkMa;
   private Boolean isUpdate;
   private String maPhieu;
   private Integer count;
   private String tutrucPdt;
   private String malk;
   private Double tongTien;
   private String dmdonvitinhTen;
   private String checkKiemKe = "false";
   private String handung = "";
   private String hamluong = "";
   private Double tonTong = new Double(0.0D);
   private List<TonKho> listTonKhoHienTai = new ArrayList();
   private Integer dmBaithuocMaso = Integer.valueOf(0);
   private String dmBaithuocMa;
   private int sobaithuoc = 1;
   private DtDmNhanVien nhanVienCN = null;
   private HashMap<Integer, List> hsmListThuocDYNoiTru = new HashMap();
   private int ctThuocNoiTruSelectedOld = -1;
   @In(required=false)
   @Out(required=false)
   private String checkThanhtoan = "block";
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   private Boolean isYeucau = Boolean.valueOf(false);
   private Boolean isKhongthu = Boolean.valueOf(false);
   private Boolean isAllowedUpdateSoLuong = Boolean.valueOf(true);
   private Integer sochia;
   private Integer sobichia;
   private String dmdoituongMa;
   @In(required=false)
   @Out(required=false)
   private String chuyenManHinhThuocCLS_MaKhoa;
   @In(required=false)
   @Out(required=false)
   private String chuyenManHinhThuocCLS_SoBenhAn;
   private String focusDochuyenTuManHinhThuocCLS;

   public String getFocusDochuyenTuManHinhThuocCLS()
   {
     return this.focusDochuyenTuManHinhThuocCLS;
   }

   public void setFocusDochuyenTuManHinhThuocCLS(String focusDochuyenTuManHinhThuocCLS)
   {
     this.focusDochuyenTuManHinhThuocCLS = focusDochuyenTuManHinhThuocCLS;
   }

   private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
   private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap();
   private List<SelectItem> listDmKhoaNTs = new ArrayList();
   private List<DmKhoa> listDmKhoaNTAll = new ArrayList();
   private String maChiDan;
   private String tenChiDan;
   private DmThuocDelegate dmThuocDelegate;

   public String getMaChiDan()
   {
     return this.maChiDan;
   }

   public void setMaChiDan(String maChiDan)
   {
     this.maChiDan = maChiDan;
   }

   public String getTenChiDan()
   {
     return this.tenChiDan;
   }

   public void setTenChiDan(String tenChiDan)
   {
     this.tenChiDan = tenChiDan;
   }

   public List<SelectItem> getListDmKhoaNTs()
   {
     return this.listDmKhoaNTs;
   }

   public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs)
   {
     this.listDmKhoaNTs = listDmKhoaNTs;
   }

   public void onblurMaKhoaAction()
   {
     if ((this.khoa != null) && (this.khoa.getDmkhoaMa() != null))
     {
       String maKhoa = this.khoa.getDmkhoaMa();
       if (this.hmDmKhoaNTAll != null)
       {
         DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNTAll.get(maKhoa.toUpperCase());
         if (dmKhoa != null)
         {
           this.khoa = dmKhoa;
         }
         else
         {
           this.khoa = new DmKhoa();
           return;
         }
       }
       if ((this.hsba.getHsbaSovaovien() != null) && (this.ngayHt != null)) {
         loadHsba();
       }
       refreshDmThuoc(this.tutrucPdt);
     }
   }

   public void onblurTenKhoaAction()
   {
     if ((this.khoa != null) && (this.khoa.getDmkhoaTen() != null))
     {
       String tenKhoa = this.khoa.getDmkhoaTen();
       Boolean hasTenKhoa = Boolean.valueOf(false);
       Set set = this.hmDmKhoaNTAll.entrySet();
       Iterator i = set.iterator();
       DmKhoa dmKhoa_Finded = new DmKhoa();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmKhoa dmKhoa = (DmKhoa)me.getValue();
         if ((dmKhoa.getDmkhoaTen() == tenKhoa) || (dmKhoa.getDmkhoaTen().equals(tenKhoa)))
         {
           hasTenKhoa = Boolean.valueOf(true);
           dmKhoa_Finded = dmKhoa;
           break;
         }
       }
       if (hasTenKhoa.booleanValue())
       {
         this.khoa.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
         this.khoa.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa().toUpperCase());
         this.khoa.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
       }
       else
       {
         this.khoa = new DmKhoa();
         return;
       }
       if ((this.hsba.getHsbaSovaovien() != null) && (this.ngayHt != null)) {
         loadHsba();
       }
       refreshDmThuoc(this.tutrucPdt);
     }
   }

   public void refreshDmKhoaNT()
   {
     this.dmKhoaDel = DmKhoaDelegate.getInstance();
     this.listDmKhoaNTAll = this.dmKhoaDel.getKhoaNT();
     this.hmDmKhoaNTAll.clear();
     for (DmKhoa o : this.listDmKhoaNTAll) {
       this.hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
     }
     for (DmKhoa each : this.listDmKhoaNTAll) {
       this.listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
     }
   }

   @Begin(join=true)
   public String init()
   {
     reset();
     this.tenChuongTrinh = MyMenuYTDTAction.vienPhiTaiKhoa;
     if ((this.chuyenManHinhThuocCLS_MaKhoa != null) && (!this.chuyenManHinhThuocCLS_MaKhoa.equals("")) && (this.chuyenManHinhThuocCLS_SoBenhAn != null) && (!this.chuyenManHinhThuocCLS_SoBenhAn.equals("")))
     {
       DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
       this.khoa = ((DmKhoa)dieutriUtilDel.findByMa(this.chuyenManHinhThuocCLS_MaKhoa, "DmKhoa", "dmkhoaMa"));
       this.hsba.setHsbaSovaovien(this.chuyenManHinhThuocCLS_SoBenhAn);

       loadHsba();

       this.focusDochuyenTuManHinhThuocCLS = "true";
     }
     return "VienPhiTaiKhoa_SoLieuBNSuDung_ThuocYDungCuSuDung";
   }

   private List<SelectItem> listDmThuocs = new ArrayList();
   private List<DmThuoc> listDmThuocAll;
   private HashMap<String, DmThuoc> hmDmThuocAll = new HashMap();
   private String searchType = "1";

   @End
   public void endFunct() {}

   public String getSearchType()
   {
     return this.searchType;
   }

   public void setSearchType(String searchType)
   {
     this.searchType = searchType;
   }

   public List<SelectItem> getListDmThuocs()
   {
     return this.listDmThuocs;
   }

   public void setListDmThuocs(List<SelectItem> listDmThuocs)
   {
     this.listDmThuocs = listDmThuocs;
   }

   public void onblurMaThuocAction()
   {
     this.maChiDan = "";
     this.tenChiDan = "";
     if ((this.tkEx != null) && (this.tkEx.getTnt(Boolean.valueOf(true)) != null) && (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true) != null))
     {
       String maThuoc = this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).getDmthuocMa();
       if (this.hmDmThuocAll != null)
       {
         DmThuoc dmThuoc = (DmThuoc)this.hmDmThuocAll.get(maThuoc.toUpperCase());
         if (dmThuoc != null)
         {
           DmThuoc dmThuoc1 = this.dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(dmThuoc1);
           this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc.getDmthuocTen());

           setHamluong(dmThuoc1.getDmthuocHamluong());
           setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(dmThuoc1);
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMien(dmThuoc1.getDmthuocMien());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNDM(Boolean.valueOf(!dmThuoc1.getDmthuocIndanhmuc().booleanValue()));
           if ((dmThuoc1.getDmthuocYcu() != null) && (dmThuoc1.getDmthuocYcu().booleanValue())) {
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruLoai("Y");
           } else {
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruLoai("T");
           }
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruKho(getDmKho(dmThuoc.getDmthuocMaso(), this.tutrucPdt));
         }
         else
         {
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(new DmThuoc());
           setHamluong("");
           setDmdonvitinhTen("");
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruKho(new DmKhoa());
         }
       }
       setIsKhongthu(Boolean.valueOf(false));
       setIsYeucau(Boolean.valueOf(false));
       if ((this.tutrucPdt.equals("2")) && (this.hsba != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa() != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")))
       {
         setTonTong(new Double(0.0D));
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(null);
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(null);
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruSoluong(null);
         return;
       }
       Integer masoThuoc = this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).getDmthuocMaso();
       if (masoThuoc != null)
       {
         getTonKhoHienTai(masoThuoc, this.tutrucPdt);
         if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
         {
           TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
           if (tk.getTonkhoMaphieukiem() != null)
           {
             setTonTong(new Double(0.0D));
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(null);
             setCheckKiemKe("true");
           }
           else
           {
             Double ton = new Double(0.0D);
             for (TonKho tk1 : this.listTonKhoHienTai) {
               ton = Double.valueOf(ton.doubleValue() + tk1.getTonkhoTon().doubleValue());
             }
             setTonTong(ton);
           }
         }
       }
     }
     else
     {
       setTonTong(new Double(0.0D));
     }
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(null);
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(null);
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruSoluong(null);
   }

   public void onblurTenThuocAction()
   {
     this.maChiDan = "";
     this.tenChiDan = "";
     if ((this.tkEx != null) && (this.tkEx.getTnt(Boolean.valueOf(true)) != null) && (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true) != null))
     {
       String tenThuoc = this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).getDmthuocTen();
       if (tenThuoc.length() > 6)
       {
         String maThuoc = tenThuoc.substring(tenThuoc.length() - 6);

         DmThuoc dmThuoc = this.dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());
         if (dmThuoc != null)
         {
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(dmThuoc);
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMien(dmThuoc.getDmthuocMien());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNDM(Boolean.valueOf(!dmThuoc.getDmthuocIndanhmuc().booleanValue()));
           this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc.getDmthuocTen());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruLoai(dmThuoc.getDmthuocYcu() == null ? "" : dmThuoc.getDmthuocYcu().toString());

           setHamluong(dmThuoc.getDmthuocHamluong());
           setDmdonvitinhTen(dmThuoc.getDmdonvitinhMaso().getDmdonvitinhTen());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruKho(getDmKho(dmThuoc.getDmthuocMaso(), this.tutrucPdt));
         }
         else
         {
           DmThuoc dmThuoc1 = this.dmThuocDelegate.findByTenThuoc(tenThuoc);
           if (dmThuoc1 != null)
           {
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(dmThuoc1);
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMien(dmThuoc1.getDmthuocMien());
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNDM(Boolean.valueOf(!dmThuoc1.getDmthuocIndanhmuc().booleanValue()));
             this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).setDmthuocTen(dmThuoc1.getDmthuocTen());
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruLoai(dmThuoc1.getDmthuocYcu() == null ? "" : dmThuoc1.getDmthuocYcu().toString());

             setHamluong(dmThuoc1.getDmthuocHamluong());
             setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruKho(getDmKho(dmThuoc1.getDmthuocMaso(), this.tutrucPdt));
           }
           else
           {
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(new DmThuoc());

             setHamluong("");
             setDmdonvitinhTen("");
             setTonTong(new Double(0.0D));
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruKho(new DmKhoa());
           }
         }
       }
       else
       {
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(new DmThuoc());
         setHamluong("");
         setDmdonvitinhTen("");
         setTonTong(new Double(0.0D));
       }
       setIsKhongthu(Boolean.valueOf(false));
       setIsYeucau(Boolean.valueOf(false));
       if ((this.tutrucPdt.equals("2")) && (this.hsba.getDoituongMa(true).getDmdoituongMa() != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")))
       {
         setTonTong(new Double(0.0D));
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(null);
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(null);
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruSoluong(null);
         return;
       }
       Integer masoThuoc = this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).getDmthuocMaso();
       if (masoThuoc != null)
       {
         getTonKhoHienTai(masoThuoc, this.tutrucPdt);
         if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
         {
           TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
           if (tk.getTonkhoMaphieukiem() != null)
           {
             setTonTong(new Double(0.0D));
             this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruMathuoc(null);
             setCheckKiemKe("true");
           }
           else
           {
             Double ton = new Double(0.0D);
             for (TonKho tk1 : this.listTonKhoHienTai) {
               ton = Double.valueOf(ton.doubleValue() + tk1.getTonkhoTon().doubleValue());
             }
             setTonTong(ton);
           }
         }
       }
       else
       {
         setTonTong(new Double(0.0D));
       }
     }
     else
     {
       setTonTong(new Double(0.0D));
     }
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(null);
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(null);
     this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruSoluong(null);
   }

   public void refreshDmThuoc(String isTutruc)
   {
     if (isTutruc.equals("0"))
     {
       DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
       String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
       if (dinhhuongThuocNT.indexOf(',') > 0)
       {
         String khoaMa1 = dinhhuongThuocNT.substring(0, dinhhuongThuocNT.indexOf(','));
         String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',') + 1, dinhhuongThuocNT.length());
         DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
         DmKhoa khoa2 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
         this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(), khoa2.getDmkhoaMaso());
       }
       else
       {
         DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
         if (khoa1.getDmkhoaMaso() != null) {
           this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
         }
       }
     }
     else if (isTutruc.equals("1"))
     {
       if (this.khoa.getDmkhoaMaso() != null) {
         this.listDmThuocAll = this.dmThuocDelegate.findAll(this.khoa.getDmkhoaMaso());
       }
     }
     else if (isTutruc.equals("2"))
     {
       if ((this.hsba != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa() != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")))
       {
         this.listDmThuocAll = this.dmThuocDelegate.findAll();
       }
       else
       {
         DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
         String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
         if (dinhhuongThuocNT.indexOf(',') > 0)
         {
           String khoaMa1 = dinhhuongThuocNT.substring(0, dinhhuongThuocNT.indexOf(','));
           String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',') + 1, dinhhuongThuocNT.length());
           DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
           DmKhoa khoa2 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
           this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(), khoa2.getDmkhoaMaso());
         }
         else
         {
           DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
           if (khoa1.getDmkhoaMaso() != null) {
             this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
           }
         }
       }
     }
     this.listDmThuocs.clear();

     this.hmDmThuocAll.clear();
     for (DmThuoc o : this.listDmThuocAll) {
       this.hmDmThuocAll.put(o.getDmthuocMa(), o);
     }
     if ((this.listDmThuocAll != null) && (this.listDmThuocAll.size() > 0))
     {
       if (this.searchType.equals("2")) {
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
         }
       } else {
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
         }
       }
       this.listDmThuocAll.clear();
     }
   }

   public DmKhoa getDmKho(Integer masoThuoc, String isTutruc)
   {
     DmKhoa dmKho = new DmKhoa();
     if (isTutruc.equals("0"))
     {
       DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
       Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
       dmKho = new DmKhoa(khoaMaso);
     }
     else if (isTutruc.equals("1"))
     {
       dmKho = this.khoa;
     }
     else if ((isTutruc.equals("2")) && (
       (this.hsba.getDoituongMa(true).getDmdoituongMa() == null) || (!this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP"))))
     {
       DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
       Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
       dmKho = new DmKhoa(khoaMaso);
     }
     return dmKho;
   }

   public void getTonKhoHienTai(Integer masoThuoc, String isTutruc)
   {
     TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
     this.listTonKhoHienTai.clear();
     List<TonKho> lstTonKhoHT = new ArrayList();
     if ((isTutruc.equals("0")) || (isTutruc.equals("2")))
     {
       DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
       Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(masoThuoc);
       if (khoaMaso.intValue() > 0) {
         lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, khoaMaso, IConstantsRes.PRIORITY_TON_LO_THUOC);
       }
     }
     else if (isTutruc.equals("1"))
     {
       if (this.khoa.getDmkhoaMaso() != null) {
         lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, this.khoa.getDmkhoaMaso(), IConstantsRes.PRIORITY_TON_LO_THUOC);
       }
     }
     if (lstTonKhoHT != null) {
       this.listTonKhoHienTai = lstTonKhoHT;
     }
   }

   public void displayTonInfo()
   {
     if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0) &&
       (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc() != null))
     {
       TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
       if ((this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruSoluong() != null) && (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruSoluong().doubleValue() <= tk.getTonkhoTon().doubleValue()))
       {
         String handungStr = "";
         if (tk.getTonkhoNamhandung() != null) {
           if (tk.getTonkhoThanghandung() != null)
           {
             if (tk.getTonkhoNgayhandung() != null) {
               handungStr = tk.getTonkhoNgayhandung() + "/" + tk.getTonkhoThanghandung() + "/" + tk.getTonkhoNamhandung();
             } else {
               handungStr = tk.getTonkhoThanghandung() + "/" + tk.getTonkhoNamhandung();
             }
           }
           else {
             handungStr = tk.getTonkhoNamhandung();
           }
         }
         setHandung(handungStr);
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruDongia(tk.getTonkhoDongia());
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(tk.getDmnhasanxuatMaso());
         this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(tk.getDmquocgiaMaso());
       }
     }
   }

   public void displayTonInfo1()
   {
     if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0) &&
       (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc() != null))
     {
       TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
       if ((this.sochia != null) && (this.sobichia != null) && (this.sobichia.intValue() != 0))
       {
         Double soluongCap = Double.valueOf(this.sochia.intValue() / this.sobichia.intValue());
         if (soluongCap.doubleValue() <= tk.getTonkhoTon().doubleValue())
         {
           String handungStr = "";
           if (tk.getTonkhoNamhandung() != null) {
             if (tk.getTonkhoThanghandung() != null)
             {
               if (tk.getTonkhoNgayhandung() != null) {
                 handungStr = tk.getTonkhoNgayhandung() + "/" + tk.getTonkhoThanghandung() + "/" + tk.getTonkhoNamhandung();
               } else {
                 handungStr = tk.getTonkhoThanghandung() + "/" + tk.getTonkhoNamhandung();
               }
             }
             else {
               handungStr = tk.getTonkhoNamhandung();
             }
           }
           setHandung(handungStr);
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruDongia(tk.getTonkhoDongia());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruHangsx(tk.getDmnhasanxuatMaso());
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruQuocgia(tk.getDmquocgiaMaso());
         }
       }
     }
   }

   public void getDmThuocs()
   {
     if (this.tutrucPdt.equals("0"))
     {
       DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
       DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa("KCH", "DmKhoa", "dmkhoaMa");
       DmKhoa khoa2 = (DmKhoa)dieutriUtilDel.findByMa("KNT", "DmKhoa", "dmkhoaMa");
       this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(), khoa2.getDmkhoaMaso());
     }
     else if (this.tutrucPdt.equals("1"))
     {
       if (this.khoa.getDmkhoaMaso() != null) {
         this.listDmThuocAll = this.dmThuocDelegate.findAll(this.khoa.getDmkhoaMaso());
       }
     }
     else if (this.tutrucPdt.equals("2"))
     {
       if ((this.hsba.getDoituongMa(true).getDmdoituongMa() != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")))
       {
         this.listDmThuocAll = this.dmThuocDelegate.findAll();
       }
       else
       {
         DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
         String dinhhuongThuocNT = IConstantsRes.DINH_HUONG_THUOC_NT;
         if (dinhhuongThuocNT.indexOf(',') > 0)
         {
           String khoaMa1 = dinhhuongThuocNT.substring(0, dinhhuongThuocNT.indexOf(','));
           String khoaMa2 = dinhhuongThuocNT.substring(dinhhuongThuocNT.indexOf(',') + 1, dinhhuongThuocNT.length());

           DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa1, "DmKhoa", "dmkhoaMa");
           DmKhoa khoa2 = (DmKhoa)dieutriUtilDel.findByMa(khoaMa2, "DmKhoa", "dmkhoaMa");
           this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso(), khoa2.getDmkhoaMaso());
         }
         else
         {
           DmKhoa khoa1 = (DmKhoa)dieutriUtilDel.findByMa(dinhhuongThuocNT, "DmKhoa", "dmkhoaMa");
           if (khoa1.getDmkhoaMaso() != null) {
             this.listDmThuocAll = this.dmThuocDelegate.findAll(khoa1.getDmkhoaMaso());
           }
         }
       }
     }
     this.listDmThuocs.clear();

     this.hmDmThuocAll.clear();
     for (DmThuoc o : this.listDmThuocAll) {
       this.hmDmThuocAll.put(o.getDmthuocMa(), o);
     }
     if ((this.listDmThuocAll != null) && (this.listDmThuocAll.size() > 0))
     {
       if (this.searchType.equals("2")) {
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
         }
       } else {
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
         }
       }
       this.listDmThuocAll.clear();
     }
   }

   public void reset()
   {
     this.listTkEx = new ArrayList();
     this.listTkEx_Pdt = new ArrayList();
     this.listTkEx_Tutruc = new ArrayList();
     this.listTkEx_KeToa = new ArrayList();
     this.checkThanhtoan = "block";
     this.hsba = new Hsba();
     this.hsbaKhoa = new HsbaKhoa();
     this.hsbaBhyt = new HsbaBhyt();
     this.tkExSelected = new TonKhoEx();
     this.tkEx = new TonKhoEx();
     if (this.khoa == null) {
       this.khoa = new DmKhoa();
     }
     this.maChiDan = "";
     this.tenChiDan = "";
     this.ngayHt = Utils.getCurrentDate();
     this.ngayVv = "";
     this.lanVao = "";
     this.tkMa = 0;
     this.isUpdate = Boolean.valueOf(false);
     this.maPhieu = "";
     this.count = Integer.valueOf(0);
     this.tutrucPdt = "0";
     this.listTntDel = new ArrayList();
     this.listTntDel_Pdt = new ArrayList();
     this.listTntDel_Tutruc = new ArrayList();
     this.listTntDel_KeToa = new ArrayList();
     this.tongTien = new Double("0");

     this.listDmKhoaNTAll.clear();
     this.listDmKhoaNTs.clear();
     refreshDmKhoaNT();



     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     refreshDmThuoc("0");

     this.isYeucau = Boolean.valueOf(false);
     this.isKhongthu = Boolean.valueOf(false);
     this.sovaovienOld = "";
     this.ngaySoLieuOld = "";
     this.khoaMaOld = "";
     this.checkKiemKe = "false";
     this.dmdonvitinhTen = "";
     this.handung = "";
     this.hamluong = "";
     this.tonTong = new Double(0.0D);
     this.sochia = null;
     this.sobichia = null;
     this.hsmListThuocDYNoiTru = new HashMap();
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienCN = nvDelegate.findByNd(this.identity.getUsername());
     this.dmdoituongMa = "";
   }

   public void reloadThuocNT()
   {
     if (this.tutrucPdt.equals("0")) {
       this.listTkEx = this.listTkEx_Pdt;
     } else if (this.tutrucPdt.equals("1")) {
       this.listTkEx = this.listTkEx_Tutruc;
     } else {
       this.listTkEx = this.listTkEx_KeToa;
     }
     this.listDmThuocs.clear();
     refreshDmThuoc(this.tutrucPdt);
     this.count = Integer.valueOf(this.listTkEx.size());
   }

   public int getSobaithuoc()
   {
     return this.sobaithuoc;
   }

   public void setSobaithuoc(int sobaithuoc)
   {
     this.sobaithuoc = sobaithuoc;
   }

   public Integer getDmBaithuocMaso()
   {
     return this.dmBaithuocMaso;
   }

   public void setDmBaithuocMaso(Integer dmBaithuocMaso)
   {
     this.dmBaithuocMaso = dmBaithuocMaso;
   }

   public String getDmBaithuocMa()
   {
     return this.dmBaithuocMa;
   }

   public void setDmBaithuocMa(String dmBaithuocMa)
   {
     this.dmBaithuocMa = dmBaithuocMa;
   }

   public void addBaiThuoc()
     throws Exception
   {
     if (!this.checkThanhtoan.equals("none"))
     {
       if ((this.dmBaithuocMaso != null) && (this.dmBaithuocMaso.intValue() != 0))
       {
         if ((this.hsmListThuocDYNoiTru != null) && (this.hsmListThuocDYNoiTru.size() > 0))
         {
           Set set = this.hsmListThuocDYNoiTru.entrySet();
           Iterator i = set.iterator();
           boolean founded = false;
           while (i.hasNext())
           {
             Map.Entry me = (Map.Entry)i.next();
             Integer key = (Integer)me.getKey();
             if (this.dmBaithuocMaso.equals(key))
             {
               founded = true;
               break;
             }
           }
           if (founded)
           {
             DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
             DmBaiThuoc dmBaiThuoc = (DmBaiThuoc)dtUtilDel.findByMaSo(this.dmBaithuocMaso, "DmBaiThuoc", "dmbaithuocMaso");
             String tenbaithuoc = dmBaiThuoc.getDmbaithuocTen();
             FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_FOUNDED, new Object[] { tenbaithuoc });
             return;
           }
         }
         List<BaithuocThuoc> listbaithuocThuoc = (ArrayList)DieuTriUtilDelegate.getInstance().findByAllConditions("BaithuocThuoc", "dmbaithuocMaso", "dmthuocMaso", this.dmBaithuocMaso + "", "");
         if (listbaithuocThuoc.size() == 0)
         {
           FacesMessages.instance().add(IConstantsRes.BAI_THUOC_DY_NOTFOUND_VITHUOC, new Object[0]);
           return;
         }
         for (int i = 0; i < listbaithuocThuoc.size(); i++)
         {
           BaithuocThuoc baithuocThuoc = (BaithuocThuoc)listbaithuocThuoc.get(i);
           Double soluongcapBN = Double.valueOf(baithuocThuoc.getBaithuocthuocSoluong() * this.sobaithuoc);


           getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso(), this.tutrucPdt);
           if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
           {
             Double tonHT = new Double(0.0D);
             for (TonKho tk : this.listTonKhoHienTai)
             {
               tonHT = Double.valueOf(tonHT.doubleValue() + tk.getTonkhoTon().doubleValue());
               if (tk.getTonkhoMaphieukiem() != null)
               {
                 this.tonTong = Double.valueOf(0.0D);
                 break;
               }
               this.tonTong = tonHT;
             }
             if (soluongcapBN.doubleValue() > this.tonTong.doubleValue())
             {
               FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU, new Object[0]);
               return;
             }
           }
           else
           {
             FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, new Object[] { baithuocThuoc.getDmthuocMaso().getDmthuocMa() });
             return;
           }
         }
         ThuocDongYNoiTru thuocDongY = new ThuocDongYNoiTru();
         thuocDongY.setThuocdongySoluong(this.sobaithuoc);
         thuocDongY.setThuocdongyNgaygiocn(new Date());
         thuocDongY.setDmbaithuocMaso(new DmBaiThuoc(this.dmBaithuocMaso));
         thuocDongY.setThuocdongyNhanviencn(this.nhanVienCN);
         if (IConstantsRes.DONG_Y_APPLY_ALL.equals("1"))
         {
           Double dongia_DongY = Double.valueOf(Double.parseDouble(IConstantsRes.EACH_THANGTHUOC_PRICE));
           thuocDongY.setThuocdongyDongia(dongia_DongY.doubleValue());
         }
         else
         {
           thuocDongY.setThuocdongyDongia(new Double(0.0D).doubleValue());
         }
         thuocDongY.setThuocdongyLoai(this.tutrucPdt);
         thuocDongY.setThuocdongyKhoa(this.khoa);

         List listThuocDongYNoiTru = new ArrayList();
         listThuocDongYNoiTru.add(thuocDongY);
         listThuocDongYNoiTru.add(new Boolean(false));
         listThuocDongYNoiTru.add(this.tutrucPdt);
         this.hsmListThuocDYNoiTru.put(this.dmBaithuocMaso, listThuocDongYNoiTru);
         if ((this.tkEx != null) && (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruNgaygio() == null)) {
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNgaygio(new Date());
         }
         if (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruNgaygio() == null) {
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNgaygio(new Date());
         }
         if (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruNhanviencn() == null) {
           this.tkEx.getTnt(Boolean.valueOf(true)).setThuocnoitruNhanviencn(this.nhanVienCN);
         }
         if (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruQuocgia(true).getDmquocgiaMaso() == null) {
           this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruQuocgia(true).setDmquocgiaMa("XXX");
         }
         if ((this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruHangsx() != null) && (this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruHangsx().getDmnhasanxuatMa() != null) && (!this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruHangsx().getDmnhasanxuatMa().equals("")))
         {
           DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
           DmNhaSanXuat nsxTmp = (DmNhaSanXuat)DTDel.findByMa(this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
           if (nsxTmp != null) {
             this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
           }
         }
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         String doituongMa = "";
         if (this.hsba.getDoituongMa(true).getDmdoituongMa() != null) {
           doituongMa = this.hsba.getDoituongMa(true).getDmdoituongMa();
         }
         try
         {
           Date dHt = df.parse(this.ngayHt);
           int vitri = -1;
           for (int i = 0; i < listbaithuocThuoc.size(); i++)
           {
             BaithuocThuoc baithuocThuoc = (BaithuocThuoc)listbaithuocThuoc.get(i);
             Double soluongcapBN = Double.valueOf(baithuocThuoc.getBaithuocthuocSoluong() * this.sobaithuoc);


             getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso(), this.tutrucPdt);
             if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
             {
               ThuocNoiTru thuocNT = new ThuocNoiTru();

               thuocNT.setHsbaKhoa(this.hsbaKhoa);
               thuocNT.setThuocnoitruKhoa(this.khoa);
               thuocNT.setThuocnoitruYeucau(this.isYeucau);
               thuocNT.setThuocnoitruKhongthu(this.isKhongthu);
               thuocNT.setThuocnoitruStatus("0");
               thuocNT.setThuocnoitruNgaygio(dHt);
               thuocNT.setThuocnoitruNDM(Boolean.valueOf(!baithuocThuoc.getDmthuocMaso().getDmthuocIndanhmuc().booleanValue()));
               thuocNT.setThuocnoitruNgaygiocn(new Date());
               thuocNT.setThuocnoitruNhanviencn(this.nhanVienCN);
               thuocNT.setDmbaithuocMaso(this.dmBaithuocMaso);
               thuocNT.setDmdoituongMaso(this.hsba.getDoituongMa(true));

               DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
               DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(baithuocThuoc.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
               thuocNT.setThuocnoitruMathuoc(baithuocThuoc.getDmthuocMaso());
               thuocNT.getThuocnoitruMathuoc().setDmdonvitinhMaso(dvt);
               if ((this.tutrucPdt.equals("0")) || (this.tutrucPdt.equals("2")))
               {
                 if (this.tutrucPdt.equals("0")) {
                   thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(0));
                 } else if (this.tutrucPdt.equals("2")) {
                   thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(1));
                 }
                 DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
                 Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
                 thuocNT.setThuocnoitruKho(new DmKhoa(khoaMaso));
               }
               else if (this.tutrucPdt.equals("1"))
               {
                 thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(1));
                 thuocNT.setThuocnoitruKho(this.khoa);
               }
               TonKhoEx tkExNew = (TonKhoEx)BeanUtils.cloneBean(this.tkEx);
               for (int j = 0; j < this.listTonKhoHienTai.size(); j++)
               {
                 TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(j);
                 Double tonLo = tonkhoHienTai.getTonkhoTon();
                 ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
                 ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(thuocNT);
                 tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
                 if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
                 {
                   tkExNew.setTk(tonkhoHienTai);
                   ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);

                   setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
                   tkExNew.setTnt(ctThuocnoitruNew);
                   if (vitri > -1)
                   {
                     this.listTkEx.set(vitri, tkExNew); break;
                   }
                   tkExNew.setThutu(Integer.valueOf(this.listTkEx.size()));
                   this.listTkEx.add(tkExNew);

                   break;
                 }
                 soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());
                 ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);

                 setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
                 tkExNew.setTnt(ctThuocnoitruNew);
                 tkExNew.setTk(tonkhoHienTai);
                 tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
                 if (vitri > -1)
                 {
                   this.listTkEx.set(vitri, tkExNew);
                 }
                 else
                 {
                   tkExNew.setThutu(Integer.valueOf(this.listTkEx.size()));
                   this.listTkEx.add(tkExNew);
                 }
               }
             }
           }
           this.dmBaithuocMa = "";
           this.dmBaithuocMaso = Integer.valueOf(0);
         }
         catch (Exception er)
         {
           er.printStackTrace();
         }
         if (this.tutrucPdt.equals("0")) {
           this.listTkEx_Pdt = this.listTkEx;
         } else if (this.tutrucPdt.equals("1")) {
           this.listTkEx_Tutruc = this.listTkEx;
         } else {
           this.listTkEx_KeToa = this.listTkEx;
         }
         tinhTien(this.listTkEx_Pdt, this.listTkEx_Tutruc, this.listTkEx_KeToa);
         this.tkEx = new TonKhoEx();
         this.count = Integer.valueOf(this.listTkEx.size());
       }
       this.sobaithuoc = 1;
     }
     else
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NOT_EDIT, new Object[0]);
     }
   }

   public void loadHsba()
   {
     String khoaMa = this.khoa.getDmkhoaMa();
     String hsbaMa = this.hsba.getHsbaSovaovien();
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
     BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
     HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
     ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
     if ((!khoaMa.equals("")) && (hsbaMa != null) && (!hsbaMa.equals("")))
     {
       this.hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase());
       if (this.hsbaKhoa == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
         reset();
         return;
       }
       this.checkThanhtoan = "block";
       this.hsba = this.hsbaKhoa.getHsbaSovaovien();
       if (!this.khoa.getDmkhoaMaso().equals(this.hsba.getHsbaKhoadangdt().getDmkhoaMaso())) {
         this.checkThanhtoan = "none";
       }
       if ((!this.sovaovienOld.equals(this.hsba.getHsbaSovaovien())) || (!this.ngaySoLieuOld.equals(this.ngayHt)) || (!this.khoaMaOld.equals(khoaMa)))
       {
         this.tkExSelected = new TonKhoEx();
         this.tkEx = new TonKhoEx();
         this.ngayVv = "";
         this.lanVao = "";
         this.tkMa = 0;
         this.isUpdate = Boolean.valueOf(false);
         this.maPhieu = "";
         this.count = Integer.valueOf(0);
         this.listTntDel = new ArrayList();
         this.listTkEx_Pdt = new ArrayList();
         this.listTkEx_Tutruc = new ArrayList();
         this.listTkEx_KeToa = new ArrayList();
         this.hsmListThuocDYNoiTru = new HashMap();
         this.tongTien = new Double("0");
         try
         {
           this.hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(this.hsba.getHsbaSovaovien().toUpperCase());
           if ((this.hsthtoan != null) && (this.hsthtoan.getHsthtoanNgaytt() != null)) {
             if (!this.hsthtoan.getHsthtoanNgaytt().equals(""))
             {
               this.checkThanhtoan = "none";
               FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
             }
           }
           this.hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbaMa.toUpperCase());
           if (this.hsbaBhyt == null) {
             this.hsbaBhyt = new HsbaBhyt();
           }
           Date dNgayVaoV = this.hsba.getHsbaNgaygiovaov();
           if (dNgayVaoV != null)
           {
             SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
             this.ngayVv = formatter.format(dNgayVaoV);
           }
           this.lanVao = bnDelegate.getLanVao(this.hsba.getBenhnhanMa().getBenhnhanMa().toUpperCase()).toString();








           List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgay(this.hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase(), this.hsbaKhoa.getHsbakhoaLan(), this.ngayHt);
           if (listTnt != null) {
             for (int i = 0; i < listTnt.size(); i++)
             {
               TonKhoEx tkExt = new TonKhoEx();
               ThuocNoiTru thuoc = (ThuocNoiTru)listTnt.get(i);
               if ((thuoc.getThuocnoitruTutrucPdt() != null) && (thuoc.getThuocnoitruTutrucPdt().intValue() == 1))
               {
                 tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                 tkExt.setTnt(thuoc);
                 tkExt.setThutu(Integer.valueOf(i));
                 this.listTkEx_Tutruc.add(tkExt);
               }
               else if ((thuoc.getThuocnoitruTutrucPdt() != null) && (thuoc.getThuocnoitruTutrucPdt().intValue() == 0))
               {
                 if ((thuoc.getThuocnoitruMaPhieuDT() == null) && (thuoc.getThuocnoitruStatus().equals("0")))
                 {
                   if (this.checkThanhtoan.equals("none")) {
                     tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                   } else {
                     tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
                   }
                 }
                 else {
                   tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                 }
                 tkExt.setTnt(thuoc);
                 tkExt.setThutu(Integer.valueOf(i));
                 this.listTkEx_Pdt.add(tkExt);
               }
               else
               {
                 if (thuoc.getThuocnoitruStatus().equals("0"))
                 {
                   if (this.checkThanhtoan.equals("none")) {
                     tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                   } else {
                     tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
                   }
                 }
                 else {
                   tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                 }
                 tkExt.setTnt(thuoc);
                 tkExt.setThutu(Integer.valueOf(i));
                 this.listTkEx_KeToa.add(tkExt);
               }
             }
           }
           this.dmdoituongMa = this.hsba.getDoituongMa().getDmdoituongMa();
           refreshDmThuoc(this.tutrucPdt);
           if (this.hsba.getDoituongMa().getDmdoituongMa().equals("TP"))
           {
             List<ThuocNoiTruXuatVien> listTntxv = tntDelegate.findThuocXVBySoVaoVienAndKhoaMaAndLanAndNgay(this.hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase(), this.hsbaKhoa.getHsbakhoaLan(), this.ngayHt);
             if (listTntxv != null) {
               for (int i = 0; i < listTntxv.size(); i++)
               {
                 TonKhoEx tkExt = new TonKhoEx();
                 ThuocNoiTruXuatVien thuocxv = (ThuocNoiTruXuatVien)listTntxv.get(i);

                 ThuocNoiTru tnt = new ThuocNoiTru();
                 tnt.setThuocnoitruMa(thuocxv.getThuocnoitruxvMa());
                 tnt.setHsbaKhoa(thuocxv.getHsbaKhoa());
                 tnt.setThuocnoitruBacsi(thuocxv.getThuocnoitruxvBacsi());
                 tnt.setThuocnoitruBosung(thuocxv.getThuocnoitruxvBosung());
                 tnt.setThuocnoitruCum(thuocxv.getThuocnoitruxvCum());
                 tnt.setThuocnoitruKhoa(thuocxv.getThuocnoitruxvKhoa());
                 tnt.setThuocnoitruLoai(thuocxv.getThuocnoitruxvLoai());
                 tnt.setThuocnoitruTutrucPdt(Integer.valueOf(2));
                 tnt.setThuocnoitruMaphong(thuocxv.getThuocnoitruxvMaphong());
                 tnt.setThuocnoitruMathuoc(thuocxv.getThuocnoitruxvMathuoc());
                 tnt.setThuocnoitruNgaygio(thuocxv.getThuocnoitruxvNgaygio());
                 tnt.setThuocnoitruNgaygiocn(thuocxv.getThuocnoitruxvNgaygiocn());
                 tnt.setThuocnoitruNhanviencn(thuocxv.getThuocnoitruxvNhanviencn());
                 tnt.setThuocnoitruPhanloai(thuocxv.getThuocnoitruxvPhanloai());
                 tnt.setThuocnoitruPhong(thuocxv.getThuocnoitruxvPhong());
                 tnt.setThuocnoitruSoluong(thuocxv.getThuocnoitruxvSoluong());
                 tnt.setThuocnoitruStt(thuocxv.getThuocnoitruxvStt());
                 tnt.setThuocnoitruMaChidan(thuocxv.getThuocnoitruxvMaChidan());
                 tnt.setThuocnoitruTenchidan(thuocxv.getThuocnoitruxvTenchidan());
                 if (this.checkThanhtoan.equals("none")) {
                   tkExt.setIsAllowedUpdate(Boolean.valueOf(false));
                 } else {
                   tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
                 }
                 tkExt.setTnt(tnt);
                 tkExt.setThutu(Integer.valueOf(i));
                 this.listTkEx_KeToa.add(tkExt);
               }
             }
           }
           if (this.tutrucPdt.equals("0")) {
             this.listTkEx = this.listTkEx_Pdt;
           } else if (this.tutrucPdt.equals("1")) {
             this.listTkEx = this.listTkEx_Tutruc;
           } else {
             this.listTkEx = this.listTkEx_KeToa;
           }
           ThuocDongYNoiTruDelegate thuocDYDel = ThuocDongYNoiTruDelegate.getInstance();
           SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
           Date ngayHtDate = new Date();
           try
           {
             ngayHtDate = formatter.parse(this.ngayHt);
           }
           catch (Exception er) {}
           List<ThuocDongYNoiTru> lstThuocDYNoiTru = thuocDYDel.findBySoVaoVienandKhoaDTandNgayandLoai(this.hsba.getHsbaSovaovien().toUpperCase(), khoaMa.toUpperCase(), ngayHtDate, this.tutrucPdt);
           for (ThuocDongYNoiTru thuocDongYNoiTru : lstThuocDYNoiTru)
           {
             List listThuocDongYNoiTru = new ArrayList();
             listThuocDongYNoiTru.add(thuocDongYNoiTru);
             listThuocDongYNoiTru.add(new Boolean(true));
             listThuocDongYNoiTru.add(this.tutrucPdt);

             this.hsmListThuocDYNoiTru.put(thuocDongYNoiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNoiTru);
           }
           this.chuyenManHinhThuocCLS_MaKhoa = khoaMa;
           this.chuyenManHinhThuocCLS_SoBenhAn = this.hsba.getHsbaSovaovien();
           tinhTien(this.listTkEx_Pdt, this.listTkEx_Tutruc, this.listTkEx_KeToa);
           this.count = Integer.valueOf(this.listTkEx.size());
           setSovaovienOld(this.hsba.getHsbaSovaovien());
           setNgaySoLieuOld(this.ngayHt);
           setKhoaMaOld(khoaMa);
         }
         catch (Exception e)
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
           e.printStackTrace();
           reset();
         }
       }
       else if ((this.listTkEx != null) && (this.listTkEx.size() > 0))
       {
         tinhTien(this.listTkEx_Pdt, this.listTkEx_Tutruc, this.listTkEx_KeToa);
         this.count = Integer.valueOf(this.listTkEx.size());
       }
     }
   }

   public void themCt()
   {
     if (this.hsba.getDoituongMa(true).getDmdoituongMa() != null) {
       this.dmdoituongMa = this.hsba.getDoituongMa(true).getDmdoituongMa();
     }
     if (!this.checkThanhtoan.equals("none")) {
       try
       {
         int viTri = -1;
         if (this.ctThuocNoiTruSelectedOld != -1) {
           if (this.listTkEx.size() > this.ctThuocNoiTruSelectedOld)
           {
             viTri = this.ctThuocNoiTruSelectedOld;
             if ((viTri > -1) && (!this.tkEx.getIsAllowedUpdate().booleanValue()))
             {
               ThuocNoiTru thuocNT = (ThuocNoiTru)BeanUtils.cloneBean(this.tkEx.getTnt());
               thuocNT.setThuocnoitruYeucau(this.isYeucau);
               thuocNT.setThuocnoitruKhongthu(this.isKhongthu);
               thuocNT.setThuocnoitruMaChidan(this.maChiDan);
               thuocNT.setThuocnoitruTenchidan(this.tenChiDan);

               boolean mien = false;
               if ((thuocNT.getThuocnoitruMien() != null) && (thuocNT.getThuocnoitruMien().booleanValue() == true)) {
                 mien = true;
               }
               boolean yeucau = false;
               if ((thuocNT.getThuocnoitruYeucau() != null) && (thuocNT.getThuocnoitruYeucau().booleanValue() == true)) {
                 yeucau = true;
               }
               if (this.dmdoituongMa.toUpperCase().equals("MP"))
               {
                 if ((mien) && (yeucau) && (!thuocNT.getThuocnoitruKhongthu().booleanValue())) {
                   thuocNT.setThuocnoitruDongia(thuocNT.getThuocnoitruDongia());
                 } else {
                   thuocNT.setThuocnoitruDongia(new Double(0.0D));
                 }
               }
               else if (thuocNT.getThuocnoitruKhongthu().booleanValue()) {
                 thuocNT.setThuocnoitruDongia(new Double(0.0D));
               } else {
                 thuocNT.setThuocnoitruDongia(thuocNT.getThuocnoitruDongia());
               }
               thuocNT.setThuocnoitruDongiatt(Utils.roundDoubleNumber(thuocNT.getThuocnoitruDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
               thuocNT.setThuocnoitruThanhtien(Utils.roundDoubleNumber(Double.valueOf(thuocNT.getThuocnoitruDongiatt().doubleValue() * thuocNT.getThuocnoitruSoluong().doubleValue()), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));

               this.tkEx.setTnt(thuocNT);
               this.listTkEx.set(viTri, this.tkEx);
               setHamluong("");
               setTonTong(new Double(0.0D));
               setHandung("");
               this.dmdonvitinhTen = "";
               this.tkEx = new TonKhoEx();
               this.count = Integer.valueOf(this.listTkEx.size());
               this.maChiDan = "";
               this.tenChiDan = "";
               viTri = -1;
               this.isYeucau = Boolean.valueOf(false);
               this.isKhongthu = Boolean.valueOf(false);
               return;
             }
             this.listTkEx.remove(this.ctThuocNoiTruSelectedOld);

             this.ctThuocNoiTruSelectedOld = -1;
           }
           else
           {
             this.ctThuocNoiTruSelectedOld = -1;
             this.tkEx = new TonKhoEx();
             return;
           }
         }
         getTonKhoHienTai(this.tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMaso(), this.tutrucPdt);
         Double soluongcapBN = this.tkEx.getTnt().getThuocnoitruSoluong();

         DecimalFormat decimalFormat = new DecimalFormat("#.#####");
         String soluongcapBN1 = decimalFormat.format(soluongcapBN);
         if (soluongcapBN1.length() > 4)
         {
           soluongcapBN1 = soluongcapBN1.substring(0, soluongcapBN1.length() - 1);
           soluongcapBN = Double.valueOf(soluongcapBN1);
         }
         Double soluongDaCapTrenGrid = new Double(0.0D);
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
         SimpleDateFormat timedf = new SimpleDateFormat("HH:mm:ss");

         Calendar cal = Calendar.getInstance();
         String currenttime = timedf.format(cal.getTime());
         Date dHt = df.parse(this.ngayHt + " " + currenttime);

         int vitri = -1;
         Integer thuocnoitru_ma = null;
         Double tongsoluongcapBN = new Double(0.0D);
         if (this.listTkEx.size() > 0)
         {
           for (int j = 0; j < this.listTkEx.size(); j++)
           {
             TonKhoEx tonkhoExGrid = (TonKhoEx)this.listTkEx.get(j);
             ThuocNoiTru thuocnoitruGrid = tonkhoExGrid.getTnt();
             Boolean khongthu = Boolean.valueOf(false);
             if (thuocnoitruGrid.getThuocnoitruKhongthu() == null) {
               khongthu = Boolean.valueOf(false);
             } else if (thuocnoitruGrid.getThuocnoitruKhongthu().booleanValue()) {
               khongthu = Boolean.valueOf(true);
             }
             if ((this.tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMa().toUpperCase().equals(thuocnoitruGrid.getThuocnoitruMathuoc().getDmthuocMa().toUpperCase())) && (this.isKhongthu.equals(khongthu))) {
               if (tonkhoExGrid.getIsAllowedUpdate().booleanValue())
               {
                 soluongDaCapTrenGrid = Double.valueOf(soluongDaCapTrenGrid.doubleValue() + thuocnoitruGrid.getThuocnoitruSoluong().doubleValue());
                 vitri = j;
                 thuocnoitru_ma = thuocnoitruGrid.getThuocnoitruMa();
               }
               else if ((thuocnoitruGrid.getThuocnoitruStatus().equals("1")) && (thuocnoitruGrid.getThuocnoitruMaPhieuDT() != null))
               {
                 tongsoluongcapBN = Double.valueOf(tongsoluongcapBN.doubleValue() + thuocnoitruGrid.getThuocnoitruSoluong().doubleValue());
               }
             }
           }
           soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() + soluongDaCapTrenGrid.doubleValue());
           tongsoluongcapBN = Double.valueOf(tongsoluongcapBN.doubleValue() + soluongcapBN.doubleValue());
           String tongsoluongcapBN1 = decimalFormat.format(tongsoluongcapBN);
           tongsoluongcapBN = Double.valueOf(tongsoluongcapBN1);
           if ((tongsoluongcapBN.doubleValue() > this.tonTong.doubleValue()) &&
             (!this.dmdoituongMa.toUpperCase().equals("TP")) && (!this.tutrucPdt.equals("2")))
           {
             this.tkEx.setTnt(null);
             this.tkEx.setTk(null);
             setHamluong("");
             setTonTong(new Double(0.0D));
             setHandung("");
             this.maChiDan = "";
             this.tenChiDan = "";
             FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU, new Object[0]);
             return;
           }
         }
         ThuocNoiTru thuocNT = new ThuocNoiTru();
         thuocNT = this.tkEx.getTnt();
         DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();

         DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
         Integer khoaMaso = dmKhoaDel.findMaSoByMasoThuoc(this.tkEx.getTnt(Boolean.valueOf(true)).getThuocnoitruMathuoc(true).getDmthuocMaso());
         thuocNT.setThuocnoitruKho(dmKhoaDel.find(khoaMaso));
         thuocNT.setHsbaKhoa(this.hsbaKhoa);
         thuocNT.setThuocnoitruKhoa(this.khoa);
         thuocNT.setThuocnoitruYeucau(this.isYeucau);
         thuocNT.setThuocnoitruKhongthu(this.isKhongthu);
         thuocNT.setThuocnoitruStatus("0");
         thuocNT.setThuocnoitruNgaygio(dHt);
         thuocNT.setThuocnoitruNgaygiocn(new Date());
         thuocNT.setThuocnoitruMaPhieuDT(null);
         thuocNT.setThuocnoitruMaChidan(this.maChiDan);
         thuocNT.setThuocnoitruTenchidan(this.tenChiDan);
         thuocNT.setDmdoituongMaso(this.hsba.getDoituongMa(true));
         if (this.tutrucPdt.equals("0")) {
           thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(0));
         } else if (this.tutrucPdt.equals("1")) {
           thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(1));
         } else {
           thuocNT.setThuocnoitruTutrucPdt(Integer.valueOf(2));
         }
         TonKhoEx tkExNew = (TonKhoEx)BeanUtils.cloneBean(this.tkEx);
         if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
         {
           for (int i = 0; i < this.listTonKhoHienTai.size(); i++)
           {
             TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(i);
             Double tonLo = tonkhoHienTai.getTonkhoTon();
             tkExNew = new TonKhoEx();
             ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
             ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(thuocNT);
             tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
             if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
             {
               tkExNew.setTk(tonkhoHienTai);
               ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);

               setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, this.dmdoituongMa);
               tkExNew.setTnt(ctThuocnoitruNew);
               if (vitri > -1)
               {
                 ThuocNoiTru tnt = tkExNew.getTnt();
                 tnt.setThuocnoitruMa(thuocnoitru_ma);
                 tkExNew.setTnt(tnt);
                 this.listTkEx.set(vitri, tkExNew);
                 break;
               }
               tkExNew.setThutu(Integer.valueOf(this.listTkEx.size()));
               this.listTkEx.add(tkExNew);

               break;
             }
             soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

             ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);
             setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, this.dmdoituongMa);
             tkExNew.setTnt(ctThuocnoitruNew);
             tkExNew.setTk(tonkhoHienTai);
             tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
             if (vitri > -1)
             {
               ThuocNoiTru tnt = tkExNew.getTnt();
               tnt.setThuocnoitruMa(thuocnoitru_ma);
               tkExNew.setTnt(tnt);
               this.listTkEx.set(vitri, tkExNew);
             }
             else
             {
               tkExNew.setThutu(Integer.valueOf(this.listTkEx.size()));
               this.listTkEx.add(tkExNew);
             }
             vitri = -1;
           }
         }
         else if ((this.dmdoituongMa.toUpperCase().equals("TP")) && (this.tutrucPdt.equals("2")))
         {
           ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
           ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(thuocNT);
           tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
           tkExNew.setTnt(ctThuocnoitruNew);
           if (vitri > -1)
           {
             this.listTkEx.set(vitri, tkExNew);
           }
           else
           {
             tkExNew.setThutu(Integer.valueOf(this.listTkEx.size()));
             this.listTkEx.add(tkExNew);
           }
         }
         if (this.tutrucPdt.equals("0")) {
           this.listTkEx_Pdt = this.listTkEx;
         } else if (this.tutrucPdt.equals("1")) {
           this.listTkEx_Tutruc = this.listTkEx;
         } else {
           this.listTkEx_KeToa = this.listTkEx;
         }
         tinhTien(this.listTkEx_Pdt, this.listTkEx_Tutruc, this.listTkEx_KeToa);
         setHamluong("");
         setTonTong(new Double(0.0D));
         setHandung("");
         this.dmdonvitinhTen = "";
         this.tkEx = new TonKhoEx();
         this.count = Integer.valueOf(this.listTkEx.size());
       }
       catch (Exception er)
       {
         er.printStackTrace();
       }
     } else {
       FacesMessages.instance().add(IConstantsRes.HSBA_NOT_EDIT, new Object[0]);
     }
     this.maChiDan = "";
     this.tenChiDan = "";
   }

   public void setThuocNoiTruInfo(ThuocNoiTru tnt, TonKho tonHt, String doituongMa)
   {
     boolean mien = false;
     if ((tnt.getThuocnoitruMien() != null) && (tnt.getThuocnoitruMien().booleanValue() == true)) {
       mien = true;
     }
     boolean yeucau = false;
     if ((tnt.getThuocnoitruYeucau() != null) && (tnt.getThuocnoitruYeucau().booleanValue() == true)) {
       yeucau = true;
     }
     if (doituongMa.toUpperCase().equals("MP"))
     {
       if ((mien) && (yeucau) && (!tnt.getThuocnoitruKhongthu().booleanValue())) {
         tnt.setThuocnoitruDongia(tonHt.getTonkhoDongia());
       } else {
         tnt.setThuocnoitruDongia(new Double(0.0D));
       }
     }
     else if ((doituongMa.toUpperCase().equals("TP")) && (this.tutrucPdt.equals("2"))) {
       tnt.setThuocnoitruDongia(new Double(0.0D));
     } else if ((tnt.getThuocnoitruKhongthu() != null) && (tnt.getThuocnoitruKhongthu().booleanValue())) {
       tnt.setThuocnoitruDongia(new Double(0.0D));
     } else {
       tnt.setThuocnoitruDongia(tonHt.getTonkhoDongia());
     }
     tnt.setThuocnoitruDongiatt(Utils.roundDoubleNumber(tnt.getThuocnoitruDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
     tnt.setThuocnoitruThanhtien(Utils.roundDoubleNumber(Double.valueOf(tnt.getThuocnoitruDongiatt().doubleValue() * tnt.getThuocnoitruSoluong().doubleValue()), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));
     tnt.setThuocnoitruDongiabh(tonHt.getTonkhoDongia());
     tnt.setThuocnoitruMalk(tonHt.getTonkhoMalienket());
     tnt.setThuocnoitruDongiaban(tonHt.getTonkhoDongiaban());
     tnt.setThuocnoitruNamnhap(tonHt.getTonkhoNamnhap());
     tnt.setThuocnoitruHangsx(tonHt.getDmnhasanxuatMaso());
     tnt.setThuocnoitruQuocgia(tonHt.getDmquocgiaMaso());
     tnt.setThuocnoitruNgayhd(tonHt.getTonkhoNgayhandung());
     tnt.setThuocnoitruThanghd(tonHt.getTonkhoThanghandung());
     tnt.setThuocnoitruNamhd(tonHt.getTonkhoNamhandung());
     tnt.setThuocnoitruNguon(tonHt.getDmnctMaso());
   }

   public void getDataFromPreviousDate()
   {
     this.listTkEx = new ArrayList();
     this.hsmListThuocDYNoiTru.clear();
     String khoaMa = this.khoa.getDmkhoaMa();
     String hsbaMa = this.hsba.getHsbaSovaovien();
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     SimpleDateFormat timedf = new SimpleDateFormat("HH:mm:ss");
     try
     {
       Calendar cal = Calendar.getInstance();
       String currenttime = timedf.format(cal.getTime());
       Date dHt = df.parse(this.ngayHt + " " + currenttime);
       if ((!khoaMa.equals("")) && (!hsbaMa.equals("")))
       {
         ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
         HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
         this.hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsba.getHsbaSovaovien(), khoaMa);

         String sPreviousDate = Integer.parseInt(this.ngayHt.substring(0, 2)) - 1 + this.ngayHt.substring(2);
         if (sPreviousDate.trim().length() < 10) {
           sPreviousDate = "0" + sPreviousDate;
         }
         List<ThuocNoiTru> listTnt = tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgay(hsbaMa.toUpperCase(), khoaMa.toUpperCase(), this.hsbaKhoa.getHsbakhoaLan(), sPreviousDate);
         if ((listTnt != null) && (listTnt.size() > 0))
         {
           List<ThuocNoiTru> listTnt_Temp = new ArrayList();
           for (ThuocNoiTru tnt : listTnt)
           {
             tnt.setThuocnoitruStatus("0");
             tnt.setThuocnoitruMa(null);
             tnt.setThuocnoitruNgaygio(dHt);
             tnt.setThuocnoitruNgaygiocn(new Date());
             tnt.setThuocnoitruMaPhieuDT(null);
             tnt.setThuocnoitruMaphieupdttra(null);
             tnt.setThuocnoitruMaphieu(null);
             tnt.setThuocnoitruTra(null);
             tnt.setThuocnoitruMalk(null);

             ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
             if (listTnt_Temp.size() == 0)
             {
               ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
               listTnt_Temp.add(ctThuocnoitruNew);
             }
             else
             {
               ThuocNoiTru ctThuocnoitru_Finded = new ThuocNoiTru();
               boolean foundInList = false;
               int vitri = -1;
               for (int j = 0; j < listTnt_Temp.size(); j++)
               {
                 ThuocNoiTru ctThuocnoitruNew1 = (ThuocNoiTru)listTnt_Temp.get(j);
                 if (ctThuocnoitruNew1.getThuocnoitruMathuoc().getDmthuocMaso().equals(tnt.getThuocnoitruMathuoc().getDmthuocMaso()))
                 {
                   ctThuocnoitru_Finded = ctThuocnoitruNew1;
                   foundInList = true;
                   vitri = j;
                   break;
                 }
               }
               if (foundInList == true)
               {
                 ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
                 ctThuocnoitruNew.setThuocnoitruSoluong(Double.valueOf(tnt.getThuocnoitruSoluong().doubleValue() + ctThuocnoitru_Finded.getThuocnoitruSoluong().doubleValue()));
                 listTnt_Temp.set(vitri, ctThuocnoitruNew);
               }
               else
               {
                 ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
                 listTnt_Temp.add(ctThuocnoitruNew);
               }
             }
             ctThuocnoitruNew = new ThuocNoiTru();
           }
           int i = 0;
           for (ThuocNoiTru thuoc : listTnt_Temp)
           {
             TonKhoEx tkExt = new TonKhoEx();
             if ((thuoc.getThuocnoitruTutrucPdt() != null) && (thuoc.getThuocnoitruTutrucPdt().intValue() == 1))
             {
               tkExt.setTnt(thuoc);
               tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
               tkExt.setThutu(Integer.valueOf(i));
               this.listTkEx_Tutruc.add(tkExt);
             }
             else if ((thuoc.getThuocnoitruTutrucPdt() != null) && (thuoc.getThuocnoitruTutrucPdt().intValue() == 0))
             {
               tkExt.setTnt(thuoc);
               tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
               tkExt.setThutu(Integer.valueOf(i));
               this.listTkEx_Pdt.add(tkExt);
             }
             else
             {
               tkExt.setTnt(thuoc);
               tkExt.setIsAllowedUpdate(Boolean.valueOf(true));
               tkExt.setThutu(Integer.valueOf(i));
               this.listTkEx_KeToa.add(tkExt);
             }
             i++;
           }
           if (this.tutrucPdt.equals("0")) {
             this.listTkEx = this.listTkEx_Pdt;
           } else if (this.tutrucPdt.equals("1")) {
             this.listTkEx = this.listTkEx_Tutruc;
           } else {
             this.listTkEx = this.listTkEx_KeToa;
           }
           this.count = Integer.valueOf(this.listTkEx.size());
         }
         Date previousDate = df.parse(sPreviousDate);
         ThuocDongYNoiTruDelegate thuocDYNoiTruDel = ThuocDongYNoiTruDelegate.getInstance();
         List<ThuocDongYNoiTru> lstThuocDYNoiTru = thuocDYNoiTruDel.findBySoVaoVienandKhoaDTandNgayandLoai(hsbaMa, khoaMa, previousDate, this.tutrucPdt);
         for (ThuocDongYNoiTru thuocDongYNoiTru : lstThuocDYNoiTru)
         {
           List listThuocDongYNoiTru = new ArrayList();
           listThuocDongYNoiTru.add(thuocDongYNoiTru);
           listThuocDongYNoiTru.add(new Boolean(false));
           listThuocDongYNoiTru.add(this.tutrucPdt);

           this.hsmListThuocDYNoiTru.put(thuocDongYNoiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNoiTru);
         }
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   public void delete(int index)
   {
     this.maChiDan = "";
     this.tenChiDan = "";
     TonKhoEx tkExDel = (TonKhoEx)this.listTkEx.remove(index);
     if ((this.tutrucPdt.equals("2")) && (this.hsba.getDoituongMa().getDmdoituongMa().toUpperCase().equals("TP"))) {
       this.listTntDel_KeToa.add(tkExDel.getTnt());
     } else {
       this.listTntDel.add(tkExDel.getTnt());
     }
     if (this.tutrucPdt.equals("0"))
     {
       for (int i = 0; i < this.listTkEx_Pdt.size(); i++)
       {
         TonKhoEx tkExt = (TonKhoEx)this.listTkEx_Pdt.get(i);
         tkExt.setThutu(Integer.valueOf(i));
       }
       this.listTkEx_Pdt = this.listTkEx;
     }
     else if (this.tutrucPdt.equals("1"))
     {
       for (int i = 0; i < this.listTkEx_Tutruc.size(); i++)
       {
         TonKhoEx tkExt = (TonKhoEx)this.listTkEx_Tutruc.get(i);
         tkExt.setThutu(Integer.valueOf(i));
       }
       this.listTkEx_Tutruc = this.listTkEx;
     }
     else
     {
       for (int i = 0; i < this.listTkEx_KeToa.size(); i++)
       {
         TonKhoEx tkExt = (TonKhoEx)this.listTkEx_KeToa.get(i);
         tkExt.setThutu(Integer.valueOf(i));
       }
       this.listTkEx_KeToa = this.listTkEx;
     }
     boolean isExistedThuocDY = false;
     for (TonKhoEx tkEx : this.listTkEx) {
       if (tkEx.getTnt().getDmbaithuocMaso() != null)
       {
         isExistedThuocDY = true;
         break;
       }
     }
     if (!isExistedThuocDY) {
       this.hsmListThuocDYNoiTru.remove(tkExDel.getTnt().getDmbaithuocMaso());
     }
     this.tkEx = new TonKhoEx();
     setHamluong("");
     setDmdonvitinhTen("");
     setHandung("");
     setTonTong(new Double(0.0D));
     this.count = Integer.valueOf(this.listTkEx.size());
     tinhTien(this.listTkEx_Pdt, this.listTkEx_Tutruc, this.listTkEx_KeToa);
   }

   public void end()
   {
     try
     {
       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();


       HashMap hsmThuocDYNTTemp = new HashMap();
       if ((this.hsmListThuocDYNoiTru != null) && (this.hsmListThuocDYNoiTru.size() > 0))
       {
         Set set = this.hsmListThuocDYNoiTru.entrySet();
         Iterator i = set.iterator();
         while (i.hasNext())
         {
           Map.Entry me = (Map.Entry)i.next();
           List listThuocDY = (List)me.getValue();
           ThuocDongYNoiTru thuocDY = (ThuocDongYNoiTru)listThuocDY.get(0);
           Boolean isSavedDB = (Boolean)listThuocDY.get(1);


           thuocDY.setThuocdongyKhoa(this.khoa);
           thuocDY.setHsbaSovaovien(this.hsba);
           listThuocDY.set(0, thuocDY);
           hsmThuocDYNTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);
         }
       }
       this.hsmListThuocDYNoiTru = hsmThuocDYNTTemp;



       String doituongMa = this.hsba.getDoituongMa().getDmdoituongMa();




       ArrayList<TonKhoEx> listTkEx_Temp = new ArrayList();
       if (this.listTkEx.size() > 0) {
         for (TonKhoEx tkEx_ : this.listTkEx)
         {
           ThuocNoiTru tnt = tkEx_.getTnt();
           if ((tnt.getThuocnoitruMalk() == null) || (tnt.getThuocnoitruMalk() == ""))
           {
             String isUseTuTruc = "";
             if ((tnt.getThuocnoitruTutrucPdt() != null) && (tnt.getThuocnoitruTutrucPdt().intValue() == 1)) {
               isUseTuTruc = "1";
             } else if ((tnt.getThuocnoitruTutrucPdt() != null) && (tnt.getThuocnoitruTutrucPdt().intValue() == 0)) {
               isUseTuTruc = "0";
             } else {
               isUseTuTruc = "2";
             }
             Double soluongcapBN = tnt.getThuocnoitruSoluong();
             Double tongTon = Double.valueOf(0.0D);
             getTonKhoHienTai(tnt.getThuocnoitruMathuoc().getDmthuocMaso(), isUseTuTruc);
             if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
               for (int j = 0; j < this.listTonKhoHienTai.size(); j++) {
                 tongTon = Double.valueOf(tongTon.doubleValue() + ((TonKho)this.listTonKhoHienTai.get(j)).getTonkhoTon().doubleValue());
               }
             }
             if (soluongcapBN.doubleValue() > tongTon.doubleValue()) {
               if ((!isUseTuTruc.equals("2")) || (!doituongMa.toUpperCase().equals("TP")))
               {
                 FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, new Object[] { tnt.getThuocnoitruMathuoc().getDmthuocMa() });
                 return;
               }
             }
             TonKhoEx tkExNew = (TonKhoEx)BeanUtils.cloneBean(tkEx_);
             if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
               for (int j = 0; j < this.listTonKhoHienTai.size(); j++)
               {
                 TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(j);
                 Double tonLo = tonkhoHienTai.getTonkhoTon();
                 tkExNew = new TonKhoEx();
                 ThuocNoiTru ctThuocnoitruNew = new ThuocNoiTru();
                 ctThuocnoitruNew = (ThuocNoiTru)BeanUtils.cloneBean(tnt);
                 tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));
                 if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
                 {
                   tkExNew.setTk(tonkhoHienTai);
                   ctThuocnoitruNew.setThuocnoitruSoluong(soluongcapBN);

                   setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
                   tkExNew.setTnt(ctThuocnoitruNew);

                   listTkEx_Temp.add(tkExNew);
                   break;
                 }
                 soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

                 ctThuocnoitruNew.setThuocnoitruSoluong(tonLo);
                 setThuocNoiTruInfo(ctThuocnoitruNew, tonkhoHienTai, doituongMa);
                 tkExNew.setTnt(ctThuocnoitruNew);
                 tkExNew.setTk(tonkhoHienTai);
                 tkExNew.setIsAllowedUpdate(Boolean.valueOf(true));

                 tkExNew.setThutu(Integer.valueOf(listTkEx_Temp.size()));
                 listTkEx_Temp.add(tkExNew);
               }
             } else if ((this.tutrucPdt.equals("2")) && (doituongMa.toUpperCase().equals("TP"))) {
               listTkEx_Temp.add(tkEx_);
             }
           }
           else
           {
             listTkEx_Temp.add(tkEx_);
           }
         }
       }
       this.listTkEx = listTkEx_Temp;
       if (this.tutrucPdt.equals("0")) {
         this.listTkEx_Pdt = this.listTkEx;
       } else if (this.tutrucPdt.equals("1")) {
         this.listTkEx_Tutruc = this.listTkEx;
       } else {
         this.listTkEx_KeToa = this.listTkEx;
       }
       ArrayList<ThuocNoiTru> listTnt = new ArrayList();
       if (this.listTkEx_Pdt.size() > 0) {
         for (TonKhoEx tkExNew : this.listTkEx_Pdt)
         {
           ThuocNoiTru thuoc = tkExNew.getTnt();
           listTnt.add(thuoc);
         }
       }
       if (this.listTkEx_Tutruc.size() > 0) {
         for (TonKhoEx tkExNew : this.listTkEx_Tutruc)
         {
           ThuocNoiTru thuoc = tkExNew.getTnt();
           listTnt.add(thuoc);
         }
       }
       ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();

       List<ThuocNoiTruXuatVien> listTntXv = new ArrayList();
       if (this.listTkEx_KeToa.size() > 0) {
         if (!this.hsba.getDoituongMa().getDmdoituongMa().toUpperCase().equals("TP")) {
           for (TonKhoEx tkExNew : this.listTkEx_KeToa) {
             if (tkExNew.getIsAllowedUpdate().booleanValue() == true)
             {
               ThuocNoiTru thuoc = tkExNew.getTnt();
               listTnt.add(thuoc);
             }
           }
         } else {
           for (TonKhoEx tkExNew : this.listTkEx_KeToa) {
             if (tkExNew.getIsAllowedUpdate().booleanValue() == true)
             {
               ThuocNoiTru thuoc = tkExNew.getTnt();
               ThuocNoiTruXuatVien thuocxv = new ThuocNoiTruXuatVien();
               thuocxv.setThuocnoitruxvMa(thuoc.getThuocnoitruMa());
               thuocxv.setHsbaKhoa(thuoc.getHsbaKhoa());
               thuocxv.setThuocnoitruxvBacsi(thuoc.getThuocnoitruBacsi());
               thuocxv.setThuocnoitruxvBosung(thuoc.getThuocnoitruBosung());
               thuocxv.setThuocnoitruxvCum(thuoc.getThuocnoitruCum());
               thuocxv.setThuocnoitruxvKhoa(thuoc.getThuocnoitruKhoa());
               thuocxv.setThuocnoitruxvLoai(thuoc.getThuocnoitruLoai());
               thuocxv.setThuocnoitruxvMaphong(thuoc.getThuocnoitruMaphong());
               thuocxv.setThuocnoitruxvMathuoc(thuoc.getThuocnoitruMathuoc());
               thuocxv.setThuocnoitruxvNgaygio(thuoc.getThuocnoitruNgaygio());
               thuocxv.setThuocnoitruxvNgaygiocn(thuoc.getThuocnoitruNgaygiocn());
               thuocxv.setThuocnoitruxvNhanviencn(thuoc.getThuocnoitruNhanviencn());
               thuocxv.setThuocnoitruxvPhanloai(thuoc.getThuocnoitruPhanloai());
               thuocxv.setThuocnoitruxvPhong(thuoc.getThuocnoitruPhong());
               thuocxv.setThuocnoitruxvSoluong(thuoc.getThuocnoitruSoluong());
               thuocxv.setThuocnoitruxvStt(thuoc.getThuocnoitruStt());
               thuocxv.setThuocnoitruxvMaChidan(thuoc.getThuocnoitruMaChidan());
               thuocxv.setThuocnoitruxvTenchidan(thuoc.getThuocnoitruTenchidan());
               listTntXv.add(thuocxv);
             }
           }
         }
       }
       List<ThuocNoiTruXuatVien> listTntXvDel = new ArrayList();
       if (this.listTntDel_KeToa.size() > 0) {
         for (ThuocNoiTru thuoc : this.listTntDel_KeToa) {
           if (thuoc.getThuocnoitruTutrucPdt().intValue() == 2)
           {
             ThuocNoiTruXuatVien thuocxv = new ThuocNoiTruXuatVien();
             thuocxv.setThuocnoitruxvMa(thuoc.getThuocnoitruMa());
             thuocxv.setHsbaKhoa(thuoc.getHsbaKhoa());
             thuocxv.setThuocnoitruxvBacsi(thuoc.getThuocnoitruBacsi());
             thuocxv.setThuocnoitruxvBosung(thuoc.getThuocnoitruBosung());
             thuocxv.setThuocnoitruxvCum(thuoc.getThuocnoitruCum());
             thuocxv.setThuocnoitruxvKhoa(thuoc.getThuocnoitruKhoa());
             thuocxv.setThuocnoitruxvLoai(thuoc.getThuocnoitruLoai());
             thuocxv.setThuocnoitruxvMaphong(thuoc.getThuocnoitruMaphong());
             thuocxv.setThuocnoitruxvMathuoc(thuoc.getThuocnoitruMathuoc());
             thuocxv.setThuocnoitruxvNgaygio(thuoc.getThuocnoitruNgaygio());
             thuocxv.setThuocnoitruxvNgaygiocn(thuoc.getThuocnoitruNgaygiocn());
             thuocxv.setThuocnoitruxvNhanviencn(thuoc.getThuocnoitruNhanviencn());
             thuocxv.setThuocnoitruxvPhanloai(thuoc.getThuocnoitruPhanloai());
             thuocxv.setThuocnoitruxvPhong(thuoc.getThuocnoitruPhong());
             thuocxv.setThuocnoitruxvSoluong(thuoc.getThuocnoitruSoluong());
             thuocxv.setThuocnoitruxvStt(thuoc.getThuocnoitruStt());
             thuocxv.setThuocnoitruxvMaChidan(thuoc.getThuocnoitruMaChidan());
             thuocxv.setThuocnoitruxvTenchidan(thuoc.getThuocnoitruTenchidan());
             listTntXvDel.add(thuocxv);
           }
         }
       }
       if ((listTntXv.size() > 0) || (listTntXvDel.size() > 0)) {
         this.maPhieu = tntDelegate.createThuocNoiTruXuatVien(listTntXv, listTntXvDel);
       }
       if ((listTnt.size() > 0) || (this.listTntDel.size() > 0)) {
         this.maPhieu = tntDelegate.createThuocNoiTru(listTnt, this.listTntDel, this.hsmListThuocDYNoiTru);
       }
       HsttThreadUtil capnhatHstt = new HsttThreadUtil();
       capnhatHstt.setHoSoBenhAn(this.hsba);
       capnhatHstt.start();
       if (!this.maPhieu.equals(""))
       {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
         this.hsmListThuocDYNoiTru.clear();
         setSovaovienOld("");
         setNgaySoLieuOld("");
         setKhoaMaOld("");
         loadHsba();
         this.listTntDel.clear();
         this.listTntDel_KeToa.clear();
       }
       else if ((listTnt.size() > 0) || (this.listTntDel.size() > 0))
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
       this.ctThuocNoiTruSelectedOld = -1;
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   public void chinhSuaAJAX(int index)
   {
     try
     {
       this.tkExSelected = ((TonKhoEx)this.listTkEx.get(index));
       if (this.tkExSelected == null) {
         return;
       }
       this.tkEx = ((TonKhoEx)BeanUtils.cloneBean(this.tkExSelected));
       this.maChiDan = this.tkEx.getTnt().getThuocnoitruMaChidan();
       this.tenChiDan = this.tkEx.getTnt().getThuocnoitruTenchidan();
       Double tonHT;
       if ((this.dmdoituongMa.toUpperCase().equals("TP")) && (this.tutrucPdt.equals("2")))
       {
         setTonTong(new Double(0.0D));
       }
       else
       {
         getTonKhoHienTai(this.tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocMaso(), this.tutrucPdt);
         tonHT = new Double(0.0D);
         for (TonKho tk : this.listTonKhoHienTai)
         {
           tonHT = Double.valueOf(tonHT.doubleValue() + tk.getTonkhoTon().doubleValue());
           if (tk.getTonkhoMaphieukiem() != null)
           {
             setTonTong(new Double(0.0D));
             this.tkEx.getTnt().setThuocnoitruMathuoc(null);
             setCheckKiemKe("true");
             break;
           }
           setTonTong(tonHT);
         }
       }
       String handungStr = "";
       if ((this.tkEx.getTnt().getThuocnoitruNamhd() != null) &&
         (!this.tkEx.getTnt().getThuocnoitruNamhd().equals(""))) {
         if (this.tkEx.getTnt().getThuocnoitruThanghd() != null)
         {
           if (this.tkEx.getTnt().getThuocnoitruThanghd().equals("")) {
             handungStr = this.tkEx.getTnt().getThuocnoitruNamhd();
           } else if (this.tkEx.getTnt().getThuocnoitruNgayhd() != null)
           {
             if (!this.tkEx.getTnt().getThuocnoitruNgayhd().equals("")) {
               handungStr = this.tkEx.getTnt().getThuocnoitruNgayhd() + "/" + this.tkEx.getTnt().getThuocnoitruThanghd() + "/" + this.tkEx.getTnt().getThuocnoitruNamhd();
             } else {
               handungStr = this.tkEx.getTnt().getThuocnoitruThanghd() + "/" + this.tkEx.getTnt().getThuocnoitruNamhd();
             }
           }
           else {
             handungStr = this.tkEx.getTnt().getThuocnoitruThanghd() + "/" + this.tkEx.getTnt().getThuocnoitruNamhd();
           }
         }
         else {
           handungStr = this.tkEx.getTnt().getThuocnoitruNamhd();
         }
       }
       setIsAllowedUpdateSoLuong(this.tkEx.getIsAllowedUpdate());
       setHandung(handungStr);
       setHamluong(this.tkEx.getTnt().getThuocnoitruMathuoc().getDmthuocHamluong());
       setIsYeucau(this.tkEx.getTnt().getThuocnoitruYeucau());
       setIsKhongthu(this.tkEx.getTnt().getThuocnoitruKhongthu());
       DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
       DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(this.tkEx.getTnt().getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
       setDmdonvitinhTen(dvt.getDmdonvitinhTen());
       this.tkEx.getTnt().getThuocnoitruMathuoc().setDmdonvitinhMaso(dvt);
       this.ctThuocNoiTruSelectedOld = index;
     }
     catch (Exception er)
     {
       logger.info("ERROR: " + er.getMessage());
     }
   }

   private void tinhTien(List<TonKhoEx> listTkEx1, List<TonKhoEx> listTkEx2, List<TonKhoEx> listTkEx3)
   {
     this.tongTien = new Double("0");
     DecimalFormat decimalFormat = new DecimalFormat("#.####");
     if ((listTkEx1.size() > 0) && (listTkEx1 != null)) {
       for (TonKhoEx tkExt : listTkEx1)
       {
         ThuocNoiTru thuoc = tkExt.getTnt();
         Double sl = thuoc.getThuocnoitruSoluong();
         String sl1 = decimalFormat.format(sl);
         sl = Double.valueOf(sl1);
         if (sl == null) {
           sl = new Double("0");
         }
         Double dg = thuoc.getThuocnoitruDongia();
         if (dg == null) {
           dg = new Double("0");
         }
         this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
       }
     }
     if ((listTkEx2.size() > 0) && (listTkEx2 != null)) {
       for (TonKhoEx tkExt : listTkEx2)
       {
         ThuocNoiTru thuoc = tkExt.getTnt();
         Double sl = thuoc.getThuocnoitruSoluong();
         String sl1 = decimalFormat.format(sl);
         sl = Double.valueOf(sl1);
         if (sl == null) {
           sl = new Double("0");
         }
         Double dg = thuoc.getThuocnoitruDongia();
         if (dg == null) {
           dg = new Double("0");
         }
         this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
       }
     }
     if ((listTkEx3.size() > 0) && (listTkEx3 != null)) {
       for (TonKhoEx tkExt : listTkEx3)
       {
         ThuocNoiTru thuoc = tkExt.getTnt();
         Double sl = thuoc.getThuocnoitruSoluong();
         String sl1 = decimalFormat.format(sl);
         sl = Double.valueOf(sl1);
         if (sl == null) {
           sl = new Double("0");
         }
         Double dg = thuoc.getThuocnoitruDongia();
         if (dg == null) {
           dg = new Double("0");
         }
         this.tongTien = Double.valueOf(this.tongTien.doubleValue() + sl.doubleValue() * dg.doubleValue());
       }
     }
   }

   public String thuchienToaThuocAction()
   {
     this.reportTypeTD = "CapNhatThuocBNSD";


     String khoaMa = this.khoa.getDmkhoaMa();
     String hsbaMa = this.hsba.getHsbaSovaovien();
     String baocao1 = null;
     try
     {
       String pathTemplate = "";
       String sub1Template = "";
       String sub2Template = "";
       String sub3Template = "";
       if ((this.hsba != null) && (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("TP")))
       {
         if (IConstantsRes.MAU_TOA_THUOC.equals("A4"))
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_main_A4.jrxml";

           sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub1_A4.jrxml";

           sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub2_A4.jrxml";

           sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub3_A4.jrxml";
         }
         else
         {
           pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_main.jrxml";

           sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub1.jrxml";

           sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub2.jrxml";

           sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_thuphi_sub3.jrxml";
         }
       }
       else if (IConstantsRes.MAU_TOA_THUOC.equals("A4"))
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_main_A4.jrxml";

         sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub1_A4.jrxml";

         sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub2_A4.jrxml";

         sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub3_A4.jrxml";
       }
       else
       {
         pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_main.jrxml";

         sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub1.jrxml";

         sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub2.jrxml";

         sub3Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/toathuoc_sub3.jrxml";
       }
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       JasperReport sub3Report = JasperCompileManager.compileReport(sub3Template);

       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);

       Map<String, Object> params = new HashMap();
       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);
       params.put("sub3", sub3Report);

       params.put("SOVAOVIEN", hsbaMa);

       params.put("HOTENBN", this.hsba.getBenhnhanMa(true).getBenhnhanHoten());
       String diachistr = "";
       if (this.hsba.getBenhnhanMa(true).getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa(true).getBenhnhanDiachi() + ",";
       }
       if (this.hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa(true).getXaMa(true).getDmxaTen() + ",";
       }
       if (this.hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.hsba.getBenhnhanMa(true).getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       String soTheTe_KhaiSinh_ChungSinh = "";

       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       HsbaBhyt hsbaBhyt = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(hsbaMa);
       if (this.hsba.getDoituongMa(true).getDmdoituongMa().toUpperCase().equals("BH"))
       {
         if (hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen() != null) {
           params.put("NOIDKKCBBANDAU", hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienTen());
         }
         if (hsbaBhyt.getHsbabhytGiatri0() != null) {
           params.put("GTTU", sdf.format(hsbaBhyt.getHsbabhytGiatri0()));
         } else {
           params.put("GTTU", "");
         }
         if (hsbaBhyt.getHsbabhytGiatri1() != null) {
           params.put("GTDEN", sdf.format(hsbaBhyt.getHsbabhytGiatri1()));
         } else {
           params.put("GTDEN", "");
         }
         if ((hsbaBhyt.getHsbabhytSothebh() != null) && (!hsbaBhyt.getHsbabhytSothebh().equals("")))
         {
           String maBV = hsbaBhyt.getHsbabhytMakcb(true).getDmbenhvienMa();
           if (maBV == null) {
             maBV = "";
           }
           String maTheBHYT = hsbaBhyt.getHsbabhytSothebh();
           params.put("MATHEBHYT", getMaTheBHYTDisplay(maTheBHYT));
           params.put("MABENHVIEN", maBV);
         }
         else
         {
           params.put("MATHEBHYT", "");
           params.put("MABENHVIEN", "");
         }
         soTheTe_KhaiSinh_ChungSinh = hsbaBhyt.getHsbabhytKhaisinh();
       }
       else
       {
         params.put("NOIDKKCBBANDAU", "");
         params.put("GTTU", "");
         params.put("GTDEN", "");
         params.put("MATHEBHYT", "");
         params.put("MABENHVIEN", "");
       }
       params.put("NGAYDIEUTRI", new Date());
       params.put("BASIDIEUTRI", null);
       if (this.nhanVienCN != null) {
         params.put("NHANVIENCN", this.nhanVienCN.getDtdmnhanvienTen());
       } else {
         params.put("NHANVIENCN", "");
       }
       int iTuoi = 1;
       if (this.hsba.getBenhnhanMa(true).getBenhnhanTuoi() != null) {
         iTuoi = this.hsba.getBenhnhanMa(true).getBenhnhanTuoi().intValue();
       }
       int iDonviTuoi = 1;
       if (this.hsba.getBenhnhanMa(true).getBenhnhanDonvituoi() != null) {
         iDonviTuoi = this.hsba.getBenhnhanMa(true).getBenhnhanDonvituoi().shortValue();
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
       params.put("GIOITINH", this.hsba.getBenhnhanMa().getDmgtMaso().getDmgtTen());

       boolean bVuotTuyen = false;
       if (bVuotTuyen) {
         params.put("VUOTTUYEN", "X");
       } else {
         params.put("VUOTTUYEN", "");
       }
       params.put("CHANDOAN", this.hsba.getHsbaMachdravien(true).getDmbenhicdTen());
       params.put("KHOADIEUTRI", this.khoa.getDmkhoaMaso());


       String sHuyetApMin = "";
       String sHuyetApMax = "";
       String sHuyetAp = "";
       String sMach = "";
       String sNhietDo = "";


       TiepDonDelegate tdDele = TiepDonDelegate.getInstance();
       if ((this.hsba.getTiepdonMa() != null) && (!this.hsba.getTiepdonMa().equals("")))
       {
         TiepDon tiepdon = tdDele.find(this.hsba.getTiepdonMa());
         if (tiepdon != null)
         {
           if (tiepdon.getTiepdonMach() != null)
           {
             sMach = tiepdon.getTiepdonMach().toString();
             params.put("MACH", sMach);
           }
           if (tiepdon.getTiepdonHamax() != null) {
             sHuyetApMax = tiepdon.getTiepdonHamax().toString();
           }
           if (tiepdon.getTiepdonHamin() != null) {
             sHuyetApMin = tiepdon.getTiepdonHamin().toString();
           }
           sHuyetAp = sHuyetApMax + " / " + sHuyetApMin;
           if ((tiepdon.getTiepdonHamax() != null) || (tiepdon.getTiepdonHamin() != null)) {
             params.put("HUYETAP", sHuyetAp);
           }
           if (tiepdon.getTiepdonNhietdo() != null)
           {
             sNhietDo = tiepdon.getTiepdonNhietdo().toString();
             params.put("NHIETDO", sNhietDo);
           }
         }
       }
       if ((soTheTe_KhaiSinh_ChungSinh != null) && (!soTheTe_KhaiSinh_ChungSinh.equals(""))) {
         params.put("SOTHETEKSCS", soTheTe_KhaiSinh_ChungSinh);
       } else {
         params.put("SOTHETEKSCS", null);
       }
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", this.reportTypeTD);


       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");


       this.index += 1;
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     return "B160_Chonmenuxuattaptin";
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

   public String getNgayVv()
   {
     return this.ngayVv;
   }

   public void setNgayVv(String ngayVv)
   {
     this.ngayVv = ngayVv;
   }

   public HsbaBhyt getHsbaBhyt()
   {
     return this.hsbaBhyt;
   }

   public void setHsbaBhyt(HsbaBhyt hsbaBhyt)
   {
     this.hsbaBhyt = hsbaBhyt;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getLanVao()
   {
     return this.lanVao;
   }

   public void setLanVao(String lanVao)
   {
     this.lanVao = lanVao;
   }

   public int getTkMa()
   {
     return this.tkMa;
   }

   public void setTkMa(int tkMa)
   {
     this.tkMa = tkMa;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public List<TonKhoEx> getListTkEx()
   {
     return this.listTkEx;
   }

   public void setListTkEx(ArrayList<TonKhoEx> listTkEx)
   {
     this.listTkEx = listTkEx;
   }

   public TonKhoEx getTkExSelected()
   {
     return this.tkExSelected;
   }

   public void setTkExSelected(TonKhoEx tkExSelected)
   {
     this.tkExSelected = tkExSelected;
   }

   public TonKhoEx getTkEx()
   {
     return this.tkEx;
   }

   public void setTkEx(TonKhoEx tkEx)
   {
     this.tkEx = tkEx;
   }

   public Boolean getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setIsUpdate(Boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public DmKhoa getKhoa()
   {
     return this.khoa;
   }

   public void setKhoa(DmKhoa khoa)
   {
     this.khoa = khoa;
   }

   public String getTutrucPdt()
   {
     return this.tutrucPdt;
   }

   public void setTutrucPdt(String tutrucPdt)
   {
     this.tutrucPdt = tutrucPdt;
   }

   public String getMalk()
   {
     return this.malk;
   }

   public void setMalk(String malk)
   {
     this.malk = malk;
   }

   public ArrayList<ThuocNoiTru> getListTntDel()
   {
     return this.listTntDel;
   }

   public void setListTntDel(ArrayList<ThuocNoiTru> listTntDel)
   {
     this.listTntDel = listTntDel;
   }

   public Double getTongTien()
   {
     return this.tongTien;
   }

   public void setTongTien(Double tongTien)
   {
     this.tongTien = tongTien;
   }

   public HsThtoan getHsthtoan()
   {
     return this.hsthtoan;
   }

   public void setHsthtoan(HsThtoan hsthtoan)
   {
     this.hsthtoan = hsthtoan;
   }

   public String getCheckThanhtoan()
   {
     return this.checkThanhtoan;
   }

   public void setCheckThanhtoan(String checkThanhtoan)
   {
     this.checkThanhtoan = checkThanhtoan;
   }

   public void selectRadioValue(ValueChangeEvent event)
   {
     HtmlSelectOneRadio radio = (HtmlSelectOneRadio)event.getComponent();
     if (radio.getValue().toString().equals("0"))
     {
       setTutrucPdt("0");
       this.listTkEx = new ArrayList();
       this.listTkEx = this.listTkEx_Pdt;
     }
     else if (radio.getValue().toString().equals("1"))
     {
       setTutrucPdt("1");
       this.listTkEx = new ArrayList();
       this.listTkEx = this.listTkEx_Tutruc;
     }
     else
     {
       setTutrucPdt("2");
       this.listTkEx = new ArrayList();
       this.listTkEx = this.listTkEx_KeToa;
     }
     this.count = Integer.valueOf(this.listTkEx.size());
   }

   public Boolean getIsYeucau()
   {
     return this.isYeucau;
   }

   public void setIsYeucau(Boolean isYeucau)
   {
     this.isYeucau = isYeucau;
   }

   public Boolean getIsKhongthu()
   {
     return this.isKhongthu;
   }

   public void setIsKhongthu(Boolean isKhongthu)
   {
     this.isKhongthu = isKhongthu;
   }

   public String getSovaovienOld()
   {
     return this.sovaovienOld;
   }

   public void setSovaovienOld(String sovaovienOld)
   {
     this.sovaovienOld = sovaovienOld;
   }

   public String getNgaySoLieuOld()
   {
     return this.ngaySoLieuOld;
   }

   public void setNgaySoLieuOld(String ngaySoLieuOld)
   {
     this.ngaySoLieuOld = ngaySoLieuOld;
   }

   public String getKhoaMaOld()
   {
     return this.khoaMaOld;
   }

   public void setKhoaMaOld(String khoaMaOld)
   {
     this.khoaMaOld = khoaMaOld;
   }

   public String getCheckKiemKe()
   {
     return this.checkKiemKe;
   }

   public void setCheckKiemKe(String checkKiemKe)
   {
     this.checkKiemKe = checkKiemKe;
   }

   public String getDmdonvitinhTen()
   {
     return this.dmdonvitinhTen;
   }

   public void setDmdonvitinhTen(String dmdonvitinhTen)
   {
     this.dmdonvitinhTen = dmdonvitinhTen;
   }

   public Double getTonTong()
   {
     return this.tonTong;
   }

   public void setTonTong(Double tonTong)
   {
     this.tonTong = tonTong;
   }

   public String getHamluong()
   {
     return this.hamluong;
   }

   public void setHamluong(String hamluong)
   {
     this.hamluong = hamluong;
   }

   public String getHandung()
   {
     return this.handung;
   }

   public void setHandung(String handung)
   {
     this.handung = handung;
   }

   public Integer getSochia()
   {
     return this.sochia;
   }

   public void setSochia(Integer sochia)
   {
     this.sochia = sochia;
   }

   public Integer getSobichia()
   {
     return this.sobichia;
   }

   public void setSobichia(Integer sobichia)
   {
     this.sobichia = sobichia;
   }

   public Boolean getIsAllowedUpdateSoLuong()
   {
     return this.isAllowedUpdateSoLuong;
   }

   public void setIsAllowedUpdateSoLuong(Boolean isAllowedUpdateSoLuong)
   {
     this.isAllowedUpdateSoLuong = isAllowedUpdateSoLuong;
   }

   public String getDmdoituongMa()
   {
     return this.dmdoituongMa;
   }

   public void setDmdoituongMa(String dmdoituongMa)
   {
     this.dmdoituongMa = dmdoituongMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.ThuocydungcusudungAction

 * JD-Core Version:    0.7.0.1

 */