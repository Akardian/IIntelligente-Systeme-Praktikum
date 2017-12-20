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
			if (n.equals("q")) {
				agent = new QLearningAgent();
			} else {
				agent = new SarsaAgent();
			}
			agent.printC();

			boolean exit = false;
			boolean printPolicy = true;
			do {
				n = reader.nextLine(); // Scans the next token of the input as an int.
				int reward = 0;
				switch (n) {
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
					for (int i = 0; i < 2000; i++) {
						reward += agent.episode();
					}
					reward = reward / 2000;
					System.out.println("Reward: " + reward);
					break;
				case "x":
					int batchsize = 200;
					int epoche = 0;
					boolean changed = false;
					do {
						System.out.println("Epoche: " + epoche);
						Direction[][] policy = agent.cloneTileDirection();
						for (int i = 0; i < batchsize; i++) {
							agent.episode();
						}
						changed = agent.checkChange(policy);
						epoche++;
					} while (changed);
					break;
				case "t":
					exit = true;
					break;
				default:
					break;
				}

				if (printPolicy) {
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
