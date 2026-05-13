package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;

public class CouncilEngine {
    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        System.out.println("\n=== War Council Planning Session ===");
        System.out.println("Party members: " + party.stream().map(Hero::getName).reduce((a,b) -> a + ", " + b).orElse("None"));
        
        QuestIterator orderedIterator = questLog.ordered();
        System.out.println("\n--- Traversing quests in arrival order ---");
        while (orderedIterator.hasNext()) {
            Quest quest = orderedIterator.next();
            questsTraversed++;
            System.out.println("\nPlanning for quest: " + quest.getTitle());
            System.out.println("  Priority: " + quest.getPriority() + ", Reward: " + quest.getRewardGold() + " gold, Urgent: " + quest.isUrgent());
            
            if (hall instanceof GuildHall) {
                if (quest.getPriority().ordinal() >= 2) {
                    ((GuildHall) hall).dispatch("order", null, "Prepare for " + quest.getTitle());
                    messagesRouted++;
                    ((GuildHall) hall).dispatch("supplies", null, "Gather resources for " + quest.getTitle());
                    messagesRouted++;
                    ((GuildHall) hall).dispatch("healing", null, "Prepare medical support for " + quest.getTitle());
                    messagesRouted++;
                    ((GuildHall) hall).dispatch("route", null, "Plan path to " + quest.getTitle());
                    messagesRouted++;
                }
                
                if (quest.isUrgent()) {
                    ((GuildHall) hall).dispatch("deploy", null, "Immediate deployment for " + quest.getTitle());
                    messagesRouted++;
                }
            }
        }
        
        membersNotified = messagesRouted * 4;
        
        QuestIterator priorityIterator = questLog.priorityAtLeast(com.narxoz.rpg.quest.QuestPriority.HIGH);
        System.out.println("\n--- High priority quests (priority filter) ---");
        while (priorityIterator.hasNext()) {
            Quest quest = priorityIterator.next();
            System.out.println("  Critical: " + quest.getTitle() + " - Reward: " + quest.getRewardGold() + " gold");
        }
        
        QuestIterator rewardIterator = questLog.rewardSorted();
        System.out.println("\n--- Quests sorted by reward ---");
        while (rewardIterator.hasNext()) {
            Quest quest = rewardIterator.next();
            System.out.println("  " + quest.getTitle() + ": " + quest.getRewardGold() + " gold");
        }
        
        System.out.println("\n=== Council Session Complete ===");
        
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}