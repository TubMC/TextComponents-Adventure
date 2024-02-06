package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.TextComponent;

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
