 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmGioiTinh;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.richfaces.json.JSONArray;
 import org.richfaces.json.JSONObject;
 import org.richfaces.json.XML;

 public class GetLichSuBenhNhanAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetLichSuBenhNhanAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.info("-----Begin performAction()-----");
     StringBuffer buf = new StringBuffer();
     log.info("---request: " + request);
     String[] para = request.split(";");
     String maBenhNhan = para[0].trim();
     log.info("---maBenhNhan: " + maBenhNhan);
     String maTiepDon = para[1].trim();
     log.info("---maTiepDon: " + maTiepDon);


     String hoBN = para[2].trim();
     log.info("---hoBN: " + hoBN);
     String tenBN = para[3].trim();
     log.info("---tenBN: " + tenBN);
     if (maBenhNhan.equals("")) {
       maBenhNhan = null;
     }
     if (maTiepDon.equals("")) {
       maTiepDon = null;
     }
     String searchBN = "";
     if (hoBN.equals(""))
     {
       if (tenBN.equals("")) {
         searchBN = null;
       } else {
         searchBN = "% " + tenBN;
       }
     }
     else if (tenBN.equals("")) {
       searchBN = hoBN + "%";
     } else {
       searchBN = hoBN + "% " + tenBN;
     }
     List<TiepDon> listSearch = TiepDonDelegate.getInstance().findTiepDonForTimKiemBN(maBenhNhan, searchBN, maTiepDon);
     BenhNhan bn = null;
     JSONArray arrBn = new JSONArray();
     for (TiepDon td : listSearch)
     {
       JSONObject bnJson = new JSONObject();
       bnJson.put("mtd", td.getTiepdonMa());
       bn = td.getBenhnhanMa();
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
       Date dSinh = bn.getBenhnhanNgaysinh();
       String nsinh = null;
       if (dSinh != null) {
         nsinh = new SimpleDateFormat("dd/MM/yyyy").format(dSinh);
       }
       if (nsinh == null) {
         nsinh = bn.getBenhnhanNamsinh();
       }
       bnJson.put("nsinh", nsinh == null ? " " : nsinh);
       if (bn.getDantocMa() != null) {
         bnJson.put("dtoc", bn.getDantocMa().getDmdantocTen());
       } else {
         bnJson.put("dtoc", " ");
       }
       arrBn.put(bnJson);
     }
     JSONObject result = new JSONObject();
     result.put("listBn", arrBn);

     buf.append(XML.toString(result));
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetLichSuBenhNhanAction

 * JD-Core Version:    0.7.0.1

 */