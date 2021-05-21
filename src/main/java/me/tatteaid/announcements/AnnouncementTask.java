package me.tatteaid.announcements;

import me.tatteaid.AutoAnnouncer;
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

    private final AutoAnnouncer instance;
    private int counter = 0;

    public AnnouncementTask(AutoAnnouncer instance) {
        this.instance = instance;

        instance.getAnnouncementManager().getAnnouncements().add("Test Announcement 1.");
        instance.getAnnouncementManager().getAnnouncements().add("Test Announcement 2.");
        instance.getAnnouncementManager().getAnnouncements().add("Test Announcement 3.");
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(instance.getAnnouncementManager().getAnnouncements().get(counter++));
        if(counter >= instance.getAnnouncementManager().getAnnouncements().size()) counter = 0;
    }
}