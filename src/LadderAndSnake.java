/*
Name: Ali Fetanat, ID: 40158208
Class: COMP 249 Section
Assignment 1
Due date: February 7th

This program simulates the ladder and snake board game.  It gets the user input for the amount of players, and then getting
them in order in terms of who plays first based on the number they get for their dice flip.  Afterwards, they would play 
by flipping their dice and based on that amount, they would go to forwards.  Moreover, some ladders and snakes have been 
considered to either take them closer to the final destination or bring them back.  This game continues until someone reaches
the 100th square and win the game.  


*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class LadderAndSnake {

   private List<Player> players;
   private List<Ladder> ladders;
   private List<Snake> snakes;

   // Constructor
   public LadderAndSnake(int n){
      this.players = new ArrayList<>();
      this.snakes = new ArrayList<>();
      this.ladders = new ArrayList<>();

      for(int i = 0; i < n; ++i)
          players.add(new Player(i+1));
      
      ladderInit();
      snakeInit();
   }

   // Add Ladder start and end Point based on the picture in the assignment page
   private void ladderInit(){
      ladders.clear();
      ladders.add(new Ladder(1,38));
      ladders.add(new Ladder(4,14));
      ladders.add(new Ladder(9,31));
      ladders.add(new Ladder(21,42));
      ladders.add(new Ladder(28,84));
      ladders.add(new Ladder(36,44));
      ladders.add(new Ladder(51,67));
      ladders.add(new Ladder(71,91));
      ladders.add(new Ladder(80,100));;
      
   }

   // Add Snake head and tail numbers based on the picture in the assignment page
   public void snakeInit(){
      snakes.clear();
      snakes.add(new Snake(16,6));
      snakes.add(new Snake(48,30));
      snakes.add(new Snake(64,60));
      snakes.add(new Snake(79,19));
      snakes.add(new Snake(93,68));
      snakes.add(new Snake(95,24));
      snakes.add(new Snake(97,76));
      snakes.add(new Snake(98,78));
   }

   // function Play which means the Game Loop would start
   public void play(){
      System.out.println("Game is Played by " + players.size() + " players");
      System.out.println("Now deciding which player will start playing;");
      Map<Integer, Player> map = new HashMap<>();
      PlayerOrder(players, map);
      System.out.print("Reached final decision on order of playing:");
      for(Player player: players)
         System.out.print(" Player " + player.getId() + ",");
      System.out.println("");

      boolean flag = false;

      while(!flag){
         for(Player player: players){
            flag = movePlayer(player);
            if(flag){
               System.out.println("Game Over, Player " + player.getId() + " Won!\nThanks for playing!");
               break;
            }
         }
         if(!flag)
            System.out.println("Game not over; flipping again");
      }
   }

   // players play their move
   public boolean movePlayer(Player player){
      int num = flipDice();
      player.move(num);

      if(player.hasWon())
         return true;

      int a = checkOnLadder(player);
      if(a != 0){
         System.out.println("Player " + player.getId() + " got dice value of " + num + "; gone to square " + player.getPosition() + " then up to square " + a);
         player.setPosition(a);
         return player.hasWon();
      }
      a = checkOnSnake(player);
      if(a != 0){
         System.out.println("Player " + player.getId() + " got dice value of " + num + "; gone to square " + player.getPosition() + " then down to square " + a);
         player.setPosition(a);
         return false;
      }
      System.out.println("Player " + player.getId() + " got dice value of " + num + "; now in square " + player.getPosition());
      return false;
   }

   // checks if on ladder start point in order to transfer them to a closer square to the final point
   public int checkOnLadder(Player player){
      for(Ladder ladder: ladders)
         if(ladder.getStart() == player.getPosition())
            return ladder.getStop();
      return 0;
   }

   // checks if on snake head point in order to transfer them to a farther square to the final point
   public int checkOnSnake(Player player){
      for(Snake snake: snakes)
         if(snake.getStart() == player.getPosition())
            return snake.getStop();
      return 0;
   }

   // sets the player order for playing the game
   public void PlayerOrder(List<Player> players, Map<Integer, Player> playerNumbers){
      for(Player player: players){

         int num = assignOrder(player);

         if(playerNumbers.containsKey(num)){
            Player samePlayer = playerNumbers.get(num);
            System.out.println("A tie was achieved between Player " + samePlayer.getId() + " and Player " + player.getId() + ". Attempting to break the tie");
            List<Player> newPlayers = new ArrayList<>();
            newPlayers.add(samePlayer);
            newPlayers.add(player);
            PlayerOrder(newPlayers, playerNumbers);
         }
         else
            playerNumbers.put(num, player);
      }

      TreeMap<Integer, Player> tm = new TreeMap<>(playerNumbers);
      players.clear();
      for (int key : tm.keySet()) {
         players.add(playerNumbers.get(key));
      }
      Collections.reverse(players);
   }

   // Assigning orders to the players
   public int assignOrder(Player player){
      int num = flipDice();
      System.out.println("Player " + player.getId() + " got a dice value of " + num);

      return num;
   }

   // Flipping a dice and limiting the number between 1 and 6
   public int flipDice(){
      int rnd = 0;
      while(rnd == 0)
         rnd = new Random().nextInt(7);
      return rnd;
   }

   // Defining a method in order to add ladders to the game
   public void addLadder(Ladder ladder){
      ladders.add(ladder);
   }

   // Defining a method in order to add players to the game
   public void addPlayer(Player player){
      players.add(player);
   }

   // Defining a method in order to add snakes to the game
   public void addSnake(Snake snake){
      snakes.add(snake);
   }

   // Defining a method for removing players from the list
   public boolean removePlayer(Player player){
      if(players.contains(player)){
         players.remove(player);
         return true;
      }

      return false;
   }

   // Defining a method for removing ladders from the list
   public boolean removeLadder(Ladder ladder){
      if(ladders.contains(ladder)){
         ladders.remove(ladder);
         return true;
      }

      return false;
   }

   // Defining a method for removing snakes from the list
   public boolean removeSnake(Snake snake){
      if(snakes.contains(snake)){
         snakes.remove(snake);
         return true;
      }

      return false;
   }

   public List<Player> getPlayers(){
      return players;
   }

   public List<Ladder> getLadders(){
      return ladders;
   }

   public List<Snake> getSnakes(){
      return snakes;
   }
}