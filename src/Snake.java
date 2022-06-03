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

//Defining snakes starts and ends because there is limits to them.
//Snake class
   public class Snake{
      private int start;
      private int stop;

      public Snake(int start, int stop){
         this.start = start;
         this.stop = stop;
      }

      public int getStart(){
         return start;
      }

      public int getStop(){
         return stop;
      }
   }