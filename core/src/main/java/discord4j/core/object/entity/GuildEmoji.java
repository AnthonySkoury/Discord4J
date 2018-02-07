/*
 * This file is part of Discord4J.
 *
 * Discord4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Discord4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Discord4J.  If not, see <http://www.gnu.org/licenses/>.
 */
package discord4j.core.object.entity;

import discord4j.common.json.response.EmojiResponse;
import discord4j.core.Client;
import discord4j.core.object.Snowflake;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A Discord guild emoji.
 *
 * <a href="https://discordapp.com/developers/docs/resources/emoji#emoji-resource">Emoji Resource</a>
 */
public final class GuildEmoji implements Entity {

	/** The Client associated to this object. */
	private final Client client;

	/** The raw data as represented by Discord. */
	private final EmojiResponse emoji;

	/** The ID of the guild this emoji is associated to. */
	private final long guildId;

	/**
	 * Constructs a {@code GuildEmoji} with an associated client and Discord data.
	 *
	 * @param client The Client associated to this object, must be non-null.
	 * @param emoji The raw data as represented by Discord, must be non-null.
	 * @param guildId The ID of the guild this emoji is associated to.
	 */
	public GuildEmoji(final Client client, final EmojiResponse emoji, final long guildId) {
		this.client = Objects.requireNonNull(client);
		this.emoji = Objects.requireNonNull(emoji);
		this.guildId = guildId;
	}

	@Override
	public Client getClient() {
		return client;
	}

	@Override
	public Snowflake getId() {
		if (emoji.getId() == null) throw new IllegalStateException();
		return Snowflake.of(emoji.getId());
	}

	/**
	 * Gets the emoji name.
	 *
	 * @return The emoji name.
	 */
	public String getName() {
		return emoji.getName();
	}

	/**
	 * Gets the IDs of the roles this emoji is whitelisted to.
	 *
	 * @return The IDs of the roles this emoji is whitelisted to.
	 */
	public Set<Snowflake> getRoleIds() {
		return Optional.ofNullable(emoji.getRoles())
				.map(Arrays::stream)
				.map(roles -> roles.mapToObj(Snowflake::of))
				.map(snowflakes -> snowflakes.collect(Collectors.toSet()))
				.orElse(Collections.emptySet());
	}

	/**
	 * Requests to retrieve the roles this emoji is whitelisted to.
	 *
	 * @return A {@link Flux} that continually emits the {@link Role roles} this emoji is whitelisted for. if an error
	 * is received, it is emitted through the {@code Flux}.
	 */
	public Flux<Role> getRoles() {
		throw new UnsupportedOperationException("Not yet implemented...");
	}

	/**
	 * Gets the ID of the user that created this emoji.
	 *
	 * @return The ID of the user that created this emoji.
	 */
	public Snowflake getUserId() {
		return Optional.ofNullable(emoji.getUser())
				.map(user -> Snowflake.of(user.getId()))
				.orElseThrow(IllegalStateException::new);
	}

	/**
	 * Requests to retrieve the user that created this emoji.
	 *
	 * @return A {@link Mono} where, upon successful completion, emits the {@link User user} that created this emoji. If
	 * an error is received, it is emitted through the {@code Mono}.
	 */
	public Mono<User> getUser() {
		throw new UnsupportedOperationException("Not yet implemented...");
	}

	/**
	 * Gets whether this emoji must be wrapped in colons.
	 *
	 * @return {@code true} if this emoji must be wrapped in colons, {@code false} otherwise.
	 */
	public boolean requireColons() {
		return Optional.ofNullable(emoji.isRequireColons()).orElseThrow(IllegalStateException::new);
	}

	/**
	 * Gets whether this emoji is managed.
	 *
	 * @return {@code true} if this emoji is managed, {@code false} otherwise.
	 */
	public boolean isManaged() {
		return Optional.ofNullable(emoji.isManaged()).orElseThrow(IllegalStateException::new);
	}

	/**
	 * Gets whether this emoji is animated.
	 *
	 * @return {@code true} if this emoji is animated, {@code false} otherwise.
	 */
	public boolean isAnimated() {
		return Optional.ofNullable(emoji.isAnimated()).orElseThrow(IllegalStateException::new);
	}

	/**
	 * Gets the ID of the guild this emoji is associated to.
	 *
	 * @return The ID of the guild this emoji is associated to.
	 */
	public Snowflake getGuildId() {
		return Snowflake.of(guildId);
	}

	/**
	 * Requests to retrieve the guild this emoji is associated to.
	 *
	 * @return A {@link Mono} where, upon successful completion, emits the {@link Guild guild} this emoji is associated
	 * to. If an error is received, it is emitted through the {@code Mono}.
	 */
	public Mono<Guild> getGuild() {
		throw new UnsupportedOperationException("Not yet implemented...");
	}
}
