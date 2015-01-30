 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import java.io.Serializable;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;

 @Scope(ScopeType.PAGE)
 @Name("ToaThuocPopup")
 @Synchronized(timeout=6000000L)
 public class ToaThuocPopupAction
   implements Serializable
 {
   private static final long serialVersionUID = -493331384169670628L;
   private static Logger log = Logger.getLogger(ToaThuocPopupAction.class);
   public static String maTiepDon;
   public static String hoTenBN;
   @DataModel
   public static List<ThuocPhongKham> ctThuocPhongKhamsPO;

   public String getMaTiepDon()
   {
     return maTiepDon;
   }

   public void setMaTiepDon(String maTiepDon)
   {
     maTiepDon = maTiepDon;
   }

   public String getHoTenBN()
   {
     return hoTenBN;
   }

   public void setHoTenBN(String hoTenBN)
   {
     hoTenBN = hoTenBN;
   }

   public List<ThuocPhongKham> getctThuocPhongKhamsPO()
   {
     return ctThuocPhongKhamsPO;
   }

   public void setctThuocPhongKhamsPO(List<ThuocPhongKham> ctThuocPhongKhamsPO)
   {
     ctThuocPhongKhamsPO = ctThuocPhongKhamsPO;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ToaThuocPopupAction

 * JD-Core Version:    0.7.0.1

 */