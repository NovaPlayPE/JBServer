package net.novaplay.jbserver.utils.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import com.github.steveice10.mc.auth.data.GameProfile;
import com.github.steveice10.mc.auth.data.GameProfile.Property;

public class GameProfileData {
	
	private UUID uuid = null;
	private String username;
	private UUID skinUUID = null;
	private Property skin = null;
	
	private GameProfileData(GameProfile profile) {
		this.uuid = profile.getId();
		this.username = profile.getName();
		this.skinUUID = this.uuid;
		if(profile.getProperties().get(0) == null) {
			profile.getProperties().add(this.skin = decodeSkinData(this.skinUUID = getDecodedSkinUuid(this.username)));
		} else {
			this.skin = profile.getProperties().get(0);
		}
	}
	
	public static GameProfileData read(GameProfile profile) {
		return new GameProfileData(profile);
	}
	
	private Property decodeSkinData(UUID uuid) {
		if(uuid == null) {
			return null;
		}
		Property property = null;
		try {
			URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid.toString().replaceAll("-","")+"?unsigned=false");
			BufferedReader buf = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String v = "";
			String signature = "";
			int i = 0;
			for(char c : buf.readLine().toCharArray()) {
				if(c == '"') {
					i++;
				} else if(i == 17) {
					v += c;
				} else if(i == 21) {
					signature += c;
				}
				if(i > 21) {
					break;
				}
			}
			buf.close();
			property = new Property("textures",v,signature);
		} catch(Exception e) {}
		return property;
	}
	
	private UUID getDecodedSkinUuid(String name) {
		if(name == null) {
			return null;
		}
		UUID uid = null;
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/"+name);
			BufferedReader buf = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			String id = "";
			int i = 0;
			for(char c : buf.readLine().toCharArray()) {
				if(c == '"') {
					i++;
				} else if(i == 3) {
					id +=c;
				}
				if(i > 3) {
					break;
				}
			}
			uuid = UUID.fromString(id.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
			buf.close();
		} catch (Exception e) {}
		return uuid;
	}

}
