package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.SelectorComponent;

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
