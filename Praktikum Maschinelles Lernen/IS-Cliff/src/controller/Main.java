package controller;

import java.util.Scanner;

import agent.Agent;
import agent.QLearningAgent;
import agent.SarsaAgent;
import enums.Direction;
import exceptions.WorldToSmall;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			Scanner reader = new Scanner(System.in); // Reading from System.in
			
			System.out.println("Enter 'q' for Q-Learnign else SARSA");
			String n = reader.nextLine(); // Scans the next token of the input as an int.
			Agent agent;
			if(n.equals("q")) {
				agent = new QLearningAgent();
			} else {
				agent = new SarsaAgent();
			}
			agent.print();

			boolean exit = false;
			boolean printPolicy = false;
			do {
				n = reader.nextLine(); // Scans the next token of the input as an int.
				int reward = 0;
				switch (n) {
				case "w":
					reward = agent.doMan(Direction.NORTH);
					System.out.println("Reward: " + reward);
					break;
				case "a":
					reward = agent.doMan(Direction.WEST_);
					System.out.println("Reward: " + reward);
					break;
				case "s":
					reward = agent.doMan(Direction.SOUTH);
					System.out.println("Reward: " + reward);
					break;
				case "d":
					reward = agent.doMan(Direction.EAST_);
					System.out.println("Reward: " + reward);
					break;
				case "q":
					reward = agent.doStep();
					System.out.println("Reward: " + reward);
					break;
				case "e":
					reward = agent.episode();
					System.out.println("Reward: " + reward);
					break;
				case "1":
					printPolicy = false;
					break;
				case "2":
					printPolicy = true;
					break;
				case "r":
					for(int i = 0 ; i < 2000; i++) {
						reward += agent.episode();
					}
					reward = reward / 2000;
					System.out.println("Reward: " + reward);
					break;
				case "x":
					int lastReward;
					do {
						lastReward = agent.episode();
					} while(lastReward != 0);
					break;
				case "r50":
					for(int i = 0 ; i < 50; i++) {
						reward += agent.episode();
					}
					reward = reward / 50;
					System.out.println("Reward: " + reward);
					break;
				case "r10":
					for(int i = 0 ; i < 10; i++) {
						reward += agent.episode();
					}
					reward = reward / 10;
					System.out.println("Reward: " + reward);
					break;
				case "t":
					exit = true;
					break;
				default:
					break;
				}
				
				if(printPolicy) {
					agent.printC();
				} else {
					agent.print();
				}
			} while (!exit);
			
		} catch (WorldToSmall e) {
			e.printStackTrace();
		}
	}

}
