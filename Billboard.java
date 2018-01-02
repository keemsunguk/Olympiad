package Olympiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Billboard {
	static int[][] readInput(File inf) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inf));
			int [][] array = new int[3][4];
			for(int i = 0; i < 3; i++) {
				String a = reader.readLine();
				String [] b = a.split(" ");
				for(int j = 0; j < 4; j++) {
					array[i][j] = Integer.valueOf(b[j]);
				}
			}
			reader.close();
			return array;
		} catch ( NumberFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	static void printContents(int[][] array) {
		
		for(int r = 0; r < 3; r++) {
			for(int s = 0; s < 4; s++) {
				System.out.print(array[r][s]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws IOException {
		File inf = new File("inputs/billboard.in");
		int [][]a = readInput(inf);
		
//		printContents(a);	//double check, debugging
		PrintWriter wr = new PrintWriter(new FileWriter("inputs/billboard.out"));
		wr.println(billboard(a));
		wr.close();

		System.out.println(billboard(a));

	}
	private static int billboard2(int [][] a) {
		return 0;
	}
	private static int billboard(int[][] a) {
		int x1ol = 0;
		int y1ol = 0;
		int x2ol = 0;
		int y2ol = 0;
		
		int b1x1 = a[0][0];
		int b1y1 = a[0][1];
		int b1x2 = a[0][2];
		int b1y2 = a[0][3];
		
		int b2x1 = a[1][0];
		int b2y1 = a[1][1];
		int b2x2 = a[1][2];
		int b2y2 = a[1][3];
		
		int tx1 = a[2][0];
		int ty1 = a[2][1];
		int tx2 = a[2][2];
		int ty2 = a[2][3];
		
		x1ol = ComputeOverlap(b1x1,b1x2, tx1, tx2);
		y1ol = ComputeOverlap(b1y1,b1y2, ty1, ty2);
		x2ol = ComputeOverlap(b2x1,b2x2, tx1, tx2);
		y2ol = ComputeOverlap(b2y1,b2y2, ty1, ty2);
		
//		System.out.format("%d %d %d %d\n", x1ol, y1ol, x2ol, y2ol);
		int area = (b1x2-b1x1)*(b1y2-b1y1) + (b2x2-b2x1)*(b2y2-b2y1) - (x1ol*y1ol+x2ol*y2ol);
		return area;
	}
	private static int ComputeOverlap(int b1, int b2, int t1, int t2) {
		int ol = 0;
		
		if(b1<= t1 && b2 <= t1) {
			ol = 0;
		} else if(b1<= t1 && t1 <= b2 && b2 <= t2) {
			ol = Math.abs(b2-t1);
		} else if(b1<=t1 && t2 <= b2) {
			ol = Math.abs(t1-b1)+Math.abs(b2-t2);
		} else if(t1 < b1 && b2 <= t2){
			ol = 0;
		} else if(t1 < b1 && t2 <= b2) {
			ol = Math.abs(t2 - b1);
		} else {
			ol = 0;
		} 
		return ol;
	}
}
