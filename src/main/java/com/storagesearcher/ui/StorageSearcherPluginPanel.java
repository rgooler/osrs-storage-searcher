package com.storagesearcher.ui;

import com.storagesearcher.util.Constants;
import lombok.extern.slf4j.Slf4j;
import com.storagesearcher.StorageSearcherConfig;
import net.runelite.api.Client;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import javax.swing.border.EmptyBorder;
import java.awt.*;

@Slf4j
public class StorageSearcherPluginPanel extends PluginPanel {

    public StorageSearcherPluginPanel(Client client, TooltipManager tooltipManager, StorageSearcherConfig config, ItemManager itemManager)
    {
        super();
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
    }
}
