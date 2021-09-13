package com.github.impervious.utils;

import com.github.impervious.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

public class Util {

    private static File botPath;

    static {
        try {
            File jarPath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile();
            botPath = new File(jarPath, "data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<String> getBotToken() {
        try {
            File tokenFile = new File(botPath, "settings.yaml");
            if(tokenFile.exists()) {
                String token = FileUtils.readFileToString(tokenFile, (String) null);
                if(!token.equalsIgnoreCase("TOKEN") && !token.isEmpty()) {
                    return Optional.of(token);
                } else {
                    return Optional.empty();
                }
            } else {
                FileUtils.writeStringToFile(tokenFile, "TOKEN", (String) null);
                return Optional.empty();
            }
        } catch(IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static void sendMessage(MessageChannel channel, String message) {
        try {
            channel.sendMessage(message).queue();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    //TODO DOES NOT WORK
    /*public static void sendEmbed(String author, String authorURL, String authorIcon, String description, String footer) {
        new EmbedBuilder()
                .setAuthor(author, authorURL, authorIcon)
                .setColor(new Color(212, 39, 177))
                .setTitle(description)
                .setFooter(footer)
                .setTimestamp(Instant.now())
                .build();
    }*/
}