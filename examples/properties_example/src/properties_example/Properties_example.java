package properties_example;

import java.io.IOException;

public class Properties_example {

	public static void main(String[] args) {
		ReaderPropertiesFile properties = new ReaderPropertiesFile();
		try {
			properties.getPropValues();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
