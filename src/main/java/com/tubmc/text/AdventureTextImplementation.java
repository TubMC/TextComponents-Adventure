package com.tubmc.text;

import java.util.Collection;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.defineables.ITypedProxy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.KeybindComponent;
import net.kyori.adventure.text.ScoreComponent;
import net.kyori.adventure.text.SelectorComponent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.TextDecoration;

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
@Internal
final class AdventureTextImplementation extends AbstractImplementation {

	@Contract("null -> null")
	@Internal
	static @Nullable final IComponent wrapComponent(@Nullable final Component toWrap) {
		if (toWrap == null) return null;
		if (toWrap instanceof ScoreComponent component) return new ScoreboardAdventureComponent(component);
		if (toWrap instanceof KeybindComponent component) return new KeybindAdventureComponent(component);
		if (toWrap instanceof TextComponent component) return new LiteralAdventureComponent(component);
		if (toWrap instanceof SelectorComponent component) return new SelectorAdventureComponent(component);
		if (toWrap instanceof TranslatableComponent component) return new TranslatableAdventureComponent(component);
		throw new IllegalArgumentException("Unexpected wrap type: " + toWrap.getClass().getName());
	}
	
	@SuppressWarnings("deprecation")
	@Contract("null -> null")
	@Internal
	static @Nullable final Component unwrapComponent(@Nullable final IComponent toUnwrap) {
		if (toUnwrap == null) return null;
		// All AbstractBaseSpigotComponent's descend from ITypedProxy where the proxied object is a BaseComponent
		// So rather than building a new object we can grab it's proxied BaseComponent
		if (toUnwrap instanceof ITypedProxy && ((ITypedProxy<?>)toUnwrap).getProxiedObject() instanceof Component) return (Component) ((ITypedProxy<?>)toUnwrap).getProxiedObject();
		Component toReturn = (toUnwrap instanceof IScoreboardComponent component) ? Component.score(component.getSelector(), component.getObjective(), component.getValue())
				: (toUnwrap instanceof IKeybindComponent component) ? Component.keybind(component.getKeybind())
				: (toUnwrap instanceof ILiteralComponent component) ? Component.text(component.getMessage())
				: (toUnwrap instanceof ISelectedComponent component) ? Component.selector(component.getSelector(), component.getSeperator())
				: (toUnwrap instanceof ITranslatableComponent component) ? Component.translatable(component.getTranslationKey(), component.getTranslationFallback(), component.getInsertions().stream().map(AdventureTextImplementation::unwrapComponent).toList())
				: null;
		if (toReturn == null) throw new IllegalArgumentException("Provided IComponent is not valid! " + toUnwrap.getClass().getName());
		if (toUnwrap.getChildren() != null && toUnwrap.getChildren().size() != 0) {
			toReturn = toReturn.children(toUnwrap.getChildren().stream().map(AdventureTextImplementation::unwrapComponent).toList());
		}
		if (toUnwrap.isBold()) toReturn = toReturn.decorate(TextDecoration.BOLD);
		if (toUnwrap.isItalic()) toReturn = toReturn.decorate(TextDecoration.ITALIC);
		if (toUnwrap.isUnderlined()) toReturn = toReturn.decorate(TextDecoration.UNDERLINED);
		if (toUnwrap.isObfuscated()) toReturn = toReturn.decorate(TextDecoration.OBFUSCATED);
		if (toUnwrap.isStrikedThrough()) toReturn = toReturn.decorate(TextDecoration.STRIKETHROUGH);
		return toReturn;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final IKeybindComponent createKeybind(@NotNull final String keybind) {
		return new KeybindAdventureComponent(Component.keybind(keybind));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ILiteralComponent createLiteral(@NotNull final String text) {
		return new LiteralAdventureComponent(Component.text(text));
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public @NotNull final IScoreboardComponent createScoreboard(@NotNull final String selector, @NotNull final String objective, @Nullable final String value) {
		return new ScoreboardAdventureComponent(Component.score(selector, objective, value));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ISelectedComponent createSelector(@NotNull final String selector, @Nullable final IComponent seperator) {
		return new SelectorAdventureComponent(Component.selector(selector, unwrapComponent(seperator)));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public @NotNull final ITranslatableComponent createTranslatable(@NotNull final String translationKey, @Nullable final String fallback, @Nullable final Collection<@NotNull IComponent> insertions) {
		return new TranslatableAdventureComponent(Component.translatable(translationKey, fallback, insertions == null ? null : insertions.stream().map(AdventureTextImplementation::unwrapComponent).toList()));
	}
}
