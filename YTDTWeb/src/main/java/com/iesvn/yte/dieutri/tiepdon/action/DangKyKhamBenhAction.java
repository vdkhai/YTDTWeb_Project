 package com.iesvn.yte.dieutri.tiepdon.action;

 import java.io.PrintStream;
 import java.io.Serializable;
 import java.rmi.RemoteException;
 import javax.xml.rpc.ServiceException;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.web.RequestParameter;

 @Scope(ScopeType.SESSION)
 @Name("B111_Kiemtrangaytaikham")
 @Synchronized(timeout=6000000L)
 public class DangKyKhamBenhAction
   implements Serializable
 {
   @RequestParameter("messg")
   private String messg;
   @RequestParameter("mabn")
   public String maBN;
   @RequestParameter("matd")
   public String maTD;
   @In(required=false)
   @Out(required=false)
   private String benhNhan_ma;
   @In(required=false)
   @Out(required=false)
   private String tiepDon_ma;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strInit;
   private boolean kiemtra = true;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String goToThamKhamVaXuTri;

   public String getGoToThamKhamVaXuTri()
   {
     return this.goToThamKhamVaXuTri;
   }

   public void setGoToThamKhamVaXuTri(String goToThamKhamVaXuTri)
   {
     this.goToThamKhamVaXuTri = goToThamKhamVaXuTri;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   @Factory("strInit")
   public void init()
   {
     this.benhNhan_ma = this.maBN;
     this.tiepDon_ma = this.maTD;
     this.strInit = "";
   }

   public String getMessg()
   {
     return this.messg;
   }

   public void setMessg(String messg)
   {
     this.messg = messg;
   }

   public String getMaBN()
   {
     return this.maBN;
   }

   public void setMaBN(String maBN)
   {
     this.maBN = maBN;
   }

   public String getMaTD()
   {
     return this.maTD;
   }

   public void setMaTD(String maTD)
   {
     this.maTD = maTD;
   }

   public boolean isKiemtra()
   {
     return this.kiemtra;
   }

   public void setKiemtra(boolean kiemtra)
   {
     this.kiemtra = kiemtra;
   }

   public String getBenhNhan_ma()
   {
     return this.benhNhan_ma;
   }

   public void setBenhNhan_ma(String benhNhanMa)
   {
     this.benhNhan_ma = benhNhanMa;
   }

   public String getTiepDon_ma()
   {
     return this.tiepDon_ma;
   }

   public void setTiepDon_ma(String tiepdonMa)
   {
     this.tiepDon_ma = tiepdonMa;
   }

   public String linkLichSuKB()
   {
     this.kiemtra = false;
     return "/B1_Tiepdon/B115_Lichsubenhnhan.xhtml";
   }

   public String thamKham()
     throws ServiceException, RemoteException
   {
     System.out.println("DangKyKhamBenhAction.thamKham()*****************matd=" + this.maTiepDonOut);
     this.goToThamKhamVaXuTri = null;
     return "TiepDon_KhamBenh_ThamKhamXuTri";
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.DangKyKhamBenhAction

 * JD-Core Version:    0.7.0.1

 */