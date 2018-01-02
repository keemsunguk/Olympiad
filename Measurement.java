package Olympiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class Measurement {

	public static class MilkData {
		private int day;
		private String cow;
		private int milk;
		public MilkData(int a, String b, int c) {
			day = a;
			cow = b;
			milk = c;
		}
		public int getDay() {
			return day;
		}
		public String getCow() {
			return cow;
		}
		public int getMilk() {
			return milk;
		}
		public String toString() {
			return day+" "+cow+" "+milk;
		}
		public void setMilk(int a) {
			milk += a;
		}
		public void setMilk(int a, int b) {
			milk = a+b;
		}
	}
	
	public static Comparator<MilkData> dayComparator = new Comparator<MilkData>(){
		
		@Override
		public int compare(MilkData c1, MilkData c2) {
            return (int) (c1.getDay() - c2.getDay());	//ascending
        }
	};
	
	public static Comparator<MilkData> milkComparator = new Comparator<MilkData>(){
		
		@Override
		public int compare(MilkData c1, MilkData c2) {
            return (int) (c2.getMilk() - c1.getMilk()); //descending
        }
	};
	
	//
	static void printContents(PriorityQueue<MilkData> m) {	
		m.forEach(c->System.out.println(c));
	}
	//
	static void printContents(List<MilkData> m) {	
		m.forEach(c->System.out.println(c));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<MilkData> milkData = new PriorityQueue<MilkData>(dayComparator);
		
		File inf = new File("inputs/measurement.in");
		BufferedReader reader = new BufferedReader(new FileReader(inf));
		int size = Integer.parseInt(reader.readLine());
		
		for(int j = 0; j < size; j++) {
			String a = reader.readLine();
			String [] b = a.split(" ");
			MilkData tmp = new MilkData(Integer.valueOf(b[0]), b[1], Integer.valueOf(b[2]));
			milkData.add(tmp);
		}
		
		reader.close();

		printContents(milkData);
		
		PrintWriter pw = new PrintWriter("inputs/measurement.out");
		int ans = measurement(milkData);
//		pw.println(measurement(d1, d2));
		pw.close();
		System.out.println(ans);
	}

	private static int measurement(PriorityQueue<MilkData> m) {
		int changeCount = 1;	//first always change
		
		List<MilkData> cows = new ArrayList<MilkData>();
		MilkData c1 = m.poll();
		cows.add(new MilkData(c1.getDay(), c1.getCow(), 7+c1.getMilk()));
		
		boolean done = false;
		while( !done ) {
			MilkData c = m.poll();
			if(c != null) {
				MilkData preMaxCow = cows.stream()
								.max( (x,y) -> x.getMilk()-y.getMilk() )
								.get();			
				if (cows.stream().anyMatch(x->x.getCow().equals(c.getCow())) ) {
					cows.stream().filter(o->o.getCow().equals(c.getCow())).findFirst().get().setMilk(c.getMilk());
				} else {
					cows.add(new MilkData(c.getDay(), c.getCow(), 7+c.getMilk()));
				}
				MilkData curMaxCow = cows.stream()
						.max( (x,y) -> x.getMilk()-y.getMilk() )
						.get();			
				if( preMaxCow.getMilk() <= curMaxCow.getMilk() ) {	//new one found
					if( !preMaxCow.getCow().equals(curMaxCow.getCow())) {
						System.out.println(curMaxCow.getCow() + " is taking top");
						changeCount++;
					}
				} 
			} else {
				done = true;
			}
		}
		printContents(cows);
		return changeCount;
	}

	private static int measurement2(PriorityQueue<MilkData> m) {
		PriorityQueue<MilkData> milkData = new PriorityQueue<MilkData>(milkComparator);
		boolean done = false;
		//MilkData c = null;
		while( !done) {
			MilkData c = m.poll();
			if (c != null) {
				if(milkData.iterator().hasNext()) {
					MilkData foundCow = milkData.stream().filter(x->c.getCow().equals(x.getCow())).findAny().orElse(null);
					if( foundCow !=null) {
						System.out.println("Found!");
						foundCow.setMilk(foundCow.getMilk()+c.getMilk());
					} else {
						milkData.add(new MilkData(c.getDay(), c.getCow(), 7+c.getMilk()));
					}
				} else {
					milkData.add(new MilkData(c.getDay(), c.getCow(), 7+c.getMilk()));
				}
				MilkData root = milkData.peek();
				if(root.getCow().equals(c.getCow())) {
					System.out.println(root.getCow() + " is taking top");
				}
			} else {
				done = true;
			}
		}
		printContents(milkData);
		return 0;
	}

}
