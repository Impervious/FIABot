package com.github.impervious.jobs;

import com.github.impervious.Main;
import com.github.impervious.utils.Channels;
import com.github.impervious.utils.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.time.Instant;
import java.util.Calendar;

public class FIAReminder implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Member venohm = Main.getMainGuild().retrieveMemberById(289669713787748352L).complete();
        Member acct4Loss = Main.getMainGuild().retrieveMemberById(309112716948275200L).complete();
        Member xEntric = Main.getMainGuild().retrieveMemberById(272790899757678593L).complete();
        Member impervious = Main.getMainGuild().retrieveMemberById(73463573900173312L).complete();

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("FIA Overlord", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing", "https://i.imgur.com/ph47q0g.jpg")
                .setColor(new Color(212, 39, 177))
                .setTitle("Hey! Listen!", "https://docs.google.com/spreadsheets/d/1RapkZM9ajQ9fFggAvPP4RThHGYitNSkBNed474wwKRU/edit?usp=sharing")
                .setDescription("Don't forget to submit your verdicts! <:PepeHappy:772354625776386078>")
                .setTimestamp(Instant.now());

        if(venohm != null && acct4Loss != null && xEntric != null && impervious != null) {
            if (weekDay == 3) { //TUESDAY
                Util.sendMessage(Channels.PROFIA_ID.getChannel(), xEntric.getAsMention() + " " + impervious.getAsMention());
                Channels.PROFIA_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            } else if (weekDay == 6) { //FRIDAY
                Util.sendMessage(Channels.DEVFIA_ID.getChannel(), venohm.getAsMention() + " " + acct4Loss.getAsMention());
                Channels.DEVFIA_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            } else {
                Util.sendMessage(Channels.TESTING_ID.getChannel(), impervious.getAsMention() + " " + impervious.getAsMention());
                Channels.TESTING_ID.getChannel().sendMessageEmbeds(builder.build()).queue();
            }
        } else {
            System.out.print("UH OH ONE OF THE USERS IS NULL");
        }
    }
}