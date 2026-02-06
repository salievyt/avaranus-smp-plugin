package dev.sm1le.avaranusSMP.modules.antiXray.util;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.Deque;
import java.util.Iterator;

public class XrayUtils {

    public static boolean hasSharpTurns(Deque<Location> path) {
        if (path.size() < 6) return false;

        Iterator<Location> it = path.iterator();
        Location a = it.next();
        Location b = it.next();
        Location c = it.next();

        Vector ab = b.toVector().subtract(a.toVector());
        Vector bc = c.toVector().subtract(b.toVector());

        return ab.angle(bc) > Math.toRadians(70);
    }

    public static boolean foundWithoutTunnel(Deque<Location> path) {
        if (path.size() < 5) return false;

        Location first = path.getFirst();
        Location last = path.getLast();

        return first.distance(last) < 3;
    }
}