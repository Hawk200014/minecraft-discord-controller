package de.hawk200014;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class RequestProcessor {
    private final String regex = "#§;§#";
    public void processData(String servername, String body){
        String[] data = body.split(regex);
        String command = data[0];

        try{
            switch (command){
                case "users":
                    break;
                case "message":
                    Guild guild = Main.jda.getGuildById((long)Singletons.getInstance().getSingleton("guildid"));
                    if(guild == null) return;
                    TextChannel textChannel = guild.getTextChannelById((long)Singletons.getInstance().getSingleton("textchannel"));
                    if(textChannel == null) return;

                default:
                    System.out.println(500);
            }
        }
        catch (Exception e){
            System.out.println(500);
        }
    }




}
