package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetHtmlContent {

	public String getHtmlContent(String filePath) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				contentBuilder.append(line);
			}
		}

		return contentBuilder.toString();
	}
}
