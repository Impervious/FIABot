package com.github.impervious.commands;

import com.github.impervious.utils.Channels;
import com.github.impervious.utils.FIAMembers;
import com.github.impervious.utils.Roles;
import com.github.impervious.utils.Util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class RemindCommand extends Command {

    public RemindCommand() {
        this.name = "remind";
        this.arguments = "<role>";
        this.ownerCommand = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getMessage().delete().queue();

        if(event.getArgs().equals("drivers")) {
            if(event.getTextChannel().equals(Channels.PROREPORT_ID.getChannel())) {
                Util.sendMessage(event.getTextChannel(), Roles.PRODRIVER_ID.getRole().getAsMention() + " " + Roles.PRORESERVE_ID.getRole().getAsMention());
                Util.sendMessage(event.getTextChannel(), "Remember to submit any reports to " + Channels.PROREPORT_ID.getChannel().getAsMention() + " within 24 hours of this message.");
            } else if(event.getTextChannel().equals(Channels.DEVREPORT_ID.getChannel())) {
                Util.sendMessage(event.getTextChannel(), Roles.DEVDRIVER_ID.getRole().getAsMention() + " " + Roles.DEVRESERVE_ID.getRole().getAsMention());
                Util.sendMessage(event.getTextChannel(), "Remember to submit any reports to " + Channels.DEVREPORT_ID.getChannel().getAsMention() + " within 24 hours of this message.");
            } else {
                Util.sendMessage(event.getTextChannel(), "Incorrect channel.");
            }
        } else if(event.getArgs().equals("fia")) {
            if(event.getTextChannel().equals(Channels.PROFIA_ID.getChannel())) {
                Util.sendMessage(Channels.PROFIA_ID.getChannel(), FIAMembers.VENOHM_ID.getMember().getAsMention() + " " + FIAMembers.ACCT4LOSS_ID.getMember().getAsMention());
                Util.sendEmbed(Channels.PROFIA_ID.getChannel(), "Don't forget to submit your verdicts! <:PepeHappy:772354625776386078> A link can be found by clicking on my name.");
            } else if(event.getTextChannel().equals(Channels.DEVFIA_ID.getChannel())) {
                Util.sendMessage(Channels.DEVFIA_ID.getChannel(), FIAMembers.IMPERVIOUS_ID.getMember().getAsMention() + " " + FIAMembers.XENTRIC_ID.getMember().getAsMention());
                Util.sendEmbed(Channels.DEVFIA_ID.getChannel(), "Don't forget to submit your verdicts! <:PepeHappy:772354625776386078> A link can be found by clicking on my name.");
            } else {
                Util.sendMessage(event.getChannel(), "Incorrect channel.");
            }
        } else {
            Util.sendMessage(event.getChannel(), "Incorrect Syntax: !!remind <drivers/fia>");
        }
    }
}