 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3112_Benhnhantrathuoc")
 @Synchronized(timeout=6000000L)
 public class BenhNhanTraThuocAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(BenhNhanTraThuocAction.class);
   @DataModel
   private ArrayList<ThuocNoiTru> listTnt;
   @DataModelSelection
   @Out(required=false)
   private ThuocNoiTru tntSelected;
   private Hsba hsba;
   private String maKhoa;
   private String ngayHienTai;
   private Integer updateItem;
   private Integer count;
   private String tra;
   private String maThuoc;
   private String dg;
   private String sl;
   private String dv;
   private String tenthuoc;

   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     reset();
     return "VienPhiTaiKhoa_SoLieuBNSuDung_BenhNhanTraThuoc";
   }

   @End
   public void endFunct() {}

   public void reset()
   {
     logger.info("-----reset()-----");
     this.listTnt = new ArrayList();
     this.hsba = new Hsba();
     this.ngayHienTai = Utils.getCurrentDate();
     this.updateItem = Integer.valueOf(0);
     this.count = Integer.valueOf(this.listTnt.size());
     this.tra = "";
     this.maThuoc = "";
     this.dg = "";
     this.sl = "";
     this.maKhoa = "";
     this.tenthuoc = "";
     this.dv = "";
   }

   public void tiepTucNhap()
   {
     logger.info("-----tiepTucNhap()-----");
     if (this.updateItem.intValue() != -1)
     {
       ThuocNoiTru tnt = (ThuocNoiTru)this.listTnt.get(this.updateItem.intValue());
       if (tnt.getThuocnoitruTutrucPdt().intValue() == 1) {
         tnt.setThuocnoitruTra(new Double(this.tra));
       } else if (tnt.getThuocnoitruTutrucPdt().intValue() == 0) {
         tnt.setThuocnoitruTra(Double.valueOf(this.tra));
       }
       this.maThuoc = "";
       this.dg = "";
       this.sl = "";
       this.tra = "";
       this.tenthuoc = "";
       this.dv = "";
     }
   }

   public void end()
   {
     logger.info("-----end()-----");
     if (this.listTnt.size() > 0)
     {
       ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
       if (tntDelegate.benhNhanTraThuoc(this.listTnt).booleanValue()) {
         FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       }
     }
   }

   public void loadHsba()
   {
     logger.info("-----loadHsba()-----");

     HsbaKhoaDelegate hsbaKhoaDelegate = HsbaKhoaDelegate.getInstance();
     HsbaKhoa hsbaKhoa = hsbaKhoaDelegate.findBySoVaoVienAndKhoaMa(this.hsba.getHsbaSovaovien(), this.maKhoa);
     if (hsbaKhoa == null)
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.hsba.getHsbaSovaovien() });
       reset();
       return;
     }
     this.hsba = hsbaKhoa.getHsbaSovaovien();
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date ngayNhanThuoc = null;
     ThuocNoiTruDelegate tntDelegate = ThuocNoiTruDelegate.getInstance();
     try
     {
       if (this.ngayHienTai != null) {
         ngayNhanThuoc = sdf.parse(this.ngayHienTai);
       }
       if (this.hsba.getHsbaSovaovien() != null)
       {
         this.listTnt = ((ArrayList)tntDelegate.findBySoVaoVienAndKhoaMaAndLanAndNgayNhan(this.hsba.getHsbaSovaovien(), this.maKhoa, null, ngayNhanThuoc));
         if ((this.listTnt == null) || (this.listTnt.size() == 0))
         {
           FacesMessages.instance().add(IConstantsRes.THUOC_KHOA_HSBA_NULL, new Object[] { this.hsba.getHsbaSovaovien(), this.maKhoa, sdf.format(ngayNhanThuoc) });
           reset();
         }
         else
         {
           this.hsba = hsbaKhoa.getHsbaSovaovien();

           logger.info("-----hsba: " + this.hsba);
           logger.info("-----listTnt: " + this.listTnt);
         }
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   public void select(int index)
   {
     logger.info("-----select(" + index + ")-----");
     this.updateItem = Integer.valueOf(index);
     ThuocNoiTru tnt = (ThuocNoiTru)this.listTnt.get(index);
     System.out.println("Tu truc PDT: " + tnt.getThuocnoitruTutrucPdt());
     if (tnt.getThuocnoitruTutrucPdt().intValue() == 0)
     {
       logger.info("-----tra hang theo pdt");
       if (tnt.getThuocnoitruMaPhieuDT() != null)
       {
         if (tnt.getThuocnoitruMaphieupdttra() == null) {
           if (tnt.getThuocnoitruStatus() == "0")
           {
             logger.info("-----chua xuat hang theo pdt");
             this.maThuoc = "";
             this.dg = "";
             this.sl = "";
             this.tra = "";
             this.tenthuoc = "";
             this.dv = "";
             FacesMessages.instance().add(IConstantsRes.BENHNHANTRATHUOC_PDT_XUAT_NULL, new Object[0]);
           }
           else if ((tnt.getThuocnoitruStatus().equals("2")) || (tnt.getThuocnoitruStatus().equals("3")) || (tnt.getThuocnoitruStatus().equals("5")))
           {
             logger.info("-----da xuat hang phieu du tru");
             this.maThuoc = tnt.getThuocnoitruMathuoc().getDmthuocMa();
             this.dg = tnt.getThuocnoitruDongia().toString();
             this.dv = tnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
             this.sl = tnt.getThuocnoitruSoluong().toString();
             this.tra = (tnt.getThuocnoitruTra() == null ? "" : tnt.getThuocnoitruTra().toString());
             this.tenthuoc = tnt.getThuocnoitruMathuoc().getDmthuocTen();
           }
         }
       }
       else
       {
         logger.info("-----chua lap phieu dt");
         FacesMessages.instance().add(IConstantsRes.BENHNHANTRATHUOC_PDT_NULL, new Object[0]);
         this.maThuoc = "";
         this.dg = "";
         this.sl = "";
         this.tra = "";
         this.tenthuoc = "";
         this.dv = "";
       }
     }
     else if (tnt.getThuocnoitruTutrucPdt().intValue() == 1)
     {
       logger.info("-----tra hang theo tu truc");
       this.maThuoc = tnt.getThuocnoitruMathuoc().getDmthuocMa();
       this.dg = tnt.getThuocnoitruDongia().toString();
       this.sl = tnt.getThuocnoitruSoluong().toString();
       this.tra = (tnt.getThuocnoitruTra() == null ? "" : tnt.getThuocnoitruTra().toString());
       this.dv = tnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
       this.tenthuoc = tnt.getThuocnoitruMathuoc().getDmthuocTen();
     }
     logger.info("-----maThuoc = " + this.maThuoc);
     logger.info("-----tenThuoc = " + this.tenthuoc);
     logger.info("-----donvi = " + this.dv);
     logger.info("-----dg = " + this.dg);
     logger.info("-----sl = " + this.sl);
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setMaKhoa(String maKhoa)
   {
     this.maKhoa = maKhoa;
   }

   public String getMaKhoa()
   {
     return this.maKhoa;
   }

   public void setListTnt(ArrayList<ThuocNoiTru> listTnt)
   {
     this.listTnt = listTnt;
   }

   public ArrayList<ThuocNoiTru> getListTnt()
   {
     return this.listTnt;
   }

   public void setTntSelected(ThuocNoiTru tntSelected)
   {
     this.tntSelected = tntSelected;
   }

   public ThuocNoiTru getTntSelected()
   {
     return this.tntSelected;
   }

   public void setNgayHienTai(String ngayHienTai)
   {
     this.ngayHienTai = ngayHienTai;
   }

   public String getNgayHienTai()
   {
     return this.ngayHienTai;
   }

   public void setUpdateItem(Integer updateItem)
   {
     this.updateItem = updateItem;
   }

   public Integer getUpdateItem()
   {
     return this.updateItem;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setTra(String tra)
   {
     this.tra = tra;
   }

   public String getTra()
   {
     return this.tra;
   }

   public String getMaThuoc()
   {
     return this.maThuoc;
   }

   public void setMaThuoc(String maThuoc)
   {
     this.maThuoc = maThuoc;
   }

   public String getDg()
   {
     return this.dg;
   }

   public void setDg(String dg)
   {
     this.dg = dg;
   }

   public String getSl()
   {
     return this.sl;
   }

   public void setSl(String sl)
   {
     this.sl = sl;
   }

   public String getDv()
   {
     return this.dv;
   }

   public void setDv(String dv)
   {
     this.dv = dv;
   }

   public String getTenthuoc()
   {
     return this.tenthuoc;
   }

   public void setTenthuoc(String tenthuoc)
   {
     this.tenthuoc = tenthuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BenhNhanTraThuocAction

 * JD-Core Version:    0.7.0.1

 */