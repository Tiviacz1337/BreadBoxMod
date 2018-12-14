package com.tiviacz.breadbox.objects;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBreadBox extends Block implements ITileEntityProvider
{
	public static final PropertyBool OPEN = PropertyBool.create("open");
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final AxisAlignedBB BREAD_BOX_AABB_NS = new AxisAlignedBB(0.0625D, 0.0D, 0.21875D, 0.9375D, 0.3125D, 0.78125D);
	public static final AxisAlignedBB BREAD_BOX_AABB_WE = new AxisAlignedBB(0.21875D, 0.0D, 0.0625D, 0.78125D, 0.3125D, 0.9375D);
	
	public BlockBreadBox(String name, Material material) 
	{
		super(material);
		
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(SoundType.WOOD);
		setHardness(1.0F);
		setResistance(3.0F);
		setHarvestLevel("hand", 0);
		setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false)); 
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) 
	{
		if(playerIn.isSneaking())
		{
			state = state.cycleProperty(OPEN);
            worldIn.setBlockState(pos, state, 2);
			playSound(playerIn, worldIn, pos, state.getValue(OPEN));
		}
		
		if(!worldIn.isRemote)
		{
			ItemStack helditem = playerIn.getHeldItem(hand);
			TileEntityBreadBox te = (TileEntityBreadBox)worldIn.getTileEntity(pos);
			
			if(state.getValue(OPEN))
			{
				if(!playerIn.isSneaking())
				{
					if(helditem.getItem() == Items.BREAD)
					{
						if(!te.isFull())
						{
							helditem.shrink(1);
							te.addBread();
						}
					}
					
					else if(helditem.isEmpty())
					{
						if(!te.isEmpty())
						{
							playerIn.inventory.addItemStackToInventory(new ItemStack(Items.BREAD));
							te.removeBread();
						}
					}
				}
			}
		}
		
		return true;
	}
	
	private void playSound(EntityPlayer playerIn, World worldIn, BlockPos pos, boolean open)
    {
        if(open)
        {
        	worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
        else
        {
        	worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }
    }
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		TileEntityBreadBox te = (TileEntityBreadBox)worldIn.getTileEntity(pos);
		
		if(te instanceof TileEntityBreadBox)
		{
			int x = te.breadCount;
			
			if(!worldIn.isRemote)
			{
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BREAD, x));
			}
		}
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		switch(state.getValue(FACING))
        {
            case NORTH:
                return BREAD_BOX_AABB_NS;
            case SOUTH:
                return BREAD_BOX_AABB_NS;
            case EAST:
                return BREAD_BOX_AABB_WE;
            case WEST:
                return BREAD_BOX_AABB_WE;
		default:
			return BREAD_BOX_AABB_WE;
        }
    }
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT;
    }
	
	@Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
	
	@Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(OPEN, (meta & 4) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
    	int i = 0;
        i = i | getMetaForFacing(state.getValue(FACING));
        
        if(state.getValue(OPEN))
        {
        	i |= 4;
        }
        
        return i;
    }
    
    private static EnumFacing getFacing(int meta)
    {
        switch(meta & 3)
        {
            case 0:
                return EnumFacing.NORTH;
            case 1:
                return EnumFacing.SOUTH;
            case 2:
                return EnumFacing.WEST;
            case 3:
            default:
                return EnumFacing.EAST;
        }
    }

    private static int getMetaForFacing(EnumFacing facing)
    {
        switch(facing)
        {
            case NORTH:
                return 0;
            case SOUTH:
                return 1;
            case WEST:
                return 2;
            case EAST:
            default:
                return 3;
        }
    }
    
    @Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) 
	{
		return getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(OPEN, false);
	}
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {OPEN, FACING});
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new TileEntityBreadBox();
	}
}