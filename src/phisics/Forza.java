package phisics;

class Forza {

	private float modulo, xAngle, yAngle;

	Forza(float modulo, float yAngle) {
		this.modulo = modulo;
		this.xAngle = Float.NaN;
		this.yAngle = yAngle;
	}

	void apply(PuntoMateriale pm, float delta) {
		pm.position.move(xAngle, yAngle, modulo / pm.getWeight() * delta);
	}

}
