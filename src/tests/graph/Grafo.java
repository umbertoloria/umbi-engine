package tests.graph;

import java.util.*;

public class Grafo<T, W extends Comparable<W>> {

	private HashMap<T, HashMap<T, W>> archi = new HashMap<>();

	public void add(T nodo) {
		if (!exists(nodo)) {
			archi.put(nodo, new HashMap<>());
		}
	}

	public boolean exists(T nodo) {
		return archi.containsKey(nodo);
	}

	public void link(T sorgente, T destinazione, W peso) {
		add(sorgente);
		add(destinazione);
		archi.get(sorgente).put(destinazione, peso);
		archi.get(destinazione).put(sorgente, peso);
	}

	public Set<T> vertici() {
		return archi.keySet();
	}

	public boolean linked(T x, T y) {
		if (exists(x) && exists(y)) {
			return archi.get(x).containsKey(y);
		} else {
			return false;
		}
	}

	public List<Arco<T, W>> archi() {
		List<Arco<T, W>> result = new ArrayList<>();
		for (T u : archi.keySet()) {
			for (T v : archi.get(u).keySet()) {
				Arco<T, W> rev = new Arco<>(u, v, archi.get(u).get(v));
				Arco<T, W> cur = new Arco<>(v, u, archi.get(u).get(v));
				if (!result.contains(rev)) {
					result.add(cur);
				}
			}
		}
		return result;
	}

	public Set<Arco<T, W>> adjacents(T u) {
		Set<Arco<T, W>> adj = new HashSet<>();
		if (exists(u)) {
			for (T t : archi.get(u).keySet()) {
				adj.add(new Arco<>(u, t, archi.get(u).get(t)));
			}
		}
		return adj;
	}

}
