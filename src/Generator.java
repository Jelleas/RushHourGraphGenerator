import java.applet.Applet;
import java.awt.Graphics;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	
	private static void writeNumberOfVehicles(String fileName, int numBoardsPerDistance) {
		try {
			File file = new File(fileName);
			
			if (!file.exists())
				file.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			
			int maxDistance = (int)Library.link.clusterLink.getMax("maxDistance");
			for (int i = 1; i <= maxDistance; i++) {
				List<Cluster> clusters = Library.link.clusterLink.getRandomWhere("`maxDistance` = " + i, numBoardsPerDistance, false);
				
				for (Cluster cluster : clusters)
					bw.write(i + " " + cluster.getNumberOfVehicles() + " " + cluster.getId() + "\n");
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
	
	public static Board getSolution(Board board) {
		return Cluster.getSolution(board);
	}
	
	public static void main(String[] args) {
		Library.init();
		Library.syncWithDatabase();
		Library.buildDatabase();
		
		//writeNumberOfCarsOverNumberOfTrucksRatio("numberOfCarsOverNumberOfTrucksRatio100.txt", 100);
		//writeNumberOfVehicles("numberOfCarsOverMaxDistanceRatio100.txt", 100);
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