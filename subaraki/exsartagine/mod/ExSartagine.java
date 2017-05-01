package subaraki.exsartagine.mod;

import java.util.Arrays;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import subaraki.exsartagine.block.ExSartagineBlock;
import subaraki.exsartagine.gui.GuiHandler;
import subaraki.exsartagine.item.ExSartagineItems;
import subaraki.exsartagine.proxy.ServerProxy;
import subaraki.telepads.handler.ConfigurationHandler;
import subaraki.telepads.item.TelepadItems;

@Mod(name = ExSartagine.NAME, modid = ExSartagine.MODID, version = ExSartagine.VERSION, dependencies = ExSartagine.DEPENDENCY)
public class ExSartagine {

	public static final String MODID = "exsartagine";
	public static final String NAME = "exsartagine mod";
	public static final String VERSION = "1.11 0.0.0.1";
	public static final String DEPENDENCY = "required-after:subcommonlib";
	
	@SidedProxy(serverSide = "subaraki.exsartagine.proxy.ServerProxy" , clientSide = "subaraki.exsartagine.proxy.ClientProxy")
	public static ServerProxy proxy;
	
	@Instance(MODID)
	public static ExSartagine instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		ModMetadata modMeta = event.getModMetadata();
		modMeta.authorList = Arrays.asList(new String[] { "Subaraki" });
		modMeta.autogenerated = false;
		modMeta.credits = "";
		modMeta.description = "Thermo Efficient Pan";
		modMeta.url = "https://github.com/ArtixAllMighty/ExSartagine/wiki";
		
		instance = this;
		
		ExSartagineBlock.load();
		ExSartagineItems.load();
		
		proxy.registerTileEntityAndRenderer();
		proxy.registerRenders();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		////////////////////////////////////////////////////////////////////////
		GameRegistry.addRecipe(new ItemStack(ExSartagineItems.pan), new Object[]{
				"xxx","IxS","III",
				'I',Items.IRON_INGOT,
				'S',Items.STICK
		});
	}
}
