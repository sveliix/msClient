package msClient.command;

import java.util.ArrayList;

import msClient.main;
import msClient.command.commands.CWList;
import msClient.command.commands.Cw;
import msClient.command.commands.EloRechner;
import msClient.command.commands.Help;
import msClient.command.commands.Sneak;
import msClient.mlgHelper.Rechner;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class CommandManager {

	public static ArrayList<Command> commands;
	
	public CommandManager() {
		commands = new ArrayList();
		addCommand(new Help());
		addCommand(new Sneak());
		addCommand(new Cw());
		addCommand(new CWList());
		addCommand(new EloRechner());
		addCommand(new Command() {
			
			@Override
			public void onCommand(String command, String[] args) throws Exception {
				// TODO Auto-generated method stub
				if(args.length<3) {
					
					if(Integer.valueOf(args[0]) < Integer.valueOf(args[1])) {
						int t = Integer.valueOf(args[0]);
						args[0] = args[1];
						args[1] = String.valueOf(t);
					}
					
					if(Rechner.calcMLG(Integer.valueOf(args[0]), Integer.valueOf(args[1])) == null){
						main.addChatMessage("zu hohe/niedrige Differenz");
					}else {
						main.addChatMessage(args[0] + " zu " + args[1] + ": " + Rechner.calcMLG(Integer.valueOf(args[0]), Integer.valueOf(args[1])));
					}
				}else {
					main.addChatMessage("Invalid Command Usage!");
					main.addChatMessage(getSyntax());
				}
			}
			
			@Override
			public String getSyntax() {
				// TODO Auto-generated method stub
				return ".mlg [Höhe Spieler] [Mlg Höhe]";
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "MLG-Rechner";
			}
			
			@Override
			public String getAlias() {
				// TODO Auto-generated method stub
				return "mlg";
			}
		});
	}
	
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	public ArrayList<Command> getCommands(){
		return commands;
	}
	
	public void callCommand(String input) {
		String[] split = input.split(" ");
		String command = split[0];
		String args = input.substring(command.length()).trim();
		for(Command c: getCommands()) {
			if(c.getAlias().equalsIgnoreCase(command)) {
				try {
					c.onCommand(args, args.split(" "));
				}catch(Exception e) {
					main.addChatMessage("Invalid Command Usage!");
					main.addChatMessage(c.getSyntax());
				}
				return;
			}
		}
		main.addChatMessage("Command not found!");
	}
	
}
