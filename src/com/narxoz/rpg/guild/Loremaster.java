package com.narxoz.rpg.guild;

public class Loremaster extends GuildMember {
    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Loremaster " + getName() + "] Received '" + topic + "' from " + from.getName() + ": " + payload);
        if (topic.equals("lore") || topic.equals("history")) {
            System.out.println("[Loremaster " + getName() + "] Recalling ancient knowledge about: " + payload);
        } else if (topic.equals("curse")) {
            System.out.println("[Loremaster " + getName() + "] Researching curse breaking rituals for: " + payload);
        }
    }
}