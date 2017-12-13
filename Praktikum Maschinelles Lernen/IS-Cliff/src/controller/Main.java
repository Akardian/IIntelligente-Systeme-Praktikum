package controller;

import java.util.Scanner;

import agent.Agent;
import environment.Direction;
import exceptions.MoveToFar;
import exceptions.WorldToSmall;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws MoveToFar {
		try {
			Agent agent = new Agent();
			agent.print();

			Scanner reader = new Scanner(System.in); // Reading from System.in
			boolean exit = false;
			boolean printc = false;
			do {
				String n = reader.nextLine(); // Scans the next token of the input as an int.
				switch (n) {
				case "w":
					agent.qLearningMan(Direction.NORTH);
					break;
				case "a":
					agent.qLearningMan(Direction.WEST_);
					break;
				case "s":
					agent.qLearningMan(Direction.SOUTH);
					break;
				case "d":
					agent.qLearningMan(Direction.EAST_);
					break;
				case "q":
					agent.qLearningStep();
					break;
				case "e":
					agent.qLearningEpisode();
					break;
				case "1":
					printc = false;
					break;
				case "2":
					printc = true;
					break;
				case "r":
					for(int i = 0 ; i < 1000; i++) {
						agent.qLearningEpisode();
					}
					break;
				case "t":
					exit = true;
					break;
				default:
					break;
				}
				
				if(printc) {
					agent.printC();
				} else {
					agent.print();
				}
			} while (!exit);
			
		} catch (WorldToSmall e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
