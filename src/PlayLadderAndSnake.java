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
import java.util.Scanner;

//Driver class
/*Doing the do and while loop to get the required input from the user which has to be either 2,3 or 4 players.
It keeps asking the user to put the acceptable input 4 times and after that, it terminates the program. 
Also I defined a counter to tell the user how many bad attempt they have already made.
*/
public class PlayLadderAndSnake{
   public static void main(String[] args) {
	   Scanner myKey =new Scanner(System.in);
	   int l, counter=0;
	  
	   do {
	   System.out.println("Please enter the number of players: (Please enter a number between 2 and 4 inclusively)");
	   l=myKey.nextInt();
	   if(l==2 || l==3 || l==4) {
	   LadderAndSnake ladderAndSnake = new LadderAndSnake(l);
       ladderAndSnake.play();
	   }
	   else {
		   System.out.println("This is your " + (counter+1)+ " bad attempt.  The input is invalid! Please try again.");
	   }
	   if(counter==2) {
		  System.out.println("This is your last chance!");
	   }
	   counter+=1;
	   
	   if(counter==4) {
		   System.out.print("You have exhausted all your attempts. Program will terminate!");
		   System.exit(0);
	   }
   }while(l!=2 && l!=3 && l!=4);
	   myKey.close();
}
}