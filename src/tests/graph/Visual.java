package tests.graph;

import engine.Mesh;
import engine.Renderer;
import engine.events.Event;
import engine.layer.Layer;
import engine.shaders.Shader;
import graphics.Color;
import graphics.camera.Camera;
import graphics.camera.FlatCamera;
import graphics.maths.Mat;
import graphics.maths.Vec3;
import tests.Switch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Visual extends Layer {

	private Camera camera;
	private Shader shader;
	private Grafo<Vec3, Float> g = new Grafo<>();
	private Color white = new Color(1, 1, 1);
	private Color gray = new Color(.5f, .5f, .5f);
	private Color orange = new Color(.8f, .4f, .3f);
	private Color red = new Color(1, .2f, 0);

	public void onInit() {
		float width = 2f;
		float height = width / 1.777f;
		float left = -width / 2;
		float right = width / 2;
		float bottom = -height / 2;
		float top = height / 2;
		camera = new FlatCamera(left, right, bottom, top, 0, 100);
		shader = new Shader(
				"" +
						"#version 330 core                                                        \n" +
						"layout (location = 0) in vec3 in_position;                               \n" +
						"                                                                         \n" +
						"uniform mat4 model;                                                      \n" +
						"uniform mat4 projectionView;                                             \n" +
						"                                                                         \n" +
						"void main() {                                                            \n" +
						"    gl_Position = projectionView * model * vec4(in_position, 1.0);       \n" +
						"}                                                                        \n",
				"" +
						"#version 330 core                                                        \n" +
						"                                                                         \n" +
						"layout (location = 0) out vec4 out_color;                                \n" +
						"                                                                         \n" +
						"uniform vec4 color;                                                      \n" +
						"                                                                         \n" +
						"void main() {                                                            \n" +
						"    out_color = color;                                                   \n" +
						"}                                                                        \n");
		Vec3[] vects = {
				new Vec3(-.7f, 0, 0),
				new Vec3(-.9f, .5f, 0),
				new Vec3(-.8f, -.4f, 0),
				new Vec3(0, .4f, 0),
				new Vec3(-.2f, -.4f, 0),
				new Vec3(.1f, .2f, 0)
		};
		for (Vec3 vect : vects) {
			g.add(vect);
		}
		Vec3[][] coppie = {
				{vects[0], vects[1]},
				{vects[1], vects[2]},
				{vects[1], vects[3]},
				{vects[2], vects[4]},
				{vects[4], vects[5]},
				{vects[3], vects[5]}
		};
		for (Vec3[] vec3s : coppie) {
			Vec3 u = vec3s[0];
			Vec3 v = vec3s[1];
			g.link(u, v, (float) Math.sqrt(Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2)));
		}
		heap.insert(0f, vects[0]);
		for (int i = 1; i < vects.length; i++) {
			heap.insert(Float.MAX_VALUE, vects[i]);
		}
	}

	private Heap<Float, Vec3> heap = new Heap<>(1);
	private Set<Vec3> nodiVisti = new HashSet<>();
	private Set<Arco<Vec3, Float>> archiVisti = new HashSet<>();
	private HashMap<Vec3, Arco<Vec3, Float>> back = new HashMap<>();
	private Switch updater = new Switch(1500);

	public void onUpdate(float delta) {
		if (!heap.empty() && updater.timeout(delta)) {
			float distance = heap.priority(heap.peek());
			Vec3 u = heap.extract();
			nodiVisti.add(u);
			if (back.containsKey(u)) {
				archiVisti.add(back.get(u));
			}
			for (Arco<Vec3, Float> e : g.adjacents(u)) {
				if (!nodiVisti.contains(e.to)) {
					float newPriority = distance + e.weight;
					if (heap.priority(e.to) > newPriority) {
						heap.remove(e.to);
						heap.insert(newPriority, e.to);
						back.put(e.to, e);
					}
				}
			}
		}
	}

	public void onEvent(Event event) {
	}

	public void onRender(Renderer renderer) {
		Mat projectionViewMatrix = camera.getProjectionViewMatrix();
		shader.enable();
		shader.setUniformMat4f("projectionView", projectionViewMatrix);
		for (Arco<Vec3, Float> e : g.archi()) {
			shader.setUniformMat4f("model", makeModelMatrix(e));
			boolean found = false;
			for (Arco<Vec3, Float> arco : archiVisti) {
				if (e.equals(arco) || e.reverse().equals(arco)) {
					found = true;
					break;
				}
			}
			shader.setUniformColor("color", found ? orange : gray);
			Mesh.quad.render();
		}
		for (Vec3 x : g.vertici()) {
			shader.setUniformMat4f("model", makeModelMatrix(x));
			shader.setUniformColor("color", nodiVisti.contains(x) ? red : white);
			Mesh.sphere.render();
		}
	}

	private Mat makeModelMatrix(Arco<Vec3, Float> arco) {
		float x1 = arco.from.x;
		float x2 = arco.to.x;
		float y1 = arco.from.y;
		float y2 = arco.to.y;
		float cx = (x1 + x2) / 2;
		float cy = (y1 + y2) / 2;
		float r = (float) Math.toDegrees(Math.atan2(y2 - y1, x2 - x1)) % 360;
		float sx = arco.weight;
		float sy = .002f;
		return Mat.model(cx, cy, -1, 0, 0, r, sx, sy, 0);
	}

	private Mat makeModelMatrix(Vec3 p) {
		return Mat.model(p.x, p.y, p.z, 0, 0, 0, .05f, .05f, .05f);
	}

}
