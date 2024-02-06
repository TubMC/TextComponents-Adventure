package com.tubmc.text;

import java.util.Collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.AllowsEmptyString;
import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.TranslationArgument;

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
final class TranslatableAdventureComponent extends AbstractBaseAdventureComponent<TranslatableComponent> implements ITranslatableComponent{

	TranslatableAdventureComponent(@NotNull final TranslatableComponent component) {
		super(component);
	}
	
	@Override
	public final @NotNull @DisallowsEmptyString String getTranslationKey() {
		return this.getProxiedObject().key();
	}

	@Override
	public final void setTranslationKey(final @NotNull @DisallowsEmptyString String newTranslationKey) {
		this.internal = this.getProxiedObject().key(newTranslationKey);
	}

	@Override
	public final @Nullable @AllowsEmptyString String getTranslationFallback() {
		return this.getProxiedObject().fallback();
	}

	@Override
	public final void setTranslationFallback(final @Nullable @AllowsEmptyString String newFallback) {
		this.internal = this.getProxiedObject().fallback(newFallback);
	}

	@Override
	public final @Nullable Collection<@NotNull IComponent> getInsertions() {
		return this.getProxiedObject().arguments().stream()
				.map(TranslationArgument::value)
				// force all to be components
				.map(e -> e instanceof Component c ? c : AdventureTextImplementation.IMPLEMENTATION.createLiteral(e.toString()).asComponent())
				.map(AdventureTextImplementation::wrapComponent).toList();
	}

	@Override
	public final void setInsertions(final @NotNull Collection<@NotNull IComponent> newInsertions) {
		this.internal = this.getProxiedObject().arguments(newInsertions.stream().map(AdventureTextImplementation::unwrapComponent).toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull TranslatableAdventureComponent clone() {
		return new TranslatableAdventureComponent((TranslatableComponent)this.internal);
	}
	
	@Override
	public final @NotNull TranslatableComponent getProxiedObject() {
		return (TranslatableComponent) this.internal;
	}
}
