/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import javafx.animation.Transition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class ButtonPaddingShrinkTransition extends Transition
{
    private Insets originalPadding = null;
    private double maxPaddingLeft, maxPaddingRight, minPaddingLeft, minPaddingRight;
    private Button button;


    public ButtonPaddingShrinkTransition(Duration duration, Button button, double widthOffset, double minPaddingLeft, double minPaddingRight)
    {
        this.button = button;
        this.minPaddingLeft = minPaddingLeft;
        this.minPaddingRight = minPaddingRight;

        this.setCycleDuration(duration);

        this.originalPadding = this.button.getPadding();

        this.maxPaddingLeft = Math.min(widthOffset / 2, this.originalPadding.getLeft() - this.minPaddingLeft);
        this.maxPaddingRight = Math.min(widthOffset / 2, this.originalPadding.getRight() - this.minPaddingRight);
    }


    @Override
    protected void interpolate(double frac)
    {
        Insets p2 = new Insets(this.originalPadding.getTop(), this.originalPadding.getLeft() - (this.maxPaddingLeft * frac), this.originalPadding.getBottom(), this.originalPadding.getRight() - (this.maxPaddingRight * frac));
        this.button.setPadding(p2);


    }
}
