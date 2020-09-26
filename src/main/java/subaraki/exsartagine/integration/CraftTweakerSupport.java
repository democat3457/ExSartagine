package subaraki.exsartagine.integration;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.block.IBlockState;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import subaraki.exsartagine.recipe.FryingPanRecipes;
import subaraki.exsartagine.recipe.PotRecipes;
import subaraki.exsartagine.recipe.Recipes;
import subaraki.exsartagine.util.Reference;

@ZenRegister
@ZenClass("mods."+ Reference.MODID)
public class CraftTweakerSupport {

	@ZenMethod
	public static void addPotRecipe (IIngredient input, IItemStack output) {
		PotRecipes.addRecipe(CraftTweakerMC.getIngredient(input), CraftTweakerMC.getItemStack(output));
	}

	@ZenMethod
	public static void addPanRecipe (IIngredient input, IItemStack output) {
		FryingPanRecipes.addRecipe(CraftTweakerMC.getIngredient(input), CraftTweakerMC.getItemStack(output));
	}

	@ZenMethod
	public static void addHeatSource (IBlockState source) {
		Recipes.addHeatSource(CraftTweakerMC.getBlockState(source).getBlock());
	}

	@ZenMethod
	public static void addPlaceable (IBlockState source) {
		Recipes.addPlaceable(CraftTweakerMC.getBlockState(source).getBlock());
	}


}