/*
 * Copyright (c) 2015 Tobias Bley, UltraMixer Digital Audio Solutions (www.ultramixer.com)
 */

package com.ultramixer.laddafx;

import javafx.animation.Transition;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class WidthShrinkTransition extends Transition
{
    private final double startWidth;
    private Region node;

    public WidthShrinkTransition(Duration duration, Region node)
    {
        this.node = node;
        this.startWidth = node.getPrefWidth();
        this.setCycleDuration(duration);

    }

    @Override
    protected void interpolate(double frac)
    {
        double w = startWidth * (1 - frac);
        this.node.setMinWidth(w);
        this.node.setMaxWidth(w);
    }
}
