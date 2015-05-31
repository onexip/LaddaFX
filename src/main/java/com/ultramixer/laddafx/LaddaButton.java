/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import com.sun.javafx.css.converters.SizeConverter;
import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SkinBase;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class LaddaButton extends Button
{
    public static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
    private static final double ANIMATION_DURATION = 200;
    private static final double ANIMATION_WIDTH_OFFSET = 10;
    private final static List<CssMetaData<? extends Styleable, ?>> styleables =
            new ArrayList<CssMetaData<? extends Styleable, ?>>(SkinBase.getClassCssMetaData());
    private static CssMetaData<LaddaButton, Number> PROGRESS_INDICATOR_SIZE_CSS = null;

    static
    {
        PROGRESS_INDICATOR_SIZE_CSS =
                new CssMetaData<LaddaButton, Number>("-fx-progress-indicator-size",
                        SizeConverter.getInstance(), 16)
                {

                    @Override
                    public boolean isSettable(LaddaButton n)
                    {
                        return n.progressIndicatorSize == null || !n.progressIndicatorSizeProperty().isBound();

                    }

                    @Override
                    public StyleableProperty<Number> getStyleableProperty(LaddaButton n)
                    {
                        return (StyleableProperty<Number>) n.progressIndicatorSize;
                    }
                };


        styleables.add(PROGRESS_INDICATOR_SIZE_CSS);
        STYLEABLES = Collections.unmodifiableList(styleables);
    }

    private final ProgressIndicator progressIndicator;
    private SimpleBooleanProperty inProgress;
    private SimpleObjectProperty<LaddaButtonStyle> laddaButtonStyleProperty = new SimpleObjectProperty<LaddaButtonStyle>(LaddaButtonStyle.EXPAND_LEFT);
    private DoubleProperty progressIndicatorSize = new StyleableDoubleProperty(16)
    {
        public Object getBean()
        {
            return LaddaButton.this;
        }

        public String getName()
        {
            return "progressIndicatorSize";
        }

        public CssMetaData<LaddaButton, Number> getCssMetaData()
        {
            return PROGRESS_INDICATOR_SIZE_CSS;
        }
    };


    public LaddaButton()
    {
        this.getStyleClass().add("ladda-button");

        getCssMetaData().addAll(STYLEABLES);

        this.progressIndicator = new ProgressIndicator();
        progressIndicator.setMouseTransparent(true);
        progressIndicator.setPrefSize(getProgressIndicatorSize(), getProgressIndicatorSize());

        progressIndicatorSize.addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                progressIndicator.setPrefWidth(newValue.doubleValue());
                progressIndicator.setPrefHeight(newValue.doubleValue());
            }
        });

        inProgressProperty().addListener(new ChangeListener<Boolean>()
        {
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    showProgressStartAnimation();
                }
                else
                {
                    showProgressStopAnimation();
                }
            }
        });

        laddaButtonStyleProperty().addListener(new ChangeListener<LaddaButtonStyle>()
        {
            public void changed(ObservableValue<? extends LaddaButtonStyle> observable, LaddaButtonStyle oldValue, LaddaButtonStyle newValue)
            {
                refreshLaddaButtonStyle();
            }
        });


    }


    private void refreshLaddaButtonStyle()
    {
        LaddaButtonStyle style = getLaddaButtonStyle();
        if (style.equals(LaddaButtonStyle.EXPAND_LEFT))
        {
            setContentDisplay(ContentDisplay.LEFT);
        }
        else if (style.equals(LaddaButtonStyle.EXPAND_RIGHT))
        {
            setContentDisplay(ContentDisplay.RIGHT);
        }
        else if (style.equals(LaddaButtonStyle.EXPAND_UP))
        {
            setContentDisplay(ContentDisplay.TOP);
        }
        else if (style.equals(LaddaButtonStyle.EXPAND_DOWN))
        {
            setContentDisplay(ContentDisplay.BOTTOM);
        }
    }

    public LaddaButtonStyle getLaddaButtonStyle()
    {
        return laddaButtonStyleProperty().get();
    }

    public void setLaddaButtonStyle(LaddaButtonStyle laddaButtonStyle)
    {
        this.laddaButtonStyleProperty().set(laddaButtonStyle);
    }

    public SimpleObjectProperty<LaddaButtonStyle> laddaButtonStyleProperty()
    {
        return laddaButtonStyleProperty;
    }

    private void showProgressStartAnimation()
    {
        if (getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_LEFT) || getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_RIGHT))
        {
            Timeline tt = new Timeline(
                    new KeyFrame(Duration.millis(1),
                            new KeyValue(this.progressIndicator.minWidthProperty(), 0),
                            new KeyValue(this.progressIndicator.maxWidthProperty(), 0),
                            new KeyValue(this.graphicProperty(), progressIndicator)),

                    new KeyFrame(Duration.millis(ANIMATION_DURATION * 0.8),
                            new KeyValue(this.progressIndicator.minWidthProperty(), getProgressIndicatorSize() + ANIMATION_WIDTH_OFFSET, Interpolator.EASE_IN),
                            new KeyValue(this.progressIndicator.maxWidthProperty(), getProgressIndicatorSize() + ANIMATION_WIDTH_OFFSET, Interpolator.EASE_IN)
                    ),
                    new KeyFrame(Duration.millis(ANIMATION_DURATION),
                            new KeyValue(this.progressIndicator.minWidthProperty(), getProgressIndicatorSize(), Interpolator.EASE_OUT),
                            new KeyValue(this.progressIndicator.maxWidthProperty(), getProgressIndicatorSize(), Interpolator.EASE_OUT)
                    )

            );

            tt.setDelay(Duration.millis(0));
            tt.play();
        }
        else if (getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_UP) || getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_DOWN))
        {
            Timeline tt = new Timeline(
                    new KeyFrame(Duration.millis(1),
                            new KeyValue(this.progressIndicator.minHeightProperty(), 0),
                            new KeyValue(this.progressIndicator.maxHeightProperty(), 0),
                            new KeyValue(this.progressIndicator.prefHeightProperty(), 0),
                            new KeyValue(this.graphicProperty(), progressIndicator)),

                    new KeyFrame(Duration.millis(ANIMATION_DURATION * 0.8),
                            new KeyValue(this.progressIndicator.minHeightProperty(), getProgressIndicatorSize() + ANIMATION_WIDTH_OFFSET, Interpolator.EASE_IN),
                            new KeyValue(this.progressIndicator.maxHeightProperty(), getProgressIndicatorSize() + ANIMATION_WIDTH_OFFSET, Interpolator.EASE_IN),
                            new KeyValue(this.progressIndicator.prefHeightProperty(), getProgressIndicatorSize() + ANIMATION_WIDTH_OFFSET, Interpolator.EASE_IN)
                    ),
                    new KeyFrame(Duration.millis(ANIMATION_DURATION),
                            new KeyValue(this.progressIndicator.minHeightProperty(), getProgressIndicatorSize(), Interpolator.EASE_OUT),
                            new KeyValue(this.progressIndicator.maxHeightProperty(), getProgressIndicatorSize(), Interpolator.EASE_OUT),
                            new KeyValue(this.progressIndicator.prefHeightProperty(), getProgressIndicatorSize(), Interpolator.EASE_OUT)
                    )

            );
            tt.setDelay(Duration.millis(0));
            tt.play();
        }

    }

    private void showProgressStopAnimation()
    {
        if (getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_LEFT) || getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_RIGHT))
        {
            WidthShrinkTransition widthShrinkTransition = new WidthShrinkTransition(Duration.millis(ANIMATION_DURATION * 0.8), progressIndicator);
            widthShrinkTransition.setCycleCount(1);
            widthShrinkTransition.setInterpolator(Interpolator.EASE_IN);

            ButtonPaddingLeftRightShrinkTransition buttonPaddingTransition = new ButtonPaddingLeftRightShrinkTransition(Duration.millis(ANIMATION_DURATION * 0.2), LaddaButton.this, ANIMATION_WIDTH_OFFSET, 5, 5);
            buttonPaddingTransition.setCycleCount(2);
            buttonPaddingTransition.setAutoReverse(true);
            buttonPaddingTransition.setInterpolator(Interpolator.EASE_OUT);


            SequentialTransition st = new SequentialTransition();
            st.getChildren().addAll(widthShrinkTransition, buttonPaddingTransition);
            st.setOnFinished(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    setGraphic(null);
                }
            });

            st.play();
        }
        else if (getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_UP) || getLaddaButtonStyle().equals(LaddaButtonStyle.EXPAND_DOWN))
        {
            HeightShrinkTransition heightShrinkTransition = new HeightShrinkTransition(Duration.millis(ANIMATION_DURATION * 0.8), progressIndicator);
            heightShrinkTransition.setCycleCount(1);
            heightShrinkTransition.setInterpolator(Interpolator.EASE_IN);

            ButtonPaddingTopBottomShrinkTransition buttonPaddingTransition = new ButtonPaddingTopBottomShrinkTransition(Duration.millis(ANIMATION_DURATION * 0.2), LaddaButton.this, ANIMATION_WIDTH_OFFSET, 2, 2);
            buttonPaddingTransition.setCycleCount(2);
            buttonPaddingTransition.setAutoReverse(true);
            buttonPaddingTransition.setInterpolator(Interpolator.EASE_OUT);


            SequentialTransition st = new SequentialTransition();
            st.getChildren().addAll(heightShrinkTransition, buttonPaddingTransition);
            st.setOnFinished(new EventHandler<ActionEvent>()
            {
                public void handle(ActionEvent event)
                {
                    setGraphic(null);
                }
            });

            st.play();
        }
    }

    public boolean isInProgress()
    {
        return inProgress.get();
    }

    public void setInProgress(boolean inProgress)
    {
        this.inProgress.set(inProgress);
    }

    public SimpleBooleanProperty inProgressProperty()
    {
        if (inProgress == null)
        {
            inProgress = new SimpleBooleanProperty(false);
        }
        return inProgress;
    }

    public ProgressIndicator getProgressIndicator()
    {
        return progressIndicator;
    }

    public double getProgressIndicatorSize()
    {
        return progressIndicatorSize.get();
    }

    public void setProgressIndicatorSize(double progressIndicatorSize)
    {
        this.progressIndicatorSize.set(progressIndicatorSize);
    }

    public DoubleProperty progressIndicatorSizeProperty()
    {
        return progressIndicatorSize;
    }


}
