package com.fightclub.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FightClubDemo {

    public static void main(String[] args) {

        List<FightClubUser> fightClubUserList = new ArrayList<>();

        while (true) {
            System.out.println("Fight Club Options:");
            System.out.println("1: Looking For Fight");
            System.out.println("2: Create New Fighter");
            System.out.println("3: Delete Old Fighter");
            System.out.println("4: Show all Your's Fighters");
            System.out.println("5: Exit from Fight Club");

            System.out.println("");

            System.out.println("What You Want?");
            Scanner scanner = new Scanner(System.in);
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1: {
                    System.out.println("Chose your Fighter:");
                    int fighterNumber = scanner.nextInt();

                    break;
                }
                case 2: {
                    System.out.println("Give Him a Name");
                    String fighterName = scanner.nextLine();

                    break;
                }

                case 3: {
                    System.out.println("Which one doesen't interest You anymore?");
                    int fighterNumber = scanner.nextInt();

                break;
                }

                case 4: {
                    System.out.println("Your Fighters");
                    for (FightClubUser userFighterList : fightClubUserList){
                        System.out.println(userFighterList);
                    }
                    System.out.println("End of List");

                        break;
                }

                case 5:{
                    System.out.println("Remember The Firs Rule ");

                }
            }
        }
    }
}
