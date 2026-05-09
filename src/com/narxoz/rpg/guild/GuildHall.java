package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        if (member instanceof Quartermaster) {
            addSubscriber("supplies", member);
            addSubscriber("order", member);
        } else if (member instanceof Scout) {
            addSubscriber("route", member);
            addSubscriber("recon", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
            addSubscriber("aid", member);
            addSubscriber("casualty", member);
        } else if (member instanceof Captain) {
            addSubscriber("order", member);
            addSubscriber("command", member);
            addSubscriber("deploy", member);
        }
        addSubscriber("broadcast", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> subscribers = subscribersFor(topic);
        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}