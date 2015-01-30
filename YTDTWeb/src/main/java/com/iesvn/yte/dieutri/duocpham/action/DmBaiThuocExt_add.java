 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.delegate.BaithuocThuocDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import com.iesvn.yte.dieutri.entity.BaithuocThuoc;
 import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
 import java.util.*;
 import java.util.Map.Entry;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.contexts.Context;
 import org.jboss.seam.contexts.Contexts;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.jsf.ListDataModel;
 import org.jboss.seam.log.Log;

 @Name("DmBaiThuoc_add")
 @Scope(ScopeType.SESSION)
 public class DmBaiThuocExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @DataModel
   private ArrayList<BaithuocThuoc> listbaithuocThuoc;
   @DataModelSelection
   private BaithuocThuoc selected;
   private BaithuocThuoc baithuocThuoc;
   @In(required=false)
   @Out(required=false)
   private DmBaiThuoc dmBaiThuoc;
   private Boolean isUpdate = Boolean.valueOf(false);
   private String soluong;
   private DmThuocDelegate dmThuocDelegate;
   private String dmtMa;
   private String dmtTen = "";
   HashMap<String, DmThuoc> hmDmThuoc = new HashMap();
   private List<SelectItem> listDmThuocs = new ArrayList();
   private ArrayList<BaithuocThuoc> listbaithuocThuocDel;
   @Logger
   private Log log;

   public String getSoluong()
   {
     return this.soluong;
   }

   public void setSoluong(String soluong)
   {
     this.soluong = soluong;
   }

   public Boolean getIsUpdate()
   {
     return this.isUpdate;
   }

   public void setIsUpdate(Boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public ArrayList<BaithuocThuoc> getListbaithuocThuoc()
   {
     return this.listbaithuocThuoc;
   }

   public void setListbaithuocThuoc(ArrayList<BaithuocThuoc> listbaithuocThuoc)
   {
     this.listbaithuocThuoc = listbaithuocThuoc;
   }

   public BaithuocThuoc getBaithuocThuoc()
   {
     return this.baithuocThuoc;
   }

   public void setBaithuocThuoc(BaithuocThuoc baithuocThuoc)
   {
     this.baithuocThuoc = baithuocThuoc;
   }

   public BaithuocThuoc getSelected()
   {
     return this.selected;
   }

   public void setSelected(BaithuocThuoc selected)
   {
     this.selected = selected;
   }

   @Create
   public String init()
   {
     this.log.info("Init DmBaiThuocExt_add....", new Object[0]);

     resetValue();

     return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy_add";
   }

   public String update()
   {
     this.log.info("Init DmBaiThuocExt_update....", new Object[0]);

     int rowIndex = -1;
     ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmBaiThuoc");
     rowIndex = lsDataModel.getRowIndex();
     if (rowIndex != -1) {
       this.dmBaiThuoc = ((DmBaiThuoc)((List)lsDataModel.getWrappedData()).get(rowIndex));
     }
     if (this.dmBaiThuoc != null) {
       this.listbaithuocThuoc = ((ArrayList)DieuTriUtilDelegate.getInstance().findByAllConditions("BaithuocThuoc", "dmbaithuocMaso", "dmthuocMaso", this.dmBaiThuoc.getDmbaithuocMaso() + "", ""));
     }
     return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy_add";
   }

   public void save()
   {
     this.log.info("Save DmBaiThuocExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.dmBaiThuoc.getDmbaithuocMa();
     this.dmBaiThuoc.setDmbaithuocNgaygiocn(Double.valueOf(date.getTime()));
     this.dmBaiThuoc.setDmbaithuocDt(Boolean.valueOf(true));
     setMaFromTen();
     try
     {
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       BaithuocThuocDelegate baithuocThuocDel = BaithuocThuocDelegate.getInstance();
       System.out.println("listbaithuocThuocDel.size(): " + this.listbaithuocThuocDel.size());
       if ((this.listbaithuocThuocDel != null) && (this.listbaithuocThuocDel.size() > 0)) {
         for (int i = 0; i < this.listbaithuocThuocDel.size(); i++)
         {
           BaithuocThuoc temp = (BaithuocThuoc)this.listbaithuocThuocDel.get(i);
           if (temp.getBaithuocthuocMaso() != null) {
             baithuocThuocDel.remove(temp);
           }
         }
       }
       if (this.dmBaiThuoc.getDmbaithuocMaso() == null) {
         dtUtil.create(this.dmBaiThuoc);
       } else {
         dtUtil.edit(this.dmBaiThuoc);
       }
       this.dmBaiThuoc = ((DmBaiThuoc)dtUtil.findByMa(this.dmBaiThuoc.getDmbaithuocMa(), "DmBaiThuoc", "dmbaithuocMa"));
       System.out.println("listbaithuocThuoc.size(): " + this.listbaithuocThuoc.size());
       for (int i = 0; i < this.listbaithuocThuoc.size(); i++)
       {
         BaithuocThuoc temp = (BaithuocThuoc)this.listbaithuocThuoc.get(i);
         temp.setDmbaithuocMaso(this.dmBaiThuoc);
         temp.setBaithuocthuocNgaygiocn(Double.valueOf(date.getTime()));
         if (temp.getBaithuocthuocMaso() == null) {
           dtUtil.create(temp);
         } else {
           dtUtil.edit(temp);
         }
       }
       FacesMessages.instance().add(IConstantsRes.baithuoc_cr_su, new Object[] { this.dmBaiThuoc.getDmbaithuocMa() });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.baithuoc_cr_fa, new Object[] { this.dmBaiThuoc.getDmbaithuocMa() });
     }
   }

   public void setDmtMa(String dmtMa)
   {
     this.dmtMa = dmtMa;
   }

   public String getDmtMa()
   {
     return this.dmtMa;
   }

   public String getDmtTen()
   {
     return this.dmtTen;
   }

   public void setDmtTen(String dmtTen)
   {
     this.dmtTen = dmtTen;
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
     this.log.info("-----BEGIN onblurMaThuocAction()-----", new Object[0]);
     if (this.dmtMa != null)
     {
       DmThuoc dmThuoc = (DmThuoc)this.hmDmThuoc.get(this.dmtMa.toUpperCase());
       if (dmThuoc != null)
       {
         setDmtTen(dmThuoc.getDmthuocTen());
         this.baithuocThuoc.setDmthuocMaso(dmThuoc);
         this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
       }
       else
       {
         setDmtTen("");
         this.baithuocThuoc.setDmthuocMaso(new DmThuoc());
       }
     }
     this.log.info("-----END onblurMaThuocAction()-----", new Object[0]);
   }

   public void onblurTenThuocAction()
   {
     this.log.info("-----BEGIN onblurTenThuocAction()-----", new Object[0]);
     Boolean hasTenThuoc = Boolean.valueOf(false);
     String maThuoc = "";
     DmThuoc dmThuocFinded = new DmThuoc();
     if (this.hmDmThuoc != null)
     {
       Set set = this.hmDmThuoc.entrySet();
       Iterator i = set.iterator();
       while (i.hasNext())
       {
         Map.Entry me = (Map.Entry)i.next();
         DmThuoc dmThuoc = (DmThuoc)me.getValue();
         if ((dmThuoc.getDmthuocTen().trim() == this.dmtTen) || (dmThuoc.getDmthuocTen().trim().equals(this.dmtTen)))
         {
           hasTenThuoc = Boolean.valueOf(true);
           maThuoc = dmThuoc.getDmthuocMa();
           dmThuocFinded = dmThuoc;
           break;
         }
       }
     }
     if (hasTenThuoc.booleanValue())
     {
       setDmtMa(maThuoc);
       this.baithuocThuoc.setDmthuocMaso(dmThuocFinded);
       this.log.info("-----DA THAY DMTHUOC-----", new Object[0]);
     }
     else
     {
       setDmtMa("");
       this.baithuocThuoc.setDmthuocMaso(new DmThuoc());
     }
     this.log.info("-----END onblurTenThuocAction()-----", new Object[0]);
   }

   public void refreshDmThuoc()
   {
     this.listDmThuocs.clear();
     this.hmDmThuoc.clear();
     this.dmThuocDelegate = DmThuocDelegate.getInstance();

     List<DmThuoc> lstDmThuoc = new ArrayList();
     lstDmThuoc = this.dmThuocDelegate.findDongYAll();
     if ((lstDmThuoc != null) && (lstDmThuoc.size() > 0)) {
       for (DmThuoc o : lstDmThuoc)
       {
         this.listDmThuocs.add(new SelectItem(o.getDmthuocTen()));
         this.hmDmThuoc.put(o.getDmthuocMa(), o);
       }
     }
   }

   public String goBack()
   {
     this.log.info("GoBack DmBaiThuocExt_add....", new Object[0]);

     return "/B4_Duocpham/KhoChinh/B4155_Baithuocdongy";
   }

   private void setMaFromTen()
   {
     this.log.info("setMaFromTen DmBaiThuocExt_add....", new Object[0]);

     int count = 0;
     String maTemp = this.dmBaiThuoc.getDmbaithuocMa();
     List<DmBaiThuoc> ls = new ArrayList();

     ls = DieuTriUtilDelegate.getInstance().findMaLike("DmBaiThuoc", "dmbaithuocMa", "dmbaithuocDt", this.dmBaiThuoc.getDmbaithuocMa(), true);
     for (DmBaiThuoc dmBaiThuocTemp : ls)
     {
       if ((this.dmBaiThuoc.getDmbaithuocMaso() != null) &&
         (this.dmBaiThuoc.getDmbaithuocMa().equals(dmBaiThuocTemp.getDmbaithuocMa()))) {
         return;
       }
       String temp = dmBaiThuocTemp.getDmbaithuocMa().substring(6);
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
     this.dmBaiThuoc.setDmbaithuocMa(maTemp);
   }

   public void reset()
   {
     this.log.info("Reset DmBaiThuocExt_add....", new Object[0]);

     resetValue();
   }

   public void themCt()
   {
     this.log.info("-----themCt()-----", new Object[0]);
     if (!this.isUpdate.booleanValue()) {
       try
       {
         BaithuocThuoc baithuocThuocNew = (BaithuocThuoc)BeanUtils.cloneBean(this.baithuocThuoc);
         boolean update = false;
         for (int i = 0; i < this.listbaithuocThuoc.size(); i++)
         {
           System.out.println("Ma thuoc grid: " + ((BaithuocThuoc)this.listbaithuocThuoc.get(i)).getDmthuocMaso().getDmthuocMaso());
           System.out.println("Ma thuoc add moi: " + this.baithuocThuoc.getDmthuocMaso().getDmthuocMaso());
           if (((BaithuocThuoc)this.listbaithuocThuoc.get(i)).getDmthuocMaso().getDmthuocMaso().equals(this.baithuocThuoc.getDmthuocMaso().getDmthuocMaso()))
           {
             baithuocThuocNew.setBaithuocthuocMaso(((BaithuocThuoc)this.listbaithuocThuoc.get(i)).getBaithuocthuocMaso());
             this.listbaithuocThuoc.set(i, baithuocThuocNew);
             update = true;
             break;
           }
         }
         if (!update)
         {
           DmThuoc dmThuoc = (DmThuoc)DieuTriUtilDelegate.getInstance().findByMa(this.baithuocThuoc.getDmthuocMaso().getDmthuocMa(), "DmThuoc", "dmthuocMa");
           if (dmThuoc != null)
           {
             baithuocThuocNew.setDmthuocMaso(dmThuoc);
             this.listbaithuocThuoc.add(baithuocThuocNew);
           }
         }
       }
       catch (IllegalAccessException e)
       {
         e.printStackTrace();
       }
       catch (InstantiationException e)
       {
         e.printStackTrace();
       }
       catch (InvocationTargetException e)
       {
         e.printStackTrace();
       }
       catch (NoSuchMethodException e)
       {
         e.printStackTrace();
       }
     }
     setNewValueForBaithuocThuoc();
     this.dmtMa = "";
     this.dmtTen = "";
   }

   public void deleteCt(Integer index)
   {
     this.log.info("-----deleteCt()-----", new Object[0]);
     this.log.info("-----index: " + index, new Object[0]);
     this.listbaithuocThuocDel.add(this.listbaithuocThuoc.get(index.intValue()));
     this.listbaithuocThuoc.remove(index.intValue());
     this.log.info("-----listCtNhapKho.size(): " + this.listbaithuocThuoc.size(), new Object[0]);
   }

   public void selectCt(Integer index)
   {
     this.log.info("-----selectCt()-----", new Object[0]);
     this.log.info("-----index: " + index, new Object[0]);
     this.baithuocThuoc = ((BaithuocThuoc)this.listbaithuocThuoc.get(index.intValue()));
   }

   private void resetValue()
   {
     this.dmBaiThuoc = new DmBaiThuoc();
     this.listbaithuocThuoc = new ArrayList();
     this.listbaithuocThuocDel = new ArrayList();
     this.dmtMa = "";
     this.dmtTen = "";
     refreshDmThuoc();
     setNewValueForBaithuocThuoc();
   }

   private void setNewValueForBaithuocThuoc()
   {
     this.baithuocThuoc = new BaithuocThuoc();
     this.baithuocThuoc.setDmbaithuocMaso(new DmBaiThuoc());
     this.baithuocThuoc.setDmthuocMaso(new DmThuoc());
   }

   public DmBaiThuoc getDmBaiThuoc()
   {
     return this.dmBaiThuoc;
   }

   public void setDmBaiThuoc(DmBaiThuoc dmBaiThuoc)
   {
     this.dmBaiThuoc = dmBaiThuoc;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.DmBaiThuocExt_add

 * JD-Core Version:    0.7.0.1

 */