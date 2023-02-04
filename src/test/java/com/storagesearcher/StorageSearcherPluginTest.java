package com.storagesearcher;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class StorageSearcherPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(StorageSearcherPlugin.class);
		RuneLite.main(args);
	}
}