package com.bgsoftware.superiorskyblock.commands.admin;

import com.bgsoftware.superiorskyblock.SuperiorSkyblockPlugin;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.key.Key;
import com.bgsoftware.superiorskyblock.api.wrappers.SuperiorPlayer;
import com.bgsoftware.superiorskyblock.commands.CommandTabCompletes;
import com.bgsoftware.superiorskyblock.commands.IAdminIslandCommand;
import com.bgsoftware.superiorskyblock.commands.arguments.CommandArguments;
import com.bgsoftware.superiorskyblock.commands.arguments.NumberArgument;
import com.bgsoftware.superiorskyblock.key.KeyImpl;
import com.bgsoftware.superiorskyblock.lang.Message;
import com.bgsoftware.superiorskyblock.threads.Executor;
import com.bgsoftware.superiorskyblock.utils.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CmdAdminSetEntityLimit implements IAdminIslandCommand {

    @Override
    public List<String> getAliases() {
        return Collections.singletonList("setentitylimit");
    }

    @Override
    public String getPermission() {
        return "superior.admin.setentitylimit";
    }

    @Override
    public String getUsage(java.util.Locale locale) {
        return "admin setentitylimit <" +
                Message.COMMAND_ARGUMENT_PLAYER_NAME.getMessage(locale) + "/" +
                Message.COMMAND_ARGUMENT_ISLAND_NAME.getMessage(locale) + "/" +
                Message.COMMAND_ARGUMENT_ALL_ISLANDS.getMessage(locale) + "> <" +
                Message.COMMAND_ARGUMENT_ENTITY.getMessage(locale) + "> <" +
                Message.COMMAND_ARGUMENT_LIMIT.getMessage(locale) + ">";
    }

    @Override
    public String getDescription(java.util.Locale locale) {
        return Message.COMMAND_DESCRIPTION_ADMIN_SET_ENTITY_LIMIT.getMessage(locale);
    }

    @Override
    public int getMinArgs() {
        return 5;
    }

    @Override
    public int getMaxArgs() {
        return 5;
    }

    @Override
    public boolean canBeExecutedByConsole() {
        return true;
    }

    @Override
    public boolean supportMultipleIslands() {
        return true;
    }

    @Override
    public void execute(SuperiorSkyblockPlugin plugin, CommandSender sender, SuperiorPlayer targetPlayer, List<Island> islands, String[] args) {
        Key entityKey = KeyImpl.of(args[3]);

        NumberArgument<Integer> arguments = CommandArguments.getLimit(sender, args[4]);

        if (!arguments.isSucceed())
            return;

        int limit = arguments.getNumber();

        Executor.data(() -> islands.forEach(island -> island.setEntityLimit(entityKey, limit)));

        if (islands.size() > 1)
            Message.CHANGED_ENTITY_LIMIT_ALL.send(sender, StringUtils.format(entityKey.getGlobalKey()));
        else if (targetPlayer == null)
            Message.CHANGED_ENTITY_LIMIT_NAME.send(sender, StringUtils.format(entityKey.getGlobalKey()), islands.get(0).getName());
        else
            Message.CHANGED_ENTITY_LIMIT.send(sender, StringUtils.format(entityKey.getGlobalKey()), targetPlayer.getName());
    }

    @Override
    public List<String> adminTabComplete(SuperiorSkyblockPlugin plugin, CommandSender sender, Island island, String[] args) {
        return args.length == 4 ? CommandTabCompletes.getEntitiesForLimit(args[3]) : new ArrayList<>();
    }

}
