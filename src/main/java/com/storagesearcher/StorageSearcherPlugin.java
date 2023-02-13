package com.storagesearcher;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.storagesearcher.ui.StorageSearcherPluginPanel;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import java.awt.image.BufferedImage;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.ui.NavigationButton;

@Slf4j
@PluginDescriptor(
	name = "Storage Searcher",
	description = "Runelite Plugin to search all your storage for what you have in them"
)
public class StorageSearcherPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private StorageSearcherConfig config;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ClientToolbar clientToolbar;

	@Inject
	private TooltipManager tooltipManager;

	private static boolean prepared = false;
	private NavigationButton navButton;
	private StorageSearcherPluginPanel panel;
	private static final BufferedImage ICON = ImageUtil.loadImageResource(StorageSearcherPlugin.class, "/icon.png");
	@Override
	protected void startUp() throws Exception
	{
		log.info("Storage Searcher started!");
		panel = new StorageSearcherPluginPanel(client, tooltipManager, config, itemManager);
		navButton = NavigationButton.builder()
				.tooltip("Storage Searcher")
				.priority(8)
				.icon(ICON)
				.panel(panel)
				.build();
		clientToolbar.addNavigation(navButton);

		if (!prepared)
		{
			clientThread.invoke(() ->
			{
				switch (client.getGameState())
				{
					case LOGIN_SCREEN:
					case LOGIN_SCREEN_AUTHENTICATOR:
					case LOGGING_IN:
					case LOADING:
					case LOGGED_IN:
					case CONNECTION_LOST:
					case HOPPING:
						//StorableItem.prepareStorableItemNames(itemManager);
						prepared = true;
						return true;
					default:
						return false;
				}
			});
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Storage Searcher stopped!");
		clientToolbar.removeNavigation(navButton);
		panel = null;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Storage Searcher says " + config.greeting(), null);
		}
	}

	@Provides
	StorageSearcherConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(StorageSearcherConfig.class);
	}
}
