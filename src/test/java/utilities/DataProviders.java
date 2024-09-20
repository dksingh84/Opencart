package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("loginData");
		int colcount = xl.getCellCount("loginData", 1);

		String data[][] = new String[rownum][colcount];
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				data[i - 1][j] = xl.getCellData("loginData", i, j);
			}
		}
		return data;
	}
}
