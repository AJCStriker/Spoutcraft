/*
 * This file is part of Spoutcraft (http://wiki.getspout.org/).
 * 
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.player;

import org.getspout.spout.entity.CraftHumanEntity;
import org.spoutcraft.spoutcraftapi.entity.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;

public class SpoutPlayer extends CraftHumanEntity implements Player{
	
	public SpoutPlayer(net.minecraft.src.EntityPlayer handle) {
		super(handle);
		this.handle = handle;
	}
	
	public EntityPlayer getMCPlayer(){
		return (EntityPlayer)handle;
	}
	
	public void setPlayer(EntityPlayer player) {
		this.handle = player;
	}
	
	public boolean isOnline() {
		return Minecraft.theMinecraft.isMultiplayerWorld();
	}

	public boolean isSneaking() {
		return getMCPlayer().isSneaking();
	}

	public void setSneaking(boolean sneak) {
		getMCPlayer().setFlag(1, sneak);
	}
}
