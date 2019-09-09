package fr.badblock.bukkit.games.uhcmodifier.utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import lombok.Getter;

/**
 * Repr�sente une cr�ature (mob), comme org.bukkit.entity.CreatureType
 * (d�pr�ci�) et rajoute un nombre cons�quant d'informations non disponnible via
 * l'API Bukkit. Pour les autres entit�s, utiliser EntityType (Bukkit).
 * 
 * Permet aussi la traduction gr�ce � getKey().
 * 
 * @author LeLanN
 */
public enum CreatureType {
	BLAZE("blaze", Reaction.HOSTILE, NaturallySpawnReason.SPAWNER), BAT("bat", Reaction.FRIENDLY,
			NaturallySpawnReason.NATURAL), // TODO create ambient
	CAVE_SPIDER("cave_spider", Reaction.HOSTILE, NaturallySpawnReason.SPAWNER), CHICKEN("chicken", Reaction.FRIENDLY,
			NaturallySpawnReason.NATURAL), COW("cow", Reaction.FRIENDLY, NaturallySpawnReason.NATURAL), CREEPER(
					"creeper", Reaction.HOSTILE, NaturallySpawnReason.NATURAL), ENDER_DRAGON("ender_dragon",
							Reaction.HOSTILE, NaturallySpawnReason.GENERATION), ENDERMAN("enderman", Reaction.ANGRY,
									NaturallySpawnReason.NATURAL), ENDERMITE("endermite", Reaction.HOSTILE,
											NaturallySpawnReason.ENDERPEARL), GHAST("ghast", Reaction.HOSTILE,
													NaturallySpawnReason.NATURAL), GIANT("giant", Reaction.HOSTILE,
															NaturallySpawnReason.ONLY_PLUGIN), GUARDIAN("guardian",
																	Reaction.HOSTILE,
																	NaturallySpawnReason.NATURAL), HORSE("horse",
																			Reaction.FRIENDLY,
																			NaturallySpawnReason.NATURAL), MAGMA_CUBE(
																					"magma_cube", Reaction.HOSTILE,
																					NaturallySpawnReason.NATURAL), MUSHROOM_COW(
																							"mushroom_cow",
																							Reaction.FRIENDLY,
																							NaturallySpawnReason.NATURAL), OCELOT(
																									"ocelot",
																									Reaction.FRIENDLY,
																									NaturallySpawnReason.NATURAL), PIG(
																											"pig",
																											Reaction.FRIENDLY,
																											NaturallySpawnReason.NATURAL), PIG_ZOMBIE(
																													"pig_zombie",
																													Reaction.ANGRY,
																													NaturallySpawnReason.NATURAL), RABBIT(
																															"rabbit",
																															Reaction.FRIENDLY,
																															NaturallySpawnReason.NATURAL), SHEEP(
																																	"sheep",
																																	Reaction.FRIENDLY,
																																	NaturallySpawnReason.NATURAL), SILVERFISH(
																																			"silverfish",
																																			Reaction.HOSTILE,
																																			NaturallySpawnReason.BLOCK), SKELETON(
																																					"skeleton",
																																					Reaction.HOSTILE,
																																					NaturallySpawnReason.NATURAL), SLIME(
																																							"slime",
																																							Reaction.HOSTILE,
																																							NaturallySpawnReason.NATURAL), SNOWMAN(
																																									"snowman",
																																									Reaction.FRIENDLY,
																																									NaturallySpawnReason.NATURAL), SPIDER(
																																											"spider",
																																											Reaction.HOSTILE,
																																											NaturallySpawnReason.NATURAL), SQUID(
																																													"squid",
																																													Reaction.FRIENDLY,
																																													NaturallySpawnReason.NATURAL), VILLAGER(
																																															"villager",
																																															Reaction.FRIENDLY,
																																															NaturallySpawnReason.NATURAL), WITCH(
																																																	"witch",
																																																	Reaction.HOSTILE,
																																																	NaturallySpawnReason.NATURAL), WITHER(
																																																			"wither",
																																																			Reaction.HOSTILE,
																																																			NaturallySpawnReason.BLOCK), WOLF(
																																																					"wolf",
																																																					Reaction.ANGRY,
																																																					NaturallySpawnReason.NATURAL), ZOMBIE(
																																																							"zombie",
																																																							Reaction.HOSTILE,
																																																							NaturallySpawnReason.NATURAL);

	/**
	 * Repr�sente les raisons de spawn d'une cr�ature
	 * 
	 * @author LeLanN
	 */
	public static enum NaturallySpawnReason {
		BLOCK, ENDERPEARL, GENERATION, NATURAL, ONLY_PLUGIN, SPAWNER;
	}
	/**
	 * Repr�sente une r�action de cr�ature
	 * 
	 * @author LeLanN
	 */
	public static enum Reaction {
		/**
		 * Ne va jamais attaquer le joueur
		 */
		FRIENDLY,
		/**
		 * Essayera toujours de tuer le joueur
		 */
		HOSTILE,
		/**
		 * Attaquera le joueur seulement si il a �t� agress�
		 */
		ANGRY;
	}
	/**
	 * R�cup�re une CreatureType via une EntityType Bukkit
	 * 
	 * @param bukkit
	 *            L'EntityType Bukkit
	 * @return La CreatureType (si non trouv�, null)
	 */
	public static CreatureType getByBukkit(EntityType bukkit) {
		for (CreatureType ct : values())
			if (bukkit == ct.bukkit())
				return ct;
		return null;
	}

	/**
	 * R�cup�re une CreatureType via une entit� Bukkit
	 * 
	 * @param bukkit
	 *            L'entit� Bukkit
	 * @return La CreatureType (si non trouv�, null)
	 */
	public static CreatureType getByBukkitEntity(Entity bukkit) {
		return getByBukkit(bukkit.getType());
	}

	/**
	 * R�cup�re un CreatureType � partir du nom de l'entit�
	 * 
	 * @param name
	 *            Le nom
	 * @return Le CreatureType (si non trouv�, null)
	 */
	public static CreatureType matchCreature(String name) {
		for (CreatureType ct : values())
			if (ct.getName().equalsIgnoreCase(name))
				return ct;
		return null;
	}

	@Getter
	private String name;

	@Getter
	private Reaction reaction;

	@Getter
	private NaturallySpawnReason spawnReason;

	private CreatureType(String name, Reaction reaction, NaturallySpawnReason spawnReason) {
		this.name = name;
		this.reaction = reaction;
		this.spawnReason = spawnReason;
	}

	/**
	 * R�cup�re l'EntityType �quivalent
	 * 
	 * @return L'EntityType
	 */
	public EntityType bukkit() {
		return EntityType.valueOf(name());
	}

	public String getKey() {
		return "entities." + name;
	}

	/**
	 * V�rifie si la cr�ature est amicale
	 * 
	 * @return Un boolean
	 */
	public boolean isFriendly() {
		return reaction == Reaction.FRIENDLY;
	}

	/**
	 * V�rifie si la cr�ature est hostile
	 * 
	 * @return Un boolean
	 */
	public boolean isHostile() {
		return !isFriendly();
	}

	/**
	 * Fait spawn une nouvelle entit� du type du CreatureType � une certaine
	 * position
	 * 
	 * @param l
	 *            La location
	 * @return La nouvelle entit�
	 */
	public Entity spawn(Location l) {
		return l.getWorld().spawnEntity(l, bukkit());
	}
}
