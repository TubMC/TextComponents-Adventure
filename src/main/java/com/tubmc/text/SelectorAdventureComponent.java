package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.SelectorComponent;

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
final class SelectorAdventureComponent extends AbstractBaseAdventureComponent<SelectorComponent> implements ISelectedComponent {

	SelectorAdventureComponent(@NotNull final SelectorComponent component) {
		super(component);
	}
	
	@Override
	public final @NotNull @DisallowsEmptyString String getSelector() {
		return this.getProxiedObject().pattern();
	}

	@Override
	public final void setSelector(final @NotNull @DisallowsEmptyString String newSelector) {
		this.internal = this.getProxiedObject().pattern(newSelector);
	}

	@Override
	public final @Nullable IComponent getSeperator() {
		return AdventureTextImplementation.wrapComponent(this.getProxiedObject().separator());
	}

	@Override
	public final void setSeperator(final @Nullable IComponent newSeperator) {
		this.internal = this.getProxiedObject().separator(AdventureTextImplementation.unwrapComponent(newSeperator));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull SelectorAdventureComponent clone() {
		return new SelectorAdventureComponent((SelectorComponent)this.internal);
	}
	
	@Override
	public final @NotNull SelectorComponent getProxiedObject() {
		return (SelectorComponent) this.internal;
	}
}
