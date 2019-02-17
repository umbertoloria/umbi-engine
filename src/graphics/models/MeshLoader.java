package graphics.models;

import graphics.maths.Vec3;

import java.util.ArrayList;
import java.util.Scanner;

class MeshLoader {

	static Model loadMesh(String fileName) {
		ArrayList<Vec3> positions = new ArrayList<>();
		ArrayList<Vec3> normals = new ArrayList<>();
		ArrayList<OBJIndex> indices = new ArrayList<>();
		Scanner s = new Scanner(MeshLoader.class.getResourceAsStream(fileName));
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String[] t = line.split(" ");
			if (line.startsWith("# ") || line.startsWith("o ") || line.startsWith("s ")) {
				continue;
			}
			if (line.startsWith("v ")) {
				positions.add(new Vec3(Float.parseFloat(t[1]), Float.parseFloat(t[2]), Float.parseFloat(t[3])));
			} else if (line.startsWith("vn ")) {
				normals.add(new Vec3(Float.parseFloat(t[1]), Float.parseFloat(t[2]), Float.parseFloat(t[3])));
			} else if (line.startsWith("f ")) {
				indices.add(parseOBJIndex(t[1]));
				indices.add(parseOBJIndex(t[2]));
				indices.add(parseOBJIndex(t[3]));
			}
		}
		return new Model(positions, normals, indices);
	}

	private static OBJIndex parseOBJIndex(String t) {
		String[] values = t.split("/");
		return new OBJIndex(Integer.parseInt(values[0]) - 1, Integer.parseInt(values[2]) - 1);
	}

	static class OBJIndex {
		private int positionIndex, normalIndex;

		OBJIndex(int positionIndex, int normalIndex) {
			this.positionIndex = positionIndex;
			this.normalIndex = normalIndex;
		}

		int getPositionIndex() {
			return positionIndex;
		}

		int getNormalIndex() {
			return normalIndex;
		}
	}

}

