package tiparire.model;

public class ConnectionStrings {

	private static ConnectionStrings instance = new ConnectionStrings();

	private String myUrl;
	private String myNamespace;
	private String myDatabase;

	private ConnectionStrings() {
		myUrl = "http://10.1.0.58/AndroidWebServices/TiparireService.asmx";
		myNamespace = "http://tiparire.org/";
		
		
	//	myUrl = "http://10.1.0.58/AndroidWebServices/TiparireServiceTest.asmx";
	//	myNamespace = "http://tiparireTest.org/";		
		

	}

	public static ConnectionStrings getInstance() {
		return instance;
	}

	public String getUrl() {
		return this.myUrl;
	}

	public String getNamespace() {
		return this.myNamespace;
	}

	public String getDatabaseName() {
		return this.myDatabase;
	}

}
