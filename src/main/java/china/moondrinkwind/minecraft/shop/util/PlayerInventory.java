package china.moondrinkwind.minecraft.shop.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @Author MoonDrinkWind
 * @Description Player Inventory Class
 * */
public class PlayerInventory {
    public int countAir(Player player){
        Inventory playerInventory = player.getInventory();
        int inventorySize = playerInventory.getSize();
        int airQuantity = 0;
        for(int i = 0; i < inventorySize; i++){
            if(playerInventory.getItem(i).getType().equals(Material.AIR)){
                airQuantity += 1;
            }
        }
        return airQuantity;
    }

    public void addItem(Player player,Material itemMaterial,int quantity){
        Inventory playerInventory = player.getInventory();
        int multiple = quantity / 64;
        int remainder = quantity % 64;
        for(int i = 0; i < multiple; i++){
            playerInventory.addItem(new ItemStack(itemMaterial,64));
        }
        if(remainder != 0){
            playerInventory.addItem(new ItemStack(itemMaterial,remainder));
        }
    }

    public void removeItem(Player player,Material targetItem,int quantity){
        Inventory playerInventory = player.getInventory();
        int inventorySize = playerInventory.getSize();
        for(int i = 0; i < inventorySize; i++){
            if(playerInventory.getItem(i).getType().equals(targetItem)){
                quantity -= playerInventory.getItem(i).getAmount();
                playerInventory.setItem(i,new ItemStack(Material.AIR));
            }
        }
    }

    public int countItem(Player player,Material targetItem){
        Inventory playerInventory = player.getInventory();
        int inventorySize = playerInventory.getSize();
        int quantity = 0;
        for(int i = 0; i < inventorySize; i++){
            if(playerInventory.getItem(i).getType().equals(targetItem)){
                quantity += playerInventory.getItem(i).getAmount();
            }
        }
        return quantity;
    }
}
