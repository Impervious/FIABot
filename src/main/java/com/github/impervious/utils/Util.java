package com.github.impervious.utils;

import com.github.impervious.Main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
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
        } catch(InsufficientPermissionException missingPerm) {
            reportError("Missing permission to send message.", missingPerm);
        } catch(IllegalArgumentException illegalArg) {
            reportError("Message is too long, empty or null", illegalArg);
        }
    }

    public static void sendEmbed(TextChannel channel, String description) {
        Member impervious = Main.getMainGuild().retrieveMemberById(73463573900173312L).complete();
        MessageEmbed embed = new EmbedBuilder()
                .setAuthor(Main.getInstance().getClient().getSelfUser().getName(), Main.getInstance().getClient().getSelfUser().getAvatarUrl())
                .setColor(new Color(212, 39, 177))
                .setTitle(description)
                .setFooter("Made by " + impervious.getEffectiveName() + "#" + impervious.getUser().getDiscriminator())
                .setTimestamp(Instant.now())
                .build();
            channel.sendMessageEmbeds(embed).queue();
    }

    public static void reportError(String message, Exception e) {
        e.printStackTrace();
        EmbedBuilder embed = new EmbedBuilder();

        StringBuilder stack = new StringBuilder();
        for(StackTraceElement s : e.getStackTrace()) {
            stack.append(s.toString());
            stack.append("\n");
        }

        String stackString = stack.toString();
        if(stackString.length() > 1024) {
            stackString = stackString.substring(0, 1024);
        }

        Channels.ERRORS_ID.getChannel().sendMessageEmbeds(embed
                .setColor(Color.RED)
                .setAuthor(Main.getInstance().getClient().getSelfUser().getName(), Main.getInstance().getClient().getSelfUser().getAvatarUrl())
                .setDescription(message)
                .addField("\u200B", "\u200B", false)
                .addField("Exception:", e.toString(), false)
                .addField("Stack:", stackString, false)
                .setTimestamp(Instant.now())
                .build()).queue();
    }
}