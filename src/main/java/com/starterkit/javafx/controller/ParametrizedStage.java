package com.starterkit.javafx.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// REV: ta klasa nie jest wykorzystywana
public class ParametrizedStage extends Stage{
	private String filePath;
	
	public ParametrizedStage(String filePath) {
		this.filePath = filePath;
	}
	
	public void create() {
		Image img = new Image(filePath);
    	ImageView imgView = new ImageView(img);
    	
    	//anchorPane.getChildren().add(imgView);
	}

}
