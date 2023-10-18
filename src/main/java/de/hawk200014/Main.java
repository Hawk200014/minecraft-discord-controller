package de.hawk200014;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
    public static void main(String[] args)
    {
        initSingletons();
        initDiscord(args[0]);
        initApi();
    }

    public static void initDiscord(String key){
        JDABuilder builder = JDABuilder.createDefault(key);

        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("Minecraft Servers"));

        builder.build();
    }

    public static void initApi(){
        spark.Spark.post("/api", (request, response) -> {
            return ((RequestProcessor)Singletons.getInstance().getSingleton("requestprocessor")).processData(request, response);
        });
    }

    public static void initSingletons(){
        new Singletons().addSingleton(new RequestProcessor(), "requestprocessor");
    }

}