package com.alerman.advent2019.day12;


import javax.vecmath.Point3d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day12 {

    public static void main(String[] args) {
        Set<Integer> seenHashCodes = new HashSet<>();
        List<Moon> moons = new ArrayList<>();
        List<Moon> copyMoons = new ArrayList<>();
        initMoons(moons);
        initMoons(copyMoons);

        long steps = 0;
        boolean found = false;
        while(found == false) {
            //update velocities
            for (int i = 0; i < 4; i++) {
                Moon a = moons.get(i);
                for (int j = i + 1; j < 4; j++) {
                    Moon b = moons.get(j);
                    a.velocity.setX(a.velocity.getX() + Double.compare(b.getPosition().getX(), a.getPosition().getX()));
                    b.velocity.setX(b.velocity.getX() + Double.compare(a.getPosition().getX(), b.getPosition().getX()));
                    a.velocity.setY(a.velocity.getY() + Double.compare(b.getPosition().getY(), a.getPosition().getY()));
                    b.velocity.setY(b.velocity.getY() + Double.compare(a.getPosition().getY(), b.getPosition().getY()));
                    a.velocity.setZ(a.velocity.getZ() + Double.compare(b.getPosition().getZ(), a.getPosition().getZ()));
                    b.velocity.setZ(b.velocity.getZ() + Double.compare(a.getPosition().getZ(), b.getPosition().getZ()));

                }
            }

            //update positions
            for (int i = 0; i < 4; i++) {
                Moon moon = moons.get(i);
                moon.getPosition().setX(moon.getPosition().getX() + moon.getVelocity().getX());
                moon.getPosition().setY(moon.getPosition().getY() + moon.getVelocity().getY());
                moon.getPosition().setZ(moon.getPosition().getZ() + moon.getVelocity().getZ());
            }
            if(moons.get(0).getVelocity().getX() == 0D && copyMoons.equals(moons) && steps != 0)
            {
                found = true;
            }

            steps++;

            if(steps%10000000 == 0)
            {
                System.out.println(steps);
            }

        }
        System.out.println(steps);
        System.out.println(computeEnergy(moons));

        System.out.println("HERE");
    }

    private static void initMoons(List<Moon> moons) {
        Moon moon1 = new Moon(new Point3d(-16,-1,-12));
        Moon moon2 = new Moon(new Point3d(0,-4,-17));
        Moon moon3 = new Moon(new Point3d(-11,11,0));
        Moon moon4 = new Moon(new Point3d(2,2,-6));
        moons.add(moon1);
        moons.add(moon2);
        moons.add(moon3);
        moons.add(moon4);
    }

    private static double computeEnergy(List<Moon> moons) {
        double total = 0;
        for(int i=0;i<4;i++)
        {
            Moon a = moons.get(i);
            double potential = Math.abs(a.getPosition().getX()) +  Math.abs(a.getPosition().getY()) +  Math.abs(a.getPosition().getZ());
            double kinetic = Math.abs(a.getVelocity().getX()) +  Math.abs(a.getVelocity().getY()) +  Math.abs(a.getVelocity().getZ());
            total += (potential * kinetic);
        }

        return total;

    }
}
