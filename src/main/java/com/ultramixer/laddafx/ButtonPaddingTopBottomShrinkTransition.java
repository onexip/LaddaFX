/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonPaddingTopBottomShrinkTransition extends Transition
{
    private Insets originalPadding = null;
    private double maxPaddingTop, maxPaddingBottom, minPaddingTop, minPaddingBottom;
    private Button button;


    public ButtonPaddingTopBottomShrinkTransition(Duration duration, Button button, double heightOffset, double minPaddingTop, double minPaddingBottom)
    {
        this.button = button;
        this.minPaddingTop = minPaddingTop;
        this.minPaddingBottom = minPaddingBottom;

        this.setCycleDuration(duration);

        this.originalPadding = this.button.getPadding();

        this.maxPaddingTop = Math.min(heightOffset / 2, this.originalPadding.getTop() - this.minPaddingTop);
        this.maxPaddingBottom = Math.min(heightOffset / 2, this.originalPadding.getBottom() - this.minPaddingBottom);
    }


    @Override
    protected void interpolate(double frac)
    {
        Insets p2 = new Insets(this.originalPadding.getTop() - (this.maxPaddingTop * frac), this.originalPadding.getLeft(), this.originalPadding.getBottom() - (this.maxPaddingBottom * frac), this.originalPadding.getRight());
        this.button.setPadding(p2);


    }
}
