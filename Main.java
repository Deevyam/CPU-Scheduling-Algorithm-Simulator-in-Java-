import java.util.*;

import Algorithms.FCFS;
import Algorithms.SJF;
import Algorithms.PriorityScheduling;
import Algorithms.RoundRobin;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------------------------------------");
        System.out.println("\t***** CPU Scheduling Algorithms *****\t");
        System.out.println("-------------------------------------------------------");

        boolean x = true;

        while (x) {
            System.out.println("Algorithms Available : ");
            System.out.println(
                    "1. First Come First Serve (FCFS) \n2. Shortest Job First (SJF) \n3. Priority Scheduling (Non-Premptive) \n4. Round Robin \n5. Exit");

            System.out.println("-------------------------------------------------------");
            System.out.print("Enter the no. for algo that you want to implement : ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    FCFS proFcfs = new FCFS();
                    proFcfs.fcfs();
                    System.out.println("-------------------------------------------------------");
                    break;

                case 2:
                    SJF proSjf = new SJF();
                    proSjf.sjf();
                    System.out.println("-------------------------------------------------------");
                    break;

                case 3:
                    PriorityScheduling proPS = new PriorityScheduling();
                    proPS.priorityScheduling();
                    System.out.println("-------------------------------------------------------");
                    break;

                case 4:
                    RoundRobin proRR = new RoundRobin();
                    proRR.roundRobin();
                    System.out.println("-------------------------------------------------------");
                    break;

                case 5:
                    System.out.println("\t !! Thank You for Respounding !! \t");
                    x = false;
                    System.out.println("-------------------------------------------------------");
                    break;
            }
        }
    }
}