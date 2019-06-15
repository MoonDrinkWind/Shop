package china.moondrinkwind.minecraft.shop;

import china.moondrinkwind.minecraft.shop.util.ConfigHandler;
import china.moondrinkwind.minecraft.shop.util.PlayerInventory;
import china.moondrinkwind.minecraft.shop.util.PlayerMoneyAccount;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @Author MoonDrinkWind
 * @Description Plugin Main Class
 * */
public final class Shop extends JavaPlugin {
    private static Shop self;
    private ConfigHandler configHandler;
    private PlayerInventory playerInventory;
    private PlayerMoneyAccount playerMoneyAccount;

    public Shop(){
        self = this;
    }

    public static Shop getInstance(){
        return self;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveConfig();
        getLogger().info("商店/提示]：插件启动");
    }

    @Override
    public void onDisable() {
        getLogger().info("商店/提示]：插件关闭");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("Shop")){
            Player player = (Player)sender;
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("Buy")){
                    if(configHandler.getBuyPrice(args[1]) == 0){
                        player.sendMessage("§a[商店/提示]：物品不存在");
                    }else{
                        Double price = configHandler.getBuyPrice(args[1]);
                        int quantity = Integer.parseInt(args[2]);
                        if(playerMoneyAccount.getMoney(player) >= price * quantity){
                            int airQuantity = playerInventory.countAir(player);
                            int multiple = quantity / 64;
                            if(!((quantity % 64) == 0)){
                                multiple += 1;
                            }
                            if(airQuantity <= multiple){
                                Material itemMaterial = configHandler.getItem(args[0]);
                                playerInventory.addItem(player,itemMaterial,quantity);
                                playerMoneyAccount.removeMoney(player,price * quantity);
                                player.sendMessage("§a[商店/成功]：成功购买 本次花费: "+price*quantity);
                            }else{
                                player.sendMessage("§a[商店/失败]：背包空间不足");
                            }
                        }else{
                            player.sendMessage("§a[商店/失败]：你的口袋里的钱还不够");
                        }
                    }
                }else if(args[0].equalsIgnoreCase("Sell")){
                    if(configHandler.getSellPrice(args[1]) == 0){
                        player.sendMessage("§a[商店/提示]：物品不存在");
                    }else{
                        Material targetItem = configHandler.getItem(args[1]);
                        int itemQuantity = playerInventory.countItem(player,targetItem);
                        int targetQuantity = Integer.parseInt(args[2]);
                        Double price = configHandler.getSellPrice(args[0]);
                        if(itemQuantity >= targetQuantity){
                            playerInventory.removeItem(player,targetItem,targetQuantity);
                            playerMoneyAccount.addMoney(player,price * targetQuantity);
                            player.sendMessage("§a[商店/成功]：出售成功 本次获得："+price * targetQuantity);
                        }else{
                            player.sendMessage("§a[商店/失败]：背包中物品低于目标数量");
                        }
                    }
                }
                }else if(args.length == 4){
                    if(args[0].equalsIgnoreCase("add")){
                        if(!(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))){
                            configHandler.addItem(args[1],Double.parseDouble(args[2]),Double.parseDouble(args[3]),player.
                                    getInventory().getItemInMainHand().getType().toString());
                            player.sendMessage("§e[商店/成功]：行了");
                            reloadConfig();
                        }else{
                            player.sendMessage("§e[商店/失败]：瞧你大伙乐的 空气能卖？");
                        }
                    }else{
                        return false;
                    }
                }else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("remove")){
                        configHandler.removeItem(args[1]);
                        player.sendMessage("§e[商店/成功]：行了");
                        reloadConfig();
                    }else{
                        return false;
                    }
                } else{
                    return false;
                }
            }
        return true;
    }
}
