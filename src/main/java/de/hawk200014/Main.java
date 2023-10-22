package de.hawk200014;

import de.hawk200014.ServerList.ServerList;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static void main(String[] args) {
        if(args.length == 2) {
            initSingletons(args[1], "serverlist.txt");
            initDiscord(args[0]);
            initApi();
        }
        else{
            System.out.println("DiscordMcNode.jar [DiscordToken] [ApiSecret]");
        }
    }

    public static void initDiscord(String key) {
        JDABuilder builder = JDABuilder.createDefault(key);

        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("Minecraft Servers"));

        JDA jda = builder.build();
        try {
            jda.awaitReady();
        }
        catch (Exception e){
            System.exit(-1);
        }
    }

    public static void initApi() {

    }

    public static void initSingletons(String apiSecret, String filepath) {
        new Singletons().addSingleton(new RequestProcessor(), "requestprocessor");
        Singletons.getInstance().addSingleton(apiSecret, "apisecret");
        Singletons.getInstance().addSingleton(new ServerList(filepath), "serverlist");
    }

}