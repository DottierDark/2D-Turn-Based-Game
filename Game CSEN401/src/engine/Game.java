package engine;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.*;
import model.collectibles.*;
import java.util.Random;

import exceptions.InvalidTargetException;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static Cell[][] map;
	
	public static void loadHeroes(String filePath) throws Exception {
		// SAW IN STACKOVEFLOW CODE IN IMPORTING CSV FILES.
		String CSVrow = ""; 
		String CSVsplitter = ",";  // rows split by ','
		List<String[]> HeroesData = new ArrayList<>();
		
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
	
	public static void Map (int width,int height) {
		map = new Cell[width][height];
	}
	  public static  void set_on_map(Cell chara , int x, int y) {
	         if (x < 0 || x > map.length || y < 0 || y > map[x].length) {
	               throw new IllegalArgumentException("Incorrect X or Y parameters");
	         }
	         map[x][y] = chara;
	      }
	public static void startGame(Hero h) {
		Map(15,15);
		heroes.add(h);
		availableHeroes.remove(h);
		set_on_map(new CharacterCell(h),0,0);
		int x,y;
		Random rand = new Random();
		spawn_zombies(10);
		int j = 5;
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}while(map[x][y] !=null);
			set_on_map(new CollectibleCell(new Supply()),x,y);
		}
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}while(map[x][y] !=null);
			set_on_map(new CollectibleCell(new Vaccine()),x,y);
		}
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}while(map[x][y] !=null);
			set_on_map(new TrapCell(),x,y);
		}
	}
	
	public static boolean checkWin() {
		if( heroes.size() >=5) {
			return true;
		}
		return false;
	}
	
	public static boolean checkGameOver() {
		if(heroes.isEmpty())
			return true;
		for(Hero hero: heroes) {
			if(!hero.getVaccineInventory().isEmpty())
				return false;
		}
				
		return true;
	}
	
	public static void endTurn() {
		
		zombies.forEach((x) -> {
			int i =0;
			x.setTarget(heroes.get(i++));
			try {
				x.attack();
			} catch (InvalidTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		heroes.forEach((x)-> {
			x.setActionsAvailable(x.getMaxActions());
			x.setSpecialAction(false);
			x.setTarget(null);
		});
		spawn_zombies(1);
	}
	
	public static void spawn_zombies(int j) {
		int x,y;
		Random rand = new Random();
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}while(map[x][y] !=null);
			Zombie z = new Zombie();
			zombies.add(z);
			set_on_map(new CharacterCell(z),x,y);
		}
		
	}
}
