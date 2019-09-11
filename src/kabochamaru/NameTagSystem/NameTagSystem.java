package kabochamaru.NameTagSystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NameTagSystem extends JavaPlugin implements Listener{
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("�v���O�C�����L���ɂȂ�܂���");
		saveDefaultConfig();
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		FileConfiguration config =getConfig();
		if(config.contains(name)) {
			if(config.getString(name) != null) {
				String tag = config.getString(name);
				player.setCustomName("��b[��f"+tag+"��b]��f "+name);
				player.setDisplayName("��b[��f"+tag+"��b]��f "+name);
				player.setPlayerListName("��b[��f"+tag+"��b]��f "+name);
			}
		}else {
			config.set(name, null);
			saveConfig();
		}
	}
	public boolean onCommand(CommandSender sender, Command command, String Label, String[] args){
		switch(command.getName()){
		case "tag":
			try {
				FileConfiguration config =getConfig();
				if (args[0] != null) {
					switch (args[0]) {
					case "set":
						try {
							if (args[1] != null) {
								 Player player = getServer().getPlayer(args[1]);
								 if (player != null) {
									 if (args[2] != null) {
										 String name = player.getName();
										 config.set(name, args[2]);
										 saveConfig();
										 player.setCustomName("��b[��f"+args[2]+"��b]��f "+name);
										 player.setDisplayName("��b[��f"+args[2]+"��b]��f "+name);
										 player.setPlayerListName("��b[��f"+args[2]+"��b]��f "+name);
										 sender.sendMessage("��f>>>��b[INFO]��f "+name+" ��tag����b[��f"+args[2]+"��b]��f�ύX���܂���");
									 }else {
										 sender.sendMessage("��f>>>/tag set [���O] [TAG]| tag�ݒ�");
									 }
								 }else {
									   sender.sendMessage("��f>>>��b[INFO]��f����PLAYER�̓T�[�o�[�ɂ��܂���");
								   }
							}
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("��f>>>/tag set [���O] [TAG]| tag�ݒ�");			
					}
						break;
					case "del":
						try {
							if (args[1] != null) {
								 Player player = getServer().getPlayer(args[1]);
								 if (player != null) {
										 String name = player.getName();
										 config.set(name, null);
										 saveConfig();
										 player.setCustomName(name);
										 player.setDisplayName(name);
										 player.setPlayerListName(name);
										 sender.sendMessage("��f>>>��b[INFO]��f "+name+" ��tag���폜���܂���");
									
								 }else {
									   sender.sendMessage("��f>>>��b[INFO]��f����PLAYER�̓T�[�o�[�ɂ��܂���");
								   }
							}
						}
						catch(ArrayIndexOutOfBoundsException e){
							sender.sendMessage("��f>>>/tag del [���O] [TAG]| tag�폜");				
					}		
						break;
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e){
				sender.sendMessage("��f>>>/tag set [���O] [TAG]| tag�ݒ�");
				sender.sendMessage("��f>>>/tag del [���O] [TAG]| tag�폜");				
		}		
	}
		return false;
	}
}


