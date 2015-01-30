 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.rmi.RemoteException;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.xml.rpc.ServiceException;
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

 @Name("Timkiembangkiemke")
 @Scope(ScopeType.CONVERSATION)
 public class Timkiembangkiemke
   implements Serializable
 {
   @Logger
   private Log log;
   @Out(required=false)
   @In(required=false)
   private String resetFormTimKiemKiemKe = null;
   @DataModel
   private List<DmPhanLoaiThuoc> listDmPLThuocKK = new ArrayList();
   @DataModel
   private List<KiemKe> listResultBangKiemKe = new ArrayList();
   @DataModelSelection("listDmPLThuocKK")
   @Out(required=false)
   private DmPhanLoaiThuoc dmPLThuocSelectKK;
   private int index = 0;
   private String ngaykiemkeTu;
   private String ngaykiemkeDen;
   private Integer loaihang_maso = null;
   private Integer plthuoc_maso = null;
   private String plthuoc_ma = "";
   private String loaihang_ma = "";
   private String maKiemKe;
   private String tenChuongTrinh;
   private String trangthai;
   @In(required=false)
   @Out(required=false)
   private DmKhoa dmKhoaKhoOut;
   @In(required=false)
   @Out(required=false)
   private KiemKe kiemkeOut;
   @In(required=false)
   @Out(required=false)
   private String trangthaittOut;

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

   @Factory("resetFormTimKiemKiemKe")
   public void initresetFormTimKiemKiemKe()
   {
     this.log.info("initresetFormTimKiemKiemKe", new Object[0]);
     resetForm();
   }

   @Begin(join=true)
   public String init(String loaiKho)
   {
     reset();
     this.resetFormTimKiemKiemKe = "";
     String maLoaiKho = "";
     if (loaiKho.equals("KC"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       maLoaiKho = IConstantsRes.KHOA_KC_MA;
     }
     else if (loaiKho.equals("KhoNoiTru"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
       maLoaiKho = IConstantsRes.KHOA_NT_MA;
     }
     else if (loaiKho.equals("BHYT"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
       maLoaiKho = IConstantsRes.KHOA_BHYT_MA;
     }
     else if (loaiKho.equals("TE"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
       maLoaiKho = IConstantsRes.KHOA_TE_MA;
     }
     DieuTriUtilDelegate dtriUtil = DieuTriUtilDelegate.getInstance();
     this.dmKhoaKhoOut = ((DmKhoa)dtriUtil.findByMa(maLoaiKho, "DmKhoa", "dmkhoaMa"));
     return "QuanLyKhoChinh_KiemKeKhoChinh_TimKiemBangKiemKeDinhKy";
   }

   @End
   public void end() {}

   public void reset()
   {
     this.log.info("=============reset==============", new Object[0]);
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
     this.ngaykiemkeTu = Utils.getCurrentDate();
     this.ngaykiemkeDen = Utils.getCurrentDate();
     this.maKiemKe = "";
     this.loaihang_ma = "";
     this.loaihang_maso = null;
     this.trangthai = "";
     this.listResultBangKiemKe = new ArrayList();
   }

   public void timkiemAction()
   {
     this.log.info("-----timkiem()-----", new Object[0]);
     this.listResultBangKiemKe = new ArrayList();
     KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     Date ngayKiemKeTu = new Date();
     Date ngayKiemKeDen = new Date();
     try
     {
       if (!this.ngaykiemkeTu.equals("")) {
         ngayKiemKeTu = df.parse(this.ngaykiemkeTu);
       } else {
         ngayKiemKeTu = null;
       }
       if (!this.ngaykiemkeDen.equals("")) {
         ngayKiemKeDen = df.parse(this.ngaykiemkeDen);
       }
     }
     catch (ParseException e)
     {
       FacesMessages.instance().add("Ngay khong dung", new Object[] { this.ngaykiemkeTu });
       e.printStackTrace();
     }
     List<KiemKe> lstKiemKe = kiemkeDelegate.findByDieuKienTimKiem(this.maKiemKe, ngayKiemKeTu, ngayKiemKeDen, this.dmKhoaKhoOut.getDmkhoaMaso(), this.loaihang_maso, this.listDmPLThuocKK, this.trangthai);
     if (lstKiemKe == null)
     {
       FacesMessages.instance().add("Không tìm thấy kiểm kê nào.", new Object[] { this.maKiemKe });
     }
     else
     {
       this.log.info("lstKiemKe = " + lstKiemKe.size(), new Object[0]);
       this.listResultBangKiemKe = lstKiemKe;
     }
     this.log.info("-----End timkiem()-----", new Object[0]);
   }

   public String taomoiKiemKe()
     throws ServiceException, RemoteException
   {
     this.resetFormTimKiemKiemKe = null;
     return "QuanLyKhoLe_KiemKeKhoChinh_TaoBangKiemKeDinhKy";
   }

   public String getListDmphanloaithuoc(String maPhieukiem)
   {
     KiemKeDelegate kiemkeDelegate = KiemKeDelegate.getInstance();
     String lstPLThuoc = kiemkeDelegate.getListPhanLoaiThuocMa(maPhieukiem);
     return lstPLThuoc;
   }

   public void resetForm()
   {
     setPlthuoc_ma("");
     this.listDmPLThuocKK.clear();
     this.listResultBangKiemKe.clear();
     setLoaihang_ma("");
     this.resetFormTimKiemKiemKe = "";
     this.ngaykiemkeTu = Utils.getCurrentDate();
     this.ngaykiemkeDen = Utils.getCurrentDate();
     this.maKiemKe = "";
     timkiemAction();
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

   public String selectBangKiemKe(Integer index)
   {
     this.log.info("-----selectBangKiemKe()-----", new Object[0]);
     this.log.info("-----index: " + index, new Object[0]);
     this.kiemkeOut = ((KiemKe)this.listResultBangKiemKe.get(index.intValue()));
     this.trangthaittOut = this.kiemkeOut.getTrangthai();
     this.resetFormTimKiemKiemKe = null;
     return "QuanLyKhoLe_KiemKeKhoChinh_XemBangKiemKeDinhKy";
   }

   public String formatDateTime(Date ngaygio)
   {
     SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
     String strDatetime = sf.format(ngaygio);
     return strDatetime;
   }

   public String getTenLoaiThuoc(String tenLoaiThuoc)
   {
     if ((tenLoaiThuoc.trim().equals("")) || (tenLoaiThuoc == null)) {
       return "ALL";
     }
     return tenLoaiThuoc;
   }

   public String viewGUI(String trangthai)
   {
     if (trangthai.equals("OPEN")) {
       return "Đang kiểm kê";
     }
     return "Đã hoàn tất";
   }

   public String getNgaykiemkeTu()
   {
     return this.ngaykiemkeTu;
   }

   public void setNgaykiemkeTu(String ngaykiemkeTu)
   {
     this.ngaykiemkeTu = ngaykiemkeTu;
   }

   public String getNgaykiemkeDen()
   {
     return this.ngaykiemkeDen;
   }

   public void setNgaykiemkeDen(String ngaykiemkeDen)
   {
     this.ngaykiemkeDen = ngaykiemkeDen;
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

   public void setIndex(int index)
   {
     this.index = index;
   }

   public int getIndex()
   {
     return this.index;
   }

   public String getTrangthai()
   {
     return this.trangthai;
   }

   public void setTrangthai(String trangthai)
   {
     this.trangthai = trangthai;
   }

   public List<KiemKe> getListResultBangKiemKe()
   {
     return this.listResultBangKiemKe;
   }

   public void setListResultBangKiemKe(List<KiemKe> listResultBangKiemKe)
   {
     this.listResultBangKiemKe = listResultBangKiemKe;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.Timkiembangkiemke

 * JD-Core Version:    0.7.0.1

 */