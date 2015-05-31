/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tbee.javafx.scene.layout.MigPane;

public class LaddaFXDemo extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("LaddaFX Demo");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ultramixer/laddafx/test/layout.fxml"));
        MigPane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
