package com.github.impervious.jobs;

import com.github.impervious.FIABot;
import com.github.impervious.utils.Channels;
import com.github.impervious.utils.Util;

import net.dv8tion.jda.api.entities.Member;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.awt.*;
import java.util.Calendar;

public class FIAReminder implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Member venohm = FIABot.getMainGuild().retrieveMemberById(289669713787748352L).complete();
        Member acct4Loss = FIABot.getMainGuild().retrieveMemberById(309112716948275200L).complete();
        Member xEntric = FIABot.getMainGuild().retrieveMemberById(272790899757678593L).complete();
        Member impervious = FIABot.getMainGuild().retrieveMemberById(73463573900173312L).complete();

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        if(venohm != null && acct4Loss != null && xEntric != null && impervious != null) {
            if (weekDay == 3) { //TUESDAY
                Util.sendMessage(Channels.PROFIA_ID.getChannel(), xEntric.getAsMention() + " " + impervious.getAsMention());
                Util.sendEmbed(Channels.PROFIA_ID.getChannel(), "Don't forget to submit your verdicts! <:PepeHappy:772354625776386078> A link can be found by clicking on my name.");
            } else if (weekDay == 6) { //FRIDAY
                Util.sendMessage(Channels.DEVFIA_ID.getChannel(), venohm.getAsMention() + " " + acct4Loss.getAsMention());
                Util.sendEmbed(Channels.DEVFIA_ID.getChannel(), "Don't forget to submit your verdicts! <:PepeHappy:772354625776386078> A link can be found by clicking on my name.");
            } else {
                Util.easyEmbed(Channels.ERRORS_ID.getChannel(), "Job was triggered not on a Tuesday or a Friday????", Color.ORANGE);
            }
        } else {
            Util.easyEmbed(Channels.ERRORS_ID.getChannel(), "One of the users is null. Maybe someone left?", Color.ORANGE);
        }
    }
}