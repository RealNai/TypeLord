import java.util.*;

class Hero {
  
  public static Mon m;
  public String name;
  public int hp;
  public int hp_max;
  public String role; 
  public boolean status;
  public int atk;
  public int baht;
  public static int SlimeHp;
  public static boolean warn = false;
  public ArrayList<String> inventory = new ArrayList<String>();
  public ArrayList<String> quest = new ArrayList<String>();
  public ArrayList<String> treasure = new ArrayList<String>();
  public ArrayList<String> attacks = new ArrayList<String>();

  public ArrayList<String> slimeSet = new ArrayList<String>();
  public ArrayList<String> slimeSetProgrammer = new ArrayList<String>();
  public ArrayList<String> slimeSetChallenger = new ArrayList<String>();
  
  public ArrayList<String> godSet = new ArrayList<String>();
  public ArrayList<String> godSetChallenger = new ArrayList<String>();
  
  public ArrayList<String> teacherSetProgrammer = new ArrayList<String>();

  static Random random = new Random();

  public static String warning(String word){
    return colorize(word, ConsoleColors.RED);
  }
  public static String rB(String word){
    return colorize(word, ConsoleColors.RED_BOLD);
  }
  public static String r(String word){
    return colorize(word, ConsoleColors.YELLOW_BOLD_BRIGHT);
  }
  static Scanner s = new Scanner(System.in);
  public static String colorize(String text, String color) {
    return color + text + ConsoleColors.RESET;
  }

  public String getRandomAttack(Hero k){
    int index = random.nextInt(3);

    return k.attacks.get(index);
  }
  public void monTurn(Hero h, Mon m, Hero k){
    h.hp = h.hp - m.atk;
    System.out.format("\n%s use %s and deal %d to you.",m.name, getRandomAttack(k), m.atk);
  }

  public void checkDefeat(Hero h, Mon m, Hero k){
    if (h.hp <= 0){
      System.out.format("\nYou were defeated! X-X\n");
      h.status = false;
    } else if (m.hp <= 0){
      System.out.format("\nYou defeated %s! >w<\n", m.name);
      m.status = false;
    } else {
      monTurn(h,m,k);
    }
  }
  public void checkDefeatMon(Hero h, Mon m, Hero k){
    if (h.status && m.status) {
      if (h.hp <= 0){
        System.out.format("\nYou were defeated! X-X\n");
      } else if (m.hp <= 0){
        System.out.format("\nYou defeated %s! >w<\n", m.name);
      } else {
        fight(h,m,k);
      }
    }  
  }
  public String checkRole(Hero h, Mon m){
    int index = random.nextInt(4);
    if (m.name.equals("Blue Slime")){
      if (h.role.equals("programmer")){
        return h.slimeSetProgrammer.get(index);
      } else if (h.role.equals("challenger")){
        return h.slimeSetChallenger.get(index);
      } else {
        return h.slimeSet.get(index);
      }
    } else if (m.name.equals("Kratos")) {
      if (h.role.equals("challenger")){
        return h.godSetChallenger.get(index);
      } else {
        return h.godSet.get(index);
      }
    } else {
      return h.teacherSetProgrammer.get(index);
    }
  }

  public void fightMenu(Hero h, Mon m, Hero k){
    Game.clearScreen();
    System.out.format("You encounter %s\n",m.name);
    if (m.name.equals("Blue Slime")){
      Art.slime();
    } else if (m.name.equals("Kratos")){
      Art.kratos();
    } else{
      Art.tim();
    }
    System.out.format("%s : %d/%d HP",m.name,m.hp,m.hp_max);
    System.out.format("\n%s : %d/%d HP",h.name,h.hp,h.hp_max);
    System.out.format("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n %s     %s                     _________\n  %s %s    %s %s                    BACK <b> |\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*",rB("___________"),rB("__________"),r("LIGHT <1>"),rB("|"),r("HEAVY <2>"),rB("|"));
    String choice = Game.whatWillYouDo(h);
    if (choice.equals("light")||choice.equals("1")||choice.equals("l")) {
      if (Game.type(30,checkRole(h,m))){
        int dmg = h.atk + random.nextInt(20);
        if (m.weakTo.equals("Light Attacks")){
          dmg = h.atk*2 + random.nextInt(20);
        }
        m.hp = m.hp - dmg;
        System.out.format("\nYou kicked %s\n",m.name);
        Game.sleep(1);
        System.out.format("You dealt %d damage to %s",dmg,m.name);
        Game.sleep(2);
        checkDefeat(h,m,k);
        Game.sleep(2);
        checkDefeatMon(h,m,k);
      } else {
        m.hp = m.hp - h.atk/2;
        System.out.format("\nYou slightly kicked %s\n",m.name);
        Game.sleep(1);
        System.out.format("You dealt %d damage to %s",h.atk/2,m.name);
        Game.sleep(2);
        checkDefeat(h,m,k);
        Game.sleep(2);
        checkDefeatMon(h,m,k);
      }
    } else if (choice.equals("heavy")||choice.equals("2")||choice.equals("h")){
      if (Game.type(30,checkRole(h,m))){
        int dmg = h.atk + random.nextInt(20);
        if (m.weakTo.equals("Heavy Attacks")){
          dmg = h.atk*2 + random.nextInt(20);
        }
        m.hp = m.hp - dmg;
        System.out.format("\nYou heavily kicked %s\n",m.name);
        Game.sleep(1);
        System.out.format("You dealt %d damage to %s",dmg,m.name);
        Game.sleep(2);
        checkDefeat(h,m,k);
        Game.sleep(2);
        checkDefeatMon(h,m,k);
      } else {
        m.hp = m.hp - h.atk/2;
        System.out.format("\nYou slightly kicked %s\n",m.name);
        Game.sleep(1);
        System.out.format("You dealt %d damage to %s",h.atk/2,m.name);
        Game.sleep(2);
        checkDefeat(h,m,k);
        Game.sleep(2);
        checkDefeatMon(h,m,k);
      }
    } else if (Game.checkCommands(h, choice) == true) {
      fightMenu(h,m,k);
    } else if (choice.equals("back")||choice.equals("b")) {
      fight(h,m,k);
    } else {
      fightMenu(h,m,k);
    }
  } 

