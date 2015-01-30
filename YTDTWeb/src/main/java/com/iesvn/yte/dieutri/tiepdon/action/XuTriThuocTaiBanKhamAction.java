 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmBanKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.ThamKhamUtil;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
 import net.sf.jasperreports.engine.JasperPrint;
 import org.apache.commons.beanutils.BeanUtils;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B121_3_Xutrithuoctaibankham")
 @Synchronized(timeout=6000000L)
 public class XuTriThuocTaiBanKhamAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String gioThamKham;
   private String mabs;
   private Integer masobs;
   private String tenbs;
   private String chandoan;
   private int sobaithuoc;
   private static Logger log = Logger.getLogger(XuTriThuocTaiBanKhamAction.class);
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String initB121_3_Xutrithuoctaibankham;
   @DataModel
   private List<ThuocPhongKham> ctThuocPhongKhams;
   @DataModel
   private List<ThuocPhongKhamExt> listTPKBanKhamTruoc;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private String returnToThuocYDungCuPhongKham;
   @Out(required=false)
   @In(required=false)
   String fromMenu;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @Out(required=false)
   @In(required=false)
   private String reportPathTD;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD;
   private int index;
   private String tenThuoc;
   private Integer khoaMaSo;
   private String khoaMa;
   private String sLoiDan;
   private String tenLoiDan;
   private String tenLoiDans;
   private String handung;
   private String hamluong;
   private String dmdonvitinhTen;
   private ThamKham thamkham;
   private HashMap<Integer, List> hsmListThuocDYNgoaiTru;
   private Integer thuocdongyMaso;
   @DataModelSelection("ctThuocPhongKhams")
   @Out(required=false)
   private ThuocPhongKham ctThuocPhongKhamSelected;
   private int ctThuocPhongKhamSelectedOld;
   private boolean updateNhapct;
   private ThuocPhongKham ctThuocPhongKham;
   private DmDoiTuong doiTuong;
   private Double tonTong;
   private List<TonKho> listTonKhoHienTai;
   private String checkKiemKe;
   @In(required=false)
   @Out(required=false)
   private String loaiToaThuocThamKhamVaXuTri;
   private String loaiToaThuocOld;
   private String titleThamKhamVaXuTri;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   private String maFinish;
   private Integer dmBaithuocMaso;
   private String dmBaithuocMa;
   private DmThuocDelegate dmThuocDelegate;
   private List<SelectItem> listDmThuocs;
   private List<DmThuoc> listDmThuocAll;
   private HashMap<String, DmThuoc> hmDmThuocAll;
   private String searchType;
   private List<ThuocPhongKham> listCtTPK_temp;
   private List<ClsKham> clslist;
   private Set<Integer> rowsToUpdate;
   private String maChiDan;
   private String tenChiDan;
   private DtDmNhanVien nhanVienCN;
   private Boolean hienThiInPhieu;

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
     if ((this.ctThuocPhongKham != null) && (this.ctThuocPhongKham.getThuocphongkhamMathuoc(true) != null))
     {
       String maThuoc = this.ctThuocPhongKham.getThuocphongkhamMathuoc(true).getDmthuocMa();
       if (this.hmDmThuocAll != null)
       {
         DmThuoc dmThuoc1 = (DmThuoc)this.hmDmThuocAll.get(maThuoc.toUpperCase());
         if (dmThuoc1 != null)
         {
           this.ctThuocPhongKham = new ThuocPhongKham();
           DmThuoc dmThuoc2 = this.dmThuocDelegate.findByMaThuoc(maThuoc.toUpperCase());
           this.ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc2);
           this.ctThuocPhongKham.setThuocphongkhamMien(dmThuoc2.getDmthuocMien());
           this.ctThuocPhongKham.setThuocphongkhamNdm(Boolean.valueOf(!dmThuoc2.getDmthuocIndanhmuc().booleanValue()));
           this.ctThuocPhongKham.getThuocphongkhamMathuoc(true).setDmthuocTen(dmThuoc1.getDmthuocTen());

           setHamluong(dmThuoc1.getDmthuocHamluong());
           setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
         }
         else
         {
           this.ctThuocPhongKham = new ThuocPhongKham();
           setHamluong("");
           setDmdonvitinhTen("");
           setTonTong(new Double(0.0D));
         }
       }
     }
     else
     {
       this.ctThuocPhongKham = new ThuocPhongKham();
       setHamluong("");
       setDmdonvitinhTen("");
       setTonTong(new Double(0.0D));
     }
     setMaChiDan("");
     setTenChiDan("");
     if (!this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       if (this.ctThuocPhongKham.getThuocphongkhamMathuoc() != null)
       {
         Integer masoThuoc = this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso();
         getTonKhoHienTai(masoThuoc);
         if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
         {
           TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
           if (tk.getTonkhoMaphieukiem() != null)
           {
             setTonTong(new Double(0.0D));
             this.ctThuocPhongKham.setThuocphongkhamMathuoc(null);
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
     this.ctThuocPhongKham.setThuocphongkhamHangsx(null);
     this.ctThuocPhongKham.setThuocphongkhamQuocgia(null);
   }

   public void onblurTenThuocAction()
   {
     if ((this.ctThuocPhongKham != null) && (this.ctThuocPhongKham.getThuocphongkhamMathuoc(true) != null))
     {
       String tenThuoc = this.ctThuocPhongKham.getThuocphongkhamMathuoc(true).getDmthuocTen();
       if (tenThuoc.length() > 6)
       {
         String maThuoc = tenThuoc.substring(tenThuoc.length() - 6);

         DmThuoc dmThuoc2 = this.dmThuocDelegate.findByMaThuoc(maThuoc);
         if (dmThuoc2 != null)
         {
           this.ctThuocPhongKham = new ThuocPhongKham();
           this.ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc2);
           this.ctThuocPhongKham.getThuocphongkhamMathuoc(true).setDmthuocTen(dmThuoc2.getDmthuocTen());
           this.ctThuocPhongKham.setThuocphongkhamMien(dmThuoc2.getDmthuocMien());
           this.ctThuocPhongKham.setThuocphongkhamNdm(Boolean.valueOf(!dmThuoc2.getDmthuocIndanhmuc().booleanValue()));

           setHamluong(dmThuoc2.getDmthuocHamluong());
           setDmdonvitinhTen(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhTen());
         }
         else
         {
           this.ctThuocPhongKham = new ThuocPhongKham();
           setHamluong("");
           setDmdonvitinhTen("");
           setTonTong(new Double(0.0D));
         }
       }
       else
       {
         this.ctThuocPhongKham = new ThuocPhongKham();
         setDmdonvitinhTen("");
         setHamluong("");
         setTonTong(new Double(0.0D));
       }
     }
     setMaChiDan("");
     setTenChiDan("");
     if (!this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       if (this.ctThuocPhongKham.getThuocphongkhamMathuoc() != null)
       {
         Integer masoThuoc = this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso();
         getTonKhoHienTai(masoThuoc);
         if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0))
         {
           TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
           if (tk.getTonkhoMaphieukiem() != null)
           {
             setTonTong(new Double(0.0D));
             setCheckKiemKe("true");
             this.ctThuocPhongKham.setThuocphongkhamMathuoc(null);
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
     this.ctThuocPhongKham.setThuocphongkhamHangsx(null);
     this.ctThuocPhongKham.setThuocphongkhamQuocgia(null);
   }

   public void getTonKhoHienTai(Integer masoThuoc)
   {
     TonKhoDelegate tonkhoDel = TonKhoDelegate.getInstance();
     this.listTonKhoHienTai.clear();
     List<TonKho> lstTonKhoHT = new ArrayList();
     lstTonKhoHT = tonkhoDel.getTonKhoHienTai(masoThuoc, this.khoaMaSo, IConstantsRes.PRIORITY_TON_LO_THUOC);
     if (lstTonKhoHT != null) {
       this.listTonKhoHienTai = lstTonKhoHT;
     }
   }

   public void displayTonInfo()
   {
     if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0) &&
       (this.ctThuocPhongKham.getThuocphongkhamMathuoc() != null))
     {
       TonKho tk = (TonKho)this.listTonKhoHienTai.get(0);
       if ((this.ctThuocPhongKham.getThuocphongkhamSoluong() != null) && (this.ctThuocPhongKham.getThuocphongkhamSoluong().doubleValue() <= tk.getTonkhoTon().doubleValue()))
       {
         String handungStr = "";
         if ((tk.getTonkhoNamhandung() != null) || (tk.getTonkhoNamhandung() != "")) {
           if ((tk.getTonkhoThanghandung() != null) || (tk.getTonkhoThanghandung() != ""))
           {
             if ((tk.getTonkhoNgayhandung() != null) || (tk.getTonkhoNgayhandung() != "")) {
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
         this.ctThuocPhongKham.setThuocphongkhamDongiatt(Utils.roundDoubleNumber(tk.getTonkhoDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
         this.ctThuocPhongKham.setThuocphongkhamDongia(tk.getTonkhoDongia());
         this.ctThuocPhongKham.setThuocphongkhamHangsx(tk.getDmnhasanxuatMaso());
         this.ctThuocPhongKham.setThuocphongkhamQuocgia(tk.getDmquocgiaMaso());
       }
     }
   }

   public void refreshDmThuoc()
   {
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     this.listDmThuocs.clear();
     if ((null == this.listDmThuocs) || (this.listDmThuocs.isEmpty())) {
       if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
         this.listDmThuocAll = this.dmThuocDelegate.findAll();
       } else if ((!this.khoaMa.equals("KCH")) && (!this.khoaMa.equals("KBH")) && (!this.khoaMa.equals("KNT")) && (!this.khoaMa.equals("KTE"))) {
         this.listDmThuocAll = this.dmThuocDelegate.findAll(this.khoaMaSo);
       } else {
         this.listDmThuocAll = this.dmThuocDelegate.findAll(this.khoaMa);
       }
     }
     this.hmDmThuocAll.clear();
     for (DmThuoc o : this.listDmThuocAll) {
       this.hmDmThuocAll.put(o.getDmthuocMa(), o);
     }
     if ((this.listDmThuocAll != null) && (this.listDmThuocAll.size() > 0))
     {
       if (this.searchType.equals("2"))
       {
         this.listDmThuocs.clear();
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
         }
       }
       else
       {
         this.listDmThuocs.clear();
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
         }
       }
       this.listDmThuocAll.clear();
     }
   }

   public String thuchienAction_toa_thuoc_quay()
   {
     if (Utils.KE_TOA_BENH_NHAN_TU_MUA.equals(this.loaiToaThuocThamKhamVaXuTri)) {
       this.reportTypeTD = "KeToaVe";
     } else {
       this.reportTypeTD = "XuTriThuocTaiBanKham";
     }
     ThamKhamUtil tkUtil = new ThamKhamUtil();
     tkUtil.reportTypeTD = this.reportTypeTD;
     tkUtil.XuatReport_don_thuoc_ketoa_quay(log, this.thamkham, this.listCtTPK_temp, this.clslist, this.ctThuocPhongKhams, this.index);

     this.jasperPrintTD = tkUtil.jasperPrintTD;
     this.reportPathTD = tkUtil.reportPathTD;
     this.reportTypeTD = tkUtil.reportTypeTD;
     this.index += 1;
     return "B160_Chonmenuxuattaptin";
   }

   public String troVe()
   {
     try
     {
       this.ctThuocPhongKhamSelectedOld = -1;

       this.ctThuocPhongKhams = null;
       this.ctThuocPhongKhamSelected = null;
       this.ctThuocPhongKham = null;
       this.thamkham = null;
       this.listTPKBanKhamTruoc = null;
       this.hsmListThuocDYNgoaiTru = null;
       this.listTonKhoHienTai = null;
       this.hmDmThuocAll = null;
       return "B121_3_Xutrithuoctaibankham";
     }
     catch (Exception e)
     {
       log.info("***** troVe() exception = " + e);
     }
     return null;
   }

   public Set<Integer> getRowsToUpdate()
   {
     return this.rowsToUpdate;
   }

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

   public void addBaiThuoc()
     throws Exception
   {
     if ((this.dmBaithuocMaso != null) && (this.dmBaithuocMaso.intValue() != 0))
     {
       if ((this.hsmListThuocDYNgoaiTru != null) && (this.hsmListThuocDYNgoaiTru.size() > 0))
       {
         Set set = this.hsmListThuocDYNgoaiTru.entrySet();
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
         Double soluongThuocInBaithuoc = Double.valueOf(baithuocThuoc.getBaithuocthuocSoluong() * this.sobaithuoc);
         if ((this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) || (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)))
         {
           getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
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
             if (soluongThuocInBaithuoc.doubleValue() > this.tonTong.doubleValue())
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
       }
       ThuocDongYNgoaiTru thuocDongY = new ThuocDongYNgoaiTru();
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
       thuocDongY.setThuocdongyLoai(this.loaiToaThuocThamKhamVaXuTri);
       DmKhoa khoa = new DmKhoa();
       khoa.setDmkhoaMaso(this.khoaMaSo);
       thuocDongY.setThuocdongyKhoa(khoa);

       List listThuocDongYNgoaiTru = new ArrayList();
       listThuocDongYNgoaiTru.add(thuocDongY);
       listThuocDongYNgoaiTru.add(new Boolean(false));
       listThuocDongYNgoaiTru.add(this.loaiToaThuocThamKhamVaXuTri);
       this.hsmListThuocDYNgoaiTru.put(this.dmBaithuocMaso, listThuocDongYNgoaiTru);
       if (this.ctThuocPhongKham.getThuocphongkhamNgaygio() == null) {
         this.ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
       }
       if ((this.thamkham.getThamkhamBacsi() != null) && (this.ctThuocPhongKham.getThuocphongkhamBacsi() == null)) {
         this.ctThuocPhongKham.setThuocphongkhamBacsi(this.thamkham.getThamkhamBacsi());
       }
       if (this.ctThuocPhongKham.getThuocphongkhamNgaygio() == null) {
         this.ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
       }
       if (this.ctThuocPhongKham.getThuocphongkhamNhanviencn() == null) {
         this.ctThuocPhongKham.setThuocphongkhamNhanviencn(this.nhanVienCN);
       }
       if (this.ctThuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMaso() == null) {
         this.ctThuocPhongKham.getThuocphongkhamQuocgia(true).setDmquocgiaMa("XXX");
       }
       if ((this.ctThuocPhongKham.getThuocphongkhamHangsx() != null) && (this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa() != null) && (!this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa().equals("")))
       {
         DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
         DmNhaSanXuat nsxTmp = (DmNhaSanXuat)DTDel.findByMa(this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
         if (nsxTmp != null) {
           this.ctThuocPhongKham.getThuocphongkhamHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
         }
       }
       this.ctThuocPhongKham.setThuocphongkhamMaChidan(this.maChiDan);
       this.ctThuocPhongKham.setThuocphongkhamChidan(this.tenChiDan);
       for (int i = 0; i < listbaithuocThuoc.size(); i++)
       {
         BaithuocThuoc baithuocThuoc = (BaithuocThuoc)listbaithuocThuoc.get(i);
         Double soluongThuocInBaithuoc = Double.valueOf(baithuocThuoc.getBaithuocthuocSoluong() * this.sobaithuoc);
         DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
         DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(baithuocThuoc.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
         this.ctThuocPhongKham.setThuocphongkhamMathuoc(baithuocThuoc.getDmthuocMaso());
         this.ctThuocPhongKham.setThuocphongkhamNdm(Boolean.valueOf(!baithuocThuoc.getDmthuocMaso().getDmthuocIndanhmuc().booleanValue()));
         this.ctThuocPhongKham.setThuocphongkhamMien(baithuocThuoc.getDmthuocMaso().getDmthuocMien());
         this.ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
         this.ctThuocPhongKham.setDmbaithuocMaso(this.dmBaithuocMaso);
         this.ctThuocPhongKham.setThuocphongkhamChedott(this.thamkham.getTiepdonMa(true).getDoituongMa(true));
         if ((this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) || (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)))
         {
           getTonKhoHienTai(baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
           if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
             for (int j = 0; j < this.listTonKhoHienTai.size(); j++)
             {
               TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(j);
               Double tonLo = tonkhoHienTai.getTonkhoTon();
               ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               if (soluongThuocInBaithuoc.doubleValue() <= tonLo.doubleValue())
               {
                 ctThuocPK.setThuocphongkhamSoluong(soluongThuocInBaithuoc);

                 setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa());
                 this.ctThuocPhongKhams.add(ctThuocPK);

                 ctThuocPK = new ThuocPhongKham();
                 break;
               }
               ctThuocPK = new ThuocPhongKham();
               ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               soluongThuocInBaithuoc = Double.valueOf(soluongThuocInBaithuoc.doubleValue() - tonLo.doubleValue());

               ctThuocPK.setThuocphongkhamSoluong(tonLo);
               setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa());
               this.ctThuocPhongKhams.add(ctThuocPK);

               ctThuocPK = new ThuocPhongKham();
             }
           }
         }
         else
         {
           this.ctThuocPhongKham.setThuocphongkhamSoluong(soluongThuocInBaithuoc);
           this.ctThuocPhongKhams.add(this.ctThuocPhongKham);
         }
         this.ctThuocPhongKham = new ThuocPhongKham();
       }
       this.dmBaithuocMa = "";
       this.dmBaithuocMaso = Integer.valueOf(0);
     }
     this.sobaithuoc = 1;
   }

   public void enterAJAX()
     throws Exception
   {
     try
     {
       if ((this.thamkham.getThamkhamBacsi() != null) && (this.ctThuocPhongKham.getThuocphongkhamBacsi() == null)) {
         this.ctThuocPhongKham.setThuocphongkhamBacsi(this.thamkham.getThamkhamBacsi());
       }
       if (this.ctThuocPhongKham.getThuocphongkhamNgaygio() == null) {
         this.ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());
       }
       if (this.ctThuocPhongKham.getThuocphongkhamNhanviencn() == null) {
         this.ctThuocPhongKham.setThuocphongkhamNhanviencn(this.nhanVienCN);
       }
       if (this.ctThuocPhongKham.getThuocphongkhamQuocgia(true).getDmquocgiaMaso() == null) {
         this.ctThuocPhongKham.getThuocphongkhamQuocgia(true).setDmquocgiaMa("XXX");
       }
       if ((this.ctThuocPhongKham.getThuocphongkhamHangsx() != null) && (this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa() != null) && (!this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa().equals("")))
       {
         DieuTriUtilDelegate DTDel = DieuTriUtilDelegate.getInstance();
         DmNhaSanXuat nsxTmp = (DmNhaSanXuat)DTDel.findByMa(this.ctThuocPhongKham.getThuocphongkhamHangsx().getDmnhasanxuatMa(), "DmNhaSanXuat", "dmnhasanxuatMa");
         if (nsxTmp != null) {
           this.ctThuocPhongKham.getThuocphongkhamHangsx().setDmnhasanxuatTen(nsxTmp.getDmnhasanxuatTen());
         }
       }
       this.ctThuocPhongKham.setThuocphongkhamMaChidan(this.maChiDan);
       this.ctThuocPhongKham.setThuocphongkhamChidan(this.tenChiDan);

       String doituongMa = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
       DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
       this.ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
       this.ctThuocPhongKham.setThuocphongkhamChedott(this.thamkham.getTiepdonMa(true).getDoituongMa(true));


       int viTri = -1;
       if (this.ctThuocPhongKhamSelectedOld != -1) {
         if (this.ctThuocPhongKhams.size() > this.ctThuocPhongKhamSelectedOld)
         {
           viTri = this.ctThuocPhongKhamSelectedOld;
           this.ctThuocPhongKhams.remove(this.ctThuocPhongKhamSelectedOld);
           this.ctThuocPhongKhamSelectedOld = -1;
         }
         else
         {
           this.ctThuocPhongKhamSelectedOld = -1;

           this.ctThuocPhongKham = new ThuocPhongKham();
           this.maChiDan = "";
           this.tenChiDan = "";
           setHamluong("");
           setTonTong(new Double(0.0D));
           setHandung("");
           if (this.rowsToUpdate == null) {
             this.rowsToUpdate = new HashSet();
           }
           this.rowsToUpdate.clear();
           for (int i = 0; i < this.ctThuocPhongKhams.size(); i++) {
             this.rowsToUpdate.add(new Integer(i));
           }
           return;
         }
       }
       if ((this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) || (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)))
       {
         getTonKhoHienTai(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
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
         Double soluongcapBN = this.ctThuocPhongKham.getThuocphongkhamSoluong();
         if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM))
         {
           int vitri = -1;
           if (this.ctThuocPhongKhams.size() > 0)
           {
             Double soluongDaCapTrenGrid = new Double(0.0D);
             for (int j = 0; j < this.ctThuocPhongKhams.size(); j++)
             {
               ThuocPhongKham thuocphongkhamGrid = (ThuocPhongKham)this.ctThuocPhongKhams.get(j);
               if ((thuocphongkhamGrid.getThuocphongkhamMa() == null) &&
                 (this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(thuocphongkhamGrid.getThuocphongkhamMathuoc().getDmthuocMa())) && (this.ctThuocPhongKham.getThuocphongkhamKhongthu().equals(thuocphongkhamGrid.getThuocphongkhamKhongthu())))
               {
                 soluongDaCapTrenGrid = Double.valueOf(soluongDaCapTrenGrid.doubleValue() + thuocphongkhamGrid.getThuocphongkhamSoluong().doubleValue());
                 vitri = j;
               }
             }
             soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() + soluongDaCapTrenGrid.doubleValue());
             if (soluongcapBN.doubleValue() > this.tonTong.doubleValue())
             {
               this.ctThuocPhongKham = new ThuocPhongKham();
               this.maChiDan = "";
               this.tenChiDan = "";
               setHamluong("");
               setTonTong(new Double(0.0D));
               setHandung("");
               FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU, new Object[0]);
               return;
             }
           }
           if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
             for (int i = 0; i < this.listTonKhoHienTai.size(); i++)
             {
               TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(i);
               Double tonLo = tonkhoHienTai.getTonkhoTon();
               ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
               {
                 ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);

                 setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                 if (vitri > -1) {
                   this.ctThuocPhongKhams.set(vitri, ctThuocPK);
                 } else {
                   this.ctThuocPhongKhams.add(ctThuocPK);
                 }
                 vitri = -1;
                 ctThuocPK = new ThuocPhongKham();
                 break;
               }
               ctThuocPK = new ThuocPhongKham();
               ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

               ctThuocPK.setThuocphongkhamSoluong(tonLo);
               setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
               if (vitri > -1) {
                 this.ctThuocPhongKhams.set(vitri, ctThuocPK);
               } else {
                 this.ctThuocPhongKhams.add(ctThuocPK);
               }
               ctThuocPK = new ThuocPhongKham();
               vitri = -1;
             }
           }
         }
         else
         {
           Double soluongDaCapTrenGrid = new Double(0.0D);
           int vitri = -1;
           if (this.ctThuocPhongKhams.size() > 0)
           {
             for (int j = 0; j < this.ctThuocPhongKhams.size(); j++)
             {
               ThuocPhongKham thuocphongkhamGrid = (ThuocPhongKham)this.ctThuocPhongKhams.get(j);
               if ((this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(thuocphongkhamGrid.getThuocphongkhamMathuoc().getDmthuocMa())) && (this.ctThuocPhongKham.getThuocphongkhamKhongthu().equals(thuocphongkhamGrid.getThuocphongkhamKhongthu())))
               {
                 soluongDaCapTrenGrid = Double.valueOf(soluongDaCapTrenGrid.doubleValue() + thuocphongkhamGrid.getThuocphongkhamSoluong().doubleValue());
                 vitri = j;
               }
             }
             soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() + soluongDaCapTrenGrid.doubleValue());
             if (soluongcapBN.doubleValue() > this.tonTong.doubleValue())
             {
               this.ctThuocPhongKham = new ThuocPhongKham();
               this.maChiDan = "";
               this.tenChiDan = "";
               setHamluong("");
               setTonTong(new Double(0.0D));
               setHandung("");
               FacesMessages.instance().add(IConstantsRes.XLBK_SOLUONGKHONGDU, new Object[0]);
               return;
             }
           }
           if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
             for (int i = 0; i < this.listTonKhoHienTai.size(); i++)
             {
               TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(i);
               Double tonLo = tonkhoHienTai.getTonkhoTon();
               ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
               {
                 ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);

                 setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                 if (vitri > -1) {
                   this.ctThuocPhongKhams.set(vitri, ctThuocPK);
                 } else {
                   this.ctThuocPhongKhams.add(ctThuocPK);
                 }
                 ctThuocPK = new ThuocPhongKham();
                 vitri = -1;
                 break;
               }
               ctThuocPK = new ThuocPhongKham();
               ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKham);
               soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

               ctThuocPK.setThuocphongkhamSoluong(tonLo);
               setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
               if (vitri > -1) {
                 this.ctThuocPhongKhams.set(vitri, ctThuocPK);
               } else {
                 this.ctThuocPhongKhams.add(ctThuocPK);
               }
               vitri = -1;
               ctThuocPK = new ThuocPhongKham();
             }
           }
         }
       }
       this.maChiDan = "";
       this.tenChiDan = "";
       setThuocdongyMaso(Integer.valueOf(0));
       setHamluong("");
       setTonTong(new Double(0.0D));
       setHandung("");
       if (this.rowsToUpdate == null) {
         this.rowsToUpdate = new HashSet();
       }
       this.rowsToUpdate.clear();
       for (int i = 0; i < this.ctThuocPhongKhams.size(); i++) {
         this.rowsToUpdate.add(new Integer(i));
       }
     }
     catch (Exception e)
     {
       log.info("***** enterAJAX() Exception = " + e);
     }
     this.ctThuocPhongKham = new ThuocPhongKham();
   }

   public void setThuocPhongKhamInfo(ThuocPhongKham ctThuocPK, TonKho tonkhoHienTai, String doituongMa)
   {
     boolean mien = false;
     if ((ctThuocPK.getThuocphongkhamMien() != null) && (ctThuocPK.getThuocphongkhamMien().booleanValue() == true)) {
       mien = true;
     }
     boolean yeucau = false;
     if ((ctThuocPK.getThuocphongkhamYeucau() != null) && (ctThuocPK.getThuocphongkhamYeucau().booleanValue() == true)) {
       yeucau = true;
     }
     if (doituongMa.equals("MP"))
     {
       if ((mien) && (yeucau) && (!ctThuocPK.getThuocphongkhamKhongthu().booleanValue())) {
         ctThuocPK.setThuocphongkhamDongia(tonkhoHienTai.getTonkhoDongia());
       } else {
         ctThuocPK.setThuocphongkhamDongia(new Double(0.0D));
       }
     }
     else if (ctThuocPK.getThuocphongkhamKhongthu().booleanValue()) {
       ctThuocPK.setThuocphongkhamDongia(new Double(0.0D));
     } else {
       ctThuocPK.setThuocphongkhamDongia(tonkhoHienTai.getTonkhoDongia());
     }
     ctThuocPK.setThuocphongkhamDongiatt(Utils.roundDoubleNumber(ctThuocPK.getThuocphongkhamDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));
     ctThuocPK.setThuocphongkhamThanhtien(Utils.roundDoubleNumber(Double.valueOf(ctThuocPK.getThuocphongkhamDongiatt().doubleValue() * ctThuocPK.getThuocphongkhamSoluong().doubleValue()), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));

     ctThuocPK.setThuocphongkhamDongiabh(tonkhoHienTai.getTonkhoDongia());
     ctThuocPK.setThuocphongkhamMalk(tonkhoHienTai.getTonkhoMalienket());
     ctThuocPK.setThuocphongkhamDongiaban(tonkhoHienTai.getTonkhoDongiaban());
     ctThuocPK.setThuocphongkhamNamnhap(tonkhoHienTai.getTonkhoNamnhap());
     ctThuocPK.setThuocphongkhamHangsx(tonkhoHienTai.getDmnhasanxuatMaso());
     ctThuocPK.setThuocphongkhamQuocgia(tonkhoHienTai.getDmquocgiaMaso());
     ctThuocPK.setThuocphongkhamLo(tonkhoHienTai.getTonkhoLo());
     ctThuocPK.setThuocphongkhamNgayhd(tonkhoHienTai.getTonkhoNgayhandung());
     ctThuocPK.setThuocphongkhamThanghd(tonkhoHienTai.getTonkhoThanghandung());
     ctThuocPK.setThuocphongkhamNamhd(tonkhoHienTai.getTonkhoNamhandung());
     ctThuocPK.setDmnctMaso(tonkhoHienTai.getDmnctMaso());
     ctThuocPK.setDmnguonkinhphiMaso(tonkhoHienTai.getDmnguonkinhphiMaso());
     ctThuocPK.setThuocphongkhamNgaygiocn(new Date());
     ctThuocPK.setThuocphongkhamNhanviencn(this.nhanVienCN);
   }

   public String getDvtName(int index)
   {
     ThuocPhongKham thuocPK = (ThuocPhongKham)this.ctThuocPhongKhams.get(index);
     if (thuocPK != null)
     {
       DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
       if (thuocPK.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhMaso() != null)
       {
         DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(thuocPK.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
         return dvt.getDmdonvitinhTen();
       }
     }
     return "";
   }

   public void deleteRecordForUpdateDataModelTable()
   {
     if ((this.ctThuocPhongKhams != null) && (this.ctThuocPhongKhams.size() > 0)) {
       this.ctThuocPhongKhams.remove(this.ctThuocPhongKhams.size() - 1);
     }
   }

   public void addRecordForUpdateDataModelTable()
   {
     this.ctThuocPhongKhams.add(new ThuocPhongKham());
   }

   public void chinhSuaAJAX(int index)
     throws Exception
   {
     this.ctThuocPhongKhamSelected = ((ThuocPhongKham)this.ctThuocPhongKhams.get(index));
     if (this.ctThuocPhongKhamSelected == null) {
       return;
     }
     if ((this.ctThuocPhongKhamSelected != null) && (this.ctThuocPhongKhamSelected.getThuocphongkhamMaphieud() != null) && (!this.ctThuocPhongKhamSelected.getThuocphongkhamMaphieud().equals(""))) {
       return;
     }
     if ((this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) && (this.ctThuocPhongKhamSelected != null) && (this.ctThuocPhongKhamSelected.getThuocphongkhamMa() != null))
     {
       FacesMessages.instance().add(IConstantsRes.XLBK_NOTEDIT, new Object[] { this.ctThuocPhongKhamSelected.getThuocphongkhamMalk() });
       this.ctThuocPhongKhamSelected = new ThuocPhongKham();
       this.ctThuocPhongKham = new ThuocPhongKham();
       this.maChiDan = "";
       this.tenChiDan = "";
       setHamluong("");
       setTonTong(new Double(0.0D));
       setHandung("");
       return;
     }
     if (this.ctThuocPhongKhamSelected.getThuocdongyNgoaiTru() != null) {
       this.thuocdongyMaso = this.ctThuocPhongKhamSelected.getThuocdongyNgoaiTru().getThuocdongyMaso();
     } else {
       this.thuocdongyMaso = Integer.valueOf(0);
     }
     this.ctThuocPhongKham = ((ThuocPhongKham)BeanUtils.cloneBean(this.ctThuocPhongKhamSelected));

     this.tenLoiDans = this.ctThuocPhongKham.getThuocphongkhamTenloidan();

     this.maChiDan = this.ctThuocPhongKham.getThuocphongkhamMaChidan();
     this.tenChiDan = this.ctThuocPhongKham.getThuocphongkhamChidan();

     getTonKhoHienTai(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
     Double tonHT = new Double(0.0D);
     for (TonKho tk : this.listTonKhoHienTai)
     {
       tonHT = Double.valueOf(tonHT.doubleValue() + tk.getTonkhoTon().doubleValue());
       if (tk.getTonkhoMaphieukiem() != null)
       {
         setTonTong(new Double(0.0D));
         this.ctThuocPhongKham.setThuocphongkhamMathuoc(null);
         setCheckKiemKe("true");
         break;
       }
       setTonTong(tonHT);
     }
     String handungStr = "";
     if ((this.ctThuocPhongKham.getThuocphongkhamNamhd() != null) &&
       (!this.ctThuocPhongKham.getThuocphongkhamNamhd().equals(""))) {
       if (this.ctThuocPhongKham.getThuocphongkhamThanghd() != null)
       {
         if (this.ctThuocPhongKham.getThuocphongkhamThanghd().equals("")) {
           handungStr = this.ctThuocPhongKham.getThuocphongkhamNamhd();
         } else if (this.ctThuocPhongKham.getThuocphongkhamNgayhd() != null)
         {
           if (!this.ctThuocPhongKham.getThuocphongkhamNgayhd().equals("")) {
             handungStr = this.ctThuocPhongKham.getThuocphongkhamNgayhd() + "/" + this.ctThuocPhongKham.getThuocphongkhamThanghd() + "/" + this.ctThuocPhongKham.getThuocphongkhamNamhd();
           } else {
             handungStr = this.ctThuocPhongKham.getThuocphongkhamThanghd() + "/" + this.ctThuocPhongKham.getThuocphongkhamNamhd();
           }
         }
         else {
           handungStr = this.ctThuocPhongKham.getThuocphongkhamThanghd() + "/" + this.ctThuocPhongKham.getThuocphongkhamNamhd();
         }
       }
       else {
         handungStr = this.ctThuocPhongKham.getThuocphongkhamNamhd();
       }
     }
     setHandung(handungStr);
     setHamluong(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocHamluong());
     DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
     DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmdonvitinhMaso().getDmdonvitinhMaso(), "DmDonViTinh", "dmdonvitinhMaso");
     setDmdonvitinhTen(dvt.getDmdonvitinhTen());
     this.ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);



     this.ctThuocPhongKhamSelectedOld = index;
   }

   public void xoaAJAX(int index)
     throws Exception
   {
     ThuocPhongKham ctThuocPK = (ThuocPhongKham)this.ctThuocPhongKhams.get(index);
     if ((ctThuocPK != null) && (ctThuocPK.getThuocphongkhamMaphieud() != null) && (!ctThuocPK.getThuocphongkhamMaphieud().equals(""))) {
       return;
     }
     try
     {
       this.ctThuocPhongKhams.remove(index);

       boolean isExistedThuocDY = false;
       for (ThuocPhongKham tpk : this.ctThuocPhongKhams) {
         if (tpk.getDmbaithuocMaso() != null)
         {
           isExistedThuocDY = true;
           break;
         }
       }
       if (!isExistedThuocDY) {
         this.hsmListThuocDYNgoaiTru.remove(ctThuocPK.getDmbaithuocMaso());
       }
       this.ctThuocPhongKham = new ThuocPhongKham();
     }
     catch (Exception e)
     {
       log.info("***** xoaAJAX Exception = " + e);
     }
   }

   public XuTriThuocTaiBanKhamAction()
   {
     this.sobaithuoc = 1;



































     this.reportPathTD = null;



     this.reportTypeTD = null;



     this.jasperPrintTD = null;



     this.index = 0;












     this.handung = "";
     this.hamluong = "";


     this.hsmListThuocDYNgoaiTru = new HashMap();






     this.ctThuocPhongKhamSelectedOld = -1;

     this.updateNhapct = false;

     this.ctThuocPhongKham = null;

     this.doiTuong = null;
     this.tonTong = new Double(0.0D);
     this.listTonKhoHienTai = new ArrayList();
     this.checkKiemKe = "false";





     this.loaiToaThuocOld = "";





     this.isReport = "false";

     this.maFinish = "";
     this.dmBaithuocMaso = Integer.valueOf(0);

























     this.listDmThuocs = new ArrayList();

     this.hmDmThuocAll = new HashMap();
     this.searchType = "1";


















































































































































































































































     this.listCtTPK_temp = null;
     this.clslist = null;






























































































































































































































































































































































































































































































































































































































































































     this.nhanVienCN = null;

     this.hienThiInPhieu = Boolean.valueOf(false);
   }

   @Begin(join=true)
   public String initFromMenu(String fromMenu, String loaiThuocPhongKham)
   {
     this.fromMenu = fromMenu;
     this.initB121_3_Xutrithuoctaibankham = "no";
     this.loaiToaThuocThamKhamVaXuTri = loaiThuocPhongKham;
     resetValueFromMenu();
     initService();
     if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       return "ketoave";
     }
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienCN = nvDelegate.findByNd(this.identity.getUsername());

     return "xutrithuoctaibankham";
   }

   public void displayInforFromMenu()
     throws Exception
   {
     this.maBanKhamOut = this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa();
     this.maTiepDonOut = this.thamkham.getTiepdonMa(true).getTiepdonMa();
     tempInit();
   }

   public void resetValueFromMenu()
   {
     this.maBanKhamOut = "";
     this.maTiepDonOut = "";
     this.thamkham = new ThamKham();
     this.ngaySinh = "";
     this.thoiGian = "";
     this.ctThuocPhongKham = new ThuocPhongKham();

     this.doiTuong = new DmDoiTuong();
     this.ctThuocPhongKhams = new ArrayList();
     this.hsmListThuocDYNgoaiTru = new HashMap();
     this.sLoiDan = "";
     this.ngaySinh = "";
     this.thuocdongyMaso = Integer.valueOf(0);
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.thoiGian = formatter.format(new Date());
     this.gioThamKham = Utils.getGioPhut(new Date());
     this.hienThiInPhieu = Boolean.valueOf(false);

     this.sobaithuoc = 1;
   }

   @Factory("initB121_3_Xutrithuoctaibankham")
   @Begin(nested=true)
   public void init()
     throws Exception
   {
     tempInit();
     this.fromMenu = "";
   }

   private void tempInit()
   {
     try
     {
       initService();

       this.sLoiDan = "";
       this.tenLoiDans = "";

       this.checkKiemKe = "false";
       this.thuocdongyMaso = Integer.valueOf(0);
       if ((this.maBanKhamOut == null) || (this.maBanKhamOut.equals("")) || (this.maTiepDonOut == null) || (this.maTiepDonOut.equals("")))
       {
         resetValueFromMenu();
         return;
       }
       this.hsmListThuocDYNgoaiTru = new HashMap();
       this.ctThuocPhongKham = new ThuocPhongKham();

       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       ThamKham thamkham_temp = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if (thamkham_temp == null)
       {
         resetValueFromMenu();
         FacesMessages.instance().add(IConstantsRes.TIEPDON_NOTFOUND, new Object[0]);
         return;
       }
       this.thamkham = thamkham_temp;
       if (this.thamkham.getThamkhamBacsi() != null)
       {
         this.mabs = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
         this.masobs = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMaso();

         this.tenbs = this.thamkham.getThamkhamBacsi().getDtdmnhanvienTen();
       }
       else
       {
         this.mabs = "";
         this.tenbs = "";
         this.masobs = null;
       }
       this.chandoan = (this.thamkham.getBenhicd10() != null ? this.thamkham.getBenhicd10().getDmbenhicdTen() : "");


       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoa = new DmKhoa();
       if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM))
       {
         DtDmBanKhamDelegate dtDmBKDelegate = DtDmBanKhamDelegate.getInstance();
         DtDmBanKham dtDmBK = dtDmBKDelegate.find(this.thamkham.getThamkhamBankham().getDtdmbankhamMaso());
         dmKhoa = dtDmBK.getDmkhoaMaso();
       }
       else if (IConstantsRes.IS_USED_KHOTE == "0")
       {
         if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_BHYT_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
         } else if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_TRE_EM_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
         } else if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_CHINH_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
         } else if (this.thamkham.getThamkhamBankham(true).getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_NOITRU_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
         }
       }
       else
       {
         TiepDonDelegate tdonDelegate = TiepDonDelegate.getInstance();
         String tiepDonMa = this.thamkham.getTiepdonMa(true).getTiepdonMa();
         TiepDon tiepdon = tdonDelegate.find(tiepDonMa);
         DtDmBanKham dtDmBK = tiepdon.getTiepdonBankham();
         if (dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_BHYT_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa");
         } else if (dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_TRE_EM_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa");
         } else if (dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_CHINH_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa");
         } else if (dtDmBK.getDtdmbankhamTinhchat().equals(IConstantsRes.KHO_NOITRU_MA)) {
           dmKhoa = (DmKhoa)dieuTriUtilDelegate.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa");
         }
       }
       this.khoaMaSo = dmKhoa.getDmkhoaMaso();
       this.khoaMa = dmKhoa.getDmkhoaMa();





       this.doiTuong = this.thamkham.getTiepdonMa().getDoituongMa();







       this.initB121_3_Xutrithuoctaibankham = "0";





       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();




       this.ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.thamkham.getTiepdonMa(true).getTiepdonMa(), this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       if ((this.ctThuocPhongKhams != null) && (this.ctThuocPhongKhams.size() > 0)) {
         this.tenLoiDans = ((ThuocPhongKham)this.ctThuocPhongKhams.get(0)).getThuocphongkhamTenloidan();
       }
       ThuocDongYNgoaiTruDelegate thuocDYNgoaiTruDel = ThuocDongYNgoaiTruDelegate.getInstance();
       List<ThuocDongYNgoaiTru> lstThuocDongYNgoaiTru = thuocDYNgoaiTruDel.findByMaTiepDonAndMaBanKham(this.thamkham.getTiepdonMa(true).getTiepdonMa(), this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       for (ThuocDongYNgoaiTru thuocDongYNgoaiTru : lstThuocDongYNgoaiTru)
       {
         List listThuocDongYNgoaiTru = new ArrayList();
         listThuocDongYNgoaiTru.add(thuocDongYNgoaiTru);
         listThuocDongYNgoaiTru.add(new Boolean(true));
         listThuocDongYNgoaiTru.add(this.loaiToaThuocThamKhamVaXuTri);
         this.hsmListThuocDYNgoaiTru.put(thuocDongYNgoaiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNgoaiTru);
       }
       if (this.ctThuocPhongKhams == null) {
         this.ctThuocPhongKhams = new ArrayList();
       }
       if ((!this.loaiToaThuocOld.equals(this.loaiToaThuocThamKhamVaXuTri)) || (this.listDmThuocs == null) || (this.listDmThuocs.isEmpty()))
       {
         this.initB121_3_Xutrithuoctaibankham = "done";
         this.loaiToaThuocOld = this.loaiToaThuocThamKhamVaXuTri;
         refreshDmThuoc();
       }
       else
       {
         this.initB121_3_Xutrithuoctaibankham = "done";
       }
       setOtherValue();
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
       e.printStackTrace();
     }
   }

   public void getToaThuocTruoc()
   {
     ThamKhamDelegate thamKhamDelegate = ThamKhamDelegate.getInstance();
     ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
     this.hsmListThuocDYNgoaiTru.clear();
     ThamKham thamkham_loadthuoc = thamKhamDelegate.getLastThamKhamWithSoTheBHYTAndBanKham(this.thamkham.getTiepdonMa(true).getTiepdonSothebh(), this.thamkham.getThamkhamBankham().getDtdmbankhamMaso());
     if (thamkham_loadthuoc != null)
     {
       List<ThuocPhongKham> ctToaThuocTruoc = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       try
       {
         if ((ctToaThuocTruoc != null) && (ctToaThuocTruoc.size() > 0))
         {
           List<ThuocPhongKham> listTpk_Temp = new ArrayList();
           for (ThuocPhongKham toaThuocTruoc : ctToaThuocTruoc)
           {
             ThuocPhongKham thuocphongkhamNew = new ThuocPhongKham();
             thuocphongkhamNew = (ThuocPhongKham)BeanUtils.cloneBean(toaThuocTruoc);
             thuocphongkhamNew.setThuocphongkhamMaphieud(null);
             thuocphongkhamNew.setThuocphongkhamDatt(null);
             thuocphongkhamNew.setThuocphongkhamMa(null);
             thuocphongkhamNew.setThuocphongkhamNgaygio(new Date());
             thuocphongkhamNew.setThuocphongkhamNgaygiocn(new Date());
             thuocphongkhamNew.setThuocphongkhamMalk(null);
             if (this.thamkham.getThamkhamBacsi() != null) {
               thuocphongkhamNew.setThuocphongkhamBacsi(this.thamkham.getThamkhamBacsi());
             }
             if (listTpk_Temp.size() == 0)
             {
               listTpk_Temp.add(thuocphongkhamNew);
             }
             else
             {
               ThuocPhongKham thuocphongkham_Finded = new ThuocPhongKham();
               boolean foundInList = false;
               int vitri = -1;
               for (int j = 0; j < listTpk_Temp.size(); j++)
               {
                 ThuocPhongKham thuocphongkhamNew1 = (ThuocPhongKham)listTpk_Temp.get(j);
                 if (thuocphongkhamNew1.getThuocphongkhamMathuoc().getDmthuocMaso().equals(thuocphongkhamNew.getThuocphongkhamMathuoc().getDmthuocMaso()))
                 {
                   thuocphongkham_Finded = thuocphongkhamNew1;
                   foundInList = true;
                   vitri = j;
                   break;
                 }
               }
               if (foundInList == true)
               {
                 thuocphongkhamNew.setThuocphongkhamSoluong(Double.valueOf(thuocphongkhamNew.getThuocphongkhamSoluong().doubleValue() + thuocphongkham_Finded.getThuocphongkhamSoluong().doubleValue()));
                 listTpk_Temp.set(vitri, thuocphongkhamNew);
               }
               else
               {
                 listTpk_Temp.add(thuocphongkhamNew);
               }
             }
           }
           for (ThuocPhongKham toaThuocTruoc : listTpk_Temp) {
             this.ctThuocPhongKhams.add(toaThuocTruoc);
           }
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.TOA_THUOC_TRUOC_KO_CO, new Object[0]);
         }
         ThuocDongYNgoaiTruDelegate thuocDYNgoaiTruDel = ThuocDongYNgoaiTruDelegate.getInstance();
         List<ThuocDongYNgoaiTru> lstThuocDongYNgoaiTru = thuocDYNgoaiTruDel.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
         for (ThuocDongYNgoaiTru thuocDongYNgoaiTru : lstThuocDongYNgoaiTru)
         {
           List listThuocDongYNgoaiTru = new ArrayList();
           listThuocDongYNgoaiTru.add(thuocDongYNgoaiTru);
           listThuocDongYNgoaiTru.add(new Boolean(false));
           listThuocDongYNgoaiTru.add(this.loaiToaThuocThamKhamVaXuTri);
           this.hsmListThuocDYNgoaiTru.put(thuocDongYNgoaiTru.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDongYNgoaiTru);
         }
       }
       catch (Exception er)
       {
         er.printStackTrace();
       }
     }
   }

   public void initService()
   {
     if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) {
       this.titleThamKhamVaXuTri = IConstantsRes.KE_TOA_TAI_BAN_KHAM;
     } else if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       this.titleThamKhamVaXuTri = IConstantsRes.KE_TOA_VE;
     } else if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN)) {
       this.titleThamKhamVaXuTri = IConstantsRes.KE_TOA_QUAY_BENH_VIEN;
     }
     this.hienThiInPhieu = Boolean.valueOf(true);
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(Utils.FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!this.thamkham.getThamkhamNgaygio().equals(""))) {
       this.thoiGian = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
     }
     if (this.thamkham.getThamkhamNgaygio() != null) {
       this.gioThamKham = Utils.getGioPhut(this.thamkham.getThamkhamNgaygio());
     }
   }

   public String ghiNhanAjax()
     throws Exception
   {
     try
     {
       HashMap hsmThuocDYNGTTemp = new HashMap();
       if ((this.hsmListThuocDYNgoaiTru != null) && (this.hsmListThuocDYNgoaiTru.size() > 0))
       {
         Set set = this.hsmListThuocDYNgoaiTru.entrySet();
         Iterator i = set.iterator();
         while (i.hasNext())
         {
           Map.Entry me = (Map.Entry)i.next();
           List listThuocDY = (List)me.getValue();
           ThuocDongYNgoaiTru thuocDY = (ThuocDongYNgoaiTru)listThuocDY.get(0);
           Boolean isSavedDB = (Boolean)listThuocDY.get(1);
           thuocDY.setThuocdongyThamkham(this.thamkham);
           thuocDY.setTiepdonMa(this.thamkham.getTiepdonMa(true));
           listThuocDY.set(0, thuocDY);
           hsmThuocDYNGTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);
         }
       }
       this.hsmListThuocDYNgoaiTru = hsmThuocDYNGTTemp;
       DmKhoa dmKhoa = new DmKhoa();
       dmKhoa.setDmkhoaMaso(this.khoaMaSo);
       for (ThuocPhongKham tpk : this.ctThuocPhongKhams)
       {
         RemoveUtil.removeIfNullForThuocPhongKham(tpk);
         tpk.setThuocphongkhamThamkham(this.thamkham);
         tpk.setThuocphongkhamLoai(String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
         tpk.setThuocphongkhamKhoa(dmKhoa);
         tpk.setThuocphongkhamTenloidan(this.tenLoiDans);


         tpk.setThuocphongkhamDongiabh(tpk.getThuocphongkhamDongia());
       }
       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       if (this.thamkham.getThamkhamNgaygio() == null) {
         this.thamkham.setThamkhamNgaygio(new Date());
       }
       this.thamkham.setThamkhamNgaygiocn(new Date());

       DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
       DtDmNhanVien nhanVienCN = nvDelegate.findByNd(this.identity.getUsername());
       this.thamkham.setThamkhamNhanviencn(nhanVienCN);

       RemoveUtil.removeAllNullFromTiepDon(this.thamkham.getTiepdonMa());
       RemoveUtil.removeAllNullFromThamKham(this.thamkham);

       String doituongMa = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
       if ((this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM)) || (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)))
       {
         if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.XU_TRI_THUOC_TAI_BAN_KHAM))
         {
           List<ThuocPhongKham> listTpks = new ArrayList();
           for (ThuocPhongKham tpk : this.ctThuocPhongKhams) {
             if ((tpk.getThuocphongkhamMalk() == null) || (tpk.getThuocphongkhamMalk() == ""))
             {
               Double soluongcapBN = tpk.getThuocphongkhamSoluong();

               getTonKhoHienTai(tpk.getThuocphongkhamMathuoc().getDmthuocMaso());
               Double tongTon = Double.valueOf(0.0D);
               if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
                 for (int j = 0; j < this.listTonKhoHienTai.size(); j++) {
                   tongTon = Double.valueOf(tongTon.doubleValue() + ((TonKho)this.listTonKhoHienTai.get(j)).getTonkhoTon().doubleValue());
                 }
               }
               if (soluongcapBN.doubleValue() > tongTon.doubleValue())
               {
                 FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, new Object[] { tpk.getThuocphongkhamMathuoc().getDmthuocMa() });
                 return "";
               }
               if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
                 for (int j = 0; j < this.listTonKhoHienTai.size(); j++)
                 {
                   TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(j);
                   Double tonLo = tonkhoHienTai.getTonkhoTon();
                   ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
                   if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
                   {
                     ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);

                     setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                     listTpks.add(ctThuocPK);

                     ctThuocPK = new ThuocPhongKham();
                     break;
                   }
                   ctThuocPK = new ThuocPhongKham();
                   ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
                   soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

                   ctThuocPK.setThuocphongkhamSoluong(tonLo);
                   setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                   listTpks.add(ctThuocPK);

                   ctThuocPK = new ThuocPhongKham();
                 }
               }
             }
             else
             {
               listTpks.add(tpk);
             }
           }
           this.ctThuocPhongKhams = listTpks;
         }
         String result = tpkDelegate.createToaThuocPhongKham(this.ctThuocPhongKhams, this.thamkham, this.loaiToaThuocThamKhamVaXuTri, this.hsmListThuocDYNgoaiTru);
         if (result.indexOf("ERROR") > -1) {
           FacesMessages.instance().add(result, new Object[0]);
         }
       }
       else if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_QUAY_BENH_VIEN))
       {
         List<ThuocPhongKham> listTpks = new ArrayList();
         for (ThuocPhongKham tpk : this.ctThuocPhongKhams) {
           if ((tpk.getThuocphongkhamMalk() == null) || (tpk.getThuocphongkhamMalk() == ""))
           {
             Double soluongcapBN = tpk.getThuocphongkhamSoluong();

             getTonKhoHienTai(tpk.getThuocphongkhamMathuoc().getDmthuocMaso());
             Double tongTon = Double.valueOf(0.0D);
             if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
               for (int j = 0; j < this.listTonKhoHienTai.size(); j++) {
                 tongTon = Double.valueOf(tongTon.doubleValue() + ((TonKho)this.listTonKhoHienTai.get(j)).getTonkhoTon().doubleValue());
               }
             }
             if (soluongcapBN.doubleValue() > tongTon.doubleValue())
             {
               FacesMessages.instance().add(IConstantsRes.XLBK_HETTONTHUOC, new Object[] { tpk.getThuocphongkhamMathuoc().getDmthuocTen() });
               return "";
             }
             if ((this.listTonKhoHienTai != null) && (this.listTonKhoHienTai.size() > 0)) {
               for (int j = 0; j < this.listTonKhoHienTai.size(); j++)
               {
                 TonKho tonkhoHienTai = (TonKho)this.listTonKhoHienTai.get(j);
                 Double tonLo = tonkhoHienTai.getTonkhoTon();
                 ThuocPhongKham ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
                 if (soluongcapBN.doubleValue() <= tonLo.doubleValue())
                 {
                   ctThuocPK.setThuocphongkhamSoluong(soluongcapBN);

                   setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                   listTpks.add(ctThuocPK);

                   ctThuocPK = new ThuocPhongKham();
                   break;
                 }
                 ctThuocPK = new ThuocPhongKham();
                 ctThuocPK = (ThuocPhongKham)BeanUtils.cloneBean(tpk);
                 soluongcapBN = Double.valueOf(soluongcapBN.doubleValue() - tonLo.doubleValue());

                 ctThuocPK.setThuocphongkhamSoluong(tonLo);
                 setThuocPhongKhamInfo(ctThuocPK, tonkhoHienTai, doituongMa);
                 listTpks.add(ctThuocPK);

                 ctThuocPK = new ThuocPhongKham();
               }
             }
           }
           else
           {
             listTpks.add(tpk);
           }
         }
         this.ctThuocPhongKhams = listTpks;
         String result = tpkDelegate.createKeToaQuayBenhVien(this.ctThuocPhongKhams, this.thamkham, this.loaiToaThuocThamKhamVaXuTri, this.hsmListThuocDYNgoaiTru);
         if (result.indexOf("ERROR") > -1) {
           FacesMessages.instance().add(result, new Object[0]);
         }
       }
       HsThtoank hsttk = new HsThtoank();
       hsttk.setTiepdonMa(this.thamkham.getTiepdonMa());
       ThamKhamUtil tkUtil = new ThamKhamUtil();
       tkUtil.tinhToanChoHSTKKham(this.thamkham, hsttk, Boolean.valueOf(false), this.listCtTPK_temp, this.clslist);

       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.hsmListThuocDYNgoaiTru.clear();

       this.ctThuocPhongKhamSelectedOld = -1;
       this.ctThuocPhongKhamSelected = new ThuocPhongKham();
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);

       e.printStackTrace();
       log.error("ERROR -- ghiNhanAjax() THUOC_Ngoai_Tru" + e.toString());
     }
     if ((this.fromMenu == null) || (this.fromMenu.equals(""))) {
       return "ghinhan";
     }
     return null;
   }

   public String quaylai()
     throws Exception
   {
     return "ghinhan";
   }

   public void showCLSBanKhamTruoc()
   {
     this.listTPKBanKhamTruoc = new ArrayList();
     ThamKhamDelegate thamkhamDel = ThamKhamDelegate.getInstance();
     if (this.thamkham != null)
     {
       List<ThuocPhongKham> thuocphongkhams = thamkhamDel.getListThuocBanKhamTruoc(this.thamkham.getTiepdonMa().getTiepdonMa(), this.thamkham.getThamkhamMa());
       for (ThuocPhongKham tpk : thuocphongkhams)
       {
         ThuocPhongKhamExt tpkExt = new ThuocPhongKhamExt();
         tpkExt.setTpk(tpk);
         tpkExt.setBankhamTen(tpk.getThuocphongkhamThamkham().getThamkhamBankham(true).getDtdmbankhamTen());
         this.listTPKBanKhamTruoc.add(tpkExt);
       }
     }
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

   public boolean isUpdateNhapct()
   {
     return this.updateNhapct;
   }

   public void setUpdateNhapct(boolean updateNhapct)
   {
     this.updateNhapct = updateNhapct;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getGioThamKham()
   {
     return this.gioThamKham;
   }

   public void setGioThamKham(String gioThamKham)
   {
     this.gioThamKham = gioThamKham;
   }

   public String getThoiGian()
   {
     return this.thoiGian;
   }

   public void setThoiGian(String thoiGian)
   {
     this.thoiGian = thoiGian;
   }

   public DmDoiTuong getDoiTuong()
   {
     return this.doiTuong;
   }

   public void setDoiTuong(DmDoiTuong doiTuong)
   {
     this.doiTuong = doiTuong;
   }

   public ThuocPhongKham getCtThuocPhongKham()
   {
     return this.ctThuocPhongKham;
   }

   public void setCtThuocPhongKham(ThuocPhongKham ctThuocPhongKham)
   {
     this.ctThuocPhongKham = ctThuocPhongKham;
   }

   public String getInitB121_3_Xutrithuoctaibankham()
   {
     return this.initB121_3_Xutrithuoctaibankham;
   }

   public void setInitB121_3_Xutrithuoctaibankham(String initB121_3_Xutrithuoctaibankham)
   {
     this.initB121_3_Xutrithuoctaibankham = initB121_3_Xutrithuoctaibankham;
   }

   public List<ThuocPhongKham> getCtThuocPhongKhams()
   {
     return this.ctThuocPhongKhams;
   }

   public void setCtThuocPhongKhams(List<ThuocPhongKham> ctThuocPhongKhams)
   {
     this.ctThuocPhongKhams = ctThuocPhongKhams;
   }

   public ThuocPhongKham getCtThuocPhongKhamSelected()
   {
     return this.ctThuocPhongKhamSelected;
   }

   public void setCtThuocPhongKhamSelected(ThuocPhongKham ctThuocPhongKhamSelected)
   {
     this.ctThuocPhongKhamSelected = ctThuocPhongKhamSelected;
   }

   public int getCtThuocPhongKhamSelectedOld()
   {
     return this.ctThuocPhongKhamSelectedOld;
   }

   public void setCtThuocPhongKhamSelectedOld(int ctThuocPhongKhamSelectedOld)
   {
     this.ctThuocPhongKhamSelectedOld = ctThuocPhongKhamSelectedOld;
   }

   public String getTenThuoc()
   {
     return this.tenThuoc;
   }

   public void setTenThuoc(String tenThuoc)
   {
     this.tenThuoc = tenThuoc;
   }

   public String getTitleThamKhamVaXuTri()
   {
     return this.titleThamKhamVaXuTri;
   }

   public void setTitleThamKhamVaXuTri(String titleThamKhamVaXuTri)
   {
     this.titleThamKhamVaXuTri = titleThamKhamVaXuTri;
   }

   public Integer getKhoaMaSo()
   {
     return this.khoaMaSo;
   }

   public void setKhoaMaSo(Integer khoaMaSo)
   {
     this.khoaMaSo = khoaMaSo;
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

   public void setMaFinish(String maFinish)
   {
     this.maFinish = maFinish;
   }

   public String getMaFinish()
   {
     return this.maFinish;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public String getReturnToThuocYDungCuPhongKham()
   {
     return this.returnToThuocYDungCuPhongKham;
   }

   public void setReturnToThuocYDungCuPhongKham(String returnToThuocYDungCuPhongKham)
   {
     this.returnToThuocYDungCuPhongKham = returnToThuocYDungCuPhongKham;
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

   public String getReportPathTD()
   {
     return this.reportPathTD;
   }

   public void setReportPathTD(String reportPathTD)
   {
     this.reportPathTD = reportPathTD;
   }

   public String getReportTypeTD()
   {
     return this.reportTypeTD;
   }

   public void setReportTypeTD(String reportTypeTD)
   {
     this.reportTypeTD = reportTypeTD;
   }

   public JasperPrint getJasperPrintTD()
   {
     return this.jasperPrintTD;
   }

   public void setJasperPrintTD(JasperPrint jasperPrintTD)
   {
     this.jasperPrintTD = jasperPrintTD;
   }

   public String getLoaiToaThuocThamKhamVaXuTri()
   {
     return this.loaiToaThuocThamKhamVaXuTri;
   }

   public void setLoaiToaThuocThamKhamVaXuTri(String loaiToaThuocThamKhamVaXuTri)
   {
     this.loaiToaThuocThamKhamVaXuTri = loaiToaThuocThamKhamVaXuTri;
   }

   public String getFromMenu()
   {
     return this.fromMenu;
   }

   public void setFromMenu(String fromMenu)
   {
     this.fromMenu = fromMenu;
   }

   public void setRowsToUpdate(Set<Integer> rowsToUpdate)
   {
     this.rowsToUpdate = rowsToUpdate;
   }

   public Boolean getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(Boolean hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }

   public String getSLoiDan()
   {
     return this.sLoiDan;
   }

   public void setSLoiDan(String loiDan)
   {
     this.sLoiDan = loiDan;
   }

   public String getTenLoiDan()
   {
     return this.tenLoiDan;
   }

   public void setTenLoiDan(String tenLoiDan)
   {
     this.tenLoiDan = tenLoiDan;
   }

   public String getMabs()
   {
     return this.mabs;
   }

   public void setMabs(String mabs)
   {
     this.mabs = mabs;
   }

   public Integer getMasobs()
   {
     return this.masobs;
   }

   public void setMasobs(Integer masobs)
   {
     this.masobs = masobs;
   }

   public String getTenLoiDans()
   {
     return this.tenLoiDans;
   }

   public void setTenLoiDans(String tenLoiDans)
   {
     this.tenLoiDans = tenLoiDans;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getTenbs()
   {
     return this.tenbs;
   }

   public void setTenbs(String tenbs)
   {
     this.tenbs = tenbs;
   }

   public String getChandoan()
   {
     return this.chandoan;
   }

   public void setChandoan(String chandoan)
   {
     this.chandoan = chandoan;
   }

   public int getSobaithuoc()
   {
     return this.sobaithuoc;
   }

   public void setSobaithuoc(int sobaithuoc)
   {
     this.sobaithuoc = sobaithuoc;
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

   public Integer getThuocdongyMaso()
   {
     return this.thuocdongyMaso;
   }

   public void setThuocdongyMaso(Integer thuocdongyMaso)
   {
     this.thuocdongyMaso = thuocdongyMaso;
   }

   public List<ThuocPhongKhamExt> getListTPKBanKhamTruoc()
   {
     return this.listTPKBanKhamTruoc;
   }

   public void setListTPKBanKhamTruoc(ArrayList<ThuocPhongKhamExt> listTPKBanKhamTruoc)
   {
     this.listTPKBanKhamTruoc = listTPKBanKhamTruoc;
   }

   @End
   public void end() {}

   public void destroyService() {}

   @Destroy
   public void destroy() {}

   public class ThuocPhongKhamExt
   {
     ThuocPhongKham tpk;
     String bankhamTen;

     public ThuocPhongKhamExt() {}

     public ThuocPhongKhamExt(ThuocPhongKham tpk, String bankhamTen)
     {
       this.tpk = tpk;
       this.bankhamTen = bankhamTen;
     }

     public ThuocPhongKham getTpk(boolean created)
     {
       if (created) {
         this.tpk = new ThuocPhongKham();
       }
       return this.tpk;
     }

     public ThuocPhongKham getTpk()
     {
       return this.tpk;
     }

     public void setTpk(ThuocPhongKham tpk)
     {
       this.tpk = tpk;
     }

     public String getBankhamTen()
     {
       return this.bankhamTen;
     }

     public void setBankhamTen(String bankhamTen)
     {
       this.bankhamTen = bankhamTen;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.XuTriThuocTaiBanKhamAction

 * JD-Core Version:    0.7.0.1

 */