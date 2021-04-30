package me.tatteaid.announcements;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/*
 TODO:
  - Sounds when an announcement is sent.
  - HEX Color Support for 1.16+.
  - Ability to center the announcement.
  - Per world announcements.
  - Per permission announcements.
  - PlaceholderAPI support in announcements.
  - Ability to randomize announcements.
  - No announcements when no players are online.
 */
public class AnnouncementTask extends BukkitRunnable {

    private List<String> announcements = new ArrayList<>();
    private int counter = 0;

    public AnnouncementTask() {
        announcements.add("Test announcement 1.");
        announcements.add("Test announcement 2.");
        announcements.add("Test announcement 3.");
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(announcements.get(counter++));
        if(counter >= announcements.size()) counter = 0;
    }
}