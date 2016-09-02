package io.dimitris.spnotify;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class AppleScriptEngine {
	
	protected static AppleScriptEngine instance;
	protected ScriptEngine engine = null;
	
	public static AppleScriptEngine getInstance() {
		if (instance == null) {
			instance = new AppleScriptEngine();
		}
		return instance;
	}
	
	private AppleScriptEngine() {
		ScriptEngineManager mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("AppleScript");
	}
	
	public Object eval(String... lines) throws ScriptException {
		String script = "";
		for (String line : lines) {
			script = script + line + "\n";
		}
		try {
			return engine.eval(script);
		}
		catch (ScriptException ex) {
			throw ex;
		}
	}
	
	
}
