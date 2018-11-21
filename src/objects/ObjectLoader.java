package objects;

import buffers.VAO;
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
		// vertex
		ArrayList<Float> buffer = new ArrayList<>();
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			pos = line.split(",");
			if (pos.length != 3) {
				System.out.println(line);
				System.out.println(pos.length);
				throw new RuntimeException("Problem");
			}
			buffer.add(Float.parseFloat(pos[0]));
			buffer.add(Float.parseFloat(pos[1]));
			buffer.add(Float.parseFloat(pos[2]));
		}
		Float[] tmpData = buffer.toArray(new Float[0]);
		float[] data = new float[tmpData.length];
		for (int i = 0; i < tmpData.length; i++) {
			data[i] = tmpData[i];
		}
		VAO vao = new VAO();
		vao.add(data);
		res.setVao(vao);
		return res;
	}

}
