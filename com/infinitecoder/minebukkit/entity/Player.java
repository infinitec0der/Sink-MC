package com.infinitecoder.minebukkit.entity;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

public class Player {
	
	private UUID uuid;
	private String name;
	private EntityPlayerMP nmsEntity;
	
	public Player(EntityPlayerMP nmsEntity) {
		this.nmsEntity = nmsEntity;
		this.name = nmsEntity.getCommandSenderName();
		this.uuid = nmsEntity.getUniqueID();
	}
	
	public void sendMessage(ChatComponentText message) {
		nmsEntity.addChatComponentMessage(message);
	}
	
	@Deprecated
	public void sendMessage(String message) {
		sendMessage(new ChatComponentText(message));
	}
	
	public UUID getUniqueID() {
		return uuid;
	}
	
	public String getName() {
		return name;
	}
	
	@Deprecated
	public EntityPlayerMP getNMSEntity() {
		return nmsEntity;
	}
	
}
