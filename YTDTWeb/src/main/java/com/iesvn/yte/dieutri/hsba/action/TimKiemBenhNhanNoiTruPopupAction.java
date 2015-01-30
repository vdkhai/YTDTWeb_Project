 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.richfaces.json.JSONArray;
 import org.richfaces.json.JSONException;
 import org.richfaces.json.JSONObject;

 @Scope(ScopeType.SESSION)
 @Name("danhsachbenhnhannoitru")
 @Synchronized(timeout=6000000L)
 public class TimKiemBenhNhanNoiTruPopupAction
   implements Serializable
 {
   private static final long serialVersionUID = -493331384169670628L;
   private static Logger log = Logger.getLogger(TimKiemBenhNhanNoiTruPopupAction.class);
   private String result;
   @DataModel
   private List<ItemForSearch> listForDanhsachbenhnhan;

   public List<ItemForSearch> getListForDanhsachbenhnhan()
   {
     return this.listForDanhsachbenhnhan;
   }

   public void setListForDanhsachbenhnhan(List<ItemForSearch> listForDanhsachbenhnhan)
   {
     this.listForDanhsachbenhnhan = listForDanhsachbenhnhan;
   }

   @Create
   public void init()
   {
     log.info("---init");
     this.listForDanhsachbenhnhan = new ArrayList();
   }

   public void fillList()
   {
     this.listForDanhsachbenhnhan = new ArrayList();
     log.info(this.result);
     if (((this.result != null ? 1 : 0) & (!this.result.equals("") ? 1 : 0)) != 0)
     {
       JSONArray arr;
       try
       {
         arr = new JSONObject(this.result).getJSONArray("listBn");
       }
       catch (JSONException ee)
       {
         arr = new JSONArray();
         try
         {
           arr.put(new JSONObject(this.result).getJSONObject("listBn"));
         }
         catch (JSONException e) {}
       }
       try
       {
         ItemForSearch item = null;
         for (int i = 0; i < arr.length(); i++)
         {
           item = new ItemForSearch();
           JSONObject obj = arr.getJSONObject(i);
           item.setBnMa(obj.getString("bnMa"));
           item.setBnHoten(obj.getString("ten"));
           item.setBnGtinh(obj.getString("gtinh"));
           item.setBnTuoi(obj.getString("tuoi"));
           if (obj.getString("dvt").equals("1")) {
             item.setBnDvtuoi(IConstantsRes.NAM);
           } else if (obj.getString("dvt").equals("2")) {
             item.setBnDvtuoi(IConstantsRes.THANG);
           } else if (obj.getString("dvt").equals("3")) {
             item.setBnDvtuoi(IConstantsRes.NGAY);
           } else {
             item.setBnDvtuoi("");
           }
           item.setBnNgaysinh(obj.getString("nsinh"));
           item.setBnDantoc(obj.getString("dtoc"));
           item.setTdMa(obj.getString("mtd"));
           this.listForDanhsachbenhnhan.add(item);
         }
       }
       catch (Exception e) {}
     }
   }

   public String getResult()
   {
     return this.result;
   }

   public void setResult(String result)
   {
     this.result = result;
   }

   public class ItemForSearch
   {
     private String bnMa;
     private String bnHoten;
     private String bnGtinh;
     private String bnTuoi;
     private String bnDvtuoi;
     private String bnNgaysinh;
     private String bnDantoc;
     private String tdMa;

     public ItemForSearch() {}

     public String getBnMa()
     {
       return this.bnMa;
     }

     public void setBnMa(String bnMa)
     {
       this.bnMa = bnMa;
     }

     public String getBnHoten()
     {
       return this.bnHoten;
     }

     public void setBnHoten(String bnHoten)
     {
       this.bnHoten = bnHoten;
     }

     public String getBnGtinh()
     {
       return this.bnGtinh;
     }

     public void setBnGtinh(String bnGtinh)
     {
       this.bnGtinh = bnGtinh;
     }

     public String getBnTuoi()
     {
       return this.bnTuoi;
     }

     public void setBnTuoi(String bnTuoi)
     {
       this.bnTuoi = bnTuoi;
     }

     public String getBnDvtuoi()
     {
       return this.bnDvtuoi;
     }

     public void setBnDvtuoi(String bnDvtuoi)
     {
       this.bnDvtuoi = bnDvtuoi;
     }

     public String getBnNgaysinh()
     {
       return this.bnNgaysinh;
     }

     public void setBnNgaysinh(String bnNgaysinh)
     {
       this.bnNgaysinh = bnNgaysinh;
     }

     public String getBnDantoc()
     {
       return this.bnDantoc;
     }

     public void setBnDantoc(String bnDantoc)
     {
       this.bnDantoc = bnDantoc;
     }

     public String getTdMa()
     {
       return this.tdMa;
     }

     public void setTdMa(String tdMa)
     {
       this.tdMa = tdMa;
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.TimKiemBenhNhanNoiTruPopupAction

 * JD-Core Version:    0.7.0.1

 */