package controller;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import agent.Agent;
import exceptions.MoveToFar;
import exceptions.WorldToSmall;

public class Main {

	public static void main(String[] args) throws MoveToFar {
		try {
			Agent agent = new Agent(-1);
			agent.print();

			Scanner reader = new Scanner(System.in);  // Reading from System.in

			int i = 0;
			do {
				//int n = reader.nextInt(); // Scans the next token of the input as an int.
				agent.QLearningStep();
				i++;
			} while (i < 4);
			agent.print();
		} catch (WorldToSmall e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
