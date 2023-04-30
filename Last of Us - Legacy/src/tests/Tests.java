package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.characters.Zombie;
import model.collectibles.Armor;
import model.collectibles.Collectible;
import model.collectibles.Material;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Building;
import model.world.Hospital;
import model.world.MilitaryBase;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {
	

    @Test
    public void testConstructorAndGetters() {
        Armor armor = new Armor(150, Material.METAL);
        assertEquals("protection should be set to 150", 150, armor.getProtection());
        assertEquals("material should be set to METAL", Material.METAL, armor.getMaterial());
    }

    @Test
    public void testConstructorAndGetters_P() {
        Armor armor = new Armor(150, Material.PLASTIC);
        assertEquals("protection should be set to 150", 150, armor.getProtection());
        assertEquals("material should be set to PLASTIC", Material.PLASTIC, armor.getMaterial());
    }
    @Test
    public void testConstructorAndGetters_W() {
        Armor armor = new Armor(150, Material.WOOD);
        assertEquals("protection should be set to 150", 150, armor.getProtection());
        assertEquals("material should be set to WOOD", Material.WOOD, armor.getMaterial());
    }
    @Test
    public void testConstructorAndGetters_PA() {
        Armor armor = new Armor(150, Material.PAPER);
        assertEquals("protection should be set to 150", 150, armor.getProtection());
        assertEquals("material should be set to PAPER", Material.PAPER, armor.getMaterial());
    }
    
    @Test
    public void testArmorPrivateAttributes() throws NoSuchFieldException, SecurityException {
        assertTrue("protection should be private", Modifier.isPrivate(Armor.class.getDeclaredField("protection").getModifiers()));
        assertTrue("material should be private",Modifier.isPrivate(Armor.class.getDeclaredField("material").getModifiers()));
    }
  
	
	@Test
	public void testClassInherits() {
		try {
			Class<?> armorClass = Class.forName("model.collectibles.Armor");
			assertTrue( model.collectibles.Supply.class.isAssignableFrom(armorClass));
		} catch (ClassNotFoundException e) {
			fail("Armor has a the wrong superclass");
		}
	}
	
	@Test
    public void A_testGettersAndSettersNotInClass() {
    	try {
            Method setMethod = Armor.class.getDeclaredMethod("setProtection", int.class);
            assertFalse("protection is READ-ONLY", true);
            
        } catch (NoSuchMethodException e) {
        }
    	try {
            Method setMethod = Armor.class.getDeclaredMethod("setMaterial", Material.class);
            assertFalse("material is READ-ONLY", true);
            
        } catch (NoSuchMethodException e) {
        }
    }
	
	@Test
	public void testClassInherits_B() {
		try {
			Class<?> BuildingClass = Class.forName("model.world.Building");
			assertTrue( model.world.Cell.class.isAssignableFrom(BuildingClass));
		} catch (ClassNotFoundException e) {
			fail("Armor has a the wrong superclass");
		}
	}
	
	
	@Test
	public void testClassInherits_H() {
		try {
			Class<?> hospitalClass = Class.forName("model.world.Hospital");
			assertTrue( Class.forName("model.world.Building").isAssignableFrom(hospitalClass));
		} catch (ClassNotFoundException e) {
			fail("Armor has a the wrong superclass");
		}
	}
	
	@Test
	public void testClassInherits_MB() {
		try {
			Class<?> militaryBaseClass = Class.forName("model.world.MilitaryBase");
			assertTrue( Class.forName("model.world.Building").isAssignableFrom(militaryBaseClass));
		} catch (ClassNotFoundException e) {
			fail("Armor has a the wrong superclass");
		}
	}
	
	@Test
    public void testBuildingPrivateAttributes() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
        assertTrue("loot should be private",Modifier.isPrivate(Class.forName("model.world.Building").getDeclaredField("loot").getModifiers()));
    }
	
	@Test
    public void testBuildingisAbstract() {
	 Class<Building> myClass = Building.class;
	 assertTrue("Building should be an abstract class", Modifier.isAbstract(myClass.getModifiers()));
    }
 
 @Test
 public void testLootReadOnly() {
     try {
         Method method = Building.class.getDeclaredMethod("setLoot", Collectible.class);
         assertTrue("loot should be READ-ONLY",false);
     } catch (NoSuchMethodException e) {
     }
 }
 
 @Test
 public void testHospitalConstructor() {
	 Hospital hospital = new Hospital();
	 assertTrue("Hospital should contain a Vaccine", hospital.getLoot() instanceof Vaccine);
 }
 
 @Test
 public void testMilitaryBaseConstructor() {
	 MilitaryBase militaryBase = new MilitaryBase();
	 assertTrue("MilitaryBase should contain a Supply", militaryBase.getLoot() instanceof Supply);
 }
}
