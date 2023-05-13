<<<<<<< HEAD
package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Explorer extends Hero{

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions) ;
		
	}

	public void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException {
		
		try {
			super.useSpecial();
		} catch(NoAvailableResourcesException e) {
			throw new NoAvailableResourcesException();
		} catch(InvalidTargetException e) {
			throw new InvalidTargetException();
		}
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}
	
}
=======
package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Explorer extends Hero{

	public Explorer(String name,int maxHp, int attackDmg, int maxActions) {
		super( name, maxHp,  attackDmg,  maxActions) ;
		
	}

	public void useSpecial() throws  NoAvailableResourcesException, InvalidTargetException {
		
try {
		super.useSpecial();
}

catch( NoAvailableResourcesException e) {
	throw new NoAvailableResourcesException();
}
catch(InvalidTargetException e) {
	throw new InvalidTargetException();
}
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				Game.map[i][j].setVisible(true);
			}
		}
	}
	
}
>>>>>>> 977751f9456d3d6d175d5eca1be5736d50367250
