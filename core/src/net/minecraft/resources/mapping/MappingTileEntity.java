package net.minecraft.resources.mapping;

import net.minecraft.tileentity.TileEntity;

public class MappingTileEntity extends AbstractMapping<Class<? extends TileEntity>> {

	public MappingTileEntity(int id, String address, Class<? extends TileEntity> overridden, Class<? extends TileEntity> actual) {
		super(address, overridden, actual);
	}

	@Override
	public void map(Class<? extends TileEntity> element) {
		TileEntity.unregister(address);
		if (element != null) TileEntity.register(element, address);
	}

}
