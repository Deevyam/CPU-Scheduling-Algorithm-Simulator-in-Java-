package Algorithms;

import java.util.*;
// import Algorithms.Process;

public class RoundRobin {
    Scanner sc = new Scanner(System.in);

    public void roundRobin() {
        System.out.print("Enter the number of process : ");
        int noOfPro = sc.nextInt();

        int remainingBT[] = new int[noOfPro];
        Process[] process = new Process[noOfPro];

        for (int i = 0; i < noOfPro; i++) {
            System.out.print("Enter the Arrival and Burst time for process " + (i + 1) + " : ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            process[i] = new Process((i + 1), arrivalTime, burstTime);
            // initially remaining BT is equal to original BT
            remainingBT[i] = process[i].BT;
        }

        System.out.print("Enter the Time Quantum(TQ) (Should be more than 2): ");
        int tq = sc.nextInt();

        int currentTime = 0;
        int totalTAT = 0;
        int totalWT = 0;
        int completed = 0;
        boolean[] isInQueue = new boolean[noOfPro];
        Queue<Process> readyQueue = new LinkedList<>();

        readyQueue.add(process[0]);
        isInQueue[0] = true;

        while (completed != noOfPro) {
            Process currentProcess = readyQueue.poll();
            int proInd = currentProcess.Pid - 1;

            if (remainingBT[proInd] > tq) {
                remainingBT[proInd] -= tq;
                currentTime += tq;

                // Add new processes to the queue that have arrived by the current time
                for (int i = 0; i < noOfPro; i++) {
                    if (!isInQueue[i] && process[i].AT <= currentTime) {
                        readyQueue.add(process[i]);
                        isInQueue[i] = true;
                    }
                }

                // If the process is not finished then re-add it to the queue
                readyQueue.add(currentProcess);
            } else {
                currentTime += remainingBT[proInd];
                remainingBT[proInd] = 0;
                currentProcess.CT = currentTime;
                currentProcess.TAT = currentProcess.CT - currentProcess.AT;
                currentProcess.WT = currentProcess.TAT - currentProcess.BT;

                totalTAT += currentProcess.TAT;
                totalWT += currentProcess.WT;
                completed++;

                // Add new processes to the queue that have arrived by the current time
                for (int i = 0; i < noOfPro; i++) {
                    if (!isInQueue[i] && process[i].AT <= currentTime) {
                        readyQueue.add(process[i]);
                        isInQueue[i] = true;
                    }
                }
            }

            // If the queue is empty, jump to the next arrival time
            if (readyQueue.isEmpty()) {
                for (int i = 0; i < noOfPro; i++) {
                    if (!isInQueue[i]) {
                        readyQueue.add(process[i]);
                        isInQueue[i] = true;
                        currentTime = process[i].AT;
                        break;
                    }
                }
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
