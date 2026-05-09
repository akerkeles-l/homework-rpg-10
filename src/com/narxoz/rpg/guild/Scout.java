package com.narxoz.rpg.guild;

public class Scout extends GuildMember {
    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Scout " + getName() + "] Received '" + topic + "' from " + from.getName() + ": " + payload);
        if (topic.equals("route")) {
            System.out.println("[Scout " + getName() + "] Mapping route for: " + payload);
        } else if (topic.equals("recon")) {
            System.out.println("[Scout " + getName() + "] Gathering intelligence: " + payload);
        } else if (topic.equals("deploy")) {
            System.out.println("[Scout " + getName() + "] Scouting ahead for deployment: " + payload);
        }
    }
}