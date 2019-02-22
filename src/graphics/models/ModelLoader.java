package graphics.models;

import graphics.maths.Vec2;
import graphics.maths.Vec3;

import java.util.ArrayList;
import java.util.Scanner;

class ModelLoader {

	static Model loadModel(String fileName) {
		boolean hasNormals = false;
		boolean hasTextureCoordinates = false;

		ArrayList<Vec3> positions = new ArrayList<>();
		ArrayList<Vec3> normals = new ArrayList<>();
		ArrayList<Vec2> textureCoordinates = new ArrayList<>();
		ArrayList<ModelFace> indices = new ArrayList<>();

		Scanner s = new Scanner(ModelLoader.class.getResourceAsStream(fileName));
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] t = line.split(" ");
			if (line.startsWith("# ") || line.startsWith("o ") || line.startsWith("s ")) {
				continue;
			}
			if (line.startsWith("v ")) {
				positions.add(new Vec3(Float.parseFloat(t[1]), Float.parseFloat(t[2]), Float.parseFloat(t[3])));
			} else if (line.startsWith("vn ")) {
				if (!hasNormals) {
					hasNormals = true;
				}
				normals.add(new Vec3(Float.parseFloat(t[1]), Float.parseFloat(t[2]), Float.parseFloat(t[3])));
			} else if (line.startsWith("vt ")) {
				if (!hasTextureCoordinates) {
					hasTextureCoordinates = true;
				}
				textureCoordinates.add(new Vec2(Float.parseFloat(t[1]), Float.parseFloat(t[2])));
			} else if (line.startsWith("f ")) {
				indices.add(parseOBJIndex(t[1], hasNormals, hasTextureCoordinates));
				indices.add(parseOBJIndex(t[2], hasNormals, hasTextureCoordinates));
				indices.add(parseOBJIndex(t[3], hasNormals, hasTextureCoordinates));
			}
		}
		Vec3[] positionsArray = new Vec3[positions.size()];
		positions.toArray(positionsArray);

		Vec2[] textureCoordinatesArray = new Vec2[textureCoordinates.size()];
		textureCoordinates.toArray(textureCoordinatesArray);

		Vec3[] normalsArray = new Vec3[normals.size()];
		normals.toArray(normalsArray);

		ModelFace[] indicesArray = new ModelFace[indices.size()];
		indices.toArray(indicesArray);

		if (hasNormals) {
			return new Model(positionsArray, normalsArray, indicesArray);
		}
		if (hasTextureCoordinates) {
			return new Model(positionsArray, textureCoordinatesArray, indicesArray);
		}
		return new Model(positionsArray, indicesArray);
	}

	private static ModelFace parseOBJIndex(String t, boolean hasNormals, boolean hasTextureCoordinates) {
		String[] values = t.split("/");
		int positionIndex = Integer.parseInt(values[0]) - 1;
		int textureCoordinateIndex = hasTextureCoordinates ? Integer.parseInt(values[1]) - 1 : -1;
		int normalIndex = hasNormals ? Integer.parseInt(values[2]) - 1 : -1;
		return new ModelFace(positionIndex, textureCoordinateIndex, normalIndex);
	}

}

