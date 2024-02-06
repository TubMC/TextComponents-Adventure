package com.tubmc.text;

public final class Entrypoint {
	
	public static final void register() {
		// implementations autoregister themselves
		if (AbstractImplementation.IMPLEMENTATION == null) {
			new AdventureTextImplementation();
		}
	}
	
}
