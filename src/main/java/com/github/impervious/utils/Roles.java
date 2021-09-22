package com.github.impervious.utils;

import com.github.impervious.FIABot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.dv8tion.jda.api.entities.Role;

@RequiredArgsConstructor
public enum Roles {

    PRODRIVER_ID(758712296381612092L),
    PRORESERVE_ID(758719582814601247L),
    DEVDRIVER_ID(781259406695727125L),
    DEVRESERVE_ID(781259577348849694L);

    @Getter
    private final long id;

    public Role getRole() { return FIABot.getInstance().getClient().getRoleById(id); }
}