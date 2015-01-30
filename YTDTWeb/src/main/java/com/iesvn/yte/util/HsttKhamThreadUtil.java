 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import java.io.PrintStream;
 import java.util.Date;

 public class HsttKhamThreadUtil
   extends Thread
 {
   private TiepDon tiepDon;

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public void run()
   {
     System.out.println("HsttKhamThreadUtil starting -- " + new Date());

     HsThtoank hsttk = new HsThtoank();
     hsttk.setTiepdonMa(this.tiepDon);
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(this.tiepDon);
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);

     System.out.println("HsttKhamThreadUtil stopping -- " + new Date());
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.HsttKhamThreadUtil

 * JD-Core Version:    0.7.0.1

 */