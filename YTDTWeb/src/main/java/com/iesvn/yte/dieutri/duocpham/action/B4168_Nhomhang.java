 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmPhanLoaiThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B4168_Nhomhang")
 @Scope(ScopeType.SESSION)
 public class B4168_Nhomhang
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private DmPhanLoaiThuoc nhomhang;
   @DataModel
   private List<DmPhanLoaiThuoc> listPhanLoaiThuocs;
   private List<SelectItem> listLoaiThuocs;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init B4168_Nhomhang....", new Object[0]);
     resetValue();
     this.listLoaiThuocs = new ArrayList();
     List<DmLoaiThuoc> list = DieuTriUtilDelegate.getInstance().findAll("DmLoaiThuoc", "dmloaithuocDt", true);
     this.listLoaiThuocs.add(new SelectItem(null, ""));
     for (DmLoaiThuoc item : list) {
       this.listLoaiThuocs.add(new SelectItem(item.getDmloaithuocMaso(), item.getDmloaithuocTen()));
     }
     return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
   }

   public String search()
   {
     this.log.info("Search Danh muc phan loai thuoc", new Object[0]);
     DmPhanLoaiThuocDelegate pltDel = DmPhanLoaiThuocDelegate.getInstance();
     this.listPhanLoaiThuocs.clear();
     this.listPhanLoaiThuocs = pltDel.findByMaAndTenAndDmloaiMa(this.nhomhang.getDmphanloaithuocMa(), this.nhomhang.getDmphanloaithuocTen(), this.nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso());
     if (this.listPhanLoaiThuocs.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
     return "/B4_Duocpham/KhoChinh/B4168_nhomhang";
   }

   public String getKhoName(String strKhoMaso)
   {
     try
     {
       Integer khoMaso = Integer.valueOf(Integer.parseInt(strKhoMaso));
       DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();
       DmKhoa dmKhoa = (DmKhoa)dtDel.findByMaSo(khoMaso, "DmKhoa", "dmkhoaMaso");
       if (dmKhoa != null) {
         return dmKhoa.getDmkhoaTen();
       }
       return "";
     }
     catch (Exception er) {}
     return "";
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete B4168_nhomhang....", new Object[0]);
     if (rowIndex != -1)
     {
       String maPLT = ((DmPhanLoaiThuoc)this.listPhanLoaiThuocs.get(rowIndex)).getDmphanloaithuocMa();
       Integer masoPLT = ((DmPhanLoaiThuoc)this.listPhanLoaiThuocs.get(rowIndex)).getDmphanloaithuocMaso();
       try
       {
         DmPhanLoaiThuoc dm = new DmPhanLoaiThuoc();



         DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();
         DmPhanLoaiThuocDelegate pltDel = DmPhanLoaiThuocDelegate.getInstance();
         dm = pltDel.find(masoPLT);
         if (dmThuocDel.hasThuoInPhanLoaiThuoc(masoPLT))
         {
           dm.setDmphanloaithuocDt(Boolean.valueOf(false));
           dm.setDmphanloaithuocNgaygiocn(Double.valueOf(new Date().getTime()));
           pltDel.edit(dm);
         }
         else
         {
           pltDel.remove(dm);
         }
         this.listPhanLoaiThuocs = pltDel.findByMaAndTenAndDmloaiMa(this.nhomhang.getDmphanloaithuocMa(), this.nhomhang.getDmphanloaithuocTen(), this.nhomhang.getDmloaithuocMaso(true).getDmloaithuocMaso());
         FacesMessages.instance().add(IConstantsRes.plthuoc_de_su, new Object[] { maPLT });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.plthuoc_de_fa, new Object[] { maPLT, e.toString() });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset B4168_Nhomhang....", new Object[0]);
     resetValue();
   }

   private void resetValue()
   {
     this.nhomhang = new DmPhanLoaiThuoc();
     this.nhomhang.setDmloaithuocMaso(new DmLoaiThuoc());
     this.listPhanLoaiThuocs = new ArrayList();
   }

   public static long getSerialVersionUID()
   {
     return 1L;
   }

   public Log getLog()
   {
     return this.log;
   }

   public void setLog(Log log)
   {
     this.log = log;
   }

   public DmPhanLoaiThuoc getNhomhang()
   {
     return this.nhomhang;
   }

   public void setNhomhang(DmPhanLoaiThuoc nhomhang)
   {
     this.nhomhang = nhomhang;
   }

   public List<DmPhanLoaiThuoc> getListPhanLoaiThuocs()
   {
     return this.listPhanLoaiThuocs;
   }

   public void setListPhanLoaiThuocs(List<DmPhanLoaiThuoc> listPhanLoaiThuocs)
   {
     this.listPhanLoaiThuocs = listPhanLoaiThuocs;
   }

   public List<SelectItem> getListLoaiThuocs()
   {
     return this.listLoaiThuocs;
   }

   public void setListLoaiThuocs(List<SelectItem> listLoaiThuocs)
   {
     this.listLoaiThuocs = listLoaiThuocs;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.B4168_Nhomhang

 * JD-Core Version:    0.7.0.1

 */