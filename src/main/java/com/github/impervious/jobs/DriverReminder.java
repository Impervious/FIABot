package com.github.impervious.jobs;

import com.github.impervious.FIABot;
import com.github.impervious.utils.Channels;
import com.github.impervious.utils.Util;

import net.dv8tion.jda.api.entities.Role;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Calendar;

public class DriverReminder implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Role proDriver = FIABot.getMainGuild().getRoleById(758712296381612092L);
        Role proReserve = FIABot.getMainGuild().getRoleById(758719582814601247L);
        Role devDriver = FIABot.getMainGuild().getRoleById(781259406695727125L);
        Role devReserve = FIABot.getMainGuild().getRoleById(781259577348849694L);

        Calendar cal = Calendar.getInstance();
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);

        if (weekDay == 4 && proDriver != null && proReserve != null) { //WEDNESDAY
            Util.sendMessage(Channels.PROGENERAL_ID.getChannel(), proDriver.getAsMention() + " " + proReserve.getAsMention());
            Util.sendEmbed(Channels.PROGENERAL_ID.getChannel(), "Remember to submit any reports to " + Channels.PROREPORT_ID.getChannel().getAsMention() + " within 24 hours of this message.");
        } else if (weekDay == 1 && devDriver != null && devReserve != null) { //SUNDAY
            Util.sendMessage(Channels.DEVGENERAL_ID.getChannel(), devDriver.getAsMention() + " " + devReserve.getAsMention());
            Util.sendEmbed(Channels.DEVGENERAL_ID.getChannel(), "Remember to submit any reports to " + Channels.DEVREPORT_ID.getChannel().getAsMention() + " within 24 hours of this message.");
        }
    }
}