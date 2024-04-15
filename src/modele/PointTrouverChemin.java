package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PointTrouverChemin {
	private int x;
	private int y;
	private PointTrouverChemin precedent;

	public PointTrouverChemin(int x, int y, PointTrouverChemin precedent) {
		this.x = x;
		this.y = y;
		this.precedent = precedent;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	@Override
	public boolean equals(Object o) {
		PointTrouverChemin point = (PointTrouverChemin) o;
		return x == point.x && y == point.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public PointTrouverChemin decaler(int ox, int oy) {
		return new PointTrouverChemin(x + ox, y + oy, this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public PointTrouverChemin getPrecedent() {
		return precedent;
	}

	public void setPrecedent(PointTrouverChemin precedent) {
		this.precedent = precedent;
	}
	
}
