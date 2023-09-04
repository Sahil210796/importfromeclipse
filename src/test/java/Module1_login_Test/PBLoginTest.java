package Module1_login_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import LibraryFiles.BaseClass;
import LibraryFiles.UtiltyClass;
import Module1_Login.MyAccPage;
import Module1_Login.MyProfilePage;
import Module1_Login.PBHomePage;
import Module1_Login.PBLoginPage;
import Module1_Login.PBMobNumPage;
import Module1_Login.PBPwdPage;



public class PBLoginTest extends BaseClass
{
	PBLoginPage login;
	PBMobNumPage mobNum;
	PBPwdPage pwd;
	PBHomePage home;
	MyAccPage myAcc;
	MyProfilePage profile;	
	int TCID;
		
	@Parameters("browserName")
	@BeforeClass
	public void openBrowser(String browserName) throws EncryptedDocumentException, IOException 
	{				
		initializeBrowser(browserName);
		
		login=new PBLoginPage(driver); 
		mobNum=new PBMobNumPage(driver);
		pwd=new PBPwdPage(driver);
	    home=new PBHomePage(driver);
		myAcc=new MyAccPage(driver);
		profile=new MyProfilePage(driver);	
	}
	
	@BeforeMethod
	public void loginToApp() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		login.clickPBLoginPageSignInBtn();
		Thread.sleep(2000);
		mobNum.inpPBMobNumPageMobnum(UtiltyClass.getPFData("MobNum"));
		mobNum.clickPBMobNumPageLoginWithPwd();
		Thread.sleep(2000);
		pwd.inpPBPwdPagePwd(UtiltyClass.getPFData("PWD"));
		pwd.clickPBPwdPageSignInBtn();
		Thread.sleep(2000);
	}
	
	@Test
	public void VerifyFullName() throws InterruptedException, EncryptedDocumentException, IOException   
	{
		TCID=101;
		home.openDDOptionPBHomePageMyAcc();
		Thread.sleep(2000);
		myAcc.clickMyAccPageMyProfile();
		profile.switchToChildWindow();
		
		String actFullName=profile.getMyProfilePageFullName();
		String expFullName=UtiltyClass.getTestData(0, 2);
		
		Assert.assertEquals(actFullName, expFullName, "Failed: both results are diff");
		Thread.sleep(2000);
	}
	

	
	@AfterMethod
	public void logoutFromApp(ITestResult s1) throws IOException 
	{
		if(s1.getStatus()==ITestResult.FAILURE)
		{
			UtiltyClass.captureSS(driver, TCID);
		}		
	}
	
	
	@AfterClass
	public void closeBrowser() 
	{
		driver.quit();
	}
}
