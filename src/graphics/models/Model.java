package graphics.models;

import engine.buffers.utils.FileUtils;
import graphics.maths.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {

	public static Model cube, sphere, cone, cylinder, monkey;

	public static void loadAll() {
		cube = new Model("cube.obj");
		sphere = new Model("sphere.obj");
		cone = new Model("cone.obj");
		cylinder = new Model("cylinder.obj");
		monkey = new Model("monkey.obj");
	}

	private float[] verticesArray;
	private int[] indicesArray;
	private float[] normalsArray;

	private Model(String file) {
		// TODO: Textures e Normals
		Scanner in = new Scanner(FileUtils.loadAsString(Model.class, file));

		ArrayList<Vec3> vertices = new ArrayList<>();
		//ArrayList<Vec3> cosmetics = new ArrayList<>();
		ArrayList<Vec3> normals = new ArrayList<>();
		ArrayList<Integer> indices = new ArrayList<>();

		String line;
		String[] parts;
		String[] v1, v2, v3;
		while (in.hasNextLine()) {
			line = in.nextLine();
			parts = line.split(" ");
			if (line.startsWith("v ")) {
				float x = Float.parseFloat(parts[1]);
				float y = Float.parseFloat(parts[2]);
				float z = Float.parseFloat(parts[3]);
				vertices.add(new Vec3(x, y, z));
			}/* else if (line.startsWith("vt ")) {
				cosmetics.add(Float.parseFloat(parts[1]));
				cosmetics.add(Float.parseFloat(parts[2]));
			}*/ else if (line.startsWith("vn ")) {
				float x = Float.parseFloat(parts[1]);
				float y = Float.parseFloat(parts[2]);
				float z = Float.parseFloat(parts[3]);
				normals.add(new Vec3(x, y, z));
			} else if (line.startsWith("f ")) {
				if (normalsArray == null) {
					normalsArray = new float[vertices.size() * 3];
				}
				v1 = parts[1].split("/");
				v2 = parts[2].split("/");
				v3 = parts[3].split("/");
				processVertex(v1, indices, normals, normalsArray);
				processVertex(v2, indices, normals, normalsArray);
				processVertex(v3, indices, normals, normalsArray);
			}
		}

		verticesArray = new float[vertices.size() * 3];
		indicesArray = new int[indices.size()];

		int vertexPointer = 0;
		for (Vec3 vertex : vertices) {
			verticesArray[vertexPointer++] = vertex.x;
			verticesArray[vertexPointer++] = vertex.y;
			verticesArray[vertexPointer++] = vertex.z;
		}

		for (int i = 0; i < indicesArray.length; i++) {
			indicesArray[i] = indices.get(i);
		}
	}

	private void processVertex(String[] vertexData, List<Integer> indices, List<Vec3> normals,
	                           float[] normalsArray) {
		int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indices.add(currentVertexPointer);

		Vec3 currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalsArray[currentVertexPointer * 3] = currentNorm.x;
		normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
		normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
	}

	public float[] getVertices() {
		return verticesArray;
	}

	public int[] getIndices() {
		return indicesArray;
	}

	public float[] getNormals() {
		return normalsArray;
	}

}
