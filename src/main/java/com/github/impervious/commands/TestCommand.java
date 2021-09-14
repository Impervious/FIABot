package com.github.impervious.commands;

import com.github.impervious.Main;
import com.github.impervious.utils.Util;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import net.dv8tion.jda.api.entities.Member;

public class TestCommand extends Command {

    public TestCommand() {
        this.name = "test";
        this.aliases = new String[]{"remind"};
    }

    @Override
    protected void execute(CommandEvent event) {
        Member impervious = Main.getMainGuild().retrieveMemberById(73463573900173312L).complete();
        event.getMessage().delete().queue();
        if(event.getAuthor().equals(impervious.getUser())) {
            //TEST CODE HERE :)
            Util.sendEmbed(event.getTextChannel(), "description");
        } else {
            Util.sendMessage(event.getTextChannel(), "<:monkaStab:772354625641906236>");
        }
    }
}