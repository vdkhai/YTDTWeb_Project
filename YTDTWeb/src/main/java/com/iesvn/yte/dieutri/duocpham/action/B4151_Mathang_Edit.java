 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBaoQuanThuoc;
 import com.iesvn.yte.entity.DmCachDungThuoc;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanNhomThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.ListDataModel;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4151_Mathang_Edit")
 @Scope(ScopeType.CONVERSATION)
 public class B4151_Mathang_Edit
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DmThuoc mathang;
   private List<SelectItem> listB4151_Donvitinh;
   private List<SelectItem> listB4151_Baoquan;
   private List<SelectItem> listB4151_Nhomthuoc;
   private List<SelectItem> listB4151_Cachdung;
   private List<SelectItem> listB4151_Nhasanxuat;
   private List<SelectItem> listB4151_Phanloaithuoc;
   private List<SelectItem> listB4151_QuocGia;
   private List<SelectItem> listB4151_Loaithuoc;
   @Logger
   private Log log;

   @Begin(join=true)
   public String init()
   {
     this.log.info("Init B4151_Mathang....", new Object[0]);
     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listB4151");
     rowIndex = lsDataModel.getRowIndex();
     this.log.info("rowIndex..****************..:" + rowIndex, new Object[0]);
     if (rowIndex != -1)
     {
       resetValue();
       load_rowIndex(rowIndex, lsDataModel);
       initCbo();
       return "/B4_Duocpham/KhoChinh/B4151_mathang_edit";
     }
     return "";
   }

   public void reset()
   {
     this.log.info("Reset B4151_Mathang....", new Object[0]);

     resetValue();
   }

   public void load_rowIndex(int rowIndex, ListDataModel lsDataModel)
   {
     this.log.info("Load rowIndex B4151_Mathang ....", new Object[0]);
     if (rowIndex != -1)
     {
       this.mathang = ((DmThuoc)((List)lsDataModel.getWrappedData()).get(rowIndex));

       this.log.info("this.mathang.getDmthuocSoDangKy():" + this.mathang.getDmthuocSoDangKy(), new Object[0]);
       if (this.mathang.getDmbaoquanMaso() == null) {
         this.mathang.setDmbaoquanMaso(new DmBaoQuanThuoc());
       }
       if (this.mathang.getDmcachdungMaso() == null) {
         this.mathang.setDmcachdungMaso(new DmCachDungThuoc());
       }
       if (this.mathang.getDmdonvitinhMaso() == null) {
         this.mathang.setDmdonvitinhMaso(new DmDonViTinh());
       }
       if (this.mathang.getDmnhasanxuatMaso() == null) {
         this.mathang.setDmnhasanxuatMaso(new DmNhaSanXuat());
       }
       if (this.mathang.getDmphanloaithuocMaso() == null) {
         this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
       }
       if (this.mathang.getDmphannhomthuocMaso() == null) {
         this.mathang.setDmphannhomthuocMaso(new DmPhanNhomThuoc());
       }
       if (this.mathang.getDmthuocNuocdefa() == null) {
         this.mathang.setDmthuocNuocdefa(new DmQuocGia());
       }
     }
     this.log.info("rowIndex =" + rowIndex, new Object[0]);
   }

   public void save()
   {
     this.log.info("Save B4151_Mat hang_Edit....", new Object[0]);

     String ma = "";
     Date date = new Date();
     this.mathang.setDmthuocNgaygiocn(Double.valueOf(date.getTime()));

     ma = this.mathang.getDmthuocMa();
     String ten = this.mathang.getDmthuocTen().trim();
     ten = ten.replaceAll("\\s+", " ");
     this.mathang.setDmthuocTen(ten);
     this.mathang.setDmthuocDt(Boolean.valueOf(true));
     if (this.mathang.getDmbaoquanMaso(true).getDmbaoquanthuocMaso() == null) {
       this.mathang.setDmbaoquanMaso(null);
     }
     if (this.mathang.getDmcachdungMaso(true).getDmcachdungthuocMaso() == null) {
       this.mathang.setDmcachdungMaso(null);
     }
     if (this.mathang.getDmdonvitinhMaso(true).getDmdonvitinhMaso() == null) {
       this.mathang.setDmdonvitinhMaso(null);
     }
     if (this.mathang.getDmnhasanxuatMaso(true).getDmnhasanxuatMaso() == null) {
       this.mathang.setDmnhasanxuatMaso(null);
     }
     if (this.mathang.getDmphanloaithuocMaso(true).getDmphanloaithuocMaso() == null) {
       this.mathang.setDmphanloaithuocMaso(null);
     }
     if (this.mathang.getDmthuocNuocdefa(true).getDmquocgiaMaso() == null) {
       this.mathang.setDmthuocNuocdefa(null);
     }
     if (this.mathang.getDmthuocPlbhyt() == null) {
       this.mathang.setDmthuocPlbhyt(new Short((short)1));
     }
     try
     {
       DieuTriUtilDelegate.getInstance().edit(this.mathang);
       FacesMessages.instance().add(IConstantsRes.thuoc_up_su, new Object[] { ma });
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.thuoc_up_fa, new Object[] { ma });
     }
     finally
     {
       resetValue();
     }
   }

   @End
   public String goBack()
   {
     this.log.info("GoBack B4151 Mat hang....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4151_mathang";
   }

   private void resetValue()
   {
     this.mathang = new DmThuoc();
     this.mathang.setDmdonvitinhMaso(new DmDonViTinh());
     this.mathang.setDmcachdungMaso(new DmCachDungThuoc());
     this.mathang.setDmbaoquanMaso(new DmBaoQuanThuoc());
     this.mathang.setDmphannhomthuocMaso(new DmPhanNhomThuoc());
     this.mathang.setDmnhasanxuatMaso(new DmNhaSanXuat());
     this.mathang.setDmphanloaithuocMaso(new DmPhanLoaiThuoc());
     this.mathang.setDmthuocInplinh(Boolean.valueOf(true));
     this.mathang.setDmthuocNuocdefa(new DmQuocGia());
     this.mathang.setDmthuocPlbhyt(new Short((short)1));
   }

   private void initCbo()
   {
     this.log.info("Init Select items for combobox....", new Object[0]);

     initCboDonViTinh();
     initCboBaoquan();
     initCboCachdung();
     initCboNhasanxuat();
     initCboNhomthuoc();
     initCboPhanloaithuoc();
     initCboQuocGia();
     initCboLoaiThuoc();
   }

   private void initCboQuocGia()
   {
     this.log.info("getList_quocgia_item B4152_Dm Thuoc....", new Object[0]);

     this.listB4151_QuocGia = new ArrayList();

     List<DmQuocGia> list = DieuTriUtilDelegate.getInstance().findAll("DmQuocGia");
     for (DmQuocGia item : list) {
       this.listB4151_QuocGia.add(new SelectItem(item.getDmquocgiaMaso(), item.getDmquocgiaTenvn()));
     }
   }

   private void initCboDonViTinh()
   {
     this.log.info("getList_donvitinh_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Donvitinh = new ArrayList();



     List<DmDonViTinh> list = DieuTriUtilDelegate.getInstance().findAll("DmDonViTinh", "dmdonvitinhDt", true);
     for (DmDonViTinh item : list) {
       this.listB4151_Donvitinh.add(new SelectItem(item.getDmdonvitinhMaso(), item.getDmdonvitinhTen()));
     }
   }

   public List<SelectItem> getListB4151_QuocGia()
   {
     return this.listB4151_QuocGia;
   }

   public void setListB4151_QuocGia(List<SelectItem> listB4151QuocGia)
   {
     this.listB4151_QuocGia = listB4151QuocGia;
   }

   private void initCboBaoquan()
   {
     this.log.info("getList_Bao quan_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Baoquan = new ArrayList();
     this.listB4151_Baoquan.add(new SelectItem(null, "chọn cách bảo quản..."));

     List<DmBaoQuanThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmBaoQuanThuoc", "dmbaoquanthuocDt", true);
     for (DmBaoQuanThuoc item : list) {
       this.listB4151_Baoquan.add(new SelectItem(item.getDmbaoquanthuocMaso(), item.getDmbaoquanthuocTen()));
     }
   }

   private void initCboCachdung()
   {
     this.log.info("getList_Cach dung_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Cachdung = new ArrayList();
     this.listB4151_Cachdung.add(new SelectItem(null, "chọn cách dùng thuốc..."));

     List<DmCachDungThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmCachDungThuoc", "dmcachdungthuocDt", true);
     for (DmCachDungThuoc item : list) {
       this.listB4151_Cachdung.add(new SelectItem(item.getDmcachdungthuocMaso(), item.getDmcachdungthuocTen()));
     }
   }

   private void initCboNhomthuoc()
   {
     this.log.info("getList_Nhom thuoc_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Nhomthuoc = new ArrayList();
     this.listB4151_Nhomthuoc.add(new SelectItem(null, "chọn nhóm thuốc..."));

     List<DmPhanNhomThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmPhanNhomThuoc", "dmphannhomthuocDt", true);
     for (DmPhanNhomThuoc item : list) {
       this.listB4151_Nhomthuoc.add(new SelectItem(item.getDmphannhomthuocMaso(), item.getDmphannhomthuocTen()));
     }
   }

   private void initCboNhasanxuat()
   {
     this.log.info("getList_Nha san xuat_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Nhasanxuat = new ArrayList();
     this.listB4151_Nhasanxuat.add(new SelectItem(null, "chọn nhà sản xuất..."));

     List<DmNhaSanXuat> list = DieuTriUtilDelegate.getInstance().findAll("DmNhaSanXuat", "dmnhasanxuatDt", true);
     for (DmNhaSanXuat item : list) {
       this.listB4151_Nhasanxuat.add(new SelectItem(item.getDmnhasanxuatMaso(), item.getDmnhasanxuatTen()));
     }
   }

   private void initCboPhanloaithuoc()
   {
     this.log.info("getList_Phan loai thuoc_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Phanloaithuoc = new ArrayList();
     this.listB4151_Phanloaithuoc.add(new SelectItem(null, "chọn phân loại thuốc"));

     boolean notIn = false;
     Integer dmLoaithuocMaso = Integer.valueOf(4);
     if (this.mathang.getDmthuocPlbhyt().shortValue() == 1) {
       notIn = true;
     }
     List<DmPhanLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);
     for (DmPhanLoaiThuoc item : list) {
       this.listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), item.getDmphanloaithuocTen()));
     }
   }

   private void initCboLoaiThuoc()
   {
     this.log.info("getList_Loai thuoc_item B4151_Dm Thuoc....", new Object[0]);

     this.listB4151_Loaithuoc = new ArrayList();
     this.listB4151_Loaithuoc.add(new SelectItem(Integer.valueOf(1), "Nhóm thuốc, hóa chất"));
     this.listB4151_Loaithuoc.add(new SelectItem(Integer.valueOf(10), "Nhóm vật tư y tế tiêu hao"));
   }

   public void onChangeLoaithuoc()
   {
     this.listB4151_Phanloaithuoc = new ArrayList();
     boolean notIn = false;
     Integer dmLoaithuocMaso = Integer.valueOf(4);
     if (this.mathang.getDmthuocPlbhyt().shortValue() == 1) {
       notIn = true;
     }
     List<DmPhanLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmPhanLoaiThuoc", "dmphanloaithuocDt", true);
     for (DmPhanLoaiThuoc item : list) {
       this.listB4151_Phanloaithuoc.add(new SelectItem(item.getDmphanloaithuocMaso(), item.getDmphanloaithuocTen()));
     }
   }

   public DmThuoc getMathang()
   {
     return this.mathang;
   }

   public void setMathang(DmThuoc mathang)
   {
     this.mathang = mathang;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }

   public List<SelectItem> getListB4151_Donvitinh()
   {
     return this.listB4151_Donvitinh;
   }

   public void setListB4151_Donvitinh(List<SelectItem> listB4151_Donvitinh)
   {
     this.listB4151_Donvitinh = listB4151_Donvitinh;
   }

   public List<SelectItem> getListB4151_Baoquan()
   {
     return this.listB4151_Baoquan;
   }

   public void setListB4151_Baoquan(List<SelectItem> listB4151_Baoquan)
   {
     this.listB4151_Baoquan = listB4151_Baoquan;
   }

   public List<SelectItem> getListB4151_Nhomthuoc()
   {
     return this.listB4151_Nhomthuoc;
   }

   public void setListB4151_Nhomthuoc(List<SelectItem> listB4151_Nhomthuoc)
   {
     this.listB4151_Nhomthuoc = listB4151_Nhomthuoc;
   }

   public List<SelectItem> getListB4151_Cachdung()
   {
     return this.listB4151_Cachdung;
   }

   public void setListB4151_Cachdung(List<SelectItem> listB4151_Cachdung)
   {
     this.listB4151_Cachdung = listB4151_Cachdung;
   }

   public List<SelectItem> getListB4151_Nhasanxuat()
   {
     return this.listB4151_Nhasanxuat;
   }

   public void setListB4151_Nhasanxuat(List<SelectItem> listB4151_Nhasanxuat)
   {
     this.listB4151_Nhasanxuat = listB4151_Nhasanxuat;
   }

   public List<SelectItem> getListB4151_Phanloaithuoc()
   {
     return this.listB4151_Phanloaithuoc;
   }

   public void setListB4151_Phanloaithuoc(List<SelectItem> listB4151_Phanloaithuoc)
   {
     this.listB4151_Phanloaithuoc = listB4151_Phanloaithuoc;
   }

   public List<SelectItem> getListB4151_Loaithuoc()
   {
     return this.listB4151_Loaithuoc;
   }

   public void setListB4151_Loaithuoc(List<SelectItem> listB4151_Loaithuoc)
   {
     this.listB4151_Loaithuoc = listB4151_Loaithuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.B4151_Mathang_Edit

 * JD-Core Version:    0.7.0.1

 */