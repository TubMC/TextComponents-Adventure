package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.ScoreComponent;

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
final class ScoreboardAdventureComponent extends AbstractBaseAdventureComponent<ScoreComponent> implements IScoreboardComponent {

	ScoreboardAdventureComponent(@NotNull final ScoreComponent component) {
		super(component);
	}
	
	@Override
	public final @NotNull @DisallowsEmptyString String getSelector() {
		return this.getProxiedObject().name();
	}

	@Override
	public final void setSelector(final @NotNull @DisallowsEmptyString String newSelector) {
		this.internal = this.getProxiedObject().name(newSelector);
	}

	@Override
	public final @NotNull @DisallowsEmptyString String getObjective() {
		return this.getProxiedObject().objective();
	}

	@Override
	public final void setObjective(final @NotNull @DisallowsEmptyString String newObjective) {
		this.internal = this.getProxiedObject().objective(newObjective);
	}

	@SuppressWarnings("deprecation")
	@Override
	public final @Nullable @DisallowsEmptyString String getValue() {
		return this.getProxiedObject().value();
	}

	@SuppressWarnings("deprecation")
	@Override
	public final void setValue(final @Nullable @DisallowsEmptyString String newValue) {
		this.internal = this.getProxiedObject().value(newValue);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull ScoreboardAdventureComponent clone() {
		return new ScoreboardAdventureComponent((ScoreComponent)this.internal);
	}
	
	@Override
	public final @NotNull ScoreComponent getProxiedObject() {
		return (ScoreComponent) this.internal;
	}
}
