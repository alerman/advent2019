package com.alerman.advent2019.day12;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.vecmath.Point3d;

@Data
@EqualsAndHashCode
public class Moon {
    Point3d velocity = new Point3d(0,0,0);
    Point3d position;

    public Moon(Point3d initialPosition)
    {
        this.position = initialPosition;
    }
}
