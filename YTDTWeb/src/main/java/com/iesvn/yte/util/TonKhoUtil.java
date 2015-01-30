 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import org.apache.log4j.Logger;

 public class TonKhoUtil
 {
   private static Logger log = Logger.getLogger(TonKhoUtil.class);

   public static TonKho getTonKhoHienTai(String sMaLK, Integer maKhoa)
   {
     TonKho tonkho = new TonKho();
     try
     {
       TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
       tonkho = tkDel.getTonKhoHienTai(sMaLK, maKhoa);
     }
     catch (Exception ex)
     {
       log.info("****** ERROR! " + ex.toString());
     }
     return tonkho;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.TonKhoUtil

 * JD-Core Version:    0.7.0.1

 */