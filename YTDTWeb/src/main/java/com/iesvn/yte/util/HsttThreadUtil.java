 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import java.io.PrintStream;
 import java.util.Date;

 public class HsttThreadUtil
   extends Thread
 {
   private Hsba hoSoBenhAn;

   public Hsba getHoSoBenhAn()
   {
     return this.hoSoBenhAn;
   }

   public void setHoSoBenhAn(Hsba hoSoBenhAn)
   {
     this.hoSoBenhAn = hoSoBenhAn;
   }

   public void run()
   {
     System.out.println("HsttThreadUtil starting -- " + new Date());

     HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
     HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(this.hoSoBenhAn.getHsbaSovaovien());
     if (hsThtoan != null) {
       hsThtoanDelegate.remove(hsThtoan);
     }
     hsThtoan = new HsThtoan();
     hsThtoan.setHsbaSovaovien(this.hoSoBenhAn);
     HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(this.hoSoBenhAn);
     hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
     hsThtoan.setHsthtoanKhoa(this.hoSoBenhAn.getHsbaKhoadangdt());
     Utils.setInforForHsThToan(hsThtoan);
     hsThtoanDelegate.create(hsThtoan);

     System.out.println("HsttThreadUtil stopping -- " + new Date());
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.HsttThreadUtil

 * JD-Core Version:    0.7.0.1

 */