package com.tubmc.text;

import java.awt.Color;
import java.util.Collection;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.tubmc.text.interaction.ClickInteraction;
import com.tubmc.text.interaction.ClickType;
import com.tubmc.text.interaction.EntityHoverData;
import com.tubmc.text.interaction.HoverInteraction;
import com.tubmc.text.interaction.HoverType;
import com.tubmc.text.interaction.ItemHoverData;

import fun.bb1.objects.defineables.ITypedProxy;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEvent.ShowEntity;
import net.kyori.adventure.text.event.HoverEvent.ShowItem;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

@Internal
abstract class AbstractBaseAdventureComponent<C extends Component> implements ImplementationComponentBase, ITypedProxy<C> {
		
	protected @NotNull Component internal;
	
	protected AbstractBaseAdventureComponent(final @NotNull C component) {
		this.internal = component;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable Color getColor() {
		final TextColor color = this.internal.color();
		return color == null ? null : new Color(color.red(), color.green(), color.blue());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setColor(@Nullable final Color newColor) {
		if (newColor == null) {
			this.internal = this.internal.color(null);
			return;
		}
		this.internal = this.internal.color(TextColor.color(newColor.getRed(), newColor.getGreen(), newColor.getBlue()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isBold() {
		return this.internal.hasDecoration(TextDecoration.BOLD);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setBold(final boolean isBold) {
		this.internal = this.internal.decoration(TextDecoration.BOLD, (isBold) ? TextDecoration.State.TRUE : TextDecoration.State.FALSE);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isItalic() {
		return this.internal.hasDecoration(TextDecoration.ITALIC);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setItalic(final boolean isItalic) {
		this.internal = this.internal.decoration(TextDecoration.ITALIC, (isItalic) ? TextDecoration.State.TRUE : TextDecoration.State.FALSE);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isUnderlined() {
		return this.internal.hasDecoration(TextDecoration.UNDERLINED);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUnderlined(final boolean isUnderlined) {
		this.internal = this.internal.decoration(TextDecoration.UNDERLINED, (isUnderlined) ? TextDecoration.State.TRUE : TextDecoration.State.FALSE);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isStrikedThrough() {
		return this.internal.hasDecoration(TextDecoration.STRIKETHROUGH);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setStrikeThrough(final boolean isStrikedThrough) {
		this.internal = this.internal.decoration(TextDecoration.STRIKETHROUGH, (isStrikedThrough) ? TextDecoration.State.TRUE : TextDecoration.State.FALSE);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isObfuscated() {
		return this.internal.hasDecoration(TextDecoration.OBFUSCATED);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setObfuscated(final boolean isObfuscated) {
		this.internal = this.internal.decoration(TextDecoration.OBFUSCATED, (isObfuscated) ? TextDecoration.State.TRUE : TextDecoration.State.FALSE);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable String getFont() {
		return this.internal.font().asString();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFont(@Nullable final String newFont) {
		this.internal = this.internal.font(Key.key(newFont));
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final @Nullable ClickInteraction getClicked() {
		if (this.internal.clickEvent() == null) return null;
		final ClickEvent click = this.internal.clickEvent();
		return new ClickInteraction(switch(click.action()) {
			case CHANGE_PAGE -> ClickType.GOTO_PAGE;
			case COPY_TO_CLIPBOARD -> ClickType.CLIPBOARD;
			case OPEN_FILE -> ClickType.OPEN_FILE;
			case OPEN_URL -> ClickType.OPEN_URL;
			case RUN_COMMAND -> ClickType.EXECUTE;
			case SUGGEST_COMMAND -> ClickType.SUGGEST;
			default -> throw new IllegalArgumentException("Unexpected value: " + this.internal.clickEvent().action());
		}, click.value());
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public final void setClicked(@Nullable final ClickInteraction interaction) {
		if (interaction == null) {
			this.internal = this.internal.clickEvent(null);
			return;
		}
		this.internal = this.internal.clickEvent(switch (interaction.type()) {
			case CLIPBOARD -> ClickEvent.copyToClipboard(interaction.data());
			case EXECUTE -> ClickEvent.runCommand(interaction.data());
			case GOTO_PAGE -> ClickEvent.changePage(interaction.data());
			case OPEN_FILE -> ClickEvent.openFile(interaction.data());
			case OPEN_URL -> ClickEvent.openUrl(interaction.data());
			case SUGGEST -> ClickEvent.suggestCommand(interaction.data());
			default -> throw new IllegalArgumentException("Unexpected value: " + interaction.type());
		});
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable HoverInteraction<?> getHovering() {
		if (this.internal.hoverEvent() == null) return null;
		final HoverEvent<?> hover = this.internal.hoverEvent();
		switch (HoverEvent.Action.NAMES.key(hover.action())) {
			case "show_text": return new HoverInteraction<IComponent>(HoverType.TEXT, AdventureTextImplementation.wrapComponent((Component)hover.value()));
			case "show_item": 
				final ShowItem item = (ShowItem) hover.value();
				return new HoverInteraction<ItemHoverData>(HoverType.ITEM, new ItemHoverData(item.item().asString(), item.count(), item.nbt().string()));
			case "show_entity":
				final ShowEntity entity = (ShowEntity) hover.value();
				return new HoverInteraction<EntityHoverData>(HoverType.ENTITY, new EntityHoverData(AdventureTextImplementation.wrapComponent(entity.name()), entity.type().asString(), entity.id()));
			default: throw new IllegalArgumentException("Unknown hover type, " + HoverEvent.Action.NAMES.key(hover.action()));
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setHovering(@Nullable final HoverInteraction<?> interaction) {
		if (interaction == null) {
			this.internal = this.internal.hoverEvent(null);
			return;
		}
		if (interaction.data() instanceof final EntityHoverData entity) {
			this.internal = this.internal.hoverEvent(HoverEvent.showEntity(Key.key(entity.typeIdentifier()), entity.entityUUID(), AdventureTextImplementation.unwrapComponent(entity.entityName())));
			return;
		}
		if (interaction.data() instanceof final ItemHoverData item) {
			this.internal = this.internal.hoverEvent(HoverEvent.showItem(Key.key(item.itemIdentifier()), item.count(), BinaryTagHolder.binaryTagHolder(item.nbtTagAsString())));
			return;
		}
		this.internal = this.internal.hoverEvent(HoverEvent.showText(AdventureTextImplementation.unwrapComponent((IComponent)interaction.data())));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @Nullable Collection<@NotNull IComponent> getChildren() {
		return this.internal.children().stream().map(AdventureTextImplementation::wrapComponent).toList();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setChildren(@NotNull final Collection<@NotNull IComponent> newChildrenComponents) {
		this.internal = this.internal.children(newChildrenComponents.stream().map(AdventureTextImplementation::unwrapComponent).toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract @NotNull AbstractBaseAdventureComponent<C> clone();
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final @NotNull AbstractBaseAdventureComponent<C> deepClone() {
		return this.clone();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract @NotNull C getProxiedObject();
}