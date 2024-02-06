package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.TextComponent;

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
final class LiteralAdventureComponent extends AbstractBaseAdventureComponent<TextComponent> implements ILiteralComponent {

	LiteralAdventureComponent(@NotNull final TextComponent component) {
		super(component);
	}
	
	@Override
	public final void setMessage(@Nullable @DisallowsEmptyString final String newMessage) {
		this.internal = this.getProxiedObject().content(newMessage == null ? "" : newMessage);
	}
	
	@Override
	public final @Nullable @DisallowsEmptyString String getMessage() {
		return this.getProxiedObject().content();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull LiteralAdventureComponent clone() {
		return new LiteralAdventureComponent((TextComponent)this.internal);
	}
	
	@Override
	public final @NotNull TextComponent getProxiedObject() {
		return (TextComponent) this.internal;
	}

}
