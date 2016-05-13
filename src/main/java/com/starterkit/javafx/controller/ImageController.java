package com.starterkit.javafx.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public class ImageController extends PersonSearchController {

	private String pathname;
	private String[] files;
	private int index;

	@FXML
	private ImageView imageView;

	@FXML
	private Button nextImageButton;

	@FXML
	private Button previousImageButton;

	@FXML
	private Button slideshowButton;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ScrollPane scrollPane;
	
	public ImageController() {

	}

	public void initData(int index, String filepath, String[] files) throws IOException {
		this.index = index;
		this.pathname = filepath;
		this.files = files;
		imageView.setFitWidth(600);
		imageView.setFitHeight(500);
		
		createImage();
	}

	public void createImage() throws IOException {
		imageView.setImage(new Image(getActualFilename()));

	}

	public void previousImageButtonAction() throws IOException {
		if (index == 0)
			index = files.length - 1;
		else
			index--;
		createImage();
	}

	public void nextImageButtonAction() throws IOException {
		if ((files.length - 1) == index)
			index = 0;
		else
			index++;
		createImage();
	}

	private String getActualFilename() {
		return this.pathname + files[index];
	}

	public void slideshowButtonAction() throws IOException, InterruptedException {
		new Thread() {
			public void run() {
				for (int i = 0; i < files.length; i++) {
					try {
						nextImageButtonAction();
						Thread.sleep(2 * 1000);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		createImage();
	}
	
	@FXML
	private void scrollAction(ScrollEvent event) {
		if (event.getDeltaY() > 0) {
			zoomIn();
		} else {
			zoomOut();
		}
	}


	private void zoomOut() {
		imageView.setFitWidth(imageView.getFitWidth() * 0.8);
		imageView.setFitHeight(imageView.getFitHeight() * 0.8);
	}

	private void zoomIn() {
		imageView.setFitWidth(imageView.getFitWidth() * 1.2);
		imageView.setFitHeight(imageView.getFitHeight() * 1.2);
	}

}
