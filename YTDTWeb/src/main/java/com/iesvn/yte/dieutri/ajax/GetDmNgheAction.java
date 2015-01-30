 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmNgheAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmNghe = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmNghe = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmNgheNghiep", "dmnghenghiepNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmNghe != null) {
       for (Object obj : listDmNghe)
       {
         DmNgheNghiep dmNghe = (DmNgheNghiep)obj;
         String ngheNghiepBC = "";
         if (dmNghe.getDmnghenghiepPhanloai() != null) {
           ngheNghiepBC = String.valueOf(dmNghe.getDmnghenghiepPhanloai().getDmnghenghiepbcMaso());
         }
         buf.append("<record   MaSo='" + dmNghe.getDmnghenghiepMaso() + "' Ma='" + dmNghe.getDmnghenghiepMa() + "' Ten='" + Utils.findAndreplace(dmNghe.getDmnghenghiepTen()) + "' DMNGHENGHIEP_PHANLOAI='" + ngheNghiepBC + "' DMNGHENGHIEP_PHANLOAI2='" + Utils.reFactorString(dmNghe.getDmnghenghiepPhanloai2()) + "' DMNGHENGHIEP_AGEMIN='" + Utils.reFactorString(dmNghe.getDmnghenghiepAgemin()) + "' DMNGHENGHIEP_AGEMAX='" + Utils.reFactorString(dmNghe.getDmnghenghiepAgemax()) + "' NgayChinhSua='" + dmNghe.getDmnghenghiepNgaygiocn() + "' DT='" + Utils.reFactorString(dmNghe.getDmnghenghiepDt()) + "' QL='" + Utils.reFactorString(dmNghe.getDmnghenghiepQl()) + "' DP='" + Utils.reFactorString(dmNghe.getDmnghenghiepDp()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmNgheAction

 * JD-Core Version:    0.7.0.1

 */