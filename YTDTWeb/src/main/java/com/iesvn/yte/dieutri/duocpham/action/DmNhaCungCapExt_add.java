 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmTinh;
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
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmNhaCungCap_add")
 @Scope(ScopeType.SESSION)
 public class DmNhaCungCapExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DmNhaCungCap nhacungcap;
   private List<SelectItem> listTinh;
   private List<SelectItem> listNguonChuongTrinh;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmNhaCungCapExt_add....", new Object[0]);

     resetValue();
     initComboxTinh();
     initComboxNguonChuongTrinh();

     return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap_add";
   }

   public void save(int tinhId, int nguonCTId)
   {
     this.log.info("Save DmNhaCungCapExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.nhacungcap.getDmnhacungcapMa();
     this.nhacungcap.setDmnhacungcapNgaygiocn(Double.valueOf(date.getTime()));
     this.nhacungcap.setDmnhacungcapDt(Boolean.valueOf(true));
     setMaFromTen();
     if (tinhId == -1) {
       this.nhacungcap.setDmtinhMaso(null);
     }
     if (nguonCTId == -1) {
       this.nhacungcap.setDmnctMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.nhacungcap);
       FacesMessages.instance().add(IConstantsRes.nhacungcap_cr_su, new Object[] { "" });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.nhacungcap_cr_fa, new Object[] { "" });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmNhaCungCapExt_add....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4152_Nhacungcap";
   }

   private void setMaFromTen()
   {
     this.log.info("setMaFromTen DmNhaCungCapExt_add....", new Object[0]);

     int count = 0;
     String maTemp = this.nhacungcap.getDmnhacungcapMa();
     List<DmNhaCungCap> ls = new ArrayList();

     ls = DieuTriUtilDelegate.getInstance().findMaLike("DmNhaCungCap", "dmnhacungcapMa", "dmnhacungcapDt", this.nhacungcap.getDmnhacungcapMa(), true);
     for (DmNhaCungCap dmNhaCungCap : ls)
     {
       String temp = dmNhaCungCap.getDmnhacungcapMa().substring(6);
       int counTemp = Integer.parseInt(temp);
       if (count < counTemp) {
         count = counTemp;
       }
     }
     count++;
     if ((count + "").length() == 1)
     {
       if (count == 0) {
         maTemp = maTemp + "00001";
       } else {
         maTemp = maTemp + "0000" + count;
       }
     }
     else if ((count + "").length() == 2) {
       maTemp = maTemp + "000" + count;
     } else if ((count + "").length() == 3) {
       maTemp = maTemp + "00" + count;
     } else if ((count + "").length() == 4) {
       maTemp = maTemp + "0" + count;
     } else if ((count + "").length() == 5) {
       maTemp = maTemp + count;
     } else if ((count + "").length() == 4) {
       maTemp = maTemp + "99999" + count;
     }
     this.nhacungcap.setDmnhacungcapMa(maTemp);
   }

   public void reset()
   {
     this.log.info("Reset DmNhaCungCapExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.nhacungcap = new DmNhaCungCap();
     this.nhacungcap.setDmtinhMaso(new DmTinh());
     this.nhacungcap.setDmnctMaso(new DmNguonChuongTrinh());
   }

   private void initComboxTinh()
   {
     this.listTinh = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listTinh.add(item);

     List<DmTinh> lstempTinh = DieuTriUtilDelegate.getInstance().findAll("DmTinh", "dmtinhChon", true);
     for (DmTinh dmTinh : lstempTinh)
     {
       item = new SelectItem(dmTinh.getDmtinhMaso(), dmTinh.getDmtinhTen());
       this.listTinh.add(item);
     }
   }

   private void initComboxNguonChuongTrinh()
   {
     this.listNguonChuongTrinh = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listNguonChuongTrinh.add(item);

     List<DmNguonChuongTrinh> lstempNguonChuongtrinh = DieuTriUtilDelegate.getInstance().findAll("DmNguonChuongTrinh", "dmnctDt", true);
     for (DmNguonChuongTrinh dmNguonChuongTrinh : lstempNguonChuongtrinh)
     {
       item = new SelectItem(dmNguonChuongTrinh.getDmnctMaso(), dmNguonChuongTrinh.getDmnctTen());
       this.listNguonChuongTrinh.add(item);
     }
   }

   public DmNhaCungCap getNhacungcap()
   {
     return this.nhacungcap;
   }

   public void setNhacungcap(DmNhaCungCap nhacungcap)
   {
     this.nhacungcap = nhacungcap;
   }

   public List<SelectItem> getListTinh()
   {
     return this.listTinh;
   }

   public void setListTinh(List<SelectItem> listTinh)
   {
     this.listTinh = listTinh;
   }

   public List<SelectItem> getListNguonChuongTrinh()
   {
     return this.listNguonChuongTrinh;
   }

   public void setListNguonChuongTrinh(List<SelectItem> listNguonChuongTrinh)
   {
     this.listNguonChuongTrinh = listNguonChuongTrinh;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmNhaCungCapExt_add

 * JD-Core Version:    0.7.0.1

 */