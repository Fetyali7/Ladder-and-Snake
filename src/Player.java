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
//Player class
   public class Player{
      private int id;
      private int position;
      
      //They would start from the square 0.
      public Player(int id){
         this.id = id;
         position = 0;
      }
      
      //Final point is 100. So whoever gets there faster, wins the game.
      public boolean hasWon(){
         return position == 100;
      }

      public int getId(){
         return id;
      }

      public int getPosition(){
         return position;
      }

      public void setPosition(int position){
         this.position = position;
      }
      // if they passed the 100th square, they would go back the same amount squares as they surpassed the 100.
      public void move(int steps){
         if(position + steps >100){
        	 position = 100 - ((position + steps)-100);
         }
        
         else position += steps;
      }
   }