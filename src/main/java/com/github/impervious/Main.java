package com.github.impervious;

import java.util.Optional;

import com.github.impervious.commands.TestCommand;
import com.github.impervious.jobs.DriverReminder;
import com.github.impervious.jobs.FIAReminder;
import com.github.impervious.utils.Util;

import com.jagrosh.jdautilities.command.CommandClientBuilder;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.security.auth.login.LoginException;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;

public class Main {

    private static JDA jda;

    private Scheduler scheduler;

    private static Main instance;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        instance = this;
        Util util = new Util();
        Optional<String> token = util.getBotToken();
        if(token.isEmpty()) {
            System.out.println("Add your token to settings.yaml");
            System.out.println("Shutting down...");
            System.exit(0);
            return;
        }

        CommandClientBuilder client = new CommandClientBuilder();
        client.useDefaultGame();
        client.setOwnerId("73463573900173312");
        client.setActivity(null);
        client.setPrefix("$");
        client.setAlternativePrefix("!!");
        client.addCommands(new TestCommand());

        try {
            jda = JDABuilder.createDefault(token.get())
                    .addEventListeners(client.build())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

        try {
            SchedulerFactory factory = new StdSchedulerFactory();
            scheduler = factory.getScheduler();
            if(scheduler != null) {
                scheduler.start();
            }
        } catch (SchedulerException e) {
            Util.reportError("Error starting scheduler", e);
        }

        //JOBS AND TRIGGERS

        JobDetail fiaReminder = newJob(FIAReminder.class)
                .withIdentity("fiaReminder", "group1")
                .build();

        CronTrigger fiaReminderTrigger = TriggerBuilder.newTrigger()
                .withIdentity("fiaReminderTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 0 18 ? * TUE,FRI *")) // FIRES EVERY TUESDAY & FRIDAY(2 DAYS AFTER EACH TIERS RACE) AT 6PM EST
                .build();

        JobDetail driverReminder = newJob(DriverReminder.class)
                .withIdentity("driverReminder", "group1")
                .build();

        CronTrigger driverReminderTrigger = TriggerBuilder.newTrigger()
                .withIdentity("driverReminderTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule("0 30 19 ? * WED,SUN *")) // FIRES EVERY WEDNESDAY & SUNDAY AT 7:30PM EST
                .build();

        try {
            if(scheduler != null) {
                scheduler.scheduleJob(fiaReminder, fiaReminderTrigger);
                scheduler.scheduleJob(driverReminder, driverReminderTrigger);
            }
        } catch(SchedulerException e) {
            Util.reportError("Error scheduling jobs", e);
        }
    }

    public static Guild getMainGuild() { return jda.getGuildById("739893045994061946"); }
    public static Main getInstance() { return instance; }
    public JDA getClient() { return jda; }
}