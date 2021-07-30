import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {
	/*
	 * Create the class CompareByCaption that implements the Comparator interface
	 * and compares two Photographs by caption (in alphabetical order). If two
	 * captions are identical, then compare by rating, in descending order with the
	 * highest-rated photo first.
	 */
	public static void main(String[] args) {
		Photograph p1 = new Photograph("anna.jpg", "Anna", "2014-01-11", 8);
		Photograph p2 = new Photograph("betty.jpg", "Betty", "2014-08-11", 6);
		Photograph p3 = new Photograph("catherine.jpg", "Catherine", "2014-01-11", 2);
		ArrayList<Photograph> list2 = new ArrayList<Photograph>();
		list2.add(p1);
		list2.add(p2);
		list2.add(p3);
		System.out.println(list2);
		Collections.sort(list2, new CompareByCaption());
		System.out.println(list2);

	}

	// if a compared to b (caption-wise) is less than 0 (i.e. a comes alphabetically
	// first),
	// it returns negative, if they are the same, it defers to rating where the
	// greater rating returns a
	// negative, else positive in both cases (opposite of initial if)

	public int compare(Photograph a, Photograph b) {
		if (a.caption.compareTo(b.caption) < 0) {
			return -1;
		} else if (a.caption.compareTo(b.caption) == 0) {
			if (a.rating > b.rating) {
				return -1;
			} else if (a.rating == b.rating) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
