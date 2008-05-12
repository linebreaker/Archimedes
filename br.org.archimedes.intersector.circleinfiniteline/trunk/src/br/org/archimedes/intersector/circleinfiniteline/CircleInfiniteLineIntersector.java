package br.org.archimedes.intersector.circleinfiniteline;

import java.util.ArrayList;
import java.util.Collection;

import br.org.archimedes.Constant;
import br.org.archimedes.Geometrics;
import br.org.archimedes.circle.Circle;
import br.org.archimedes.exceptions.NullArgumentException;
import br.org.archimedes.infiniteline.InfiniteLine;
import br.org.archimedes.interfaces.Intersector;
import br.org.archimedes.model.Element;
import br.org.archimedes.model.Point;
import br.org.archimedes.model.Vector;

public class CircleInfiniteLineIntersector implements Intersector{

	public Collection<Point> getIntersections(Element element,
			Element otherElement) throws NullArgumentException {

		if (element == null || otherElement == null)
			throw new NullArgumentException();
		
		Collection<Point> intersections = new ArrayList<Point>();

		InfiniteLine infiniteLine;
		Circle circle;

		if (element.getClass() == InfiniteLine.class) {
			infiniteLine = (InfiniteLine) element;
			circle = (Circle) otherElement;
		} else {
			infiniteLine = (InfiniteLine) otherElement;
			circle = (Circle) element;
		}
		
		Point projection = null;
		double distance = 0.0;
		
		try {
			projection = infiniteLine.getProjectionOf(circle.getCenter());
			distance = Geometrics.calculateDistance(circle.getCenter(),
					projection);
		} catch (NullArgumentException e) {
			e.printStackTrace();
		}

		if ((distance - circle.getRadius()) <= Constant.EPSILON) {

			Vector infiniteLineVector = new Vector(infiniteLine.getInitialPoint(), infiniteLine
					.getEndingPoint());
			infiniteLineVector = Geometrics.normalize(infiniteLineVector);

			double semiCord = Math.sqrt(circle.getRadius() * circle.getRadius()
					- distance * distance);

			infiniteLineVector = infiniteLineVector.multiply(semiCord);
			Point intersection1 = projection.addVector(infiniteLineVector);
			infiniteLineVector = infiniteLineVector.multiply(-1);
			Point intersection2 = projection.addVector(infiniteLineVector);

			if (infiniteLine.contains(intersection1))
				intersections.add(intersection1);
			if (!intersection2.equals(intersection1) && infiniteLine.contains(intersection2)) {
				intersections.add(intersection2);
			}
		}
		return intersections;
	}
}