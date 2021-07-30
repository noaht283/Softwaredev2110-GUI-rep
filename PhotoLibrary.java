import java.util.ArrayList;
import java.util.HashSet;

public class PhotoLibrary extends PhotographContainer {

	private HashSet<Album> albums = new HashSet<Album>();
	private int id; // number of the id of the person

	public static void main(String[] args) {

	}

	public ArrayList getAlbumList() {
		ArrayList<Album> albumList = new ArrayList<>();
		for (Album i : albums) {
			albumList.add(i);
		}
		return albumList;
	}

	public HashSet<Album> getalbums() {
		return albums;
	}

	public PhotoLibrary(String name, int id) // initializes fields in the constructor
	{
		super(name);
		this.id = id;
	}

	public void setName(String name) { // initializes string: name
		this.name = name;
	}

	public int getId() { // receives the integer: id
		return id;
	}

	public boolean removePhoto(Photograph p) // If the ArrayList photos already has Photograph P it removes p from the
												// list, unless p is null or not in AL
	{
		if (photos.contains(p)) {
			photos.remove(p);
			for (Album a : albums) {
				if (a.hasPhoto(p)) {
					a.removePhoto(p);
				}

			}
			return true;
		} else {
			return false;
		}

	}

	public boolean equals(Object obj) { //
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		PhotoLibrary otherPerson = (PhotoLibrary) obj;
		return this.id == otherPerson.id;
	}

	@Override
	public String toString() // returns the values of the fields
	{
		return "Name : " + this.name + " ID: " + this.id + " Photo List: " + this.photos + "Album Names: "
				+ getAlbumList();
	}

	public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b)
	// creates a new ArrayList and uses two for statements to cycle through both
	// person a and bs photos and declares that if a's photos
	// of the index i are equal to b's of index i (and photos doesn't contain it
	// already) then it adds b photos to the new ArrayList
	{
		ArrayList<Photograph> doublePhotos = new ArrayList<Photograph>();
		for (int i = 0; i <= a.photos.size() - 1; i++) {
			for (int j = 0; j <= b.photos.size() - 1; j++) {
				if (a.photos.get(i).equals(b.photos.get(j)) && !(doublePhotos.contains(b.photos.get(j)))) {
					doublePhotos.add(b.photos.get(j));
				}
			}
		}
		return doublePhotos;
	}

	public HashSet<Album> getAlbums() {
		return albums;
	}

	public static double similarity(PhotoLibrary a, PhotoLibrary b)
	// using common photos it produces the doublePhotos ArrayList, it then specifies
	// if a's photos is bigger than b's photos (or vice versa)
	// it then divides the new ArrayList with the smaller person's photos, it then
	// returns that double (if A or B didn't post, it returns 0.0)
	{
		ArrayList<Photograph> doublePhotos = new ArrayList<Photograph>();
		for (int i = 0; i <= a.photos.size() - 1; i++) {
			for (int j = 0; j <= b.photos.size() - 1; j++) {
				if (a.photos.get(i).equals(b.photos.get(j)) && !(doublePhotos.contains(b.photos.get(j)))) {
					doublePhotos.add(b.photos.get(j));
				}
			}
		}

		if (a.photos.size() >= b.photos.size() && (a.photos.size() != 0) && (b.photos.size() != 0)) {
			return doublePhotos.size() / b.photos.size();
		} else if (a.photos.size() <= b.photos.size() && (a.photos.size() != 0) && (b.photos.size() != 0)) {
			return doublePhotos.size() / a.photos.size();
		} else {
			return 0.0;
		}

	}

	public boolean createAlbum(String albumName)
	// creates a new album of type album then adds albumName to them if album doesnt
	// contain it
	{
		Album albumAdd = new Album(albumName);
		if (albums.contains(albumAdd)) {
			return false;
		}
		if (!(albums.contains(albumAdd))) {
			albums.add(albumAdd);
			return true;
		} else {
			return false;
		}

	}

	public boolean removeAlbum(String albumName)
	// Removes the Album with name albumName if an Album with that name exists in
	// the set
	// of albums. Returns true if the remove was successful, false otherwise.
	{
		if (!(albums.contains(getAlbumByName(albumName)))) {

			return false;
		}
		if (albums.contains(getAlbumByName(albumName))) {

			albums.remove(getAlbumByName(albumName));
			return true;
		} else {

			return false;
		}
	}

	public boolean addPhotoToAlbum(Photograph p, String albumName)
	/*
	 * adds photo from album if the photo is already in album using a for statement
	 * for each photo in album
	 */
	{
		if (!(photos.contains(p))) {
			return false;
		}
		for (Album i : albums) {
			if (i.getName() == albumName && !((p == null) || (albumName == null))) {
				i.addPhoto(p);
				return true;
			}
		}
		return false;
	}

	public boolean removePhotoFromAlbum(Photograph p, String albumName) {
		/*
		 * Remove the Photograph p from the Album in the set of albums that has name
		 * albumName. Return true if the photo was successfully removed. Otherwise
		 * return false.
		 */
		if (!(photos.contains(p))) {
			return false;
		}
		for (Album i : albums) {
			if (i.getName() == albumName && !((p == null) || (albumName == null))) {
				i.removePhoto(p);
				return true;
			}
		}
		return false;
	}

	private Album getAlbumByName(String albumName) {

		for (Album i : albums) {
			if (i.getName() == albumName && !(albumName == null)) {
				return i;
			}
		}
		return null;
	}

}
