package msClient.command.commands;

import msClient.main;
import msClient.command.Command;
import msClient.mlgHelper.Rechner;

public class EloRechner extends Command{

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return "elo";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Elo-Rechner";
	}

	@Override
	public String getSyntax() {
		// TODO Auto-generated method stub
		return ".elo [Elo 1] [Elo 2]";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(Integer.valueOf(args[0]) + "  " + args[1]);
		if(args.length<3) {					
			
			if(calcElo(Integer.valueOf(args[0]), Integer.valueOf(args[1])) == null){
				main.addChatMessage("zu hohe/niedrige Differenz");
			}else {
				main.addChatMessage(calcElo(Integer.valueOf(args[0]), Integer.valueOf(args[1])));
				main.addChatMessage(main.instance.getTSClients());
			}
		}else {
			main.addChatMessage("Invalid Command Usage!");
			main.addChatMessage(getSyntax());
		}
	}
	
	public String calcElo(int clan1, int clan2) {
		/*double t = Math.pow(2, 5);
		System.out.println(t + " Test");*/
		double t = (clan1-clan2);
		t = t/400;
		//System.out.println("JAA " + t);
		double t1 = Math.pow(10, t);
		//System.out.println(t1);
		double t2 = Math.pow(10, ((clan2-clan1)/400));
		float e1 = (float) (1/(1+t1));
		float e2 = (float) (1/(1+t2));
		//System.out.println(e1);
		//System.out.println(e2);
		
		int l1 = Math.round( (50*(0-e1)));
		int w1 = Math.round( (50*(1-e1)));
		int l2 = Math.round( (50*(0-e1)));
		int w2 = Math.round( (50*(1-e1)));
		
		return "§4" + l1 + " §7[" + clan1 + "] " + "§2+" + w1 + " §f: §4" + l2 + " §7[" + clan2 + "] " + "§2+" + w2;
		
	}

}
