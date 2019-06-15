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


    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("Shop")){
            Player player = (Player)sender;
            if(args.length == 3){
                if(args[0].equalsIgnoreCase("Buy")){
                    if(configHandler.getBuyPrice(args[1]) == 0){
                        player.sendMessage("[商店/提示]：物品不存在");
                    }else{
                        Double price = configHandler.getBuyPrice(args[1]);
                        int quantity = Integer.parseInt(args[2]);
                        if(playerMoneyAccount.getMoney(player) >= price * quantity){
                            Material itemMaterial = configHandler.getItem(args[0]);
                            playerInventory.addItem(player,itemMaterial,quantity);
                            playerMoneyAccount.removeMoney(player,price * quantity);
                            player.sendMessage("[商店/成功]：成功购买 本次花费: "+price*quantity);
                        }else{
                            player.sendMessage("[商店/失败]：你的口袋里的钱还不够");
                        }
                    }
                }else if(args[0].equalsIgnoreCase("Sell")){
                    if(configHandler.getSellPrice(args[1]) == 0){
                        player.sendMessage("[商店/提示]：物品不存在");
                    }else{

                    }
                }else{
                    return false;
                }
            }else if(args.length == 2){

            }else{
                return false;
            }
        }
        return true;
    }
}
