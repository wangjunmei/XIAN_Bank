import com.sun.jna.Library;
import com.sun.jna.Native;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Test_xa {
    @Test
    public  void Test_xianbank() {
        //System.out.println(System.getProperty("java.library.path"));
        System.out.println("开始");

        //DD.INSTANCE.DD_mov(500, 500);   //�����ƶ�
        //DD.INSTANCE.DD_movR(100, 100); //����ƶ�
        //DD.INSTANCE.DD_btn(4);DD.INSTANCE.DD_btn(8); //����Ҽ�
        System.getProperties().setProperty("webdriver.chrome.driver", "drivers/chromedriver_windows.exe");
        //开启新WebDriver进程
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.baidu.com/");


        File ieFile = new File("drivers/IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", ieFile.getAbsolutePath());
        DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
        ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "https://www.baidu.com/");
         webDriver = new InternetExplorerDriver(ieCaps);

        //配置ChromeDiver
        //System.getProperties().setProperty("webdriver.chrome.driver", "drivers/chromedriver_windows.exe");
        //开启新WebDriver进程
        //WebDriver webDriver = new ChromeDriver();
        //设定网址
        webDriver.get("https://ebank.xacbank.com/XAIBank/page/setpassword/setpassword.html?t=1515482820749");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println(webDriver.findElement(By.id("password_pge")).getLocation());
        //webDriver.findElement(By.id("password_pge")).click();
        //DD.INSTANCE.DD_key(601, 1);
       // DD.INSTANCE.DD_key(601, 2); //����win
        DD.INSTANCE.DD_mov(705, 531);
        DD.INSTANCE.DD_btn(1);

        DD.INSTANCE.DD_str("123456"); //�ַ���
        webDriver.quit();
    }

    public interface DD extends Library {
        DD INSTANCE = (DD)Native.loadLibrary("DD81200x64.64", DD.class);
        //64λJAVA����*64.dll, 32λ����*12345632.dll ����ϵͳ����λ���޹ء���
        public int DD_mov(int x, int y);
        public int DD_movR(int dx, int dy);
        public int DD_btn(int btn);
        public int DD_whl(int whl);
        public int DD_key(int ddcode, int flag);
        public int DD_str(String s);
    }
}
