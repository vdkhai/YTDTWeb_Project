 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.CONVERSATION)
 @Name("B138_Chuyensolieuvaonoitru")
 @Synchronized(timeout=6000000L)
 public class ChuyenSoLieuVaoNoiTruAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   @Logger
   private Log log;
   private String matinh;
   private String tentinh;
   private String mahuyen;
   private String tenhuyen;
   private String maxa;
   private String tenxa;
   private String diachi;
   private String ngay;
   private String gioitinh;
   private String hoten;
   private String tuoi;
   private String matiepdon;
   private String madantoc;
   private String tendantoc;
   private String sothebhyt;
   private String tuyenbh;
   private String makhoa;
   private Integer masokhoa;
   private String sobenhan;
   private String makcbbhyt;
   private String tenkcbbhyt;
   private TiepDon tiepdon;
   private Hsba hsba;

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public String getMatinh()
   {
     return this.matinh;
   }

   public void setMatinh(String matinh)
   {
     this.matinh = matinh;
   }

   public String getTentinh()
   {
     return this.tentinh;
   }

   public void setTentinh(String tentinh)
   {
     this.tentinh = tentinh;
   }

   public String getMahuyen()
   {
     return this.mahuyen;
   }

   public void setMahuyen(String mahuyen)
   {
     this.mahuyen = mahuyen;
   }

   public String getTenhuyen()
   {
     return this.tenhuyen;
   }

   public void setTenhuyen(String tenhuyen)
   {
     this.tenhuyen = tenhuyen;
   }

   public String getMaxa()
   {
     return this.maxa;
   }

   public void setMaxa(String maxa)
   {
     this.maxa = maxa;
   }

   public String getTenxa()
   {
     return this.tenxa;
   }

   public void setTenxa(String tenxa)
   {
     this.tenxa = tenxa;
   }

   public String getDiachi()
   {
     return this.diachi;
   }

   public void setDiachi(String diachi)
   {
     this.diachi = diachi;
   }

   public String getGioitinh()
   {
     return this.gioitinh;
   }

   public void setGioitinh(String gioitinh)
   {
     this.gioitinh = gioitinh;
   }

   public String getHoten()
   {
     return this.hoten;
   }

   public void setHoten(String hoten)
   {
     this.hoten = hoten;
   }

   public String getTuoi()
   {
     return this.tuoi;
   }

   public void setTuoi(String tuoi)
   {
     this.tuoi = tuoi;
   }

   public String getMatiepdon()
   {
     return this.matiepdon;
   }

   public void setMatiepdon(String matiepdon)
   {
     this.matiepdon = matiepdon;
   }

   public String getMadantoc()
   {
     return this.madantoc;
   }

   public void setMadantoc(String madantoc)
   {
     this.madantoc = madantoc;
   }

   public String getTendantoc()
   {
     return this.tendantoc;
   }

   public void setTendantoc(String tendantoc)
   {
     this.tendantoc = tendantoc;
   }

   public String getSothebhyt()
   {
     return this.sothebhyt;
   }

   public void setSothebhyt(String sothebhyt)
   {
     this.sothebhyt = sothebhyt;
   }

   public String getTuyenbh()
   {
     return this.tuyenbh;
   }

   public void setTuyenbh(String tuyenbh)
   {
     this.tuyenbh = tuyenbh;
   }

   public String getMakhoa()
   {
     return this.makhoa;
   }

   public void setMakhoa(String makhoa)
   {
     this.makhoa = makhoa;
   }

   public Integer getMasokhoa()
   {
     return this.masokhoa;
   }

   public void setMasokhoa(Integer masokhoa)
   {
     this.masokhoa = masokhoa;
   }

   public String getSobenhan()
   {
     return this.sobenhan;
   }

   public void setSobenhan(String sobenhan)
   {
     this.sobenhan = sobenhan;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   @Begin(join=true)
   public String init()
   {
     this.log.info("begin init", new Object[0]);

     resetValue();
     this.log.info("end init", new Object[0]);
     return "TiepDon_TiepDonKhamBenhCapCuu_ChuyenSoLieuVaoNoiTru";
   }

   public void resetValue()
   {
     this.ngay = Utils.getCurrentDate();


     this.matinh = null;
     this.tentinh = null;
     this.mahuyen = null;
     this.tenhuyen = null;
     this.maxa = null;
     this.tenxa = null;
     this.diachi = null;



     this.gioitinh = null;

     this.hoten = null;
     this.tuoi = null;
     this.matiepdon = null;

     this.madantoc = null;
     this.tendantoc = null;

     this.sothebhyt = null;
     this.tuyenbh = null;

     this.makhoa = null;
     this.masokhoa = null;
     this.sobenhan = null;

     this.makcbbhyt = null;
     this.tenkcbbhyt = null;


     this.tiepdon = null;
     this.hsba = null;
   }

   public void displayInfor()
     throws Exception
   {
     this.log.info("---displayInfor()-----", new Object[0]);
     if ((this.matiepdon != null) && (!this.matiepdon.equals("")))
     {
       TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
       TiepDon td_Tmp = tdDelegate.find(this.matiepdon);
       if (td_Tmp != null)
       {
         this.tiepdon = td_Tmp;

         this.matiepdon = this.tiepdon.getTiepdonMa();

         BenhNhan benhnhan = this.tiepdon.getBenhnhanMa();

         this.hoten = benhnhan.getBenhnhanHoten();
         this.gioitinh = benhnhan.getDmgtMaso(true).getDmgtTen();
         this.tuoi = (benhnhan.getBenhnhanTuoi() + " " + (benhnhan.getBenhnhanDonvituoi().shortValue() == 3 ? IConstantsRes.NGAY : benhnhan.getBenhnhanDonvituoi().shortValue() == 2 ? IConstantsRes.THANG : ""));

         this.matinh = benhnhan.getTinhMa(true).getDmtinhMa();
         this.tentinh = benhnhan.getTinhMa(true).getDmtinhTen();

         this.mahuyen = benhnhan.getHuyenMa(true).getDmhuyenMa();
         this.tenhuyen = benhnhan.getHuyenMa(true).getDmhuyenTen();

         this.maxa = benhnhan.getXaMa(true).getDmxaMa();
         this.tenxa = benhnhan.getXaMa(true).getDmxaTen();

         this.diachi = benhnhan.getBenhnhanDiachi();

         this.madantoc = benhnhan.getDantocMa(true).getDmdantocMa();
         this.tendantoc = benhnhan.getDantocMa(true).getDmdantocTen();

         this.sothebhyt = this.tiepdon.getTiepdonSothebh();

         this.makcbbhyt = this.tiepdon.getKcbbhytMa(true).getDmbenhvienMa();
         this.tenkcbbhyt = this.tiepdon.getKcbbhytMa(true).getDmbenhvienTen();
         if (this.tiepdon.getTiepdonTuyen() != null) {}
         DieuTriUtilDelegate dtUtilDelegate = DieuTriUtilDelegate.getInstance();
         DtDmTuyenKcb dtDmTuyenKcb = (DtDmTuyenKcb)dtUtilDelegate.findByMa(String.valueOf(this.tiepdon.getTiepdonTuyen()), "DtDmTuyenKcb", "dtdmtuyenkcbMa");
         if (dtDmTuyenKcb != null) {
           this.tuyenbh = dtDmTuyenKcb.getDtdmtuyenkcbTen();
         }
         HsbaDelegate hsbaDele = HsbaDelegate.getInstance();
         this.hsba = hsbaDele.findByTiepDonMa(this.matiepdon);
         if (this.hsba != null)
         {
           this.sobenhan = this.hsba.getHsbaSovaovien();
           DmKhoa khoa = this.hsba.getHsbaKhoavaov();
           if (khoa != null)
           {
             this.makhoa = khoa.getDmkhoaMa();
             this.masokhoa = khoa.getDmkhoaMaso();
           }
         }
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.MATIEPDON_NOTFOUND + " " + this.matiepdon, new Object[0]);
         resetValue();
       }
     }
   }

   @End
   public void end() {}

   public String ghinhan()
     throws Exception
   {
     try
     {
       if (this.tiepdon == null) {
         return "";
       }
       HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
       HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(this.tiepdon.getTiepdonMa());
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       ThamKham tk = tkDelegate.findByMaTiepDon(this.tiepdon.getTiepdonMa());
       if (tk.getThamkhamDieutri() == null)
       {
         FacesMessages.instance().add(IConstantsRes.CHUACHON_HXL, new Object[0]);
         return "";
       }
       String maHuongDieuTri = tk.getThamkhamDieutri().getDmdieutriMa();
       if (!maHuongDieuTri.equals("V"))
       {
         FacesMessages.instance().add(IConstantsRes.KHONGPHAI_HXL_DTNT, new Object[0]);
         return "";
       }
       if (hsttk != null)
       {
         FacesMessages.instance().add(IConstantsRes.HO_SO_DA_TT_KHONG_DUOC_PHEP_CHINH_SUA, new Object[0]);
         return "";
       }
       tk.setThamkhamChuyenSoLieuVaoNoiTru(Boolean.valueOf(true));

       tkDelegate.edit(tk);
       hsttk = new HsThtoank();
       hsttk.setTiepdonMa(this.tiepdon);
       tinhToanChoHSTKKham(hsttk);
       Utils.setInforForHsThToan(hsttk);
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);


       e.printStackTrace();
       this.log.error("*************loi***********" + e.toString(), new Object[0]);
     }
     this.log.info("*****End Ghi nhan() *****", new Object[0]);

     return null;
   }

   private List<ThuocPhongKham> listCtTPK_temp = null;
   private List<ClsKham> clslist = null;

   private void tinhToanChoHSTKKham(HsThtoank hsttk)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.tiepdon);
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);


     this.listCtTPK_temp = hsthtoankUtilTmp.getListCtTPK_temp();
     this.clslist = hsthtoankUtilTmp.getListCtkq_temp();
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getMakcbbhyt()
   {
     return this.makcbbhyt;
   }

   public void setMakcbbhyt(String makcbbhyt)
   {
     this.makcbbhyt = makcbbhyt;
   }

   public String getTenkcbbhyt()
   {
     return this.tenkcbbhyt;
   }

   public void setTenkcbbhyt(String tenkcbbhyt)
   {
     this.tenkcbbhyt = tenkcbbhyt;
   }

   public TiepDon getTiepdon()
   {
     return this.tiepdon;
   }

   public void setTiepdon(TiepDon tiepdon)
   {
     this.tiepdon = tiepdon;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ChuyenSoLieuVaoNoiTruAction

 * JD-Core Version:    0.7.0.1

 */