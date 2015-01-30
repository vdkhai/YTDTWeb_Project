 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class GetTonKhoBHYTAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetTonKhoBHYTAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.info("-----Begin performAction()-----" + request);
     StringBuffer buf = new StringBuffer();
     String maHang = "";

     StringTokenizer sToken = new StringTokenizer(request, ";");
     if (sToken != null) {
       maHang = sToken.nextToken();
     }
     log.info("Ma thuoc :" + maHang);
     if (maHang != null) {
       maHang = maHang.trim();
     }
     List<TonKho> listTonKho = new ArrayList();
     try
     {
       log.info("call webservice");
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       log.info("NhomKhoaMa: " + IConstantsRes.KHOA_BHYT_MA);
       List<TonKho> listTk = tkDelegate.findTonKhoByDtmMaAndKhoMa(maHang, IConstantsRes.KHOA_BHYT_MA);
       log.info("listTk: " + listTk);
       if (listTk != null) {
         if ((listTk != null) && (listTk.size() > 0)) {
           for (TonKho tk : listTk)
           {
             log.info("-----Ton kho ma: %s" + tk.getTonkhoMa());
             listTonKho.add(tk);
           }
         }
       }
       log.info("end webservice");
     }
     catch (Exception e)
     {
       log.error("-:( Error: " + e.toString());
     }
     buf.append("<list>");
     buf.append("<record TonKhoMa='' TonKhoTon='' MaHang='' TenHang='' QuocGiaSx='' HangSx='' DonGia='' />");
     if (listTonKho != null) {
       for (TonKho tonkho : listTonKho) {
         buf.append("<record  TonKhoMa='" + tonkho.getTonkhoMa() + "' TonKhoTon='" + tonkho.getTonkhoTon() + "' MaHang='" + tonkho.getDmthuocMaso().getDmthuocMa() + "' TenHang='" + tonkho.getDmthuocMaso().getDmthuocTen() + "' QuocGiaSx='" + tonkho.getDmquocgiaMaso().getDmquocgiaTen() + "' HangSx='" + tonkho.getDmnhasanxuatMaso().getDmnhasanxuatTen() + "' DonGia='" + tonkho.getTonkhoDongiaban() + "' />");
       }
     }
     buf.append("</list>");
     log.info("-----End performAction()-----" + buf.toString());
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetTonKhoBHYTAction

 * JD-Core Version:    0.7.0.1

 */