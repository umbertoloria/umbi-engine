package graphics.models;

import graphics.maths.Vec3;

import java.util.ArrayList;
import java.util.List;

public class Model {

	public static Model cube, sphere, cone, cylinder, monkey;

	public static void loadAll() {
		cube = MeshLoader.loadMesh("cube.obj");
		sphere = MeshLoader.loadMesh("sphere.obj");
		cone = MeshLoader.loadMesh("cone.obj");
		cylinder = MeshLoader.loadMesh("cylinder.obj");
		monkey = MeshLoader.loadMesh("monkey.obj");
	}

	private Vertex[] vertices;
	private int[] indices;

	Model(List<Vec3> positions, List<Vec3> normals, List<MeshLoader.OBJIndex> objIndices) {
		ArrayList<Vertex> verts = new ArrayList<>();
		ArrayList<Integer> inds = new ArrayList<>();
		int cursore = 0;
		for (MeshLoader.OBJIndex objIndex : objIndices) {
			Vertex app = new Vertex(
					positions.get(objIndex.getPositionIndex()),
					normals.get(objIndex.getNormalIndex())
			);
			int firstOccurrence = -1;
			for (int j = 0; j < verts.size(); j++) {
				if (verts.get(j).equals(app)) {
					firstOccurrence = j;
				}
			}
			if (firstOccurrence >= 0) {
				inds.add(firstOccurrence);
			} else {
				verts.add(app);
				inds.add(cursore++);
			}
		}
		vertices = new Vertex[verts.size()];
		verts.toArray(vertices);
		indices = new int[inds.size()];
		for (int i = 0; i < inds.size(); i++) {
			indices[i] = inds.get(i);
		}
	}

	public int getIndicesCount() {
		return indices.length;
	}

	public float[] getPositionsArray() {
		float[] positions = new float[vertices.length * 3];
		int i = 0;
		for (Vertex vertex : vertices) {
			positions[i++] = vertex.getPosition().x;
			positions[i++] = vertex.getPosition().y;
			positions[i++] = vertex.getPosition().z;
		}
		return positions;
	}

	public float[] getNormalsArray() {
		float[] normals = new float[vertices.length * 3];
		int i = 0;
		for (Vertex vertex : vertices) {
			normals[i++] = vertex.getNormal().x;
			normals[i++] = vertex.getNormal().y;
			normals[i++] = vertex.getNormal().z;
		}
		return normals;
	}

	public int[] getIndicesArray() {
		return indices;
	}

	public float[] getLinearPositionsArray() {
		float[] positions = new float[indices.length * 3];
		int i = 0;
		for (int index : indices) {
			positions[i++] = vertices[index].getPosition().x;
			positions[i++] = vertices[index].getPosition().y;
			positions[i++] = vertices[index].getPosition().z;
		}
		return positions;
	}

	public float[] getLinearNormalsArray() {
		float[] normals = new float[indices.length * 3];
		int i = 0;
		for (int index : indices) {
			normals[i++] = vertices[index].getNormal().x;
			normals[i++] = vertices[index].getNormal().y;
			normals[i++] = vertices[index].getNormal().z;
		}
		return normals;
	}

}
