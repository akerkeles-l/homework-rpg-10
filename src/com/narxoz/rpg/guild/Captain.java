package com.narxoz.rpg.guild;

public class Captain extends GuildMember {
    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Captain " + getName() + "] Received '" + topic + "' from " + from.getName() + ": " + payload);
        if (topic.equals("order") || topic.equals("command")) {
            System.out.println("[Captain " + getName() + "] Acknowledging order and issuing battle commands: " + payload);
        } else if (topic.equals("route")) {
            System.out.println("[Captain " + getName() + "] Approving route: " + payload);
        } else if (topic.equals("broadcast")) {
            System.out.println("[Captain " + getName() + "] Broadcasting to all troops: " + payload);
        }
    }
}