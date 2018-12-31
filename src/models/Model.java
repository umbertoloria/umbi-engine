package models;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class Model {

	public static Model cube, sphere, cone;

	public static void loadAll() {
		cube = new Model("cube.obj");
		sphere = new Model("sphere.obj");
		cone = new Model("cone.obj");
	}

	private float[] vertices;
	private int[] indices;

	private Model(String file) {
		Scanner in = new Scanner(FileUtils.loadAsString(Model.class, file));
		ArrayList<Float> vertices = new ArrayList<>();
		//ArrayList<Vec3> textures = new ArrayList<>();
		//ArrayList<Vec3> normals = new ArrayList<>();
		ArrayList<Integer> indices = new ArrayList<>();
		String line;
		String[] parts;
		String[] v1, v2, v3;
		while (in.hasNextLine()) {
			line = in.nextLine();
			parts = line.split(" ");
			if (line.startsWith("v ")) {
				vertices.add(Float.parseFloat(parts[1]));
				vertices.add(Float.parseFloat(parts[2]));
				vertices.add(Float.parseFloat(parts[3]));
			}/* else if (line.startsWith("vt ")) {
				textures.add(Float.parseFloat(parts[1]));
				textures.add(Float.parseFloat(parts[2]));
			} else if (line.startsWith("vn ")) {
				normals.add(Float.parseFloat(parts[1]));
				normals.add(Float.parseFloat(parts[2]));
				normals.add(Float.parseFloat(parts[3]));
			}*/ else if (line.startsWith("f ")) {
				v1 = parts[1].split("/");
				indices.add(Integer.parseInt(v1[0]) - 1);
				v2 = parts[2].split("/");
				indices.add(Integer.parseInt(v2[0]) - 1);
				v3 = parts[3].split("/");
				indices.add(Integer.parseInt(v3[0]) - 1);
			}
		}
		this.vertices = new float[vertices.size()];
		for (int i = 0; i < vertices.size(); i++) {
			this.vertices[i] = vertices.get(i);
		}
		this.indices = new int[indices.size()];
		for (int i = 0; i < indices.size(); i++) {
			this.indices[i] = indices.get(i);
		}
	}

	public float[] getVertices() {
		return vertices;
	}

	public int[] getIndices() {
		return indices;
	}
}
