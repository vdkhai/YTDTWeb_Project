 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DtDmBangGiaCanLamSan")
 @Scope(ScopeType.SESSION)
 public class DmBanggiacanlamsanExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private List<SelectItem> listPhanLoai;
   private boolean searchDaxoa = false;
   private int scrollerPage = 1;
   @In(required=false)
   @Out(required=false)
   private DtDmClsBangGia clsbanggia;
   @DataModel
   private List<DtDmClsBangGia> listDtDmClsBangGia;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmBanggiacanlamsanExt....", new Object[0]);

     resetValue();
     initComboxPhanLoai();
     return "/B3_Vienphi/ThuVienPhi/B3253_Banggiacanlamsan";
   }

   public void search()
   {
     this.log.info("Search DmBanggiacanlamsanExt...., search da xoa = " + this.searchDaxoa, new Object[0]);


     this.listDtDmClsBangGia = DtDmClsBangGiaDelegate.getInstance().findByAllConditions(this.clsbanggia.getDtdmclsbgMa(), this.clsbanggia.getDtdmclsbgDiengiai(), this.clsbanggia.getDtdmclsbgPhanloai().getDtdmclsMaso(), this.searchDaxoa);
     if (this.listDtDmClsBangGia.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   private void initComboxPhanLoai()
   {
     this.listPhanLoai = new ArrayList();

     SelectItem item = new SelectItem(new Integer(0), "");
     this.listPhanLoai.add(item);

     List<DtDmCls> lstempCls = DieuTriUtilDelegate.getInstance().findAll("DtDmCls", "dtdmclsChon", true);
     for (DtDmCls dtDmCls : lstempCls)
     {
       item = new SelectItem(dtDmCls.getDtdmclsMaso(), dtDmCls.getDtdmclsTen());
       this.listPhanLoai.add(item);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmBanggiacanlamsanExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DtDmClsBangGia)this.listDtDmClsBangGia.get(rowIndex)).getDtdmclsbgMa();
       try
       {
         DtDmClsBangGia be = (DtDmClsBangGia)this.listDtDmClsBangGia.get(rowIndex);

         be.setDtdmclsbgChon(Boolean.valueOf(false));
         be.setDtdmclsbgNgaygiocn(Double.valueOf(date.getTime()));
         DieuTriUtilDelegate.getInstance().edit(be);
         this.listDtDmClsBangGia.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.clsbanggia_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.clsbanggia_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmBanggiacanlamsanExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmBanggiacanlamsanExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.clsbanggia = new DtDmClsBangGia();
     this.listDtDmClsBangGia = new ArrayList();

     this.searchDaxoa = false;
     this.scrollerPage = 1;
   }

   public DtDmClsBangGia getClsbanggia()
   {
     return this.clsbanggia;
   }

   public void setClsbanggia(DtDmClsBangGia clsbanggia)
   {
     this.clsbanggia = clsbanggia;
   }

   public List<DtDmClsBangGia> getListDtDmClsBangGia()
   {
     return this.listDtDmClsBangGia;
   }

   public void setListDtDmClsBangGia(List<DtDmClsBangGia> listDtDmClsBangGia)
   {
     this.listDtDmClsBangGia = listDtDmClsBangGia;
   }

   public List<SelectItem> getListPhanLoai()
   {
     return this.listPhanLoai;
   }

   public void setListPhanLoai(List<SelectItem> listPhanLoai)
   {
     this.listPhanLoai = listPhanLoai;
   }

   public boolean isSearchDaxoa()
   {
     return this.searchDaxoa;
   }

   public void setSearchDaxoa(boolean searchDaxoa)
   {
     this.searchDaxoa = searchDaxoa;
   }

   public int getScrollerPage()
   {
     return this.scrollerPage;
   }

   public void setScrollerPage(int scrollerPage)
   {
     this.scrollerPage = scrollerPage;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DmBanggiacanlamsanExt

 * JD-Core Version:    0.7.0.1

 */