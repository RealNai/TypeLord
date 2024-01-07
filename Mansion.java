import java.util.*;

public class Mansion {

  /*
   * Quick accesses System.out.format(""); Game.sleep(2);
   */

  // new instant
  public static Hero h;
  static Scanner s = new Scanner(System.in);
  public static String colorize(String text, String color) {
    return color + text + ConsoleColors.RESET;
  }

  public static void flushToilet(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();

    boolean hasKey = h.inventory.contains("key 1");

    if (!hasKey) {
      System.out.format("%s: Eww! This is so stink.\n\n", h.name);
      Game.sleep(2);
      System.out.format("Paimon: Ehhh, why are we even here. (＃＞＜)\n\n");
      Game.sleep(2);
      System.out.format("Paimon: Hey, look! There is a %s here.\n\n", Game.keyWord("key"));
      Game.sleep(2);
      System.out.format("Paimon: let's take it.\n\n");
      Game.sleep(2);
      System.out.format("You received a strange key\n\n");
      h.inventory.add("key 1");
      Game.pressEnterToContinue();
      toilet(h,g,G,t,T);
    } else {
      System.out.format("There's nothing left here...");
      Game.sleep(2);
      toilet(h,g,G,t,T);
    }

    // TODO
  }

  public static void shower(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();
    System.out.format("You opened the shower blanket.\n\n");
    Game.sleep(2);
    System.out.format("There's a shiny keyboard stuck vertically in the tub.\n\n");
    Game.sleep(2);
    System.out.format("%s: Yo, what the heck is this?\n\n", h.name);
    Game.sleep(2);
    System.out.format("Paimon: Ehhh, I don't know.\n\n");
    Game.sleep(2);
    System.out.format("%s: Imma take it out!\n\n", h.name);
    Game.sleep(1);
    System.out.format(".");
    Game.sleep(1);
    System.out.format(".");
    Game.sleep(1);
    System.out.format(".\n\n");
    Game.sleep(1);
    System.out.format("You received a Legendary Excaliboard!\n\n");
    Game.sleep(2);
    h.atk = h.atk + 100;
    h.hp_max = h.hp_max + 1000;
    h.hp = h.hp_max;
    System.out.format("THe keyboard's devine power gives you [ATK + 100] [Max Hp + 1000]\n\n");
    Game.pressEnterToContinue();
    toilet(h,g,G,t,T);
  }

  public static void toilet(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();

    System.out.format("You entered the toilet.\n\n");
    Game.sleep(2);
    System.out.format(
        "There's a %s toilet in the corner of the room and %s on the right side. Should we check it out or %s?\n",
        Game.keyWord("flush"), Game.keyWord("shower"), Game.keyWord("leave"));
    Game.sleep(2);

    String choice = Game.whatWillYouDo(h);
    if (choice.equals("flush")) {
      flushToilet(h,g,G,t,T);
    } else if (Game.checkCommands(h, choice) == true) {
      toilet(h,g,G,t,T);
    } else if (choice.equals("shower")) {
      shower(h,g,G,t,T);
    } else if (choice.equals("leave")) {
      lobby(h,g,G,t,T);
    } else {
      toilet(h,g,G,t,T);
    }
  }

  public static void balcony(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();

    boolean hasQuest = h.quest.contains("quest 1");

    if (!hasQuest) {
      System.out.format("You entered the balcony.\n\n");
      Game.sleep(2);
      System.out.format("There's a %s standing at the balcony. Should we talk to him or %s?\n\n", Game.keyWord("man"),
      Game.keyWord("leave"));
      Game.sleep(2);

      String choice = Game.whatWillYouDo(h);
      if (choice.equals("man")) {
        man(h,g,G,t,T);
      } else if (Game.checkCommands(h, choice) == true) {
        toilet(h,g,G,t,T);
      } else if (choice.equals("leave")) {
        lobby(h,g,G,t,T);
      } else {
        balcony(h,g,G,t,T);
      }
    } else {
      System.out.format("You already accepted the quest.");
      Game.sleep(2);
      lobby(h,g,G,t,T);
    } 
  }

  public static void man(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();

    System.out.format("You walked to the man...");
    Game.sleep(2);
    System.out.format("\n\nThe man: So you have come.");
    Game.sleep(2);
    System.out.format("\n\nThe man: There is no time! You have to defeat the %s of this mansion.",Game.keyWord("owner"));
    Game.sleep(2);
    System.out.format("\n\nDo you want to accept the quest?%s\n",Game.keyWord("y/n"));
    String confirmStats = s.nextLine();
    if (confirmStats.equalsIgnoreCase("n")){
      System.out.format("You refused and left the balcony.");
      Game.sleep(2);
      lobby(h,g,G,t,T);
    } else if (confirmStats.equalsIgnoreCase("y")){
      h.quest.add("quest 1");
      System.out.format("You accepted the quest and left the balcony.");
      Game.sleep(2);
      lobby(h,g,G,t,T);
    } else{
      System.out.format("%s Please try again. \n",confirmStats);
      man(h,g,G,t,T);
    }
  }
  
  public static void dungeon(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();
    
    boolean hasItem = h.inventory.contains("key 1");
    boolean hasQuest = h.quest.contains("quest 1");
    String role = h.role;

    if (!hasQuest||!hasItem ) {
      System.out.format("The dungeon is locked!.\n");
      Game.sleep(2);
      lobby(h,g,G,t,T);
    } 
    else if (h.role.equals("programmer")) {
      h.fight(h,t,T);
      if (h.status){
      T.treasure.add("Full-health Potion");
      Collections.shuffle(T.treasure);
      String prize = T.treasure.get(0);
      System.out.format("You received 1 Full-health Potion and 70,000 baht");
      h.baht = h.baht + 70000;
      h.inventory.add(prize);
      Game.pressEnterToContinue();
      }
    } else {
      h.fight(h,g,G);
      if (h.status){
        G.treasure.add("Full-health Potion");
        Collections.shuffle(G.treasure);
        String prize = G.treasure.get(0);
        System.out.format("You received 1 Full-health Potion and 400,000 baht");
        h.baht = h.baht + 400000;
        h.inventory.add(prize);
        Game.pressEnterToContinue();
      }
    }
  }

  // TODO
  public static void lobby(Hero h, Mon g, Hero G, Mon t, Hero T) {
    Game.clearScreen();
    System.out.format("\nYou have entered the lobby of the Mansion\n\n");
    Game.sleep(2);
    System.out.format("There's a %s, %s and %s.\n", Game.keyWord("toilet"), Game.keyWord("balcony"),Game.keyWord("dungeon"));
    Game.sleep(2);

    String choice = Game.whatWillYouDo(h);
    if (choice.equals("toilet")) {
      toilet(h,g,G,t,T);
    } else if (Game.checkCommands(h, choice) == true) {
      lobby(h,g,G,t,T);
    } else if (choice.equals("balcony")) {
      balcony(h,g,G,t,T);
    } else if (choice.equals("dungeon")) {
      dungeon(h,g,G,t,T);
    } else {
      lobby(h,g,G,t,T);
    }
  }

  public static void start(Hero h, Mon g, Hero G, Mon t, Hero T) {
    lobby(h,g,G,t,T);

  }

}