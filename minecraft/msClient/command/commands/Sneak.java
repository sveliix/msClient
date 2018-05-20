package msClient.command.commands;

import msClient.command.Command;
import msClient.config.Vbs;

public class Sneak extends Command{

	@Override
	public String getAlias() {
		return "sneak";
	}

	@Override
	public String getDescription() {
		return "Sneak";
	}

	@Override
	public String getSyntax() {
		return ".sneak";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		
		Vbs.showSneak = !Vbs.showSneak;
		
	}

}
