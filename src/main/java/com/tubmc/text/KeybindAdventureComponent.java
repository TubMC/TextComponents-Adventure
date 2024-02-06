package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.KeybindComponent;

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
final class KeybindAdventureComponent extends AbstractBaseAdventureComponent<KeybindComponent> implements IKeybindComponent {

	KeybindAdventureComponent(@NotNull final KeybindComponent component) {
		super(component);
	}
	
	@Override
	public final @Nullable @DisallowsEmptyString String getKeybind() {
		return this.getProxiedObject().keybind();
	}

	@Override
	public final void setKeybind(final @Nullable @DisallowsEmptyString String newKeybind) {
		this.internal = this.getProxiedObject().keybind(newKeybind);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull KeybindAdventureComponent clone() {
		return new KeybindAdventureComponent((KeybindComponent)this.internal);
	}
	
	@Override
	public final @NotNull KeybindComponent getProxiedObject() {
		return (KeybindComponent) this.internal;
	}

}
