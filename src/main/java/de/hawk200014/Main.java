package de.hawk200014;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static void main(String[] args)
    {
        JDABuilder builder = JDABuilder.createDefault(args[0]);

        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("Minecraft Servers"));

        builder.build();
    }
}