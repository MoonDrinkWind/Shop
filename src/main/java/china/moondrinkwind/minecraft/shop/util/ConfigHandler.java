package china.moondrinkwind.minecraft.shop.util;

import china.moondrinkwind.minecraft.shop.Shop;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @Author MoonDrinkWind
 * @Description Config Handler Class
 * */
public class ConfigHandler {
    private Shop plugin = Shop.getInstance();
    private FileConfiguration config = plugin.getConfig();

    public void addItem(String displayName,Double buyPrice,Double sellPrice,String ID){
        config.set(displayName+".buyPrice",buyPrice);
        config.set(displayName+".sellPrice",sellPrice);
        config.set(displayName+".buyPrice",ID.toString().replace("Material.",""));
        plugin.saveConfig();
    }

    public void removeItem(String displayName){
        config.set(displayName,null);
        plugin.saveConfig();
    }

    public Material getItem(String displayName){
       return Material.getMaterial(config.getString(displayName+".ID"));
    }

    public Double getBuyPrice(String displayName){
        return config.getDouble(displayName+".buyPrice");
    }

    public Double getSellPrice(String displayName){
        return config.getDouble(displayName+".sellPrice");
    }
}
