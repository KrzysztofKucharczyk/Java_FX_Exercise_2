package com.starterkit.javafx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.dataprovider.data.BookVO;
import com.starterkit.javafx.dataprovider.data.SexVO;
import com.starterkit.javafx.model.PersonSearch;
import com.starterkit.javafx.texttospeech.Speaker;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller for the person search screen.
 * <p>
 * The JavaFX runtime will inject corresponding objects in the @FXML annotated
 * fields. The @FXML annotated methods will be called by JavaFX runtime at
 * specific points in time.
 * </p>
 *
 * @author Leszek
 */
public class PersonSearchController {

	private static final Logger LOG = Logger.getLogger(PersonSearchController.class);

	/**
	 * Resource bundle loaded with this controller. JavaFX injects a resource
	 * bundle specified in {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code resources}.
	 * </p>
	 */
	@FXML
	private ResourceBundle resources;

	/**
	 * URL of the loaded FXML file. JavaFX injects an URL specified in
	 * {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code location}.
	 * </p>
	 */
	@FXML
	private URL location;

	/**
	 * JavaFX injects an object defined in FXML with the same "fx:id" as the
	 * variable name.
	 */
	@FXML
	private TextField titleField;
	
	@FXML
	private TextField authorsField;

	@FXML
	private Button searchButton;
	
	@FXML
	private Button showBooksButton;

	@FXML
	private TableView<BookVO> resultTable;

	@FXML
	private TableColumn<BookVO, String> titleColumn;

	@FXML
	private TableColumn<BookVO, String> authorsColumn;

	@FXML
	private TableColumn<BookVO, String> yearColumn;

	@FXML
	private TableColumn<BookVO, String> genreColumn;

	private final DataProvider dataProvider = DataProvider.INSTANCE;

	@SuppressWarnings("unused")
	private final Speaker speaker = Speaker.INSTANCE;

	private final PersonSearch model = new PersonSearch();

	/**
	 * The JavaFX runtime instantiates this controller.
	 * <p>
	 * The @FXML annotated fields are not yet initialized at this point.
	 * </p>
	 */
	public PersonSearchController() {
		LOG.debug("Constructor: nameField = " + titleField);
	}

	/**
	 * The JavaFX runtime calls this method after loading the FXML file.
	 * <p>
	 * The @FXML annotated fields are initialized at this point.
	 * </p>
	 * <p>
	 * NOTE: The method name must be {@code initialize}.
	 * </p>
	 */
	@FXML
	private void initialize() {
		LOG.debug("initialize(): nameField = " + titleField);

		initializeResultTable();

		/*
		 * Bind controls properties to model properties.
		 */
		titleField.textProperty().bindBidirectional(model.titleProperty());
		authorsField.textProperty().bindBidirectional(model.authorsProperty());
		resultTable.itemsProperty().bind(model.resultProperty());
		
		titleField.setText("");
		authorsField.setText("");

		/*
		 * Make the Search button inactive when the Name field is empty.
		 */
		searchButton.disableProperty().bind(titleField.textProperty().isEmpty().and(authorsField.textProperty().isEmpty()));
	}


