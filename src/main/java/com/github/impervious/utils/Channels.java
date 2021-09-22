package com.github.impervious.utils;

import com.github.impervious.FIABot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.dv8tion.jda.api.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {

    /*
    *   HUB CHANNELS
    */

    HUBTEST_ID(373117816817057793L),
    ERRORS_ID(581194394288390154L),

    /*
    *   LNR CHANNELS
    */

    PROGENERAL_ID(758712768211451915L),
    DEVGENERAL_ID(758713446526091374L),

    PROREPORT_ID(758712857763250187L),
    DEVREPORT_ID(758713467653324902L),

    TESTING_ID(788105798743752715L),
    DEVFIA_ID(774504983768072202L),
    PROFIA_ID(774505083600896041L);

    @Getter
    private final long id;

    public TextChannel getChannel() { return FIABot.getInstance().getClient().getTextChannelById(id); }
}