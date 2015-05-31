/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import org.tbee.javafx.scene.layout.fxml.MigPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

public class LaddaFXDemoController implements Initializable
{
    @FXML
    private MigPane root;

    public void initialize(URL location, ResourceBundle resources)
    {
        Set<Node> laddaButtons = root.lookupAll("LaddaButton");
        for (Node laddaButtonNode : laddaButtons)
        {
            if (laddaButtonNode instanceof LaddaButton)
            {
                final LaddaButton laddaButton = (LaddaButton) laddaButtonNode;

                laddaButton.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event)
                    {
                        if (laddaButton.isInProgress())
                        {
                            laddaButton.setInProgress(false);
                        }
                        else
                        {
                            laddaButton.setInProgress(true);
                        }
                    }
                });
            }
        }
    }
}
