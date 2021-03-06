package de.pfannekuchen.lotas.dropmanipulation.drops.blockdrops;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import de.pfannekuchen.lotas.core.MCVer;
import de.pfannekuchen.lotas.gui.DropManipulationScreen;
import de.pfannekuchen.lotas.gui.widgets.ImageButton;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class LeaveDropManipulation extends DropManipulationScreen.DropManipulation {

	public static ImageButton dropApple = new ImageButton(x, y, c -> {
		LeaveDropManipulation.dropApple.setToggled(!LeaveDropManipulation.dropApple.isToggled());
	}, new Identifier("lotas", "drops/apple.png"));
	public static ImageButton dropStick = new ImageButton(x, y, c -> {
		LeaveDropManipulation.dropStick.setToggled(!LeaveDropManipulation.dropStick.isToggled());
	}, new Identifier("lotas", "drops/stick.png"));
	public static ImageButton dropSapling = new ImageButton(x, y, c -> {
		LeaveDropManipulation.dropSapling.setToggled(!LeaveDropManipulation.dropSapling.isToggled());
	}, new Identifier("lotas", "drops/sapling.png"));

	public LeaveDropManipulation(int x, int y, int width, int height) {
		LeaveDropManipulation.x = x;
		LeaveDropManipulation.y = y;
		LeaveDropManipulation.width = width;
		LeaveDropManipulation.height = height;
		enabled = MCVer.CheckboxWidget(x, y, 150, 20, "Override Leave Drops", false);
	}

	@Override
	public String getName() {
		return "Leaves";
	}

	@Override
	public List<ItemStack> redirectDrops(BlockState block) {
		List<ItemStack> list = new ArrayList<>();
		if (Blocks.OAK_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.OAK_SAPLING));
		} else if (Blocks.BIRCH_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.BIRCH_SAPLING));
		} else if (Blocks.SPRUCE_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.SPRUCE_SAPLING));
		} else if (Blocks.JUNGLE_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.JUNGLE_SAPLING));
		} else if (Blocks.DARK_OAK_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.DARK_OAK_SAPLING));
		} else if (Blocks.ACACIA_LEAVES.equals(block.getBlock().getDefaultState().getBlock())) {
			if (dropSapling.isToggled())
				list.add(new ItemStack(Items.ACACIA_SAPLING));
		} else {
			return ImmutableList.of();
		}
		if (dropApple.isToggled() && (Blocks.OAK_LEAVES.equals(block.getBlock().getDefaultState().getBlock()) || Blocks.DARK_OAK_LEAVES.equals(block.getBlock().getDefaultState().getBlock())))
			list.add(new ItemStack(Items.APPLE));
		if (dropStick.isToggled())
			list.add(new ItemStack(Items.STICK));
		return list;
	}

	@Override
	public List<ItemStack> redirectDrops(Entity entity) {
		return ImmutableList.of();
	}

	@Override
	public void update() {
		enabled.x = x;
		enabled.y = y;
		dropApple.x = x;
		dropApple.y = y + 96;
		dropStick.x = x + 22;
		dropStick.y = y + 96;
		dropSapling.x = x + 44;
		dropSapling.y = y + 96;
	}

	@Override
	public void mouseAction(double mouseX, double mouseY, int button) {
		enabled.mouseClicked(mouseX, mouseY, button);
		if (enabled.isChecked()) {
			dropApple.mouseClicked(mouseX, mouseY, button);
			dropStick.mouseClicked(mouseX, mouseY, button);
			dropSapling.mouseClicked(mouseX, mouseY, button);
		}
	}

	@Override
	public void render(Object matrices, int mouseX, int mouseY, float delta) {
		MCVer.render(enabled, mouseX, mouseY, delta);

		if (!enabled.isChecked()) {
			MCVer.color(.5f, .5f, .5f, .4f);
		} else {
			MCVer.drawStringWithShadow("Leaves drop:" + (dropApple.isToggled() ? " 1 Apple" : "") + (dropStick.isToggled() ? " 1 Stick" : "") + (dropSapling.isToggled() ? " 1 Sapling" : ""), x, y + 64, 0xFFFFFF);
			MCVer.render(dropApple, mouseX, mouseY, delta);
			MCVer.render(dropStick, mouseX, mouseY, delta);
			MCVer.render(dropSapling, mouseX, mouseY, delta);
		}

		MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("lotas", "drops/leave.png"));
		MCVer.renderImage(width - 128, y + 24, 0.0F, 0.0F, 96, 96, 96, 96);
	}

}
