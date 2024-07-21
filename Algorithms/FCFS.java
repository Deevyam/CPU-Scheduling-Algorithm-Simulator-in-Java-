package Algorithms;

import java.util.*;
// import Algorithms.Process;

public class FCFS {
    Scanner sc = new Scanner(System.in);

    public void fcfs() {
        System.out.print("Enter the number of process : ");
        int noOfPro = sc.nextInt();

        Process[] process = new Process[noOfPro];
        for (int i = 0; i < noOfPro; i++) {
            System.out.print("Enter the Arrival and Burst time for process " + (i + 1) + " : ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            process[i] = new Process((i + 1), arrivalTime, burstTime);
        }

        // first sort the processes according to their arrival time -> using bubble sort
        int temp;
        for (int i = 0; i < noOfPro; i++) {
            for (int j = i + 1; j < noOfPro; j++) {
                if (process[i].AT > process[j].AT) {
                    temp = process[i].AT;
                    process[i].AT = process[j].AT;
                    process[j].AT = temp;

                    temp = process[i].Pid;
                    process[i].Pid = process[j].Pid;
                    process[j].Pid = temp;

                    temp = process[i].BT;
                    process[i].BT = process[j].BT;
                    process[j].BT = temp;
                }
            }
        }

        // for 1st process
        int currentTime = 0;
        int totalTAT = 0;
        int totalWT = 0;
        for (int i = 0; i < noOfPro; i++) {
            if (currentTime < process[i].AT) {
                currentTime = process[i].AT; // CPU sit idle
            }

            process[i].CT = currentTime + process[i].BT;
            currentTime = process[i].CT;
            process[i].TAT = process[i].CT - process[i].AT;
            totalTAT += process[i].TAT;
            process[i].WT = process[i].TAT - process[i].BT;
            totalWT += process[i].WT;
        }

        System.out.println(
                "PID\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : process) {
            System.out.println(
                    p.Pid + "\t" + p.AT + "\t" + p.BT + "\t" + p.CT + "\t" + p.TAT + "\t"
                            + p.WT);
        }

        System.out.println("The Gantt Chart is : ");
        for (Process p : process) {
            System.out.print("P" + p.Pid + " ");
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
