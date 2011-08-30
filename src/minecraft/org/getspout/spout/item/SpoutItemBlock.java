package org.getspout.spout.item;

import java.util.HashMap;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import org.getspout.spout.io.CustomTextureManager;
import org.newdawn.slick.opengl.Texture;

public class SpoutItemBlock extends ItemBlock {

	private final static HashMap<Integer, Integer> itemBlock = new HashMap<Integer, Integer>();
	private final static HashMap<Integer, String> itemTexture = new HashMap<Integer, String>();
	private final static HashMap<Integer, String> itemPlugin = new HashMap<Integer, String>();

	public SpoutItemBlock(int blockId) {
		super(blockId);
	}

	public static void addItemInfoMap(int damage, String pluginName, String textureURL, Integer blockId) {
		if (blockId == null) {
			itemBlock.remove(damage);
		} else {
			itemBlock.put(damage, blockId);
		}
		if (pluginName == null || textureURL == null) {
			itemTexture.remove(damage);
			itemPlugin.remove(damage);
		} else {
			itemTexture.put(damage, textureURL);
			itemPlugin.put(damage, pluginName);
		}
	}

	public static void wipeMap() {
		itemBlock.clear();
		itemTexture.clear();
		itemPlugin.clear();
	}
	
	private static String getTextureURL(Integer damage) {
		if (damage == null) {
			return null;
		} else {
			return itemTexture.get(damage);
		}
	}
	
	private static String getPluginName(Integer damage) {
		if (damage == null) {
			return null;
		} else {
			return itemPlugin.get(damage);
		}
	}
	
	public static Integer getTextureId(int damage) {
		
		String plugin = getPluginName(damage);
		String textureURL = getTextureURL(damage);
		
		if (plugin == null || textureURL == null) {
			return null;
		}
		
		 Texture texture = CustomTextureManager.getTextureFromUrl(plugin, textureURL);
		 
		 if (texture == null) {
			 return null;
		 }
		 
		 return texture.getTextureID();
		
	}

	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int face) {
		if (stack.itemID == 1) {
			int damage = stack.getItemDamage();
			if (damage >= 1024) {
				Integer blockId = itemBlock.get(damage);
				if (blockId == null) {
					return true;
				} else {
					boolean result = onBlockItemUse(blockId, stack, player, world, x, y, z, face);
					return result;
				}
			}
		}
		return super.onItemUse(stack, player, world, x, y, z, face);
	}

	// From super class
	public boolean onBlockItemUse(int blockID, ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7) {
		if (var3.getBlockId(var4, var5, var6) == Block.snow.blockID) {
			var7 = 0;
		} else {
			if (var7 == 0) {
				--var5;
			}

			if (var7 == 1) {
				++var5;
			}

			if (var7 == 2) {
				--var6;
			}

			if (var7 == 3) {
				++var6;
			}

			if (var7 == 4) {
				--var4;
			}

			if (var7 == 5) {
				++var4;
			}
		}

		if (var1.stackSize == 0) {
			return false;
		} else if (var5 == 127 && Block.blocksList[blockID].blockMaterial.isSolid()) {
			return false;
		} else if (var3.canBlockBePlacedAt(blockID, var4, var5, var6, false, var7)) {
			Block var8 = Block.blocksList[blockID];
			if (var3.setBlockAndMetadataWithNotify(var4, var5, var6, blockID, this.getPlacedBlockMetadata(var1.getItemDamage()))) {
				Block.blocksList[blockID].onBlockPlaced(var3, var4, var5, var6, var7);
				Block.blocksList[blockID].onBlockPlacedBy(var3, var4, var5, var6, var2);
				var3.playSoundEffect((double) ((float) var4 + 0.5F), (double) ((float) var5 + 0.5F), (double) ((float) var6 + 0.5F), var8.stepSound.func_1145_d(),
						(var8.stepSound.getVolume() + 1.0F) / 2.0F, var8.stepSound.getPitch() * 0.8F);
				--var1.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

}
