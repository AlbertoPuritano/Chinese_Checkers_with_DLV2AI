package com.winnie.the.pooh;

import java.util.ArrayList;


import it.unical.mat.embasp.base.*;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import javafx.util.Pair;

public class AI {
	private static Handler handler;
	private String dlvPath;
	private InputProgram program;
	private String intP1;
	private String intP2;
	private String intP1Fase2;
	private String intP2Fase2;
	public AI() {
		dlvPath= "dlv2.exe";
		intP1= "encodings/intP1.dlv";
		intP2= "encodings/intP2.dlv";
		intP1Fase2= "encodings/intP1Fase2.dlv";
		intP2Fase2= "encodings/intP2Fase2.dlv";
		handler = new DesktopHandler(new DLV2DesktopService(dlvPath));
		program = new ASPInputProgram();
	}
	public void clear()
	{
		handler.removeAll();
		program.clearAll();
	}
	public void loadFacts(ArrayList<Mossa> moves)
	{
		try {
			for (Mossa m: moves)
              	program.addObjectInput(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
      	handler.addProgram(program);
	}
	public void loadEncoding(int choice)
	{
		switch(choice)
		{
			case 1:
				program.addFilesPath(intP1);
				break;
			case 2:
				program.addFilesPath(intP2);
				break;
			case 3:
				program.addFilesPath(intP1Fase2);
				break;
			case 4:
				program.addFilesPath(intP2Fase2);
				break;
		}
	}
    public AnswerSets getAnswersets(boolean choice)
    {
		OptionDescriptor option= new OptionDescriptor("-n=0 ");
		if (choice)
			handler.addOption(option);
		Output o = handler.startSync();
        AnswerSets answers = (AnswerSets) o;
		System.out.println(answers.getAnswerSetsString());
		//System.out.println("answeset size " + answers.getAnswersets().size());
		handler.removeOption(option);
        return answers;
    }
}
