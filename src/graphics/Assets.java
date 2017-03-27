package graphics;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import utilities.Utilities;

public class Assets {
	
	// sprite sheets
	public static SpriteSheet mountain = new SpriteSheet(Utilities.loadImage("/mountain/mountain.png")); 
	
	
	public static BufferedImage[] pistolIdle = new BufferedImage[20];
	public static BufferedImage[] rifleIdle = new BufferedImage[20];
	
	public static BufferedImage[] pistolReload = new BufferedImage[15];
	public static BufferedImage[] rifleReload = new BufferedImage[20];
	
	public static BufferedImage[] pistolShootAnim = new BufferedImage[3];
	public static BufferedImage[] rifleShootAnim = new BufferedImage[3];
	
	public static BufferedImage[] zombie = new BufferedImage[17];
	public static BufferedImage[] zombieAttack = new BufferedImage[9];
	
	// blood splats
	
	public static BufferedImage blood1;
	
	// guns skin
	
	public static BufferedImage pistolSkin, ak47, rifleLoader;
	
	
	//sounds
	
	public static Clip pistolShoot, rifleShoot, background, zombieHit, pistolReloadSound,
	rifleReloadSound,emptyGun, zombieBite;
	
	//tiles
	
	public static BufferedImage grass, dirt;
	
	//objects
	
	public static BufferedImage tree;
	
	
	
	
	
	//trees
	public static BufferedImage[] trees = new BufferedImage[4];
	
	
	public static void init(){
		
		// animations
		
		for(int i = 0; i<pistolIdle.length; i++)
			pistolIdle[i] = Utilities.loadImage("/player/idle/pistolIdle/"+i+".png");
		
		for(int i = 0; i<rifleIdle.length; i++)
			rifleIdle[i] = Utilities.loadImage("/player/idle/rifleIdle/"+i+".png");
		
		
		for(int i = 0; i<pistolReload.length; i++)
			pistolReload[i] = Utilities.loadImage("/player/reload/pistol/"+i+".png");
		
		for(int i = 0; i<rifleReload.length; i++)
			rifleReload[i] = Utilities.loadImage("/player/reload/rifle/"+i+".png");
		
		for(int i = 0; i<pistolShootAnim.length; i++)
			pistolShootAnim[i] = Utilities.loadImage("/player/shoot/pistol/"+i+".png");
		
		for(int i = 0; i<rifleShootAnim.length; i++)
			rifleShootAnim[i] = Utilities.loadImage("/player/shoot/rifle/"+i+".png");
		
		
		for(int i = 0; i<zombie.length; i++)
			zombie[i] = Utilities.loadImage("/zombie/walk/"+i+".png");
		
		for(int i = 0; i<zombieAttack.length; i++)
			zombieAttack[i] = Utilities.loadImage("/zombie/attack/"+i+".png");
		
		//trees
		for(int i = 0; i < trees.length; i++)
			trees[i] = Utilities.loadImage("/tress/tree"+i+".png");
		
		
		//objects
		
		tree = Utilities.loadImage("/obstacles/tree.png");
		
		// tiles

		grass = Utilities.loadImage("/tiles/grass.png");
		dirt = Utilities.loadImage("/tiles/dirt.png");
		
		// gun skins
		
		pistolSkin = Utilities.loadImage("/guns/pistol.png");
		ak47 = Utilities.loadImage("/guns/ak-47.png");
		rifleLoader = Utilities.loadImage("/guns/rifleLoader.png");
		
		// blood splats
		
		blood1 = Utilities.loadImage("/zombie/blood/bloodsplat.png");
		
		//sounds
		pistolShoot = Utilities.LoadSound("/pistol.wav");
		rifleShoot = Utilities.LoadSound("/machineGun.wav");
		background = Utilities.LoadSound("/background.wav");
		zombieHit = Utilities.LoadSound("/zombiehit.wav");
		pistolReloadSound = Utilities.LoadSound("/pistolreload.wav");
		rifleReloadSound = Utilities.LoadSound("/riflereload.wav");
		emptyGun = Utilities.LoadSound("/emptygun.wav");
		zombieBite = Utilities.LoadSound("/zombieBite.wav");
		
	}
	
}
