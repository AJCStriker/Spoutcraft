package net.minecraft.src;

import org.getspout.spout.entity.CraftGiant;

import net.minecraft.src.EntityMob;
import net.minecraft.src.World;

public class EntityGiantZombie extends EntityMob {

	public EntityGiantZombie(World var1) {
		super(var1);
		this.texture = "/mob/zombie.png";
		this.moveSpeed = 0.5F;
		this.attackStrength = 50;
		this.health *= 10;
		this.yOffset *= 6.0F;
		this.setSize(this.width * 6.0F, this.height * 6.0F);
		//Spout start
		this.spoutEntity = new CraftGiant(this);
		//Spout end
	}

	protected float getBlockPathWeight(int var1, int var2, int var3) {
		return this.worldObj.getLightBrightness(var1, var2, var3) - 0.5F;
	}
}
