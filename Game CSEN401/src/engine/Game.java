package engine;

import java.util.ArrayList;
import java.util.List;
import java.io.*;


import model.characters.*;
import model.world.Cell;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	
	public static void loadHeroes(String filePath) throws Exception {
		if(filePath==null) {
			throw new Exception();
		}
		String line = "";
		String csvSplitBy = ",";
		List<String[]> data = new ArrayList<>();
		availableHeroes = new ArrayList<Hero>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			while((line = br.readLine()) != null) {
				String[] row = line.split(csvSplitBy);
				data.add(row);				
			}
		}
			catch(FileNotFoundException e) {
				System.out.println("file was not found");
			}
			
			for(int i=0; i<8; i++) {
				String name;
				String type;
				int maxHp;
				int maxActions;
				int attackDmg;
				
				//8 rows
				String[] heroData = data.get(i);
				//for(int j=0; j<heroData.length; j++)
				//System.out.println(heroData[j]);
				// HeroData is row of csv with hero stats
				name = heroData[0];
				//System.out.println(heroData[0]);
				type = heroData[1];
				//System.out.println(heroData[1]);
				maxHp = Integer.parseInt(heroData[2]);
				//System.out.println(heroData[2]);
				maxActions = Integer.parseInt(heroData[3]);
				//System.out.println(heroData[3]);
				attackDmg = Integer.parseInt(heroData[4]);
				//System.out.println(heroData[4]);
				switch(type) {
				case("FIGH"):
					//System.out.println(name + " F");
					availableHeroes.add(new Fighter(name, maxHp, maxActions, attackDmg)); break;
				case("MED"):
					//System.out.println(name + " M");
					availableHeroes.add(new Medic(name, maxHp, maxActions, attackDmg)); break;
				case("EXP"):
					//System.out.println(name + " E");
					availableHeroes.add(new Explorer(name, maxHp, maxActions, attackDmg)); break;
				}
			}
			System.out.println();
		
	}
	/*public static void main(String[] args) {
		try {
			loadHeros("F:\\Programs\\Java\\Projects\\Game CSEN401\\src\\engine\\Heros.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		for(int i=0; i<availableHeros.size(); i++)
			System.out.println(availableHeros.get(i).toString());
	*/
}
