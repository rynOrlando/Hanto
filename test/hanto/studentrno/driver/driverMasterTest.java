package hanto.studentrno.driver;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import hanto.studentrnorlando.Driver;

public class driverMasterTest {

	@Test // 1
	public void intialazationDriverWorks()
	{
		Driver driver = new Driver();
		assertNotNull(driver);
	}
	
	@Test //2
	public void mainDriverMethodNoErrors()
	{
		String [] args = {"NO ARGs"};
		Driver.main(args);
	}
}
