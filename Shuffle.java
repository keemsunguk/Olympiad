package Olympiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Shuffle {
	static void printContents(int[] array) {
		
		for(int r = 0; r < array.length; r++) {
				System.out.print(array[r]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		File inf = new File("inputs/shuffle.in");
		BufferedReader reader = new BufferedReader(new FileReader(inf));
		int size = Integer.parseInt(reader.readLine());
		int [] d1 = new int[size];
		int [] d2 = new int[size];
		
		String a1 = reader.readLine();
		String a2 = reader.readLine();
		
		String [] b1 = a1.split(" ");
		String [] b2 = a2.split(" ");
		
		for(int j = 0; j < size; j++) {
			d1[j] = Integer.valueOf(b1[j]);
			d2[j] = Integer.valueOf(b2[j]);
		}
		reader.close();

//		printContents(d1);
//		printContents(d2);
		
		int [] ans = shuffle(d1, d2);
		printContents(ans);
		PrintWriter pw = new PrintWriter("inputs/shuffle.out");
		for(int j = 0; j < ans.length; j++) {
			pw.println(ans[j]);
		}
		pw.close();

	}

	private static int[] shuffle(int[] rule, int[] position) {
		int [] target = new int [position.length];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < position.length; j++) {
				target[j] = position[rule[j]-1];
				System.out.print(target[j]+" ");
			}
			System.out.println();
			for(int j = 0; j < position.length; j++) {
				position[j] = target[j];
			}
		}
		return target;
	}

}
