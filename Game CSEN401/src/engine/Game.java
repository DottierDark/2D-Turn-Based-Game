package engine;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes;
	public static ArrayList<Zombie> zombies;
	public static Cell[][] map;
	
	public static void loadHeroes(String filePath) throws Exception {
		// SAW IN STACKOVEFLOW CODE IN IMPORTING CSV FILES.
		String line = ""; 
		String csvSplitBy = ",";  // rows split by ','
		List<String[]> HeroesData = new ArrayList<>();
		availableHeroes = new ArrayList<Hero>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			while((line = br.readLine()) != null) {
				String[] row = line.split(csvSplitBy); // split rows into string values
				HeroesData.add(row);				
			}
			//end of stack overflow code.
			for(int i=0; i<HeroesData.size(); i++) {
				String name;
				String type;
				int maxHp;
				int maxActions;
				int attackDmg;
				
				//get row with hero data from total list
				String[] heroData = HeroesData.get(i);
				
				// HeroData is row of csv with hero stats
				name = heroData[0];
				type = heroData[1];
				maxHp = Integer.parseInt(heroData[2]);
				maxActions = Integer.parseInt(heroData[3]);
				attackDmg = Integer.parseInt(heroData[4]);
				switch(type) {
				case("FIGH"): // Fighter case
					availableHeroes.add(new Fighter(name, maxHp, attackDmg, maxActions)); break;
				case("MED"): // Medic case
					availableHeroes.add(new Medic(name, maxHp, attackDmg, maxActions)); break;
				case("EXP"): // Explorer cases
					availableHeroes.add(new Explorer(name, maxHp, attackDmg, maxActions)); break;
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
