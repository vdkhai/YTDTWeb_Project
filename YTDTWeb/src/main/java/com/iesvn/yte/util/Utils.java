 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.entity.HsThtoan;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import java.text.DecimalFormat;
 import java.text.NumberFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.ResourceBundle;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;

 public class Utils
 {
   private static Log log = LogFactory.getLog(Utils.class);
   public static String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm";
   public static String FORMAT_DATE_TIME_HOUR_FIRST = "HH:mm dd/MM/yyyy";
   public static String FORMAT_DATE = "dd/MM/yyyy";
   public static String FORMAT_DATE_TIME_HOUR = "dd/MM/yyyy HH:mm:ss";
   public static String XU_TRI_THUOC_TAI_BAN_KHAM = "1";
   public static String KE_TOA_BENH_NHAN_TU_MUA = "2";
   public static String KE_TOA_QUAY_BENH_VIEN = "3";
   private static final long ONE_HOUR = 3600000L;

   public static String formatNumber(Double d, String pattern)
   {
     if (d == null) {
       return "";
     }
     DecimalFormat df = new DecimalFormat(pattern);
     return df.format(d);
   }

   public static String getGioPhut(Date d)
   {
     Calendar cal = Calendar.getInstance();
     cal.setTime(d);
     String gioTmp = "" + cal.get(11);
     if (gioTmp.length() == 1) {
       gioTmp = "0" + gioTmp;
     }
     String phutTmp = "" + cal.get(12);
     if (phutTmp.length() == 1) {
       phutTmp = "0" + phutTmp;
     }
     return gioTmp + ":" + phutTmp;
   }

   public static String getResource(String key, String resourcePath)
   {
     ResourceBundle bundle = null;
     if (resourcePath == null) {
       bundle = ResourceBundle.getBundle("com.iesvn.yte.ApplicationResources");
     } else {
       bundle = ResourceBundle.getBundle(resourcePath);
     }
     String result = "";
     if (bundle != null) {
       result = bundle.getString(key);
     }
     return result;
   }

   public static String reFactorString(String value)
   {
     if (value == null) {
       return "";
     }
     return value.trim();
   }

   public static String reFactorString(Integer value)
   {
     if (value == null) {
       return "";
     }
     return String.valueOf(value);
   }

   public static String reFactorString(Float value)
   {
     if (value == null) {
       return "";
     }
     return String.valueOf(value);
   }

   public static String reFactorString(Double value)
   {
     if (value == null) {
       return "";
     }
     return String.valueOf(value);
   }

   public static String reFactorString(Boolean value)
   {
     if ((value == null) || (!value.booleanValue())) {
       return "0";
     }
     return "1";
   }

   public static String reFactorString(Calendar value)
   {
     if (value == null) {
       return "";
     }
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

     return df.format(value.getTime());
   }

   public static String reFactorString(Date value)
   {
     if (value == null) {
       return "";
     }
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

     return df.format(value);
   }

   public static String reFactorString(Short value)
   {
     if (value == null) {
       return "";
     }
     return String.valueOf(value);
   }

   public static String findAndreplace(String str)
   {
     if (str == null) {
       return "";
     }
     String result = "";
     for (int i = 0; i < str.length(); i++)
     {
       char c = str.charAt(i);
       if (c == '<') {
         result = result + "&lt;";
       } else if (c == '>') {
         result = result + "&gt;";
       } else if (c == '\'') {
         result = result + "%27";
       } else if (c == '&') {
         result = result + "&amp;";
       } else {
         result = result + c;
       }
     }
     return result;
   }

   public static String getCurrentDate()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     String curDate = sdf.format(currentDate);
     return curDate;
   }

   public static Calendar getDBDate(String gio, String ngay)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME);

     String tmpGio = gio;
     if (tmpGio.length() != 5) {
       return null;
     }
     Date tmp_date;
     try
     {
       tmp_date = formatter.parse(ngay + " " + tmpGio);
     }
     catch (ParseException e)
     {
       return null;
     }
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(tmp_date);

     return calendar;
   }

   public static Calendar getDBDateWithHour(int gio, String ngay, boolean dauNgay)
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME_HOUR);

     String tmpGio = String.valueOf(gio);
     while (tmpGio.length() < 2) {
       tmpGio = "0" + tmpGio;
     }
     if (dauNgay) {
       tmpGio = tmpGio + ":00:00";
     } else {
       tmpGio = tmpGio + ":59:59";
     }
     Date tmp_date;
     try
     {
       tmp_date = formatter.parse(ngay + " " + tmpGio);
     }
     catch (ParseException e)
     {
       return null;
     }
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(tmp_date);

     return calendar;
   }

   public static String createNextIndex(String strMSHS)
   {
     String newMHS = "";
     Date d = new Date();
     Calendar calendar = Calendar.getInstance();
     calendar.setTime(d);

     String curYear = new String("" + calendar.get(1));


     int yearLen = new Integer(IConstantsRes.YEAR_NUM_LENGTH).intValue();


     int orderLen = new Integer(IConstantsRes.ORDER_NUM_LENGTH).intValue();


     curYear = curYear.substring(curYear.length() - yearLen);
     String zeroFill = "";
     if (strMSHS.equals(""))
     {
       for (int i = 0; i < orderLen - 1; i++) {
         zeroFill = zeroFill + "0";
       }
       newMHS = zeroFill + 1 + curYear;
     }
     else
     {
       String keyYear = strMSHS.substring(strMSHS.length() - yearLen);
       if (curYear.endsWith(keyYear))
       {
         Integer keyOrder = new Integer(strMSHS.substring(0, strMSHS.indexOf(keyYear)));
         int newNum = keyOrder.intValue() + 1;
         for (int i = 0; i < orderLen - ("" + newNum).length(); i++) {
           zeroFill = zeroFill + "0";
         }
         newMHS = zeroFill + newNum + curYear;
       }
       else
       {
         int newNum = 1;
         for (int i = 0; i < orderLen - ("" + newNum).length(); i++) {
           zeroFill = zeroFill + "0";
         }
         newMHS = zeroFill + newNum + curYear;
       }
     }
     return newMHS;
   }

   public static String convertCalendar2Str(Calendar cal)
   {
     String strReturn = "";
     if (cal != null)
     {
       String strDay = "" + (cal.get(5) < 10 ? "0" + cal.get(5) : Integer.valueOf(cal.get(5)));
       String strMonth = "" + (cal.get(2) + 1 < 10 ? "0" + (cal.get(2) + 1) : Integer.valueOf(cal.get(2) + 1));
       strReturn = strDay + "/" + strMonth + "/" + cal.get(1);
     }
     return strReturn;
   }

   public static int compareDate(Calendar c1, Calendar c2)
   {
     int result = 0;
     if ((c1 == null) || (c2 == null)) {
       result = -2;
     } else if (c1.get(1) > c2.get(1)) {
       result = 1;
     } else if (c1.get(1) < c2.get(1)) {
       result = -1;
     } else if (c1.get(2) > c2.get(2)) {
       result = 1;
     } else if (c1.get(2) < c2.get(2)) {
       result = -1;
     } else if (c1.get(5) > c2.get(5)) {
       result = 1;
     } else if (c1.get(5) < c2.get(5)) {
       result = -1;
     }
     return result;
   }

   public static String formatLongDouble(Double value)
   {
     if (value == null) {
       return "";
     }
     try
     {
       NumberFormat formatter = NumberFormat.getNumberInstance();
       formatter.setGroupingUsed(true);
       formatter.setMaximumFractionDigits(2);

       return formatter.format(value.doubleValue());
     }
     catch (Exception e)
     {
       log.info("ERROR -- formatLongDouble : value = " + value);
       log.info(e.toString());
     }
     return "";
   }

   public static String taoDonViTuoi(int dvt)
   {
     String strDvt = null;
     switch (dvt)
     {
     case 1:
       strDvt = IConstantsRes.NAM; break;
     case 2:
       strDvt = IConstantsRes.THANG; break;
     case 3:
       strDvt = IConstantsRes.NGAY;
     }
     if (StringUtils.isNotBlank(strDvt)) {
       strDvt = "(" + strDvt + ")";
     }
     return strDvt;
   }

   private static double getTongChi(double thuocTrongDM, double thuocNDM, double VTTHTrongDM, double VTTHGNDM, double CLSTrongDM, double CLSNDM, double PhauThuatTrongDM, double PhauThuatNDM, double PhongTrongDM, double PhongNDM, double congKham)
   {
     double ketqua = 0.0D;



     ketqua = thuocTrongDM + thuocNDM + VTTHTrongDM + VTTHGNDM + CLSTrongDM + CLSNDM;
     return ketqua;
   }

   private static double getTrongDanhMuc(double thuocTrongDM, double VTTHTrongDM, double CLSTrongDM, double PhauThuatTrongDM, double PhongTrongDM)
   {
     double ketqua = 0.0D;



     ketqua = thuocTrongDM + VTTHTrongDM + CLSTrongDM;
     return ketqua;
   }

   private static double getNgoaiDanhMuc(double thuocNDM, double VTTHGNDM, double CLSNDM, double PhauThuatNDM, double PhongNDM)
   {
     double ketqua = 0.0D;



     ketqua = thuocNDM + VTTHGNDM + CLSNDM;
     return ketqua;
   }

   public static void setInforForHsThToan(HsThtoank hsThanhToan)
   {
     double thuocTrongDM = hsThanhToan.getHsthtoankThuoc() == null ? 0.0D : hsThanhToan.getHsthtoankThuoc().doubleValue();
     double thuocNDM = hsThanhToan.getHsthtoankThuocndm() == null ? 0.0D : hsThanhToan.getHsthtoankThuocndm().doubleValue();
     double VTTHTrongDM = hsThanhToan.getHsthtoankVtth() == null ? 0.0D : hsThanhToan.getHsthtoankVtth().doubleValue();
     double VTTHGNDM = hsThanhToan.getHsthtoankVtthndm() == null ? 0.0D : hsThanhToan.getHsthtoankVtthndm().doubleValue();
     double CLSTrongDM = hsThanhToan.getHsthtoankCls() == null ? 0.0D : hsThanhToan.getHsthtoankCls().doubleValue();
     double CLSNDM = hsThanhToan.getHsthtoankClsndm() == null ? 0.0D : hsThanhToan.getHsthtoankClsndm().doubleValue();
     double PhauThuatTrongDM = hsThanhToan.getHsthtoankPhauthuat() == null ? 0.0D : hsThanhToan.getHsthtoankPhauthuat().doubleValue();
     double PhauThuatNDM = hsThanhToan.getHsthtoankPhauthuatndm() == null ? 0.0D : hsThanhToan.getHsthtoankPhauthuatndm().doubleValue();
     double PhongTrongDM = hsThanhToan.getHsthtoankPhong() == null ? 0.0D : hsThanhToan.getHsthtoankPhong().doubleValue();
     double PhongNDM = hsThanhToan.getHsthtoankPhongndm() == null ? 0.0D : hsThanhToan.getHsthtoankPhongndm().doubleValue();
































     hsThanhToan.setHsthtoankDm(Double.valueOf(getTrongDanhMuc(thuocTrongDM, VTTHTrongDM, CLSTrongDM, PhauThuatTrongDM, PhongTrongDM)));






     hsThanhToan.setHsthtoankNdm(Double.valueOf(getNgoaiDanhMuc(thuocNDM, VTTHGNDM, CLSNDM, PhauThuatNDM, PhongNDM)));
   }

   public static void setInforForHsThToan(HsThtoan hsThanhToan)
   {
     double thuocTrongDM = hsThanhToan.getHsthtoanThuoc() == null ? 0.0D : hsThanhToan.getHsthtoanThuoc().doubleValue();
     double thuocNDM = hsThanhToan.getHsthtoanThuocndm() == null ? 0.0D : hsThanhToan.getHsthtoanThuocndm().doubleValue();
     double VTTHTrongDM = hsThanhToan.getHsthtoanVtth() == null ? 0.0D : hsThanhToan.getHsthtoanVtth().doubleValue();
     double VTTHGNDM = hsThanhToan.getHsthtoanVtthndm() == null ? 0.0D : hsThanhToan.getHsthtoanVtthndm().doubleValue();
     double CLSTrongDM = hsThanhToan.getHsthtoanCls() == null ? 0.0D : hsThanhToan.getHsthtoanCls().doubleValue();
     double CLSNDM = hsThanhToan.getHsthtoanClsndm() == null ? 0.0D : hsThanhToan.getHsthtoanClsndm().doubleValue();
     double PhauThuatTrongDM = hsThanhToan.getHsthtoanPhauthuat() == null ? 0.0D : hsThanhToan.getHsthtoanPhauthuat().doubleValue();
     double PhauThuatNDM = hsThanhToan.getHsthtoanPhauthuatndm() == null ? 0.0D : hsThanhToan.getHsthtoanPhauthuatndm().doubleValue();
     double PhongTrongDM = hsThanhToan.getHsthtoanPhong() == null ? 0.0D : hsThanhToan.getHsthtoanPhong().doubleValue();
     double PhongNDM = hsThanhToan.getHsthtoanPhongndm() == null ? 0.0D : hsThanhToan.getHsthtoanPhongndm().doubleValue();
     double congKham = hsThanhToan.getHsthtoanCongkham() == null ? 0.0D : hsThanhToan.getHsthtoanCongkham().doubleValue();























     hsThanhToan.setHsthtoanTongchi(Double.valueOf(getTongChi(thuocTrongDM, thuocNDM, VTTHTrongDM, VTTHGNDM, CLSTrongDM, CLSNDM, PhauThuatTrongDM, PhauThuatNDM, PhongTrongDM, PhongNDM, congKham)));







     hsThanhToan.setHsthtoanDm(Double.valueOf(getTrongDanhMuc(thuocTrongDM, VTTHTrongDM, CLSTrongDM, PhauThuatTrongDM, PhongTrongDM)));







     hsThanhToan.setHsthtoanNdm(Double.valueOf(getNgoaiDanhMuc(thuocNDM, VTTHGNDM, CLSNDM, PhauThuatNDM, PhongNDM)));
   }

   public static String maDoiTuongTreEm()
   {
     return "TE";
   }

   public static String formatNumberForRead(double number)
   {
     NumberFormat nf = NumberFormat.getInstance();
     String temp = nf.format(number);
     String strReturn = "";
     int slen = temp.length();
     for (int i = 0; i < slen; i++)
     {
       if (String.valueOf(temp.charAt(i)).equals(".")) {
         break;
       }
       if (Character.isDigit(temp.charAt(i))) {
         strReturn = strReturn + String.valueOf(temp.charAt(i));
       }
     }
     return strReturn;
   }

   public static String NumberToString(double number)
   {
     String sNumber = formatNumberForRead(number);

     String sReturn = "";

     int iLen = sNumber.length();

     String sNumber1 = "";
     for (int i = iLen - 1; i >= 0; i--) {
       sNumber1 = sNumber1 + sNumber.charAt(i);
     }
     int iRe = 0;
     for (;;)
     {
       String sCut = "";
       if (iLen > 3)
       {
         sCut = sNumber1.substring(iRe * 3, iRe * 3 + 3);
         sReturn = Read(sCut, iRe) + sReturn;
         iRe++;
         iLen -= 3;
       }
       else
       {
         sCut = sNumber1.substring(iRe * 3, iRe * 3 + iLen);
         sReturn = Read(sCut, iRe) + sReturn;
         break;
       }
     }
     if (number == 0.0D) {
       sReturn = IConstantsRes.khong;
     }
     String upper_first = sReturn.substring(0, 1).toUpperCase() + sReturn.substring(1);
     return upper_first;
   }

   public static String Read(String sNumber, int iPo)
   {
     String sReturn = "";

     String[] sPo = { "", IConstantsRes.ngan + " ", IConstantsRes.trieu + " ", IConstantsRes.ty + " ", IConstantsRes.nganty + " " };
     String[] sSo = { IConstantsRes.khong + " ", IConstantsRes.mot + " ", IConstantsRes.hai + " ", IConstantsRes.ba + " ", IConstantsRes.bon + " ", IConstantsRes.nam + " ", IConstantsRes.sau + " ", IConstantsRes.bay + " ", IConstantsRes.tam + " ", IConstantsRes.chin + " " };
     String[] sDonvi = { "", IConstantsRes.muoi + " ", IConstantsRes.tram + " " };

     int iLen = sNumber.length();

     int iRe = 0;
     for (int i = 0; i < iLen; i++)
     {
       String sTemp = "" + sNumber.charAt(i);
       int iTemp = Integer.parseInt(sTemp);

       String sRead = "";
       if (iTemp == 0) {
         switch (iRe)
         {
         case 0:
           break;
         case 1:
           if (Integer.parseInt("" + sNumber.charAt(0)) != 0) {
             sRead = IConstantsRes.le + " ";
           }
           break;
         case 2:
           if ((Integer.parseInt("" + sNumber.charAt(0)) != 0) || (Integer.parseInt("" + sNumber.charAt(1)) != 0)) {
             sRead = IConstantsRes.khong_tam + " ";
           }
           break;
         }
       } else if (iTemp == 1) {
         switch (iRe)
         {
         case 1:
           sRead = IConstantsRes.muoi_10 + " "; break;
         default:
           sRead = IConstantsRes.mot + " " + sDonvi[iRe]; break;
         }
       } else if (iTemp == 5) {
         switch (iRe)
         {
         case 0:
           if (sNumber.length() <= 1) {
             sRead = IConstantsRes.nam + " ";
           } else if (Integer.parseInt("" + sNumber.charAt(1)) != 0) {
             sRead = IConstantsRes.lam + " ";
           } else {
             sRead = IConstantsRes.nam + " ";
           }
           break;
         default:
           sRead = sSo[iTemp] + sDonvi[iRe]; break;
         }
       } else {
         sRead = sSo[iTemp] + sDonvi[iRe];
       }
       sReturn = sRead + sReturn;
       iRe++;
     }
     if (sReturn.length() > 0) {
       sReturn = sReturn + sPo[iPo];
     }
     return sReturn;
   }

   public static String getGioPhutNgay(Date date)
   {
     Calendar cal = Calendar.getInstance();
     SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
     cal.setTime(date);
     String sngay = IConstantsRes.NGAY;

     String sgio = "giờ";
     int iGio = cal.get(11);
     int iPhut = cal.get(12);
     String full = iGio + " " + sgio + " " + iPhut + ", " + sngay + " " + sdf.format(cal.getTime());
     return full;
   }

   public static String formatNumberWithSeprator(Double dNumber)
   {
     NumberFormat nf = NumberFormat.getInstance();
     String sNum = nf.format(dNumber);
     sNum = sNum.replace(".", "_");
     sNum = sNum.replaceAll(",", ".");
     sNum = sNum.replaceAll("_", ",");
     return sNum;
   }

   public static long getDaysBetween(Date d1, Date d2)
   {
     return (d2.getTime() - d1.getTime()) / 86400000L + 1L;
   }

   public static long getSoNgayDieuTri(Date vaovien, Date ravien)
   {
     Date ngayVaovien = removeHourFromDate(vaovien);
     Date ngayRavien = removeHourFromDate(ravien);
     if ((ngayVaovien != null) && (ngayRavien != null))
     {
       if (ngayRavien.equals(ngayVaovien)) {
         return new Long("1").longValue();
       }
       Long soNgayDT = Long.valueOf((ngayRavien.getTime() - ngayVaovien.getTime()) / 86400000L);
       Long ngayCongthem = new Long(IConstantsRes.CONG_VAO_SO_NGAY_DIEU_TRI);
       return soNgayDT.longValue() + ngayCongthem.longValue();
     }
     return new Long("0").longValue();
   }

   public static Double rounDoubleForReport(Double dnum)
   {
     long ly = dnum.longValue();
     double dz1 = dnum.doubleValue() - ly;
     Double dRoundNum = dnum;
     if (dz1 > 0.0D) {
       dRoundNum = new Double(ly + 1L);
     }
     Double dReturn = dRoundNum;
     double mod = dRoundNum.doubleValue() % 10.0D;
     if (mod > 0.0D) {
       dReturn = Double.valueOf(dReturn.doubleValue() - mod + 10.0D);
     }
     return dReturn;
   }

   public static Date removeHourFromDate(Date dDate)
   {
     if (dDate != null)
     {
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       try
       {
         String strDate = df.format(dDate);
         return df.parse(strDate);
       }
       catch (Exception e)
       {
         e.printStackTrace();
         return null;
       }
     }
     return null;
   }

   public static String numberThuocToString(double number, long type)
   {
     try
     {
       if (type == 3L) {
         return NumberToString(number);
       }
       DecimalFormat df = new DecimalFormat("###,###.00");
       return df.format(number);
     }
     catch (Exception e) {}
     return "";
   }

   public static Double roundDoubleNumber(Double dNum, String pattern)
   {
     if (dNum == null) {
       return new Double(0.0D);
     }
     DecimalFormat twoDigit = new DecimalFormat(pattern);
     return new Double(twoDigit.format(dNum.doubleValue()));
   }

   public static Double roundDoubleTwoDecimals(Double dNum)
   {
     if (dNum != null)
     {
       DecimalFormat twoDForm = new DecimalFormat("###.##");
       return Double.valueOf(twoDForm.format(dNum));
     }
     return new Double(0.0D);
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.Utils

 * JD-Core Version:    0.7.0.1

 */