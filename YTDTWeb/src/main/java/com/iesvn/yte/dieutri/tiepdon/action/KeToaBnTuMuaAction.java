 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
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
 import java.io.PrintStream;
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
 @Name("B121_5_Ketoabntumua")
 @Synchronized(timeout=6000000L)
 public class KeToaBnTuMuaAction
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
   private static Logger log = Logger.getLogger(KeToaBnTuMuaAction.class);
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String initB121_5_Ketoabntumua;
   @DataModel
   private List<ThuocPhongKham> ctThuocPhongKhams;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private String returnToXuatHangChoBNBHYT;
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
   @DataModel
   private List<ThuocPhongKhamExt> listTPKBanKhamTruoc;
   private int ctThuocPhongKhamSelectedOld;
   private boolean updateNhapct;
   private ThuocPhongKham ctThuocPhongKham;
   private DmDoiTuong doiTuong;
   @In(required=false)
   @Out(required=false)
   private String loaiToaThuocThamKhamVaXuTri;
   private String titleThamKhamVaXuTri;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   private String maFinish;
   private Integer dmBaithuocMaso;
   private String dmBaithuocMa;
   private Integer dmthuocMaso;
   private String dmthuocMa;
   private String dmthuocTen;
   private Double thuocphongkhamSoluong;
   private Integer dmdonvitinhMaso;
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
     log.info("-----BEGIN onblurMaThuocAction()-----");
     if (this.hmDmThuocAll != null)
     {
       DmThuoc dmThuoc1 = (DmThuoc)this.hmDmThuocAll.get(this.dmthuocMa.toUpperCase());
       if (dmThuoc1 != null)
       {
         DmThuoc dmThuoc2 = this.dmThuocDelegate.findByMaThuoc(this.dmthuocMa.toUpperCase());
         setDmthuocMaso(dmThuoc2.getDmthuocMaso());
         setDmthuocMa(dmThuoc2.getDmthuocMa());
         setDmthuocTen(dmThuoc2.getDmthuocTen());
         log.info("-----DA THAY DMTHUOC-----");
         setHamluong(dmThuoc1.getDmthuocHamluong());
         setDmdonvitinhTen(dmThuoc1.getDmdonvitinhMaso().getDmdonvitinhTen());
         setDmdonvitinhMaso(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhMaso());
       }
       else
       {
         setDmthuocMaso(null);
         setDmthuocMa("");
         setDmthuocTen("");
         setHamluong("");
         setDmdonvitinhTen("");
         setDmdonvitinhMaso(null);
       }
     }
     setMaChiDan("");
     setTenChiDan("");
     log.info("-----END onblurMaThuocAction()-----");
   }

   public void onblurTenThuocAction()
   {
     log.info("-----BEGIN onblurTenThuocAction()-----");
     if (this.dmthuocTen.length() > 6)
     {
       String maThuoc = this.dmthuocTen.substring(this.dmthuocTen.length() - 6);
       System.out.println("MA THUOC(onblurTenThuoc): " + maThuoc);
       DmThuoc dmThuoc2 = this.dmThuocDelegate.findByMaThuoc(maThuoc);
       if (dmThuoc2 != null)
       {
         setDmthuocMaso(dmThuoc2.getDmthuocMaso());
         setDmthuocMa(dmThuoc2.getDmthuocMa());
         setDmthuocTen(dmThuoc2.getDmthuocTen());

         setHamluong(dmThuoc2.getDmthuocHamluong());
         setDmdonvitinhTen(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhTen());
         setDmdonvitinhMaso(dmThuoc2.getDmdonvitinhMaso().getDmdonvitinhMaso());
       }
       else
       {
         setDmthuocMaso(null);
         setDmthuocMa("");
         setDmthuocTen("");
         setHamluong("");
         setDmdonvitinhTen("");
         setDmdonvitinhMaso(null);
       }
     }
     else
     {
       setDmthuocMaso(null);
       setDmthuocMa("");
       setDmthuocTen("");
       setDmdonvitinhTen("");
       setDmdonvitinhMaso(null);
       setHamluong("");
     }
     setMaChiDan("");
     setTenChiDan("");

     log.info("-----END onblurTenThuocAction()-----");
   }

   public void refreshDmThuoc()
   {
     this.dmThuocDelegate = DmThuocDelegate.getInstance();
     this.listDmThuocs.clear();
     if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       this.listDmThuocAll = this.dmThuocDelegate.findAll();
     }
     System.out.println("listDmThuocAll.size: " + this.listDmThuocAll.size());

     this.hmDmThuocAll.clear();
     for (DmThuoc o : this.listDmThuocAll) {
       this.hmDmThuocAll.put(o.getDmthuocMa(), o);
     }
     if ((this.listDmThuocAll != null) && (this.listDmThuocAll.size() > 0))
     {
       if (this.searchType.equals("2"))
       {
         log.info("-----Tim theo ten Khoa hoc-----");
         this.listDmThuocs.clear();
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTenkh() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTen() + " - " + each.getDmthuocMa()));
         }
       }
       else
       {
         log.info("-----Tim theo ten Thuong mai-----");
         this.listDmThuocs.clear();
         for (DmThuoc each : this.listDmThuocAll) {
           this.listDmThuocs.add(new SelectItem(each.getDmthuocTen() + " - " + each.getDmdonvitinhMaso(true).getDmdonvitinhTen() + " - " + each.getDmthuocTenkh() + " - " + each.getDmthuocMa()));
         }
       }
       this.listDmThuocAll.clear();
     }
   }

   public String thuchienAction_toa_thuoc_bn_ve()
   {
     if (Utils.KE_TOA_BENH_NHAN_TU_MUA.equals(this.loaiToaThuocThamKhamVaXuTri)) {
       this.reportTypeTD = "KeToaVe";
     }
     ThamKhamUtil tkUtil = new ThamKhamUtil();
     tkUtil.reportTypeTD = this.reportTypeTD;
     String fileName = tkUtil.XuatReportBNVe(log, this.thamkham, this.listCtTPK_temp, this.clslist, this.ctThuocPhongKhams, this.index);
     if (fileName.equals(""))
     {
       FacesMessages.instance().add(IConstantsRes.IN_TOA_THUOC_ERROR, new Object[0]);
       return "";
     }
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
       log.info("***** troVe()");
       this.ctThuocPhongKhamSelectedOld = -1;

       this.ctThuocPhongKhams = null;
       this.ctThuocPhongKhamSelected = null;
       this.ctThuocPhongKham = null;
       this.thamkham = null;

       return "B121_3_Xutrithuoctaibankham";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public void showCLSBanKhamTruoc()
   {
     log.info("Begin showCLSBanKhamTruoc");
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
     log.info("End showCLSBanKhamTruoc");
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
     log.info("Begin addBaiThuoc, sobaithuoc = " + this.sobaithuoc);
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
         this.ctThuocPhongKham.setThuocphongkhamChedott(this.thamkham.getTiepdonMa(true).getDoituongMa(true));
         this.ctThuocPhongKham.setDmbaithuocMaso(this.dmBaithuocMaso);
         if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA))
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
     log.info("*****Begin enterAJAX()");
     this.ctThuocPhongKham = new ThuocPhongKham();
     try
     {
       DmThuoc dmThuoc = new DmThuoc();
       dmThuoc.setDmthuocMaso(this.dmthuocMaso);
       dmThuoc.setDmthuocMa(this.dmthuocMa);
       dmThuoc.setDmthuocTen(this.dmthuocTen);
       this.ctThuocPhongKham.setThuocphongkhamMathuoc(dmThuoc);
       this.ctThuocPhongKham.setThuocphongkhamSoluong(getThuocphongkhamSoluong());
       this.ctThuocPhongKham.setThuocphongkhamBacsi(this.thamkham.getThamkhamBacsi());
       this.ctThuocPhongKham.setThuocphongkhamNhanviencn(this.nhanVienCN);
       this.ctThuocPhongKham.setThuocphongkhamNgaygio(new Date());

       this.ctThuocPhongKham.setThuocphongkhamMaChidan(this.maChiDan);
       this.ctThuocPhongKham.setThuocphongkhamChidan(this.tenChiDan);

       String doituongMa = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       DieuTriUtilDelegate dtUtilDel = DieuTriUtilDelegate.getInstance();
       DmDonViTinh dvt = (DmDonViTinh)dtUtilDel.findByMaSo(this.dmdonvitinhMaso, "DmDonViTinh", "dmdonvitinhMaso");
       this.ctThuocPhongKham.getThuocphongkhamMathuoc().setDmdonvitinhMaso(dvt);
       this.ctThuocPhongKham.setThuocphongkhamChedott(this.thamkham.getTiepdonMa(true).getDoituongMa(true));
       this.ctThuocPhongKham.setThuocphongkhamDongiatt(new Double(0.0D));
       this.ctThuocPhongKham.setThuocphongkhamThanhtien(new Double(0.0D));


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
           setHandung("");
           setDmthuocMa("");
           setDmthuocMaso(null);
           setDmthuocTen("");
           setDmdonvitinhMaso(null);
           setDmdonvitinhTen("");
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
       System.out.println("viTri: " + viTri);
       if (viTri == -1)
       {
         if (this.ctThuocPhongKhams.size() > 0)
         {
           int vt = -1;
           double sl = 0.0D;
           boolean foundOnNewsList = false;
           for (int i = 0; i < this.ctThuocPhongKhams.size(); i++)
           {
             ThuocPhongKham ctTpk = (ThuocPhongKham)this.ctThuocPhongKhams.get(i);
             if (this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa().equals(ctTpk.getThuocphongkhamMathuoc().getDmthuocMa()))
             {
               foundOnNewsList = true;
               vt = i;
               sl = this.ctThuocPhongKham.getThuocphongkhamSoluong().doubleValue() + ctTpk.getThuocphongkhamSoluong().doubleValue();
               break;
             }
           }
           if (!foundOnNewsList)
           {
             this.ctThuocPhongKhams.add(this.ctThuocPhongKham);
           }
           else
           {
             this.ctThuocPhongKham.setThuocphongkhamSoluong(new Double(sl));
             this.ctThuocPhongKhams.set(vt, this.ctThuocPhongKham);
           }
         }
         else
         {
           this.ctThuocPhongKhams.add(this.ctThuocPhongKham);
         }
       }
       else {
         this.ctThuocPhongKhams.add(viTri, this.ctThuocPhongKham);
       }
       this.maChiDan = "";
       this.tenChiDan = "";
       setThuocdongyMaso(Integer.valueOf(0));
       setHamluong("");
       setHandung("");
       setDmthuocMa("");
       setDmthuocMaso(null);
       setDmthuocTen("");
       setDmdonvitinhMaso(null);
       setDmdonvitinhTen("");
       setThuocphongkhamSoluong(null);
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
       log.info("*****Exception = " + e);
     }
     this.ctThuocPhongKham = new ThuocPhongKham();
     log.info("*****End enterAJAX()");
   }

   public String getDvtName(int index)
   {
     log.info("*****getDVTName()");
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
     log.info("*****Begin chinhSuaAJAX()");









     this.ctThuocPhongKhamSelected = ((ThuocPhongKham)this.ctThuocPhongKhams.get(index));
     if (this.ctThuocPhongKhamSelected == null)
     {
       log.info("ctThuocPhongKhamSelected == null");
       return;
     }
     if ((this.ctThuocPhongKhamSelected != null) && (this.ctThuocPhongKhamSelected.getThuocphongkhamMaphieud() != null) && (!this.ctThuocPhongKhamSelected.getThuocphongkhamMaphieud().equals("")))
     {
       log.info("ctThuocPhongKhamSelected != null :" + this.ctThuocPhongKhamSelected);
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
     setDmdonvitinhMaso(dvt.getDmdonvitinhMaso());
     setDmdonvitinhTen(dvt.getDmdonvitinhTen());
     setDmthuocMaso(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMaso());
     setDmthuocMa(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocMa());
     setDmthuocTen(this.ctThuocPhongKham.getThuocphongkhamMathuoc().getDmthuocTen());
     setThuocphongkhamSoluong(this.ctThuocPhongKham.getThuocphongkhamSoluong());

     this.ctThuocPhongKhamSelectedOld = index;
     log.info("*****End chinhSuaAJAX()");
   }

   public void xoaAJAX(int index)
     throws Exception
   {
     log.info("*****Begin xoaAJAX()");
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
       log.info("*****Exception = " + e);
     }
     log.info("*****End xoaAJAX()");
   }

   public KeToaBnTuMuaAction()
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











     this.isReport = "false";


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
     this.initB121_5_Ketoabntumua = "no";
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

     this.doiTuong = this.thamkham.getTiepdonMa(true).getDoituongMa(true);
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

   @Factory("initB121_5_Ketoabntumua")
   @Begin(nested=true)
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     tempInit();
     this.fromMenu = "";
     log.info("***Finished init ***");
   }

   private void tempInit()
   {
     try
     {
       initService();

       this.sLoiDan = "";
       this.tenLoiDans = "";

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
       if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
         if (IConstantsRes.IS_USED_KHOTE == "0")
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
       }
       this.khoaMaSo = dmKhoa.getDmkhoaMaso();
       this.khoaMa = dmKhoa.getDmkhoaMa();
       this.doiTuong = this.thamkham.getTiepdonMa().getDoituongMa();

       this.initB121_5_Ketoabntumua = "0";

       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();

       this.ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.thamkham.getTiepdonMa(true).getTiepdonMa(), this.thamkham.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       if ((this.ctThuocPhongKhams != null) && (this.ctThuocPhongKhams.size() > 0))
       {
         this.tenLoiDans = ((ThuocPhongKham)this.ctThuocPhongKhams.get(0)).getThuocphongkhamTenloidan();
         log.info("****ctThuocPhongKhams SIZE: " + this.ctThuocPhongKhams.size());
         log.info("****ctThuocPhongKhams MA: " + ((ThuocPhongKham)this.ctThuocPhongKhams.get(0)).getThuocphongkhamMa());
         log.info("****LOI DAN: " + this.sLoiDan);
         for (int i = 0; i < this.ctThuocPhongKhams.size(); i++) {
           if ((((ThuocPhongKham)this.ctThuocPhongKhams.get(i)).getThuocphongkhamMaloidan() != null) && (!((ThuocPhongKham)this.ctThuocPhongKhams.get(i)).getThuocphongkhamMaloidan().equals("")))
           {
             this.tenLoiDans = ((ThuocPhongKham)this.ctThuocPhongKhams.get(i)).getThuocphongkhamTenloidan();
             log.info("****LOI DAN index i: " + i + this.sLoiDan);

             break;
           }
         }
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
       Iterator i$ = this.ctThuocPhongKhams.iterator();
       if (i$.hasNext())
       {
         ThuocPhongKham tpk = (ThuocPhongKham)i$.next();

         this.maFinish = tpk.getThuocphongkhamThamkham().getThamkhamMa().toString();
         log.info("-----ma phieu: " + this.maFinish);
       }
       refreshDmThuoc();

       setOtherValue();
       destroyService();
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
       log.info("DA THAY thamkham_loadthuoc " + thamkham_loadthuoc.getThamkhamMa());
       List<ThuocPhongKham> ctToaThuocTruoc = thuocPKDele.findByMaTiepDonAndMaBanKham(thamkham_loadthuoc.getTiepdonMa(true).getTiepdonMa(), thamkham_loadthuoc.getThamkhamBankham(true).getDtdmbankhamMa(), String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       try
       {
         if ((ctToaThuocTruoc != null) && (ctToaThuocTruoc.size() > 0))
         {
           List<ThuocPhongKham> listTpk_Temp = new ArrayList();
           for (ThuocPhongKham toaThuocTruoc : ctToaThuocTruoc)
           {
             toaThuocTruoc.setThuocphongkhamMaphieud(null);
             toaThuocTruoc.setThuocphongkhamDatt(null);
             toaThuocTruoc.setThuocphongkhamMa(null);
             toaThuocTruoc.setThuocphongkhamNgaygio(new Date());
             toaThuocTruoc.setThuocphongkhamNgaygiocn(new Date());
             toaThuocTruoc.setThuocphongkhamMalk(null);
             if (this.thamkham.getThamkhamBacsi() != null) {
               toaThuocTruoc.setThuocphongkhamBacsi(this.thamkham.getThamkhamBacsi());
             }
             ThuocPhongKham thuocphongkhamNew = new ThuocPhongKham();
             if (listTpk_Temp.size() == 0)
             {
               thuocphongkhamNew = (ThuocPhongKham)BeanUtils.cloneBean(toaThuocTruoc);
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
                 if (thuocphongkhamNew1.getThuocphongkhamMathuoc().getDmthuocMaso().equals(toaThuocTruoc.getThuocphongkhamMathuoc().getDmthuocMaso()))
                 {
                   thuocphongkham_Finded = thuocphongkhamNew1;
                   foundInList = true;
                   vitri = j;
                   break;
                 }
               }
               if (foundInList == true)
               {
                 thuocphongkhamNew = (ThuocPhongKham)BeanUtils.cloneBean(toaThuocTruoc);
                 thuocphongkhamNew.setThuocphongkhamSoluong(Double.valueOf(thuocphongkhamNew.getThuocphongkhamSoluong().doubleValue() + thuocphongkham_Finded.getThuocphongkhamSoluong().doubleValue()));
                 listTpk_Temp.set(vitri, thuocphongkhamNew);
               }
               else
               {
                 thuocphongkhamNew = (ThuocPhongKham)BeanUtils.cloneBean(toaThuocTruoc);
                 listTpk_Temp.add(thuocphongkhamNew);
               }
             }
             thuocphongkhamNew = new ThuocPhongKham();
           }
           System.out.println("listTpk_Temp.size: " + listTpk_Temp.size());
           for (ThuocPhongKham toaThuocTruoc : listTpk_Temp) {
             this.ctThuocPhongKhams.add(toaThuocTruoc);
           }
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
     log.info("END getToaThuocTruoc() ctThuocPhongKhams.size() " + this.ctThuocPhongKhams.size());
   }

   public void initService()
   {
     if (this.loaiToaThuocThamKhamVaXuTri.equals(Utils.KE_TOA_BENH_NHAN_TU_MUA)) {
       this.titleThamKhamVaXuTri = IConstantsRes.KE_TOA_VE;
     }
     this.hienThiInPhieu = Boolean.valueOf(true);

     log.info("titleThamKhamVaXuTri:" + this.titleThamKhamVaXuTri);
     log.info("loaiToaThuocThamKhamVaXuTri:" + this.loaiToaThuocThamKhamVaXuTri);
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
     log.info("*****Begin ghiNhanAjax() *****");

     HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
     if ((this.thamkham.getTiepdonMa(true).getTiepdonMa() != null) && (!this.thamkham.getTiepdonMa(true).getTiepdonMa().equals("")) &&
       (this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa().equals("BH")))
     {
       HsThtoank hsttk_tmp = hsttkDelegate.findBytiepdonMa(this.thamkham.getTiepdonMa(true).getTiepdonMa());
       if (hsttk_tmp != null)
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         return "";
       }
     }
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
           System.out.println("Ghi nhan ----thuocDY.Maso= " + thuocDY.getThuocdongyMaso());
           System.out.println("Ghi nhan-----bai thuoc ma so= " + thuocDY.getDmbaithuocMaso().getDmbaithuocMaso());
           thuocDY.setThuocdongyThamkham(this.thamkham);
           thuocDY.setTiepdonMa(this.thamkham.getTiepdonMa(true));
           listThuocDY.set(0, thuocDY);
           hsmThuocDYNGTTemp.put(thuocDY.getDmbaithuocMaso().getDmbaithuocMaso(), listThuocDY);
         }
       }
       this.hsmListThuocDYNgoaiTru = hsmThuocDYNGTTemp;
       for (ThuocPhongKham tpk : this.ctThuocPhongKhams)
       {
         RemoveUtil.removeIfNullForThuocPhongKham(tpk);
         tpk.setThuocphongkhamThamkham(this.thamkham);

         log.info(this.loaiToaThuocThamKhamVaXuTri);
         tpk.setThuocphongkhamLoai(String.valueOf(this.loaiToaThuocThamKhamVaXuTri));

         DmKhoa dmKhoa = new DmKhoa();
         dmKhoa.setDmkhoaMaso(this.khoaMaSo);
         tpk.setThuocphongkhamKhoa(dmKhoa);

         tpk.setThuocphongkhamTenloidan(this.tenLoiDans);


         tpk.setThuocphongkhamDongiabh(tpk.getThuocphongkhamDongia());

         System.out.println("Bai Thuoc ma so: " + tpk.getDmbaithuocMaso());
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
       String result = tpkDelegate.createKeToaVe(this.ctThuocPhongKhams, this.thamkham, this.loaiToaThuocThamKhamVaXuTri, this.hsmListThuocDYNgoaiTru);
       if (result.indexOf("ERROR") > -1) {
         FacesMessages.instance().add(result, new Object[0]);
       }
       ThuocPhongKhamDelegate thuocPKDele = ThuocPhongKhamDelegate.getInstance();
       this.ctThuocPhongKhams = thuocPKDele.findByMaTiepDonAndMaBanKham(this.maTiepDonOut, this.maBanKhamOut, String.valueOf(this.loaiToaThuocThamKhamVaXuTri));
       if (this.ctThuocPhongKhams == null) {
         this.ctThuocPhongKhams = new ArrayList();
       }
       log.info("goi lai thong tin thuoc pk s:" + this.ctThuocPhongKhams);
       for (ThuocPhongKham tpk2 : this.ctThuocPhongKhams)
       {
         this.maFinish = tpk2.getThuocphongkhamThamkham().getThamkhamMa().toString();
         log.info("-----ma phieu: " + this.maFinish);
       }
       setOtherValue();


       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.hsmListThuocDYNgoaiTru.clear();
       for (ThuocPhongKham tpk2 : this.ctThuocPhongKhams) {
         log.info("tpk.getThuocphongkhamDatt222()" + tpk2.getThuocphongkhamDatt());
       }
       this.ctThuocPhongKhamSelectedOld = -1;
       this.ctThuocPhongKhamSelected = new ThuocPhongKham();
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);

       e.printStackTrace();
       log.error("*************loi***********" + e.toString());
     }
     log.info("*****End ghiNhanAjax() *****");






     return null;
   }

   public String quaylai()
     throws Exception
   {
     this.initB121_5_Ketoabntumua = null;
     if ((this.returnToXuatHangChoBNBHYT != null) && (!this.returnToXuatHangChoBNBHYT.equals("")))
     {
       this.returnToXuatHangChoBNBHYT = null;
       return "QuanLyKhoBHYT_XuatKhoBHYT_XuatHangChoBenhNhan";
     }
     if ((this.returnToThuocYDungCuPhongKham != null) && (!this.returnToThuocYDungCuPhongKham.equals("")))
     {
       this.returnToThuocYDungCuPhongKham = null;
       return "ThuVienPhi_SoLieuKhamBenh_ThuocYDungCuPhongKham";
     }
     return "ghinhan";
   }

   @Destroy
   public void destroy()
   {
     log.info("************************* destroy KeToaBenhNhanTuMua");
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

   public String getInitB121_5_Ketoabntumua()
   {
     return this.initB121_5_Ketoabntumua;
   }

   public void setInitB121_5_Ketoabntumua(String initB121_5_Ketoabntumua)
   {
     this.initB121_5_Ketoabntumua = initB121_5_Ketoabntumua;
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

   public String getReturnToXuatHangChoBNBHYT()
   {
     return this.returnToXuatHangChoBNBHYT;
   }

   public void setReturnToXuatHangChoBNBHYT(String returnToXuatHangChoBNBHYT)
   {
     this.returnToXuatHangChoBNBHYT = returnToXuatHangChoBNBHYT;
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

   public Integer getDmthuocMaso()
   {
     return this.dmthuocMaso;
   }

   public void setDmthuocMaso(Integer dmthuocMaso)
   {
     this.dmthuocMaso = dmthuocMaso;
   }

   public String getDmthuocMa()
   {
     return this.dmthuocMa;
   }

   public void setDmthuocMa(String dmthuocMa)
   {
     this.dmthuocMa = dmthuocMa;
   }

   public String getDmthuocTen()
   {
     return this.dmthuocTen;
   }

   public void setDmthuocTen(String dmthuocTen)
   {
     this.dmthuocTen = dmthuocTen;
   }

   public Double getThuocphongkhamSoluong()
   {
     return this.thuocphongkhamSoluong;
   }

   public void setThuocphongkhamSoluong(Double thuocphongkhamSoluong)
   {
     this.thuocphongkhamSoluong = thuocphongkhamSoluong;
   }

   public Integer getDmdonvitinhMaso()
   {
     return this.dmdonvitinhMaso;
   }

   public void setDmdonvitinhMaso(Integer dmdonvitinhMaso)
   {
     this.dmdonvitinhMaso = dmdonvitinhMaso;
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

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.KeToaBnTuMuaAction

 * JD-Core Version:    0.7.0.1

 */