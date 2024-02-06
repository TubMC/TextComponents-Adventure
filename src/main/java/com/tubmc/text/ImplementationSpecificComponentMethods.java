package com.tubmc.text;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;

/**
 *    Copyright 2023-2024 TubMC.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
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
	/**
	 * Returns {@link #toAdventure()}, provided to fulfil {@link ComponentLike}
	 * 
	 * @since 1.0.0
	 * @return {@link #toAdventure()}
	 * @see #toAdventure()
	 */
	@Override
	default @NotNull Component asComponent() {
		return this.toAdventure();
	}
	/**
	 * Invokes and returns {@link AdventureTextImplementation#unwrapComponent(IComponent)}
	 * 
	 * @return {@link AdventureTextImplementation#unwrapComponent(IComponent)}
	 * @see AdventureTextImplementation#unwrapComponent(IComponent)
	 */
	public default @NotNull Component toAdventure() {
		return AdventureTextImplementation.unwrapComponent((IComponent)this);
	}
	/**
	 * Sends this message to the provided {@link Audience}
	 * 
	 * @param audience The {@link Audience} to send the chat message to
	 * @since 1.0.0
	 * @see Audience#sendMessage(ComponentLike)
	 */
	public default void sendTo(@NotNull final Audience audience) {
		audience.sendMessage(this);
	}
	/**
	 * Sends this message to the provided {@link Audience}
	 * 
	 * @param audience The {@link Audience} to send the actionbar to
	 * @since 1.0.0
	 * @see Audience#sendActionBar(ComponentLike)
	 */
	public default void sendAsActionBarTo(@NotNull final Audience audience) {
		audience.sendActionBar(this);
	}
}