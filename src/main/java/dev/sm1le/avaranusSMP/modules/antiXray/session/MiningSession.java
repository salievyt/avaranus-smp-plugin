package dev.sm1le.avaranusSMP.modules.antiXray.session;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayDeque;
import java.util.Deque;

public class MiningSession {

    public int stone;
    public int ores;
    public int diamonds;

    public long startTime = System.currentTimeMillis();
    public final Deque<Location> path = new ArrayDeque<>();

    public void record(Material type, Location loc) {
        if (path.size() > 30) path.removeFirst();
        path.addLast(loc);

        if (type == Material.STONE || type == Material.DEEPSLATE)
            stone++;

        if (type.toString().endsWith("_ORE"))
            ores++;

        if (type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE)
            diamonds++;
    }

    public long playTime() {
        return System.currentTimeMillis() - startTime;
    }
}