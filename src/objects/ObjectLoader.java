package objects;

import buffers.VAO;
import shaders.ShadersManager;
import utils.Color;

import java.util.ArrayList;
import java.util.Scanner;

public class ObjectLoader {

	public static Object get(String objName) {
		Object res = new Object();
		Scanner sc = new Scanner(ObjectLoader.class.getResourceAsStream(objName + ".txt"));
		// size
		String[] pos = sc.nextLine().split(",");
		res.setSize(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]));
		// color
		String line = sc.nextLine();
		try {
			res.setColor(new Color(line));
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Problem");
		}
		// shader
		res.setShader(ShadersManager.get(sc.nextLine()));
		// vertex
		if (!sc.nextLine().equals("vertices")) {
			throw new RuntimeException("Problem");
		}
		res.setVao(new VAO(getVertices(sc), getIndices(sc)));
		return res;
	}

	private static float[] getVertices(Scanner sc) {
		ArrayList<Float> buffer = new ArrayList<>();
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();
			if (line.equals("faces")) {
				break;
			}

			String[] vertex = take3Tokens(line);

			buffer.add(Float.parseFloat(vertex[0]));
			buffer.add(Float.parseFloat(vertex[1]));
			buffer.add(Float.parseFloat(vertex[2]));
		}
		Float[] tmpData = buffer.toArray(new Float[0]);
		float[] data = new float[tmpData.length];
		for (int i = 0; i < tmpData.length; i++) {
			data[i] = tmpData[i];
		}
		return data;
	}

	private static int[] getIndices(Scanner sc) {
		ArrayList<Integer> buffer = new ArrayList<>();
		String line;
		while (sc.hasNextLine()) {
			line = sc.nextLine().trim();

			String[] vertex = take3Tokens(line);

			buffer.add(Integer.parseInt(vertex[0]));
			buffer.add(Integer.parseInt(vertex[1]));
			buffer.add(Integer.parseInt(vertex[2]));
		}
		Integer[] tmpData = buffer.toArray(new Integer[0]);
		int[] data = new int[tmpData.length];
		for (int i = 0; i < tmpData.length; i++) {
			data[i] = tmpData[i];
		}
		return data;
	}

	private static String takeUntilSpace(String str) {
		int i;
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				break;
			}
		}
		return str.substring(0, i);
	}

	private static String[] take3Tokens(String line) {
		String[] res = new String[3];
		res[0] = takeUntilSpace(line);
		line = line.substring(res[0].length() + 1).trim();
		res[1] = takeUntilSpace(line);
		line = line.substring(res[1].length() + 1).trim();
		res[2] = line;
		return res;
	}

}
