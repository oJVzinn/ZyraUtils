package com.zyramc.ojvzinn.utils.commands;

import com.zyramc.ojvzinn.utils.utils.SlimeJumperManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SlimeJumperCommand extends Commands {

    protected SlimeJumperCommand() {
        super("slime", true);
    }

    @Override
    void perfomaceExecute(CommandSender sender, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("utils.slime")) {
            player.sendMessage("§cPara executar este comando, é necessário o grupo §4Gerente §cou superior.");
            return;
        }

        if (args.length < 2) {
            player.sendMessage("§cUtilize /slime [add/remove] [id] ");
            return;
        }

        String action = args[0];
        String id = args[1];

        switch (action) {

            case "add": {
                if (SlimeJumperManager.findById(id) != null) {
                    player.sendMessage("§cTente utilizar outro id! Esse já existe.");
                    return;
                }

                SlimeJumperManager.createSlimeJumper(id, player.getLocation().getBlock().getLocation());
                player.sendMessage("§aSlime Jump criado com sucesso!");
                break;
            }

            case "remove": {
                if (SlimeJumperManager.findById(id) == null) {
                    player.sendMessage("§cTente utilizar outro id! Esse não existe.");
                    return;
                }

                SlimeJumperManager.deleteSlimeJumper(id);
                player.sendMessage("§aSlime Jump deletado com sucesso!");
                break;
            }
        }

        player.sendMessage("§cUtilize /slime [add/remove] [id] ");
    }
}
