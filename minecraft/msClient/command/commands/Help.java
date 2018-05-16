package msClient.command.commands;

import msClient.main;
import msClient.command.Command;
import msClient.command.CommandManager;

public class Help extends Command{

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "help";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Help";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return ".help";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		for(Command o : CommandManager.commands) {
			main.addChatMessage(o.getSyntax());
		}
		
	}

}
