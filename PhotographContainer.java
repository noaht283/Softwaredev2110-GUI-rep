import java.util.ArrayList;

// abstract class is extended to multiple different classes and now holds two fields and
// many different methods that can be accessed by all different classes
public abstract class PhotographContainer {
	protected String name;
	protected ArrayList<Photograph> photos = new ArrayList<Photograph>(); // list of a person's photos

	public PhotographContainer(String name) {
		// constructor for class Album
		this.name = name;
		this.photos = photos;
	}

	public String getName() {
		// returns name
		return name;
	}

	public void setName(String name) {
		// Initializes name
		this.name = name;
	}

	public ArrayList<Photograph> getPhotos() {
		// returns photos
		return photos;
	}

	public boolean addPhoto(Photograph p) {
		// if photos doesn't contain p adds p unless null or contains
		if (p == null) {
			return false;
		}
		if (!(photos.contains(p))) {
			photos.add(p);
			return true;
		} else {
			return false;
		}

	}

	public boolean hasPhoto(Photograph p) {
		// if contains photo return true else false
		if (photos.contains(p)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean removePhoto(Photograph p) {
		// if contains photo removes and return true
		if (photos.contains(p)) {
			photos.remove(p);
			return true;
		} else {
			return false;
		}
	}

	public int numPhotographs() {
		// returns size of photos in int
		return photos.size();
	}

	public boolean equals(Object o) {
		// creates new object of album o and returns this.name equals new object if true
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Album otherAlbum = (Album) o;
		return name.contentEquals(otherAlbum.name);
	}

	public String toString() {
		// prints out both name and filenames using a new array-list with stored
		// filenames
		ArrayList<String> filenamePhotos = new ArrayList<String>();
		for (int i = 0; i <= photos.size() - 1; i++) {
			filenamePhotos.add(photos.get(i).getFilename());
		}
		return ("Album " + this.name + " Filenames: " + filenamePhotos);

	}

	public ArrayList<Photograph> getPhotos(int rating) {
		ArrayList<Photograph> ratedPhotos = new ArrayList<Photograph>(); // empty ArrayList that holds all added photos
		ArrayList<Photograph> emptyPhotos = new ArrayList<Photograph>();// empty ArrayList to return if no photos are in
																		// ratedPhotos
		for (int i = 0; i < photos.size(); i++) {
			if (photos.get(i).getRating() >= rating) // checks if each item has a rating greater or equal to the
														// argument
			{
				ratedPhotos.add(photos.get(i));
			}
		}
		if (ratedPhotos.size() > 0) // checks that ratedPhotos has any Photograph objects
		{
			return ratedPhotos;
		}
		return emptyPhotos;

	}

	public ArrayList<Photograph> getPhotosInYear(int year) {
		/*
		 * Return an ArrayList of photos from the photos feed that were taken in the
		 * year provided.
		 */
		ArrayList<Photograph> sameYearPhotos = new ArrayList<Photograph>();
		if (year < 0) {
			return null;
		}
		for (int i = 0; i <= photos.size() - 1; i++) {
			if (photos.get(i).getDateTaken().contains(Integer.toString(year))) {
				sameYearPhotos.add(photos.get(i));
			}

		}
		if (sameYearPhotos.size() >= 0) {
			return sameYearPhotos;
		} else {
			return null;
		}
	}

	public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
		/*
		 * \ Return an ArrayList of photos from the photos feed that were taken in the
		 * month and year provided
		 */
		ArrayList<Photograph> sameMonthPhotos = new ArrayList<Photograph>();
		if (month > 12 || month < 1 || year < 1) {
			return null;
		}
		for (int i = 0; i <= photos.size() - 1; i++) {
			if (photos.get(i).getDateTaken().contains(Integer.toString(year) + "-0" + Integer.toString(month))) {
				sameMonthPhotos.add(photos.get(i));
			} else if (photos.get(i).getDateTaken().contains(Integer.toString(year) + "-" + Integer.toString(month))) {
				sameMonthPhotos.add(photos.get(i));
			}
		}
		if (sameMonthPhotos.size() >= 0) {
			return sameMonthPhotos;
		} else {
			return null;
		}

	}

	// method to verify if a date is correct, tries if the year is less than 0, if
	// month is between 0-12,
	// checks the days based on month, etc.
	private boolean isValidDate(String date) {
		if (date.length() != 10) {
			return false;
		}
		try {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));
			if (year < 0) {
				return false;
			}
			if (month < 0 || month > 12) {
				return false;
			}
			if (month == 2) {
				return day <= 28;
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				return day <= 30;
			} else {
				return day <= 31;
			}
		} catch (NumberFormatException e) {
			return false;
		}

	}

	// starts by declaring if endDate is bigger than beginDate (and vice-versa)
	// return false,
	// then it deletes the hyphens in both statements and turns them into integers,
	// then the for
	//
	public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {

		if (!isValidDate(beginDate) || !isValidDate(endDate)) {
			return null;
		}
		if (beginDate.compareTo(endDate) > 0) {
			return null;
		}
		ArrayList<Photograph> inBetweenPhotos = new ArrayList<Photograph>();
		beginDate = beginDate.replace("-", "");
		endDate = endDate.replace("-", "");
		int newBeginDate = Integer.valueOf(beginDate);
		int newEndDate = Integer.valueOf(endDate);

		for (int i = 0; i <= photos.size() - 1; i++) {
			if (Integer.valueOf(photos.get(i).getDateTaken().replace("-", "")) >= newBeginDate
					&& Integer.valueOf(photos.get(i).getDateTaken().replace("-", "")) <= newEndDate) {
				inBetweenPhotos.add(photos.get(i));
			}
		}
		return inBetweenPhotos;

	}
}
