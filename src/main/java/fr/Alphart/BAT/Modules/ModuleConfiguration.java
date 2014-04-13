package fr.Alphart.BAT.Modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;
import fr.Alphart.BAT.BAT;

public class ModuleConfiguration extends Config {

	public ModuleConfiguration(final String moduleName) {
		CONFIG_HEADER = new String[] { "BungeeAdminTools - " + moduleName + " configuration file" };
		CONFIG_FILE = new File(BAT.getInstance().getDataFolder(), moduleName + ".yml");
		try {
			init();
		} catch (final InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Getter
	private boolean enabled = true;

	private Map<String, Boolean> commands = new LinkedHashMap<String, Boolean>();

	/**
	 * Get the names of the enabled commands for this module
	 * 
	 * @return list of the enabled commands
	 */
	public List<String> getEnabledCmds() {
		final List<String> enabledCmds = new ArrayList<String>();
		for (final Entry<String, Boolean> entry : commands.entrySet()) {
			if (entry.getValue()) {
				enabledCmds.add(entry.getKey());
			}
		}
		return enabledCmds;
	}

	/**
	 * Add commands provided by this module into the configuration file
	 * 
	 * @param commands
	 *            list
	 */
	public void addDefaultCmds(final List<String> cmds) {
		Collections.sort(cmds);
		for (final String command : cmds) {
			if (!commands.containsKey(command)) {
				commands.put(command, true);
			}
		}
	}
}