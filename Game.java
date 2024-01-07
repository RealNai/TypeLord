import java.util.*;

class Game{
  //Create new random function
  static Random random = new Random();
  public static Hero h;
  //clearScreen function
  public static void clearScreen() { 
   System.out.print("\033[H\033[2J"); 
   System.out.flush(); 
 }
 public static void sleep(int seconds){
   long start = System.currentTimeMillis();
   while (start >= System.currentTimeMillis() - seconds * 1000);
 }
 public static boolean type(int seconds, String writeQuestion){
   Scanner s = new Scanner(System.in);
   System.out.println("Type: <"+ writeQuestion + "> in " + seconds +"seconds");
   long start = System.currentTimeMillis();
   String writeAnswer = s.nextLine();
   if (!writeQuestion.equals(writeAnswer)){
    System.out.println("\nYou typed somthing wrong");
    sleep(1);
    return false;
   }
   else if (System.currentTimeMillis() - start > seconds * 1000){
    System.out.println("\nYou typed too slow");
    sleep(1);
    return false;
   }
   else {
    System.out.println("Perfect!");
    sleep(1);
    return true;
   }
   /*Tutorials
    type(5, "Phong");
   */
 }
  //colorize function
  public static String colorize(String text, String color){
    return color + text + ConsoleColors.RESET;
    /* Tutorials
    -> printing
    System.out.println(colorize("BACON", ConsoleColors.RED + ConsoleColors.BLUE_BACKGROUND_BRIGHT));
    
    -> formating
    System.out.format("",colorize("book", ConsoleColors.GREEN));
    
    */
  }
  public static String box(String text){
    String bar1 = ("*-*-*-*[Unique Skills]*-*-*-*\n");
    String bar2 = ("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
    return bar1 + text + "\n" + bar2;
  }
  
  public static int getInt(Scanner s){
    int ans = s.nextInt();
    s.nextLine();
    
    return ans;
  }
  
  public static int getRandomRange(int min, int max){
    return random.nextInt(max - min) + min;
  }
  
  public static void pressEnterToContinue(){
    System.out.println("Press ENTER to continue");
    s.nextLine();
  }
  
  static Scanner s = new Scanner(System.in);
  //Game.s.nextLine();
  
  public static void die(){
    System.out.println("You died");
  }

  public static void role(Hero hero1){
    clearScreen();
    
    h = hero1;
    System.out.format("\nStaff: %s",colorize("Select your profession.\n", ConsoleColors.WHITE_BOLD));
    System.out.format("1. %s\n", colorize("Typist",ConsoleColors.YELLOW_BOLD_BRIGHT));
    System.out.println("\tUnique Skill: Earn more income after killing enemies.");
    //System.out.format("2. %s\n", colorize("Speedrunner",ConsoleColors.CYAN_BOLD_BRIGHT));
    //System.out.println("\tUnique Skill: The faster you typed, the higher damage dealt.");
    //System.out.format("3. %s", colorize("Gacha-ist", ConsoleColors.GREEN_BOLD_BRIGHT));
    //System.out.println(" [Luck + 10]");
    //System.out.println("\tUnique Skill: Random 1 Unique Skill.");
    System.out.format("2. %s", colorize("Challenger", ConsoleColors.RED_BOLD_BRIGHT));
    System.out.println(" [Atk + 50]");
    System.out.println("\tUnique Skill: Type many different language (EN-TH-JP).");
    System.out.format("3. %s\n", colorize("Programmer", ConsoleColors.BLACK_BOLD_BRIGHT));
    System.out.println("\tUnique Skill: Type lines of code to kill enemies.");
    //System.out.format("?. %s\n", colorize("M*@d#r.r", ConsoleColors.WHITE_BOLD_BRIGHT));
    //System.out.println("\tUnique Skill: Unknown");
    System.out.print("\nEnter the number:");
    String choice = s.nextLine();
    clearScreen();

    if (choice.equals("1")){
      h.role = "typist";
      System.out.format("You are now a %s.",colorize("Typist",ConsoleColors.YELLOW_BOLD_BRIGHT));
    } else if (choice.equals("2")){
      h.role = "challenger";
      h.atk = h.atk + 50;
      System.out.format("You are now a %s.",colorize("Challenger",ConsoleColors.RED_BOLD_BRIGHT));
    } else if (choice.equals("3")){
      h.role = "programmer";
      System.out.format("You are now a %s.",colorize("Programmer", ConsoleColors.BLACK_BOLD_BRIGHT));
    //} else if (choice.equals("4")){
    //  h.role = "challenger";
    //  System.out.format("You are now a %s.",colorize("Challenger", ConsoleColors.RED_BOLD_BRIGHT));
    //} else if (choice.equals("5")){
    //  h.role = "programmer";
    //  System.out.format("You are now a %s.",colorize("Programmer", ConsoleColors.BLACK_BOLD_BRIGHT));
    //} else if (choice.equals("1978")){
    //  h.role = "murderer";
    //  System.out.format("You are now a %s.", colorize("Murderer", ConsoleColors.WHITE_BOLD_BRIGHT));
    } else {
      role(h);
    }
    
  }

  public static void chooseStats(Hero h){
    System.out.format("\nStaff: This is your stats. Are you statisfied? %s\n", colorize("y/n",ConsoleColors.YELLOW_BRIGHT));
    h.hp = random.nextInt(100) + 100;
    h.hp_max = h.hp;
    System.out.println("*-*-*-*-*-*-*-*-*-*-*-");
    System.out.format("HP : %s\n",colorize(String.format("%d",h.hp), ConsoleColors.GREEN_BRIGHT));
    h.atk = random.nextInt(5) + 5;
    System.out.format("ATK: %s\n\n",colorize(String.format("%d", h.atk), ConsoleColors.RED_BRIGHT));
    h.baht = random.nextInt(3500) + 12500; 
    System.out.format("Money: %s baht\n",colorize(String.format("%d",h.baht), ConsoleColors.YELLOW_BRIGHT));
    System.out.println("*-*-*-*-*-*-*-*-*-*-*-");
    
    String confirmStats = s.nextLine();
    if (confirmStats.equalsIgnoreCase("n")){
      chooseStats(h);
    } else if (confirmStats.equalsIgnoreCase("y")){
      
    } else{
      System.out.format("%s is not valid answer. Please try again. \n",confirmStats);
      chooseStats(h);
    }
  }
    
  public static void setting(){
    //delete Paimon
    //No text-display
  }
  
  public static String keyWord(String word){
    return colorize(word, ConsoleColors.GREEN_BOLD_BRIGHT);
  }
  
  public static String whatWillYouDo(Hero h){
    System.out.format("\n%s, what will you do?\n\n", h.name);
    
    return s.nextLine().toLowerCase();
  }
  public static String whatWillYouDoTutorial(Hero h){
    System.out.format("\nStaff: Please Try typing the commands\n\n");
    
    return s.nextLine().toLowerCase();
  }
  //list of commands
  public static boolean checkCommands(Hero h, String choice){
    if (choice.equals("stats")){
      System.out.format("---------------------- \n");
      System.out.format("Name %s \n",h.name);
      System.out.format("Role: %s \n",h.role);
      System.out.format("Hp %d/%d \n",h.hp,h.hp_max);
      System.out.format("Atk %d \n",h.atk);
      System.out.format("---------------------- \n");
      pressEnterToContinue();
      clearScreen();
      return true;
    } else if (choice.equals("help")){
      System.out.format("\nList of Commands\n-*-*-*-*-*-*-*-*-*-*-\n'stats'\n-*-*-*-*-*-*-*-*-*-*-\n");
      pressEnterToContinue();
      clearScreen();
      //checkCommandsDefault(h);
      return true;
    }
    else {
      return false;
    }
  }
  public static void tryCommands(Hero h){
    System.out.format("\n\nDo you want keep trying commmands? %s", keyWord("y/n"));
    String confirmStats = s.nextLine();
    if (confirmStats.equalsIgnoreCase("n")){

    } else if (confirmStats.equalsIgnoreCase("y")){
      loopAskTutorial(h);
    } else{
      System.out.format("%s is not valid answer. Please try again. \n",confirmStats);
      tryCommands(h);
    }
  }

  public static void loopAskTutorial(Hero h){
    
    if (checkCommands(h, whatWillYouDoTutorial(h)) == true){
      tryCommands(h);
    } else {
      System.out.format("\nNo such command, type 'help' to see list of commmands");
      sleep(3);
      clearScreen();
      loopAskTutorial(h);
    }
  }
  
  
  //Start
  public static void start(){
    //Settings
    clearScreen();
    Hero h = new Hero();
    Hero Slime = new Hero();
    Mon slime = new Mon();
///Place for testing functions///////////////////////////////////////////////////////////////////////////
    h.status = true;
    h.hp = 100;
    h.hp_max = 100;
    h.atk = 10;
    h.baht = 100;
    h.inventory.add("Full-health Potion");
    h.inventory.add("Full-health Potion");
    h.inventory.add("Full-health Potion");
    h.inventory.add("Full-health Potion");
    
    slime.status = true;
    slime.name = "Blue Slime";
    slime.hp = 150;
    slime.hp_max = 150;
    slime.atk = 10;
    slime.baht = 50; 
    slime.weakTo = "Light Attacks"; 
    Slime.attacks.add("Jumpy Dumpy"); 
    Slime.attacks.add("Rolling Ocean"); 
    Slime.attacks.add("Blue Reflection"); 
    slime.data = "An oceanic blue slime typically founded in computer science class dungeon. Is weak to %s";

    Hero Teacher = new Hero();
    Mon teacher = new Mon();
    teacher.status = true;
    teacher.name = "Mr. Tim";
    teacher.hp = 1000;
    teacher.hp_max = 1000;
    teacher.atk = 250;
    teacher.baht = 70000; 
    teacher.weakTo = "Heavy Attacks"; 
    Teacher.attacks.add("Bacon slash"); 
    Teacher.attacks.add("Bacon Rage"); 
    Teacher.attacks.add("Bacon Ultimate Attack"); 
    teacher.data = "Mr. Tim is the head of World Bacon Organization, typically roaming in computer science class dungeon. Is weak to %s";
    
    Hero God = new Hero();
    Mon god = new Mon();
    god.status = true;
    god.name = "Kratos";
    god.hp = 1000;
    god.hp_max = 1000;
    god.atk = 250;
    god.baht = 400000; 
    god.weakTo = "Heavy Attacks"; 
    God.attacks.add("Spartan Rage"); 
    God.attacks.add("Bare Hand"); 
    God.attacks.add("Blades of Chaos:"); 
    god.data = "Kratos, God of war, the son of Zeus. Is weak to %s";
    
    //public ArrayList<String> slimeSet = new ArrayList<String>();
    h.slimeSet.add("hi");
    h.slimeSet.add("hello");
    h.slimeSet.add("die");
    h.slimeSet.add("bye");
    //public ArrayList<String> slimeSetProgrammer = new ArrayList<String>();
    h.slimeSetProgrammer.add("i=+1;");    
    h.slimeSetProgrammer.add("Hero.java");    
    h.slimeSetProgrammer.add("clearScreen();");
    h.slimeSetProgrammer.add("public static void start()");
    //public ArrayList<String> slimeSetChallenger = new ArrayList<String>();
    h.slimeSetChallenger.add("Hello!");   
    h.slimeSetChallenger.add("おはよう!");    
    h.slimeSetChallenger.add("สวัสดีครัย");
    h.slimeSetChallenger.add("ありがとう!");
    //public ArrayList<String> godSet = new ArrayList<String>();
    h.godSet.add("An oceanic blue slime typically founded in computer science classes.");    
    h.godSet.add("To catch a fly from the ass of Zeus is not worth my time.");    
    h.godSet.add("Boy!");
    h.godSet.add("In the direction of deer.");
    //public ArrayList<String> godSetChallenger = new ArrayList<String>();
    h.godSetChallenger.add("An oceanic blue slime typically founded in computer science classes.");
    h.godSetChallenger.add("おはようございます");    
    h.godSetChallenger.add("พิมเร็วๆหน่อยมีเวลานิดเดียวนะเห้ย!");
    h.godSetChallenger.add("ありがとうございます");
    //public ArrayList<String> teacherSetProgrammer = new ArrayList<String>();
    h.teacherSetProgrammer.add("public ArrayList<String> godSet = new ArrayList<String>();");    
    h.teacherSetProgrammer.add("String prize = Slime.treasure.get(0);");    
    h.teacherSetProgrammer.add("static Scanner s = new Scanner(System.in);");
    h.teacherSetProgrammer.add("return colorize(word, ConsoleColors.GREEN_BOLD_BRIGHT);");


    





/////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Game Starts
    Art.concept();
    Art.title();
    System.out.println("\n\nPress ENTER to start the game...");
    s.nextLine();
    
    //Prolouge
    clearScreen();
    Art.beginning();
    System.out.format("You are a villager who desired to become a TypeLord, one who mastered their typing skills in every aspect.\n");
    sleep(2);
    System.out.format("\nIn order to fulfill your wish, you decided to go on an adventure.\n\n");
    sleep(2);
    System.out.println("This is the journey of... \n\nEnter your name:");
    h.name = s.nextLine();
    System.out.format("\nThis is the journey of %s, the TypeLord\n",h.name);
    System.out.format("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
    sleep(3);
    
    //Paimon
    clearScreen();
    Art.paimon();
    System.out.format("On your way to a TypeGuild. You found Paimon, a tiny fairy.\n\n");
    sleep(2);
    System.out.format("After she heard your ambition. She volunteered to accompany you throughout your journey.\n\n");
    sleep(2);
    System.out.format("Paimon: Let's go!!!\n\n");
    sleep(2);
    System.out.format("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n\n");
    pressEnterToContinue();
    
    //Location: Guild
    clearScreen();
    System.out.format("%s%s\n\n",colorize("Location: ", ConsoleColors.WHITE_BOLD),colorize("TypeGuild", ConsoleColors.GREEN));
    sleep(1);
    System.out.format("Staff: Ohayo! Welcome new adventurer.\n\n");
    sleep(1);
    System.out.format("Staff: We will be ramdomizing stats for you in a second.\n");
    sleep(3);
    
    //Random Stats
    chooseStats(h);
    
    //role
    clearScreen();
    System.out.format("Staff: Now that your stats are completed. You need to choose your profession in order to become a unique Typer.\n\n");
    sleep(3);
    System.out.format("Staff: Each profession will have a different <%s>. Choose the profession that suit your interest.\n\n", colorize("Unique Skill", ConsoleColors.YELLOW_BRIGHT));
    pressEnterToContinue();
    role(h);
    System.out.format("\n\nStaff: Your stats are now updated.\n\n");
    sleep(2);
    System.out.format("Staff: You can check your stats by typing 'stats' or check the list of commands by typing 'help'.\n\n");
    loopAskTutorial(h);
    
    //shop
    Shop.start(h);

    //leave guild
    
    clearScreen();
    System.out.println("You left the Guild.\n");
    sleep(1);
    System.out.println("Paimon: Let's test your typing skill.\n");
    sleep(2);
    System.out.println("Paimon: Try fighting a blue slime right there.\n");
    pressEnterToContinue();
    //fight

    h.fight(h,slime,Slime);

    if (h.status){
      Slime.treasure.add("Full-health Potion");
      Slime.treasure.add("Full-health Potion");
      Slime.treasure.add("Full-health Potion");
      Slime.treasure.add("Full-health Potion");
      Collections.shuffle(Slime.treasure);
      String prize = Slime.treasure.get(0);
      h.baht = h.baht + 10000;
      System.out.format("You received 1 Full-health Potion and 10,000 baht\n");
      h.inventory.add(prize);
      pressEnterToContinue();
      clearScreen();
      System.out.format("\n\nPaimon: Look there's a gigantic mansion there!");
      sleep(3);
      System.out.format("\n\nPaimon: Hmmm, wonder why its here in the forest.");
      sleep(3);
      System.out.format("\n\nPaimon: Let's go check it out!\n\n");
      sleep(3);
      pressEnterToContinue();
      Mansion.start(h,god,God,teacher,Teacher);
    }
    
    
    System.out.println("Game Over");
    //ask name
    //choose role
    //roll stats
  }
  
  
  
  
  
  
  
  
  
  
}