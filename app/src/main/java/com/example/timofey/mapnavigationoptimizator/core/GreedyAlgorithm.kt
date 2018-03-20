package com.example.timofey.mapnavigationoptimizator.core

import com.example.timofey.mapnavigationoptimizator.database.Point
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Timofey on 15.03.2018.
 */
class GreedyAlgorithm(routingMatrix: RoutingMatrix, type: CalculationType) : RoutingAlgorithm(routingMatrix, type) {

    private var stack = Stack<Int>()

    override fun execute() : IntArray {

        var arr = ArrayList<Int>()
        routingMatrix.visited[1] = 0
        stack.push(1)
        var min = Int.MAX_VALUE
        var isMinimum = false
        var element = 0
        var i = 0
        var dst = 0

        while (!stack.empty()) {
            element = stack.peek()
            i = 0
            min = Int.MAX_VALUE

            while (i < routingMatrix.size) {
                if (routingMatrix.timeArray[element][i] > 1 && routingMatrix.visited[i] == 0) {
                    if (min > routingMatrix.timeArray[element][i]) {
                        min = routingMatrix.timeArray[element][i];
                        dst = i
                        isMinimum = true
                    }
                }
                i++
            }
            if (isMinimum) {
                routingMatrix.visited[dst] = 1
                stack.push(dst)
                arr.add(dst)
                isMinimum = false
                continue
            }
            stack.pop()
        }
        return arr.toIntArray()
    }
}



//TODO Переписать на архитектуру
//import java.util.InputMismatchException;
//import java.util.Scanner;
//import java.util.Stack;
//
//public class TSPNearestNeighbour
//{
//    private int numberOfNodes;
//    private Stack<Integer> stack;
//
//    public TSPNearestNeighbour()
//    {
//        stack = new Stack<Integer>();
//    }
//
//    public void tsp(int adjacencyMatrix[][])
//    {
//        numberOfNodes = adjacencyMatrix[1].length - 1;
//        int[] visited = new int[numberOfNodes + 1];
//        visited[1] = 1;
//        stack.push(1);
//        int element, dst = 0, i;
//        int min = Integer.MAX_VALUE;
//        boolean minFlag = false;
//        System.out.print(1 + "\t");
//
//        while (!stack.isEmpty())
//        {
//            element = stack.peek();
//            i = 1;
//            min = Integer.MAX_VALUE;
//            while (i <= numberOfNodes)
//            {
//                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
//                {
//                    if (min > adjacencyMatrix[element][i])
//                    {
//                        min = adjacencyMatrix[element][i];
//                        dst = i;
//                        minFlag = true;
//                    }
//                }
//                i++;
//            }
//            if (minFlag)
//            {
//                visited[dst] = 1;
//                stack.push(dst);
//                System.out.print(dst + "\t");
//                minFlag = false;
//                continue;
//            }
//            stack.pop();
//        }
//    }
//
//    public static void main(String... arg)
//    {
//        int number_of_nodes;
//        Scanner scanner = null;
//        try
//        {
//            System.out.println("Enter the number of nodes in the graph");
//            scanner = new Scanner(System.in);
//            number_of_nodes = scanner.nextInt();
//            int adjacency_matrix[][] = new int[number_of_nodes + 1][number_of_nodes + 1];
//            System.out.println("Enter the adjacency matrix");
//            for (int i = 1; i <= number_of_nodes; i++)
//            {
//                for (int j = 1; j <= number_of_nodes; j++)
//                {
//                    adjacency_matrix[i][j] = scanner.nextInt();
//                }
//            }
//            for (int i = 1; i <= number_of_nodes; i++)
//            {
//                for (int j = 1; j <= number_of_nodes; j++)
//                {
//                    if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
//                    {
//                        adjacency_matrix[j][i] = 1;
//                    }
//                }
//            }
//            System.out.println("the citys are visited as follows");
//            TSPNearestNeighbour tspNearestNeighbour = new TSPNearestNeighbour();
//            tspNearestNeighbour.tsp(adjacency_matrix);
//        } catch (InputMismatchException inputMismatch)
//        {
//            System.out.println("Wrong Input format");
//        }
//        scanner.close();
//    }
//}









//TODO или сразу в котлине
//import java.util.InputMismatchException
//import java.util.Scanner
//import java.util.Stack
//
//class TSPNearestNeighbour {
//    private var numberOfNodes: Int = 0
//    private val stack: Stack<Int>
//
//    init {
//        stack = Stack()
//    }
//
//    fun tsp(adjacencyMatrix: Array<IntArray>) {
//        numberOfNodes = adjacencyMatrix[1].size - 1
//        val visited = IntArray(numberOfNodes + 1)
//        visited[1] = 1
//        stack.push(1)
//        var element: Int
//        var dst = 0
//        var i: Int
//        var min = Integer.MAX_VALUE
//        var minFlag = false
//        print(1.toString() + "\t")
//
//        while (!stack.isEmpty()) {
//            element = stack.peek()
//            i = 1
//            min = Integer.MAX_VALUE
//            while (i <= numberOfNodes) {
//                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
//                    if (min > adjacencyMatrix[element][i]) {
//                        min = adjacencyMatrix[element][i]
//                        dst = i
//                        minFlag = true
//                    }
//                }
//                i++
//            }
//            if (minFlag) {
//                visited[dst] = 1
//                stack.push(dst)
//                print(dst.toString() + "\t")
//                minFlag = false
//                continue
//            }
//            stack.pop()
//        }
//    }
//
//    companion object {
//
//        @JvmStatic
//        fun main(arg: Array<String>) {
//            val number_of_nodes: Int
//            var scanner: Scanner? = null
//            try {
//                println("Enter the number of nodes in the graph")
//                scanner = Scanner(System.`in`)
//                number_of_nodes = scanner.nextInt()
//                val adjacency_matrix = Array(number_of_nodes + 1) { IntArray(number_of_nodes + 1) }
//                println("Enter the adjacency matrix")
//                for (i in 1..number_of_nodes) {
//                    for (j in 1..number_of_nodes) {
//                        adjacency_matrix[i][j] = scanner.nextInt()
//                    }
//                }
//                for (i in 1..number_of_nodes) {
//                    for (j in 1..number_of_nodes) {
//                        if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0) {
//                            adjacency_matrix[j][i] = 1
//                        }
//                    }
//                }
//                println("the citys are visited as follows")
//                val tspNearestNeighbour = TSPNearestNeighbour()
//                tspNearestNeighbour.tsp(adjacency_matrix)
//            } catch (inputMismatch: InputMismatchException) {
//                println("Wrong Input format")
//            }
//
//            scanner!!.close()
//        }
//    }
//}