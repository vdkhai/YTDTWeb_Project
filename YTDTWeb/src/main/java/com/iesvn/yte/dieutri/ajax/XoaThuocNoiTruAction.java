 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.rmi.RemoteException;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.rpc.ServiceException;
 import org.w3c.dom.Document;
 import org.w3c.dom.NamedNodeMap;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;

 public class XoaThuocNoiTruAction
   extends Action
 {
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
       NodeList listTNT = xmlDoc.getElementsByTagName("THUOC_NOI_TRU");
       if (listTNT.getLength() > 0)
       {
         System.out.println("Tiep don count " + listTNT.getLength());
         for (int i = 0; i < listTNT.getLength(); i++)
         {
           Node nodeTNT = listTNT.item(i);
           System.out.println(nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHIEU").getTextContent());
           String maPhu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHU").getTextContent();
           System.out.println(maPhu);
           ThuocNoiTru tnt = null;
           try
           {
             if (getDelThuocNoiTru(nodeTNT) != null)
             {
               tnt = getDelThuocNoiTru(nodeTNT);
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
             ThuocNoiTruDelegate tntWS = ThuocNoiTruDelegate.getInstance();
             String tntID = tntWS.delHuyTNT(tnt);
             System.out.println("insert phieu nhap kho " + tntID);
             if (tntID != "") {
               okId = okId + maPhu + "---";
             } else {
               errorId = errorId + maPhu + "---";
             }
           }
           catch (Exception ex)
           {
             System.out.println("Error: " + ex.toString());
           }
         }
       }
       xml = okId + ";;;" + errorId;
       System.out.println("xml response: " + xml);
     }
     return xml;
   }

   public ThuocNoiTru getDelThuocNoiTru(Node nodeTNT)
     throws ServiceException, RemoteException, Exception
   {
     ThuocNoiTru tnt = new ThuocNoiTru();
     if (nodeTNT != null)
     {
       String tntmaphieu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHIEU").getTextContent();
       if (tntmaphieu != "") {
         tnt.setThuocnoitruMaphieu(tntmaphieu);
       } else {
         return null;
       }
     }
     return tnt;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.XoaThuocNoiTruAction

 * JD-Core Version:    0.7.0.1

 */