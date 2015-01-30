 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.util.Utils;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.richfaces.json.JSONArray;
 import org.richfaces.json.JSONObject;
 import org.richfaces.json.XML;

 public class GetTimKiemBenhNhanNoiTruAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetTimKiemBenhNhanNoiTruAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.info("-----Begin performAction()-----" + request);
     StringBuffer buf = new StringBuffer();
     log.info("---request: " + request);
     String[] para = request.split(";");

     String soVaoVien = para[0].trim();
     log.info("---soVaoVien: " + soVaoVien);
     String hoTen = para[1].trim();
     log.info("---hoTen: " + hoTen);
     String tuNgay = para[2].trim();
     log.info("---tuNgay: " + tuNgay);

     String denNgay = para[3].trim();
     log.info("---denNgay: " + denNgay);

     SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
     Date dTuNgay = df.parse(tuNgay);
     Date dDenNgay = df.parse(denNgay);



     List<Hsba> listSearch = HsbaDelegate.getInstance().findBySoVaoVienHoTenNgayGioVaoVien(soVaoVien, hoTen, dTuNgay, dDenNgay);
     BenhNhan bn = null;
     JSONArray arrBn = new JSONArray();
     for (Hsba hsba : listSearch)
     {
       bn = hsba.getBenhnhanMa();


       JSONObject bnJson = new JSONObject();
       bnJson.put("mtd", hsba.getHsbaSovaovien());

       bnJson.put("bnMa", bn.getBenhnhanMa());
       bnJson.put("ten", bn.getBenhnhanHoten());
       if (bn.getDmgtMaso() != null) {
         bnJson.put("gtinh", bn.getDmgtMaso().getDmgtTen());
       } else {
         bnJson.put("gtinh", " ");
       }
       bnJson.put("tuoi", bn.getBenhnhanTuoi() == null ? " " : String.valueOf(bn.getBenhnhanTuoi()));
       if (bn.getBenhnhanDonvituoi() == null) {
         bnJson.put("dvt", " ");
       } else if (bn.getBenhnhanDonvituoi().shortValue() == 1) {
         bnJson.put("dvt", "1");
       } else if (bn.getBenhnhanDonvituoi().shortValue() == 2) {
         bnJson.put("dvt", "2");
       } else if (bn.getBenhnhanDonvituoi().shortValue() == 3) {
         bnJson.put("dvt", "3");
       }
       bnJson.put("nsinh", bn.getBenhnhanNgaysinh() == null ? " " : new SimpleDateFormat("dd/MM/yyyy").format(bn.getBenhnhanNgaysinh()));
       if (bn.getDantocMa() != null) {
         bnJson.put("dtoc", bn.getDantocMa().getDmdantocTen());
       } else {
         bnJson.put("dtoc", " ");
       }
       arrBn.put(bnJson);
     }
     JSONObject result = new JSONObject();
     result.put("listBn", arrBn);
     log.info("---list find: " + result.toString());
     buf.append(XML.toString(result));
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetTimKiemBenhNhanNoiTruAction

 * JD-Core Version:    0.7.0.1

 */