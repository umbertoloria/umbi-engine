package tests.graph;

import java.util.Objects;

public class Arco<T, W extends Comparable<W>> {

	public final T from, to;
	public final W weight;

	public Arco(T from, T to, W weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Arco<T, W> reverse () {
		return new Arco<>(to, from, weight);
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Arco<?, ?> arco = (Arco<?, ?>) o;
		return Objects.equals(from, arco.from) &&
				Objects.equals(to, arco.to) &&
				Objects.equals(weight, arco.weight);
	}

	public String toString() {
		return "[" + from + ", " + to + ", w=" + weight + "]";
	}

}
