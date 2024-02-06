package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.KeybindComponent;

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
