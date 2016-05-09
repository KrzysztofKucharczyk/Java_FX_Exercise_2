package com.starterkit.javafx.model;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.javafx.dataprovider.data.BookVO;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Data displayed on the person search screen.
 *
 * @author Leszek
 */
public class PersonSearch {

	private final StringProperty title = new SimpleStringProperty();
	private final ObjectProperty<Sex> sex = new SimpleObjectProperty<>();
	private final ListProperty<BookVO> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));

	public final String getName() {
		System.out.println("---> " + title);
		System.out.println("---> " + title.get());
		return title.get();
	}

	public final void setName(String value) {
		title.set(value);
	}

	public StringProperty titleProperty() {
		return title;
	}

	public final Sex getSex() {
		return sex.get();
	}

	public final void setSex(Sex value) {
		sex.set(value);
	}

	public ObjectProperty<Sex> sexProperty() {
		return sex;
	}

	public final List<BookVO> getResult() {
		return result.get();
	}

	public final void setResult(List<BookVO> value) {
		result.setAll(value);
	}

	public ListProperty<BookVO> resultProperty() {
		return result;
	}

	@Override
	public String toString() {
		return "PersonSearch [name=" + title + ", sex=" + sex + ", result=" + result + "]";
	}

}
