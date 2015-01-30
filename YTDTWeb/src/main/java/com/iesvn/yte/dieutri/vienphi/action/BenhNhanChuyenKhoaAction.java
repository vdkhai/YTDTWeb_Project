 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTang;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3114_Benhnhanchuyenkhoa")
 @Synchronized(timeout=6000000L)
 public class BenhNhanChuyenKhoaAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(BenhNhanChuyenKhoaAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private Hsba hsba;
   private HsbaKhoa hsbaKhoa;
   private DmTang tangDangDieuTri;
   private DmKhoa khoaChuyenDen;
   private DmTang tangChuyenDen;
   private String gioChuyenKhoa;
   private String ngayChuyenKhoa;
   private String ngayHt;
   private DmKhoaDelegate dmKhoaDel = DmKhoaDelegate.getInstance();
   private HashMap<String, DmKhoa> hmDmKhoaNTAll = new HashMap();
   private List<SelectItem> listDmKhoaNTs = new ArrayList();
   private List<DmKhoa> listDmKhoaNTAll = new ArrayList();
   private List<SelectItem> listDmTangs = new ArrayList();
   private String hidGhiNhan = "false";

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     this.ngayHt = Utils.getCurrentDate();
     reset();
     refreshDmKhoaNT();
     return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanChuyenKhoa";
   }

   @End
   public void endFunct() {}

   public void end()
   {
     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
     String hsbaMa = hsbaKhoaDelegate.benhNhanChuyenKhoa(this.hsbaKhoa, this.khoaChuyenDen, null, this.tangChuyenDen);
     if ((hsbaMa != null) && (!hsbaMa.equals(""))) {
       FacesMessages.instance().add(IConstantsRes.CAP_NHAT_THANH_CONG_VOI_SO_VAO_VIEN, new Object[] { hsbaMa });
     } else {
       FacesMessages.instance().add(IConstantsRes.UPDATE_FAIL, new Object[0]);
     }
     reset();
   }

   public void loadHsba()
   {
     logger.info("-----loadHsba()-----");
     this.hidGhiNhan = "false";
     String hsbaMa = this.hsba.getHsbaSovaovien();
     if ((hsbaMa == null) || (hsbaMa.equals("")))
     {
       logger.info("hsba.getHsbaSovaovien() == null");
       reset();
       return;
     }
     try
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       Hsba hsbaCur = hsbaDelegate.find(hsbaMa);
       if (hsbaCur == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
         logger.info("khong tim thay sobenhan");
         reset();
         return;
       }
       HsThtoan hsthtoan = HsThtoanDelegate.getInstance().findBySovaovien(hsbaMa);
       if ((hsthtoan != null) && (hsthtoan.getHsthtoanNgaytt() != null))
       {
         this.hidGhiNhan = "true";
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
       }
       this.hsba = hsbaCur;
       if (this.hsba.getHsbaKhoadangdt() == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
         logger.info("khoaDangDieuTri == null");
         reset();
         return;
       }
       HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
       this.hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsba.getHsbaSovaovien(), this.hsba.getHsbaKhoadangdt(true).getDmkhoaMa());
       if (this.hsbaKhoa == null)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
         reset();
         return;
       }
       this.khoaChuyenDen = new DmKhoa();
       this.tangChuyenDen = new DmTang();
       this.tangDangDieuTri = this.hsbaKhoa.getDmtangMaso();
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { hsbaMa });
       e.printStackTrace();
       reset();
     }
   }

   public void reset()
   {
     this.gioChuyenKhoa = "";
     this.ngayChuyenKhoa = "";
     this.hsba = new Hsba();
     this.hsbaKhoa = new HsbaKhoa();

     this.tangDangDieuTri = new DmTang();
     this.khoaChuyenDen = new DmKhoa();
     this.tangChuyenDen = new DmTang();
     this.hidGhiNhan = "false";
   }

   public void onblurMaKhoaAction()
   {
     logger.info("-----BEGIN onblurMaKhoaAction()-----");
     if ((this.khoaChuyenDen != null) && (this.khoaChuyenDen.getDmkhoaMa() != null))
     {
       String maKhoa = this.khoaChuyenDen.getDmkhoaMa();
       if (this.hmDmKhoaNTAll != null)
       {
         DmKhoa dmKhoa = (DmKhoa)this.hmDmKhoaNTAll.get(maKhoa.toUpperCase());
         if (dmKhoa != null)
         {
           this.khoaChuyenDen = dmKhoa;
           logger.info("Tim ma khoa: Da thay khoa " + this.khoaChuyenDen.getDmkhoaTen());
         }
         else
         {
           this.khoaChuyenDen = new DmKhoa();
           return;
         }
       }
       this.tangChuyenDen = new DmTang();
       refreshDmTang();
     }
     logger.info("-----END onblurMaKhoaAction()-----");
   }

   public void onblurTenKhoaAction()
   {
     logger.info("-----BEGIN onblurTenKhoaAction()-----");
     if ((this.khoaChuyenDen != null) && (this.khoaChuyenDen.getDmkhoaTen() != null))
     {
       String tenKhoa = this.khoaChuyenDen.getDmkhoaTen();
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
         this.khoaChuyenDen.setDmkhoaMaso(dmKhoa_Finded.getDmkhoaMaso());
         this.khoaChuyenDen.setDmkhoaMa(dmKhoa_Finded.getDmkhoaMa());
         this.khoaChuyenDen.setDmkhoaTen(dmKhoa_Finded.getDmkhoaTen());
       }
       else
       {
         this.khoaChuyenDen = new DmKhoa();
         return;
       }
       this.tangChuyenDen = new DmTang();
       refreshDmTang();
     }
     logger.info("-----END onblurTenKhoaAction()-----");
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
     if ((this.khoaChuyenDen != null) && (this.khoaChuyenDen.getDmkhoaMaso() != null))
     {
       String khoaMa = this.khoaChuyenDen.getDmkhoaMa().toUpperCase();
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

   public String getGioChuyenKhoa()
   {
     return this.gioChuyenKhoa;
   }

   public void setGioChuyenKhoa(String gioChuyenKhoa)
   {
     this.gioChuyenKhoa = gioChuyenKhoa;
   }

   public String getNgayChuyenKhoa()
   {
     return this.ngayChuyenKhoa;
   }

   public void setNgayChuyenKhoa(String ngayChuyenKhoa)
   {
     this.ngayChuyenKhoa = ngayChuyenKhoa;
   }

   public static Logger getLogger()
   {
     return logger;
   }

   public static void setLogger(Logger logger)
   {
     logger = logger;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public HsbaKhoa getHsbaKhoa()
   {
     return this.hsbaKhoa;
   }

   public void setHsbaKhoa(HsbaKhoa hsbaKhoa)
   {
     this.hsbaKhoa = hsbaKhoa;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public DmKhoa getKhoaChuyenDen()
   {
     return this.khoaChuyenDen;
   }

   public void setKhoaChuyenDen(DmKhoa khoaChuyenDen)
   {
     this.khoaChuyenDen = khoaChuyenDen;
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

   public String getHidGhiNhan()
   {
     return this.hidGhiNhan;
   }

   public void setHidGhiNhan(String hidGhiNhan)
   {
     this.hidGhiNhan = hidGhiNhan;
   }

   public DmTang getTangDangDieuTri()
   {
     return this.tangDangDieuTri;
   }

   public void setTangDangDieuTri(DmTang tangDangDieuTri)
   {
     this.tangDangDieuTri = tangDangDieuTri;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BenhNhanChuyenKhoaAction

 * JD-Core Version:    0.7.0.1

 */