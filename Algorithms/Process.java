package Algorithms;

// import java.util.*;

public class Process {
    int Pid;
    int AT;
    int BT;
    int priority;
    int CT;
    int WT;
    int TAT;

    public Process(int id, int arrivalTime, int burstTime) {
        this.Pid = id;
        this.AT = arrivalTime;
        this.BT = burstTime;
    }

    // for Priority Scheduling algorithm
    public Process(int id, int arrivalTime, int burstTime, int priority) {
        this.Pid = id;
        this.AT = arrivalTime;
        this.BT = burstTime;
        this.priority = priority;
    }

    public static void main(String[] args) {

    }
}
