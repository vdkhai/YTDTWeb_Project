 import java.applet.Applet;
 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import java.util.Enumeration;
 import java.util.StringTokenizer;
 import javax.comm.CommDriver;
 import javax.comm.CommPort;
 import javax.comm.CommPortIdentifier;
 import javax.comm.PortInUseException;
 import javax.comm.SerialPort;
 import javax.comm.UnsupportedCommOperationException;

 public class AappletCom6
   extends Applet
   implements Runnable
 {
   Thread commThread;
   boolean running = true;
   protected CommPortIdentifier selectedPortIdentifier;
   public static final int TIMEOUTSECONDS = 30;
   public static final String COM_NAME = "COM6";
   public static final int BAUD = 9600;
   CommPort thePort;
   OutputStream os = null;
   protected BufferedReader is;

   public void askPO() {}

   public void pingpong(String str, String deviceNum)
   {
     beginOperation();
     System.out.println("call pingpong()");
     System.out.println("str:" + str);
     if (this.os == null)
     {
       System.out.println("os:" + this.os);
       return;
     }
     if (str == null) {
       return;
     }
     try
     {
       String fistStr = "[L" + deviceNum + "F";
       System.out.println("firstStr:" + fistStr);
       this.os.write(fistStr.getBytes());


       StringTokenizer stk = new StringTokenizer(str, ",");
       while (stk.hasMoreElements())
       {
         String s = (String)stk.nextElement();
         if ((s != null) && (!s.equals(""))) {
           try
           {
             Integer iS = Integer.valueOf(Integer.parseInt(s));
             this.os.write(iS.intValue());
           }
           catch (Exception ex)
           {
             char c = s.charAt(0);
             if (((c >= 'a') && (c <= '}')) || ((c >= '7') && (c <= 'Z')))
             {
               int x = c - ' ';
               this.os.write(x);
             }
           }
         }
       }
       this.os.write(255);
       this.os.flush();

       Thread.sleep(2000L);
     }
     catch (Exception e)
     {
       e.printStackTrace();
       endOperation();
     }
     endOperation();
   }

   public void outputToLed(String str, String deviceNum)
   {
     beginOperation();

     System.out.println("call outputToLed()");
     System.out.println("str:" + str);
     if (this.os == null)
     {
       System.out.println("os:" + this.os);
       return;
     }
     if (str == null) {
       return;
     }
     try
     {
       String fistStr = "[L" + deviceNum + "T";
       System.out.println("firstStr2:" + fistStr);
       this.os.write(fistStr.getBytes());


       StringTokenizer stk = new StringTokenizer(str, ",");
       while (stk.hasMoreElements())
       {
         String s = (String)stk.nextElement();
         if ((s != null) && (!s.equals(""))) {
           try
           {
             Integer iS = Integer.valueOf(Integer.parseInt(s));
             this.os.write(iS.intValue());
           }
           catch (Exception ex)
           {
             char c = s.charAt(0);
             if (((c >= 'a') && (c <= '}')) || ((c >= '7') && (c <= 'Z')))
             {
               int x = c - ' ';
               this.os.write(x);
             }
           }
         }
       }
       this.os.write(255);
       this.os.flush();
     }
     catch (Exception e)
     {
       e.printStackTrace();
       endOperation();
     }
     endOperation();
   }

   private void beginOperation() {}

   private void endOperation() {}

   public void init()
   {
     System.out.println("thanh - init - myInitialize()");
     myInitialize();
   }

   public void myInitialize()
   {
     String drivername = "com.sun.comm.Win32Driver";
     try
     {
       CommDriver driver = (CommDriver)Class.forName(drivername).newInstance();

       driver.initialize();
     }
     catch (Exception e)
     {
       System.out.println(e.getMessage());
     }
     System.out.println("call init() ");
     Enumeration pList = CommPortIdentifier.getPortIdentifiers();
     while (pList.hasMoreElements())
     {
       CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
       if (cpi.getPortType() == 1) {
         this.selectedPortIdentifier = cpi;
       }
       System.out.println("cpi.getName(): " + cpi.getName());
       if ((cpi.getName() != null) && (cpi.getName().equals("COM6"))) {
         break;
       }
     }
     System.out.println("selectedPortIdentifier: " + this.selectedPortIdentifier);
     if (this.selectedPortIdentifier == null) {
       return;
     }
     try
     {
       this.thePort = this.selectedPortIdentifier.open("DarwinSys DataComm", 3000);



       SerialPort myPort = (SerialPort)this.thePort;



       myPort.setSerialPortParams(9600, 8, 1, 0);


       this.os = myPort.getOutputStream();
       this.is = new BufferedReader(new InputStreamReader(myPort.getInputStream()));


       System.out.println("os: " + this.os);
       System.out.println("is: " + this.is);
     }
     catch (PortInUseException e)
     {
       e.printStackTrace();
       return;
     }
     catch (UnsupportedCommOperationException e)
     {
       e.printStackTrace();
       return;
     }
     catch (IOException e)
     {
       e.printStackTrace();
       return;
     }
   }

   public void destroy()
   {
     System.out.println("Finally, clean up.");
     if (this.is != null) {
       try
       {
         this.is.close();
       }
       catch (IOException e)
       {
         e.printStackTrace();
       }
     }
     if (this.os != null) {
       try
       {
         this.os.close();
       }
       catch (IOException e)
       {
         e.printStackTrace();
       }
     }
     SerialPort myPort = (SerialPort)this.thePort;
     if (myPort != null) {
       myPort.close();
     }
   }

   public void run()
   {
     while (this.running) {}
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     AappletCom6

 * JD-Core Version:    0.7.0.1

 */