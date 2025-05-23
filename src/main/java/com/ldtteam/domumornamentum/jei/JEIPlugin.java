package com.ldtteam.domumornamentum.jei;

import com.ldtteam.domumornamentum.IDomumOrnamentumApi;
import com.ldtteam.domumornamentum.block.IModBlocks;
import com.ldtteam.domumornamentum.recipe.ModRecipeSerializers;
import com.ldtteam.domumornamentum.recipe.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.ldtteam.domumornamentum.util.Constants.MOD_ID;

@JeiPlugin
public class JEIPlugin implements IModPlugin
{
    private IIngredientManager ingredientManager;

    @Nullable
    public IIngredientManager getIngredientManager()
    {
        return this.ingredientManager;
    }

    @NotNull
    @Override
    public ResourceLocation getPluginUid()
    {
        return new ResourceLocation(MOD_ID);
    }

    @Override
    public void registerItemSubtypes(@NotNull final ISubtypeRegistration registration)
    {
        final IModBlocks blocks = IDomumOrnamentumApi.getInstance().getBlocks();
        final MaterialSubtypeInterpreter interpreter = MaterialSubtypeInterpreter.getInstance();

        registration.registerSubtypeInterpreter(blocks.getDoor().asItem(), interpreter);
        registration.registerSubtypeInterpreter(blocks.getTrapdoor().asItem(), interpreter);
        registration.registerSubtypeInterpreter(blocks.getFancyDoor().asItem(), interpreter);
        registration.registerSubtypeInterpreter(blocks.getFancyTrapdoor().asItem(), interpreter);
    }

    @Override
    public void registerCategories(@NotNull final IRecipeCategoryRegistration registration)
    {
        final ArchitectsCutterCategory category = new ArchitectsCutterCategory(registration.getJeiHelpers().getGuiHelper(), this);
        registration.addRecipeCategories(category);
    }

    @Override
    public void registerRecipes(@NotNull final IRecipeRegistration registration)
    {
        final RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        registration.addRecipes(recipeManager.getAllRecipesFor(ModRecipeTypes.ARCHITECTS_CUTTER), ModRecipeSerializers.ARCHITECTS_CUTTER.getRegistryName());
    }

    @Override
    public void registerRecipeCatalysts(@NotNull final IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(
                new ItemStack(IDomumOrnamentumApi.getInstance().getBlocks().getArchitectsCutter()),
                ModRecipeSerializers.ARCHITECTS_CUTTER.getRegistryName());
    }

    @Override
    public void onRuntimeAvailable(@NotNull final IJeiRuntime jeiRuntime)
    {
        this.ingredientManager = jeiRuntime.getIngredientManager();
    }
}
