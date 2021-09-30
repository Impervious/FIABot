package com.github.impervious.utils;

import com.github.impervious.FIABot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.dv8tion.jda.api.entities.Member;

@RequiredArgsConstructor
public enum FIAMembers {

    VENOHM_ID(289669713787748352L),
    ACCT4LOSS_ID(309112716948275200L),
    XENTRIC_ID(272790899757678593L),
    IMPERVIOUS_ID(73463573900173312L);

    @Getter
    private final long id;

    public Member getMember() { return FIABot.getLNRGuild().getMemberById(id); }
}