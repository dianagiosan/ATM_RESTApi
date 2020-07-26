package com.example.ATMProject.Infrastructure.Config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum MyFeatures implements Feature {
	
	@EnabledByDefault
	@Label("First Feature")
	WITHDRAW_DRAGOS,
	@Label("Second Feature")
	WITHDRAW_ADELINA;
	
	public boolean isActive() {
		return FeatureContext.getFeatureManager().isActive(this);
	}
	
}