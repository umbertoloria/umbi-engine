package graphics.models;

import graphics.maths.Vec2;
import graphics.maths.Vec3;

import java.util.ArrayList;

public class Model {

	public static Model cube, sphere, cone, cylinder, monkey, quad;

	public static void loadAll() {
		cube = ModelLoader.loadModel("cube.obj");
		sphere = ModelLoader.loadModel("sphere.obj");
		cone = ModelLoader.loadModel("cone.obj");
		cylinder = ModelLoader.loadModel("cylinder.obj");
		monkey = ModelLoader.loadModel("monkey.obj");
		quad = ModelLoader.loadModel("quad.obj");
	}

	private boolean hasNormals, hasTextureCoordinates;
	private Vertex[] vertices;
	private int[] indices;

	Model(Vec3[] positions, Vec3[] normals, ModelFace[] objIndices) {
		hasNormals = true;
		hasTextureCoordinates = false;
		indexThisList(positions, null, normals, objIndices);
	}

	Model(Vec3[] positions, Vec2[] textureCoordinates, ModelFace[] objIndices) {
		hasNormals = false;
		hasTextureCoordinates = true;
		indexThisList(positions, textureCoordinates, null, objIndices);
	}

	Model(Vec3[] positions, ModelFace[] objIndices) {
		hasNormals = false;
		hasTextureCoordinates = false;
		indexThisList(positions, null, null, objIndices);
	}

	private void indexThisList(Vec3[] positions, Vec2[] textureCoordinates, Vec3[] normals, ModelFace[] objIndices) {
		ArrayList<Vertex> verticesList = new ArrayList<>();
		ArrayList<Integer> indicesList = new ArrayList<>();
		int cursore = 0;
		for (ModelFace modelFace : objIndices) {
			Vertex app = makeVertex(positions, textureCoordinates, normals, modelFace);
			int firstOccurrence = -1;
			for (int j = 0; j < verticesList.size(); j++) {
				if (verticesList.get(j).equals(app)) {
					firstOccurrence = j;
				}
			}
			if (firstOccurrence >= 0) {
				indicesList.add(firstOccurrence);
			} else {
				verticesList.add(app);
				indicesList.add(cursore++);
			}
		}
		vertices = new Vertex[verticesList.size()];
		verticesList.toArray(vertices);
		indices = new int[indicesList.size()];
		for (int i = 0; i < indicesList.size(); i++) {
			indices[i] = indicesList.get(i);
		}
	}

	private Vertex makeVertex(Vec3[] positions, Vec2[] textureCoordinates, Vec3[] normals, ModelFace modelFace) {
		if (textureCoordinates != null && normals != null) {
			// Il vertice è dotato di textureCoordinate e Normal
			return new Vertex(positions[modelFace.getPositionIndex()],
					textureCoordinates[modelFace.getTextureCoordinateIndex()], normals[modelFace.getNormalIndex()]);
		} else if (normals != null) {
			// Il vertice è dotato solo di Normal
			return new Vertex(positions[modelFace.getPositionIndex()], normals[modelFace.getNormalIndex()]);
		} else if (textureCoordinates != null) {
			// Il vertice è dotato solo di textureCoordinate
			return new Vertex(positions[modelFace.getPositionIndex()],
					textureCoordinates[modelFace.getTextureCoordinateIndex()]);
		}
		// Il vertice ha solo una posizione
		return new Vertex(positions[modelFace.getPositionIndex()]);
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

	public float[] getTextureCoordinatesArray() {
		float[] normals = new float[vertices.length * 2];
		int i = 0;
		for (Vertex vertex : vertices) {
			normals[i++] = vertex.getTextureCoordinate().x;
			normals[i++] = vertex.getTextureCoordinate().y;
		}
		return normals;
	}

	public int[] getIndicesArray() {
		return indices;
	}

	public boolean hasNormals() {
		return hasNormals;
	}

	public boolean hasTextureCoordinates() {
		return hasTextureCoordinates;
	}

}
