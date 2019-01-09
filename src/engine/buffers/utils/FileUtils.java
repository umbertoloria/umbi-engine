package engine.buffers.utils;

import java.util.Scanner;

public class FileUtils {

	public static String loadAsString(Class c, String file) {
		StringBuilder result = new StringBuilder();
		Scanner sc = new Scanner(c.getResourceAsStream(file));
		String buffer;
		while (sc.hasNextLine()) {
			buffer = sc.nextLine();
			result.append(buffer);
			result.append('\n');
		}
		sc.close();
		return result.toString();
	}

}
