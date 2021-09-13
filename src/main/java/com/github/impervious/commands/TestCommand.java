package com.github.impervious.commands;

import com.github.impervious.Main;
import com.github.impervious.utils.Channels;
import com.github.impervious.utils.Util;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.util.Calendar;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
        this.aliases = new String[]{"remind"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Member impervious = Main.getMainGuild().retrieveMemberById(73463573900173312L).complete();

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg")
                .setColor(new Color(212, 39, 177))
                .setTitle("Hey! Listen!", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing")
                .setDescription("Don't forget to submit your verdicts! <:PepeHappy:772354625776386078>")
                .setTimestamp(Instant.now());

        if(impervious != null) {
            if (weekDay == 3) { //TUESDAY
                //Util.sendMessage(Channels.NOTIFICATIONS.getChannel(), xEntric.getAsTag() + " " + impervious.getAsTag());
                Channels.ROOM_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            } else if (weekDay == 6) { //FRIDAY
                //Util.sendMessage(Channels.TESTING_ID.getChannel(), impervious.getName() + " " + impervious.getAsMention());
                Channels.ROOM_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            } else {
                Util.sendMessage(Channels.TESTING_ID.getChannel(), impervious.getAsMention() + " " + impervious.getAsMention());
                Channels.TESTING_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            }
        } else {
            Util.sendMessage(event.getTextChannel(), "nice try, nerd.");
        }
    }
}