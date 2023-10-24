package de.hawk200014;

import de.hawk200014.Socket.ClientHandler;
import de.hawk200014.Socket.ServerSocketManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.net.ServerSocket;

public class Main {
    public static String secret;
    public static JDA jda;
    public static void main(String[] args) {
        long guildid = 0;
        if(args.length == 2) {
            initSingletons(args[1], "serverlist.txt", guildid);
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

        Main.jda = builder.build();
        try {
            jda.awaitReady();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void initApi() {
        try {
            ServerSocketManager serverSocketManager = new ServerSocketManager(new ServerSocket(35565));
            Thread thread = new Thread(serverSocketManager);
            thread.start();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(-2);
        }
    }

    public static void initSingletons(String socketSecret, String filepath, long guildID) {
        RequestProcessor rp = new RequestProcessor();
        ClientHandler.rp = rp;
        new Singletons().addSingleton(rp, "requestprocessor");
        Main.secret = socketSecret;
        Singletons.getInstance().addSingleton(socketSecret, "apisecret");
        Singletons.getInstance().addSingleton(guildID, "guidid");
    }

}