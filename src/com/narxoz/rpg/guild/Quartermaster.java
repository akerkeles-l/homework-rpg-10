package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {
    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Quartermaster " + getName() + "] Received '" + topic + "' from " + from.getName() + ": " + payload);
        if (topic.equals("order")) {
            System.out.println("[Quartermaster " + getName() + "] Preparing supplies for: " + payload);
        } else if (topic.equals("supplies")) {
            System.out.println("[Quartermaster " + getName() + "] Checking supply stock for: " + payload);
        }
    }
}