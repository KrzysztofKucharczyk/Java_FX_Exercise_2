package com.starterkit.javafx.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ImageController extends PersonSearchController{
	
	private String pathname;
	
	@FXML
	private AnchorPane anchorPane;
	
	public ImageController() {
		
	}
	
	public void initData(String pathname) {
		this.pathname = pathname;
		Image img = new Image(this.pathname);
    	ImageView imgView = new ImageView(img);
    	anchorPane.getChildren().add(imgView);
	}
	
}
