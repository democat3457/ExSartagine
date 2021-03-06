package subaraki.exsartagine.integration.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import subaraki.exsartagine.integration.jei.wrappers.FryingPanRecipeWrapper;
import subaraki.exsartagine.recipe.FryingPanRecipe;
import subaraki.exsartagine.recipe.Recipes;

import java.util.List;
import java.util.stream.Collectors;

public class FryingPanRecipeCategory extends AbstractCookingRecipeCategory<FryingPanRecipeWrapper> {

    // Textures

    protected static final int inputSlot = 0;
    protected static final int fuelSlot = 1;
    protected static final int outputSlot = 2;

    protected IDrawableStatic staticFlame;


    public FryingPanRecipeCategory(ItemStack catalyst, IGuiHelper help) {
        super(catalyst, help);
    }

    @Override
    public void setupGui() {
        background = guiHelper.createDrawable(BACKGROUNDS, 0, 115, 82, 54);
        staticFlame = guiHelper.drawableBuilder(BACKGROUNDS, 120, 0, 22, 15).build();
        cookProgress = guiHelper.createAnimatedDrawable(staticFlame, 100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        cookProgress.draw(minecraft, 24, 18);
    }

    @Override
    public void setupRecipes(IModRegistry registry) {
        List<FryingPanRecipeWrapper> recipes = Recipes.getRecipes("pan").stream()
                .map(fryingPanRecipe -> new FryingPanRecipeWrapper((FryingPanRecipe) fryingPanRecipe, registry.getJeiHelpers())).collect(Collectors.toList());
        registry.addRecipes(recipes, getUid());
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, FryingPanRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(inputSlot, true, 0, 0);
        guiItemStacks.init(outputSlot, false, 60, 18);

        guiItemStacks.set(ingredients);
    }
}
