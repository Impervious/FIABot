package com.github.impervious.commands;

import com.github.impervious.FIABot;
import com.github.impervious.utils.Util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Member;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
    }

    @Override
    protected void execute(CommandEvent event) {
        Member impervious = FIABot.getMainGuild().retrieveMemberById(73463573900173312L).complete();
        event.getMessage().delete().queue();
        if(event.getAuthor().equals(impervious.getUser())) {
            //TEST CODE HERE :)
            Util.sendMessage(event.getTextChannel(), "eeeee");
        } else {
            Util.sendMessage(event.getTextChannel(), "<:monkaStab:772354625641906236>");
        }
    }
}