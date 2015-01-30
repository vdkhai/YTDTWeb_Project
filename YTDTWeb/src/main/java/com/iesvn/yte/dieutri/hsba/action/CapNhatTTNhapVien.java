 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBhyt;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTruXuatVien;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.BenhNhanTrungTheBhytDTO;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.entity.DmTinh;
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
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B212_Capnhatthongtinnhapvien")
 @Synchronized(timeout=6000000L)
 public class CapNhatTTNhapVien
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private String msgFail = "";
   private String msgSuccess = "";
   private String giatri1;
   private String giatri2;
   private String giatri3;
   private String giatri4;
   private String moc1;
   private String moc2;
   private String moc3;
   private String listMaTinhBhyt;
   private boolean disabledGhinhan = false;
   private boolean dangGhinhan = false;
   private List<BenhNhanTrungTheBhytDTO> listBN;
   private boolean showListBn;
   private String maDoituong_Old = "";
   private static Logger log = Logger.getLogger(CapNhatTTNhapVien.class);
   private static final long serialVersionUID = 10L;
   private String resultHidden = "";
   private String ngayhientai = "";
   private String showMenu;
   private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
   private DmTang tangChuyenDen;
   private List<SelectItem> listDmTangs = new ArrayList();
   private List<SelectItem> listDmKhoaNTs = new ArrayList();
   private List<DmKhoa> listDmKhoaNTAll = new ArrayList();
   private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap();
   private DmKhoa khoaDangDt;
   private boolean lockDoituong = false;
   private boolean daCheckTrungBN = false;
   private boolean trungBN = false;
   private String strMsgTrungBN = "";
   private BenhNhan benhNhan;
   private String ngaySinh;
   private Hsba hoSoBenhAn;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioRaVien;
   private String ngayRaVien;
   private HsbaBhyt hsbaBHYT;

   public String init(String typeMenu)
   {
     resetValue();
     refreshDmKhoaNT();
     this.showMenu = typeMenu;
     return "DieuTri_CapNhat_CapNhatThongTinNhapVien";
   }

   @End
   public void end() {}

   private void setOtherInfor()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);

     Date ngayGioVaoVien = this.hoSoBenhAn.getHsbaNgaygiovaov();
     if (ngayGioVaoVien != null)
     {
       Calendar dngayGioVaoVien = Calendar.getInstance();
       dngayGioVaoVien.setTime(ngayGioVaoVien);
       if (ngayGioVaoVien != null)
       {
         this.gioVaoVien = Utils.getGioPhut(ngayGioVaoVien);
         this.ngayVaoVien = formatter.format(ngayGioVaoVien);
       }
     }
     Date ngayGioRaVien = this.hoSoBenhAn.getHsbaNgaygiorav();
     if (ngayGioRaVien != null)
     {
       Calendar dngayGioRaVien = Calendar.getInstance();
       dngayGioRaVien.setTime(ngayGioRaVien);
       if (ngayGioRaVien != null)
       {
         this.gioRaVien = Utils.getGioPhut(ngayGioRaVien);
         this.ngayRaVien = formatter.format(Long.valueOf(ngayGioRaVien.getTime()));
       }
     }
     if (this.hsbaBHYT != null)
     {
       Date dGiaTri1 = this.hsbaBHYT.getHsbabhytGiatri0();
       if (dGiaTri1 != null) {
         this.giatri1 = formatter.format(dGiaTri1);
       } else {
         this.giatri1 = "";
       }
       Date dGiaTri2 = this.hsbaBHYT.getHsbabhytGiatri1();
       if (dGiaTri2 != null) {
         this.giatri2 = formatter.format(dGiaTri2);
       } else {
         this.giatri2 = "";
       }
       Date dGiaTri3 = this.hsbaBHYT.getHsbabhytGiatri2();
       if (dGiaTri3 != null) {
         this.giatri3 = formatter.format(dGiaTri3);
       } else {
         this.giatri3 = "";
       }
       Date dGiaTri4 = this.hsbaBHYT.getHsbabhytGiatri3();
       if (dGiaTri4 != null) {
         this.giatri4 = formatter.format(dGiaTri4);
       } else {
         this.giatri4 = "";
       }
       Date dmoc1 = this.hsbaBHYT.getHsbabhytMoc1();
       if (dmoc1 != null) {
         this.moc1 = formatter.format(dmoc1);
       } else {
         this.moc1 = "";
       }
       Date dmoc2 = this.hsbaBHYT.getHsbabhytMoc2();
       if (dmoc2 != null) {
         this.moc2 = formatter.format(dmoc2);
       } else {
         this.moc2 = "";
       }
       Date dmoc3 = this.hsbaBHYT.getHsbabhytMoc3();
       if (dmoc3 != null) {
         this.moc3 = formatter.format(dmoc3);
       } else {
         this.moc3 = "";
       }
     }
     else
     {
       this.giatri1 = "";
       this.giatri2 = "";
       this.giatri3 = "";
       this.giatri4 = "";

       this.moc1 = "";
       this.moc2 = "";
       this.moc3 = "";
     }
   }

   public void resetValue()
   {
     log.info("init() ");
     this.benhNhan = new BenhNhan();
     SetInforUtil.setInforIfNullForBN(this.benhNhan);

     this.hoSoBenhAn = new Hsba();
     this.hoSoBenhAn.setBenhnhanMa(this.benhNhan);

     SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

     this.hsbaBHYT = new HsbaBhyt();
     SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);

     this.ngaySinh = "";
     this.gioVaoVien = Utils.getGioPhut(new Date());
     this.ngayVaoVien = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     this.gioRaVien = "";
     this.ngayRaVien = "";
     this.resultHidden = "";

     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     this.ngayhientai = formatter.format(new Date());

     this.giatri1 = "";
     this.giatri2 = "";
     this.giatri3 = "";
     this.giatri4 = "";
     this.moc1 = "";
     this.moc2 = "";
     this.moc3 = "";
     this.lockChuyenKhoa = "";
     this.disabledGhinhan = false;
     this.dangGhinhan = false;
     this.listBN = new ArrayList();
     this.showListBn = false;

     List<DmTinh> listDmTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh");
     this.listMaTinhBhyt = "";
     for (DmTinh each : listDmTinh) {
       this.listMaTinhBhyt = (this.listMaTinhBhyt + each.getDmtinhBHYT() + ",");
     }
     this.tangChuyenDen = new DmTang();
     this.khoaDangDt = new DmKhoa();
     this.lockDoituong = false;
     this.daCheckTrungBN = false;
     this.trungBN = false;
     this.strMsgTrungBN = "";
   }

   private String gioi = "";
   private String lockChuyenKhoa;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT;

   public HsbaBhyt getHsbaBHYT()
   {
     return this.hsbaBHYT;
   }

   public void setHsbaBHYT(HsbaBhyt hsbaBHYT)
   {
     this.hsbaBHYT = hsbaBHYT;
   }

   public void displayInfor()
     throws Exception
   {
     log.info("begin displayInfo=======");
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();

       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       if ((sba != null) && (!sba.trim().equals("")))
       {
         resetValue();
         Hsba hoSoBenhAn_temp = hsbaDelegate.find(sba);
         if (hoSoBenhAn_temp != null)
         {
           this.hoSoBenhAn = hoSoBenhAn_temp;
           SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);

           this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();

           SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
           if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
             this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
           }
           SetInforUtil.setInforIfNullForBN(this.benhNhan);


           this.maDoituong_Old = this.hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
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
           HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
           HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
           if (hsbaBhytLast != null)
           {
             this.hsbaBHYT = hsbaBhytLast;
             SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);
           }
           setOtherInfor();

           HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();
           HsThtoan hsbaHsThtoan_temp = hsttDelegate.findBySovaovien(this.hoSoBenhAn.getHsbaSovaovien());
           if ((hsbaHsThtoan_temp != null) &&
             (hsbaHsThtoan_temp.getHsthtoanDatt() != null) && (hsbaHsThtoan_temp.getHsthtoanDatt().booleanValue()))
           {
             FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
             this.disabledGhinhan = true;
           }
           List<ThuocNoiTruXuatVien> lstTntXv = hsbaDelegate.findTntXuatVienBySoBenhAn(this.hoSoBenhAn.getHsbaSovaovien());

           List<HsbaKhoa> listHsbaKhoa = new ArrayList();
           listHsbaKhoa = HsbaKhoaDelegate.getInstance().findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
           List<ThuocNoiTru> listTnt_Xuatvien = new ArrayList();
           ThuocNoiTruDelegate tntDel = ThuocNoiTruDelegate.getInstance();
           for (HsbaKhoa eachHsbaKhoa : listHsbaKhoa)
           {
             listTnt_Xuatvien = tntDel.findByHsbaKhoa(eachHsbaKhoa.getHsbakhoaMaso());
             if ((listTnt_Xuatvien != null) && (listTnt_Xuatvien.size() > 0)) {
               break;
             }
           }
           if (((lstTntXv != null) && (lstTntXv.size() > 0)) || ((listTnt_Xuatvien != null) && (listTnt_Xuatvien.size() > 0))) {
             this.lockDoituong = true;
           } else {
             this.lockDoituong = false;
           }
           this.khoaDangDt = this.hoSoBenhAn.getHsbaKhoadangdt();




           List<HsbaChuyenMon> listHsbaCM = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien(this.hoSoBenhAn.getHsbaSovaovien());
           for (HsbaKhoa hsbaKhoa : listHsbaKhoa) {
             if (hsbaKhoa.getKhoaMa().getDmkhoaMa().equals(this.hoSoBenhAn.getHsbaKhoadangdt().getDmkhoaMa()))
             {
               if (hsbaKhoa.getDmtangMaso() == null) {
                 break;
               }
               this.tangChuyenDen = hsbaKhoa.getDmtangMaso(); break;
             }
           }
           this.lockChuyenKhoa = ((listHsbaKhoa.size() > 1) || ((listHsbaCM != null) && (listHsbaCM.size() > 1)) ? "lock" : "");
         }
         else
         {
           resetValue();

           FacesMessages.instance().add(IConstantsRes.SOBENHAN_NOTFOUND, new Object[0]);
           log.info("khong tim thay sobenhan");
         }
         log.info("----hoSoBenhAn_temp-:" + hoSoBenhAn_temp);
       }
       else
       {
         resetValue();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR displayInfo=======" + e);
       e.printStackTrace();
     }
     log.info("End displayInfo=======");
   }

   public void onblurMaKhoaAction()
   {
     log.info("-----BEGIN onblurMaKhoaAction()-----");
     if ((this.khoaDangDt != null) && (this.khoaDangDt.getDmkhoaMa() != null))
     {
       String maKhoa = this.khoaDangDt.getDmkhoaMa();
       if (this.hmDmKhoaNTAll != null)
       {
         DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNTAll.get(maKhoa.toUpperCase());
         if (dmKhoa != null)
         {
           this.khoaDangDt.setDmkhoaMaso(dmKhoa.getDmkhoaMaso());
           this.khoaDangDt.setDmkhoaMa(dmKhoa.getDmkhoaMa());
           this.khoaDangDt.setDmkhoaTen(dmKhoa.getDmkhoaTen());
           log.info("Tim ma khoa: Da thay khoa " + this.khoaDangDt.getDmkhoaTen());
         }
         else
         {
           this.khoaDangDt = new DmKhoa();
           return;
         }
       }
       this.tangChuyenDen = new DmTang();
       refreshDmTang();
     }
     log.info("-----END onblurMaKhoaAction()-----");
   }

   public void onblurTenKhoaAction()
   {
     log.info("-----BEGIN onblurTenKhoaAction()-----");
     if ((this.khoaDangDt != null) && (this.khoaDangDt.getDmkhoaTen() != null))
     {
       String tenKhoa = this.khoaDangDt.getDmkhoaTen();
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
         this.khoaDangDt.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
         this.khoaDangDt.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
         this.khoaDangDt.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
       }
       else
       {
         this.khoaDangDt = new DmKhoa();
         return;
       }
       this.tangChuyenDen = new DmTang();
       refreshDmTang();
     }
     log.info("-----END onblurTenKhoaAction()-----");
   }

   public void refreshDmKhoaNT()
   {
     this.dmKhoaDel = DmKhoaDelegate.getInstance();
     this.listDmKhoaNTAll.clear();
     this.listDmKhoaNTs.clear();
     this.listDmKhoaNTAll = this.dmKhoaDel.getKhoaNT();
     this.hmDmKhoaNTAll.clear();
     for (DmKhoa o : this.listDmKhoaNTAll) {
       this.hmDmKhoaNTAll.put(o.getDmkhoaMa(), o);
     }
     for (DmKhoa each : this.listDmKhoaNTAll) {
       this.listDmKhoaNTs.add(new SelectItem(each.getDmkhoaTen()));
     }
   }

   public void onblurTenTangAction()
   {
     if ((this.tangChuyenDen != null) && (this.tangChuyenDen.getDmtangTen() != null))
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       List<DmTang> lstTangs = dtUtil.findTenLike("DmTang", "dmtangTen", this.tangChuyenDen.getDmtangTen());
       if ((lstTangs != null) && (lstTangs.size() > 0)) {
         this.tangChuyenDen = ((DmTang)lstTangs.get(0));
       } else {
         this.tangChuyenDen = new DmTang();
       }
     }
   }

   public void refreshDmTang()
   {
     this.listDmTangs = new ArrayList();
     if ((this.khoaDangDt != null) && (this.khoaDangDt.getDmkhoaMaso() != null))
     {
       String khoaMa = this.khoaDangDt.getDmkhoaMa();
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();

       List<DmTang> lstDmTangs = dtUtil.findMaLike("DmTang", "dmkhoaMaso.dmkhoaMa", "dmtangChon", khoaMa, true);
       if ((lstDmTangs != null) && (lstDmTangs.size() > 0))
       {
         for (DmTang dmTang : lstDmTangs) {
           this.listDmTangs.add(new SelectItem(dmTang.getDmtangTen()));
         }
         for (DmTang dmTang : lstDmTangs) {
           if (dmTang.getDmtangDefault().booleanValue() == true)
           {
             this.tangChuyenDen = dmTang;
             break;
           }
         }
       }
     }
   }

   public void ghiNhan()
   {
     log.info("ghiNhan()");

     HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
     if ((!this.daCheckTrungBN) && ((this.hoSoBenhAn.getHsbaSovaovien() == null) || (this.hoSoBenhAn.getHsbaSovaovien().trim().equals(""))))
     {
       String hotenBN_Tmp = "";
       String khoaVaoVien_Tmp = "";
       String khoaDangDT_Tmp = "";
       String ngayGioVaoVien_Tmp = "";
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
       Hsba hsbaTmp = null;
       if ((this.hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")) && (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO")))
       {
         List<Hsba> listHsba = hsbaDelegate.findBySoTheBHYT(this.hsbaBHYT.getHsbabhytSothebh());
         if ((listHsba != null) && (listHsba.size() > 0)) {
           for (Hsba eachHsba : listHsba) {
             if (eachHsba.getHsbaNgaygiorav() == null)
             {
               hsbaTmp = eachHsba;
               break;
             }
           }
         }
       }
       else
       {
         hsbaTmp = hsbaDelegate.findByThongTinBenhNhan(this.benhNhan.getBenhnhanHoten(), this.gioi.equals("r1") ? new Integer("2") : new Integer("1"), this.benhNhan.getBenhnhanNamsinh(), this.gioVaoVien, this.ngayVaoVien);
         log.info("after findByThongTinBenhNhan, hsbaTmp = " + hsbaTmp);
       }
       if (hsbaTmp != null)
       {
         this.trungBN = true;
         try
         {
           hotenBN_Tmp = hsbaTmp.getBenhnhanMa().getBenhnhanHoten();
         }
         catch (Exception e) {}
         try
         {
           khoaVaoVien_Tmp = hsbaTmp.getHsbaKhoavaov().getDmkhoaTen();
         }
         catch (Exception e) {}
         try
         {
           khoaDangDT_Tmp = hsbaTmp.getHsbaKhoadangdt().getDmkhoaTen();
         }
         catch (Exception e) {}
         try
         {
           ngayGioVaoVien_Tmp = sdf.format(hsbaTmp.getHsbaNgaygiovaov());
         }
         catch (Exception e) {}
       }
       this.daCheckTrungBN = true;
       this.strMsgTrungBN = "";
       if (this.trungBN)
       {
         if ((this.hoSoBenhAn.getDoituongMa(true).getDmdoituongMa().equals("BH")) && (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO"))) {
           this.strMsgTrungBN = ("Bệnh nhân có số thẻ BHYT " + this.hsbaBHYT.getHsbabhytSothebh() + "(" + hotenBN_Tmp + ")" + " đã nhập viện lúc " + ngayGioVaoVien_Tmp + " vào khoa " + khoaVaoVien_Tmp + ", hiện đang nằm ở khoa " + khoaDangDT_Tmp + ". Có tiếp tục nhập không?");
         } else {
           this.strMsgTrungBN = ("Bệnh nhân " + hotenBN_Tmp + " đã nhập viện lúc " + ngayGioVaoVien_Tmp + " vào khoa " + khoaVaoVien_Tmp + ", hiện đang nằm ở khoa " + khoaDangDT_Tmp + ". Có tiếp tục nhập không?");
         }
         return;
       }
     }
     if (this.dangGhinhan == true)
     {
       log.info("He thong dang Ghi nhan thong tin !!");
       return;
     }
     this.dangGhinhan = true;
     log.info("########################### He thong dang Ghi nhan thong tin !!" + this.dangGhinhan);
     try
     {
       this.daCheckTrungBN = false;
       this.trungBN = false;
       this.strMsgTrungBN = "";
       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       String maDoituong_New = this.hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
       log.info("sobenhan: " + this.hoSoBenhAn);
       if ((this.gioVaoVien != null) && (!this.gioVaoVien.equals("")) && (this.ngayVaoVien != null) && (!this.ngayVaoVien.equals("")))
       {
         Calendar hsbaNgaygiovaov = Utils.getDBDate(this.gioVaoVien, this.ngayVaoVien);
         if (hsbaNgaygiovaov != null)
         {
           this.hoSoBenhAn.setHsbaNgaygiovaov(hsbaNgaygiovaov.getTime());
         }
         else
         {
           log.info(" hsbaNgaygiovaov : NULL . LAY NGAY GIO HE THONG");
           this.hoSoBenhAn.setHsbaNgaygiovaov(new Date());
         }
       }
       else
       {
         this.hoSoBenhAn.setHsbaNgaygiovaov(new Date());
       }
       if ((this.ngaySinh != null) && (!this.ngaySinh.equals(""))) {
         this.benhNhan.setBenhnhanNgaysinh(Utils.getDBDate("00:00", this.ngaySinh).getTime());
       } else {
         this.benhNhan.setBenhnhanNgaysinh(null);
       }
       log.info("gioi : " + this.gioi);
       if ((this.gioi == null) || (this.gioi.equals("")))
       {
         this.benhNhan.setDmgtMaso(null);
       }
       else if ("r1".equals(this.gioi))
       {
         DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
         DmGioiTinh gioiTinh = (DmGioiTinh)dele.findByMa("1", "DmGioiTinh", "dmgtMa");
         this.benhNhan.setDmgtMaso(gioiTinh);
       }
       else
       {
         DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
         DmGioiTinh gioiTinh = (DmGioiTinh)dele.findByMa("0", "DmGioiTinh", "dmgtMa");
         this.benhNhan.setDmgtMaso(gioiTinh);
       }
       SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
       if ((this.giatri1 != null) && (!this.giatri1.equals("")))
       {
         Date dGiaTri1 = formatter.parse(this.giatri1);
         this.hsbaBHYT.setHsbabhytGiatri0(dGiaTri1);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytGiatri0(null);
       }
       if ((this.giatri2 != null) && (!this.giatri2.equals("")))
       {
         Date dGiaTri2 = formatter.parse(this.giatri2);
         this.hsbaBHYT.setHsbabhytGiatri1(dGiaTri2);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytGiatri1(null);
       }
       if ((this.giatri3 != null) && (!this.giatri3.equals("")))
       {
         Date dGiaTri3 = formatter.parse(this.giatri3);
         this.hsbaBHYT.setHsbabhytGiatri2(dGiaTri3);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytGiatri2(null);
       }
       if ((this.giatri4 != null) && (!this.giatri4.equals("")))
       {
         Date dGiaTri4 = formatter.parse(this.giatri4);
         this.hsbaBHYT.setHsbabhytGiatri3(dGiaTri4);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytGiatri3(null);
       }
       if ((this.moc1 != null) && (!this.moc1.equals("")))
       {
         Date dmoc1 = formatter.parse(this.moc1);
         this.hsbaBHYT.setHsbabhytMoc1(dmoc1);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytMoc1(null);
       }
       if ((this.moc2 != null) && (!this.moc2.equals("")))
       {
         Date dmoc2 = formatter.parse(this.moc2);
         this.hsbaBHYT.setHsbabhytMoc2(dmoc2);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytMoc2(null);
       }
       if ((this.moc3 != null) && (!this.moc3.equals("")))
       {
         Date dmoc3 = formatter.parse(this.moc3);
         this.hsbaBHYT.setHsbabhytMoc3(dmoc3);
       }
       else
       {
         this.hsbaBHYT.setHsbabhytMoc3(null);
       }
       DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
       if (maDoituong_New.equalsIgnoreCase("BH")) {
         if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMaso() != null)
         {
           DmBenhVien noiKCB = (DmBenhVien)dele.findByMa(this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa(), "DmBenhVien", "dmbenhvienMa");
           if (noiKCB == null)
           {
             FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.NOI_DK_KCB, this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa() });
             this.dangGhinhan = false;
             return;
           }
           this.hsbaBHYT.setHsbabhytMakcb(noiKCB);
         }
         else
         {
           this.dangGhinhan = false;
           FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.NOI_DK_KCB });
           return;
         }
       }
       Short tuyenBH = new Short("1");
       if ((this.hsbaBHYT.getHsbabhytCoGiayChuyenVien() != null) && (this.hsbaBHYT.getHsbabhytCoGiayChuyenVien().booleanValue())) {
         tuyenBH = new Short("1");
       } else if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().equalsIgnoreCase(IConstantsRes.MA_BENH_VIEN)) {
         tuyenBH = new Short("1");
       } else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH"))
       {
         if (this.hsbaBHYT.getHsbabhytMakcb(true).getDmbenhvienMa().startsWith(IConstantsRes.TINH_BHYT_DEFAULT)) {
           tuyenBH = new Short("2");
         } else {
           tuyenBH = new Short("3");
         }
       }
       else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN"))
       {
         if ((this.hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso() != null) && (this.hsbaBHYT.getHsbabhytMakcb(true).getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI))) {
           tuyenBH = new Short("2");
         } else {
           tuyenBH = new Short("3");
         }
       }
       else {
         tuyenBH = new Short("3");
       }
       if ((this.hoSoBenhAn.getHsbaCapcuu() != null) && (this.hoSoBenhAn.getHsbaCapcuu().booleanValue())) {
         tuyenBH = new Short("1");
       }
       this.hsbaBHYT.setHsbabhytTuyen(tuyenBH);


       this.hoSoBenhAn.setHsbaNgaygiocn(Calendar.getInstance().getTime());
       if (maDoituong_New.equalsIgnoreCase("BH")) {
         if (this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso() != null)
         {
           DmTinh tinh = (DmTinh)dele.findByMaSo(this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhMaso(), "DmTinh", "dmtinhMaso");
           if (tinh != null)
           {
             this.hsbaBHYT.setHsbabhytTinhbh(tinh);
           }
           else
           {
             FacesMessages.instance().add(IConstantsRes.NOT_FOUND, new Object[] { IConstantsRes.TINH_CAP_BHYT, this.hsbaBHYT.getHsbabhytTinhbh(true).getDmtinhBHYT() });
             this.dangGhinhan = false;
             return;
           }
         }
         else
         {
           this.dangGhinhan = false;
           FacesMessages.instance().add(IConstantsRes.BAT_BUOC_NHAP, new Object[] { IConstantsRes.TINH_CAP_BHYT });
           return;
         }
       }
       this.hoSoBenhAn.setHsbaKhoadangdt(this.khoaDangDt);

       RemoveUtil.removeAllNullFromHSBA(this.hoSoBenhAn);
       RemoveUtil.removeAllNullFromBN(this.benhNhan);
       RemoveUtil.removeAllNullFromHSBABHYT(this.hsbaBHYT);

       String sKetQua = hsbaDelegate.capNhatThongTinNhapVien(this.hoSoBenhAn, this.hsbaBHYT, this.benhNhan, this.tangChuyenDen);
       if (sKetQua.equals("error"))
       {
         FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
         return;
       }
       if ((sba != null) && (sba.trim().equals(""))) {
         FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS + ": " + sKetQua, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS + ": " + sKetQua, new Object[0]);
       }
       this.hoSoBenhAn.setHsbaSovaovien(sKetQua);

       this.resultHidden = "success";

       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
       if (hsbaBhytLast != null)
       {
         this.hsbaBHYT = hsbaBhytLast;
         SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);
       }
       else
       {
         this.hsbaBHYT = new HsbaBhyt();
         SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);
       }
       TonKhoDelegate tkDel;
       ThuocNoiTruDelegate tntDel;
       if ((sba != null) && (!sba.trim().equals("")))
       {
         log.info("maDoituong_Old = " + this.maDoituong_Old + ", maDoituong_New = " + maDoituong_New);
         if (!this.maDoituong_Old.equals(maDoituong_New))
         {
           this.maDoituong_Old = maDoituong_New;
           Date ngayBatDauBh = null;
           Date ngayHetHanBh = null;
           if ((this.giatri3 != null) && (!this.giatri3.equals(""))) {
             ngayBatDauBh = formatter.parse(this.giatri3);
           } else if ((this.giatri1 != null) && (!this.giatri1.equals(""))) {
             ngayBatDauBh = formatter.parse(this.giatri1);
           }
           if ((this.giatri4 != null) && (!this.giatri4.equals(""))) {
             ngayHetHanBh = formatter.parse(this.giatri4);
           } else if ((this.giatri2 != null) && (!this.giatri2.equals(""))) {
             ngayHetHanBh = formatter.parse(this.giatri2);
           }
           ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();
           List<ClsMo> listClsMo = clsMoDelegate.findBySoVaoVien(sba);
           for (ClsMo cls : listClsMo) {
             if (((cls.getClsmoKhongthu() == null) || (cls.getClsmoKhongthu().booleanValue() != true)) &&



               ((cls.getClsmoYeucau() == null) || (cls.getClsmoYeucau().booleanValue() != true)) && (



               (cls.getClsmoMien() == null) || (cls.getClsmoMien().booleanValue() != true)))
             {
               if (maDoituong_New.equalsIgnoreCase("BH"))
               {
                 Date ngayChidinhCLS = formatter.parse(formatter.format(cls.getClsmoNgay()));
                 if (((cls.getClsmoNDM() != null) && (cls.getClsmoNDM().booleanValue() == true)) || (ngayBatDauBh == null) || (ngayHetHanBh == null) || (ngayChidinhCLS.before(ngayBatDauBh)) || (ngayChidinhCLS.after(ngayHetHanBh))) {
                   cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
                 } else {
                   cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiabh());
                 }
               }
               else if (maDoituong_New.equalsIgnoreCase("MP"))
               {
                 cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongiamp());
               }
               else
               {
                 cls.setClsmoDongia(cls.getClsmoMahang().getDtdmclsbgDongia());
               }
               cls.setClsmoDongiabh(cls.getClsmoMahang().getDtdmclsbgDongiabh());
               clsMoDelegate.edit(cls);
             }
           }
           tkDel = TonKhoDelegate.getInstance();
           List<ThuocNoiTru> listTnt = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(sba);
           if ((listTnt != null) && (listTnt.size() > 0))
           {
             tntDel = ThuocNoiTruDelegate.getInstance();
             for (ThuocNoiTru eachTnt : listTnt)
             {
               TonKho tonKho = tkDel.findTonkhoByMalienket(eachTnt.getThuocnoitruMalk());
               if (maDoituong_New.equalsIgnoreCase("MP"))
               {
                 if ((eachTnt.getThuocnoitruYeucau() != null) && (eachTnt.getThuocnoitruYeucau().booleanValue() == true)) {
                   eachTnt.setThuocnoitruDongia(tonKho.getTonkhoDongia());
                 } else {
                   eachTnt.setThuocnoitruDongia(new Double(0.0D));
                 }
               }
               else if ((eachTnt.getThuocnoitruKhongthu() != null) && (eachTnt.getThuocnoitruKhongthu().booleanValue() == true)) {
                 eachTnt.setThuocnoitruDongia(new Double(0.0D));
               } else {
                 eachTnt.setThuocnoitruDongia(tonKho.getTonkhoDongia());
               }
               if (eachTnt.getThuocnoitruMaPhieuDT() == null) {
                 eachTnt.setDmdoituongMaso(this.hoSoBenhAn.getDoituongMa());
               }
               tntDel.edit(eachTnt);
             }
           }
         }
       }
       resetValue();
     }
     catch (Exception e)
     {
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       this.resultHidden = "fail";
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   int index = 1;

   public void XuatReport()
   {
     this.loaiBCDT = "giaykhambenhvaovien";
     log.info("Vao Method XuatReport giaykhambenhvaovien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/giaykhambenhvaovien.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
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

       log.info("================= benhNhan.getDmgtMaso(true).getDmgtTen():" + this.benhNhan.getDmgtMaso(true).getDmgtTen());

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
         params.put("MaDanToc", "_");
       }
       params.put("DiaChi", this.benhNhan.getBenhnhanDiachi());

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
         params.put("MaNgoaiKieu", "_");
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

       String doiTuongMa = this.hoSoBenhAn.getDoituongMa(true).getDmdoituongMa();
       if ((doiTuongMa != null) && (doiTuongMa.equals("BH")))
       {
         params.put("DoiTuong_BHYT", "X");
         params.put("DoiTuong_ThuPhi", "_");
         params.put("DoiTuong_Mien", "_");
         params.put("DoiTuong_Khac", "_");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("TP")))
       {
         params.put("DoiTuong_BHYT", "_");
         params.put("DoiTuong_ThuPhi", "X");
         params.put("DoiTuong_Mien", "_");
         params.put("DoiTuong_Khac", "_");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("MP")))
       {
         params.put("DoiTuong_BHYT", "_");
         params.put("DoiTuong_ThuPhi", "_");
         params.put("DoiTuong_Mien", "X");
         params.put("DoiTuong_Khac", "_");
       }
       else
       {
         params.put("DoiTuong_BHYT", "_");
         params.put("DoiTuong_ThuPhi", "_");
         params.put("DoiTuong_Mien", "_");
         params.put("DoiTuong_Khac", "X");
       }
       if ((this.hsbaBHYT.getHsbabhytCoquanbh() != null) && (!this.hsbaBHYT.getHsbabhytCoquanbh().equals(""))) {
         params.put("NoiLamViec", this.hsbaBHYT.getHsbabhytCoquanbh());
       } else {
         params.put("NoiLamViec", "");
       }
       params.put("ThoiHanBaoHiem", this.hsbaBHYT.getHsbabhytGiatri1());
       if ((this.hsbaBHYT.getHsbabhytSothebh() != null) && (!this.hsbaBHYT.getHsbabhytSothebh().equals("")))
       {
         if (this.hsbaBHYT.getHsbabhytKhoibh() != null) {
           params.put("SoTheBHYT", this.hsbaBHYT.getHsbabhytSothebh() + " - " + this.hsbaBHYT.getHsbabhytKhoibh().getDtdmkhoibhytMa());
         } else {
           params.put("SoTheBHYT", this.hsbaBHYT.getHsbabhytSothebh());
         }
       }
       else {
         params.put("SoTheBHYT", "");
       }
       params.put("NguoiBaoTin", "");

       params.put("NgayGioVaoVien", this.hoSoBenhAn.getHsbaNgaygiovaov());

       String diengiaitt = this.hoSoBenhAn.getHsbaDiengiaituyent();
       if (diengiaitt == null) {
         diengiaitt = "";
       }
       DmBenhIcd benhtuyentruoc = (DmBenhIcd)dele.findByMa(this.hoSoBenhAn.getHsbaMachdoantuyent(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
       if (benhtuyentruoc != null)
       {
         params.put("ChanDoanTuyenTruoc", benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt);
       }
       else
       {
         if (diengiaitt.equals("")) {
           diengiaitt = "";
         }
         params.put("ChanDoanTuyenTruoc", diengiaitt);
       }
       params.put("SoVaoVien", this.hoSoBenhAn.getHsbaSovaovien());

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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "giaykhambenhvaovien");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   private boolean checkTrungMaBnInListBn(String maBN, List<BenhNhanTrungTheBhytDTO> listBN)
   {
     if (listBN == null) {
       return false;
     }
     if (listBN.size() < 1) {
       return false;
     }
     for (BenhNhanTrungTheBhytDTO eachBN : listBN) {
       if (eachBN.getBnMa().equals(maBN)) {
         return true;
       }
     }
     return false;
   }

   public void checkSoTheBHYT()
   {
     String sothe = this.hsbaBHYT.getHsbabhytSothebh();
     if (sothe.equals("")) {
       return;
     }
     this.showListBn = false;

     List<TiepDon> listTD = TiepDonDelegate.getInstance().findBySoTheBHYT(sothe);
     if ((listTD != null) && (listTD.size() > 0))
     {
       if (IConstantsRes.CHO_PHEP_TRUNG_SO_THE_BHYT.equals("NO"))
       {
         Hsba hoSoBenhAn_temp = HsbaDelegate.getInstance().findByTiepDonMa(((TiepDon)listTD.get(0)).getTiepdonMa());
         if (hoSoBenhAn_temp != null)
         {
           this.hoSoBenhAn = hoSoBenhAn_temp;
           SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);
           this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
           SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
           if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
             this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
           }
           SetInforUtil.setInforIfNullForBN(this.benhNhan);
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
           HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
           HsbaBhyt hsbaBhytLast = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
           if (hsbaBhytLast != null)
           {
             this.hsbaBHYT = hsbaBhytLast;
             SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);
           }
           this.hoSoBenhAn.setHsbaSovaovien(null);


           this.gioVaoVien = Utils.getGioPhut(new Date());
           this.ngayVaoVien = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
           this.gioRaVien = "";
           this.ngayRaVien = "";

           this.hoSoBenhAn.setHsbaNgaygiovaov(null);
           this.hoSoBenhAn.setHsbaNgaygiorav(null);
           this.hoSoBenhAn.setHsbaType(null);
           this.hoSoBenhAn.setHsbaIsNoitru(Boolean.valueOf(true));
           this.hoSoBenhAn.setHsbaNgaygiotv(null);
           this.hoSoBenhAn.setHsbaNgaygiocn(new Date());

           this.hoSoBenhAn.setHsbaCapcuu(null);
           this.hoSoBenhAn.setHsbaRuoubia(null);
           this.hoSoBenhAn.setHsbaKetqua(null);
           this.hoSoBenhAn.setHsbaTuvong(null);
           this.hoSoBenhAn.setHsbaTuvvong24g(null);

           this.hoSoBenhAn.setTainanMa(null);
           this.hoSoBenhAn.setDmptgtnMaso(null);
           this.hoSoBenhAn.setHsbaNguyennhan(null);
           this.hoSoBenhAn.setDiadiemMa(null);

           this.hoSoBenhAn.setHsbaDonvigoi(null);
           this.hoSoBenhAn.setHsbaMachdoantuyent(null);
           this.hoSoBenhAn.setHsbaDiengiaituyent(null);
           this.hoSoBenhAn.setHsbaMachdoanbd(null);
           this.hoSoBenhAn.setHsbaDiengiaibd(null);
           if (this.hoSoBenhAn.getHsbaKhoadangdt() != null)
           {
             this.hoSoBenhAn.setHsbaKhoadangdtCm(this.hoSoBenhAn.getHsbaKhoadangdt());
             this.hoSoBenhAn.setHsbaKhoavaov(this.hoSoBenhAn.getHsbaKhoadangdt());
           }
           else
           {
             this.hoSoBenhAn.setHsbaKhoadangdtCm(null);
             this.hoSoBenhAn.setHsbaKhoavaov(null);
           }
           this.hoSoBenhAn.setHsbaKhoarav(null);
           this.hoSoBenhAn.setHsbaMachdravien(null);
           this.hoSoBenhAn.setHsbaYeuCau(null);


           setOtherInfor();
         }
       }
       else
       {
         if (this.listBN == null) {
           this.listBN = new ArrayList();
         } else {
           this.listBN.clear();
         }
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
         for (TiepDon eachTD : listTD) {
           if (eachTD.getBenhnhanMa() != null) {
             if (!checkTrungMaBnInListBn(eachTD.getBenhnhanMa().getBenhnhanMa(), this.listBN))
             {
               BenhNhanTrungTheBhytDTO bn = new BenhNhanTrungTheBhytDTO();
               bn.setTiepdonMa(eachTD.getTiepdonMa());
               bn.setBnMa(eachTD.getBenhnhanMa().getBenhnhanMa());
               bn.setBnHoTen(eachTD.getBenhnhanMa().getBenhnhanHoten());
               bn.setBnGioiTinh(eachTD.getBenhnhanMa().getDmgtMaso().getDmgtTen());
               bn.setBnNamSinh(eachTD.getBenhnhanMa().getBenhnhanNamsinh());
               bn.setNoiDkKcb(eachTD.getKcbbhytMa() == null ? "" : eachTD.getKcbbhytMa().getDmbenhvienTen());
               bn.setThoiHanThe((eachTD.getTiepdonGiatri1() == null ? "" : formatter.format(eachTD.getTiepdonGiatri1())) + " - " + (eachTD.getTiepdonGiatri2() == null ? "" : formatter.format(eachTD.getTiepdonGiatri2())));
               this.listBN.add(bn);
             }
           }
         }
         if (this.listBN.size() > 0) {
           this.showListBn = true;
         }
       }
       FacesMessages.instance().add(IConstantsRes.SOTHEBHYT_DATONTAI, new Object[] { sothe });
     }
   }

   public void nhapBnMoi()
   {
     FacesMessages.instance().clear();


     this.showListBn = false;
   }

   public void layThongTinBnCu(int index)
   {
     log.info("Begin layThongTinBnCu, index = " + index + "hoSoBenhAn.getHsbaSovaovien() = " + this.hoSoBenhAn.getHsbaSovaovien());
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     Hsba hoSoBenhAn_temp = HsbaDelegate.getInstance().findByTiepDonMa(((BenhNhanTrungTheBhytDTO)this.listBN.get(index)).getTiepdonMa());
     log.info("hoSoBenhAn_temp = " + hoSoBenhAn_temp);
     if (hoSoBenhAn_temp != null)
     {
       String sba = this.hoSoBenhAn.getHsbaSovaovien();
       this.hoSoBenhAn = hoSoBenhAn_temp;
       log.info("hoSoBenhAn = " + this.hoSoBenhAn);
       HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
       HsbaBhyt hsbaBhytLast_Old = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
       log.info("hsbaBhytLast_Old = " + hsbaBhytLast_Old);
       this.hoSoBenhAn.setHsbaSovaovien(sba);
       SetInforUtil.setInforIfNullForHSBA(this.hoSoBenhAn);
       this.benhNhan = this.hoSoBenhAn.getBenhnhanMa();
       if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
         this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
       }
       SetInforUtil.setInforIfNullForBN(this.benhNhan);
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
       HsbaBhyt hsbaBhytLast_New = hsbaBhytDelegate.findBySoVaoVienLastHsbaBhyt(this.hoSoBenhAn.getHsbaSovaovien());
       log.info("hsbaBhytLast_New = " + hsbaBhytLast_New);
       if (hsbaBhytLast_New != null)
       {
         this.hsbaBHYT = hsbaBhytLast_New;
         SetInforUtil.setInforIfNullForHSBABHYT(this.hsbaBHYT);
       }
       else if (hsbaBhytLast_Old != null)
       {
         this.hsbaBHYT.setHsbabhytGiatri0(hsbaBhytLast_Old.getHsbabhytGiatri0());
         this.hsbaBHYT.setHsbabhytGiatri1(hsbaBhytLast_Old.getHsbabhytGiatri1());
         this.hsbaBHYT.setHsbabhytGiatri2(hsbaBhytLast_Old.getHsbabhytGiatri2());
         this.hsbaBHYT.setHsbabhytGiatri3(hsbaBhytLast_Old.getHsbabhytGiatri3());
         this.hsbaBHYT.setHsbabhytMoc1(hsbaBhytLast_Old.getHsbabhytMoc1());
         this.hsbaBHYT.setHsbabhytMoc2(hsbaBhytLast_Old.getHsbabhytMoc2());
         this.hsbaBHYT.setHsbabhytMoc3(hsbaBhytLast_Old.getHsbabhytMoc3());
       }
       this.hoSoBenhAn.setHsbaSovaovien(null);


       this.gioVaoVien = Utils.getGioPhut(new Date());
       this.ngayVaoVien = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
       this.gioRaVien = "";
       this.ngayRaVien = "";

       this.hoSoBenhAn.setHsbaNgaygiovaov(null);
       this.hoSoBenhAn.setHsbaNgaygiorav(null);
       this.hoSoBenhAn.setHsbaType(null);
       this.hoSoBenhAn.setHsbaIsNoitru(Boolean.valueOf(true));
       this.hoSoBenhAn.setHsbaNgaygiotv(null);
       this.hoSoBenhAn.setHsbaNgaygiocn(new Date());

       this.hoSoBenhAn.setHsbaCapcuu(null);
       this.hoSoBenhAn.setHsbaRuoubia(null);
       this.hoSoBenhAn.setHsbaKetqua(null);
       this.hoSoBenhAn.setHsbaTuvong(null);
       this.hoSoBenhAn.setHsbaTuvvong24g(null);

       this.hoSoBenhAn.setTainanMa(null);
       this.hoSoBenhAn.setDmptgtnMaso(null);
       this.hoSoBenhAn.setHsbaNguyennhan(null);
       this.hoSoBenhAn.setDiadiemMa(null);

       this.hoSoBenhAn.setHsbaDonvigoi(null);
       this.hoSoBenhAn.setHsbaMachdoantuyent(null);
       this.hoSoBenhAn.setHsbaDiengiaituyent(null);
       this.hoSoBenhAn.setHsbaMachdoanbd(null);
       this.hoSoBenhAn.setHsbaDiengiaibd(null);
       if (this.hoSoBenhAn.getHsbaKhoadangdt() != null)
       {
         this.hoSoBenhAn.setHsbaKhoadangdtCm(this.hoSoBenhAn.getHsbaKhoadangdt());
         this.hoSoBenhAn.setHsbaKhoavaov(this.hoSoBenhAn.getHsbaKhoadangdt());
       }
       else
       {
         this.hoSoBenhAn.setHsbaKhoadangdtCm(null);
         this.hoSoBenhAn.setHsbaKhoavaov(null);
       }
       this.hoSoBenhAn.setHsbaKhoarav(null);
       this.hoSoBenhAn.setHsbaMachdravien(null);
       this.hoSoBenhAn.setHsbaYeuCau(null);


       setOtherInfor();
     }
     else
     {
       TiepDon tiepdon = TiepDonDelegate.getInstance().findBenhNhanByTiepdonMa(((BenhNhanTrungTheBhytDTO)this.listBN.get(index)).getTiepdonMa());
       this.benhNhan = tiepdon.getBenhnhanMa();
       if ((this.benhNhan.getBenhnhanNgaysinh() != null) && (!this.benhNhan.getBenhnhanNgaysinh().equals(""))) {
         this.ngaySinh = formatter.format(Long.valueOf(this.benhNhan.getBenhnhanNgaysinh().getTime()));
       }
       SetInforUtil.setInforIfNullForBN(this.benhNhan);
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
       this.hsbaBHYT.setHsbabhytGiatri0(tiepdon.getTiepdonGiatri1());
       this.hsbaBHYT.setHsbabhytGiatri1(tiepdon.getTiepdonGiatri2());
       this.hsbaBHYT.setHsbabhytGiatri2(tiepdon.getTiepdonGiatri3());
       this.hsbaBHYT.setHsbabhytGiatri3(tiepdon.getTiepdonGiatri4());
       this.hsbaBHYT.setHsbabhytMoc1(tiepdon.getTiepdonMoc1());
       this.hsbaBHYT.setHsbabhytMoc2(tiepdon.getTiepdonMoc2());
       this.hsbaBHYT.setHsbabhytMoc3(tiepdon.getTiepdonMoc3());
       setOtherInfor();
     }
     FacesMessages.instance().clear();
     this.showListBn = false;
   }

   public String getMsgFail()
   {
     return this.msgFail;
   }

   public void setMsgFail(String msgFail)
   {
     this.msgFail = msgFail;
   }

   public String getMsgSuccess()
   {
     return this.msgSuccess;
   }

   public void setMsgSuccess(String msgSuccess)
   {
     this.msgSuccess = msgSuccess;
   }

   public void nhaplai()
     throws Exception
   {
     log.info("nhaplai()");
     resetValue();
   }

   public static void main(String[] args) {}

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

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public String getGioVaoVien()
   {
     return this.gioVaoVien;
   }

   public void setGioVaoVien(String gioVaoVien)
   {
     this.gioVaoVien = gioVaoVien;
   }

   public String getNgayVaoVien()
   {
     return this.ngayVaoVien;
   }

   public void setNgayVaoVien(String ngayVaoVien)
   {
     this.ngayVaoVien = ngayVaoVien;
   }

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

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

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getGiatri1()
   {
     return this.giatri1;
   }

   public void setGiatri1(String giatri1)
   {
     this.giatri1 = giatri1;
   }

   public String getGiatri2()
   {
     return this.giatri2;
   }

   public void setGiatri2(String giatri2)
   {
     this.giatri2 = giatri2;
   }

   public String getGiatri3()
   {
     return this.giatri3;
   }

   public void setGiatri3(String giatri3)
   {
     this.giatri3 = giatri3;
   }

   public String getGiatri4()
   {
     return this.giatri4;
   }

   public void setGiatri4(String giatri4)
   {
     this.giatri4 = giatri4;
   }

   public String getMoc1()
   {
     return this.moc1;
   }

   public void setMoc1(String moc1)
   {
     this.moc1 = moc1;
   }

   public String getMoc2()
   {
     return this.moc2;
   }

   public void setMoc2(String moc2)
   {
     this.moc2 = moc2;
   }

   public String getMoc3()
   {
     return this.moc3;
   }

   public void setMoc3(String moc3)
   {
     this.moc3 = moc3;
   }

   public String getLockChuyenKhoa()
   {
     return this.lockChuyenKhoa;
   }

   public void setLockChuyenKhoa(String lockChuyenKhoa)
   {
     this.lockChuyenKhoa = lockChuyenKhoa;
   }

   public boolean isDisabledGhinhan()
   {
     return this.disabledGhinhan;
   }

   public void setDisabledGhinhan(boolean disabledGhinhan)
   {
     this.disabledGhinhan = disabledGhinhan;
   }

   public List<BenhNhanTrungTheBhytDTO> getListBN()
   {
     return this.listBN;
   }

   public void setListBN(List<BenhNhanTrungTheBhytDTO> listBN)
   {
     this.listBN = listBN;
   }

   public boolean isShowListBn()
   {
     return this.showListBn;
   }

   public void setShowListBn(boolean showListBn)
   {
     this.showListBn = showListBn;
   }

   public String getShowMenu()
   {
     return this.showMenu;
   }

   public void setShowMenu(String showMenu)
   {
     this.showMenu = showMenu;
   }

   public String getListMaTinhBhyt()
   {
     return this.listMaTinhBhyt;
   }

   public void setListMaTinhBhyt(String listMaTinhBhyt)
   {
     this.listMaTinhBhyt = listMaTinhBhyt;
   }

   public List<SelectItem> getListDmKhoaNTs()
   {
     return this.listDmKhoaNTs;
   }

   public void setListDmKhoaNTs(List<SelectItem> listDmKhoaNTs)
   {
     this.listDmKhoaNTs = listDmKhoaNTs;
   }

   public DmTang getTangChuyenDen()
   {
     return this.tangChuyenDen;
   }

   public void setTangChuyenDen(DmTang tangChuyenDen)
   {
     this.tangChuyenDen = tangChuyenDen;
   }

   public List<SelectItem> getListDmTangs()
   {
     return this.listDmTangs;
   }

   public void setListDmTangs(List<SelectItem> listDmTangs)
   {
     this.listDmTangs = listDmTangs;
   }

   public DmKhoa getKhoaDangDt()
   {
     return this.khoaDangDt;
   }

   public void setKhoaDangDt(DmKhoa khoaDangDt)
   {
     this.khoaDangDt = khoaDangDt;
   }

   public boolean isLockDoituong()
   {
     return this.lockDoituong;
   }

   public void setLockDoituong(boolean lockDoituong)
   {
     this.lockDoituong = lockDoituong;
   }

   public boolean isDaCheckTrungBN()
   {
     return this.daCheckTrungBN;
   }

   public void setDaCheckTrungBN(boolean daCheckTrungBN)
   {
     this.daCheckTrungBN = daCheckTrungBN;
   }

   public boolean isTrungBN()
   {
     return this.trungBN;
   }

   public void setTrungBN(boolean trungBN)
   {
     this.trungBN = trungBN;
   }

   public String getStrMsgTrungBN()
   {
     return this.strMsgTrungBN;
   }

   public void setStrMsgTrungBN(String strMsgTrungBN)
   {
     this.strMsgTrungBN = strMsgTrungBN;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.CapNhatTTNhapVien

 * JD-Core Version:    0.7.0.1

 */