	private void initializeResultTable() {
		/*
		 * Define what properties of PersonVO will be displayed in different
		 * columns.
		 */
		titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

		authorsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAuthors()));
		authorsColumn.setCellValueFactory(new PropertyValueFactory<>("authors"));

		yearColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getYear()));
		yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

		genreColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getGenre()));
		genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
		// sexColumn.setCellValueFactory(
		// new Callback<TableColumn.CellDataFeatures<BookVO, String>,
		// ObservableValue<String>>() {
		//
		// @Override
		// public ObservableValue<String> call(CellDataFeatures<BookVO, String>
		// param) {
		// String sex = param.getValue().getAuthors()();
		// String text = getInternationalizedText(Sex.fromSexVO(sex));
		// return new ReadOnlyStringWrapper(text);
		// }
		// });
		// birthDateColumn
		// .setCellValueFactory(cellData -> new
		// ReadOnlyObjectWrapper<>(cellData.getValue().getBirthDate()));
		//
		// /*
		// * Define how the data (formatting, alignment, etc.) is displayed in
		// * columns.
		// */
		// birthDateColumn
		// .setCellFactory(param -> new
		// LocalDateTableCell<BookVO>(DateTimeFormatter.ofPattern("dd MMMM
		// yyyy")));
		//
		// /*
		// * Define how the values in columns are sorted. By the default the
		// * values are sorted lexicographically.
		// */
		// birthDateColumn.setComparator(new Comparator<LocalDate>() {
		//
		// @Override
		// public int compare(LocalDate o1, LocalDate o2) {
		// return o1.getDayOfMonth() - o2.getDayOfMonth();
		// }
		// });

		/*
		 * Show specific text for an empty table. This can also be done in FXML.
		 */
		resultTable.setPlaceholder(new Label(resources.getString("table.emptyText")));

		/*
		 * When table's row gets selected say given person's name.
		 */
		resultTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BookVO>() {

			@Override
			public void changed(ObservableValue<? extends BookVO> observable, BookVO oldValue, BookVO newValue) {
				LOG.debug(newValue + " selected");

				// if (newValue != null) {
				// Task<Void> backgroundTask = new Task<Void>() {
				//
				// @Override
				// protected Void call() throws Exception {
				// speaker.say(newValue.getName());
				// return null;
				// }
				//
				// @Override
				// protected void failed() {
				// LOG.error("Could not say name: " + newValue.getName(),
				// getException());
				// }
				// };
				// new Thread(backgroundTask).start();
				// }
			}
		});
	}

	/**
	 * The JavaFX runtime calls this method when the <b>Search</b> button is
	 * clicked.
	 *
	 * @param event
	 *            {@link ActionEvent} holding information about this event
	 */
	@FXML
	private void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");
		searchButtonActionVersion();
	}

	@FXML
	private void showButtonAction(ActionEvent event) {
		LOG.debug("'Show' button clicked");

		showButtonActionVersion();
	}

	/**
	 * This implementation is correct.
	 * <p>
	 * The {@link DataProvider#findPersons(String, SexVO)} call is executed in a
	 * background thread.
	 * </p>
	 */
	private void searchButtonActionVersion() {
		/*
		 * Use task to execute the potentially long running call in background
		 * (separate thread), so that the JavaFX Application Thread is not
		 * blocked.
		 */
		Task<Collection<BookVO>> backgroundTask = new Task<Collection<BookVO>>() {

			/**
			 * This method will be executed in a background thread.
			 */
			@Override
			protected Collection<BookVO> call() throws Exception {
				LOG.debug("call() called");

				if(model.getAuthors().isEmpty())
					model.setAuthors(" ");
				Collection<BookVO> result = dataProvider.findBooks( 
						model.getTitle(),
						model.getAuthors());

				return result;
			}

			/**
			 * This method will be executed in the JavaFX Application Thread
			 * when the task finishes.
			 */
			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				/*
				 * Get result of the task execution.
				 */
				Collection<BookVO> result = getValue();
				/*
				 * Copy the result to model.
				 */
				model.setResult(new ArrayList<BookVO>(result));

				/*
				 * Reset sorting in the result table.
				 */
				resultTable.getSortOrder().clear();

				//titleField.setText("");
				//authorsField.setText("");
			}
		};

		/*
		 * Start the background task. In real life projects some framework
		 * manages background tasks. You should never create a thread on your
		 * own.
		 */
		new Thread(backgroundTask).start();
	}

	private void showButtonActionVersion() {
		/*
		 * Use task to execute the potentially long running call in background
		 * (separate thread), so that the JavaFX Application Thread is not
		 * blocked.
		 */
		Task<Collection<BookVO>> backgroundTask = new Task<Collection<BookVO>>() {

			/**
			 * This method will be executed in a background thread.
			 */
			@Override
			protected Collection<BookVO> call() throws Exception {
				LOG.debug("call() called");

				/*
				 * Get the data.
				 */
				Collection<BookVO> result = dataProvider.getBooks();

				/*
				 * Value returned from this method is stored as a result of task
				 * execution.
				 */
				return result;
			}

			/**
			 * This method will be executed in the JavaFX Application Thread
			 * when the task finishes.
			 */
			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				/*
				 * Get result of the task execution.
				 */
				Collection<BookVO> result = getValue();
				/*
				 * Copy the result to model.
				 */
				model.setResult(new ArrayList<BookVO>(result));

				/*
				 * Reset sorting in the result table.
				 */
				resultTable.getSortOrder().clear();
			}
		};

		/*
		 * Start the background task. In real life projects some framework
		 * manages background tasks. You should never create a thread on your
		 * own.
		 */
		new Thread(backgroundTask).start();
	}
}
