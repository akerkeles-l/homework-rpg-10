package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");
        System.out.println("\nThe Adventurers' Guild: War Council");

        Hero hero1 = new Hero("Sir Arthur", 100, 25, 20);
        Hero hero2 = new Hero("Elara Moonshadow", 80, 30, 15);
        Hero hero3 = new Hero("Thorgrim Ironfist", 120, 28, 25);
        
        System.out.println("\nHeroes assembled:");
        System.out.println("  " + hero1);
        System.out.println("  " + hero2);
        System.out.println("  " + hero3);
        
        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Dragon's Peak", QuestPriority.URGENT, 1000, true));
        questLog.add(new Quest("Goblin Ambush", QuestPriority.NORMAL, 150, false));
        questLog.add(new Quest("Cursed Crypt", QuestPriority.HIGH, 500, true));
        questLog.add(new Quest("Lost Relic", QuestPriority.LOW, 75, false));
        questLog.add(new Quest("Dark Ritual", QuestPriority.URGENT, 800, true));
        questLog.add(new Quest("Bandit Camp", QuestPriority.NORMAL, 200, false));
        questLog.add(new Quest("Ancient Temple", QuestPriority.HIGH, 450, false));
        
        System.out.println("\nQuest Log contains " + questLog.size() + " quests");
        
        GuildHall hall = new GuildHall();
        Quartermaster quartermaster = new Quartermaster("Quartermaster Smith", hall);
        Scout scout = new Scout("Scout Willow", hall);
        Healer healer = new Healer("Healer Maria", hall);
        Captain captain = new Captain("Captain Roberts", hall);
        Loremaster loremaster = new Loremaster("Loremaster Aldric", hall);
        
        System.out.println("\nGuild Hall members registered:");
        System.out.println("  " + quartermaster.getName() + " (Quartermaster)");
        System.out.println("  " + scout.getName() + " (Scout)");
        System.out.println("  " + healer.getName() + " (Healer)");
        System.out.println("  " + captain.getName() + " (Captain)");
        System.out.println("  " + loremaster.getName() + " (Loremaster)");
        
        System.out.println("\n--- Guild members sending test messages ---");
        captain.issueOrder("order", "Scout the mountains for dragon activity");
        scout.reportRoute("route", "Mapping the path to Dragon's Peak");
        healer.prepareAid("healing", "Stocking healing potions for the expedition");
        quartermaster.requestSupplies("supplies", "Extra rations and weapons");
        loremaster.shareLore("lore", "Ancient dragon weaknesses and legends");
        
        System.out.println("\n--- Starting Council Engine ---");
        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(java.util.List.of(hero1, hero2, hero3), questLog, hall);
        
        System.out.println("\n=== Final Council Run Result ===");
        System.out.println(result);
        
        System.out.println("\n=== Iterator Pattern Achievements ===");
        System.out.println("✓ Custom QuestIterator interface");
        System.out.println("✓ OrderedQuestIterator (arrival order)");
        System.out.println("✓ ReverseQuestIterator (reverse order)");
        System.out.println("✓ PriorityQuestIterator (filter by priority)");
        System.out.println("✓ RewardSortedQuestIterator (new iterator - Open/Closed proof)");
        System.out.println("✓ QuestLog keeps internal List private");
        
        System.out.println("\n=== Mediator Pattern Achievements ===");
        System.out.println("✓ GuildHall topic-based mediator");
        System.out.println("✓ All colleagues communicate only through mediator");
        System.out.println("✓ No direct colleague references");
        System.out.println("✓ Loremaster added as new colleague - Open/Closed proof");
        
        System.out.println("\n=== Design Patterns Demonstrated ===");
        System.out.println("Iterator Pattern: Traversing QuestLog in multiple ways");
        System.out.println("Mediator Pattern: Decoupled guild communication");
    }
}