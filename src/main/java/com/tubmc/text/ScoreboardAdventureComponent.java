package com.tubmc.text;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import fun.bb1.objects.annotations.DisallowsEmptyString;
import net.kyori.adventure.text.ScoreComponent;

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
