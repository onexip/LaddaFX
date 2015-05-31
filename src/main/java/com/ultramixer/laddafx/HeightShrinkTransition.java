/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class HeightShrinkTransition extends Transition
{
    private final double startHeight;
    private Region node;

    public HeightShrinkTransition(Duration duration, Region node)
    {
        this.node = node;
        this.startHeight = node.getPrefHeight();
        this.setCycleDuration(duration);

    }

    @Override
    protected void interpolate(double frac)
    {
        double h = startHeight * (1 - frac);
        this.node.setMinHeight(h);
        this.node.setMaxHeight(h);
        this.node.setPrefHeight(h);
    }
}
