package china.moondrinkwind.minecraft.shop.util;

import io.loyloy.fe.API;
import io.loyloy.fe.Fe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @Author MoonDrinkWind
 * @Description Player Money Account Class
 * */
public class PlayerMoneyAccount {
    private API FeAPI = new API((Fe) Bukkit.getPluginManager().getPlugin("Fe"));

    public void addMoney(Player player,Double money){
        FeAPI.getAccount(player.getName(),player.getUniqueId().toString()).setMoney(
                FeAPI.getAccount(player.getName(),player.getUniqueId().toString()).getMoney()+money);
    }

    public void removeMoney(Player player,Double money){
        FeAPI.getAccount(player.getName(),player.getUniqueId().toString()).setMoney(
                FeAPI.getAccount(player.getName(),player.getUniqueId().toString()).getMoney()-money);
    }

    public Double getMoney(Player player){
        return FeAPI.getAccount(player.getName(),player.getUniqueId().toString()).getMoney();
    }
}
