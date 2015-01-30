 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.rmi.RemoteException;
 import java.util.List;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.rpc.ServiceException;
 import org.apache.log4j.Logger;
 import org.jboss.seam.faces.FacesMessages;
 import org.w3c.dom.Document;
 import org.w3c.dom.NamedNodeMap;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;

 public class HuyKhamAction
   extends Action
 {
   private static Logger log = Logger.getLogger(HuyKhamAction.class);

   public String performAction(String request)
   {
     String okId = "";
     String errorId = "";
     String xml = "";
     Document xmlDoc = null;

     System.out.println("xml " + request);
     try
     {
       xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
     }
     catch (ParserConfigurationException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     catch (SAXException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     catch (IOException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     if (xmlDoc != null)
     {
       NodeList listTD = xmlDoc.getElementsByTagName("TIEP_DON");
       if (listTD.getLength() > 0)
       {
         System.out.println("Tiep don count " + listTD.getLength());
         for (int i = 0; i < listTD.getLength(); i++)
         {
           Node nodeTD = listTD.item(i);
           System.out.println(nodeTD.getAttributes().getNamedItem("TIEPDON_MA").getTextContent());
           String maPhu = nodeTD.getAttributes().getNamedItem("TIEP_DON_MAPHU").getTextContent();
           System.out.println(maPhu);
           TiepDon td = null;
           try
           {
             if (getDelTiepDon(nodeTD) != null)
             {
               td = getDelTiepDon(nodeTD);
             }
             else
             {
               errorId = errorId + maPhu + "---";
               System.out.println("errorId " + errorId);
               continue;
             }
           }
           catch (Exception ex)
           {
             ex.printStackTrace();
           }
           System.out.println("-----------");
           try
           {
             ClsKhamDelegate ClsKhamDel = ClsKhamDelegate.getInstance();
             List<ClsKham> list = ClsKhamDel.findByTiepdonMaVaLoaiClsKham(td.getTiepdonMa());
             if ((list != null) && (list.size() > 0))
             {
               FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
               errorId = errorId + maPhu + "---";
               break;
             }
             HsbaDelegate hsbaDel = HsbaDelegate.getInstance();
             Hsba objHSBA = hsbaDel.findByTiepDonMa(td.getTiepdonMa());
             if (objHSBA != null)
             {
               FacesMessages.instance().add(IConstantsRes.TIEPDON_KHONG_THE_XOA_BENH_NHAN_CO_KB, new Object[0]);
               errorId = errorId + maPhu + "---";
               break;
             }
             TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
             String tdID = tdDelegate.delHuyKham(td);
             System.out.println("insert phieu nhap kho " + tdID);
             if (tdID != "") {
               okId = okId + maPhu + ",,," + tdID + "---";
             } else {
               errorId = errorId + maPhu + "---";
             }
             log.info("*****Ma tiep don da xoa:" + tdID);
             log.info("*****Ma okId:" + okId);
             log.info("*****Ma errorId:" + errorId);
           }
           catch (Exception ex)
           {
             System.out.println("Error: " + ex.toString());
             errorId = errorId + maPhu + "---";
           }
         }
       }
       xml = okId + ";;;" + errorId;
       System.out.println("xml response: " + xml);
     }
     return String.format("<result>%s</result>", new Object[] { xml });
   }

   public TiepDon getDelTiepDon(Node nodePN)
     throws ServiceException, RemoteException, Exception
   {
     TiepDon td = new TiepDon();
     if (nodePN != null)
     {
       String tiepdonma = nodePN.getAttributes().getNamedItem("TIEPDON_MA").getTextContent();
       if (tiepdonma != "") {
         td.setTiepdonMa(tiepdonma);
       } else {
         return null;
       }
     }
     return td;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.HuyKhamAction

 * JD-Core Version:    0.7.0.1

 */