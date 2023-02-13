package com.storagesearcher;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("storagesearcher")
public interface StorageSearcherConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}


	@ConfigItem(
			keyName = "hideEmptyStorage",
			name = "Hide Empty Storage",
			description = "Automatically hide storage that has nothing in it"
	)
	default boolean hideEmptyStorage()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showBank",
			name = "Show Bank",
			description = "Show the Bank storage"
	)
	default boolean showBank()
	{
		return true;
	}

	@ConfigItem(
			keyName = "showSeedBank",
			name = "Show Seed Bank",
			description = "Show Seed Bank storage from the Farming Guild"
	)
	default boolean showSeedBank()
	{
		return true;
	}
}
