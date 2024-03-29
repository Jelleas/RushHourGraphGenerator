import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import databaseLink.ClusterLinker;
import databaseLink.Link;

import library.ClusterLibrary;
import library.Library;
import board.*;

public final class Generator extends Applet {
	public static Board getHardestBoard() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard1() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, true, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard2() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, true, false, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, true, false, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {false, true, true, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getBoard3() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, true, true, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, true, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, false, false, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, false, false, true, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, true, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, true, false, false, false});
		
		return new Board(rows, columns);
	}
	
	public static Board getExampleBoard1() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		for (int i = 0; i < Board.lineSize; i++) {
			rows[i] = Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
			columns[i] = Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		}
		
		rows[2] = Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		columns[0] = Library.lineLibrary.getLine(new boolean[] {false, true, false, false, false, false});
		return new Board(rows, columns);
	}
	
	public static Board getExampleBoard2() {
		Line[] rows = new Line[Board.lineSize];
		Line[] columns = new Line[Board.lineSize];
		
		rows[0] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, true, true, false});
		rows[1] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, true, true, false});
		rows[2] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, true, false});
		rows[3] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, true, false, false});
		rows[4] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		rows[5] 	= Library.lineLibrary.getLine(new boolean[] {true, true, false, false, false, false});
		
		columns[0] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[1] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[2] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, false, false});
		columns[3] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[4] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, false, true, false});
		columns[5] 	= Library.lineLibrary.getLine(new boolean[] {false, false, false, true, true, false});
		
		return new Board(rows, columns);
	}
	
	public static void speedTestReachableBoards(Board board, long iterations) {
		ArrayList<Board> reachableBoards = null;
		
		long start = System.currentTimeMillis();
		for (long i = 0; i < iterations; i++) 
			reachableBoards = board.getReachableBoards();
		long end = System.currentTimeMillis();
		
		board.print();
		for (Board reachableBoard : reachableBoards)
			reachableBoard.print();
		
		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec: " + (end - start));
	}
	
	public static void speedTestClusterExpand(Board board, long iterations) {
		Cluster cluster = new Cluster(board);
		//cluster.expand();
		//ArrayList<Board> solutions = cluster.getSolutions();
		ArrayList<Board> solutions = ClusterLibrary.getAllSolutions(board);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			cluster = new Cluster(solutions);
			cluster.expand();
		}
		long end = System.currentTimeMillis();

		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec:           " + (end - start));
		System.out.println("Size of cluster:                  " + cluster.size());
		System.out.println("Max distance in cluster:          " + cluster.getMaxDistance());
		System.out.println("Number of solutions in cluster:   " + cluster.getSolutions().size());
		System.out.println("Distance to original board:       " + cluster.getDistanceOf(board));
		System.out.println("Average branch factor of cluster: " + cluster.getAverageBranchFactor());
		
		/*System.out.println();
		System.out.println("\n============= ORIGINAL BOARD =============\n");
		board.prettyPrint();
		System.out.println();
		System.out.println("==========================================");
		
		System.out.println();
		System.out.println("============ PATH TO SOLUTION ============");
		System.out.println();
		for (Board boardOnPath : cluster.getPathToSolution(board)) {
			boardOnPath.prettyPrint();
			System.out.println();
		}
		System.out.println("==========================================");
		
		System.out.println();
		System.out.println("== BOARDS AT MAX DISTANCE FROM SOLUTION ==");
		System.out.println();
		for (Board boardAtMaxDistance : cluster.getBoardsAtMaxDistance()) {
			boardAtMaxDistance.prettyPrint();
			System.out.println();
		}
		System.out.println("=========================================="); */
	}
	
	public static void speedTestSolve(Board board, int iterations) {
		Cluster cluster = null;
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			cluster = new Cluster(board);
			cluster.solve();
		}
		long end = System.currentTimeMillis();

		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken to solve in millisec:  " + (end - start));
		System.out.println("Found solution at depth:          " + cluster.getMaxDistance());
		System.out.println("Number of nodes searched:         " + cluster.size());
	}
	
	public static void speedTestFindSolutions(Board board, int iterations) {
		ArrayList<Board> solutions = null;
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++)
			solutions = ClusterLibrary.getAllSolutions(board);
		long end = System.currentTimeMillis();
		
		System.out.println("Number of iterations              " + iterations);
		System.out.println("Time taken in millisec:           " + (end - start));
		System.out.println("Number of solutions found:        " + solutions.size());
	}
	
	public static void testPickRandom(Link link, int numClusters) {
		//List<Cluster> clusters = link.clusterLink.getWhere("size > 90000 LIMIT 5");
		List<Cluster> clusters = link.clusterLink.getRandom(numClusters, true);
		
		for (Cluster cluster : clusters) {
			System.out.println("Size:         " + cluster.size());
			System.out.println("MaxDistance:  " + cluster.getMaxDistance());
			System.out.println("NumSolutions: " + cluster.getNumSolutions());
			System.out.println();
		}
		
		System.out.println(link.clusterLink.getAverage("maxDistance"));
		//Board board = Generator.getHardestBoard();
		//Cluster cluster = new Cluster(ClusterLibrary.getAllSolutions(new Cluster(board)));
		//cluster.expand();
		//link.clusterLink.add(cluster);
	}
	
	public static int translateDistance(List<Board> path) {
		int distance = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			Board board1 = path.get(i);
			Board board2 = path.get(i + 1);
			Line line1 = null;
			Line line2 = null;
			
			for (int j = 0; j < Board.lineSize; j++) {
				if (board1.getRows()[j] != board2.getRows()[j]) {
					line1 = board1.getRows()[j];
					line2 = board2.getRows()[j];
				}
				else if (board1.getColumns()[j] != board2.getColumns()[j]) {
					line1 = board1.getColumns()[j];
					line2 = board2.getColumns()[j];
				}
			}

			ArrayList<Integer> vehicleLocs1 = new ArrayList<Integer>();
			ArrayList<Integer> vehicleLocs2 = new ArrayList<Integer>();
			boolean foundVehicle1 = false;
			boolean foundVehicle2 = false;
			for (int j = 0; j < Board.lineSize; j++) {
				if (line1.line[j] && !foundVehicle1) {
					foundVehicle1 = true;
					vehicleLocs1.add(j);
				}
				else if (!line1.line[j])
					foundVehicle1 = false;
				
				if (line2.line[j] && !foundVehicle2) {
					foundVehicle2 = true;
					vehicleLocs2.add(j);
				}
				else if (!line2.line[j])
					foundVehicle2 = false;
			}
			
			for (int j = 0; j < vehicleLocs1.size(); j++)
				distance += Math.abs(vehicleLocs1.get(j) - vehicleLocs2.get(j));
		}
		
		return distance;
	}
	
	public static ClusterLinker getClusterLink() {
		return Library.link.clusterLink;
	}
	
	public void init() {
		main(null);
	}

	public static void writeCountResults(String fileName, List<String> whereClauses) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			for (int i = 1; i <= whereClauses.size(); i++) {
				long count = Library.link.clusterLink.getCountWhere(whereClauses.get(i - 1));
				bw.write(i + " " + count + "\n");
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeGraphvizGraph(String fileName, Cluster cluster) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			Set<Board> hardestBoards = new HashSet<Board>(cluster.getBoardsAtMaxDistance());
			List<Board> boards = cluster.getBoards();
			
			Map<Board, Set<Board>> boardToVisitedReachables = new HashMap<Board, Set<Board>>();
			Map<Board, Integer> boardToId = new HashMap<Board, Integer>();
			for (int i = 0; i < boards.size(); i++) {
				boardToId.put(boards.get(i), i);
				boardToVisitedReachables.put(boards.get(i), new HashSet<Board>());
			}
			
			bw.write("graph MyGraph {\n");
			for (Board board : boards) {
				if (board.isSolution())
					bw.write("Board" + boardToId.get(board) + " [label=\"Board" + boardToId.get(board) + "\", style=\"filled\", color=\"black\", fillcolor=\"green\"];\n");
				else if (hardestBoards.contains(board))
					bw.write("Board" + boardToId.get(board) + " [label=\"Board" + boardToId.get(board) + "\", style=\"filled\", color=\"black\", fillcolor=\"red\"];\n");
				else
					bw.write("Board" + boardToId.get(board) + " [label=\"Board" + boardToId.get(board) + "\", style=\"filled\", color=\"black\", fillcolor=\"white\"];\n");
				
				for (Board reachableBoard : board.getReachableBoards())
					if (!boardToVisitedReachables.get(board).contains(reachableBoard)) {
						boardToVisitedReachables.get(board).add(reachableBoard);
						boardToVisitedReachables.get(reachableBoard).add(board);
						bw.write("Board" + boardToId.get(board) + " -- Board" + boardToId.get(reachableBoard) + ";\n");
					}
			}
			bw.write("};");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeClusterSizeExcludingSubClusters(String fileName, String whereClause) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			List<Cluster> clusters = Library.link.clusterLink.getWhere(whereClause);

			for (int i = 0, l = clusters.size(); i < l; i++) {
				Cluster cluster = clusters.get(i);
				List<Board> hardestBoards = cluster.getBoardsAtMaxDistance();
				
				for (int j = 0; j < hardestBoards.size(); j++) {
					Board hardestBoard = hardestBoards.get(j);
					Cluster actualCluster = new Cluster(hardestBoard);
					actualCluster.expand();
					bw.write(i + " " + actualCluster.size() + "\n");
					
					for (int k = hardestBoards.size() - 1; k >= 0; k--)
						if (hardestBoards.get(k) != hardestBoard && actualCluster.contains(hardestBoards.get(k)))
							hardestBoards.remove(k);
				}
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeClusterSizeIncludingSubclusters(String fileName, String whereClause) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			List<Long> maxDistances = Library.link.clusterLink.getNumbersWhere(whereClause, "size");
			
			for (int i = 0, l = maxDistances.size(); i < l; i++)
				bw.write(i + " " + maxDistances.get(i) + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeRandomClusterSizes(String fileName, String whereClause, int limit) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			List<Long> sizes = Library.link.clusterLink.getRandomSizesWhere(whereClause, limit);
			
			for (int i = 0, l = sizes.size(); i < l; i++)
				bw.write(i + " " + sizes.get(i) + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeHistogramDistanceInSizes(String fileName, int nBins) {
		long maxSize = Library.link.clusterLink.getMax("size");
		double binSize = maxSize / (double)nBins;
		long[] bins = new long[nBins];
		
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			for (int i = 1; i < bins.length; i++) {
				long sum = Library.link.clusterLink.getSumWhere("size > " + Math.round(binSize * (i - 1)) + " AND size <= " + Math.round(binSize * i), "maxDistance");
				long count = Library.link.clusterLink.getCountWhere("size > " + Math.round(binSize * (i - 1)) + " AND size <= " + Math.round(binSize * i));
				bw.write(binSize * i + " " + sum + " " + count + "\n");
			}
			
			long sum = Library.link.clusterLink.getSumWhere("size > " + Math.round(binSize * (bins.length - 1)) + " AND size <= " + maxSize, "maxDistance");
			long count = Library.link.clusterLink.getCountWhere("size > " + Math.round(binSize * (bins.length - 1)) + " AND size <= " + maxSize);
			bw.write(maxSize + " " + sum + " " + count);
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeVehiclesOnRowsOverVehiclesOnColumnsRatio(String fileName, int numBoardsPerDistance) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxDistance = (int)Library.link.clusterLink.getMax("maxDistance");
			for (int i = 0; i <= maxDistance; i++) {
				List<Cluster> clusters = Library.link.clusterLink.getRandomWhere("`maxDistance` = " + i, numBoardsPerDistance, false);
				
				for (Cluster cluster : clusters) {
					double ratio = cluster.getNumberOfVehiclesOnRows() / (double)cluster.getNumberOfVehiclesOnColumns();
					if (!Double.isInfinite(ratio))
						bw.write(i + " " + ratio + " " + cluster.getId() + "\n");
				}
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumberOfVehicles(String fileName) {
		try {
			File file = new File(fileName);
			if (!file.exists())
				file.createNewFile();
			
			long start = System.currentTimeMillis();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxId = (int)Library.link.clusterLink.getMax("id");
			for (int id = 0; id <= maxId; id++) {
				if (id % 100000 == 0)
					System.out.println(id + " Time Taken: " + (System.currentTimeMillis() - start));
				
				Cluster cluster = Library.link.clusterLink.getWhere("id = " + id, false, false).get(0);
				long maxDistance = Library.link.clusterLink.getNumbersWhere("id  = " + id, "maxDistance").get(0);
				bw.write(id + " " + maxDistance + " " + cluster.getNumberOfVehicles() + "\n");
			}
				
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeAverageDistancePerNumberOfVehicles(String fileName, int numBoardsPerDistance) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxDistance = (int)Library.link.clusterLink.getMax("maxDistance");
			for (int i = 0; i <= maxDistance; i++) {
				System.out.println(i);
				List<Cluster> clusters = Library.link.clusterLink.getRandomWhere("`maxDistance` = " + i, numBoardsPerDistance, false);
				
				for (int j = clusters.size() - 1; j >= 0; j--) {
					Cluster cluster = clusters.get(j);
					clusters.remove(j);
					cluster.expand();
					bw.write(i + " " + cluster.getAverageDistance() + " " + cluster.getNumberOfVehicles() + " " + cluster.getId() + "\n");
				}
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumberOfCarsOverNumberOfTrucksRatio(String fileName, int numBoardsPerDistance) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxDistance = (int)Library.link.clusterLink.getMax("maxDistance");
			for (int i = 0; i <= maxDistance; i++) {
				List<Cluster> clusters = Library.link.clusterLink.getRandomWhere("`maxDistance` = " + i, numBoardsPerDistance, false);
				
				for (Cluster cluster : clusters)
					if (cluster.getNumberOfTrucks() != 0)
						bw.write(i + " " + (cluster.getNumberOfCars() / (double)cluster.getNumberOfTrucks()) + " " + cluster.getId() + "\n");
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumBoardsPerDistance(String fileName) {
		long maxId = Library.link.clusterLink.getMax("id");
		int maxDistance = (int)(long)Library.link.clusterLink.getMax("maxDistance");
		long[] numBoardsPerDistance = new long[maxDistance + 1];
		long start = System.currentTimeMillis();
		
		for (long id = 1; id <= maxId; id++) {
			Cluster cluster = Library.link.clusterLink.getWhere("id = " + id).get(0);
			int[] numBoardsPerDistanceCluster = cluster.getNumBoardsPerDistance();
			
			for (int i = 0; i < numBoardsPerDistanceCluster.length; i++)
				numBoardsPerDistance[i] += numBoardsPerDistanceCluster[i];
			
			if (id % 100000 == 0)
				System.out.println(id + " Time Taken:" + (System.currentTimeMillis() - start));
		}
		
		System.out.println("Time Taken:" + (System.currentTimeMillis() - start));
		
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			for (int i = 0; i < numBoardsPerDistance.length; i++)
				bw.write(i + " " + numBoardsPerDistance[i] + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void searchMinMaxErrorInClusters(String fileName, int hardnessBorder) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			List<Cluster> clusters = Library.link.clusterLink.getWhere("maxDistance >= " + hardnessBorder + " AND size > 800 AND size < 1000", false);
			clusters.get(0).expand();
			Cluster easiestCluster = clusters.get(0);
			double easiestError = easiestCluster.calcSquaredErrorForSizePerDistanceFromAverage() / easiestCluster.size();
			Cluster hardestCluster = clusters.get(0);
			double hardestError = hardestCluster.calcSquaredErrorForSizePerDistanceFromAverage() / hardestCluster.size();
			
			while (!clusters.isEmpty()) {
				Cluster cluster = clusters.get(clusters.size() - 1);
				clusters.remove(clusters.size() - 1);
				cluster.expand();
				double error = cluster.calcSquaredErrorForSizePerDistanceFromAverage() / cluster.size();
				
				if (error < easiestError) {
					easiestError = error;
					easiestCluster = cluster;
				}
				if (error > hardestError) {
					hardestError = error;
					hardestCluster = cluster;
				}
			}
			
			System.out.print(easiestCluster.getId() + " " + easiestError + " : " + hardestCluster.getId() + " " + hardestError);
			
			easiestCluster = Library.link.clusterLink.getWhere("id = 14444975").get(0);
			hardestCluster = Library.link.clusterLink.getWhere("id = 22043261").get(0);
			
			for (int i = 0, l = easiestCluster.getMaxDistance(); i <= l; i++)
				bw.write(i + " " + easiestCluster.getBoardsAtDistance(i).size() / (double)easiestCluster.size() + "\n");
			
			for (int i = 0, l = hardestCluster.getMaxDistance(); i <= l; i++)
				bw.write(i + " " + hardestCluster.getBoardsAtDistance(i).size() / (double)hardestCluster.size() + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void searchMinMaxRatioInClusters(String fileName, int hardnessBorder) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			List<Cluster> clusters = Library.link.clusterLink.getWhere("maxDistance >= " + hardnessBorder + " AND size > 800 AND size < 1000", false);
			clusters.get(0).expand();
			Cluster easiestCluster = clusters.get(0);
			double easiestRatio = easiestCluster.calcHardnessRatio(hardnessBorder / 2);
			Cluster hardestCluster = clusters.get(0);
			double hardestRatio = hardestCluster.calcHardnessRatio(hardnessBorder / 2);
			
			while (!clusters.isEmpty()) {
				Cluster cluster = clusters.get(clusters.size() - 1);
				clusters.remove(clusters.size() - 1);
				cluster.expand();
				double ratio = cluster.calcHardnessRatio(hardnessBorder / 2);
				
				if (ratio < easiestRatio) {
					easiestRatio = ratio;
					easiestCluster = cluster;
				}
				if (ratio > hardestRatio) {
					hardestRatio = ratio;
					hardestCluster = cluster;
				}
			}
			
			System.out.print(easiestCluster.getId() + " " + easiestRatio + " : " + hardestCluster.getId() + " " + hardestRatio);
			
			for (int i = 0, l = easiestCluster.getMaxDistance(); i <= l; i++)
				bw.write(i + " " + easiestCluster.getBoardsAtDistance(i).size() / (double)easiestCluster.size() + "\n");
			
			for (int i = 0, l = hardestCluster.getMaxDistance(); i <= l; i++)
				bw.write(i + " " + hardestCluster.getBoardsAtDistance(i).size() / (double)hardestCluster.size() + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumConfigurationsForBiggestAndOtherSubclusters(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			long start = System.currentTimeMillis();
			
			int maxId = (int)Library.link.clusterLink.getMax("id");
			for (int id = 1; id <= maxId; id++) {
				List<Long> subclusterSizes = Library.link.subclusterLink.getNumbersWhere("cluster = " + id, "size");
				long maxSubclusterSize = Collections.max(subclusterSizes);
				
				long sumOtherSubclusterSize = 0;
				for (long subclusterSize : subclusterSizes)
					sumOtherSubclusterSize += subclusterSize;
				sumOtherSubclusterSize -= maxSubclusterSize;
				
				bw.write(id + " " + maxSubclusterSize + " " + sumOtherSubclusterSize + "\n");
				
				if (id % 100000 == 0)
					System.out.println(id + " / " + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumSolutionsForBiggestAndOtherSolvableSubclusters(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			long start = System.currentTimeMillis();
			
			int maxId = (int)Library.link.clusterLink.getMax("id");
			for (int id = 1; id <= maxId; id++) {
				List<Long> subclusterNumSolutions = Library.link.subclusterLink.getNumbersWhere("cluster = " + id +
						" AND maxDistance < " + Cluster.unsolvableDistance, "numSolutions");
				long maxSubclusterNumSolution = Collections.max(subclusterNumSolutions);
				
				long sumOtherSubclusterNumSolutions = 0;
				for (long subclusterNumSolution : subclusterNumSolutions)
					sumOtherSubclusterNumSolutions += subclusterNumSolution;
				sumOtherSubclusterNumSolutions -= maxSubclusterNumSolution;
				
				bw.write(id + " " + maxSubclusterNumSolution + " " + sumOtherSubclusterNumSolutions + "\n");
				
				if (id % 100000 == 0)
					System.out.println(id + " / " + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeAverageSolutionsDistance(String fileName, int numClustersPerDistance) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxMaxDistance = (int)Library.link.clusterLink.getMax("maxDistance");
			long start = System.currentTimeMillis();
			for (int maxDistance = 0; maxDistance <= maxMaxDistance; maxDistance++) {
				System.out.println("At maxDistance: " + maxDistance + " Time Taken: " + (System.currentTimeMillis() - start));
				
				List<Cluster> clusters = Library.link.subclusterLink.getRandomWhere("maxDistance = " + maxDistance, numClustersPerDistance, false);
				while (!clusters.isEmpty()) {
					System.out.println("At:" + clusters.size() + " Time Taken: " + (System.currentTimeMillis() - start));
					Cluster cluster = clusters.get(clusters.size() - 1);
					clusters.remove(clusters.size() - 1);
					
					cluster.expand();
					List<Board> allSolutions = cluster.getSolutions();
					Set<Board> allSolutionsSet = new HashSet<Board>(allSolutions);
	
					long distance = 0;
					for (Board solution : allSolutions) {
						Cluster otherCluster = new Cluster(solution);
						otherCluster.solveTillBoardsFound(allSolutionsSet);
						
						for (Board otherSolution : allSolutions)
							distance += otherCluster.getDistanceOf(otherSolution);
					}
					
					bw.write(cluster.getId() + " " + maxDistance + " " + distance + " " + allSolutions.size() + " " + cluster.size() + "\n");
					bw.flush();
				}
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeRandomSubclusterOnRandomSolution(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			long start = System.currentTimeMillis();
			
			Random random = new Random();
			
			int maxId = (int)Library.link.clusterLink.getMax("id");
			for (int id = 1; id <= maxId; id++) {
				List<Long> subclusterSizes = Library.link.subclusterLink.getNumbersWhere("cluster = " + id + " ORDER BY id", "size");
				List<Long> subclusterNumSolutions = Library.link.subclusterLink.getNumbersWhere("cluster = " + id + " ORDER BY id", "numSolutions");
				
				int sumSubclusterNumSolutions = 0;
				for (long subclusterNumSolution : subclusterNumSolutions)
					sumSubclusterNumSolutions += subclusterNumSolution;
				
				int randomSolution = random.nextInt(sumSubclusterNumSolutions);
				sumSubclusterNumSolutions = 0;
				long sizePicked = 0;
				for (int i = 0; i < subclusterNumSolutions.size(); i++) {
					sumSubclusterNumSolutions += subclusterNumSolutions.get(i);
					if (randomSolution < sumSubclusterNumSolutions) {
						sizePicked = subclusterSizes.get(i);
						break;
					}
				}
				
				bw.write(id + " " + sizePicked + "\n");
				
				if (id % 100000 == 0)
					System.out.println(id + " / " + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
			}
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumberOfSubclustersPerCluster(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			long start = System.currentTimeMillis();
			
			Map<Integer, Integer> numberOfSubclustersToCount = new HashMap<Integer, Integer>();
			for (int id = 1, maxId = (int)Library.link.clusterLink.getMax("id"); id <= maxId; id++) {
				if (id % 100000 == 0)
					System.out.println(id + "/" + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
				
				int numberOfSubclusters = Library.link.subclusterLink.getNumbersWhere("cluster = " + id, "size").size();
				
				Integer count = numberOfSubclustersToCount.get(numberOfSubclusters);
				if (count == null)
					count = 0;
				count += 1;
				numberOfSubclustersToCount.put(numberOfSubclusters, count);
			}
			
			List<Integer> numberOfSubclustersList = new ArrayList<Integer>(numberOfSubclustersToCount.keySet());
			Collections.sort(numberOfSubclustersList);
			
			for (Integer numberOfSubclusters : numberOfSubclustersList)
				bw.write(numberOfSubclusters + " " + numberOfSubclustersToCount.get(numberOfSubclusters) + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumberOfConfigurationsPerCluster(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			long start = System.currentTimeMillis();
			
			Map<Integer, Integer> numberOfConfigurationsToCount = new HashMap<Integer, Integer>();
			for (int id = 1, maxId = (int)Library.link.clusterLink.getMax("id"); id <= maxId; id++) {
				if (id % 100000 == 0)
					System.out.println(id + "/" + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
				
				List<Long> sizes = Library.link.subclusterLink.getNumbersWhere("cluster = " + id, "size");
				int numberOfConfigurations = 0;
				for (Long size : sizes)
					numberOfConfigurations += size;
				
				Integer count = numberOfConfigurationsToCount.get(numberOfConfigurations);
				if (count == null)
					count = 0;
				count += 1;
				numberOfConfigurationsToCount.put(numberOfConfigurations, count);
			}
			
			List<Integer> numberOfConfigurationsList = new ArrayList<Integer>(numberOfConfigurationsToCount.keySet());
			Collections.sort(numberOfConfigurationsList);
			
			for (Integer numberOfConfigurations : numberOfConfigurationsList)
				bw.write(numberOfConfigurations + " " + numberOfConfigurationsToCount.get(numberOfConfigurations) + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeNumberOfConfigurationsPerSubcluster(String fileName) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			long start = System.currentTimeMillis();
			
			Map<Integer, Integer> numberOfConfigurationsToCount = new HashMap<Integer, Integer>();
			for (int id = 1, maxId = (int)Library.link.subclusterLink.getMax("id"); id <= maxId; id++) {
				if (id % 100000 == 0)
					System.out.println(id + "/" + maxId + " Time Taken: " + (System.currentTimeMillis() - start));
				
				int numberOfConfigurations = (int)(long)Library.link.subclusterLink.getNumbersWhere("id = " + id, "size").get(0);
				
				Integer count = numberOfConfigurationsToCount.get(numberOfConfigurations);
				if (count == null)
					count = 0;
				count += 1;
				numberOfConfigurationsToCount.put(numberOfConfigurations, count);
			}
			
			List<Integer> numberOfConfigurationsList = new ArrayList<Integer>(numberOfConfigurationsToCount.keySet());
			Collections.sort(numberOfConfigurationsList);
			
			for (Integer numberOfConfigurations : numberOfConfigurationsList)
				bw.write(numberOfConfigurations + " " + numberOfConfigurationsToCount.get(numberOfConfigurations) + "\n");
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void temp() {
		long start = System.currentTimeMillis();
		
		BigDecimal totalSquaredDistance = new BigDecimal("0");
		
		long maxId = Library.link.clusterLink.getMax("id");
		BigDecimal maxIdBigDecimal = BigDecimal.valueOf(maxId);
		BigDecimal avgDistance = new BigDecimal("172234727").divide(new BigDecimal("68478733"), 5, RoundingMode.HALF_UP);
		for (long id = 1; id <= maxId; id++) {
			if (id % 100000 == 0)
				System.out.println(id + " Time Taken: " + (System.currentTimeMillis() - start) + " " + totalSquaredDistance);
			BigDecimal size = BigDecimal.valueOf(Library.link.subclusterLink.getCountWhere("cluster = " + id));
			BigDecimal distanceSquared = (size.subtract(avgDistance)).pow(2);
			totalSquaredDistance = totalSquaredDistance.add(distanceSquared.divide(maxIdBigDecimal, 5, RoundingMode.HALF_UP));
		}

		System.out.println(totalSquaredDistance);
	}
	
	public static Board getSolution(Board board) {
		return Cluster.getSolution(board);
	}
	
	public static void main(String[] args) {
		Library.init();
		Library.syncWithDatabase();
		//Library.buildDatabase();
		
		//Library.link.subclusterLink.createIndex();
		//writeNumConfigurationsForBiggestAndOtherSubclusters("numConfigurationsForBiggestAndOtherSolvableSubclusters.txt");
		//writeNumberOfConfigurationsPerSubcluster("numberOfConfigurationsPerSubcluster.txt");
		//writeNumberOfConfigurationsPerCluster("numberOfConfigurationsPerCluster.txt");
		//writeNumberOfSubclustersPerCluster("numberOfSubclustersPerCluster.txt");
		//writeRandomSubclusterOnRandomSolution("randomSubclusterOnRandomSolutionSizes.txt");
		//writeNumSolutionsForBiggestAndOtherSubclusters("numSolutionsForBiggestAndOtherSubclusters2.txt");
		//writeAverageSolutionsDistance("averageSolutionsDistance10_0.txt", 10);
		
		//writeAverageDistancePerNumberOfVehicles("averageDistancePerNumberOfVehicles.txt", 100);
		//writeNumberOfCarsOverNumberOfTrucksRatio("numberOfCarsOverNumberOfTrucksRatio100.txt", 100);
		writeNumberOfVehicles("numberOfCarsOverMaxDistanceRatio.txt");
		//writeVehiclesOnRowsOverVehiclesOnColumnsRatio("vehiclesOnRowsOverVehiclesOnColumnsRatio100.txt", 100);
		//writeClusterSizeIncludingSubclusters("sizes_withMaxDistanceBiggerThan39_includingSubclusters.txt", "maxDistance >= 40");
		//writeClusterSizeExcludingSubClusters("sizes_withMaxDistanceBiggerThan39_excludingSubclusters.txt", "maxDistance >= 40");
		//writeRandomClusterSizes("randomSizes_includingSubclusters.txt", "", 1000);
		//writeHistogramDistanceInSizes("histogramDistanceInSizes500.txt", 500); 
		//searchMinMaxErrorInClusters("hardnessDifference_withMaxDistanceBiggerThan30.txt", 30);
		
		
		//Cluster cluster = Library.link.clusterLink.getWhere("id = 14444975").get(0);
		//writeGraphvizGraph("low_error_board_graph_" + 14444975 + ".dot", cluster);
		
		//Cluster cluster = Library.link.clusterLink.getWhere("id = 15060919").get(0);
		//writeGraphvizGraph("high_ratio_board_graph_" + 15060919 + ".dot", cluster);
		/*
		List<String> whereClauses = new ArrayList<String>();
		long maxDistance = Library.link.clusterLink.getMax("maxDistance");
		for (long i = 0; i <= maxDistance; i++)
			whereClauses.add("maxDistance = " + i);
		writeCountResults("distributionMaxDistance.txt", whereClauses);
		*/
		
		//ClusterLibrary.cheatBoard = link.clusterLink.getWhere("id = 1").get(0).getBoards().get(0);
		//Library.buildDatabase();
		//Library.clusterLibrary.generateBoards();
		//Cluster cluster = Library.link.clusterLink.getWhere("id = 63132151").get(0); // hardest board!
		//Cluster cluster = Library.link.clusterLink.getWhere("id = 11456277").get(0); // second hardest board!
		
		//for (Board board : cluster.getBoardsAtMaxDistance()) {
		//Board board = cluster.getBoardsAtMaxDistance().get(0);
		//board.prettyPrint();
		//List<Board> path = cluster.getPath(board);
		//System.out.println(translateDistance(path));
		
		//System.out.println("PATH");
		//for (Board b : path) {
		//	b.prettyPrint();
		//	System.out.println();
		//}
		//System.out.println("END PATH");
		
		//System.out.println("Distance " + (path.size() - 1));
		//System.out.println("TranslatedDistance " + translateDistance(path));
		//System.out.println();
	//}

		//Library.clusterLibrary.generateBoards();
		
		//Board board = Generator.getHardestBoard();
		//Board board = Generator.getBoard1();
		//Board board = Generator.getBoard2();
		//Board board = Generator.getBoard3();
		
		//Generator.speedTestClusterExpand(board, 1);
		//Generator.speedTestSolve(board, 1);
		//Generator.speedTestFindSolutions(board, 1);
		 
	}
}