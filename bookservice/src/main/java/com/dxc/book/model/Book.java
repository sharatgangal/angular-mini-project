package com.dxc.book.model;



import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author sgangal2
 *
 */
@Document
//@Document---This annotation marks a class as being a domain object that we 
//want to persist to the database{mongodb is the database}
//It also allows us to choose the name of the collection we want to use.

@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonIgnoreProperties---is used at a class level to mark a property 
//or list of properties to be ignored.

public class Book {
	 @Id
	 private String _id;
	 private String bookId;
	 private String userId;
	 private String bookTitle;
	 private String[] bookAuthor;
     private String bookImage;
     private int bookFrequency;
     
   //Constructors are being used i.e both parameterized and  non-parameterized constructor


	
	/**
	 * no argument constructor
	 */
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return unique id
	 */
	//Both setters and getters being implmented.

	public String get_id() {
		return _id;
	}

	/**
	 * @param _id
	 */
	public void set_id(String _id) {
		this._id = _id;
	}

	/**
	 * @return unique id of book
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return book title
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return book author
	 */
	public String[] getBookAuthor() {
		return bookAuthor;
	}

	/**
	 * @param bookAuthor
	 */
	public void setBookAuthor(String[] bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	/**
	 * @return book image
	 */
	public String getBookImage() {
		return bookImage;
	}

	/**
	 * @param bookImage
	 */
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	/**
	 * @return bookFrequency
	 */
	public int getBookFrequency() {
		return bookFrequency;
	}

	/**
	 * @param bookFrequency
	 */
	public void setBookFrequency(int bookFrequency) {
		this.bookFrequency = bookFrequency;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + Arrays.hashCode(bookAuthor);
		result = prime * result + bookFrequency;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((bookImage == null) ? 0 : bookImage.hashCode());
		result = prime * result + ((bookTitle == null) ? 0 : bookTitle.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (!Arrays.equals(bookAuthor, other.bookAuthor))
			return false;
		if (bookFrequency != other.bookFrequency)
			return false;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (bookImage == null) {
			if (other.bookImage != null)
				return false;
		} else if (!bookImage.equals(other.bookImage))
			return false;
		if (bookTitle == null) {
			if (other.bookTitle != null)
				return false;
		} else if (!bookTitle.equals(other.bookTitle))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Book [_id=" + _id + ", bookId=" + bookId + ", userId=" + userId + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + Arrays.toString(bookAuthor) + ", bookImage=" + bookImage + ", bookFrequency="
				+ bookFrequency + "]";
	}

}