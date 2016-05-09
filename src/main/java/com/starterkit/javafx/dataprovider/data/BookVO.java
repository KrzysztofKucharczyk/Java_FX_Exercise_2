package com.starterkit.javafx.dataprovider.data;

/**
 * Person data.
 *
 * @author Leszek
 */
public class BookVO {

		private Long id;
	private String title;
	private String authors;
	private String status;
	private String genre;
	private String year;

	public BookVO( String title, String authors, String status, String genre, String year) {
		this.id = 3L;
		this.title = title;
		this.authors = authors;
		this.status = status;
		this.genre = genre;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Person [title=" + title + ", authors=" + authors;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
