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
		String CSVrow = ""; 
		String CSVsplitter = ",";  // rows split by ','
		List<String[]> HeroesData = new ArrayList<>();
		availableHeroes = new ArrayList<Hero>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			//while loop to get data from csv
			while((CSVrow = br.readLine()) != null) {
				String[] row = CSVrow.split(CSVsplitter); // split rows into string values
				HeroesData.add(row);				
			}//end of while loop
			//for loop to initialize heroes from data
			for(int i=0; i<HeroesData.size(); i++) {
				String heroName;
				String heroType;
				int maxHpOfHero;
				int maxActionsOfHero;
				int attackDmgOfHero;
				
				//get row with hero data from total list
				String[] heroData = HeroesData.get(i);
				
				// HeroData is row of csv with hero stats
				heroName = heroData[0];
				heroType = heroData[1];
				maxHpOfHero = Integer.parseInt(heroData[2]);
				maxActionsOfHero = Integer.parseInt(heroData[3]);
				attackDmgOfHero = Integer.parseInt(heroData[4]);
				switch(heroType) {
				case("FIGH"): // Fighter case
					availableHeroes.add(new Fighter(heroName, maxHpOfHero, attackDmgOfHero, maxActionsOfHero)); break;
				case("MED"): // Medic case
					availableHeroes.add(new Medic(heroName, maxHpOfHero, attackDmgOfHero, maxActionsOfHero)); break;
				case("EXP"): // Explorer cases
					availableHeroes.add(new Explorer(heroName, maxHpOfHero, attackDmgOfHero, maxActionsOfHero)); break;
				}
			}
			//in case the csv file was not found or corrupted
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void startGame(Hero h) {
		// TODO Auto-generated method stub
	}
	
	public static boolean checkWin() {
		// TODO Auto-generated method stub
	}
	
	public static boolean checkGameOver() {
		// TODO Auto-generated method stub
	}
	
	public static void endTurn() {
		// TODO Auto-generated method stub
	}
	
}
