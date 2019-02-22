package graphics.textures;

import java.util.ArrayList;
import java.util.Scanner;

public class SpriteMap {

	private SpriteGroup[] groups;

	public SpriteMap(String file) {
		Scanner in = new Scanner(SpriteMap.class.getResourceAsStream(file));
		ArrayList<SpriteGroup> coordinatesList = new ArrayList<>();
		String[] tokens = new String[3];
		while (in.hasNextLine()) {
			String line = in.nextLine().trim();
			int tokensCount = interpreta(line, 0, tokens, 0);
			String[] interval = tokens[1].split("-");
			int begin = Integer.parseInt(interval[0]);
			int end = Integer.parseInt(interval[1]);
			if (tokensCount == 3) {
				if (tokens[2].equals("lineare")) {
					coordinatesList.add(new SpriteGroup(tokens[0], begin, end, SpriteGroup.Trend.LINEARE));
				} else if (tokens[2].equals("alternata")) {
					coordinatesList.add(new SpriteGroup(tokens[0], begin, end, SpriteGroup.Trend.ALTERNATA));
				} else {
					throw new RuntimeException("SpriteMap inconsistente");
				}
			} else {
				coordinatesList.add(new SpriteGroup(tokens[0], begin, end));
			}
		}
		groups = new SpriteGroup[coordinatesList.size()];
		coordinatesList.toArray(groups);
	}

	private int interpreta(String line, int stringIndex, String[] tokens, int tokensCount) {
		if (stringIndex < line.length() && tokensCount < 3) {
			int n = stringIndex + 1;
			if (line.charAt(stringIndex) == ' ') {
				while (n < line.length() && line.charAt(n) == ' ') {
					n++;
				}
			} else {
				while (n < line.length() && line.charAt(n) != ' ') {
					n++;
				}
				tokens[tokensCount++] = line.substring(stringIndex, n);
			}
			return interpreta(line, n, tokens, tokensCount);
		} else {
			return tokensCount;
		}
	}

	public float nextFrom(int x) {
		if (groups[groups.length - 1].getLast() == x) {
			return groups[0].getFirst();
		}
		for (int i = 0; i < groups.length; i++) {
			if (groups[i].contains(x)) {
				if (groups[i].getLast() == x) {
					return groups[i + 1].getFirst();
				} else {
					return x + 1;
				}
			}
		}
		return x;
	}

	SpriteGroup[] getGroups() {
		return groups;
	}

}
