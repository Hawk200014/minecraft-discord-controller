package de.hawk200014;

import de.hawk200014.Socket.ClientHandler;
import de.hawk200014.Socket.ServerSocketManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static String secret;
    public static Config config;
    public static JDA jda;
    public static void main(String[] args) {
        initFolders();
        Main.config = new Config(new File(System.getProperty("user.dir") + "/config/app.config"));
        long guildid = 0;
        String discordToken = "";
        String secret = "";
        if(args.length == 0){
            try {
                guildid = Long.parseLong(config.getProperty("guildid"));
                discordToken = config.getProperty("discordtoken");
                secret = config.getProperty("secret");
            }
            catch (NumberFormatException e){
                System.out.println("GuildId is invalid and needs to be new configured!");
            }
            initSingletons(secret,guildid);
            initDiscord(discordToken);
            initSocket();
        }
        if(args.length == 3) {
            initSingletons(args[1], Long.parseLong(args[2]));
            initDiscord(args[0]);
            initSocket();
        }
        else{
            System.out.println("DiscordMcNode.jar [DiscordToken] [ApiSecret] [guildID]");
        }
    }

    private static void initFolders() {
        String datadir = System.getProperty("user.dir") + "/data";
        String configdir = System.getProperty("user.dir") + "/config";
        Path datapath = Paths.get(datadir);
        Path configpath = Paths.get(configdir);
        if(!Files.exists(datapath)){
            try {
                Files.createDirectory(datapath);
            } catch (IOException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }
        if(!Files.exists(configpath)){
            try {
                Files.createDirectory(configpath);
            } catch (IOException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static void initDiscord(String key) {
        JDABuilder builder = JDABuilder.createDefault(key);
        Main.config.saveProperty("discordtoken", key, "DiscordToken");
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

    public static void initSocket() {
        try {
            ServerSocketManager serverSocketManager = new ServerSocketManager(new ServerSocket(35565));
            Thread thread = new Thread(serverSocketManager);
            thread.start();
        } catch (Exception e){
            e.printStackTrace();
            System.exit(-2);
        }
    }

    public static void initSingletons(String socketSecret, long guildID) {
        RequestProcessor rp = new RequestProcessor();
        ClientHandler.rp = rp;
        new Singletons().addSingleton(rp, "requestprocessor");
        Main.secret = socketSecret;
        Main.config.saveProperty("socketSecret", socketSecret, "Socket Secret");
        Main.config.saveProperty("guildid", guildID + "", "guildid");
        Singletons.getInstance().addSingleton(guildID, "guildid");
    }

}