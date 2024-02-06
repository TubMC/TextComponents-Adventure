package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;

/**
 * Adds functionality to all {@link IComponent}'s
 * 
 * @author BradBot_1
 * @since 1.0.0
 * @version 1.0.0
 * @see IComponent
 */
@Internal
sealed interface ImplementationSpecificComponentMethods extends ComponentLike permits IComponent {
	
	@Override
	default @NotNull Component asComponent() {
		return this.toAdventure();
	}
	
	public default @NotNull Component toAdventure() {
		return AdventureTextImplementation.unwrapComponent((IComponent)this);
	}
	
}