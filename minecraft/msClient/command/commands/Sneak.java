package msClient.command.commands;

import msClient.command.Command;
import msClient.config.Variables;

public class Sneak extends Command{

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "sneak";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Sneak";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return ".sneak";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Variables.showSneak = !Variables.showSneak;
		
	}

}
