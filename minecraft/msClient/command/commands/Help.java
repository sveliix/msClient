package msClient.command.commands;

import msClient.main;
import msClient.command.Command;
import msClient.command.CommandManager;

public class Help extends Command{

	@Override
	public String getAlias() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "Help";
	}

	@Override
	public String getSyntax() {
		return ".help";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		
		for(Command o : CommandManager.commands) {
			main.addChatMessage(o.getSyntax());
		}
		
	}

}
