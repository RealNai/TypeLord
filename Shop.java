import java.util.*;

public class Shop{
  public static Hero h;
  public static String colorize(String text, String color){
    return color + text + ConsoleColors.RESET;
  }
  public static void clearScreen() { 
   System.out.print("\033[H\033[2J"); 
   System.out.flush(); 
  }
  public static String price(String word){
    return colorize(word, ConsoleColors.YELLOW_BOLD_BRIGHT);
  }
  public static void sleep(int seconds){
    long start = System.currentTimeMillis();
    while (start >= System.currentTimeMillis() - seconds * 1000);
  }
  public static boolean checkMoney(Hero h, int price){
    if ((h.baht - price) >= 0) {
      return true;
    } else {
      return false;
    }
  }
  
  public static void shop(Hero h){
    clearScreen();
    System.out.format("Seller: Choose whatever you want!\n\n");
    System.out.format("Money: %s\n\n", price(String.format("%d", h.baht)));
    System.out.format("\n(1)Weapon: Razer keyboard [Atk + 20] %s\n",price("-10,000 baht"));
    System.out.format("\n(2)Armour: Hand Gloves [Max Hp + 100] %s\n",price("-2,500 baht"));
    System.out.format("\n(3)Weapon: 4k Monitor [Atk + 25] %s\n",price("-15,000 baht"));
    
    System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
    System.out.format("Type %s to exit.",Game.keyWord("leave"));
    System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

    String choice = Game.whatWillYouDo(h);
    if (choice.equals("1")){
      boolean checkMoney = checkMoney(h, 10000);
      boolean hasItem1 = h.inventory.contains("Razer keyboard");
      if (!hasItem1 && checkMoney){
        h.inventory.add("Razer keyboard");
        h.atk += 20;
        h.baht -= 10000;
        System.out.format("\nRazer Keyboard has been added to your inventory!\n", h.name);
        Game.sleep(1);
        System.out.format("\nYour stats has been updated! Atk:%d\n", h.atk);
        Game.sleep(2);
        shop(h);
      } else if (hasItem1){
          System.out.format("\nOut of stock!");
          Game.sleep(1);
          shop(h);
      } else {
          System.out.format("\n Not enough money!");
          Game.sleep(1);
          shop(h);
      }
    } else if (choice.equals("2")){
        boolean checkMoney = checkMoney(h, 2500);
        boolean hasItem2 = h.inventory.contains("Hand Gloves");
        if (!hasItem2 && checkMoney){
          h.inventory.add("Hand Gloves");
          h.hp_max = h.hp_max + 100;
          h.hp = h.hp_max;
          h.baht -= 2500;
          System.out.format("\nHand Gloves has been added to your inventory!\n", h.name);
          Game.sleep(1);
          System.out.format("\nYour stats has been updated! Hp:%d\n", h.hp);
          Game.sleep(2);
          shop(h);
        } else if (hasItem2){
            System.out.format("\nOut of stock!");
            Game.sleep(1);
            shop(h);
        } else {
            System.out.format("\n Not enough money!");
            Game.sleep(1);
            shop(h);
        }
    } else if (choice.equals("3")){
        boolean checkMoney = checkMoney(h, 15000);
        boolean hasItem3 = h.inventory.contains("4k Monitor");
        if (!hasItem3 && checkMoney){
          h.inventory.add("4k Monitor");
          h.atk += 25;
          h.baht -= 15000;
          System.out.format("\n4k Monitor has been added to your inventory!\n", h.name);
          Game.sleep(1);
          System.out.format("\nYour stats has been updated! Atk:%d\n", h.atk);
          Game.sleep(2);
          shop(h);
        } else if (hasItem3){
            System.out.format("\nOut of stock!");
            Game.sleep(1);
            shop(h);
        } else {
            System.out.format("\n Not enough money!");
            Game.sleep(1);
            shop(h);
        }
    } else if (Game.checkCommands(h, choice)==true) {
        shop(h);
    } else if (choice.equals("leave")) {
        System.out.format("\n\nYou left the Guild with Paimon.\n\n");
    } else{
        shop(h);
    } 
  }

  public static void start(Hero h){
    clearScreen();

    System.out.format("\n\nDo you want to %s or visit a %s\n\n", Game.keyWord("leave"), Game.keyWord("shop"));

    String choice = Game.whatWillYouDo(h);
    if (choice.equals("shop")){
      shop(h);
    } else if (Game.checkCommands(h, choice)==true) {
      start(h);
    } else if (choice.equals("leave")) {
      
    } else{
      start(h);
    } 
  }
}