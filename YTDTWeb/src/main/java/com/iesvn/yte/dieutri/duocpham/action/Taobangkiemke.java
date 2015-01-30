 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import java.util.Map;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;
 import org.jboss.seam.security.Identity;

 @Name("B4143_Taobangkiemke")
 @Scope(ScopeType.CONVERSATION)
 public class Taobangkiemke
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String resetFormB4143 = null;
   @In(required=false)
   @Out(required=false)
   private DmKhoa dmKhoaKhoOut;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   @In(required=false)
   @Out(required=false)
   private KiemKe kiemkeOut;
   @In(required=false)
   @Out(required=false)
   private String trangthaittOut;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList();
   @DataModelSelection("listDmPLThuocKK")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelectKK;
   private int index = 0;
   private String ngayhientai;
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private Integer khoa_maso = null;
   private Boolean chon = Boolean.valueOf(false);
   private String plthuoc_ma = "";
   private String loaihang_ma = "";
   private String khoa_ma = "";
   Map<String, Object> params = null;
   private String maKiemKe;

   public String getMaKiemKe()
   {
     return this.maKiemKe;
   }

   public void setMaKiemKe(String maKiemKe)
   {
     this.maKiemKe = maKiemKe;
   }

   String dmKhoXuat = "";

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   @Factory("resetFormB4143")
   public void initresetFormB4143()
   {
     this.log.info("init()", new Object[0]);
     this.ngayhientai = Utils.getCurrentDate();
     resetForm();
   }

   @Begin(join=true)
   public String init()
   {
     resetList();
     return "QuanLyKhoChinh_KiemKeKhoChinh_TaoBangKiemKeDinhKy";
   }

   @End
   public void end() {}

   public String quayve()
     throws Exception
   {
     resetForm();
     return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
   }

   public void resetList()
   {
     this.log.info("=============reset listttttttttttt " + this.listDmPLThuocKK.size(), new Object[0]);
     if (this.listDmPLThuocKK.size() > 0)
     {
       DmPhanLoaiThuoc dmplt = new DmPhanLoaiThuoc();
       dmplt = (DmPhanLoaiThuoc)this.listDmPLThuocKK.get(0);
       this.log.info(dmplt, new Object[0]);
       if (!dmplt.getDmloaithuocMaso().getDmloaithuocMa().equals(this.loaihang_ma))
       {
         this.listDmPLThuocKK.clear();
         setPlthuoc_ma("");
       }
     }
     this.ngayhientai = Utils.getCurrentDate();
     this.maKiemKe = "";
     this.loaihang_ma = "";
   }

   private String getListDVMaParamsHelp(List<String> inputs)
   {
     this.log.info("Vao method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Inputs: " + inputs.toString(), new Object[0]);
     String result = "";
     for (String each : inputs) {
       if (each != "") {
         result = result + "'" + each + "',";
       }
     }
     result = result.substring(0, result.length() - 1);
     this.log.info("Thoat method getListDVMaParamsHelp ", new Object[0]);
     this.log.info("Output: " + result, new Object[0]);
     return result;
   }

   public void resetForm()
   {
     setPlthuoc_ma("");
     this.listDmPLThuocKK.clear();
     setLoaihang_ma("");
     this.resetFormB4143 = "";
     this.ngayhientai = Utils.getCurrentDate();
     this.maKiemKe = "";
     this.loaihang_ma = "";
   }

   public void enter()
   {
     this.log.info("bat dau them du lieu vao luoi", new Object[0]);

     boolean test = false;
     DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
     if (!this.plthuoc_ma.equals("")) {
       if ((this.listDmPLThuocKK.size() == 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list bang 0", new Object[0]);
         Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
         this.listDmPLThuocKK.add(plThuoc);
         this.log.info("da add phan tu dau tien" + this.listDmPLThuocKK.size(), new Object[0]);
       }
       else if ((this.listDmPLThuocKK.size() > 0) && (this.plthuoc_ma != null))
       {
         this.log.info("size list lon hon 0", new Object[0]);
         for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
           if (item.getDmphanloaithuocMa().equals(this.plthuoc_ma)) {
             test = true;
           }
         }
         if (!test)
         {
           Object obj = dtutilDelegate.findByMa(this.plthuoc_ma, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
           DmPhanLoaiThuoc plThuoc = (DmPhanLoaiThuoc)obj;
           this.listDmPLThuocKK.add(plThuoc);
         }
         this.log.info("da add phan tu" + this.listDmPLThuocKK.size(), new Object[0]);
       }
     }
     setPlthuoc_ma("");
   }

   public void deletedmPLThuoc()
   {
     this.log.info("bat dau xoa , size" + this.listDmPLThuocKK.size(), new Object[0]);
     this.listDmPLThuocKK.remove(this.dmPLThuocSelectKK);
     this.log.info("da xoa , size" + this.listDmPLThuocKK.size(), new Object[0]);
     this.log.info("ket thuc xoa", new Object[0]);
   }

   public String thuchienAction()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     DieuTriUtilDelegate dieutriUtilDel = DieuTriUtilDelegate.getInstance();
     try
     {
       DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
       DtDmNhanVien nv = nvDelegate.findByNd(this.identity.getUsername());
       if (nv == null)
       {
         FacesMessages.instance().add(IConstantsRes.USER_MAP_NV, new Object[] { this.identity.getUsername() });
         return null;
       }
       String listPl = "(";
       String loaiMa = this.loaihang_ma;
       DmLoaiThuoc dmloaithuoc = new DmLoaiThuoc();
       System.out.println("loaiMa: " + loaiMa);
       if (this.loaihang_ma.trim().equals(""))
       {
         setLoaihang_ma(null);
         dmloaithuoc = null;
       }
       else
       {
         dmloaithuoc = (DmLoaiThuoc)dieutriUtilDel.findByMa(this.loaihang_ma, "DmLoaiThuoc", "dmloaithuocMa");
       }
       if (this.khoa_ma.trim().equals("")) {
         setKhoa_ma(null);
       }
       if (this.listDmPLThuocKK.size() > 0)
       {
         setLoaihang_ma(null);
         List<String> listtemp = new ArrayList();
         for (DmPhanLoaiThuoc item : this.listDmPLThuocKK) {
           listtemp.add(item.getDmphanloaithuocMa());
         }
         listPl = listPl + getListDVMaParamsHelp(listtemp) + ")";
         this.log.info("list phan loai " + listPl, new Object[0]);
       }
       else
       {
         listPl = "('')";
       }
       this.khoa_ma = this.dmKhoaKhoOut.getDmkhoaMa();


       KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
       boolean isExistedKiemKe = kiemkeDel.isExistedKiemKe(this.loaihang_ma, this.dmKhoaKhoOut.getDmkhoaMaso());
       if (isExistedKiemKe)
       {
         FacesMessages.instance().add(IConstantsRes.KIEMKE_DANG_KIEM_KE, new Object[0]);
         return null;
       }
       System.out.println("khoa_ma: " + this.khoa_ma);
       System.out.println("listPl: " + listPl);
       System.out.println("loaiMa: " + loaiMa);
       System.out.println("kecaTonBangKhong: " + this.chon.booleanValue());
       TonKhoDelegate tonkhoDAO = TonKhoDelegate.getInstance();
       long countTonKhoList = tonkhoDAO.getTotalListTonKhoForKiemKe(this.khoa_ma, listPl, loaiMa, this.chon.booleanValue());
       if (countTonKhoList == 0L)
       {
         FacesMessages.instance().add(IConstantsRes.KIEMKE_KHONG_CO_THUOC, new Object[0]);
         return null;
       }
       this.log.info("list ton kho " + countTonKhoList, new Object[0]);

       KiemKe kiemke = new KiemKe();
       kiemke.setDmkhoaMaso(this.dmKhoaKhoOut);
       kiemke.setDmloaithuocMaso(dmloaithuoc);
       kiemke.setDtdmnhanvienCn(nv);
       kiemke.setDtdmnhanvienKiemke(nv);
       kiemke.setMaphieukiem(null);
       kiemke.setNgaygiocn(new Date());
       kiemke.setNgaykiemke(new Date());
       kiemke.setTrangthai("OPEN");

       String resultInsert = kiemkeDel.taoBangKiemKe(kiemke, listPl, this.listDmPLThuocKK, this.loaihang_ma, this.chon.booleanValue());
       if (resultInsert != null)
       {
         this.maKiemKe = resultInsert;
         FacesMessages.instance().add("Tạo thành công phiếu kiểm kê ", new Object[0]);
         kiemke.setMaphieukiem(this.maKiemKe);
         this.kiemkeOut = kiemke;
         this.trangthaittOut = "OPEN";
       }
       else
       {
         FacesMessages.instance().add(resultInsert, new Object[0]);
         return null;
       }
       this.resetFormB4143 = null;
     }
     catch (Exception ex)
     {
       this.log.info("That bai ", new Object[0]);
       ex.printStackTrace();
     }
     return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public Integer getLoaihang_maso()
   {
     return this.loaihang_maso;
   }

   public void setLoaihang_maso(Integer loaihang_maso)
   {
     this.loaihang_maso = loaihang_maso;
   }

   public Integer getPlthuoc_maso()
   {
     return this.plthuoc_maso;
   }

   public void setPlthuoc_maso(Integer plthuoc_maso)
   {
     this.plthuoc_maso = plthuoc_maso;
   }

   public Integer getKhoa_maso()
   {
     return this.khoa_maso;
   }

   public void setKhoa_maso(Integer khoa_maso)
   {
     this.khoa_maso = khoa_maso;
   }

   public Boolean getChon()
   {
     return this.chon;
   }

   public void setChon(Boolean chon)
   {
     this.chon = chon;
   }

   public String getPlthuoc_ma()
   {
     return this.plthuoc_ma;
   }

   public void setPlthuoc_ma(String plthuoc_ma)
   {
     this.plthuoc_ma = plthuoc_ma;
   }

   public String getLoaihang_ma()
   {
     return this.loaihang_ma;
   }

   public void setLoaihang_ma(String loaihang_ma)
   {
     this.loaihang_ma = loaihang_ma;
   }

   public String getKhoa_ma()
   {
     return this.khoa_ma;
   }

   public void setKhoa_ma(String khoa_ma)
   {
     this.khoa_ma = khoa_ma;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Taobangkiemke

 * JD-Core Version:    0.7.0.1

 */