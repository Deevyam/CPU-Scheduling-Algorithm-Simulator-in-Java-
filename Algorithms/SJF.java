package Algorithms;

import java.util.*;
// import Algorithms.Process;

public class SJF {
    Scanner sc = new Scanner(System.in);

    public void sjf() {

        System.out.print("Enter the number of process : ");
        int noOfPro = sc.nextInt();

        Process[] process = new Process[noOfPro];
        for (int i = 0; i < noOfPro; i++) {
            System.out.print("Enter the Arrival and Burst time for process " + (i + 1) + " : ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            process[i] = new Process((i + 1), arrivalTime, burstTime);
        }

        int currentTime = 0;
        int completed = 0;
        int totalTAT = 0;
        int totalWT = 0;
        boolean[] isCompleted = new boolean[noOfPro];

        while (completed != noOfPro) {
            int minIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < noOfPro; i++) {
                if (process[i].AT <= currentTime && !isCompleted[i]) {
                    if (process[i].BT < minBurstTime) {
                        minBurstTime = process[i].BT;
                        minIndex = i;
                    } else if (process[i].BT == minBurstTime) { // then check arrival time
                        if (process[i].AT < process[minIndex].AT) {
                            minBurstTime = process[i].BT;
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
                "PID\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : process) {
            System.out.println(
                    p.Pid + "\t" + p.AT + "\t" + p.BT + "\t" + p.CT + "\t" + p.TAT + "\t"
                            + p.WT);
        }

        // System.out.println("The Gantt Chart is : ");
        // for (int i = 0; i < noOfPro; i++) {
        // System.out.print("P" + Pid[i] + " ");
        // }

        System.out.println();
        double ATAT = (double) totalTAT / noOfPro;
        double AWT = (double) totalWT / noOfPro;

        System.out.println("Average Turn Around Time is : " + ATAT + " units");
        System.out.println("Average waiting time is : " + AWT + " units");
    }

    public static void main(String[] args) {

    }
}