  public void fight(Hero h, Mon m,Hero k){
    Game.clearScreen();
    System.out.format("You encounter %s\n",m.name);
    if (m.name.equals("Blue Slime")){
      Art.slime();
    } else if (m.name.equals("Kratos")){
      Art.kratos();
    } else{
      Art.tim();
    }
    System.out.format("%s : %d/%d HP",m.name,m.hp,m.hp_max);
    System.out.format("\n%s : %d/%d HP",h.name,h.hp,h.hp_max);
    System.out.format("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n %s     __________    _____________    ________\n  %s %s    CHECK <c> |   INVENTORY [i]|   RUN <r> |\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*",rB("___________"),r("FIGHT <f>"),rB("|"));
    if (h.warn == false){
      System.out.format(warning("\n*Every action will cost you one turn. Except 'Commands'")); 
      h.warn = true;
    }
    String choice = Game.whatWillYouDo(h);

    if (choice.equals("fight")||choice.equals("f")) {
      fightMenu(h,m,k);
    } else if (choice.equals("check")||choice.equals("c")) {
      System.out.format(m.data,rB(m.weakTo + " "));
      Game.pressEnterToContinue();
      monTurn(h,m,k);
      Game.sleep(2);
      checkDefeatMon(h,m,k);
    } else if (choice.equals("inventory")||choice.equals("i")) {
      int countA = 0;
      int countB = 0;
      int countC = 0;
      int countD = 0;
      for (int i = 0; i < inventory.size() ; i++) {
        if (inventory.get(i).equals("Full-health Potion")){
          countA += 1;
        } else if (inventory.get(i).equals("")){
          countB += 1;
        } else if (inventory.get(i).equals("")){
          countC += 1;
        } 
      }
      System.out.format("----------Bag-----------");
      int num = 1;
      String bag1 = "";
      String bag2 = "";
      String bag3 = "";
      String bag4 = "";
      if (countA == 1){
        System.out.format("\n%d) Full-health Potion",num);
        if (num == 1){
          bag1 = "Full-health Potion";
        }
        num += 1;
      } else if (countA > 1){
        System.out.format("\n%d) Full-health Potion x %d",num,countA);
        if (num == 1){
          bag1 = "Full-health Potion";
        }
        num += 1;
      }
      //add to-be-counting items



      System.out.format("\n------------------------\n");
      System.out.println(rB("(type 'b' to go back)\n"));
      choice = Game.whatWillYouDo(h);
      if (choice.equals("1")) {
        System.out.format("You healed %d hp\n",h.hp_max-h.hp);
        h.hp = h.hp_max;
        for (int i = 0; i < inventory.size() ; i++) {
          if (inventory.get(i).equals("Full-health Potion")){
            inventory.remove(i);
          }
        }
        checkDefeat(h,m,k);
      } else {
        fight(h,m,k);
      }
      Game.pressEnterToContinue();
      checkDefeatMon(h,m,k);
    } else if (choice.equals("run")||choice.equals("r")) {
      System.out.println("You can't run");
      monTurn(h,m,k);
      Game.pressEnterToContinue();
      checkDefeatMon(h,m,k);
    } else if (Game.checkCommands(h, choice) == true) {
      fight(h,m,k);
    } else {
      fight(h,m,k);
    }

  }















}