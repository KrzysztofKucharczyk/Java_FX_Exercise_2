package com.starterkit.javafx.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ImageController extends PersonSearchController{
	
	private Image img;
	private String pathname;

	@FXML
	private ImageView imageView;
	
	@FXML
	private Button nextImageButton;
	
	@FXML
	private Button previousImageButton;
	
	@FXML
	private AnchorPane anchorPane;
	
	public ImageController() {
		
	}
	
	public void initData(String pathname) throws IOException {
		
		
		this.pathname = pathname;
		createImage();
	}
	
	public void createImage() throws IOException {
		imageView.setImage(new Image(this.pathname));

	}
	
	public void nextImageButtonAction() {
		;
	}
	
}
