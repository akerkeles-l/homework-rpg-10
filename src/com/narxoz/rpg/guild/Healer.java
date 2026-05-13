package com.narxoz.rpg.guild;

public class Healer extends GuildMember {
    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Healer " + getName() + "] Received '" + topic + "' from " + from.getName() + ": " + payload);
        if (topic.equals("healing")) {
            System.out.println("[Healer " + getName() + "] Preparing healing potions for: " + payload);
        } else if (topic.equals("aid")) {
            System.out.println("[Healer " + getName() + "] Organizing medical aid: " + payload);
        } else if (topic.equals("casualty")) {
            System.out.println("[Healer " + getName() + "] Tending to wounded: " + payload);
        }
    }
}