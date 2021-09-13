package com.github.impervious.utils;

import com.github.impervious.Main;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.TextChannel;

@RequiredArgsConstructor
public enum Channels {

    ROOM_ID(373117816817057793L),
    TESTING_ID(788105798743752715L),
    DEVFIA_ID(774505083600896041L),
    PROFIA_ID(774504983768072202L);

    @Getter
    private final long id;

    public TextChannel getChannel() { return Main.getMainGuild().getTextChannelById(id); }
}