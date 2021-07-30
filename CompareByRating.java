import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {
	/*
	 * Create the class CompareByRating that implements the Comparator interface and
	 * compares two Photographs by rating (in descending order). If two ratings are
	 * identical, then compare by caption in alphabetical order.
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
		Collections.sort(list2, new CompareByRating());
		System.out.println(list2);
	}

	// if a is greater compared to b (rating-wise) return a negative,
	// if they are the same, it defers to caption where the more alphabetically
	// first
	// caption is returned negative, else positive in both cases (vice versa of the
	// original ifs)

	public int compare(Photograph a, Photograph b) {
		if (a.rating > b.rating) {
			return -1;
		} else if (a.rating == b.rating) {
			if (a.caption.compareTo(b.caption) < 0) {
				return -1;
			} else if (a.caption.compareTo(b.caption) == 0) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}
