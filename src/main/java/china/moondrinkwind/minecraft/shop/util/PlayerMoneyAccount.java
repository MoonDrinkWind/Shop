package china.moondrinkwind.minecraft.shop.util;

import org.bukkit.entity.Player;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @Author MoonDrinkWind
 * @Description Player Money Account Class
 * */
public class PlayerMoneyAccount {
    String url = "jdbc:mysql://localhost:3306/minecraft?useSSL=false&serverTimeZone=Shanghai";
    String clazz = "com.mysql.cj.jdbc.Driver";
    public long getMoney(Player player){
        long money = 0;
        try{
            Class.forName(clazz);
            PreparedStatement ps = DriverManager.getConnection(url).prepareStatement("select Balance from cmi_users where username = ?");
            ps.setString(1,player.getName());
            money = ps.executeQuery().getLong("Balance");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return money;
    }

    public void addMoney(Player player,long quantity){
        try{
            Class.forName(clazz);
            PreparedStatement ps = DriverManager.getConnection(url).prepareStatement("update cmi_users set Balance = ? where username = ?");
            ps.setLong(1,getMoney(player)+quantity);
            ps.setString(2,player.getName());
            ps.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void removeMoney(Player player,long quantity){
        try{
            Class.forName(clazz);
            PreparedStatement ps = DriverManager.getConnection(url).prepareStatement("update cmi_users set Balance = ? where username = ?");
            ps.setLong(1,getMoney(player)-quantity);
            ps.setString(2,player.getName());
            ps.execute();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
