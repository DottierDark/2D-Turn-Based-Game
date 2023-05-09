package engine;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
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
import exceptions.NotEnoughActionsException;

public class Game {
	
	public static ArrayList<Hero> availableHeroes;
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static Cell[][] map = new Cell[15][15];
	
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

	public static void setOnMap(Cell cell , int x, int y) {
		if (x < 0 || x > 14 || y < 0 || y > 14) {
			throw new IllegalArgumentException("Incorrect X or Y parameters");
		}
		map[x][y] = cell;
	}


	public static void updateMap() {

		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				map[i][j].setVisible(false);
			}
		}

		
		heroes.forEach((hero)-> {

			int x = (int) hero.getLocation().getX();
			int y = (int) hero.getLocation().getY();
			map[x][y].setVisible(true);
			for(int j=x-1; j<x+2; j++) {
				for(int k=y-1; k<y+2; k++) {
					if(j >= 0 && j < 15 && k >= 0 && k < 15) {
						map[j][k].setVisible(true);
					}
				}
			}
		});
	}

	public static void startGame(Hero h) {
		map = new Cell[15][15];
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				map[i][j] = new CharacterCell(null);
			}
		}
		h.setLocation(new Point(0,0));
		heroes.add(h);
		availableHeroes.remove(h);
		setOnMap(new CharacterCell(h),0,0);
		map[0][0].setVisible(true);
		map[0][1].setVisible(true);
		map[1][0].setVisible(true);
		map[1][1].setVisible(true);
		spawnZombies(10);

		int x,y;
		Random rand = new Random();
		// Spawn supplies
		int j = 5; // number of supplies & vaccines & trap cells
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
			} while(
				(map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() != null) 
				|| (map[x][y] instanceof CollectibleCell)
				);
			setOnMap(new CollectibleCell(new Supply()),x,y);
		}

		// Spawn vaccines
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}  while(
				(map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() != null) 
				|| (map[x][y] instanceof CollectibleCell)
				);
			
			setOnMap(new CollectibleCell(new Vaccine()),x,y);
		}

		// Spawn trap cells
		for(int i = 0 ; i<j;i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);
				
			}  while(
				(map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() != null) 
				|| (map[x][y] instanceof CollectibleCell) 
				|| (map[x][y] instanceof TrapCell)
				);
			setOnMap(new TrapCell(),x,y);
		}
		updateMap();
	}
	
	/**
	 * Method to check if game is won
	 * @return yes or no
	 */
	public static boolean checkWin() {
		if(checkGameOver() && heroes.size() >=5) {
			return true;
		}
		return false;
	}
	
	public static boolean checkGameOver() {
		if(heroes.isEmpty())
			return true;
		int vaccCount = 0;
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				if(map[i][j] instanceof CollectibleCell && ((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine) {
					vaccCount++;
				}
			}
		}
		if(vaccCount != 0) {
			return false;
		}

		for(Hero hero: heroes) {
			if(hero.getVaccineInventory().isEmpty() == false && vaccCount == 0)
				return false;
		}	
		return true;
	}
	
	public static void endTurn() {
		
		zombies.forEach((zombie) -> {
				try {
					zombie.attack();
				} catch (InvalidTargetException | NotEnoughActionsException e) {
					e.printStackTrace();
				}
		});
		
		heroes.forEach((hero)-> {
			hero.setActionsAvailable(hero.getMaxActions());
			hero.setSpecialAction(false);
			hero.setTarget(null);
		});

		zombies.forEach((zombie)-> {
			zombie.setTarget(null);
		});

		
		spawnZombies(1);

		updateMap();
		
	}
	
	public static void spawnZombies(int numberOfZombies) {
		int x,y;
		if(zombies.size() == 10) {
			return;
		}
		Random rand = new Random();
		for(int i = 0 ; i<numberOfZombies; i++) {
			do {
				x = rand.nextInt(15);
				y = rand.nextInt(15);

			} while(
				(map[x][y] instanceof CharacterCell && ((CharacterCell) map[x][y]).getCharacter() != null) 
				|| (map[x][y] instanceof CollectibleCell) 
				|| (map[x][y] instanceof TrapCell)
				);

			Zombie z = new Zombie();
			z.setLocation(new Point(x, y));
			zombies.add(z);
			setOnMap(new CharacterCell(z),x,y);
		}
		
	}
}
