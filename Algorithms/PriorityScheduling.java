package Algorithms;

import java.util.*;
// import Algorithms.Process;

// Priority scheduling -> same as that of sjf here, we sort the processes first according to their priority and if the priority is same then the sort according to arrival time Highest priority
// * Lowest no - > highest priority (Non-preemptive scheduling)
public class PriorityScheduling {
    Scanner sc = new Scanner(System.in);

    public void priorityScheduling() {
        System.out.println("Note : Lowest No. -> Highest Priority");
        System.out.print("Enter the number of process : ");
        int noOfPro = sc.nextInt();

        Process[] process = new Process[noOfPro];
        for (int i = 0; i < noOfPro; i++) {
            System.out.print("Enter the Arrival time, Burst time and Priority for process " + (i + 1) + " : ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            int priority = sc.nextInt();
            process[i] = new Process((i + 1), arrivalTime, burstTime, priority);
        }

        int currentTime = 0;
        int completed = 0;
        int totalTAT = 0;
        int totalWT = 0;
        boolean[] isCompleted = new boolean[noOfPro];

        while (completed != noOfPro) {
            int minIndex = -1;
            int highestPriority = Integer.MAX_VALUE;

            for (int i = 0; i < noOfPro; i++) {
                if (process[i].AT <= currentTime && !isCompleted[i]) {
                    if (process[i].priority < highestPriority) {
                        highestPriority = process[i].priority;
                        minIndex = i;
                    } else if (process[i].priority == highestPriority) { // then check arrival time
                        if (process[i].AT < process[minIndex].AT) {
                            highestPriority = process[i].priority;
                            minIndex = i;
                        }
                    }
                }
            }

            if (minIndex == -1) {
                currentTime++; // CPU sits idle
            } else {
                process[minIndex].CT = currentTime + process[minIndex].BT;
                currentTime += process[minIndex].BT;

                process[minIndex].TAT = process[minIndex].CT - process[minIndex].AT;
                totalTAT += process[minIndex].TAT;

                process[minIndex].WT = process[minIndex].TAT - process[minIndex].BT;
                totalWT += process[minIndex].WT;

                isCompleted[minIndex] = true;
                completed++;
            }
        }

        System.out.println(
                "PID\tAT\tBT\tPriority\tCT\tTAT\tWT");
        for (int i = 0; i < noOfPro; i++) {
            System.out.println(
                    process[i].Pid + "\t" + process[i].AT + "\t" + process[i].BT + "\t" + process[i].priority + "\t\t"
                            + process[i].CT + "\t" + process[i].TAT + "\t"
                            + process[i].WT);
        }

        System.out.println();
        double ATAT = (double) totalTAT / noOfPro;
        double AWT = (double) totalWT / noOfPro;

        System.out.println("Average Turn Around Time is : " + ATAT + " units");
        System.out.println("Average waiting time is : " + AWT + " units");
    }

    public static void main(String[] args) {

    }
}
